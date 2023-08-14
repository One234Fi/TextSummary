/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package One234Fi.nlp.Utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/**
 * Static utility methods that may be used in multiple classes
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
    
    public static int getMaxIndex(int[] arr) {
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
    
    /**
     * Calculate the magnitude of a double array
     * 
     * @param vector a double array
     * @return the calculated magnitude as a double
     */
    public static double getMagnitude(double[] vector) {
        double magnitude = 0.0;
        
        for (double num : vector) {
            magnitude += num * num;
        }
        magnitude = Math.sqrt(magnitude);
        
        return magnitude;
    }
    
    public static double getMagnitude(int[] vector) {
        double magnitude = 0.0;
        
        for (double num : vector) {
            magnitude += num * num;
        }
        magnitude = Math.sqrt(magnitude);
        
        return magnitude;
    }
    
    /**
     * Calculate the dot product of two double vectors of the same size
     * 
     * @param vector1 a double array
     * @param vector2 a double array
     * @return the calculated dot product as a double
     */
    public static int getDotProduct(int[] vector1, int[] vector2) {
        int dotProduct = 0;
        for (int i = 0; i < vector1.length; i++) {
            //skip zeroes
            if (vector1[i] != 0 && vector2[i] != 0) {
                dotProduct += vector1[i] * vector2[i];
            }
        }
        return dotProduct;
    }
    
    public static double getDotProduct(double[] vector1, double[] vector2) {
        double dotProduct = 0.0;
        for (int i = 0; i < vector1.length; i++) {
            //skip zeroes
            if (vector1[i] != 0 && vector2[i] != 0) {
                dotProduct += vector1[i] * vector2[i];
            }
        }
        return dotProduct;
    }
    
    public static Set<String> getStopWords() {
        Set<String> stopWords = new HashSet<>();
        
        try {
            stopWords.addAll(Files.readAllLines(Paths.get("src/main/java/One234Fi//nlp/stopWords.txt")));
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }
        
        return stopWords;
    }
}
