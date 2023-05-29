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
public class ParallelizedStringVectorizer extends StringVectorizer {
    @Override
    public double[][] getVectorizedData(String[] contentSentences, String[] wordDictionary) {
        double[][] vectorizedSentences = new double[contentSentences.length][wordDictionary.length];
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < contentSentences.length; i++) {
            //vectorizedSentences[i] = getVectorizedString(contentSentences[i], wordDictionary);
            executor.execute(new VectorizerThread(vectorizedSentences, i, contentSentences[i], wordDictionary, this));
        }
        executor.shutdown();
        try {
            //periodically print a message and block until all the threads are done
            boolean awaitTermination = executor.awaitTermination(30, TimeUnit.SECONDS);
            while (!executor.isTerminated()) {
                System.out.println("Waiting for vectorizer threads to finish...");
                awaitTermination = executor.awaitTermination(30, TimeUnit.SECONDS);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ParallelizedStringVectorizer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vectorizedSentences;
    }
}
