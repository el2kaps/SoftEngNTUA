package gr.ntua.ece.softeng2021

import spock.lang.Specification
import gr.ntua.ece.softeng2021.data.model.User
import gr.ntua.ece.softeng2021.backend.data.LoggedUsers
import gr.ntua.ece.softeng2021.data.model.SessionsPerPoint
import gr.ntua.ece.softeng2021.data.model.SessionsPerStation
import gr.ntua.ece.softeng2021.data.model.SessionsPerEV
import gr.ntua.ece.softeng2021.data.model.SessionsPerProvider
import gr.ntua.ece.softeng2021.backend.data.DataAccess
import gr.ntua.ece.softeng2021.backend.data.DataAccessException

class UnitTests extends Specification {
def "U01.UserSettersAndGetters"(){
    given:
    def user1 = new User("nikos","0123456789")
    def user2 = new User()
    when:
    user2.setUserID(1234)
    user2.setUsername("AA")
    user2.setPassword("PASS1234")
    user2.setFirstName("Hello1")
    user2.setLastName("Hello2")

    then:
    user1.getUsername() == "nikos"
    user1.getPassword() == "0123456789"
    user1.getFirstName() == null
    user1.getLastName() == null
    user2.getUsername() == "AA"
    user2.getPassword() == "PASS1234"
    user2.getFirstName() == "Hello1"
    user2.getLastName() == "Hello2"

  }

  def "U02.LoggedUsers"(){
    given:
    def Active1 = new LoggedUsers()
    when:
    Active1.setUsername("john")
    then:
    Active1.getUsername().equals("john")
  }

  def "U03.SessionsPerPoint"(){
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


  def "U04.SessionsPerStation"(){
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
    evses.setVehicleID("VehicleID111")
    evses.setRequestTimestamp("0000000000")
    evses.setPeriodFrom("2020-06-09")
    evses.setPeriodTo("2020-08-07")
    evses.setTotalEnergyConsumed(34.6)
    evses.setNumberOfVisitedPoints(3)
    evses.setNumberOfVehicleChargingSessions(7)
    evses.setSessionIndex(1)
    evses.setSessionID("MysessionID")
    evses.setEnergyProvider("BestProvider")
	  evses.setStartedOn("2018-09-19")
    evses.setFinishedOn("2018-10-20")
	  evses.setEnergyDelivered(83.4)
	  evses.setPricePolicyRef("PPR1")
	  evses.setCostPerKWh(3.4)
	  evses.setSessionCost(53.4)

    then:
    evses.getVehicleID()=="VehicleID111"
    evses.getRequestTimestamp()=="0000000000"
    evses.getPeriodFrom()=="2020-06-09"
    evses.getPeriodTo()=="2020-08-07"
    evses.getTotalEnergyConsumed()==34.6
    evses.getNumberOfVisitedPoints()==3
    evses.getNumberOfVehicleChargingSessions()==7
    evses.getSessionIndex()==1
    evses.getSessionID() == "MysessionID"
    evses.getEnergyProvider()== "BestProvider"
	  evses.getStartedOn()=="2018-09-19"
    evses.getFinishedOn()=="2018-10-20"
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
