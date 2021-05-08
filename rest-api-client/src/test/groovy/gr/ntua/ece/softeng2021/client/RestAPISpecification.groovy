package gr.ntua.ece.softeng2021.client

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.github.tomakehurst.wiremock.matching.MultipartValuePatternBuilder

import gr.ntua.ece.softeng2021.data.model.SessionsPerPoint
import gr.ntua.ece.softeng2021.data.model.SessionsPerStation
import gr.ntua.ece.softeng2021.data.model.SessionsPerEV
import gr.ntua.ece.softeng2021.data.model.SessionsPerProvider
import gr.ntua.ece.softeng2021.data.model.User

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

import java.nio.file.Path
import java.nio.file.Paths
import java.time.LocalDate

import static com.github.tomakehurst.wiremock.client.WireMock.*

@Stepwise
class RestAPISpecification extends Specification {

    private static final String TOKEN1 = "token1"
    private static final String TOKEN2 = "token2"

    private static final int MOCK_SERVER_PORT = 8766

    private static final String JSON_PAYLOAD_1 = """
[
  {
    "Point": "point_00f0a437f4ca44b79c7caa57c18e4a15",
    "PointOperator": "cso_1e739c72835942509a1ca9eb55ddfe6b",
    "RequestTimestamp": "421598785643",
    "PeriodFrom": "2020-11-1 00:00:00",
    "PeriodTo": "2022-11-30 00:00:00",
    "NumberOfChargingSessions": 1,
    //"SessionIndex": 1,
    "SessionID ": "charging_program_5c4fc0dcad7c4a91b895f07f41d93f10",
    "StartedOn": "2021-02-22 13:02:00",
    "FinishedOn": "2021-02-22 14:20:00",
    "Protocol": "Protocol2",
    "EnergyDelivered": 7.74,
    "Payment": "Card",
    "VehicleType": "Focus electric"
  }
]
""".stripIndent()

    private static final String JSON_PAYLOAD_2 = """
    [
        {
            "StationID": "station_181bbdd597d64b65926c33acfc975372",
            "Operator": "cso_6d7db8be65e349bc886daa7054450b86",
            "RequestTimestamp": "421598785643",
            "PeriodFrom": "2020-11-1 00:00:00",
            "PeriodTo": "2022-11-30 00:00:00",
            "TotalEnergyDelivered": 37.4,
            "NumberOfChargingSessions": 3,
            "PointID": "point_19740c533df04e13b6a3b51610eb8926",
            "PointSessions": 1,
            "EnergyDelivered": 11.98
        }
    ]
""".stripIndent()

private static final String JSON_PAYLOAD_3 = """
[
    {
        "VehicleID": "car_05e36be4b8294a6dbda2beb244fc5c4a",
        "RequestTimestamp": "421598785643",
        "PeriodFrom": "2020-11-1 00:00:00",
        "PeriodTo": "2022-11-30 00:00:00",
        "TotalEnergyConsumed": 19.53,
        "NumberOfVisitedPoints": 1,
        "SessionID": "charging_program_72317592b45240ff8878ca59fc8a2730",
        "EnergyProvider": "Nichols",
        "StartedOn": "2021-02-23 19:53:00",
        "FinishedOn": "2021-02-23 20:05:00",
        "EnergyDelivered": 19.53,
        "PricePolicyRef": "PricePolicyRef2",
        "CostPerKWh": 11.74,
        "SessionCost": 229.2822
    }
]
""".stripIndent()

private static final String JSON_PAYLOAD_4 = """
[]
""".stripIndent()


    @Shared WireMockServer wms
    @Shared RestAPI caller1 = new RestAPI("localhost",8765)
    @Shared RestAPI caller2 = new RestAPI("localhost", 8765)

    def setupSpec() {
        wms = new WireMockServer(WireMockConfiguration.options().httpsPort(MOCK_SERVER_PORT))
        wms.start()
    }

    def cleanupSpec() {
        wms.stop()
    }

