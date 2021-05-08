package gr.ntua.ece.softeng2021.backend.data.model;

import java.util.List;

public class RecordSessionsPerProvider {
    private String ProviderID;
    private String ProviderName;
    private String StationID;
    private String SessionID;
    private String VehicleID;
    private String StartedOn;
    private String FinishedOn;
    private Integer NumberOfActivePoints;
    private Float EnergyDelivered;
    private String PricePolicyRef;
    private Float CostPerKWh;
    private Float TotalCost;

    public RecordSessionsPerProvider(){}

    public void setProviderID(String providerID) {
        //System.out.println("AODNAO");
        ProviderID = providerID;
    }

    public void setProviderName(String providerName) {
        ProviderName = providerName;
    }

    public void setStationID(String stationID) {
        StationID = stationID;
    }

    public void setSessionID(String sessionID) {
        SessionID = sessionID;
    }

    public void setVehicleID(String vehicleID) {
        VehicleID = vehicleID;
    }

    public void setStartedOn(String startedOn) {
        StartedOn = startedOn;
    }

    public void setFinishedOn(String finishedOn) {
        FinishedOn = finishedOn;
    }

    public void setNumberOfActivePoints(Integer numberOfActivePoints) {
        NumberOfActivePoints = numberOfActivePoints;
    }

    public void setEnergyDelivered(Float energyDelivered) {
        EnergyDelivered = energyDelivered;
    }

    public void setPricePolicyRef(String pricePolicyRef) {
        PricePolicyRef = pricePolicyRef;
    }

    public void setCostPerKWh(Float costPerKWh) {
        CostPerKWh = costPerKWh;
    }

    public void setTotalCost(Float totalCost) {
        TotalCost = totalCost;
    }

    public String getProviderID() {
        return ProviderID;
    }

    public String getProviderName() {
        return ProviderName;
    }

    public String getStationID() {
        return StationID;
    }

    public String getSessionID() {
        return SessionID;
    }

    public String getVehicleID() {
        return VehicleID;
    }

    public String getStartedOn() {
        return StartedOn;
    }

    public String getFinishedOn() {
        return FinishedOn;
    }

    public Integer getNumberOfActivePoints() {
        return NumberOfActivePoints;
    }

    public Float getEnergyDelivered() {
        return EnergyDelivered;
    }

    public String getPricePolicyRef() {
        return PricePolicyRef;
    }

    public Float getCostPerKWh() {
        return CostPerKWh;
    }

    public Float getTotalCost() {
        return TotalCost;
    }
    public String[] toString2(){
        return new String[]{
                this.ProviderID,
                this.ProviderName,
                this.StationID,
                this.SessionID,
                this.VehicleID,
                this.StartedOn,
                this.FinishedOn,
                String.valueOf(this.EnergyDelivered),
                this.PricePolicyRef,
                String.valueOf(this.CostPerKWh),
                String.valueOf(this.TotalCost)
        };
    }
}
