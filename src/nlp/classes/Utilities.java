/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nlp.classes;

/**
 *
 * @author ethan
 */
public class Utilities {
    public static int getMaxIndex(double[] arr) {
        double max = arr[0];
        int index = 0;
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
                index = i;
            }
        }
        
        return index;
    }
}
