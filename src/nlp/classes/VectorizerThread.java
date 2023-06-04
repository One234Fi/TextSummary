/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nlp.classes;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import nlp.interfaces.IStringVectorizer;

/**
 * This class might be stupid/bad practice but I'm trying it anyway
 * @author ethan
 */
public class VectorizerThread implements Runnable {
    //reference to array, index to modify, the string to vectorize
    private final Map reference;
    private final String string;
    private final String[] wordDictionary;
    private final IStringVectorizer vectorizerType;
    
    /**
     * 
     * @param reference an array to write to (probably bad practice but I'll worry about it later)
     * @param string the string that needs to be vectorized
     * @param wordDictionary the dictionary that will be used as a vector template
     * @param vectorizer the specific implementation to use
     */
    public VectorizerThread(Map reference, String string, String[] wordDictionary, IStringVectorizer vectorizer) {
        this.reference = reference;
        this.string = string;
        this.wordDictionary = wordDictionary;
        this.vectorizerType = vectorizer;
    }

    /**
     * modify only one "slot" of the reference array to (hopefully) avoid concurrent modification errors
     */
    @Override
    public void run() {
        this.reference.put(this.string, this.vectorizerType.getVectorizedString(string, wordDictionary));
    }
    
}
