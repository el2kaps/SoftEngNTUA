package gr.ntua.ece.softeng2021.backend.data.model;

public class Bill {
    private String Name;
    private Float Cost;
   
    public Bill(){}


    public void setName(String name) {
        Name = name;
    }

    public void setCost(Float c) {
        Cost = c;
    }

    public String getName() {
        return this.Name;
    }

    public Float getCost() {
        return this.Cost;
    }
}