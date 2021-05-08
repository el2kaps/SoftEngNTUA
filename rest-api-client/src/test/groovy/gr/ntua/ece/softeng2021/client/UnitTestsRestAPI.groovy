package gr.ntua.ece.softeng2021.client

import spock.lang.Specification

import gr.ntua.ece.softeng2021.data.model.User
import gr.ntua.ece.softeng2021.data.model.SessionsPerPoint
import gr.ntua.ece.softeng2021.data.model.SessionsPerStation
import gr.ntua.ece.softeng2021.data.model.SessionsPerEV
import gr.ntua.ece.softeng2021.data.model.SessionsPerProvider

import spock.lang.Stepwise
import java.nio.file.Path
import java.nio.file.Paths
import java.time.LocalDate


class UnitTestRestAPI extends Specification {
  def "U01.UserSettersAndGetters"(){
    given:
    //def user1 = new User("myuserID","username78e787e7","password88977779","Smthing","Somthing")
    def user1 = new User("username78e787e7","password88977779")

    def user2 = new User()
    when:
    //user2.setUserID("myuserID2")
    user2.setUsername("username8983298e")
    user2.setPassword("pass8983298e")
    //user2.setFirstName("Hello1")
    //user2.setLastName("Helllo2")

    then:
    //user1.getUserID() == "myuserID"
    user1.getUsername() == "username78e787e7"
    user1.getPassword() == "password88977779"
    //user1.getFirstName() == "Smthing"
    //user1.getLastName() =="Somthing"
    //user1.getisadmin() == 0
    //user2.equals(new User("myuserID2","username8983298e","pass8983298e","Hello1","Helllo2"))
    user2.equals(new User("username8983298e","pass8983298e"))
  }


def "U02.SessionsPerPoint"(){
    given:
    def pointses = new SessionsPerPoint()

    when:
    pointses.setPoint("point1")
    pointses.setPointOperator("operator1")
    pointses.setRequestTimestamp("421598785643")
    pointses.setPeriodFrom("20191112")
    pointses.setPeriodTo("20211112")
    pointses.setNumberOfChargingSessions(1)
    pointses.setSessionIndex(1)
    pointses.setSessionID("sess1")
    pointses.setStartedOn("15202020")
    pointses.setFinishedOn("15202020")
	pointses.setProtocol("protocol1")
	pointses.setEnergyDelivered(83.4)
	pointses.setPayment("Card")
	pointses.setVehicleType("Vehicle1")

    then:
    pointses.getPoint()=="point1"
    pointses.getPointOperator()=="operator1"
    pointses.getRequestTimestamp()=="421598785643"
    pointses.getPeriodFrom()=="20191112"
    pointses.getPeriodTo()=="20211112"
    pointses.getNumberOfChargingSessions()==1
    pointses.getSessionIndex()==1
    pointses.getSessionID()=="sess1"
    pointses.getStartedOn()=="15202020"
    pointses.getFinishedOn()=="15202020"
	pointses.getProtocol()=="protocol1"
	pointses.getEnergyDelivered()==83.4
	pointses.getPayment()=="Card"
	pointses.getVehicleType()=="Vehicle1"

  }
  def "U03.SessionsPerStation"(){
    given:
    def stationses = new SessionsPerStation()

    when:
    stationses.setStationID("station1")
    stationses.setOperator("operator1")
    stationses.setRequestTimestamp("421598785643")
    stationses.setPeriodFrom("20191112")
    stationses.setPeriodTo("20211112")
    stationses.setNumberOfChargingSessions(1)
    stationses.setNumberOfActivePoints(1)
    stationses.setPointID("point1")
    stationses.setPointSessions(1)

    then:
    stationses.getStationID()=="station1"
    stationses.getOperator()=="operator1"
    stationses.getRequestTimestamp()=="421598785643"
    stationses.getPeriodFrom()=="20191112"
    stationses.getPeriodTo()=="20211112"
    stationses.getNumberOfChargingSessions() == 1
    stationses.getNumberOfActivePoints()==1
    stationses.getPointID()=="point1"
    stationses.getPointSessions() == 1

  }

  def "U05.SessionsPerEV"(){
    given:
    def evses = new SessionsPerEV()

    when:
    evses.setVehicleID("vehicle1")
    evses.setRequestTimestamp("421598785643")
    evses.setPeriodFrom("20120201")
    evses.setPeriodTo("20211112")
    evses.setTotalEnergyConsumed(17.0)
    evses.setNumberOfVisitedPoints(1)
    evses.setNumberOfVehicleChargingSessions(1)
    evses.setSessionIndex(1)
    evses.setSessionID("sess1")
    evses.setEnergyProvider("provider1")
	  evses.setStartedOn("15202020")
    evses.setFinishedOn("15202020")
	  evses.setEnergyDelivered(83.4)
	  evses.setPricePolicyRef("PPR1")
	  evses.setCostPerKWh(3.4)
	  evses.setSessionCost(53.4)

    then:
    evses.getVehicleID()=="vehicle1"
    evses.getRequestTimestamp()=="421598785643"
    evses.getPeriodFrom()=="20120201"
    evses.getPeriodTo()=="20211112"
    evses.getTotalEnergyConsumed()==17.0
    evses.getNumberOfVisitedPoints()==1
    evses.getNumberOfVehicleChargingSessions()==1
    evses.getSessionIndex()==1
    evses.getSessionID() == "sess1"
    evses.getEnergyProvider()=="provider1"
	  evses.getStartedOn()=="15202020"
    evses.getFinishedOn()=="15202020"
	  evses.getEnergyDelivered()==83.4
	  evses.getPricePolicyRef()=="PPR1"
	  evses.getCostPerKWh()==3.4
	  evses.getSessionCost()==53.4
  }

def "U06.SessionsPerProvider"(){
    given:
    def providerses = new SessionsPerProvider()

    when:
    providerses.setProviderID("provider1")
    providerses.setProviderName("Jones")
    providerses.setStationID("station1")
    providerses.setSessionID("session1")
    providerses.setVehicleID("vehicle1")
    providerses.setStartedOn("20201516")
    providerses.setFinishedOn("20201516")
    providerses.setEnergyDelivered(67)
    providerses.setPricePolicyRef("PPR2")
    providerses.setCostPerKWh(2.34)
  	providerses.setTotalCost(46)

    then:
    providerses.getProviderID()=="provider1"
    providerses.getProviderName()=="Jones"
    providerses.getStationID()=="station1"
    providerses.getSessionID()=="session1"
    providerses.getVehicleID()=="vehicle1"
    providerses.getStartedOn()== "20201516"
    providerses.getFinishedOn()=="20201516"
    providerses.getEnergyDelivered()==67
    providerses.getPricePolicyRef() =="PPR2"
    providerses.getCostPerKWh()==2.34
	  providerses.getTotalCost()==46
  }

}
