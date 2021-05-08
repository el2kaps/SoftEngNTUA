/*package gr.ntua.ece.softeng2021.backend.data;

import java.util.Objects;

public class Station {
    private Integer stationID;
    private double latitude;
    private double longtitude;
    private Integer CSOID;

    public Station (double latitude, double longtitude, Integer CSOID){
        //this.stationID = ;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.CSOID = CSOID;
    }

    public Station() {

    }

    public Integer getStationID(){
        return this.stationID;
    }
    public double getLatitude(){
        return this.latitude;
    }
    public double getLongtitude() { return this.longtitude;}
    public Integer getCSOID(){ return this.CSOID; }
    public void setStationID(Integer id){ this.stationID = id; }
    public void setLatitude(double num){
        this.latitude = num;
    }
    public void setLongtitude(double num){
        this.longtitude = num;
    }
    public void setCSOID(Integer cso){
        this.CSOID = cso;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if(!(o instanceof Station)) return false;
        Station u = (Station) o;
        return Objects.equals(this.stationID,u.stationID);
    }
    @Override
    public int hashCode(){
        return Objects.hash(this.stationID);
    }
    @Override
    public String toString(){
        return "Station{"+"stationID="+this.stationID+", lat="+this.latitude+ ", long="+this.longtitude+"}";
    }
}
}
*/