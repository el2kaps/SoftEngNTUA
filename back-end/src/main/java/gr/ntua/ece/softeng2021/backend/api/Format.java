package gr.ntua.ece.softeng2021.backend.api;

import com.google.gson.stream.JsonWriter;
import gr.ntua.ece.softeng2021.backend.api.representation.RepresentationGenerator;

import gr.ntua.ece.softeng2021.backend.data.model.RecordSessionsPerEV;
import gr.ntua.ece.softeng2021.backend.data.model.RecordSessionsPerPoint;
import gr.ntua.ece.softeng2021.backend.data.model.RecordSessionsPerProvider;
import gr.ntua.ece.softeng2021.backend.data.model.RecordSessionsPerStation;
import gr.ntua.ece.softeng2021.backend.data.model.ChargingOptionsList;
import gr.ntua.ece.softeng2021.backend.data.model.RecordHistory;
import gr.ntua.ece.softeng2021.backend.data.model.Bill;
import gr.ntua.ece.softeng2021.backend.data.model.EVInfo;
import gr.ntua.ece.softeng2021.backend.data.model.RecordDuration;
import gr.ntua.ece.softeng2021.backend.data.User;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.WriterRepresentation;
import org.restlet.resource.ResourceException;
import com.opencsv.CSVWriter;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.function.Consumer;

