/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nlp.classes;

import java.util.HashMap;
import nlp.interfaces.IStringVectorizer;

/**
 *
 * @author ethan
 */
public class StringVectorizer implements IStringVectorizer {
    
    /**
     * Given a list of sentences and content words, vectorize all of the sentences
     * 
     * @param contentSentences the sentences to be vectorized
     * @param wordDictionary the content words to use
     * @return an array of vectorized sentences represented as double arrays
     */
    @Override
    public double[][] getVectorizedData(String[] contentSentences, String[] wordDictionary) {
        //Parallelize this, preferably sooner rather than later
        double[][] vectorizedSentences = new double[contentSentences.length][wordDictionary.length]; 
        for (int i = 0; i < contentSentences.length; i++) {
            vectorizedSentences[i] = getVectorizedString(contentSentences[i], wordDictionary);
        }
        return vectorizedSentences;
    }
    
    /**
     * Creates a vector representation of a string using the word dictionary as a basis
     * 
     * @param sentence the string to be vectorized
     * @param wordDictionary the list of words in the entire body of content
     * @return a double array representing the word count for each word in the original string
     */
    @Override
    public double[] getVectorizedString(String sentence, String[] wordDictionary) {
        double[] vectorRepresentation = new double[wordDictionary.length];
        HashMap<String, Integer> wordCount = getWordOccurences(sentence.split(" "));
        
        for (int i = 0; i < vectorRepresentation.length; i++) {
            if (wordCount.containsKey(wordDictionary[i])) {
                vectorRepresentation[i] = wordCount.get(wordDictionary[i]);
            }
        }
        
        return vectorRepresentation;
    }
    
    /**
     * counts the amount of each present word using a hashmap,
     * using the words as keys and the number of occurrences of 
     * each word as the values
     * 
     * @param sentence a list of the words in a sentence
     * @return how many times each word appears in the sentence as a hashmap
     */
    private HashMap<String, Integer> getWordOccurences(String[] sentence) {
        HashMap<String, Integer> wordCounter = new HashMap<>();
        
        for (String s : sentence) {
            if (wordCounter.containsKey(s)) {
                wordCounter.replace(s, wordCounter.get(s) + 1);
            }
            else {
                wordCounter.put(s, 1);
            }
        }
        
        return wordCounter;
    }
}
