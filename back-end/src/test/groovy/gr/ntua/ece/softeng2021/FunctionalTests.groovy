package gr.ntua.ece.softeng2021

import spock.lang.Specification
//import gr.ntua.ece.softeng2021.client.RestAPI
import spock.lang.Shared
import spock.lang.Stepwise
import java.lang.reflect.Array
import groovy.json.JsonSlurper
import gr.ntua.ece.softeng2021.data.model.User
import gr.ntua.ece.softeng2021.data.model.SessionsPerPoint
import gr.ntua.ece.softeng2021.data.model.SessionsPerStation
import gr.ntua.ece.softeng2021.data.model.SessionsPerEV
import gr.ntua.ece.softeng2021.data.model.SessionsPerProvider
import gr.ntua.ece.softeng2021.client.*

import java.nio.file.Path
import java.nio.file.Paths
import java.time.LocalDate

@Stepwise class FunctionalTests extends Specification{

    private static final String IGNORED = System.setProperty("IGNORE_SSL_ERRORS", "true")

    //private static final String HOST = System.getProperty("gretty.host")
    //private static final String PORT = System.getProperty("gretty.httpsPort")

    @Shared RestAPI caller1 = new RestAPI('localhost',8765)

    def "F01.Health check status is OK"(){
      given:
      String status = caller1.healthCheck()

      expect:
      status == "OK"

    }
    /*
        //einai ok auto alla valto pio katw
            def "F02.The database is reset successfully"(){
                given:
                String status = caller1.resetDatabase()

                expect:
                status == "OK"
            }


            def "F03.Login Admin"(){
              given:
              caller1.login("admin","petrol4ever")

              expect:
              //caller1.isLoggedIn()
              caller1.getToken() != null
            }
        */
        /*
            def "F04.Admin Creates a new user"(){
              given:
              User user = caller1.addUser("user","4321resu")

              expect:
              user.getusername() == "user"
            }
        */
    /*def "F05.Admin Imports SessionsPerPoint.csv"(){
      given:
      String csv = "/home/Downloads/point.csv" //to path pou exoume topika to arxeio

      when:
      ImportResult importResult = caller1.importFile("point",Path.of(csv))

      then:
      importResult.totalRecordsInFile == 151
      importResult.totalRecordsImported == 151
      importResult.totalRecordsInDatabase == 151
    }

    def "F06.Admin Imports Station.csv"(){
      given:
      String csv = "/home/Downloads/Station.csv"

      when:
      ImportResult importResult = caller1.importFile("Station",Path.of(csv))

      then:
      importResult.totalRecordsInFile == 51
      importResult.totalRecordsImported == 51
      importResult.totalRecordsInDatabase == 51
    }

    def "F07.Admin Imports cars.csv"(){
      given:
      String csv = "/home/Downloads/cars.csv"

      when:
      ImportResult importResult = caller1.importFile("cars",Path.of(csv))

      then:
      importResult.totalRecordsInFile == 151
      importResult.totalRecordsImported == 151
      importResult.totalRecordsInDatabase == 151 //paizei na prepei na prosthesoume kai ta prohgoumena (ta apo panw)
    }
*/


    def "F08.User logs In"(){
      given:
      caller1.login("user","4321resu")

      expect:
      caller1.isLoggedIn()
    }

    def "F09.User gets Points"(){
      given:
      List<SessionsPerPoint> records = caller1.getSessionsPerPoint("point_00f0a437f4ca44b79c7caa57c18e4a15", LocalDate.of(2018, 1, 4), LocalDate.of(2028, 1, 4), Format.JSON,"sdfdfadfas")

      expect:
      records.size() == 0
    }

    def "F10.User gets Stations"(){
      given:
      List<SessionsPerStation> records = caller1.getSessionsPerStation("station_0e0783c4f2554bbf9a2d7b5191634dd6",LocalDate.of(2018, 1, 4), LocalDate.of(2028, 1, 4), Format.JSON, "uyvit")

      expect:
      records.size()==0
    }

    def "F11.User gets EVs"(){
      given:
      List<SessionsPerEV> records = caller1.getSessionsPerEV("car_ecb42f1b4cd840ee8bf8aab87e5b86ab",LocalDate.of(2018, 1, 4), LocalDate.of(2028, 1, 4), Format.JSON,"PEINAW")

		expect:
		records.size()==0
    }

    def "F12.Helper For Next"(){
      given:
      caller1.login("admin","bufoewouq")

      expect:
      caller1.isLoggedIn()
    }


    def"F13.Helper"(){
      given:
      caller1.login("user","petrol4ever")

      expect:
      caller1.isLoggedIn()
    }


}
