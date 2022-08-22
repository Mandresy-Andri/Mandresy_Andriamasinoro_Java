package com.company;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int id;
    private String name;
    private List<AccountRecord> charges = new ArrayList<>();

    //custom constructor
    public Customer(int id, String name,int charge, String date){
        this.id=id;
        this.name=name;
        charges.add(new AccountRecord(charge,date));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        //go through each account record to get total balance
        int balance=0;
        if(!charges.isEmpty())
            for(AccountRecord record: charges)
                balance+=record.getCharge();

        return balance;
    }

    public List<AccountRecord> getCharges() {
        return charges;
    }

    @Override
    public String toString() {
        //returns id, name, and balance
        return "id: "+id+", name: "+name+", balance: "+getBalance();
    }
}
