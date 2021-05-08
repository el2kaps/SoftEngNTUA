package gr.ntua.ece.softeng2021.data.model;

import java.util.Objects;

public class SessionsPerStation {

    private String StationID;
    private String Operator;
    private String RequestTimestamp;
    private String PeriodFrom;
    private String PeriodTo;
	  private double TotalEnergyDelivered;
    private int NumberOfChargingSessions;
    private int NumberOfActivePoints;
    private String PointID;
	  private int PointSessions;
	  private double EnergyDelivered;

    public SessionsPerStation() {

    }

    public SessionsPerStation(String StationID, String Operator, String RequestTimestamp,String PeriodFrom,String PeriodTo, double TotalEnergyDelivered, int NumberOfChargingSessions, int NumberOfActivePoints, String PointID, int PointSessions, double EnergyDelivered) {
      this.StationID=StationID;
      this.Operator=Operator;
      this.RequestTimestamp = RequestTimestamp;
      this.PeriodFrom = PeriodFrom;
      this.PeriodTo = PeriodTo;
	    this.TotalEnergyDelivered = TotalEnergyDelivered;
      this.NumberOfChargingSessions = NumberOfChargingSessions;
      this.NumberOfActivePoints = NumberOfActivePoints;
      this.PointID = PointID;
	    this.PointSessions = PointSessions;
	    this.EnergyDelivered = EnergyDelivered;
    }



    public String[] Stringify(){
		String tpp=String.format("%.5f",TotalEnergyDelivered);
		String pp=String.format("%.5f",EnergyDelivered);
		//System.out.println(tpp);
		//System.out.println(pp);
		return new String[]{StationID,Operator,RequestTimestamp,PeriodFrom,PeriodTo, tpp, String.valueOf(NumberOfChargingSessions),
                          String.valueOf(NumberOfActivePoints),PointID, String.valueOf(PointSessions),pp};
    }

    public String getStationID(){
      return StationID;
    }

    public void setStationID(String StationID){
      this.StationID=StationID;
    }

    public String getOperator(){
      return Operator;
    }

    public void setOperator(String Operator){
      this.Operator=Operator;
    }

    public String getRequestTimestamp() {
        return RequestTimestamp;
    }

    public void setRequestTimestamp(String RequestTimestamp) {
        this.RequestTimestamp = RequestTimestamp;
    }

    public String getPeriodFrom() {
        return PeriodFrom;
    }

    public void setPeriodFrom(String PeriodFrom) {
        this.PeriodFrom = PeriodFrom;
    }

    public String getPeriodTo() {
        return PeriodTo;
    }

    public void setPeriodTo(String PeriodTo) {
        this.PeriodTo = PeriodTo;
    }

	public double getTotalEnergyDelivered(){
      return TotalEnergyDelivered;
    }

    public void setTotalEnergyDelivered(double a){
      this.TotalEnergyDelivered=a;
    }

    public int getNumberOfChargingSessions() {
        return NumberOfChargingSessions;
    }

    public void setNumberOfChargingSessions(int NumberOfChargingSessions) {
        this.NumberOfChargingSessions = NumberOfChargingSessions;
    }

    public int getNumberOfActivePoints() {
        return NumberOfActivePoints;
    }

    public void setNumberOfActivePoints(int NumberOfActivePoints) {
        this.NumberOfActivePoints = NumberOfActivePoints;
    }

    public String getPointID(){
      return PointID;
    }

    public void setPointID(String a){
      this.PointID=a;
    }

	public int getPointSessions() {
        return PointSessions;
    }

    public void setPointSessions(int PointSessions) {
        this.PointSessions = PointSessions;
    }

	public double getEnergyDelivered(){
      return EnergyDelivered;
    }

    public void setEnergyDelivered(double a){
      this.TotalEnergyDelivered=a;
    }

}
