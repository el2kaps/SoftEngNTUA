package gr.ntua.ece.softeng2021.client;

import com.google.gson.stream.JsonReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

//import java.io.IOException;
//import java.io.Reader;
//import java.util.ArrayList;
//import java.util.List;

import com.opencsv.*;
import java.io.IOException;
import java.io.Reader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.lang.*;
import java.util.*;
import com.opencsv.CSVReader;

import gr.ntua.ece.softeng2021.data.model.SessionsPerEV;
import gr.ntua.ece.softeng2021.data.model.SessionsPerPoint;
import gr.ntua.ece.softeng2021.data.model.SessionsPerProvider;
import gr.ntua.ece.softeng2021.data.model.SessionsPerStation;

public enum Format implements ResponseBodyProcessor {
    JSON {
        @Override
        public List<SessionsPerPoint> consumeSessionsPerPoint(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readSessionsPerPoint(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        }

        @Override
        public List<SessionsPerStation> consumeSessionsPerStation(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readSessionsPerStation(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        }

        @Override
        public List<SessionsPerEV> consumeSessionsPerEV(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
              //System.out.println("IN JSON!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                return readSessionsPerEV(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        }

        @Override
        public List<SessionsPerProvider> consumeSessionsPerProvider(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readSessionsPerProvider(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        }

    },
    CSV {
        @Override
        public List<SessionsPerPoint> consumeSessionsPerPoint(Reader reader) {
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
            try (CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(parser).build()) {
                return readSessionsPerPointcsv(csvReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }


        @Override
        public List<SessionsPerStation> consumeSessionsPerStation(Reader reader) {
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
            try (CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(parser).build()) {
                return readSessionsPerStationcsv(csvReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        @Override
        public List<SessionsPerEV> consumeSessionsPerEV(Reader reader) {
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
            try (CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(parser).build()) {
              //System.out.println("IN CSV !!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                return readSessionsPerEVcsv(csvReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }


        @Override
        public List<SessionsPerProvider> consumeSessionsPerProvider(Reader reader) {
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
            try (CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(parser).build()) {
                return readSessionsPerProvidercsv(csvReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

    };

    private static List<SessionsPerPoint> readSessionsPerPoint(JsonReader reader)
            throws IOException {
        List<SessionsPerPoint> result = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()) {
            result.add(readSessionPerPoint(reader));
        }
        reader.endArray();
        return result;
    }

    private static List<SessionsPerStation> readSessionsPerStation(JsonReader reader)
            throws IOException {
        List<SessionsPerStation> result = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()) {
            result.add(readSessionPerStation(reader));
        }
        reader.endArray();
        return result;
    }


    private static List<SessionsPerEV> readSessionsPerEV(JsonReader reader)
            throws IOException {
        List<SessionsPerEV> result = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()) {
            result.add(readSessionPerEV(reader));
        }
        reader.endArray();
        return result;
    }

    private static List<SessionsPerProvider> readSessionsPerProvider(JsonReader reader)
            throws IOException {
        List<SessionsPerProvider> result = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()) {
            result.add(readSessionPerProvider(reader));
        }
        reader.endArray();
        return result;
    }


    private static List<SessionsPerPoint>  readSessionsPerPointcsv(CSVReader reader)
            throws IOException {
        List<SessionsPerPoint>  result = new ArrayList<>();
        String[] arr;
        arr=reader.readNext();
        while((arr = reader.readNext()) != null) {
            //System.out.println(arr[6]);
            result.add(new SessionsPerPoint(arr[0],arr[1],arr[2],arr[3],arr[4],Integer.parseInt(arr[5]),
            arr[6],arr[7],arr[8],arr[9],Double.parseDouble(arr[10]),arr[11],arr[12]));
        }
        return result;
    }


    private static List<SessionsPerStation> readSessionsPerStationcsv(CSVReader reader)
            throws IOException {
        List<SessionsPerStation> result = new ArrayList<>();
        String[] arr;
        arr=reader.readNext();
        while((arr = reader.readNext()) != null) {
            //System.out.println(arr[6]);
            result.add(new SessionsPerStation(arr[0],arr[1],arr[2],arr[3],arr[4],Double.parseDouble(arr[5]),Integer.parseInt(arr[6]),Integer.parseInt(arr[7]),
            //Integer.parseInt(arr[8]),
            arr[8],Integer.parseInt(arr[9]),Double.parseDouble(arr[10])));
        }
        return result;
    }


    private static List<SessionsPerEV> readSessionsPerEVcsv(CSVReader reader)
            throws IOException {
        List<SessionsPerEV> result = new ArrayList<>();
        String[] arr;
        arr=reader.readNext();
        while((arr = reader.readNext()) != null) {
            //System.out.println(arr[6]);
            result.add(new SessionsPerEV(arr[0],arr[1],arr[2],arr[3],Double.parseDouble(arr[4]),Integer.parseInt(arr[5]),Integer.parseInt(arr[6]),
            //vale thn listaaa
            //Integer.parseInt(arr[7]), list index
            arr[7],arr[8],arr[9],arr[10],Double.parseDouble(arr[11]),arr[12],Double.parseDouble(arr[13]),Double.parseDouble(arr[14]) ));
        }
        return result;
    }

    private static List<SessionsPerProvider> readSessionsPerProvidercsv(CSVReader reader)
            throws IOException {
        List<SessionsPerProvider> result = new ArrayList<>();
        String[] arr;
        arr=reader.readNext();
        while((arr = reader.readNext()) != null) {
            //System.out.println(arr[6]);
            result.add(new SessionsPerProvider(arr[0],arr[1],arr[2],arr[3],arr[4],arr[5],arr[6],Double.parseDouble(arr[7]),arr[8],Double.parseDouble(arr[9]),Double.parseDouble(arr[10]) )  );
        }
        return result;
    }


    private static SessionsPerPoint readSessionPerPoint(JsonReader reader)
            throws IOException {
        SessionsPerPoint rec = new SessionsPerPoint();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "Point":
                    rec.setPoint(reader.nextString());
                    break;
                case "PointOperator":
                    rec.setPointOperator(reader.nextString());
                    break;
                case "RequestTimestamp":
                    rec.setRequestTimestamp(reader.nextString());
                    break;
                case "PeriodFrom":
                    rec.setPeriodFrom(reader.nextString());
                    break;
                case "PeriodTo":
                    rec.setPeriodTo(reader.nextString());
                    break;
                case "NumberOfChargingSessions":
                    rec.setNumberOfChargingSessions(reader.nextInt());
                    break;
                case "SessionIndex":
                    rec.setSessionIndex(reader.nextInt());
                    break;
                case "SessionID":
                    rec.setSessionID(reader.nextString());
                    break;
                case "StartedOn":
                    rec.setStartedOn(reader.nextString());
                    break;
                case "FinishedOn":
                    rec.setFinishedOn(reader.nextString());
                    break;
                case "Protocol":
                    rec.setProtocol(reader.nextString());
                    break;
                case "EnergyDelivered":
                    rec.setEnergyDelivered(reader.nextDouble());
                    break;
                case "Payment":
                    rec.setPayment(reader.nextString());
                    break;
                case "VehicleType":
                    rec.setVehicleType(reader.nextString());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }

    private static SessionsPerStation readSessionPerStation(JsonReader reader)
            throws IOException {
        SessionsPerStation rec = new SessionsPerStation();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "StationID":
                    rec.setStationID(reader.nextString());
                    break;
                case "Operator":
                    rec.setOperator(reader.nextString());
                    break;
                case "RequestTimestamp":
                    rec.setRequestTimestamp(reader.nextString());
                    break;
                case "PeriodFrom":
                    rec.setPeriodFrom(reader.nextString());
                    break;
                case "PeriodTo":
                    rec.setPeriodTo(reader.nextString());
                    break;
                case "TotalEnergyDelivered":
                    rec.setTotalEnergyDelivered(reader.nextDouble());
                    break;
                case "NumberOfChargingSessions":
                    rec.setNumberOfChargingSessions(reader.nextInt());
                    break;
                case "NumberOfActivePoints":
                    rec.setNumberOfActivePoints(reader.nextInt());
                    break;
                case "PointID":
                    rec.setPointID(reader.nextString());
                    break;
                case "PointSessions":
                    rec.setPointSessions(reader.nextInt());
                    break;
                case "EnergyDelivered":
                    rec.setEnergyDelivered(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }

    private static SessionsPerEV readSessionPerEV(JsonReader reader)
            throws IOException {
        SessionsPerEV rec = new SessionsPerEV();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "VehicleID":
                    rec.setVehicleID(reader.nextString());
                    break;
                case "RequestTimestamp":
                    rec.setRequestTimestamp(reader.nextString());
                    break;
                case "PeriodFrom":
                    rec.setPeriodFrom(reader.nextString());
                    break;
                case "PeriodTo":
                    rec.setPeriodTo(reader.nextString());
                    break;
                case "TotalEnergyConsumed":
                    rec.setTotalEnergyConsumed(reader.nextDouble());
                    break;
                case "NumberOfVisitedPoints":
                    rec.setNumberOfVisitedPoints(reader.nextInt());
                    break;
                case "NumberOfVehicleChargingSessions":
                    rec.setNumberOfVehicleChargingSessions(reader.nextInt());
                    break;
                //// sososoosososososo VALE LISTA
                case "SessionIndex":
                    rec.setSessionIndex(reader.nextInt());
                    break;
                case "SessionID":
                    rec.setSessionID(reader.nextString());
                    break;
                case "EnergyProvider":
                    rec.setEnergyProvider(reader.nextString());
                    break;
                case "StartedOn":
                    rec.setStartedOn(reader.nextString());
                    break;
                case "FinishedOn":
                    rec.setFinishedOn(reader.nextString());
                    break;
                case "EnergyDelivered":
                    rec.setEnergyDelivered(reader.nextDouble());
                    break;
                case "PricePolicyRef":
                    rec.setPricePolicyRef(reader.nextString());
                    break;
                case "CostPerKWh":
                    rec.setCostPerKWh(reader.nextDouble());
                    break;
                case "SessionCost":
                    rec.setSessionCost(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }



    private static SessionsPerProvider readSessionPerProvider(JsonReader reader)
            throws IOException {
        SessionsPerProvider rec = new SessionsPerProvider();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "ProviderID":
                    rec.setProviderID(reader.nextString());
                    break;
                case "ProviderName":
                    rec.setProviderName(reader.nextString());
                    break;
                case "StationID":
                    rec.setStationID(reader.nextString());
                    break;
                case "SessionID":
                    rec.setSessionID(reader.nextString());
                    break;
                case "StartedOn":
                    rec.setStartedOn(reader.nextString());
                    break;
                case "FinishedOn":
                    rec.setFinishedOn(reader.nextString());
                    break;
                case "EnergyDelivered":
                    rec.setEnergyDelivered(reader.nextDouble());
                    break;
                case "VehicleID":
                    rec.setVehicleID(reader.nextString());
                    break;
                case "PricePolicyRef":
                    rec.setPricePolicyRef(reader.nextString());
                    break;
                case "CostPerKWh":
                    rec.setCostPerKWh(reader.nextDouble());
                    break;
                case "TotalCost":
                    rec.setTotalCost(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }


}
