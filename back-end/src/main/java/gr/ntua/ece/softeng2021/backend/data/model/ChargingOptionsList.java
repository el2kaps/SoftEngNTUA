package gr.ntua.ece.softeng2021.backend.data.model;

import java.util.List;

public class ChargingOptionsList {
    private String brandName;
    private String PricePolicyRef;

    public ChargingOptionsList(){}


    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

   
    public void setPricePolicyRef(String pprf) {
        PricePolicyRef = pprf;
    }

    public String getbrandName() {
        return brandName;
    }

    public String getPricePolicyRef() {
        return PricePolicyRef;
    }

   
    public String[] toString2(){
        return new String[]{
                this.brandName,
                this.PricePolicyRef
        };
    }
}
