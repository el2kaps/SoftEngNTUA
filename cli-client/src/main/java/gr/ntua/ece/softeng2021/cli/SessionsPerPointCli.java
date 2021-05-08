package gr.ntua.ece.softeng2021.cli;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import gr.ntua.ece.softeng2021.client.RestAPI;
import gr.ntua.ece.softeng2021.data.model.SessionsPerPoint;//allo alla kati tetoio

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
    name="SessionsPerPoint"
)
public class SessionsPerPointCli extends EVCliArgs implements Callable<Integer> {

	@Option(
		names = {"--point"},
		required = true,
		description = "point for sessions fetch"
	)
	protected String point;

	@Override
    public Integer call() throws Exception {
        CommandLine cli = spec.commandLine();

        if (usageHelpRequested) {
            cli.usage(cli.getOut());
            return 0;
        }


        try {
          //System.out.println("hello");
          //System.out.println(apikey);
          //System.out.println("hello");

          DateManipulator d1 = new DateManipulator(datefrom);
          DateManipulator d2 = new DateManipulator(dateto);

          String datefrom2 = d1.PrintDate();
          String dateto2 = d2.PrintDate();
			List<SessionsPerPoint> records = new RestAPI().
			getSessionsPerPoint(point, LocalDate.parse(datefrom2),LocalDate.parse(dateto2), format, apikey);
			//List<SessionsPerPoint> records = new RestAPI().
            //getSessionsPerPoint(point, LocalDate.parse(dateArgs.date), format, apikey);
            //System.out.println(format.name());

            //aporw an aisthanesai tupseis otan pas kseno apikey na aggikseis

            if(format.name() == "JSON"){
              //System.out.println("GEIA SOUUUU!! EIMAI JSON ");
				JsonWriter j = new JsonWriter(new OutputStreamWriter(System.out, "UTF-8"));
                j.setIndent("  ");
                j.beginArray();

                for(SessionsPerPoint rec : records){
					j.beginObject();
                    j.name("Point").value(rec.getPoint());
                    j.name("PointOperator").value(rec.getPointOperator());
                    j.name("RequestTimestamp").value(rec.getRequestTimestamp());
                    j.name("PeriodFrom").value(rec.getPeriodFrom());
                    j.name("PeriodTo").value(rec.getPeriodTo());
                    j.name("NumberOfChargingSessions").value(rec.getNumberOfChargingSessions());
                    //auto isws na prepei na einai
					//j.name("SessionsPerPoint").value(rec.getSessionsPerPoint());
                  //  j.name("SessionIndex").value(rec.getSessionIndex());
                    j.name("SessionID").value(rec.getSessionID());
                    j.name("StartedOn").value(rec.getStartedOn());
                    j.name("FinishedOn").value(rec.getFinishedOn());
                    j.name("Protocol").value(rec.getProtocol());
					j.name("EnergyDelivered").value(rec.getEnergyDelivered());
					j.name("Payment").value(rec.getPayment());
					j.name("VehicleType").value(rec.getVehicleType());
                    j.endObject();
                    j.flush();

				}
                j.endArray();
                j.flush();
                System.out.println();
                System.out.println();

				return 0;
            }
            else{
              //System.out.println("GEIA SOUUUU!! EIMAI CSV ");
				CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(System.out, "UTF-8"),';',CSVWriter.NO_QUOTE_CHARACTER,CSVWriter.NO_ESCAPE_CHARACTER,"\r\n");
                String[] headers = new String[]{"Point","PointOperator","RequestTimestamp","PeriodFrom",
                                                "PeriodTo","NumberOfChargingSessions","SessionID", //"SessionsPerPoint",
                                                "StartedOn","FinishedOn","Protocol","EnergyDelivered","Payment","VehicleType"};
                csvWriter.writeNext(headers);
                csvWriter.flush();

				for(SessionsPerPoint res: records){
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
    }
}
