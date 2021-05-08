/*package gr.ntua.ece.softeng2021.backend.data;

import java.util.Objects;

public class Charging {
    private Integer ChargingID;
    private String username;
    private String pass;
    private String firstName;
    private String lastName;
    private String email;

    public User (String name, String pass, String firstName, String lastName, String email){
        this.username = name;
        this.pass = pass;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public User() {

    }

    public Integer getUserID(){
        return this.UserID;
    }
    public String getUsername(){
        return this.username;
    }
    public String getPass() { return this.pass;}
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public String getEmail(){
        return this.email;
    }
    public void setUserID(Integer id){
        this.UserID = id;
    }
    public void setUsername(String name){
        this.username = name;
    }
    public void setPass(String password){
        this.pass = password;
    }
    public void setFirstName(String name){
        this.firstName = name;
    }
    public void setLastName(String name){
        this.lastName = name;
    }
    public void setEmail(String mail){
        this.email = mail;
    }
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if(!(o instanceof User)) return false;
        User u = (User) o;
        return Objects.equals(this.UserID,u.UserID) && Objects.equals(this.username,u.username);
    }
    @Override
    public int hashCode(){
        return Objects.hash(this.UserID,this.username);
    }
    @Override
    public String toString(){
        return "User{"+"UserID="+this.UserID+", username="+this.username+"}";
    }
}
}
*/