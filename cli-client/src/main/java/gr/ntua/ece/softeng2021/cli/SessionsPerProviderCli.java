package gr.ntua.ece.softeng2021.cli;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import gr.ntua.ece.softeng2021.client.RestAPI;
import gr.ntua.ece.softeng2021.data.model.SessionsPerProvider;//allo alla kati tetoio

import picocli.CommandLine;

import java.util.Optional;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.Year;
import java.util.List;
import java.io.*;
import java.util.*;
import java.util.concurrent.Callable;
import com.opencsv.CSVWriter;

import static picocli.CommandLine.Command;
import static picocli.CommandLine.Option;
@Command(
    name="SessionsPerProvider"
)
public class SessionsPerProviderCli extends EVCliArgs implements Callable<Integer> {

	@Option(
		names = {"--provider"},
		required = true,
		description = "provider for sessions fetch"
	)
	protected String provider;

	@Override
    public Integer call() throws Exception {
        CommandLine cli = spec.commandLine();

        if (usageHelpRequested) {
            cli.usage(cli.getOut());
            return 0;
        }


        try {
          DateManipulator d1 = new DateManipulator(datefrom);
          DateManipulator d2 = new DateManipulator(dateto);

          String datefrom2 = d1.PrintDate();
          String dateto2 = d2.PrintDate();
			List<SessionsPerProvider> records = new RestAPI().
            getSessionsPerProvider(provider,LocalDate.parse(datefrom2),LocalDate.parse(dateto2), format, apikey);
                // Do something with the records :)
            if(format.name() == "JSON"){
				JsonWriter j = new JsonWriter(new OutputStreamWriter(System.out, "UTF-8"));
                j.setIndent("  ");
                j.beginArray();
                for(SessionsPerProvider rec : records){
					j.beginObject();
                    j.name("ProviderID").value(rec.getProviderID());
                    j.name("ProviderName").value(rec.getProviderName());
                    j.name("StationID").value(rec.getStationID());
                    j.name("SessionID").value(rec.getSessionID());
                    j.name("VehicleID").value(rec.getVehicleID());
                    j.name("StartedOn").value(rec.getStartedOn());
                    j.name("FinishedOn").value(rec.getFinishedOn());
					j.name("EnergyDelivered").value(rec.getEnergyDelivered());
					j.name("PricePolicyRef").value(rec.getPricePolicyRef());
					j.name("CostPerKWh").value(rec.getCostPerKWh());
					j.name("TotalCost").value(rec.getTotalCost());
                    j.endObject();
                    j.flush();

				}
                j.endArray();
                j.flush();
                System.out.println();
                System.out.println();

                //System.out.println("Fetched " + records.size() + " session per "+ provider.name() + "provider");
				return 0;
            }
            else{
				CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(System.out, "UTF-8"),';',CSVWriter.NO_QUOTE_CHARACTER,CSVWriter.NO_ESCAPE_CHARACTER,"\r\n");
                String[] headers = new String[]{"ProviderID","ProviderName","StationID","SessionID",
                                                "VehicleID","StartedOn","FinishedOn","EnergyDelivered","PricePolicyRef","CostPerKWh","TotalCost"};
                csvWriter.writeNext(headers);
                csvWriter.flush();

                //final CellProcessor[] processors=atl.getProcessors();
                for(SessionsPerProvider res: records){
                  csvWriter.writeNext(res.Stringify());
                  csvWriter.flush();
				}
                return 0;
			}
        }catch (RuntimeException e) {
            cli.getOut().println(e.getMessage());
            e.printStackTrace(cli.getOut());
            return -1;
        }
      //return 0;
    }
}
