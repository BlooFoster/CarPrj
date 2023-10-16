/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Com;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class CarList extends ArrayList<Car>{
    BrandList brandList;
    
    public CarList(BrandList bList){
        brandList = bList;
    }

    public boolean loadFromFile(String filename) throws FileNotFoundException {
        File f = new File(filename);
        if(!f.exists()) return false;
        else {
            try (Scanner input = new Scanner(f)) {
                while(input.hasNextLine()){
                    String line = input.nextLine();
                    String[] b = line.split(",");
                    int pos = brandList.searchID(b[1].trim());
                    Brand brand = brandList.get(pos);
                    Car car = new Car(b[0].trim(), brand, b[2].trim(), b[3].trim(), b[4].trim());
                    this.add(car);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean saveToFile(String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (Car car : this) {
                writer.write(car.toString());
                writer.newLine();
            }
            writer.close();
            return true;
    }

    public int searchID(String carID){
        for (int i = 0; i < this.size(); i++) {
            if(this.get(i).getCarID().equals(carID)) return i;
        }
        return -1;
    }

    public int searchFrame(String frameID){
        for (int i = 0; i < this.size(); i++) {
            if(this.get(i).getCarID().equals(frameID)) return i;
        }
        return -1;
    }

    public int searchEngine(String engineID){
        for (int i = 0; i < this.size(); i++) {
            if(this.get(i).getCarID().equals(engineID)) return i;
        }
        return -1;
    }

    public void addCar(){
        Scanner sc = new Scanner(System.in);
        
        String carID;
        do{
            sc = new Scanner(System.in);
            System.out.print("Enter car ID: ");
            carID = sc.nextLine();
            if ((this.searchID(carID)==-1)&&(!carID.isEmpty())) break;
            System.out.println("Car ID is empty or already exists. Please try again.");
        } while(true);
        
        Menu menu = new Menu();
        Brand brand = (Brand)menu.ref_getChoice(brandList);

        String color;
        do{
            System.out.print("Enter color of the car: ");
            sc = new Scanner(System.in);
            color = sc.nextLine();
            if (!color.isEmpty()) break;
            System.out.println("Color cannot be blank.");
        } while (true);

        String frameID;
        do{
            Pattern pattern = Pattern.compile("^F\\d{4}$");
            System.out.print("Enter frameID (F0000 Format): ");
            sc = new Scanner(System.in);
            frameID = sc.nextLine();
            if ((pattern.matcher(frameID).matches())&&(this.searchFrame(frameID)==-1)) break;
            System.out.println("FrameID is not in right format or it's already exists. Please try again.");
        }while(true);

        String engineID;
        do{
            Pattern pattern = Pattern.compile("^E\\d{4}$");
            System.out.print("Enter engineID (E0000 Format): ");
            sc = new Scanner(System.in);
            engineID = sc.nextLine();
            if ((pattern.matcher(engineID).matches())&&(this.searchEngine(engineID)==-1)) break;
            System.out.println("EngineID is not in right format or it's already exists. Please try again.");
        }while(true);

        Car car = new Car(carID, brand, color, frameID, engineID);
        this.add(car);
    }

    public void printBasedBrandName(){
        Scanner input = new Scanner(System.in);
        int count = 0;
        String aPartOfBrandName;
        System.out.println("Enter a part of brand name: ");
        aPartOfBrandName = input.nextLine().trim();
        for (int i = 0; i < this.size(); i++) {
            if(this.get(i).getBrand().getBrandName().contains(aPartOfBrandName)) {
                System.out.println(this.get(i).screenString());
                count++;
            }
        }
        if(count == 0) System.out.println("No car is detected!");
    }

    public boolean removeCar(){
        Scanner input = new Scanner(System.in);
        String removedID;
        System.out.println("Enter removed ID: ");
        removedID = input.nextLine().trim();
        int pos = searchID(removedID);
        if(pos < 0) {
            System.out.println("Not found!");
            return false;
        }
        else {
            this.remove(pos);
        }
        return true;
    }

    public boolean updateCar(){
        Scanner input = new Scanner(System.in);
        String id;
        System.out.println("Enter ID to update: ");
        id = input.nextLine().trim();
        int pos = searchID(id);
        if(pos < 0) {
            System.out.println("Not found!");
            return false;
        }
        else {
            Menu menu = new Menu();
            Brand brand = (Brand)menu.ref_getChoice(brandList);

            String color;
            do {
                System.out.println("Enter color: ");
                color = input.nextLine().trim();
                if(color.isEmpty()) System.out.println("Color can not be blank!");
            } while(color.isEmpty());

            String frame;
            String pattern = "F\\d{5}";
            do {
                System.out.println("Enter frameID: ");
                frame = input.nextLine().trim();
                if(searchFrame(frame) != -1) System.out.println("FrameID exist in list!");
                if(!frame.matches(pattern)) System.out.println("FrameID must be \"F00000\" format");
            } while(searchFrame(frame) != -1 || !frame.matches(pattern));

            String engine;
            String pattern1 = "E\\d{5}";
            do {
                System.out.println("Enter engineID: ");
                engine = input.nextLine().trim();
                if(searchEngine(engine) != -1) System.out.println("EngineID exist in list!");
                if(!frame.matches(pattern1)) System.out.println("EngineID must be \"E00000\" format");
            } while(searchEngine(engine) != -1 || !engine.matches(pattern1));

            this.get(pos).setBrand(brand);
            this.get(pos).setColor(color);
            this.get(pos).setFrameID(frame);
            this.get(pos).setEngineID(engine);
        }
        return true;
    }

    public void listCars(){
        Collections.sort(this, new Comparator<Car>() {

            @Override
            public int compare(Car o1, Car o2) {
                return o1.brand.getBrandName().compareTo(o2.brand.getBrandName());
            }
            
        });
        for (int i = 0; i < this.size(); i++) {
            System.out.println(this.get(i).screenString());
        }
    }
}
