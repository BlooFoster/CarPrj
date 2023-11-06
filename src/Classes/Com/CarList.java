/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class CarList extends ArrayList<Car> {

    BrandList brandList;
    ValidInput in= new ValidInput();
    Scanner input = new Scanner(System.in);

    // Constructor
    public CarList(BrandList bList) {
        brandList = bList;
    }

    // Load car data from a file
    public boolean loadFromFile(String filename) {
        File f = new File(filename);
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    String id = parts[0].trim();
                    int pos = brandList.searchID(parts[1].trim());
                    if (pos >= 0) {
                        Brand brand = brandList.get(pos);
                        String color = parts[2].trim();
                        String frameID = parts[3].trim();
                        String engineID = parts[4].trim();
                        Car car = new Car(id, brand, color, frameID, engineID);
                        this.add(car);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            //System.out.println("Error reading the file: " + e.getMessage());
            return false;
        }

        return true;
    }

    // Save the car data to a file.
    public boolean saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Car car : this) {
                writer.write(car.toString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            return false;
        }
        return true;
    }

    // Search for a car by its ID.
    public int searchID(String carID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getCarID().equals(carID)) {
                return i;
            }
        }
        return -1;
    }

    // Search for a car by its frame ID.
    public int searchFrame(String frameID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getFrameID().equals(frameID)) {
                return i;
            }
        }
        return -1;
    }

    // Search for a car by its engine ID.
    public int searchEngine(String engineID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getEngineID().equals(engineID)) {
                return i;
            }
        }
        return -1;
    }

    // Add a new car 
    // Input and validation logic for adding a car.
    public void addCar() {
        String id;
        do {
            id = in.getString("Enter a car ID: ");
            if (searchID(id) != -1) {
                System.out.println("Car ID exist in list!");
            }
        } while (searchID(id) != -1);

        Menu menu = new Menu();
        Brand brand = (Brand) menu.ref_getChoice(brandList);

        String color = in.getString("Enter color: ");

        String frame;
        String pattern = "F\\d{5}";
        do {
            System.out.println("Enter frameID: ");
            frame = input.nextLine().trim();
            if (searchFrame(frame) != -1) {
                System.out.println("FrameID exist in list!");
            }
            if (!frame.matches(pattern)) {
                System.out.println("FrameID must be \"F00000\" format");
            }
        } while (searchFrame(frame) != -1 || !frame.matches(pattern));

        String engine;
        String pattern1 = "E\\d{5}";
        do {
            System.out.println("Enter engineID: ");
            engine = input.nextLine().trim();
            if (searchEngine(engine) != -1) {
                System.out.println("EngineID exist in list!");
            }
            if (!engine.matches(pattern1)) {
                System.out.println("EngineID must be \"E00000\" format");
            }
        } while (searchEngine(engine) != -1 || !engine.matches(pattern1));

        Car car = new Car(id, brand, color, frame, engine);
        this.add(car);
    }

    // Print cars based on a part of the brand name
    public void printBasedBrandName() {
        int count = 0;
        String partOfBrandName;
        partOfBrandName = in.getString("Enter a part of brand name: ");
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getBrand().getBrandName().contains(partOfBrandName)) {
                System.out.println(this.get(i).screenString());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No car is detected!");
        }
    }

    // Remove a car from the CarList.
    public boolean removeCar() {
        String removedID = in.getString("Enter removed ID: ");
        int pos = searchID(removedID);
        if (pos < 0) {
            System.err.println("Not found!");
            return false;
        } else {
            this.remove(pos);
            System.out.println("Removed");
        }
        return true;
    }

    // Update car details in the CarList.
    public boolean updateCar() {
        String id = in.getString("Enter ID to update: ");
        int pos = searchID(id);
        if (pos < 0) {
            System.out.println("Not found!");
            return false;
        } else {
            Brand brand = brandList.getUserChoice();
            String color = in.getString("Enter color: ");

            String frame;
            String pattern = "F\\d{5}";
            do {
                frame = in.getString("Enter frameID: ");
                if (searchFrame(frame) != -1) {
                    System.out.println("FrameID exist in list!");
                }
                if (!frame.matches(pattern)) {
                    System.out.println("FrameID must be \"F00000\" format");
                }
            } while (searchFrame(frame) != -1 || !frame.matches(pattern));

            String engine;
            String pattern1 = "E\\d{5}";
            do {
                engine = in.getString("Enter engineID: ");
                if (searchEngine(engine) != -1) {
                    System.out.println("EngineID exist in list!");
                }
                if (!engine.matches(pattern1)) {
                    System.out.println("EngineID must be \"E00000\" format");
                }
            } while (searchEngine(engine) != -1 || !engine.matches(pattern1));

            this.get(pos).setBrand(brand);
            this.get(pos).setColor(color);
            this.get(pos).setFrameID(frame);
            this.get(pos).setEngineID(engine);
        }
        return true;
    }

    // List cars sorted by brand name and display them.
    public void listCars() {
        Collections.sort(this);
        for (Car car : this) {
            System.out.println(car.screenString());
        }
    }

}
