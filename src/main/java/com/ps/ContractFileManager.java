package com.ps;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {

    public static void saveContract(Contract contract) {

        try (BufferedWriter bufWriter = new BufferedWriter(new FileWriter("ContractFile.csv"))) {
            // Common contract details
            bufWriter.write(String.format("%s|%s|%s|%d\n",
                    contract.getDate(),
                    contract.getCustomerName(),
                    contract.getCustomerEmail(),
                    contract.getVehicleSold().getVin()  // Assuming vehicleSold is a Vehicle object
            ));

            // Write specific contract details based on the type of contract
            if (contract instanceof SalesContract) {
                SalesContract salesContract = (SalesContract) contract;
                bufWriter.write(String.format("Sales|%.2f|%.2f\n",
                        salesContract.getSalesPrice(),
                        salesContract.getSalesTax()
                ));
            } else if (contract instanceof LeaseContract) {
                LeaseContract leaseContract = (LeaseContract) contract;
                bufWriter.write(String.format("Lease|%d|%.2f|%.2f\n",
                        leaseContract.getLeaseTerm(),
                        leaseContract.getMonthlyPayment(),
                        leaseContract.getLeaseFee()
                ));
            }

            // Write vehicle details
            Vehicle vehicle = contract.getVehicleSold();
            bufWriter.write(String.format("%d|%d|%s|%s|%s|%s|%d|%.2f\n",
                    vehicle.getVin(),
                    vehicle.getYear(),
                    vehicle.getMake(),
                    vehicle.getModel(),
                    vehicle.getVehicleType(),
                    vehicle.getColor(),
                    vehicle.getOdometer(),
                    vehicle.getPrice()
            ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
