/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Com;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author PC
 */
public class BrandList extends ArrayList<Brand> {
    ValidInput in = new ValidInput();
    public BrandList() {
    }

    public boolean loadFromFile(String filename) {
        File file = new File(filename);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Tách thông tin bằng cách dùng dấu phẩy làm ngăn cách
                String[] parts = line.split(", ");
                if (parts.length == 3) {
                    String id = parts[0].trim();
                    String brandName = parts[1].trim();
                    String soundBrandAndPrice = parts[2].trim();

                    String[] soundBrandAndPriceParts = soundBrandAndPrice.split(":");
                    if (soundBrandAndPriceParts.length == 2) {
                        String soundBrand = soundBrandAndPriceParts[0].trim();
                        String priceStr = soundBrandAndPriceParts[1].trim();
                        double price = Double.parseDouble(priceStr);
                        Brand brand = new Brand(id, brandName, soundBrand, price);
                        this.add(brand);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Brand brand : this) {
                writer.write(brand.toString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
            return false;
        }
        return true;
    }

    //Search a brand based on brand ID
    public int searchID(String bID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getBrandID().equals(bID)) {
                return i;
            }
        }
        return -1;
    }

    //Transform the list to a menu, the user will choose a 
    //brand from this menu.
    public Brand getUserChoice() {
        return new Menu<Brand>().ref_getChoice(this);
    }

    public void addBrand() {
        String id = in.getString("Enter brand ID: ");
        do {
            if (searchID(id) != -1 ) 
                System.out.println("ID is exist!");
        } while (searchID(id) != -1);

        String name = in.getString("Enter name: ");
        String sound = in.getString("Enter sound: ");
        double price =in.getDouble("Enter price: ");
        Brand brand = new Brand(id, name, sound, price);
        this.add(brand);
    }

    //Update brand_name, sound_brand, price of an existed brand.
    public void updateBrand() {
        String id = in.getString("Enter brand ID: ");
        int pos = searchID(id);
        if (pos < 0) {
            System.out.println("Not found!");
        } else {
            String name = in.getString("Enter name: ");
            this.get(pos).setBrandName(name);

            String sound = in.getString("Enter sound: ");
            this.get(pos).setSoundBrand(sound);

            double price = in.getDouble("Enter price: ");
            this.get(pos).setPrice(price);
        }
    }

    //display list of brands  
    public void listBrands() {
        int n = this.size();
        for (int i = 0; i < n; i++) {
            System.out.println(this.get(i));
        }
    }
}
