/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nlp.classes;

import nlp.interfaces.IStringVectorizer;

/**
 * This class might be stupid/bad practice but I'm trying it anyway
 * @author ethan
 */
public class VectorizerThread implements Runnable {
    //reference to array, index to modify, the string to vectorize
    private double[][] reference;
    private int indexToModify;
    private String string;
    private String[] wordDictionary;
    private IStringVectorizer vectorizerType;
    
    //holy boilerplate batman
    public VectorizerThread(double[][] reference, int indexToModify, String string, String[] wordDictionary, IStringVectorizer vectorizer) {
        this.reference = reference;
        this.indexToModify = indexToModify;
        this.string = string;
        this.wordDictionary = wordDictionary;
        this.vectorizerType = vectorizer;
    }

    // I hope this works 
    @Override
    public void run() {
        this.reference[this.indexToModify] = this.vectorizerType.getVectorizedString(string, wordDictionary);
    }
    
}
