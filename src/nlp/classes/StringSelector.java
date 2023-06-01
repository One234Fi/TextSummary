/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nlp.classes;

import java.util.Arrays;
import nlp.interfaces.IStringSelector;

/**
 *
 * @author ethan
 */
public class StringSelector implements IStringSelector {
    /**
     * 
     * @param similarityScores
     * @param relevanceScores
     * @return the parameters combined into one array
     */
    @Override
    public double[] combineMetrics(double[] similarityScores, double[] relevanceScores) {
        int safeLength = Math.min(similarityScores.length, relevanceScores.length);
        double[] combinedScores = new double[safeLength];
        for (int i = 0; i < safeLength; i++) {
            combinedScores[i] = similarityScores[i] + relevanceScores[i];
        }
        return combinedScores;
    }

    /**
     * 
     * @param content an array of strings
     * @param scores an array of scores associated with the strings
     * @return the top fifth of the strings
     */
    @Override
    public String[] selectStrings(String[] content, double[] scores) {
        int amountToTake = content.length/5;
        double[] scoresCopy = scores;
        String[] selectedStrings = new String[amountToTake];
        for (int i = 0; i < amountToTake; i++) {
            int maxIndex = Utilities.getMaxIndex(scoresCopy);
            selectedStrings[i] = content[maxIndex];
            scoresCopy[maxIndex] = Double.MIN_VALUE;
        }
        return selectedStrings;
    }
    
}
