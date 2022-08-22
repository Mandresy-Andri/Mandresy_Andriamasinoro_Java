package com.company;


public class AccountRecord {
    private int charge;
    private String chargeDate;

    //custom constructor
    public AccountRecord(int charge, String date){
        this.charge=charge;
        this.chargeDate=date;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public String getChargeDate() {
        return chargeDate;
    }

    public void setChargeDate(String chargeDate) {
        this.chargeDate = chargeDate;
    }
}
