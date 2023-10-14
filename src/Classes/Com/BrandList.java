/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Com;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
/**
 *
 * @author PC
 */

public class BrandList extends ArrayList<Brand>{
     // TODO: call the parent's constructor
    private Scanner input;
    public BrandList() {
        input = new Scanner(System.in);
    }
    public boolean loadFromFile(String filename) {
// TODO: 1. Create an instance of File, using the file name
        // TODO: 2. Check if the file existed (optional)
        // TODO: 3. Open the file for reading
        // TODO: 4. While reading:
        // TODO: 4.1. Handle the string from the file, convert to data
        // TODO: 4.2. Create a new brand from the data
        // TODO: 4.3. Add the brand to the list
        // TODO: 5. Close the file
        // TODO: 6. Return `true` if reading successfully, `false` if errors happened
        File file = new File(filename);

        if (!file.exists()) {
            System.out.println("File does not exist.");
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                
                String[] parts = line.split(",");

                if (parts.length >= 3) {
                    String id = parts[0].trim();
                    String brandName = parts[1].trim();
                    String soundBrandAndPrice = parts[2].trim();

                    String[] soundBrandAndPriceParts = soundBrandAndPrice.split(":");
                    if (soundBrandAndPriceParts.length == 2) {
                        String soundBrand = soundBrandAndPriceParts[0].trim();
                        double price = Double.parseDouble(soundBrandAndPriceParts[1].trim());
                        Brand brand = new Brand(id, brandName, soundBrand, price);
                        
                        this.add(brand);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return false;
        }

        return true;
    }

    public boolean saveToFile(String filename) {
        // TODO: Follow the same step as in `loadFromFile()` method
        // TODO: But open the file for writing instead of reading
         try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Brand brand : this) {
                writer.write(brand.getBrandID() + ", " + brand.getBrandName() + ", " + brand.getSoundBrand() + ": " + brand.getPrice());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
            return false;
        }
    }

    public int searchID(String id) {
        // TODO: Search a brand based on `brandID`.
        // TODO: Return the position if exists.
         for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getBrandID().equals(id)) {
                return i;
            }
        }
        return -1; // Return -1 if the brand ID is not found in the list
    }

    public Brand getUserChoice() {
        // TODO: Show a menu of brands, let user choose a brand from this menu
        // 💡 Tip: Use the `Menu` class
        return new Brand();
    }

    public void addBrand() {
        // TODO: Add a new brand based on inputted data
        // TODO: Make sure all data are validated before adding
         System.out.print("Enter the brandID: ");
        String brandID = input.nextLine();
        String brandName;
        String soundBrand;
        double price;
        
        int pos = searchID(brandID);
        if (pos >= 0) {
            System.err.println("Brand with this ID already exists in the list. Please choose a unique ID.");
            return;
        }
        
        do 
        {
            System.out.print("Enter the brand name: ");
            brandName = input.nextLine();
            brandName = brandName.replace("\n", "").replace("\r", "");
        } while (brandName.isEmpty());
        
        System.out.println("");
        
        do
        {
            System.out.print("Enter the sound brand: ");
            soundBrand = input.nextLine();
            soundBrand = soundBrand.replace("\n", "").replace("\r", "");
        } while (soundBrand.isEmpty());
        
        System.out.println("");
        
        do {
            System.out.print("Enter the price (greater than 0): ");
            price = input.nextDouble();
        } while (price <= 0);

        Brand newBrand = new Brand(brandID, brandName, soundBrand, price);

        this.add(newBrand);

        System.out.println("Brand added successfully!");
    }

    public void updateBrand() {
        // TODO: Sa as `addBrand()` but updating a brand instead
        // TODO: Make sure to let user input a brand ID first
         System.out.print("Enter the brandID you want to update: ");
        String brandID = input.nextLine();

        int pos = searchID(brandID);

        if (pos < 0) {
            System.err.println("Brand not found!");
        } else {
            String newBrandName;
            do 
            {
                System.out.print("Enter the brand name: ");
                newBrandName = input.nextLine();
                newBrandName = newBrandName.replace("\n", "").replace("\r", "");//remove enter symbol
            } while (newBrandName.isEmpty());
            
            System.out.println("");
            
            String newSoundBrand;
            do
            {
                System.out.print("Enter the sound brand: ");
                newSoundBrand = input.nextLine();
                newSoundBrand = newSoundBrand.replace("\n", "").replace("\r", "");
            } while (newSoundBrand.isEmpty());
            
            System.out.println("");
            
            double newPrice;
            do {
                System.out.print("Enter the new price (greater than 0): ");
                newPrice = input.nextDouble();
            } while (newPrice <= 0);

            Brand updatedBrand = this.get(pos);
            updatedBrand.setBrandName(newBrandName);
            updatedBrand.setSoundBrand(newSoundBrand);
            updatedBrand.setPrice(newPrice);

            System.out.println("Brand updated successfully!");
        }
    }

    public void listBrands() {
        for (int i = 0; i < this.size(); i++)
            System.out.println(this.get(i));
    }
}