    def "T01. Health check status is OK"() {
        given:
        wms.givenThat(
            get(
                urlEqualTo("/evcharge/api/admin/healthcheck")
            ).willReturn(
                okJson('{"status":"OK"}')
            )
        )

        when:
        String status = caller1.healthCheck()

        then:
        status == "OK"
    }


/*
    def "T02. Admin creates a new user"() {
        given:
        wms.givenThat(
            post(
                urlEqualTo("/evcharge/api/admin/usermod")
            ).withHeader(
                RestAPI.CUSTOM_HEADER, equalTo(TOKEN1)
            )
            .withRequestBody(
                equalTo(ClientHelper.encode([username:"user", email:"user@ntua.gr", password:"secretcombination"])) //swsta pedia apo data model user
            ).willReturn(
                okJson('{"username":"user", "email":"user@ntua.gr"}')
            )
        )

        when:
        User user = caller1.addUser("user", "user@ntua.gr", "secretcombination")

        then:
        user.getUsername() == "user" &&
        user.getemail() == "user@ntua.gr"
    }
*/

/*

    def "T03. User retrieves SessionsPerPoint tuple"() {
        given:
        wms.givenThat(
            get(
                urlEqualTo("/evcharge/api/SessionsPerPoint/point_00f0a437f4ca44b79c7caa57c18e4a15/20201101/20221130?format=json")
            ).withHeader(
                RestAPI.CUSTOM_HEADER, equalTo(TOKEN2)
            ).willReturn(
                okJson(JSON_PAYLOAD_1)
            )
        )

        when:
        List<SessionsPerPoint> records = caller2.getSessionsPerPoint("point_00f0a437f4ca44b79c7caa57c18e4a15", LocalDate.of(2020, 11, 1), LocalDate.of(2022, 11, 30), Format.JSON,"n2HigJIB16glkVlg")

        then:
        records.size() >= 0
    }

    def "T04. User retrieves SessionsPerStation"() {
        given:
        wms.givenThat(
            get(
                urlEqualTo("/evcharge/api/SessionsPerStation/station_181bbdd597d64b65926c33acfc975372/20201101/20221130?format=json")
            ).withHeader(
                RestAPI.CUSTOM_HEADER, equalTo(TOKEN2)
            ).willReturn(
                okJson(JSON_PAYLOAD_2)
            )
        )

        when:
        List<SessionsPerStation> records = caller2.getSessionsPerStation(
            "station_181bbdd597d64b65926c33acfc975372",
            LocalDate.of(2020, 11, 1), LocalDate.of(2022, 11, 30), Format.JSON,"n2HigJIB16glkVlg")

        then:
        records.size() >= 0
    }

   def "T05. User retrieves SessionsPerEV tuple from 20160304"() {
        given:
        wms.givenThat(
            get(
                urlEqualTo("/evcharge/api/SessionsPerEV/car_05e36be4b8294a6dbda2beb244fc5c4a/20201101/20221130?format=json")
            ).withHeader(
                RestAPI.CUSTOM_HEADER, equalTo(TOKEN2)
            ).willReturn(
                okJson(JSON_PAYLOAD_3)
            )
        )

        when:
        List<SessionsPerEV> records = caller2.getSessionsPerEV(
            "car_05e36be4b8294a6dbda2beb244fc5c4a",
              LocalDate.of(2020, 11, 1), LocalDate.of(2022, 11, 30), Format.JSON,"n2HigJIB16glkVlg")

        then:
        records.size() >= 0
    }

    def "T06. User retrieves SessionsPerProvider tuple from 20160304"() {
         given:
         wms.givenThat(
             get(
                 urlEqualTo("/evcharge/api/SessionsPerProvider/charging_program_01d9144caca94bebb5b55bb278cde93c/20201101/20221130?format=json")
             ).withHeader(
                 RestAPI.CUSTOM_HEADER, equalTo(TOKEN2)
             ).willReturn(
                 okJson(JSON_PAYLOAD_3)
             )
         )

         when:
         List<SessionsPerProvider> records = caller2.getAGPT(
             "charging_program_01d9144caca94bebb5b55bb278cde93c",
             LocalDate.of(2020, 11, 1), LocalDate.of(2022, 11, 30), Format.JSON,"n2HigJIB16glkVlg")

         then:
         records.size() >= 0
     }
*/
    def "T07. Helper"() {
        given:
        wms.givenThat(
            post(
                urlEqualTo("/evcharge/api/login")
            ).withRequestBody(
                equalTo(ClientHelper.encode([username:"user", password:"secretcombination"]))
            ).willReturn(
                okJson("""{"token":"${TOKEN2}"}""")
            )
        )

        when:
        caller2.login("user", "secretcombination")

        then:
        caller2.isLoggedIn()
    }


