package com.ps;

public class LeaseContract extends Contract{


    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
    }

    @Override
    public void getTotalPrice() {

    }

    @Override
    public void getMonthlyPayment() {

    }

    // Private int LeaseFee;

    // get superclass constructor
    // need original price

    //override getTotalPrice()
    // 7% * original price

    // override getMonthlyPayment()
    // 4.0% * 36
}
