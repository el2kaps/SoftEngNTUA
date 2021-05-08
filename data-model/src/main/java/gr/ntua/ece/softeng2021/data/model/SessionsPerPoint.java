package gr.ntua.ece.softeng2021.data.model;

import java.util.Objects;

public class SessionsPerPoint {

    private String Point;
    private String PointOperator;
    private String RequestTimestamp;
    private String PeriodFrom;
    private String PeriodTo;
    private int NumberOfChargingSessions;
    //KANE THN LISTA RE
    private int SessionIndex;
    private String SessionID;
	  private String StartedOn;
	  private String FinishedOn;
   	private String Protocol;
    private double EnergyDelivered;
    private String Payment;
	  private String VehicleType;

    public SessionsPerPoint() {}

    public SessionsPerPoint(String Point, String PointOperator, String RequestTimestamp,String PeriodFrom,String PeriodTo, int NumberOfChargingSessions, String SessionID, String StartedOn, String FinishedOn, String Protocol, double EnergyDelivered, String Payment, String VehicleType) {
      this.Point=Point;
      this.PointOperator=PointOperator;
      this.RequestTimestamp = RequestTimestamp;
      this.PeriodFrom = PeriodFrom;
      this.PeriodTo = PeriodTo;
      this.NumberOfChargingSessions = NumberOfChargingSessions;
      //this.SessionIndex = SessionIndex;
      this.SessionID = SessionID;
	    this.StartedOn = StartedOn;
	    this.FinishedOn = FinishedOn;
	    this.Protocol = Protocol;
      this.EnergyDelivered = EnergyDelivered;
      this.Payment = Payment;
	    this.VehicleType = VehicleType;
    }



    public String[] Stringify(){
      String pp=String.format("%.5f",EnergyDelivered);
      //System.out.println(pp);
      return new String[]{Point,PointOperator,RequestTimestamp,PeriodFrom,PeriodTo,String.valueOf(NumberOfChargingSessions),
                          SessionID,StartedOn,FinishedOn,Protocol,pp,
                         Payment, VehicleType};
    }

    public String getPoint(){
      return Point;
    }

    public void setPoint(String Point){
      this.Point=Point;
    }

    public String getPointOperator(){
      return PointOperator;
    }

    public void setPointOperator(String PointOperator){
      this.PointOperator=PointOperator;
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

    public int getNumberOfChargingSessions() {
        return NumberOfChargingSessions;
    }

    public void setNumberOfChargingSessions(int NumberOfChargingSessions) {
        this.NumberOfChargingSessions = NumberOfChargingSessions;
    }

    public int getSessionIndex() {
        return SessionIndex;
    }

    public void setSessionIndex(int SessionIndex) {
        this.SessionIndex = SessionIndex;
    }

    public String getSessionID(){
      return SessionID;
    }

    public void setSessionID(String a){
      this.SessionID=a;
    }

	 public String getStartedOn() {
        return StartedOn;
    }

    public void setStartedOn(String StartedOn) {
        this.StartedOn = StartedOn;
    }
	 public String getFinishedOn() {
        return FinishedOn;
    }

    public void setFinishedOn(String FinishedOn) {
        this.FinishedOn = FinishedOn;
    }
	 public String getProtocol() {
        return Protocol;
    }

    public void setProtocol(String Protocol) {
        this.Protocol = Protocol;
    }

    public double getEnergyDelivered(){
      return EnergyDelivered;
    }

    public void setEnergyDelivered(double a){
      this.EnergyDelivered=a;
    }

    public String getPayment(){
      return Payment;
    }

    public void setPayment(String a){
      this.Payment=a;
    }

	public String getVehicleType(){
      return VehicleType;
    }

    public void setVehicleType(String a){
      this.VehicleType=a;
    }
}
