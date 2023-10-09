/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Classes.Com.BrandList;
import Classes.Com.CarList;
import Classes.Com.Menu;

import java.util.*;

/**
 *
 * @author PC
 */
public class CarManager {

    public static void main(String[] args) {
        // TODO: 1. Load all the brands and cars from files
        // TODO: 1.1. If loading not successfully (either one of two files), print out a
        // message to the user and exit the program
        // TODO: 2. Print the menu of operations, and receive input from user
        // TODO: 3. Call the appropriate function according to the option from user

        Scanner in = new Scanner(System.in);
        int userOption;

        BrandList brandList = new BrandList();
        if (!brandList.loadFromFile("brands.txt")) {
            System.out.println("Cannot load brands from brands.txt. The program will be terminated.");
            in.close();
            System.exit(1);
        }
        System.out.println("Loading brands successfully!!");

        CarList carList = new CarList(brandList);
        if (!carList.loadFromFile("cars.txt")) {
            System.out.println("Cannot load cars from cars.txt. The program will be terminated.");
            in.close();
            System.exit(1);
        }
        System.out.println("Loading cars successfully!!");
        System.out.println("================================\n");

        do {
            userOption = showMenuAndGetOption();
            System.out.println("================================");
            switch (userOption) {
                case 1:
                    brandList.listBrands();
                    break;

                case 2:
                    brandList.addBrand();
                    break;

                case 3:
                    System.out.print("Enter brand ID: ");
                    int foundPos = brandList.searchID(in.nextLine());
                    if (foundPos < 0) {
                        System.out.println("Brand not found!");
                        break;
                    }

                    System.out.println(brandList.get(foundPos));
                    break;

                case 4:
                    brandList.updateBrand();
                    break;

                case 5:
                    brandList.saveToFile("brands.txt");
                    break;

                case 6:
                    carList.listCars();
                    break;

                case 7:
                    carList.printBasedBrandName();
                    break;

                case 8:
                    carList.addCar();
                    break;

                case 9:
                    carList.removeCar();
                    break;

                case 10:
                    carList.updateCar();
                    break;

                case 11:
                    carList.saveToFile("cars.txt");
                    break;

                case 12:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid option. Try again!!");
                    break;
            }
            System.out.println("================================");
            System.out.println();
        } while (userOption != 12);

        in.close();
    }

    public static int showMenuAndGetOption() {
        ArrayList<String> options = new ArrayList<>(Arrays.asList(
                "List all brands",
                "Add a new brand",
                "Search a brand based on its ID",
                "Update a brand",
                "Save brands to the file, named brands.txt",
                "List all cars in ascending order of brand names",
                "List cars based on a part of an input brand name",
                "Add a car",
                "Remove a car based on its ID",
                "Update a car based on its ID",
                "Save cars to file, named cars.txt",
                "Exit"));

        return new Menu<String>().int_getChoice(options);
    }
}
