/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Com;
import java.util.*;
import java.lang.*;
import java.io.*;

/**
 *
 * @author PC
 */
public class CarList {

    Brandlist brandList;

    public CarList(BrandList bList){
        brandList=bList;
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
    }

    public boolean saveToFile(String filename) {
        // TODO: Follow the same step as in `loadFromFile()` method
        // TODO: But open the file for writing instead of reading
    }

    public int searchID(String carID) {
        int N = this.size();
        for (int i = 0; i < N; i++) {
            if (this.get(i).carID.equals(carID)) return i;           
        }        
        return -1; 
    }

    public int searchFrame(String frameID) {
        int N = this.size();
        for (int i = 0; i < N; i++) {
            if (this.get(i).frameID.equals(frameID)) return i;           
        }        
        return -1; 
    }

    public int searchEngine(String engineID) {
        int N = this.size();
        for (int i = 0; i < N; i++) {
            if (this.get(i).engineID.equals(engineID)) return i;           
        }        
        return -1; 
    }

    public void addCar() {
        // TODO: Add a new car based on inputted data
        // TODO: Make sure frameID is formatted as: "F0000" and not be duplicated
        // TODO: Make sure engineID is formatted as: "E0000" and not be duplicated
        // TODO: Make sure all data are validated before adding
    }

    public void printBasedBrandName() {
        // TODO: 1. Let user input a part of brand name
        // TODO: 2. Print out the `screenString()` any car whose name contains the part
        // of brand name
        // TODO: 3. If no car satisfies, print out the appropriate message to the user
    }

    public boolean removeCar() {
        // TODO: Let user input the car ID, then remove if the car exists
        // TODO: Return `true` if removed successfully, `false` if the car is not found
    }

    public boolean updateCar() {
        // TODO: Same as `removeCar()`
        // TODO: Make sure new frameID is formatted as: "F0000" and not be duplicated
        // TODO: Make sure new engineID is formatted as: "E0000" and not be duplicated
    }

    public void listCars() {

    }
}
