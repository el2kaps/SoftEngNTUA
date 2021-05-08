package gr.ntua.ece.softeng2021.backend.data;

import java.util.Objects;

public class LoggedUsers {
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    private String username;
    private String token;


    public LoggedUsers (String username, String token){
        this.username = username;
        this.token = token;
    }

    public LoggedUsers() {
    }

    public String getToken(){
        return this.token;
    }
    public void setToken(String token){ this.token = token;}

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if(!(o instanceof LoggedUsers)) return false;
        LoggedUsers u = (LoggedUsers) o;
        return Objects.equals(this.token,u.token);
    }
    @Override
    public int hashCode(){
        return Objects.hash(this.token);
    }
    @Override
    public String toString(){
        return "LoggedIn_User_with_token:{"+this.token+"}";
    }
}
