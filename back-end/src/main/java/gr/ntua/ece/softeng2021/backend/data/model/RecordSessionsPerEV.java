package gr.ntua.ece.softeng2021.backend.data.model;

import java.util.List;

public class RecordSessionsPerEV {
    private String VehicleID;
    private String RequestTimestamp;
    private String PeriodFrom;
    private String PeriodTo;
    private Float TotalEnergyConsumed;
    private Integer NumberOfVisitedPoints;
    private Integer NumberOfVehicleChargingSessions;
    private List<RecordSessionsPerEV> VehicleChargingSessionsList;
    private Integer SessionIndex;
    private String SessionID;
    private String EnergyProvider;
    private String StartedOn;
    private String FinishedOn;
    private Float EnergyDelivered;
    private String PricePolicyRef;
    private Float CostPerKWh;
    private Float SessionCost;

    public RecordSessionsPerEV(){}


    public void setVehicleID(String vehicleID) {
        VehicleID = vehicleID;
    }

    public void setRequestTimestamp(String requestTimestamp) {
        RequestTimestamp = requestTimestamp;
    }

    public void setPeriodFrom(String periodFrom) {
        PeriodFrom = periodFrom;
    }

    public void setPeriodTo(String periodTo) {
        PeriodTo = periodTo;
    }

    public void setTotalEnergyConsumed(Float totalEnergyConsumed) {
        TotalEnergyConsumed = totalEnergyConsumed;
    }

    public void setNumberOfVisitedPoints(Integer numberOfVisitedPoints) {
        NumberOfVisitedPoints = numberOfVisitedPoints;
    }

    public void setNumberOfVehicleChargingSessions(Integer numberOfVehicleChargingSessions) {
        NumberOfVehicleChargingSessions = numberOfVehicleChargingSessions;
    }

    public void setVehicleChargingSessionsList(List<RecordSessionsPerEV> vehicleChargingSessionsList) {
        VehicleChargingSessionsList = vehicleChargingSessionsList;
    }

    public void setSessionIndex(Integer sessionIndex) {
        SessionIndex = sessionIndex;
    }

    public void setSessionID(String sessionID) {
        SessionID = sessionID;
    }

    public void setEnergyProvider(String energyProvider) {
        EnergyProvider = energyProvider;
    }

    public void setStartedOn(String startedOn) {
        StartedOn = startedOn;
    }

    public void setFinishedOn(String finishedOn) {
        FinishedOn = finishedOn;
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

    public void setSessionCost(Float sessionCost) {
        SessionCost = sessionCost;
    }

    public String getVehicleID() {
        return VehicleID;
    }

    public String getRequestTimestamp() {
        return RequestTimestamp;
    }

    public String getPeriodFrom() {
        return PeriodFrom;
    }

    public String getPeriodTo() {
        return PeriodTo;
    }

    public Float getTotalEnergyConsumed() {
        return TotalEnergyConsumed;
    }

    public Integer getNumberOfVisitedPoints() {
        return NumberOfVisitedPoints;
    }

    public Integer getNumberOfVehicleChargingSessions() {
        return NumberOfVehicleChargingSessions;
    }

    public List<RecordSessionsPerEV> getVehicleChargingSessionsList() {
        return VehicleChargingSessionsList;
    }

    public Integer getSessionIndex() {
        return SessionIndex;
    }

    public String getSessionID() {
        return SessionID;
    }

    public String getEnergyProvider() {
        return EnergyProvider;
    }

    public String getStartedOn() {
        return StartedOn;
    }

    public String getFinishedOn() {
        return FinishedOn;
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

    public Float getSessionCost() {
        return SessionCost;
    }
    public String[] toString2(){
        return new String[]{
                this.VehicleID,
                this.RequestTimestamp,
                this.PeriodFrom,
                this.PeriodFrom,
                String.valueOf(this.TotalEnergyConsumed),
                String.valueOf(this.NumberOfVisitedPoints),
                String.valueOf(this.NumberOfVehicleChargingSessions),
                //list, index
                this.SessionID,
                this.EnergyProvider,
                this.StartedOn,
                this.FinishedOn,
                String.valueOf(this.EnergyDelivered),
                this.PricePolicyRef,
                String.valueOf(this.CostPerKWh),
                String.valueOf(this.SessionCost)
        };
    }
}
