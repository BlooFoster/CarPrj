/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Com;

import java.util.Scanner;

/**
 *
 * @author PC
 */
public class ValidInput {
    Scanner sc = new Scanner(System.in);
    public double getDouble(String mess) {
        double number;
        while (true) { 
            try {
                System.out.print(mess);
                number = Double.parseDouble(sc.nextLine());
                if (number > 0) {
                    break;
                } else {
                    System.err.println("Invalid input.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid input.");
            }
        }
        return number;
    }
    
    public String getString(String mess) {
        String string;
        while (true) {            
            System.out.println(mess);
            string = sc.nextLine().trim();
            if (!string.isEmpty()) {
                break;
            } else {
                System.err.println("Input must not be empty");
            }
        }
        return string;
    }
    
}
