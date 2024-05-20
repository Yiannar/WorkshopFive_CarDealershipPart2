package com.ps;

public  class SalesContract extends Contract{


    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
    }

    @Override
    public void getTotalPrice() {
        getVehicleSold().getPrice();
    }

    @Override
    public void getMonthlyPayment() {

    }


    // private double salesTaxAmount
    // private int Recording fee
    // Private double Processing fee
    // Boolean false Finance
    //


    // make a super class constructor


    // override getTotalPrice()
    // price + salesTax 5% + 100 (recording fee)
    // make an if statement
    // if(price < 10,000){
    // price + 295
    // } else if (price >= 10,000){
    // price + 495
    // }

    // override getMonthlyPayment()
    // make an if statement
    // if(financed == true && totalPrice >= 10,000){
    // 4.25% * 48
    // } else {
    // 5.25 * 24
    // } return 0

}