public enum Format implements RepresentationGenerator {
    JSON {
        public Representation generateRepresentation1(List<RecordSessionsPerPoint> result) {
            return new CustomJsonRepresentation((JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for (RecordSessionsPerPoint rec : result) {
                        w.beginObject(); // {
                        w.name("Point").value(rec.getPoint());
                        w.name("PointOperator").value(rec.getPointOperator());
                        w.name("RequestTimestamp").value(rec.getRequestTimestamp());
                        w.name("PeriodFrom").value(rec.getPeriodFrom());
                        w.name("PeriodTo").value(rec.getPeriodTo());
                        w.name("NumberOfChargingSessions").value(rec.getNumberOfChargingSessions());
                        //list
                        //SessionIndex
                        w.name("SessionIndex").value(rec.getSessionIndex());
                        w.name("SessionID").value(rec.getSessionID());
                        w.name("StartedOn").value(rec.getStartedOn());
                        w.name("FinishedOn").value(rec.getFinishedOn());
                        w.name("Protocol").value(rec.getProtocol());
                        w.name("EnergyDelivered").value(rec.getEnergyDelivered());
                        w.name("Payment").value(rec.getPayment());
                        w.name("VehicleType").value(rec.getVehicleType());
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
        public Representation generateRepresentation2(List<RecordSessionsPerStation> result) {
            return new CustomJsonRepresentation((JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for (RecordSessionsPerStation rec : result) {
                        w.beginObject(); // {
                        w.name("StationID").value(rec.getStationID());
                        w.name("Operator").value(rec.getOperator());
                        w.name("RequestTimestamp").value(rec.getRequestTimestamp());
                        w.name("PeriodFrom").value(rec.getPeriodFrom());
                        w.name("PeriodTo").value(rec.getPeriodTo());
                        w.name("TotalEnergyDelivered").value(rec.getTotalEnergyDelivered());
                        w.name("NumberOfChargingSessions").value(rec.getNumberOfChargingSessions());
                        //list
                        //SessionIndex
                        //w.name("SessionIndex").value(rec.getSessionIndex());
                        w.name("PointID").value(rec.getPointID());
                        w.name("PointSessions").value(rec.getPointSessions());
                        w.name("EnergyDelivered").value(rec.getEnergyDelivered());
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
        public Representation generateRepresentation3(List<RecordSessionsPerEV> result) {
            return new CustomJsonRepresentation((JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for (RecordSessionsPerEV rec : result) {
                        w.beginObject(); // {
                        w.name("VehicleID").value(rec.getVehicleID());
                        w.name("RequestTimestamp").value(rec.getRequestTimestamp());
                        w.name("PeriodFrom").value(rec.getPeriodFrom());
                        w.name("PeriodTo").value(rec.getPeriodTo());
                        w.name("TotalEnergyConsumed").value(rec.getTotalEnergyConsumed());
                        w.name("NumberOfVisitedPoints").value(rec.getNumberOfVisitedPoints());
                        w.name("NumberOfVehicleChargingSessions").value(rec.getNumberOfVehicleChargingSessions());
                        //list
                        w.name("SessionIndex").value(rec.getSessionIndex());
                        w.name("SessionID").value(rec.getSessionID());
                        w.name("EnergyProvider").value(rec.getEnergyProvider());
                        w.name("StartedOn").value(rec.getStartedOn());
                        w.name("FinishedOn").value(rec.getFinishedOn());
                        w.name("EnergyDelivered").value(rec.getEnergyDelivered());
                        w.name("PricePolicyRef").value(rec.getPricePolicyRef());
                        w.name("CostPerKWh").value(rec.getCostPerKWh());
                        w.name("SessionCost").value(rec.getSessionCost());
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
        public Representation generateRepresentation4(List<RecordSessionsPerProvider> result) {
            return new CustomJsonRepresentation((JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for (RecordSessionsPerProvider rec : result) {
                        w.beginObject(); // {
                        w.name("ProviderID").value(rec.getProviderID());
                        w.name("ProviderName").value(rec.getProviderName());
                        w.name("StationID").value(rec.getStationID());
                        w.name("SessionID").value(rec.getSessionID());
                        w.name("VehicleID").value(rec.getVehicleID());
                        w.name("StartedOn").value(rec.getStartedOn());
                        w.name("FinishedOn").value(rec.getFinishedOn());
                        w.name("EnergyDelivered").value(rec.getEnergyDelivered());
                        w.name("PricePolicyRef").value(rec.getPricePolicyRef());
                        w.name("CostPerKWh").value(rec.getCostPerKWh());
                        w.name("TotalCost").value(rec.getTotalCost());
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
      /*  public Representation generateRepresentation1(List<RecordSessionsPerPoint> result) {
            return new CustomJsonRepresentation((JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for (RecordSessionsPerPoint rec : result) {
                        w.beginObject(); // {
                        w.name("Point").value(rec.getPoint());
                        w.name("PointOperator").value(rec.getPointOperator());
                        w.name("RequestTimestamp").value(rec.getRequestTimestamp());
                        w.name("PeriodFrom").value(rec.getPeriodFrom());
                        w.name("PeriodTo").value(rec.getPeriodTo());
                        w.name("NumberOfChargingSessions").value(rec.getNumberOfChargingSessions());
                        //list
                        //SessionIndex
                        w.name("SessionID").value(rec.getSessionID());
                        w.name("StartedOn").value(rec.getStartedOn());
                        w.name("FinishedOn").value(rec.getFinishedOn());
                        w.name("Protocol").value(rec.getProtocol());
                        w.name("EnergyDelivered").value(rec.getEnergyDelivered());
                        w.name("Payment").value(rec.getPayment());
                        w.name("VehicleType").value(rec.getVehicleType());
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }*/
        public Representation generateRepresentation5(List<ChargingOptionsList> result) {
            return new CustomJsonRepresentation((JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for (ChargingOptionsList rec : result) {
                        w.beginObject(); // {
                        w.name("brandName").value(rec.getbrandName());
                        w.name("PricePolicyRef").value(rec.getPricePolicyRef());
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
        public Representation generateRepresentation6(List<Bill> result) {
            return new CustomJsonRepresentation((JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for (Bill rec : result) {
                        w.beginObject(); // {
                        w.name("Client Name").value(rec.getName());
                        w.name("TotalCostForMonth").value(rec.getCost());
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
        public Representation generateRepresentation7(List<RecordDuration> result) {
            return new CustomJsonRepresentation((JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for (RecordDuration rec : result) {
                        w.beginObject(); // {
                        w.name("Charging Duration in seconds").value(rec.getDuration());
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
         public Representation generateRepresentation9(List<RecordHistory> result) {
            return new CustomJsonRepresentation((JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for (RecordHistory rec : result) {
                        w.beginObject(); // {
                        w.name("VehicleID").value(rec.getVehicleID());
                        w.name("SessionID").value(rec.getSessionID());
                        w.name("PointID").value(rec.getPointID());
                        w.name("ProviderID").value(rec.getProviderID());
                        w.name("OperatorID").value(rec.getOperatorID());
                        w.name("StartedOn").value(rec.getStartedOn());
                        w.name("FinishedOn").value(rec.getFinishedOn());
                        w.name("Protocol").value(rec.getProtocol());
                        w.name("EnergyDelivered").value(rec.getEnergyDelivered());
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
         public Representation generateRepresentation8(List<EVInfo> result) {
            return new CustomJsonRepresentation((JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for (EVInfo rec : result) {
                        w.beginObject(); // {
                        w.name("VehicleID").value(rec.getVehicleID());
                        w.name("Vehicle Type").value(rec.getVehicleType());
                        w.name("Battery Capacity").value(rec.getVehicleCapacity());
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
         public Representation generateRepresentation10(List<User> result) {
            return new CustomJsonRepresentation((JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for (User rec : result) {
                        w.beginObject(); // {
                        w.name("Username:").value(rec.getUsername());
                        w.name("First Name:").value(rec.getFirstName());
                        w.name("Lase Name:").value(rec.getLastName());
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
    },
    CSV {
        public Representation generateRepresentation1(List<RecordSessionsPerPoint> result) {
            return new CustomCsvRepresentation((CSVWriter w)->{
                try{
                    String[] headers = new String[]{
                            "Point",
                            "PointOperator",
                            "RequestTimestamp",
                            "PeriodFrom",
                            "PeriodTo",
                            "NumberOfChargingSessions",
                            //"ChargingSessionsList:",
                            //"SessionIndex",
                            "SessionID",
                            "StartedOn",
                            "FinishedOn",
                            "Protocol",
                            "EnergyDelivered",
                            "Payment",
                            "VehicleType"};
                    w.writeNext(headers);
                    w.flush();
                    //RecordSessionsPerPoint re = new RecordSessionsPerPoint();
                    //final CellProcessor[] processors=atl.getProcessors();
                    for(RecordSessionsPerPoint res:result){
                        w.writeNext(res.toString2());
                        w.flush();
                    }
                } catch(IOException e){
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
        public Representation generateRepresentation2(List<RecordSessionsPerStation> result) {
            return new CustomCsvRepresentation((CSVWriter w)->{
                try{
                    String[] headers = new String[]{
                            "StationID",
                            "Operator",
                            "RequestTimestamp",
                            "PeriodFrom",
                            "PeriodTo",
                            "TotalEnergyDelivered",
                            //"ChargingSessionsList:",
                            //"SessionIndex",
                            "NumberOfChargingSessions",
                            "NumberOfActivePoints",
                            //"SessionsSummaryList:",
                            "PointID",
                            "Protocol",
                            "PointSessions",
                            "EnergyDelivered"
                    };
                    w.writeNext(headers);
                    w.flush();
                    //RecordSessionsPerStation re = new RecordSessionsPerStation();
                    //final CellProcessor[] processors=atl.getProcessors();
                    for(RecordSessionsPerStation res:result){
                        w.writeNext(res.toString2());
                        w.flush();
                    }
                } catch(IOException e){
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
        public Representation generateRepresentation3(List<RecordSessionsPerEV> result) {
            return new CustomCsvRepresentation((CSVWriter w)->{
                try{
                    String[] headers = new String[]{
                            "VehicleID",
                            "RequestTimestamp",
                            "PeriodFrom",
                            "PeriodTo",
                            "TotalEnergyConsumed",
                            "NumberOfNumberOfVisitedPoints",
                            "NumberOfVehicleChargingSessions",
                            //"VehicleChargingSessionsList:",
                            //"SessionIndex",
                            "EnergyProvider",
                            "StartedOn",
                            "FinishedOn",
                            "EnergyDelivered",
                            "EnergyDelivered",
                            "PricePolicyRef",
                            "CostPerKWh",
                            "SessionCost"
                    };
                    w.writeNext(headers);
                    w.flush();
                    //RecordSessionsPerStation re = new RecordSessionsPerStation();
                    //final CellProcessor[] processors=atl.getProcessors();
                    for(RecordSessionsPerEV res:result){
                        w.writeNext(res.toString2());
                        w.flush();
                    }
                } catch(IOException e){
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
        public Representation generateRepresentation4(List<RecordSessionsPerProvider> result) {
            return new CustomCsvRepresentation((CSVWriter w)->{
                try{
                    String[] headers = new String[]{
                            "ProviderID",
                            "ProviderName",
                            "StationID",
                            "SessionID",
                            "VehicleID",
                            "StartedOn",
                            "FinishedOn",
                            "EnergyDelivered",
                            "PricePolicyRef",
                            "CostPerKWh",
                            "TotalCost"
                    };
                    w.writeNext(headers);
                    w.flush();

                    System.out.println("EIMAI STO ARXEI FORMAT KAI ");
                    //RecordSessionsPerStation re = new RecordSessionsPerStation();
                    //final CellProcessor[] processors=atl.getProcessors();
                    for(RecordSessionsPerProvider res:result){
                        w.writeNext(res.toString2());
                        w.flush();
                    }
                } catch(IOException e){
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
        public Representation generateRepresentation5(List<ChargingOptionsList> result) {
            return new CustomCsvRepresentation((CSVWriter w)->{
                try{
                    String[] headers = new String[]{
                            "brandName",
                            "PricePolicyRef"};
                    w.writeNext(headers);
                    w.flush();
                    //RecordSessionsPerPoint re = new RecordSessionsPerPoint();
                    //final CellProcessor[] processors=atl.getProcessors();
                    for(ChargingOptionsList res:result){
                        w.writeNext(res.toString2());
                        w.flush();
                    }
                } catch(IOException e){
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
         public Representation generateRepresentation6(List<Bill> result) {
            return new CustomCsvRepresentation((CSVWriter w)->{
                try{
                    String[] headers = new String[]{
                            "CostForTheMonth"};
                    w.writeNext(headers);
                    w.flush();
                    //RecordSessionsPerPoint re = new RecordSessionsPerPoint();
                    //final CellProcessor[] processors=atl.getProcessors();
                    for(Bill res:result){
                        //w.writeNext(res);
                        //w.writeNext(Float.toString(res));
                        w.flush();
                    }
                } catch(IOException e){
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
         public Representation generateRepresentation7(List<RecordDuration> result) {
            return new CustomCsvRepresentation((CSVWriter w)->{
                try{
                    String[] headers = new String[]{
                            "Charging Duration in seconds"};
                    w.writeNext(headers);
                    w.flush();
                    //RecordSessionsPerPoint re = new RecordSessionsPerPoint();
                    //final CellProcessor[] processors=atl.getProcessors();
                    for(RecordDuration res :result){
                        //w.writeNext(res);
                        //w.writeNext(Float.toString(res));
                        w.flush();
                    }
                } catch(IOException e){
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
         public Representation generateRepresentation8(List<EVInfo> result) {
            return new CustomCsvRepresentation((CSVWriter w)->{
                try{
                    String[] headers = new String[]{
                            "EVType"};
                    w.writeNext(headers);
                    w.flush();
                    //RecordSessionsPerPoint re = new RecordSessionsPerPoint();
                    //final CellProcessor[] processors=atl.getProcessors();
                    for(EVInfo res:result){
                        //w.writeNext(res);
                        //w.writeNext(Float.toString(res));
                        w.flush();
                    }
                } catch(IOException e){
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
        public Representation generateRepresentation9(List<RecordHistory> result) {
            return new CustomCsvRepresentation((CSVWriter w)->{
                try{
                    String[] headers = new String[]{"Charging History - no need for CSV"};
                    w.writeNext(headers);
                    w.flush();
                    //RecordSessionsPerPoint re = new RecordSessionsPerPoint();
                    //final CellProcessor[] processors=atl.getProcessors();
                    for(RecordHistory res:result){
                        //w.writeNext(res);
                        //w.writeNext(Float.toString(res));
                        w.flush();
                    }
                } catch(IOException e){
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
         public Representation generateRepresentation10(List<User> result) {
            return new CustomCsvRepresentation((CSVWriter w)->{
                try{
                    String[] headers = new String[]{
                            "Username",
                            "First Name",
                            "Last Name"};
                    w.writeNext(headers);
                    w.flush();
                    //RecordSessionsPerPoint re = new RecordSessionsPerPoint();
                    //final CellProcessor[] processors=atl.getProcessors();
                    for(User res:result){
                        w.writeNext(res.toString2());
                        w.flush();
                    }
                } catch(IOException e){
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
    };

    private static final class CustomJsonRepresentation extends WriterRepresentation {

        private final Consumer<JsonWriter> consumer;

        CustomJsonRepresentation(Consumer<JsonWriter> consumer) {
            super(MediaType.APPLICATION_JSON);
            this.consumer = consumer;
        }

        @Override
        public void write(Writer writer) throws IOException {
            JsonWriter jsonWriter = new JsonWriter(writer);
            consumer.accept(jsonWriter);
        }
    }
    private static final class CustomCsvRepresentation extends WriterRepresentation{

        private final static String TEXT_CSV = "text/csv";
        private final static MediaType TEXT_CSV_TYPE = new MediaType("text", "csv");
        //private final static CsvPreference PIPE_DELIMITED = new CsvPreference.Builder('"', ';', "\r\n").build();
        private final Consumer<CSVWriter> consumer;

        CustomCsvRepresentation(Consumer<CSVWriter> consumer){
            super(TEXT_CSV_TYPE);
            this.consumer = consumer;
        }

        @Override
        public void write(Writer writer) throws IOException {
            CSVWriter csvWriter = new CSVWriter(writer,';',CSVWriter.NO_QUOTE_CHARACTER,CSVWriter.NO_ESCAPE_CHARACTER,"\r\n");
            consumer.accept(csvWriter);
        }
    }
}
