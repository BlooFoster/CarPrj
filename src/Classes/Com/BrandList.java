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
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class BrandList extends ArrayList<Brand> {

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
                        String priceStr = soundBrandAndPriceParts[1].replaceAll(",", ".").trim();
                        double price = Double.parseDouble(priceStr);
                        Brand brand = new Brand(id, brandName, soundBrand, price);
                        this.add(brand);
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
        Scanner input = new Scanner(System.in);
        String id = "";
        do {
            System.out.println("Enter a brand ID : ");
            id = input.nextLine();
            if (searchID(id) != -1) {
                System.out.println("ID is exist!");
            }
        } while (searchID(id) != -1);

        String name;
        do {
            System.out.println("Enter a brand name: ");
            name = input.nextLine();
            if (name.isEmpty()) {
                System.out.println("Brand name can not be blank!");
            }
        } while (name.isEmpty());

        String sound;
        do {
            System.out.println("Enter a brand sound: ");
            sound = input.nextLine();
            if (sound.isEmpty()) {
                System.out.println("Brand sound can not be blank!");
            }
        } while (sound.isEmpty());

        double price = 0;
            boolean validPrice = false;
            do {
                try {
                    System.out.println("Enter brand's price: ");
                    price = Double.parseDouble(input.nextLine());
                    if (price < 0) {
                        throw new Exception("Must be non-negative value");
                    }
                    validPrice = true; // Input is a valid number and non-negative.
                } catch (NumberFormatException e) {
                    System.out.println("Must be a valid number");
                } catch (Exception e1) {
                    System.out.println(e1.getMessage());
                }
            } while (!validPrice);

        Brand brand = new Brand(id, name, sound, price);
        this.add(brand);
    }

    //Update brand_name, sound_brand, price of an existed brand.
    public void updateBrand() {
        Scanner input = new Scanner(System.in);
        String id;
        System.out.println("Enter a brand ID: ");
        id = input.nextLine();
        int pos = searchID(id);
        if (pos < 0) {
            System.out.println("Not found!");
        } else {
            String name;
            do {
                System.out.println("Enter new brand name: ");
                name = input.nextLine();
                if (name.isEmpty()) {
                    System.out.println("Brand name can not be blank!");
                }
            } while (name.isEmpty());
            this.get(pos).setBrandName(name);

            String sound;
            do {
                System.out.println("Enter new brand sound: ");
                sound = input.nextLine();
                if (sound.isEmpty()) {
                    System.out.println("Brand sound can not be blank!");
                }
            } while (sound.isEmpty());
            this.get(pos).setSoundBrand(sound);

            double price = 0;
            boolean validPrice = false;
            do {
                try {
                    System.out.println("Enter brand's price: ");
                    price = Double.parseDouble(input.nextLine());
                    if (price < 0) {
                        throw new Exception("Must be non-negative value");
                    }
                    validPrice = true; // Input is a valid number and non-negative.
                } catch (NumberFormatException e) {
                    System.out.println("Must be a valid number");
                } catch (Exception e1) {
                    System.out.println(e1.getMessage());
                }
            } while (!validPrice);
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
