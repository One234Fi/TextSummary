/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nlp;

import java.util.HashMap;

/**
 *
 * @author ethan
 */
public class StringVectorizer implements IStringVectorizer {
    //the list of content words from the main body of text
    private final String[] wordDictionary;
    
    /**
     * constructor
     * 
     * @param dictionary all of the content words in the body of text being vectorized 
     */
    public StringVectorizer(String[] dictionary) {
        this.wordDictionary = dictionary;
    }
    
    /**
     * Creates a vector representation of a string using the word dictionary as a basis
     * 
     * @param sentence the string to be vectorized
     * @return a double array representing the word count for each word in the original string
     */
    @Override
    public double[] getVectorizedString(String sentence) {
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
