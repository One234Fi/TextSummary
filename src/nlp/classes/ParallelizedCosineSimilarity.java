/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nlp.classes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ethan
 */
public class ParallelizedCosineSimilarity extends CosineSimilarity {
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
}
