package com.ps;

public class SalesContract extends Contract {

    private double salesTax;
    private int processingFee;
    private int recordingFee = 100;
    private boolean financing;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, boolean financing) {
        super(date, customerName, customerEmail, vehicleSold);
        this.salesTax = vehicleSold.getPrice() * 0.05;
        if (vehicleSold.getPrice() < 10_000) {
            this.processingFee = 295;
        } else {
            this.processingFee = 495;
        }
        this.financing = financing;
    }

    @Override
    public double getTotalPrice() {
        Vehicle vehicle = getVehicleSold();
        double totalAmount = vehicle.getPrice() + getRecordingFee() + getProcessingFee() + getMonthlyPayment() + getSalesTax();
        return totalAmount;
    }

    @Override
    public double getMonthlyPayment() {
        if (isFinancing()) {
            Vehicle vehicle = getVehicleSold();
            double loanAmount = vehicle.getPrice();
            double monthlyInterestRate;
            int loanTermInMonths;

            if (vehicle.getPrice() > 10_000) {
                monthlyInterestRate = 0.0425 / 12;
                loanTermInMonths = 48;
            } else {
                monthlyInterestRate = 0.0525 / 12;
                loanTermInMonths = 24;
            }

            double exponent = Math.pow(1 + monthlyInterestRate, loanTermInMonths);
            double monthlyPayment = (loanAmount * monthlyInterestRate * exponent) / (exponent - 1);

            return monthlyPayment * loanTermInMonths;
        } else {
            return 0;
        }
    }

    public double getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }

    public int getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(int processingFee) {
        this.processingFee = processingFee;
    }

    public int getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(int recordingFee) {
        this.recordingFee = recordingFee;
    }

    public boolean isFinancing() {
        return financing;
    }

    public void setFinancing(boolean financing) {
        this.financing = financing;
    }
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
