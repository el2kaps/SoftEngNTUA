package gr.ntua.ece.softeng2021.data.model;

import java.util.Objects;

public class SessionsPerProvider {

    private String ProviderID;
    private String ProviderName;
    private String StationID;
    private String SessionID;
	  private String VehicleID;
	  private String StartedOn;
	  private String FinishedOn;
    private double EnergyDelivered;
    private String PricePolicyRef;
	  private double CostPerKWh;
	  private double TotalCost;

    public SessionsPerProvider() {}

    public SessionsPerProvider(String ProviderID, String ProviderName, String StationID, String SessionID, String VehicleID, String StartedOn, String FinishedOn, double EnergyDelivered, String PricePolicyRef, double CostPerKWh, double TotalCost) {
      this.ProviderID = ProviderID;
      this.ProviderName = ProviderName;
      this.StationID = StationID;
      this.SessionID = SessionID;
	    this.VehicleID = VehicleID;
	    this.StartedOn = StartedOn;
	    this.FinishedOn = FinishedOn;
      this.EnergyDelivered = EnergyDelivered;
      this.PricePolicyRef = PricePolicyRef;
	    this.CostPerKWh = CostPerKWh;
	    this.TotalCost = TotalCost;
    }



    public String[] Stringify(){
		String pp=String.format("%.5f",EnergyDelivered);
		String cw=String.format("%.5f",CostPerKWh);
		String sc=String.format("%.5f",TotalCost);
		//System.out.println(pp);
		//System.out.println(cw);
		//System.out.println(sc);
		return new String[]{ProviderID, ProviderName,StationID, SessionID, VehicleID, StartedOn,FinishedOn,pp,
                         PricePolicyRef, cw, sc};
    }

    public String getProviderID(){
      return ProviderID;
    }

    public void setProviderID(String ProviderID){
      this.ProviderID=ProviderID;
    }

    public String getProviderName() {
        return ProviderName;
    }

    public void setProviderName(String ProviderName) {
        this.ProviderName = ProviderName;
    }

    public String getStationID() {
        return StationID;
    }

    public void setStationID(String StationID) {
        this.StationID = StationID;
    }

    public String getSessionID(){
      return SessionID;
    }

    public void setSessionID(String a){
      this.SessionID=a;
    }

	public String getVehicleID() {
        return VehicleID;
    }

    public void setVehicleID(String VehicleID) {
        this.VehicleID = VehicleID;
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

	public double getTotalCost(){
      return TotalCost;
    }

    public void setTotalCost(double a){
      this.TotalCost=a;
    }
}
