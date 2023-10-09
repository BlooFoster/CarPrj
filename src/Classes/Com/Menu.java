/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Com;
import java.util.*;
/**
 *
 * @author PC
 */
 public class Menu<E> {
    Scanner sc = new Scanner(System.in);

    public int int_getChoice(ArrayList<E> options) {
        // TODO: Print out all the options (elements of `options` list)
        // TODO: Receive input as integer from user
        // TODO: Repeat the process if user enter invalid option
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + "-" + options.get(i));
        }
        System.out.print("Choose 1.. " + options.size() + ": ");
        return Integer.parseInt(sc.nextLine());
    }

    public E ref_getChoice(ArrayList<E> options) {
        // TODO: Call the below `int_getChoice()` method to get the option as integer,
        // called `i`
        // TODO: Return the element at `i - 1` from the `options` list.
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + "-" + options.get(i));
        }
        System.out.println("Choose 1.." + options.size() + ": ");
        return options.get(Integer.parseInt(sc.nextLine()) - 1);
    }
}

