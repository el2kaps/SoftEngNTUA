package gr.ntua.ece.softeng2021.backend.data;

import gr.ntua.ece.softeng2021.backend.data.model.RecordSessionsPerEV;
import gr.ntua.ece.softeng2021.backend.data.model.RecordSessionsPerPoint;
import gr.ntua.ece.softeng2021.backend.data.model.RecordSessionsPerProvider;
import gr.ntua.ece.softeng2021.backend.data.model.RecordSessionsPerStation;
import gr.ntua.ece.softeng2021.backend.data.model.ChargingOptionsList;
import gr.ntua.ece.softeng2021.backend.data.model.RecordHistory;
import gr.ntua.ece.softeng2021.backend.data.model.Bill;
import gr.ntua.ece.softeng2021.backend.data.model.EVInfo;
import gr.ntua.ece.softeng2021.backend.data.model.RecordDuration;
import gr.ntua.ece.softeng2021.backend.data.DataAccessException;
import org.apache.commons.dbcp2.BasicDataSource;
import java.util.List;
import java.util.*;
import org.restlet.resource.ResourceException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.spec.InvalidKeySpecException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.security.NoSuchAlgorithmException;
import java.util.Random;



public class DataAccess {

    private static final Object[] EMPTY_ARGS = new Object[0];

    private static final int MAX_TOTAL_CONNECTIONS = 16;
    private static final int MAX_IDLE_CONNECTIONS = 8;

    private JdbcTemplate jdbcTemplate;

    public void setup(String driverClass, String url, String user, String pass) throws SQLException {

        //initialize the data source
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName(driverClass);
        bds.setUrl(url);
        bds.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        bds.setMaxIdle(MAX_IDLE_CONNECTIONS);
        bds.setUsername(user);
        bds.setPassword(pass);
        bds.setValidationQuery("SELECT 1");
        bds.setTestOnBorrow(true);
        bds.setDefaultAutoCommit(true);

        //check that everything works OK
        bds.getConnection().close();

        //initialize the jdbc template utility
        jdbcTemplate = new JdbcTemplate(bds);
    }

