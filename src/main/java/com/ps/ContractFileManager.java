package com.ps;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {

    public static void saveContract(Contract contract) {
        try (BufferedWriter bufWriter = new BufferedWriter(new FileWriter("ContractFile.csv"))) {
            // Writing contract details
            bufWriter.write(String.format("%s|%s|%s|%d\n",
                    contract.getDate(),
                    contract.getCustomerName(),
                    contract.getCustomerEmail(),
                    contract.getVehicleSold().getVin()  // Using VIN to represent the vehicleSold
            ));

            // Writing vehicle details
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