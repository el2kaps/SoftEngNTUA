package gr.ntua.ece.softeng2021.backend.data;

import java.util.Objects;

public class User {
    private Integer UserID;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public User (String name, String pass){
        this.username = name;
        this.password = pass;
        this.firstName = null;
        this.lastName = null;
    }

    public User() {
    }

    public Integer getUserID(){
        return this.UserID;
    }
    public String getUsername(){
        return this.username;
    }
    public String getPassword() { return this.password;}
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public void setUserID(Integer id){ this.UserID = id;}
    public void setUsername(String name){ this.username = name;}
    public void setPassword(String password){ this.password = password; }
    public void setFirstName(String name){ this.firstName = name; }
    public void setLastName(String name){ this.lastName = name; }
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
    public String[] toString2(){
        return new String[]{
                this.username,
                this.firstName,
                this.lastName
                 };
    }
}