    def "T08. Helper2"() {
        given:
        wms.givenThat(
            post(
                urlEqualTo("/evcharge/api/login")
            ).withRequestBody(
                equalTo(ClientHelper.encode([username:"user", password:"secretcombination"]))
            ).willReturn(
                okJson("""{"token":"${TOKEN2}"}""")
            )
        )

        when:
        caller2.login("user", "secretcombination")

        then:
        caller2.isLoggedIn()
    }


    def "T9. User logs out"() {
        given:
        wms.givenThat(
            post(
                urlEqualTo("/evcharge/api/logout")
            ).withHeader(
                RestAPI.CUSTOM_HEADER, equalTo(TOKEN2)
            )
        )

        when:
        caller2.logout()

        then:
        !caller2.isLoggedIn()
    }
/*
    def "T10. Anonymous users cannot access protected resources"() {
        given:
        wms.givenThat(
            get(
                urlEqualTo("/evcharge/api/SessionsPerPoint/point_00f0a437f4ca44b79c7caa57c18e4a15/20201101/20221130?format=json")
            ).willReturn(
                aResponse().withStatus(401)
            )
        )

        when:
        caller2.getSessionsPerPoint("point_00f0a437f4ca44b79c7caa57c18e4a15", LocalDate.of(2020, 11, 1), LocalDate.of(2022, 11, 30), Format.JSON,"n2HigJIB16glkVlg")

        then:
        ServerResponseException exception = thrown()
        exception.getStatusCode() == 401
    }
*/

def "T11. The database is reset successfully"() {
    given:
    wms.givenThat(
        post(
            urlEqualTo("/evcharge/api/resetsessions")
        ).willReturn(
            okJson('{"status":"OK"}')
        )
    )

    when:
    String status = caller1.resetDatabase()

    then:
    status == "OK"
}


def "T12. Admin logs in successfully"() {
    given:
    wms.givenThat(
        post(
            urlEqualTo("/evcharge/api/login")
        ).withRequestBody(
            equalTo(ClientHelper.encode([username:"admin", password:"petrol4ever"]))
        ).willReturn(
            okJson("""{"token":"${TOKEN1}"}""")
        )
    )

    when:
    caller1.login("admin", "petrol4ever")

    then:
    caller1.isLoggedIn()
}

def "T13. User logs in"() {
    given:
    wms.givenThat(
        post(
            urlEqualTo("/evcharge/api/login")
        ).withRequestBody(
            equalTo(ClientHelper.encode([username:"ferventCur0", password:"EYK5BKG6"]))
        ).willReturn(
            okJson("""{"token":"${TOKEN2}"}""")
        )
    )

    when:
    caller2.login("ferventCur0", "EYK5BKG6")

    then:
    caller2.isLoggedIn()
}

    def "T14. Admin logs out"() {
        given:
        wms.givenThat(
            post(
                urlEqualTo("/evcharge/api/logout")
            ).withHeader(
                RestAPI.CUSTOM_HEADER, equalTo(TOKEN1)
            )
        )

        when:
        caller1.logout()

        then:
        !caller1.isLoggedIn()
    }

        def "T15. Helper"() {
            given:
            wms.givenThat(
                post(
                    urlEqualTo("/evcharge/api/login")
                ).withRequestBody(
                    equalTo(ClientHelper.encode([username:"guiltyGatorade0", password:"yf9rzHOC"]))
                ).willReturn(
                    okJson("""{"token":"${TOKEN1}"}""")
                )
            )

            when:
            caller1.login("guiltyGatorade0", "yf9rzHOC")

            then:
            caller1.isLoggedIn()
        }
}
