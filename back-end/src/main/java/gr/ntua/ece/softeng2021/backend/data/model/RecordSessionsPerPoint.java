package gr.ntua.ece.softeng2021.backend.data.model;

import java.util.List;

public class RecordSessionsPerPoint{
    private String Point;
    private String PointOperator;
    private String RequestTimestamp;
    private String PeriodFrom;
    private String PeriodTo;
    private Integer NumberOfChargingSessions;
    private List<RecordSessionsPerPoint> ChargingSessionsList;

    public void setSessionIndex(Integer sessionIndex) {
        SessionIndex = sessionIndex;
    }

    private Integer SessionIndex;

    public void setSessionID(String sessionID) {
        SessionID = sessionID;
    }

    private String SessionID;

    public void setStartedOn(String startedOn) {
        StartedOn = startedOn;
    }

    private String StartedOn;

    public void setFinishedOn(String finishedOn) {
        FinishedOn = finishedOn;
    }

    private String FinishedOn;

    public void setProtocol(String protocol) {
        Protocol = protocol;
    }

    private String Protocol;

    public void setEnergyDelivered(Float energyDelivered) {
        EnergyDelivered = energyDelivered;
    }

    private Float EnergyDelivered;

    public void setPayment(String payment) {
        Payment = payment;
    }

    private String Payment;

    public void setVehicleType(String vehicleType) {
        VehicleType = vehicleType;
    }

    private String VehicleType;

    public RecordSessionsPerPoint(){}
    public void setPoint(String p){
        this.Point = p;
    }
    public void setPointOperator(String x){
        this.PointOperator = x;
    }
    public void setRequestTimestamp(String x){
        this.RequestTimestamp = x;
    }
    public void setPeriodFrom(String x){
        this.PeriodFrom = x;
    }
    public void setPeriodTo(String x){
        this.PeriodTo = x;
    }
    public void setNumberOfChargingSessions(Integer x){
        this.NumberOfChargingSessions = x;
    }
    public void setChargingSessionsList(List<RecordSessionsPerPoint> x){
        this.ChargingSessionsList = x;
    }


    public String getPoint() {
        return Point;
    }

    public String getPointOperator() {
        return PointOperator;
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

    public Integer getNumberOfChargingSessions() {
        return NumberOfChargingSessions;
    }

    public List<RecordSessionsPerPoint> getChargingSessionsList() {
        return ChargingSessionsList;
    }

    public Integer getSessionIndex() {
        return SessionIndex;
    }

    public String getSessionID() {
        return SessionID;
    }

    public String getStartedOn() {
        return StartedOn;
    }

    public String getFinishedOn() {
        return FinishedOn;
    }

    public String getProtocol() {
        return Protocol;
    }

    public Float getEnergyDelivered() {
        return EnergyDelivered;
    }

    public String getPayment() {
        return Payment;
    }

    public String getVehicleType() {
        return VehicleType;
    }
    public String[] toString2(){
        return new String[]{
                this.Point,
                this.PointOperator,
                this.RequestTimestamp,
                this.PeriodFrom,
                this.PeriodFrom,
                String.valueOf(this.NumberOfChargingSessions),
                //list,
                // Index
                this.SessionID,
                this.StartedOn,
                this.FinishedOn,
                this.Protocol,
                String.valueOf(this.EnergyDelivered),
                this.Payment,
                this.VehicleType
        };
    }
}
