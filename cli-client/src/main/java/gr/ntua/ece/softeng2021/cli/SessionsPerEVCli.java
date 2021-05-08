package gr.ntua.ece.softeng2021.cli;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import gr.ntua.ece.softeng2021.client.RestAPI;
import gr.ntua.ece.softeng2021.data.model.SessionsPerEV;//allo alla kati tetoio

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
    name="SessionsPerEV"
)
public class SessionsPerEVCli extends EVCliArgs implements Callable<Integer> {

	@Option(
		names = {"--ev"},
		required = true,
		description = "electric vehicle for sessions fetch"
	)
	protected String ev;

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
      //System.out.println(LocalDate.parse(datefrom));
      //System.out.println(LocalDate.parse(datefrom).toString());
      //System.out.println(LocalDate.parse(datefrom).toString().getClass().getSimpleName());
			List<SessionsPerEV> records = new RestAPI().
			//List<SessionsPerEV> records = new RestAPI().
            getSessionsPerEV(ev,LocalDate.parse(datefrom2),LocalDate.parse(dateto2), format, apikey);
            //getSessionsPerEV(ev,datefrom,dateto, format);
                // Do something with the records :)
            if(format.name() == "JSON"){
				JsonWriter j = new JsonWriter(new OutputStreamWriter(System.out, "UTF-8"));
                j.setIndent("  ");
                j.beginArray();
                for(SessionsPerEV rec : records){
					          j.beginObject();
                    j.name("VehicleID").value(rec.getVehicleID());
                    j.name("RequestTimestamp").value(rec.getRequestTimestamp());
                    j.name("PeriodFrom").value(rec.getPeriodFrom());
                    j.name("PeriodTo").value(rec.getPeriodTo());
                    j.name("TotalEnergyConsumed").value(rec.getTotalEnergyConsumed());
					          j.name("NumberOfVisitedPoints").value(rec.getNumberOfVisitedPoints());
                    j.name("NumberOfVehicleChargingSessions").value(rec.getNumberOfVehicleChargingSessions());
                    //j.name("SessionsPerEV").value(rec.getSessionsPerEV().toString());
                    j.name("SessionIndex").value(rec.getSessionIndex());
                    j.name("SessionID").value(rec.getSessionID());
                    j.name("EnergyProvider").value(rec.getEnergyProvider());
					          j.name("StartedOn").value(rec.getStartedOn());
					          j.name("FinishedOn").value(rec.getFinishedOn());
					          j.name("ΕnergyDelivered").value(rec.getEnergyDelivered());
					          j.name("PricePolicyRef").value(rec.getPricePolicyRef());
					          j.name("CostPerKWh").value(rec.getCostPerKWh());
					          j.name("SessionCost").value(rec.getSessionCost());
                    j.endObject();
                    j.flush();

				}
                j.endArray();
                j.flush();
                System.out.println();
                System.out.println();

                //System.out.println("Fetched " + records.size() + " session per " +point.name() + "EV");
				return 0;
            }
            else{
				CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(System.out, "UTF-8"),';',CSVWriter.NO_QUOTE_CHARACTER,CSVWriter.NO_ESCAPE_CHARACTER,"\r\n");
                String[] headers = new String[]{"VehicleID","RequestTimestamp","PeriodFrom",
                                                "PeriodTo","TotalEnergyConsumed","NumberOfVisitedPoints","NumberOfVehicleChargingSessions",//"SessionsPerEV",
                                                "SessionIndex","SessionID","EnergyProvider","StartedOn","FinishedOn","ΕnergyDelivered","PricePolicyRef","CostPerKWh","SessionCost"};
                csvWriter.writeNext(headers);
                csvWriter.flush();


                for(SessionsPerEV res: records){
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
