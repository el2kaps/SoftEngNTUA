package gr.ntua.ece.softeng2021.cli;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import gr.ntua.ece.softeng2021.client.RestAPI;
import gr.ntua.ece.softeng2021.data.model.SessionsPerStation;//allo alla kati tetoio

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
    name="SessionsPerStation"
)
public class SessionsPerStationCli extends EVCliArgs implements Callable<Integer> {

	@Option(
		names = {"--station"},
		required = true,
		description = "station for sessions fetch"
	)
	protected String station;

	@Override
    public Integer call() throws Exception {
        CommandLine cli = spec.commandLine();

        if (usageHelpRequested) {
            cli.usage(cli.getOut());
            return 0;
        }

        DateManipulator d1 = new DateManipulator(datefrom);
        DateManipulator d2 = new DateManipulator(dateto);

        String datefrom2 = d1.PrintDate();
        String dateto2 = d2.PrintDate();

        try {
          //LocalDate localDate = LocalDate.parse(dateArgs);
			List<SessionsPerStation> records = new RestAPI().
            //getSessionsPerStation(station, LocalDate.parse(datefrom),LocalDate.parse(dateto), format, apikey);
            getSessionsPerStation(station, LocalDate.parse(datefrom2),LocalDate.parse(dateto2), format, apikey);
                // Do something with the records :)
            if(format.name() == "JSON"){
				JsonWriter j = new JsonWriter(new OutputStreamWriter(System.out, "UTF-8"));
                j.setIndent("  ");
                j.beginArray();
                for(SessionsPerStation rec : records){
					          j.beginObject();
                    j.name("StationID").value(rec.getStationID());
                    j.name("Operator").value(rec.getOperator());
                    j.name("RequestTimestamp").value(rec.getRequestTimestamp());
                    j.name("PeriodFrom").value(rec.getPeriodFrom());
                    j.name("PeriodTo").value(rec.getPeriodTo());
                    j.name("TotalEnergyDelivered").value(rec.getTotalEnergyDelivered());
					          j.name("NumerOfChargingSessions").value(rec.getNumberOfChargingSessions());
                    j.name("NumberOfActivePoints").value(rec.getNumberOfActivePoints());
                    j.name("PointID").value(rec.getPointID());
                    j.name("PointSessions").value(rec.getPointSessions());
					          j.name("EnergyDelivered").value(rec.getEnergyDelivered());
                    j.endObject();
                    j.flush();
				}
                j.endArray();
                j.flush();
                System.out.println();
                System.out.println();
                //System.out.println("Fetched " + records.size() + " session per " + station.name() + "station");
				return 0;
            }
            else{
				CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(System.out, "UTF-8"),';',CSVWriter.NO_QUOTE_CHARACTER,CSVWriter.NO_ESCAPE_CHARACTER,"\r\n");
                String[] headers = new String[]{"StationID","Operator","RequestTimestamp","PeriodFrom",
                                                "PeriodTo","TotalEnergyDelivered","NumberOfActivePoints","SessionsPerStation", //SessionsPerStation
                                                "PointID","PointSessions","EnergyDelivered"};
                csvWriter.writeNext(headers);
                csvWriter.flush();

				for(SessionsPerStation res: records){
                //for(SessionsPerStation res: records){
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
