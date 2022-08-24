package com.company;

import java.util.*;
import java.util.stream.Collectors;

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

    //List of customers
    private static List<Customer> customerList = new ArrayList<>();
    //List that keeps track of the ids of customers already created
    private static List<Integer> idList = new ArrayList<>();

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
        //if list is empty or id is not yet used then add new customer
        if(customerList.isEmpty() || idList.isEmpty() || !idList.contains(id)) {
            customerList.add(new Customer(id, name, charge, date));
            idList.add(id);
        }
        //else it means customer already exist so just update its account record
        else {
            customerList.stream().filter(ct -> ct.getId()==id).forEach(ct ->ct.getCharges().add(new AccountRecord(charge,date)));
        }
    }

    //Method that streams through customer list and returns the positive accounts
    public static String positive(){
        return customerList.stream().filter(ct -> ct.getBalance()>=0).collect(Collectors.toList()).toString();
    }

    //Method that streams through customer list and returns the negative accounts
    public static String negative(){
        return customerList.stream().filter(ct -> ct.getBalance()<0).collect(Collectors.toList()).toString();
    }

    public static void main(String[] args) {
        //go through customer data and populate Customer and AccountRecord class
        parseData();

        //print list of positive and negative accounts
        System.out.println("Positive accounts: "+positive());
        System.out.println("Negative accounts: "+negative());
    }
}
