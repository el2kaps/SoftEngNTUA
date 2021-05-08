package gr.ntua.ece.softeng2021.data.model;

import java.util.Objects;

public class SessionsPerEV {

    private String VehicleID;
    private String RequestTimestamp;
    private String PeriodFrom;
    private String PeriodTo;
	  private double TotalEnergyConsumed;
	  private int NumberOfVisitedPoints;
    private int NumberOfVehicleChargingSessions;
    //vale lista REEE
    private int SessionIndex;
    private String SessionID;
  	private String EnergyProvider;
	  private String StartedOn;
	  private String FinishedOn;
    private double EnergyDelivered;
    private String PricePolicyRef;
	  private double CostPerKWh;
	  private double SessionCost;

    public SessionsPerEV() {

    }

    public SessionsPerEV(String VehicleID, String RequestTimestamp,String PeriodFrom,String PeriodTo, double TotalEnergyConsumed, int NumberOfVisitedPoints, int NumberOfVehicleChargingSessions, String SessionID, String EnergyProvider, String StartedOn, String FinishedOn, double EnergyDelivered, String PricePolicyRef, double CostPerKWh, double SessionCost) {
      this.VehicleID=VehicleID;
      this.RequestTimestamp = RequestTimestamp;
      this.PeriodFrom = PeriodFrom;
      this.PeriodTo = PeriodTo;
	  this.TotalEnergyConsumed = TotalEnergyConsumed;
	  this.NumberOfVisitedPoints = NumberOfVisitedPoints;
      this.NumberOfVehicleChargingSessions = NumberOfVehicleChargingSessions;
      //this.SessionIndex = SessionIndex;
      this.SessionID = SessionID;
	  this.EnergyProvider = EnergyProvider;
	  this.StartedOn = StartedOn;
	  this.FinishedOn = FinishedOn;
      this.EnergyDelivered = EnergyDelivered;
      this.PricePolicyRef = PricePolicyRef;
	  this.CostPerKWh = CostPerKWh;
	  this.SessionCost = SessionCost;
    }



    public String[] Stringify(){
		String tpp=String.format("%.5f",TotalEnergyConsumed);
		String pp=String.format("%.5f",EnergyDelivered);
		String cw=String.format("%.5f",CostPerKWh);
		String sc=String.format("%.5f",SessionCost);
		//System.out.println(tpp);
		//System.out.println(pp);
		//System.out.println(cw);
		//System.out.println(sc);
		return new String[]{VehicleID, RequestTimestamp,PeriodFrom,PeriodTo, tpp, String.valueOf(NumberOfVisitedPoints), String.valueOf(NumberOfVehicleChargingSessions),
                          SessionID, EnergyProvider, StartedOn,FinishedOn,pp,
                         PricePolicyRef, cw, sc};
    }

    public String getVehicleID(){
      return VehicleID;
    }

    public void setVehicleID(String VehicleID){
      this.VehicleID=VehicleID;
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

	public double getTotalEnergyConsumed(){
      return TotalEnergyConsumed;
    }

    public void setTotalEnergyConsumed(double a){
      this.TotalEnergyConsumed=a;
    }

	public int getNumberOfVisitedPoints() {
        return NumberOfVisitedPoints;
    }

    public void setNumberOfVisitedPoints(int NumberOfVisitedPoints) {
        this.NumberOfVisitedPoints = NumberOfVisitedPoints;
    }

    public int getNumberOfVehicleChargingSessions() {
        return NumberOfVehicleChargingSessions;
    }

    public void setNumberOfVehicleChargingSessions(int NumberOfVehicleChargingSessions) {
        this.NumberOfVehicleChargingSessions = NumberOfVehicleChargingSessions;
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

	public String getEnergyProvider() {
        return EnergyProvider;
    }

    public void setEnergyProvider(String EnergyProvider) {
        this.EnergyProvider = EnergyProvider;
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

    public double getEnergyDelivered(){
      return EnergyDelivered;
    }

    public void setEnergyDelivered(double a){
      this.EnergyDelivered=a;
    }

    public String getPricePolicyRef(){
      return PricePolicyRef;
    }

    public void setPricePolicyRef(String a){
      this.PricePolicyRef=a;
    }

	public double getCostPerKWh(){
      return CostPerKWh;
    }

    public void setCostPerKWh(double a){
      this.CostPerKWh=a;
    }

	public double getSessionCost(){
      return SessionCost;
    }

    public void setSessionCost(double a){
      this.SessionCost=a;
    }
}
