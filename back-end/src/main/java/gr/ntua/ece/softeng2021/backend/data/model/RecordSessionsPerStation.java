package gr.ntua.ece.softeng2021.backend.data.model;

import java.util.List;

public class RecordSessionsPerStation {
    private String StationID;
    private String Operator;
    private String RequestTimestamp;
    private String PeriodFrom;
    private String PeriodTo;
    private Float TotalEnergyDelivered;
    private Integer NumberOfChargingSessions;
    private Integer NumberOfActivePoints;
    private List<RecordSessionsPerPoint> SessionsSummaryList;
    private String PointID;
    private Integer PointSessions;
    private Float EnergyDelivered;

    public RecordSessionsPerStation(){}

    public void setStationID(String stationID) {
        StationID = stationID;
    }

    public void setOperator(String operator) {
        Operator = operator;
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

    public void setTotalEnergyDelivered(Float totalEnergyDelivered) {
        TotalEnergyDelivered = totalEnergyDelivered;
    }

    public void setNumberOfChargingSessions(Integer numberOfChargingSessions) {
        NumberOfChargingSessions = numberOfChargingSessions;
    }

    public void setNumberOfActivePoints(Integer numberOfActivePoints) {
        NumberOfActivePoints = numberOfActivePoints;
    }

    public void setSessionsSummaryList(List<RecordSessionsPerPoint> sessionsSummaryList) {
        SessionsSummaryList = sessionsSummaryList;
    }

    public void setPointID(String pointID) {
        PointID = pointID;
    }

    public void setPointSessions(Integer pointSessions) {
        PointSessions = pointSessions;
    }

    public void setEnergyDelivered(Float energyDelivered) {
        EnergyDelivered = energyDelivered;
    }

    public String getStationID() {
        return StationID;
    }

    public String getOperator() {
        return Operator;
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

    public Float getTotalEnergyDelivered() {
        return TotalEnergyDelivered;
    }

    public Integer getNumberOfChargingSessions() {
        return NumberOfChargingSessions;
    }

    public Integer getNumberOfActivePoints() {
        return NumberOfActivePoints;
    }

    public List<RecordSessionsPerPoint> getSessionsSummaryList() {
        return SessionsSummaryList;
    }

    public String getPointID() {
        return PointID;
    }

    public Integer getPointSessions() {
        return PointSessions;
    }

    public Float getEnergyDelivered() {
        return EnergyDelivered;
    }

    public String[] toString2(){
        return new String[]{
                this.StationID,
                this.Operator,
                this.RequestTimestamp,
                this.PeriodFrom,
                this.PeriodFrom,
                String.valueOf(this.TotalEnergyDelivered),
                String.valueOf(this.NumberOfChargingSessions),
                String.valueOf(this.NumberOfActivePoints),
                //list,
                this.PointID,
                String.valueOf(this.PointSessions),
                String.valueOf(this.EnergyDelivered)
        };
    }
}