    public void accessDataCheck() throws DataAccessException {
        try {
            jdbcTemplate.query("select 1", ResultSet::next);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    //Import Sessions
     public List<Integer> ImportSessions(){
      System.out.println("Import Sessions");
      String sqlQuery=
          " LOAD DATA INFILE 'tmp.csv' INTO TABLE Session_  "+
          " FIELDS TERMINATED BY ';'   "+
          " OPTIONALLY ENCLOSED BY '\"'  "+
          " ESCAPED BY '\"' LINES TERMINATED BY '\\n' IGNORE 1 LINES "+
          " (@SessionID,@PointID,@VehicleID,@ProviderID,@OperatorID,@StartedOn,@FinishedOn,@Protocol,@EnergyDelivered,@Payment) "+
          " SET SessionID=nullif(@SessionID,'NULL'), "+
          " PointID=nullif(@PointID,'NULL'), "+
          " VehicleID=nullif(@VehicleID,'NULL'), "+
          " ProviderID=nullif(@ProviderID,'NULL'), "+
          " OperatorID=nullif(@OperatorID,'NULL'), "+
          " StartedOn=nullif(@StartedOn,'NULL'), "+
          " FinishedOn=nullif(@FinishedOn,'NULL'), "+
          " Protocol=nullif(@Protocol,'NULL'), "+
          " EnergyDelivered=nullif(@EnergyDelivered,'NULL'), "+
          " Payment=nullif(@Payment,'NULL') ";

      try{
        Integer a = this.jdbcTemplate.update(sqlQuery);
        List<Integer> r = jdbcTemplate.query("select count(SessionID) as mycnt from Session_",new IntMapper());
        System.out.println(r.toString());
        List<Integer> retme = new ArrayList<Integer>();
        retme.add(0,a);
        retme.add(1,r.get(0));
        return retme;
      }catch(Exception e){
        System.out.println(e.getMessage());
      }
      return new ArrayList<Integer>();
    }

    //Admin Test
    public boolean TestAdminToken(String token){
        System.out.println("TestAdminToken...");
        Object[] sqlParams = new Object[] {
            token
        };

        String sqlQuery=" select username from LoggedUser where token = ? ";
        System.out.println(sqlQuery);

        /*Authenticate user*/
        List<User> res=jdbcTemplate.query(sqlQuery, sqlParams,(ResultSet rs, int rowNum) -> {
                User tryuser = new User();
                tryuser.setUsername(rs.getString(1)); //get the string located at the 1st column of the result set
                return tryuser;
            });
            if(res.size()==1){
                User testuser = res.get(0);
                if(testuser.getUsername().equals("admin")) return true;
                return false;

            }
            else {return false;}
    }
    //UserExists
     public boolean UserExists(String username){
        System.out.println("Check if user exists...");
        Object[] sqlParams = new Object[] {
            username
        };

        String sqlQuery=" select * from User_ where username = ? ";
        System.out.println(sqlQuery);

        List<User> res=jdbcTemplate.query(sqlQuery, sqlParams,(ResultSet rs, int rowNum) -> {
                User tryuser = new User();
                tryuser.setUsername(rs.getString(1)); //get the string located at the 1st column of the result set
                return tryuser;
            });
            if(res.size()==1){return true;}
            else {return false;}
    }

   //Add User
    public void AddUser(String username, String password) throws DataAccessException {
        System.out.println("Add User...");
        try{
        if(!UserExists(username)){
            System.out.println("User doesn't exists!!");
            String userID = RandomString(16);
            Object[] sqlParams = new Object[] {userID, username, password};
            String AddUser= " insert into User_(UserID,username,password,firstName,lastName) "+
                            " values(?,?,?,null,null) ";
            this.jdbcTemplate.update(AddUser,sqlParams);
            }
        else{
            System.out.println("User exists!!");
            Object[] sqlParams2 = new Object[] {password, username};
            String AddUser2 = " UPDATE User_ SET password = ? WHERE username = ? ";
            this.jdbcTemplate.update("SET SQL_SAFE_UPDATES = 0");
            this.jdbcTemplate.update(AddUser2,sqlParams2);
        }

        }
        catch(Exception e){
            //return null;
            throw new DataAccessException("Error in AddUser",e);
        }
    }

    //ReadUser
    public List<User> ReadUser(String username)throws DataAccessException{
        System.out.println("User Info...");
        Object[] sqlParams = new Object[] {
            username
        };

        String sqlQuery=" select firstName, lastName from User_ where username = ? ";
        System.out.println(sqlQuery);

        try{
        return jdbcTemplate.query(sqlQuery, sqlParams,(ResultSet rs, int rowNum) -> {
                User tryuser = new User();
                tryuser.setUsername(username); //get the string located at the 1st column of the result set
                tryuser.setFirstName(rs.getString(1));
                tryuser.setLastName(rs.getString(2));
                return tryuser;
            });
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }
    //SessionsPerPoint
    public List<RecordSessionsPerPoint> fetchSessionsPerPoint(String pointID, String date1, String date2)
            throws DataAccessException {

        DateManipulator d1 = new DateManipulator(date1);
        DateManipulator d2 = new DateManipulator(date2);
        String dateto = d1.PrintDate();
        String datefrom = d2.PrintDate();

        Object[] sqlParams = new Object[] {
                pointID,
                dateto,
                datefrom
        };
        //todo: Insert a valid SQL query
        String sqlQuery =
                " select  pop.OperatorID, "+
                " count(sess.SessionID), sess.SessionID, " +
                "sess.StartedOn, sess.FinishedOn, sess.Protocol, sess.EnergyDelivered, " +
                " sess.Payment, EV.VehicleType " +
                " from PointOperatorRelation as pop, Session_  as sess, EV, Point_, PointEVRelation " +
                " where " +
                " Point_.PointID = ? " +
                " and pop.PointID = Point_.PointID " +
                " and PointEVRelation.PointID = Point_.PointID " +
                " and EV.VehicleID = PointEVRelation.VehicleID " +
                " and sess.PointID = Point_.PointID " +
                " and sess.FinishedOn between ? and ? ";

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        String formattedDateTime = currentDateTime.format(formatter);

        try {
            Integer index = 0;
            return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                RecordSessionsPerPoint dataLoad = new RecordSessionsPerPoint();
                dataLoad.setPoint(pointID); //get the string located at the 1st column of the result set
                dataLoad.setPointOperator(rs.getString(1)); //get the int located at the 2nd column of the result set
                dataLoad.setRequestTimestamp(formattedDateTime);
                dataLoad.setPeriodFrom(dateto);
                dataLoad.setPeriodTo(datefrom);
                dataLoad.setNumberOfChargingSessions(rs.getInt(2)); //query (count)
                //dataLoad.setChargingSessionsList(rs.getInt(4)); //care for List
                dataLoad.setSessionIndex(index); //export from query
                dataLoad.setSessionID(rs.getString(3));
                dataLoad.setStartedOn(rs.getString(4));
                dataLoad.setFinishedOn(rs.getString(5));
                dataLoad.setProtocol(rs.getString(6));
                dataLoad.setEnergyDelivered(rs.getFloat(7));
                dataLoad.setPayment(rs.getString(8));
                dataLoad.setVehicleType(rs.getString(9));
                return dataLoad;
            });
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }
    //SessionsPerStation
    public List<RecordSessionsPerStation> fetchSessionsPerStation(String stationID, String date1, String date2)
            throws DataAccessException {

        DateManipulator d1 = new DateManipulator(date1);
        DateManipulator d2 = new DateManipulator(date2);
        String dateto = d1.PrintDate();
        String datefrom = d2.PrintDate();

        Object[] sqlParams = new Object[] {
                stationID,
                dateto,
                datefrom
        };

        //todo: Insert a valid SQL query
        String sqlQuery1 = " select sum(Session_.EnergyDelivered) as TotalEnergyDelivered "+
                          " from "+
                          " Station as st, Session_ , Point_  where "+
                          " st.StationID = ? "+
                          " and Point_.PointID = Session_.PointID  "+
                          " and Point_.StationID = st.StationID  "+
                          " and Session_.FinishedOn between ? and ? ";

        String sqlQuery2 = " select count(Session_.SessionID) as NumberOfChargingSessions "+
                           " from "+
                          " Station as st, Session_ , Point_  where "+
                          " st.StationID = ? "+
                          " and Point_.PointID = Session_.PointID  "+
                          " and Point_.StationID = st.StationID  "+
                          " and Session_.FinishedOn between ? and ? ";

        String sqlQuery3 = " select count(distinct Point_.PointID) as NumberOfActivePoints "+
                           " from " +
                          " Station as st, Session_ , Point_  where "+
                          " st.StationID = ? "+
                          " and Point_.PointID = Session_.PointID  "+
                          " and Point_.StationID = st.StationID  "+
                          " and Session_.FinishedOn between ? and ? ";

        String sqlQuery4 = " select Point_.PointID as Point_ID , stor.OperatorID, "+
                           " (select count(Session_.SessionID) from Session_ where Session_.PointID=Point_ID) as PointSessions, "+
                           " (select sum(Session_.EnergyDelivered) as TotalEnergyDeliveredFromPoint from Session_ where Session_.PointID=Point_ID) "+
                           " from Point_, Session_, StationOperatorRelation as stor where "+
                           " Point_.StationID = ? and "+
                           " Session_.PointID = Point_.PointID and Session_.FinishedOn between ? and ? group by 1 ";

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        String formattedDateTime = currentDateTime.format(formatter);

        try {

            List<Float> total_energy = jdbcTemplate.query(sqlQuery1, sqlParams, (ResultSet rs, int rowNum) -> {
                Float fl1 = rs.getFloat(1);
                return fl1;
            });

            List<Integer> sessions_num = jdbcTemplate.query(sqlQuery2, sqlParams, (ResultSet rs, int rowNum) -> {
                Integer fl1 = rs.getInt(1);
                return fl1;
            });
            List<Integer> active_points = jdbcTemplate.query(sqlQuery3, sqlParams, (ResultSet rs, int rowNum) -> {
                Integer fl1 = rs.getInt(1);
                return fl1;
            });

             return jdbcTemplate.query(sqlQuery4, sqlParams, (ResultSet rs, int rowNum) -> {
                RecordSessionsPerStation dataLoad = new RecordSessionsPerStation();
                dataLoad.setStationID(stationID); //get the string located at the 1st column of the result set
                dataLoad.setOperator(rs.getString(2)); //get the int located at the 2nd column of the result set
                dataLoad.setRequestTimestamp(formattedDateTime);
                dataLoad.setPeriodFrom(dateto);
                dataLoad.setPeriodTo(datefrom);
                dataLoad.setTotalEnergyDelivered(total_energy.get(0)); //query (count)
                dataLoad.setNumberOfChargingSessions(sessions_num.get(0));
                dataLoad.setNumberOfActivePoints(active_points.get(0)); //export from query
                //dataLoad.setSessionsSummaryList(rs.getList(6)); //list
                dataLoad.setPointID(rs.getString(1));
                dataLoad.setPointSessions(rs.getInt(3));
                dataLoad.setEnergyDelivered(rs.getFloat(4));
                return dataLoad;
            });

        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    //SessionsPerEV
    public List<RecordSessionsPerEV> fetchSessionsPerEV(String vehicleID, String date1, String date2)
            throws DataAccessException {

        DateManipulator d1 = new DateManipulator(date1);
        DateManipulator d2 = new DateManipulator(date2);
        String dateto = d1.PrintDate();
        String datefrom = d2.PrintDate();

        Object[] sqlParams = new Object[] {
                vehicleID,
                dateto,
                datefrom
        };

        //Todo: Insert a valid SQL query
        String sqlQuery = "SELECT sum(Session_.EnergyDelivered) as TotalEnergyConsumed, count(distinct PointEVRelation.PointID) as NumberOfVisitedPoints, " +
                "count(distinct Session_.SessionID) as NumberOfVehicleChargingSessions, " +
                "Session_.SessionID, " +
                "Provider.brandName as EnergyProvider, Session_.StartedOn,Session_.FinishedOn, Session_.EnergyDelivered, " +
                "Provider.PricePolicyRef, Provider.CostPerKWh, (Provider.CostPerKWh * Session_.EnergyDelivered) as SessionCost " +
                "FROM Session_ , Provider, EV, PointEVRelation, Point_ " +
                "where  EV.VehicleID = ? " +
                "and Session_.VehicleID = EV.VehicleID " +
                "and Provider.ProviderID = Session_.ProviderID " +
                "and PointEVRelation.VehicleID = EV.VehicleID " +
                "and PointEVRelation.PointID = Point_.PointID " +
                "and Session_.FinishedOn between ? and ? ";
                //System.out.println(sqlQuery);
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        String formattedDateTime = currentDateTime.format(formatter);

        try {
            Integer index = 0;
            return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                RecordSessionsPerEV dataLoad = new RecordSessionsPerEV();
                dataLoad.setVehicleID(vehicleID); //get the string located at the 1st column of the result set
                dataLoad.setRequestTimestamp(formattedDateTime);
                dataLoad.setPeriodFrom(dateto);
                dataLoad.setPeriodTo(datefrom);
                dataLoad.setTotalEnergyConsumed(rs.getFloat(1)); //query (count)
                dataLoad.setNumberOfVisitedPoints(rs.getInt(2));
                dataLoad.setNumberOfVehicleChargingSessions(rs.getInt(3)); //export from query
               // dataLoad.setVehicleChargingSessionsList(rs.getString(4)); //list
                dataLoad.setSessionIndex(index);
                dataLoad.setSessionID(rs.getString(4));
                dataLoad.setEnergyProvider(rs.getString(5));
                dataLoad.setStartedOn(rs.getString(6));
                dataLoad.setFinishedOn(rs.getString(7));
                dataLoad.setEnergyDelivered(rs.getFloat(8));
                dataLoad.setPricePolicyRef(rs.getString(9));
                dataLoad.setCostPerKWh(rs.getFloat(10));
                dataLoad.setSessionCost(rs.getFloat(11));
                return dataLoad;

            }    );
        }
        catch(Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

   //SessionsPerProvider
    public List<RecordSessionsPerProvider> fetchSessionsPerProvider(String providerID, String date1, String date2)
            throws DataAccessException {


        DateManipulator d1 = new DateManipulator(date1);
        DateManipulator d2 = new DateManipulator(date2);
        String dateto = d1.PrintDate();
        String datefrom = d2.PrintDate();

        Object[] sqlParams = new Object[] {
                providerID,
                dateto,
                datefrom
        };

        //Todo: Insert a valid SQL query
        String sqlQuery = " select Provider.brandName, StationProviderRelation.StationID, " +
                " Session_.SessionID, Session_.VehicleID, Session_.StartedOn, Session_.FinishedOn, " +
                " Session_.EnergyDelivered, Provider.PricePolicyRef, " +
                " Provider.CostPerKWh, (Session_.EnergyDelivered * Provider.CostPerKWh) as TotalCost " +
                " from Provider, Session_, StationProviderRelation " +
                " where " +
                " Provider.ProviderID = ? " +
                " and Session_.ProviderID = Provider.ProviderID " +
                " and Session_.FinishedOn between ? and ? ";
        //System.out.println(sqlQuery);
        //System.out.println("YCGWIEBCWBYEC WEDIUWEBICEC WEHCQ");
        try {
            return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                RecordSessionsPerProvider dataLoad = new RecordSessionsPerProvider();
                dataLoad.setProviderID(providerID); //get the string located at the 1st column of the result set
                dataLoad.setProviderName(rs.getString(1));
                dataLoad.setStationID(rs.getString(2));
                dataLoad.setSessionID(rs.getString(3));
                dataLoad.setVehicleID(rs.getString(4));
                dataLoad.setStartedOn(rs.getString(5));
                dataLoad.setFinishedOn(rs.getString(6));
                dataLoad.setEnergyDelivered(rs.getFloat(7));
                dataLoad.setPricePolicyRef(rs.getString(8));
                dataLoad.setCostPerKWh(rs.getFloat(9));
                dataLoad.setTotalCost(rs.getFloat(10));
                return dataLoad;
            });
        }
        catch(Exception e) {
            System.out.println("eimai sthn 274");
            throw new DataAccessException(e.getMessage(), e);
        }
    }
  //Provider Billing
  public List<Bill> ProviderBilling(String username)  {
        System.out.println("MonthProviderBilling...");
        Object[] sqlParams = new Object[] {
                username
        };
        String optionsquery=" select Provider.username, sum(Session_.EnergyDelivered*Provider.CostPerKWh) as BillForMonthProv "+
                             " from Session_, Provider where " +
                             " Session_.ProviderID = Provider.ProviderID " +
                             " and Provider.username=? " +
                             " and Session_.StartedOn between '2021-02-01'and '2021-02-28'group by Provider.username";
        try{
                return jdbcTemplate.query(optionsquery, sqlParams, (ResultSet rs, int rowNum) -> {
                Bill bill = new Bill();
                bill.setName(username);
                bill.setCost(rs.getFloat(2)); //get the string located at the 1st column of the result set
                return bill;
            });
        }catch(Exception e){
            return null;
            //throw new DataAccessException("AlreadyLoggedIn",e);
        }
    }
  //User Billing
   public List<Bill> MonthUserBilling(String username)  {
        System.out.println("MonthUserBilling...");
        Object[] sqlParams = new Object[] {
                username
        };
        String optionsquery=" select User_.username, sum(Session_.EnergyDelivered*Provider.CostPerKWh) "+
                                    " from Session_, Provider, UserEVRelation,User_ "+
                                    " where " +
                                    " Session_.VehicleID = UserEVRelation.VehicleID "+
                                    " and UserEVRelation.UserID = User_.UserID " +
                                    " and User_.username=?  "+
                                    " and Session_.StartedOn between '2021-02-01'and '2021-02-28' group by User_.username ";

        try{
                return jdbcTemplate.query(optionsquery, sqlParams, (ResultSet rs, int rowNum) -> {
                Bill bill = new Bill();
                bill.setName(username);
                bill.setCost(rs.getFloat(2)); //get the string located at the 1st column of the result set
                return bill;
            });
        }catch(Exception e){
            return null;
            //throw new DataAccessException("AlreadyLoggedIn",e);
        }
    }

//History
public List<RecordHistory> EVHistory(String username, String evid)  {
        System.out.println("EVHistory...");
        Object[] sqlParams = new Object[] {evid, username};
        String optionsquery= " select Session_.VehicleID, Session_.SessionID, Session_.PointID, "+
        " Session_.ProviderID, Session_.OperatorID, Session_.StartedOn, Session_.FinishedOn, Session_.Protocol, " +
        " Session_.EnergyDelivered "+
        " from Session_,UserEVRelation, User_ " +
        " where Session_.VehicleID= ?  and UserEVRelation.VehicleID = Session_.VehicleID " +
        " and UserEVRelation.UserID = User_.UserID and User_.username = ? ";
        try{
                return jdbcTemplate.query(optionsquery, sqlParams, (ResultSet rs, int rowNum) -> {
                    RecordHistory dataLoad = new RecordHistory();
                    dataLoad.setVehicleID(rs.getString(1));
                    dataLoad.setSessionID(rs.getString(2));
                    dataLoad.setPointID(rs.getString(3));
                    dataLoad.setProviderID(rs.getString(4));
                    dataLoad.setOperatorID(rs.getString(5));
                    dataLoad.setStartedOn(rs.getString(6));
                    dataLoad.setFinishedOn(rs.getString(7));
                    dataLoad.setProtocol(rs.getString(8));
                    dataLoad.setEnergyDelivered(rs.getFloat(9));
                    return dataLoad;
            });
        }catch(Exception e){
            return null;
            //throw new DataAccessException("AlreadyLoggedIn",e);
        }
    }


//Duration
public List<RecordDuration> SecsDuration(String username, String session)  {
        Object[] sqlParams = new Object[] {
                username,
                session
        };

        String sqlQuery= " select  (EV.Capacity/Point_.ChargingRate) from EV, Point_, Session_, User_ , UserEVRelation where" +
                         " Session_.PointID = Point_.PointID " +
                         " and Session_.VehicleID = EV.VehicleID "+
                         " and UserEVRelation.VehicleID = EV.VehicleID "+
                         " and UserEVRelation.UserID = User_.UserId "+
                         " and User_.username = ? "+
                        " and Session_.PointID = Point_.PointID "+
                        " and Session_.SessionID = ? ";

        try{
                return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                RecordDuration dataLoad = new RecordDuration();
                dataLoad.setDuration(rs.getFloat(1)); //get the string located at the 1st column of the result set
                //tryuser.setPassword(rs.getString(2));
                return dataLoad;
            });
        }catch(Exception e){
            return null;
            //throw new DataAccessException("AlreadyLoggedIn",e);
        }
    }

//EVInfo
public List<EVInfo> fetchevinfo(String username, String evid)  {
        System.out.println("EVInfo...");
        //String encpass=password;
        //SOS vale encrypt(password)
        //Database contains password encrypted
        Object[] sqlParams = new Object[] {
                evid,
                username
        };

        String sqlQuery= " select VehicleType, Capacity from EV, UserEVRelation, User_ " +
                         " where EV.VehicleID = ? " +
                         " and User_.username = ? " +
                         " and UserEVRelation.UserID = User_.UserID "+
                         " and UserEVRelation.VehicleID = EV.VehicleID ";

        try{
            return jdbcTemplate.query(sqlQuery, sqlParams, (ResultSet rs, int rowNum) -> {
                    EVInfo dataLoad = new EVInfo();
                    dataLoad.setVehicleID(evid);
                    dataLoad.setVehicleType(rs.getString(1));
                    dataLoad.setVehicleCapacity(rs.getFloat(2));
                    return dataLoad;
            });
        }catch(Exception e){
            return null;
            //throw new DataAccessException("AlreadyLoggedIn",e);
        }
    }
  //fetchChargingOptions
   public List<ChargingOptionsList> fetchChargingOptions(String username)  {
        Object[] sqlParams = new Object[] {
                username
        };

        String optionsquery=" select  distinct (brandName), PricePolicyRef, User_.username  "+
                            " from Provider, User_ where User_.username = ? ";
        try{
                return jdbcTemplate.query(optionsquery, sqlParams, (ResultSet rs, int rowNum) -> {
                ChargingOptionsList dataLoad = new ChargingOptionsList();
                dataLoad.setBrandName(rs.getString(1)); //get the string located at the 1st column of the result set
                //System.out.println("BRAND NAME IS...........");
                //System.out.println(rs.getString(1));
                dataLoad.setPricePolicyRef(rs.getString(2));
                return dataLoad;
            });
        }catch(Exception e){
            return null;
            //throw new DataAccessException("AlreadyLoggedIn",e);
        }
    }


