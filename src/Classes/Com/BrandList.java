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
/**
 *
 * @author PC
 */

public class BrandList extends ArrayList<Brand>{
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
                writer.write(brand.toString());
                writer.newLine();
            }
            return true;
        }
    }

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
        do {
            System.out.println("Enter a brand price: ");
            price = input.nextDouble();
            if(price < 0) {
                System.out.println("Brand price can not < 0");
            }
        } while(price < 0);

        Brand brand = new Brand(id, name, sound, price);
        this.add(brand);
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
            String name;
            do {
                System.out.println("Enter new brand name: ");
                name = input.nextLine();
                if(name.isEmpty()) {
                    System.out.println("Brand name can not be blank!");
                }
            } while(name.isEmpty());
            this.get(pos).setBrandName(name);

            String sound;
            do {
                System.out.println("Enter new brand sound: ");
                sound = input.nextLine();
                if(sound.isEmpty()) {
                    System.out.println("Brand sound can not be blank!");
                }
            } while(sound.isEmpty());
            this.get(pos).setSoundBrand(sound);

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

    public void listBrands(){
        int n = this.size();
        for (int i = 0; i < n; i++) {
            System.out.println(this.get(i));
        }
    }
}