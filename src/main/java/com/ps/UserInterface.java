package com.ps;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private static Scanner scanner = new Scanner(System.in);

    public void display(){
        init();

        int mainMenuCommand;

        do{
            // Needs refactoring
            System.out.println("1- Find vehicles within a price range");
            System.out.println("2- Find vehicles by make / model");
            System.out.println("3- Find vehicles by year range");
            System.out.println("4- Find vehicles by color");
            System.out.println("5- Find vehicles by mileage range");
            System.out.println("6- Find vehicles by type (sedan, truck, SUV, van)");
            System.out.println("7- List ALL vehicles");
            System.out.println("8- Add a vehicle");
            System.out.println("9- Remove a vehicle");
            System.out.println("10- Create a contract");
            System.out.println("99- Quit");

            System.out.print("Please choose an option: ");

            mainMenuCommand = scanner.nextInt();

            switch (mainMenuCommand){
                case 1:
                    processGetByPriceRequest();
                    break;
                case 2:
                    processGetByMakeModelRequest();
                    break;
                case 3:
                    processGetByYearRequest();
                    break;
                case 4:
                    processGetByColorRequest();
                    break;
                case 5:
                    processGetByMileageRequest();
                    break;
                case 6:
                    processGetByVehicleTypeRequest();
                    break;
                case 7:
                    processGetAllVehiclesRequest();
                    break;
                case 8:
                    processAddVehicleRequest();
                    break;
                case 9:
                    processRemoveVehicleRequest();
                    break;
                case 10:
                    processCreateContractRequest();
                    break;
                case 99:
                    break;
            }
        } while(mainMenuCommand != 99);
    }

    private void init(){
        this.dealership = DealershipFileManager.getDealership();
    }

    private void displayVehicles(List<Vehicle> vehicles){
        for(Vehicle vehicle: vehicles){
            System.out.printf("VIN: %d, Year: %d, Make: %s, Model: %s, Type: %s, Color: %s, Odometer: %d, Price: %.2f\n",
                    vehicle.getVin(),
                    vehicle.getYear(),
                    vehicle.getMake(),
                    vehicle.getModel(),
                    vehicle.getVehicleType(),
                    vehicle.getColor(),
                    vehicle.getOdometer(),
                    vehicle.getPrice()
            );
        }
    }

    public void processGetByPriceRequest(){
        System.out.print("Minimum price: ");
        int min = scanner.nextInt();
        System.out.print("Maximum price: ");
        int max = scanner.nextInt();

        List<Vehicle> filteredVehicles = dealership.getVehiclesByPrice(min, max);
        displayVehicles(filteredVehicles);
    }

    public void processGetByMakeModelRequest(){
        scanner.nextLine(); // Consume Line

        System.out.print("Make: ");
        String make = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();

        List<Vehicle> filteredVehicles = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(filteredVehicles);
    }

    public void processGetByYearRequest(){
        System.out.print("Minimum year: ");
        int min = scanner.nextInt();
        System.out.print("Maximum year: ");
        int max = scanner.nextInt();

        List<Vehicle> filteredVehicles = dealership.getVehicleByYear(min, max);
        displayVehicles(filteredVehicles);
    }

    public void processGetByColorRequest(){
        scanner.nextLine(); // Consume Line

        System.out.print("Color: ");
        String color = scanner.nextLine();

        List<Vehicle> filteredVehicles = dealership.getVehicleByColor(color);
        displayVehicles(filteredVehicles);
    }

    public void processGetByMileageRequest(){
        System.out.print("Minimum Mileage: ");
        int min = scanner.nextInt();
        System.out.print("Maximum Mileage: ");
        int max = scanner.nextInt();

        List<Vehicle> filteredVehicles = dealership.getVehiclesByMileage(min, max);
        displayVehicles(filteredVehicles);
    }

    public void processGetByVehicleTypeRequest(){
        scanner.nextLine(); // Consume Line

        System.out.print("Vehicle type: ");
        String type = scanner.nextLine();

        List<Vehicle> filteredVehicles = dealership.getVehiclesByType(type);
        displayVehicles(filteredVehicles);
    }

    public void processGetAllVehiclesRequest(){
        List<Vehicle> allVehicles = dealership.getAllVehicles();
        System.out.println("------ All Vehicles ------");
        displayVehicles(allVehicles);
    }

    public void processAddVehicleRequest(){
        System.out.print("VIN: ");
        int vin = scanner.nextInt();

        System.out.print("Year: ");
        int year = scanner.nextInt();

        // Fix: Consume extra carriage
        scanner.nextLine();

        System.out.print("Make: ");
        String make = scanner.nextLine();

        System.out.print("Model: ");
        String model = scanner.nextLine();

        System.out.print("Type: ");
        String type = scanner.nextLine();

        System.out.print("Color: ");
        String color = scanner.nextLine();

        System.out.print("Odometer: ");
        int odometer = scanner.nextInt();

        System.out.print("Price: ");
        double price = scanner.nextDouble();

        Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
        this.dealership.addVehicle(vehicle);

        DealershipFileManager.saveDealership(this.dealership);
    }

    public void processRemoveVehicleRequest(){
        List<Vehicle> allVehicles = this.dealership.getAllVehicles();
        displayVehicles(allVehicles);
        System.out.print("Which would you like to remove? VIN: ");
        int vin = scanner.nextInt();

        for(Vehicle vehicle: allVehicles){
            if(vehicle.getVin() == vin){
                dealership.removeVehicle(vehicle);
                System.out.println("Vehicle found and removed");
                DealershipFileManager.saveDealership(this.dealership);
                return;
            }
        }

        System.out.println("Vehicle not found");
    }

    public void processCreateContractRequest() {
        displayVehicles(dealership.getAllVehicles());
        ContractDataManager contractDataManager = new ContractDataManager();
        LocalDate dateNow = LocalDate.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = dateNow.format(dateFormat);

        System.out.println("\nSale or Lease Vehicle");

        // Get customer's first name
        System.out.println("* Please enter the customer's first name:");
        String firstName;
        while (true) {
            firstName = scanner.next().trim();
            if (firstName.isEmpty()) {
                System.out.println("* Please enter a first name.");
            } else {
                break;
            }
        }
        String capFirstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);

        // Get customer's last name
        System.out.println("* Please enter the customer's last name:");
        String lastName;
        while (true) {
            lastName = scanner.next().trim();
            if (lastName.isEmpty()) {
                System.out.println("* Please enter a last name.");
            } else {
                break;
            }
        }
        String capLastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
        String fullName = capFirstName + " " + capLastName;

        // Get customer's email address
        System.out.println("* Please enter the customer's email address:");
        String emailAddress;
        while (true) {
            emailAddress = scanner.next().trim();
            if (emailAddress.isEmpty()) {
                System.out.println("* Please enter an email address.");
            } else {
                break;
            }
        }

        scanner.nextLine(); // Consume extra newline character

        // Get vehicle VIN
        System.out.println("* Please enter the VIN of the vehicle you would like to sell/lease:");
        int vehicleVin;
        while (true) {
            if (scanner.hasNextInt()) {
                vehicleVin = scanner.nextInt();
                break;
            } else {
                System.out.println("Please enter a valid VIN number.");
                scanner.next();
            }
        }
        scanner.nextLine(); // Consume extra newline character
        Vehicle vehicleFound = dealership.getVehiclesByVin(vehicleVin);
        if (vehicleFound == null) {
            System.out.println("Vehicle not found. Please enter a valid VIN.");
            return;
        }

        // Get contract type (sell or lease)
        String sellLeaseChoice;
        while (true) {
            System.out.println("* Would you like to sell or lease the vehicle? (sell/lease)");
            sellLeaseChoice = scanner.next().toLowerCase().trim();
            if (!sellLeaseChoice.equals("sell") && !sellLeaseChoice.equals("lease")) {
                System.out.println("* Please enter a valid choice (sell or lease).");
            } else {
                break;
            }
        }

        // Create and save the contract
        switch (sellLeaseChoice) {
            case "sell":
                System.out.println("* Would the customer like to finance their vehicle? (yes/no)");
                boolean isFinancing = false;
                while (true) {
                    String financingChoice = scanner.next().toLowerCase().trim();
                    if (financingChoice.equals("yes")) {
                        isFinancing = true;
                        break;
                    } else if (financingChoice.equals("no")) {
                        break;
                    } else {
                        System.out.println("Please enter 'yes' or 'no'.");
                    }
                }

                SalesContract salesContract = new SalesContract(formattedDate, fullName, emailAddress, vehicleFound, isFinancing);
                contractDataManager.saveContract(salesContract);
                dealership.removeVehicle(vehicleFound);
                DealershipFileManager.saveDealership(dealership);
                System.out.println("\n Sales Contract has successfully been saved! ");
                break;

            case "lease":
                LeaseContract leaseContract = new LeaseContract(formattedDate, fullName, emailAddress, vehicleFound);
                contractDataManager.saveContract(leaseContract);
                dealership.removeVehicle(vehicleFound);
                DealershipFileManager.saveDealership(dealership);
                System.out.println("\n Lease Contract has successfully been saved! ");
                break;

            default:

        }
    }
}
