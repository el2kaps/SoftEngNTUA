import java.io.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class AutoImport {
    public static void main(String[]args){
        Connection con = null;
        try {
            con = DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/newdatabase?characterEncoding=utf-8&autoReconnect=true&allowLoadLocalInfile=true&allowUrlInLocalInfile=true&serverTimezone=UTC", "USERNAME", "PASSWORD");
            System.out.println("Connection is successful !!!!!");
            /*For each table repeat this block*/
            String csvFilePath = "PATH\EVUserID.csv";
            int batchSize = 20;
            con.setAutoCommit(false);

            String sql = "INSERT INTO User_ (UserID,username,password,firstName,lastName) VALUES (?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);

            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
            String lineText = null;

            int count = 0;
            lineReader.readLine(); // skip header line

            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(";");
                String UserID = data[0];
                String username = data[1];
                String password = data[2];
                String firstName = data[3];
                //String lastName = data.length == 5 ? data[4] : "";
                String lastName = data[4];

                statement.setString(1, UserID);
                statement.setString(2, username);
                statement.setString(3, password);
                statement.setString(4, firstName);
                statement.setString(5, lastName);

                statement.addBatch();

                if (count % batchSize == 0) {
                    statement.executeBatch();
                }
            }

            lineReader.close();
            // execute the remaining queries
            statement.executeBatch();
            con.commit();
            /*block ends here*/
            //new table
            csvFilePath = "PATH\cars.csv";
            batchSize = 20;
            con.setAutoCommit(false);
            sql = "INSERT INTO EV (VehicleID, VehicleType, Capacity) VALUES (?,?,?)";
            statement = con.prepareStatement(sql);
            BufferedReader lineReader2 = new BufferedReader(new FileReader(csvFilePath));
            lineText = null;
            count = 0;
            lineReader2.readLine(); // skip header line

            while ((lineText = lineReader2.readLine()) != null) { 
                String[] data = lineText.split(";");
                String VehicleID = data[0];
                String VehicleType = data[1];
                String Capacity = data[2];

                statement.setString(1, VehicleID);
                statement.setString(2, VehicleType);
                statement.setString(3, Capacity);

                statement.addBatch();

                if (count % batchSize == 0) { 
                    statement.executeBatch(); 
                }
            }
            lineReader.close();
            // execute the remaining queries
            statement.executeBatch();
            con.commit();

            //close connection
            //con.commit();
            con.close(); 
        } catch(Exception e) { 
            e.printStackTrace();
        }
    }
}
