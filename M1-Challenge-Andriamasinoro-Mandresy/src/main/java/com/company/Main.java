package com.company;

import java.util.*;

public class Main {

    private static List<String[]> customerData = Arrays.asList(
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","-7500","01-10-2022"},
            new String[] {"1","Wayne Enterprises","18000","12-22-2021"},
            new String[] {"3","Ace Chemical","-48000","01-10-2022"},
            new String[] {"3","Ace Chemical","-95000","12-15-2021"},
            new String[] {"1","Wayne Enterprises","175000","01-01-2022"},
            new String[] {"1","Wayne Enterprises","-35000","12-09-2021"},
            new String[] {"1","Wayne Enterprises","-64000","01-17-2022"},
            new String[] {"3","Ace Chemical","70000","12-29-2022"},
            new String[] {"2","Daily Planet","56000","12-13-2022"},
            new String[] {"2","Daily Planet","-33000","01-07-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","33000","01-17-2022"},
            new String[] {"3","Ace Chemical","140000","01-25-2022"},
            new String[] {"2","Daily Planet","5000","12-12-2022"},
            new String[] {"3","Ace Chemical","-82000","01-03-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"}
    );

    //Hashmap that uses customer id as key
    private static Map<Integer, Customer> customerList = new HashMap<>();
    //List that contains customers with positive balance
    private static List<Customer> positive = new ArrayList<>();
    //List that contains customers with negative balance
    private static List<Customer> negative = new ArrayList<>();


    //Method that parses each array's data
    public static void parseData(){
        //go through each array to get info
        for(String[] customer: customerData){
            int id= Integer.parseInt(customer[0]);
            String name= customer[1];
            int charge= Integer.parseInt(customer[2]);
            String date= customer[3];

            //pass the info to updateCustomer
            updateCustomer(id,name,charge,date);
        }
    }

    //Method that updates or creates customer based on info
    public static void updateCustomer(int id,String name,int charge,String date){
        //if map is empty or does not have this id then add new customer
        if(customerList.isEmpty() || !customerList.containsKey(id))
            customerList.put(id,new Customer(id, name,charge,date));
        //else map already contains this customer data so just add new account record to it
        else {
            customerList.get(id).getCharges().add(new AccountRecord(charge,date));
        }
    }

    //Method that sends customers with positive balance in "positive" list and the rest in "negative" list
    public static void sortCustomerBalance(){
        customerList.forEach((k,v)-> {if(v.getBalance()>0) positive.add(v); else negative.add(v);});
    }

    public static void main(String[] args) {
        //go through customer data and populate Customer and AccountRecord class
        parseData();
        sortCustomerBalance();

        //print list of positive and negative accounts
        System.out.println("Positive accounts: "+positive);
        System.out.println("Negative accounts: "+negative);
    }
}
