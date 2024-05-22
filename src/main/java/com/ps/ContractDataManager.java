package com.ps;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractDataManager {

    public void saveContract(Contract contract) {

        try (BufferedWriter buffWriter = new BufferedWriter(new FileWriter("Contracts.csv", true))) {
            // Common contract details
            Vehicle vehicle = contract.getVehicleSold();

            String date = contract.getDate();
            String customerName = contract.getCustomerName();
            String customerEmail = contract.getCustomerEmail();
            int vehicleVin = vehicle.getVin();
            int vehicleYear = vehicle.getYear();
            String vehicleMake = vehicle.getMake();
            String vehicleModel = vehicle.getModel();
            String vehicleType = vehicle.getVehicleType();
            String vehicleColor = vehicle.getColor();
            int vehicleMileage = vehicle.getOdometer();
            double vehiclePrice = vehicle.getPrice();

            if (contract instanceof SalesContract) {
                SalesContract salesContract = (SalesContract) contract;

                double taxAmount = salesContract.getSalesTax();
                int recordingFee = salesContract.getRecordingFee();
                int processingFee = salesContract.getProcessingFee();
                double totalPrice = salesContract.getTotalPrice();
                boolean financing = salesContract.isFinancing();
                String financeChoice = financing ? "YES" : "NO";
                double monthlyPayment = salesContract.getMonthlyPayment();

                String soldVehicle = String.format("SALE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%d|%d|%.2f|%s|%.2f\n",
                        date, customerName, customerEmail, vehicleVin, vehicleYear,
                        vehicleMake, vehicleModel, vehicleType, vehicleColor,
                        vehicleMileage, vehiclePrice,
                        taxAmount, recordingFee, processingFee, totalPrice, financeChoice, monthlyPayment
                );
                buffWriter.write(soldVehicle);
            } else if (contract instanceof LeaseContract) {
                LeaseContract leaseContract = (LeaseContract) contract;

                double expectedValue = leaseContract.getExpectedEndingValue();
                double leaseFee = leaseContract.getLeaseFee();
                double totalPrice = leaseContract.getTotalPrice();
                double monthlyPayment = leaseContract.getMonthlyPayment();

                String leasedVehicle = String.format("LEASE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f\n",
                        date, customerName, customerEmail, vehicleVin, vehicleYear,
                        vehicleMake, vehicleModel, vehicleType, vehicleColor,
                        vehicleMileage, vehiclePrice,
                        expectedValue, leaseFee, totalPrice, monthlyPayment
                );
                buffWriter.write(leasedVehicle);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
