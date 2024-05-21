package com.ps;

public abstract class  Contract {

    private String  date;
    private String  customerName;
    private String  customerEmail;
    private Vehicle vehicleSold;
    private double totalPrice;
    private double monthlyPayment;


    // constructor

    public Contract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }


    // getters and setters

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Vehicle getVehicleSold() {
        return this.vehicleSold;
    }

    public void setVehicleSold(Vehicle vehicleSold) {
        this.vehicleSold = vehicleSold;
    }


    //getTotalPrice()

  public abstract double getTotalPrice();


    //getMonthlyPayment()

    public abstract double getMonthlyPayment();

}
