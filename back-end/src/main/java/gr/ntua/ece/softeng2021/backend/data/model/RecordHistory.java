package gr.ntua.ece.softeng2021.backend.data.model;

import java.util.List;

public class RecordHistory {

    private String VehicleID;
    private String  SessionID;
    private String PointID;
    private String ProviderID;
    private String OperatorID;
    private String StartedOn;
    private String FinishedOn;
    private String Protocol;
    private Float EnergyDelivered;

    public RecordHistory(){}


    public void setVehicleID(String vehicleID) {
        VehicleID = vehicleID;
    }

   
    public void setSessionID(String sessionID) {
        SessionID = sessionID;
    }

    public void setPointID(String pid) {
        PointID = pid;
    }

     public void setProviderID(String prov) {
        ProviderID = prov;
    }
      public void setOperatorID(String prov) {
        OperatorID = prov;
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

    public void setProtocol(String proto) {
        Protocol = proto;
    }

 public String getVehicleID() {
        return this.VehicleID;
    }

   
    public String getSessionID() {
        return this.SessionID;
    }

    public String getPointID() {
        return this.PointID;
    }

     public String getProviderID() {
        return this.ProviderID;
    }
      public String getOperatorID() {
        return this.OperatorID;
    }

    public String getStartedOn() {
        return this.StartedOn;
    }

    public String getFinishedOn() {
        return this.FinishedOn;
    }

    public Float getEnergyDelivered() {
        return this.EnergyDelivered;
    }

    public String getProtocol() {
        return this.Protocol;
    }

    

   
   /* public String[] toString2(){
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
    }*/
}