   //Login
    public Optional<String> loginuser(String username,String password)  {
        System.out.println("Login...");
        String encpass=password;
        //SOS vale encrypt(password)
        //Database contains password encrypted
        Object[] sqlParams = new Object[] {
                username,
                encpass
        };

        String sqlQuery="select * from User_ where username = ? and password = ?";
        System.out.println(sqlQuery);
        String sqlQuery2="select * from Provider where username = ? and password = ?";

        try{
            /*Authenticate user-Login for two stakeholders both EVuser and Operator*/
            //User
            List<User> res=jdbcTemplate.query(sqlQuery, sqlParams,(ResultSet rs, int rowNum) -> {
                User tryuser = new User();
                tryuser.setUsername(rs.getString(1)); //get the string located at the 1st column of the result set
                tryuser.setPassword(rs.getString(2));
                return tryuser;
            });
            //Operator
            List<User> res2=jdbcTemplate.query(sqlQuery2, sqlParams,(ResultSet rs, int rowNum) -> {
                User tryuser = new User();
                tryuser.setUsername(rs.getString(1)); //get the string located at the 1st column of the result set
                tryuser.setPassword(rs.getString(2));
                return tryuser;
            });

            System.out.println("LoginQueryOK");
            if(res.size()==1){
                //Authentication success
                System.out.println("You are user:");
                String userinfo="username:"+res.get(0).getUsername()+",password:"+res.get(0).getPassword();
                /*Generate Token*/
                //String token = CreateToken(username);
                String token = RandomString(16);
                System.out.println("HUSTON");
                System.out.println(token);

                Object[] sqlParams2 = new Object[] {
                        username,
                        token
                };
                String LoggedUser="insert into LoggedUser(username,token) values(?,?)";
                this.jdbcTemplate.update(LoggedUser,sqlParams2);
                System.out.println("LoginEnd");
                return Optional.of(token);
            }
            else if(res2.size()==1){
                //Authentication success
                System.out.println("You are user:");
                String userinfo="username:"+res2.get(0).getUsername()+",password:"+res2.get(0).getPassword();
                /*Generate Token*/
                //String token = CreateToken(username);
                String token = RandomString(16);
                System.out.println("HUSTON");
                System.out.println(token);

                Object[] sqlParams2 = new Object[] {
                        username,
                        token
                };
                String LoggedUser="insert into LoggedUser(username,token) values(?,?)";
                this.jdbcTemplate.update(LoggedUser,sqlParams2);
                System.out.println("LoginEnd");
                return Optional.of(token);
            }
            else{
                String wronguser = "Login failed: Wrong username or password!";
                return Optional.of(wronguser);
            }
        }catch(Exception e){
            return null;
            //throw new DataAccessException("AlreadyLoggedIn",e);
        }
    }
    private String CreateToken(String username) {
        byte[] array = new byte[16]; // length is bounded by 16
        new Random().nextBytes(array);
        String token = new String(array, Charset.forName("UTF-8"));
        return username+token;
    }

