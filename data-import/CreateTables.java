//STEP 1. Import required packages
import java.sql.*;

public class CreateTables {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/newdatabase?characterEncoding=utf-8&autoReconnect=true&allowLoadLocalInfile=true&allowUrlInLocalInfile=true&serverTimezone=UTC";

    //  Database credentials
    static final String USER = "USERNAME";
    static final String PASS = "PASSWORD";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating table in given database...");
            stmt = conn.createStatement();

            String sql = "CREATE TABLE User_ (UserID VARCHAR (100) PRIMARY KEY, username VARCHAR(100), password VARCHAR(100), firstName VARCHAR(100), lastName VARCHAR(100))";

            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");
            //new table
            sql = "CREATE TABLE Point_(PointID VARCHAR (100) PRIMARY KEY, StationID VARCHAR (100), ChargingRate FLOAT(3))";
            stmt.executeUpdate(sql);

            //
            sql = "CREATE TABLE Session_(SessionID VARCHAR (100) PRIMARY KEY, PointID VARCHAR (100), VehicleID VARCHAR (100), ProviderID VARCHAR (100), OperatorID VARCHAR (100), StartedOn DATETIME, FinishedOn DATETIME, Protocol VARCHAR (100), EnergyDelivered FLOAT(3), Payment VARCHAR (100))";
            stmt.executeUpdate(sql);
            //
            sql = "CREATE TABLE Station(StationID VARCHAR (100) PRIMARY KEY, latitude Decimal(8,6), longtitude Decimal(9,6))";
            stmt.executeUpdate(sql);
            //
            sql = "CREATE TABLE Operator (OperatorID VARCHAR (100) PRIMARY KEY, username VARCHAR(100), password VARCHAR(100), brandName VARCHAR(100))";
            stmt.executeUpdate(sql);
            //
            sql = "CREATE TABLE Provider(ProviderID VARCHAR (100) PRIMARY KEY, username VARCHAR(100), password VARCHAR(100), brandName VARCHAR(100), PricePolicyRef VARCHAR(100), CostPerKWh FLOAT(3))";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE EV(VehicleID VARCHAR (100) PRIMARY KEY, VehicleType VARCHAR(100), Capacity FLOAT(3))";
            stmt.executeUpdate(sql);
            //
            sql = "CREATE TABLE PointProviderRelation(PointID VARCHAR (100) NOT NULL, ProviderID VARCHAR (100) NOT NULL, FOREIGN KEY (PointID) REFERENCES Point_(PointID), FOREIGN KEY (ProviderID) REFERENCES Provider(ProviderID), UNIQUE (PointID, ProviderID))";
            stmt.executeUpdate(sql);
            //
            sql= "CREATE TABLE PointOperatorRelation(PointID VARCHAR (100) NOT NULL, OperatorID VARCHAR (100) NOT NULL, FOREIGN KEY (PointID) REFERENCES Point_(PointID), FOREIGN KEY (OperatorID) REFERENCES Operator(OperatorID), UNIQUE (PointID, OperatorID))";
            stmt.executeUpdate(sql);
            //
            sql = "CREATE TABLE UserEVRelation(UserID VARCHAR (100) NOT NULL, VehicleID VARCHAR (100) NOT NULL, FOREIGN KEY (UserID) REFERENCES User_(UserID), FOREIGN KEY (VehicleID) REFERENCES EV(VehicleID), UNIQUE (UserID, VehicleID))";
            stmt.executeUpdate(sql);
            //
            sql= "CREATE TABLE PointEVRelation(PointID VARCHAR (100) NOT NULL, VehicleID VARCHAR (100) NOT NULL, FOREIGN KEY (PointID) REFERENCES Point_(PointID), FOREIGN KEY (VehicleID) REFERENCES EV(VehicleID), UNIQUE (PointID, VehicleID))";
            stmt.executeUpdate(sql);
            //
            sql="CREATE TABLE StationProviderRelation( StationID VARCHAR (100) NOT NULL, ProviderID VARCHAR (100) NOT NULL, FOREIGN KEY (StationID) REFERENCES Station(StationID), FOREIGN KEY (ProviderID) REFERENCES Provider(ProviderID), UNIQUE (StationID, ProviderID))";
            stmt.executeUpdate(sql);
            //
            sql= "CREATE TABLE StationOperatorRelation(StationID VARCHAR (100) NOT NULL, OperatorID VARCHAR (100) NOT NULL, FOREIGN KEY (StationID) REFERENCES Station(StationID), FOREIGN KEY (OperatorID) REFERENCES Operator(OperatorID), UNIQUE (StationID, OperatorID))";
            stmt.executeUpdate(sql);
            //
            sql= "CREATE TABLE LoggedUser (token VARCHAR (100),username VARCHAR(100) PRIMARY KEY)";
            stmt.executeUpdate(sql);

            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main
}//end JDBCExampl