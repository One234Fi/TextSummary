/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nlp.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import nlp.interfaces.ISimilarityMetric;

/**
 *
 * @author ethan
 */
public class ParallelizedCosineSimilarity extends CosineSimilarity implements ISimilarityMetric {
    @Override
    public double[] getSimilarityScores(double[][] vectors) {
        double[] scores = new double[vectors.length];
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()/2);
        for (int i = 0; i < vectors.length; i++) {
            executor.execute(new CosineSimilarityThread(scores, i, vectors, this));
        }
        executor.shutdown();
        try {
            //periodically print a message and block until all the threads are done
            boolean awaitTermination = executor.awaitTermination(30, TimeUnit.SECONDS);
            while (!executor.isTerminated()) {
                System.out.println("Waiting for cosine similarity threads to finish...");
                awaitTermination = executor.awaitTermination(30, TimeUnit.SECONDS);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ParallelizedStringVectorizer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return scores;
    }
    
    /**
     * this is probably really bad lol
     * @param vector1
     * @param vector2
     * @return 
     */
    @Override
    public double getSimilarity(double[] vector1, double[] vector2) {
        List<double[]> matrix = new ArrayList<>();
        for (int i = 0; i < vector1.length; i++) {
            matrix.add(new double[]{vector1[i], vector2[i]});
        }
        
        double similarity = getDotProduct(matrix);
        double magnitudeProduct = getMagnitude(vector1) * getMagnitude(vector2);
        if (magnitudeProduct != 0) {
            return similarity / magnitudeProduct;
        }
        
        return 0.0;
    }
    
    /**
     * idk if this is actually better
     * @param vector
     * @return 
     */
    private double getMagnitude(double[] vector) {
        DoubleAdder adder = new DoubleAdder();
        List<Double> vectorList = Arrays.stream(vector).boxed().collect(Collectors.toList());
        
        vectorList.parallelStream().forEach(num ->
            adder.add(num * num)
        );
        double magnitude = Math.sqrt(adder.doubleValue());
        
        return magnitude;
    }
    
    /**
     * this might also be really bad
     * @param matrix
     * @return 
     */
    private double getDotProduct(List<double[]> matrix) {
        DoubleAdder dotProduct = new DoubleAdder();
        
        matrix.parallelStream().forEach(vector ->
                dotProduct.add(vector[0] * vector[1])
        );
        
        return dotProduct.doubleValue();
    }
}
