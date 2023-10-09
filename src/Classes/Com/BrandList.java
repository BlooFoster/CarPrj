/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Com;

/**
 *
 * @author PC
 */

public class BrandList{
     // TODO: call the parent's constructor

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

    public int searchID(String id) {
        // TODO: Search a brand based on `brandID`.
        // TODO: Return the position if exists.
    }

    public Brand getUserChoice() {
        // TODO: Show a menu of brands, let user choose a brand from this menu
        // 💡 Tip: Use the `Menu` class
    }

    public void addBrand() {
        // TODO: Add a new brand based on inputted data
        // TODO: Make sure all data are validated before adding
    }

    public void updateBrand() {
        // TODO: Sa as `addBrand()` but updating a brand instead
        // TODO: Make sure to let user input a brand ID first
    }

    public void listBrands() {
    }
}

