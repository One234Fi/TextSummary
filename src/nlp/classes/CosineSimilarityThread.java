/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nlp.classes;

import nlp.interfaces.ISimilarityMetric;

/**
 *
 * @author ethan
 */
public class CosineSimilarityThread implements Runnable {
    //reference to array, index to modify, data to use, the string to vectorize
    private final double[] reference;
    private final int indexToModify;
    private final double[][] data;
    private final ISimilarityMetric metricType;
    
    /**
     * 
     * @param reference the array to modify
     * @param indexToModify the specific index of the array to modify
     * @param data the data used to calculate the score
     * @param metricType the method used to get a similarity score
     */
    public CosineSimilarityThread(double[] reference, int indexToModify, double[][] data, ISimilarityMetric metricType) {
        this.reference = reference;
        this.indexToModify = indexToModify;
        this.data = data;
        this.metricType = metricType;
    }

    /**
     * modify only one "slot" of the reference array to (hopefully) avoid concurrent modification errors
     */
    @Override
    public void run() {
        double sum = 0.0;
        for (int i = 0; i < data.length; i++) {
            sum += metricType.getSimilarity(data[indexToModify], data[i]);
        }
        this.reference[this.indexToModify] = sum-1;
    }
}
