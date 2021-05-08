package gr.ntua.ece.softeng2021

import spock.lang.Specification
import gr.ntua.ece.softeng2021.data.model.User

class IntegrationUnittests extends Specification{

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
}
