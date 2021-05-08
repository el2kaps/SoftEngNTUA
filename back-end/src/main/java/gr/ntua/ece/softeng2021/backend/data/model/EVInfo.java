package gr.ntua.ece.softeng2021.backend.data.model;

import java.util.List;

public class EVInfo {

    private String VehicleID;
    private String VehicleType;
    private Float Capacity;

    public EVInfo(){}


    public void setVehicleID(String vehicleID) {
        VehicleID = vehicleID;
    }

   
    public void setVehicleType(String vehicleType) {
        VehicleType = vehicleType;
    }

    public void setVehicleCapacity(Float c) {
        this.Capacity = c;
    }

    public String getVehicleID() {
        return this.VehicleID;
    }

   
    public String getVehicleType() {
        return this.VehicleType;
    }

    public Float getVehicleCapacity() {
        return this.Capacity;
    }
}

    