    public Optional<String> logoutuser(String token)  {
        //String enctoken=encrypt(token);
        System.out.println("logout user--------------------------");
        Object[] sqlParams = new Object[] {
                token
        };
        System.out.println(token);
        String LoggedInQuery= "select * from LoggedUser where "+
                "token = ? ";
        String sqlQuery="delete from LoggedUser where "+
                "token = ? ";
        try{
            List<LoggedUsers> res=jdbcTemplate.query(LoggedInQuery, sqlParams,(ResultSet rs, int rowNum) -> {
                LoggedUsers tryuser = new LoggedUsers();
                tryuser.setUsername(rs.getString("username"));
                //return tryuser.getUsername();
                return tryuser;
            });
            System.out.println("BEFORE LOGGEDDDDDDDDDDDDDDDDDDD");
            if(res.size()==1){
                System.out.println("aFTER LOGGED");
                this.jdbcTemplate.update(sqlQuery,sqlParams);
                String logout ="Logout success!";
                return Optional.of(logout);
            }else{
                String error = "Error in logout...";
                return Optional.of(error);
            }
        }catch(Exception e){
            return null;
            //throw new DataAccessException("AlreadyLoggedIn",e);
        }
    }

    public String encrypt(String input) {
        //Encryption s = new Encryption(input);
        //return s.CreateEncryption();
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digestedmessage = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1,digestedmessage);
            String retval = no.toString(16);
            if(retval.length()<32){
                retval = "0".repeat(32-retval.length())+retval;
            }
            return retval;
        }catch(NoSuchAlgorithmException e){
            return "fail";
        }
    }

    public void reset() throws DataAccessException {
        String username = "admin";
        String password = "petrol4ever";
        //String password = encrypt("petrol4ever");
        //VALE KLEIDI MEGALO TYPO 100 GT TO SKAEI H ENCRYPT
        String encpass=(password);
        Object[] sqlParams = new Object[] {
                encpass
        };
        try{
            //System.out.println("ELA OLA KALA");
            this.jdbcTemplate.update("delete from LoggedUser");
            //System.out.println("TO PRTWTO OK");
            this.jdbcTemplate.update("delete from Session_");
            System.out.println("TO DEUTERO KAI TO PRWTO OK");

            this.jdbcTemplate.update("UPDATE User_ SET password = ?" +
                                      "WHERE UserID='admin'", sqlParams);
        }catch(Exception e){
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    static String RandomString(int n){
           // chose a Character random from this String
           String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                       + "0123456789"
                                       + "abcdefghijklmnopqrstuvxyz";
           // create StringBuffer size of AlphaNumericString
           StringBuilder sb = new StringBuilder(n);
           for (int i = 0; i < n; i++) {
               // generate a random number between
               // 0 to AlphaNumericString variable length
               int index = (int)(AlphaNumericString.length()* Math.random());
               // add Character one by one in end of sb
               sb.append(AlphaNumericString.charAt(index));
           }
           return sb.toString();
    }

}
