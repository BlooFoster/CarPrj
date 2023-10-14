/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Com;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
<<<<<<< HEAD
=======
import java.io.IOException;
>>>>>>> faf6dd768ba2d598e1315f4c40e2e1fbfb479766
/**
 *
 * @author PC
 */

public class BrandList extends ArrayList<Brand>{
<<<<<<< HEAD
    public BrandList(){}
    public boolean loadFromFile(String filename) throws FileNotFoundException{
        File f = new File(filename);
        if(!f.exists()) return false;
        else {
            try (Scanner input = new Scanner(f)) {
                while(input.hasNextLine()){
                    String line = input.nextLine();
                    String[] b = line.split(", ");
                    String[] c = b[2].split(": ");
                    Brand brand = new Brand(b[0], b[1], c[0], Double.parseDouble(c[1]));
                    this.add(brand);
=======
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
>>>>>>> faf6dd768ba2d598e1315f4c40e2e1fbfb479766
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean saveToFile(String filename) throws IOException {
         try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Brand brand : this) {
<<<<<<< HEAD
                writer.write(brand.toString());
=======
                writer.write(brand.getBrandID() + ", " + brand.getBrandName() + ", " + brand.getSoundBrand() + ": " + brand.getPrice());
>>>>>>> faf6dd768ba2d598e1315f4c40e2e1fbfb479766
                writer.newLine();
            }
            return true;
        }
    }

<<<<<<< HEAD
    public int searchID(String bID){
        for (int i = 0; i < this.size(); i++) {
            if(this.get(i).getBrandID().equals(bID)) return i;
        }
        return -1;
    }

    public Brand getUserChoice(){
        Menu mnu = new Menu<>();
        return (Brand)mnu.ref_getChoice(this);
    }

    public void addBrand(){
        Scanner input = new Scanner(System.in);
        String id = "";
        do {
            System.out.println("Enter a brand ID : ");
            id = input.nextLine();
            if(searchID(id) != -1) {
                System.out.println("ID is exist!");
            }
        } while (searchID(id) != -1);
        
        String name;
        do {
            System.out.println("Enter a brand name: ");
            name = input.nextLine();
            if(name.isEmpty()) {
                System.out.println("Brand name can not be blank!");
            }
        } while(name.isEmpty());

        String sound;
        do {
            System.out.println("Enter a brand sound: ");
            sound = input.nextLine();
            if(sound.isEmpty()) {
                System.out.println("Brand sound can not be blank!");
            }
        } while(sound.isEmpty());

        double price;
=======
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
        
>>>>>>> faf6dd768ba2d598e1315f4c40e2e1fbfb479766
        do {
            System.out.println("Enter a brand price: ");
            price = input.nextDouble();
            if(price < 0) {
                System.out.println("Brand price can not < 0");
            }
        } while(price < 0);

<<<<<<< HEAD
        Brand brand = new Brand(id, name, sound, price);
        this.add(brand);
=======
        Brand newBrand = new Brand(brandID, brandName, soundBrand, price);

        this.add(newBrand);

        System.out.println("Brand added successfully!");
>>>>>>> faf6dd768ba2d598e1315f4c40e2e1fbfb479766
    }

    public void updateBrand(){
        Scanner input = new Scanner(System.in);
        String id;
        System.out.println("Enter a brand ID: ");
        id = input.nextLine();
        int pos = searchID(id);
        if(pos < 0) {
            System.out.println("Not found!");
        } else {
<<<<<<< HEAD
            String name;
=======
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
>>>>>>> faf6dd768ba2d598e1315f4c40e2e1fbfb479766
            do {
                System.out.println("Enter new brand name: ");
                name = input.nextLine();
                if(name.isEmpty()) {
                    System.out.println("Brand name can not be blank!");
                }
            } while(name.isEmpty());
            this.get(pos).setBrandName(name);

<<<<<<< HEAD
            String sound;
            do {
                System.out.println("Enter new brand sound: ");
                sound = input.nextLine();
                if(sound.isEmpty()) {
                    System.out.println("Brand sound can not be blank!");
                }
            } while(sound.isEmpty());
            this.get(pos).setSoundBrand(sound);
=======
            Brand updatedBrand = this.get(pos);
            updatedBrand.setBrandName(newBrandName);
            updatedBrand.setSoundBrand(newSoundBrand);
            updatedBrand.setPrice(newPrice);
>>>>>>> faf6dd768ba2d598e1315f4c40e2e1fbfb479766

            double price;
            do {
                System.out.println("Enter new brand price: ");
                price = input.nextDouble();
                if(price < 0) {
                    System.out.println("Brand price can not < 0");
                }
            } while(price < 0);
            this.get(pos).setPrice(price);
        }
    }

<<<<<<< HEAD
    public void listBrands(){
        int n = this.size();
        for (int i = 0; i < n; i++) {
            System.out.println(this.get(i));
        }
=======
    public void listBrands() {
        for (int i = 0; i < this.size(); i++)
            System.out.println(this.get(i));
>>>>>>> faf6dd768ba2d598e1315f4c40e2e1fbfb479766
    }
}