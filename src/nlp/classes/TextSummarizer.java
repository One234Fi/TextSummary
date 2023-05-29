/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nlp.classes;

import nlp.interfaces.IFileReader;
import nlp.interfaces.IRelevanceMetric;
import nlp.interfaces.ISimilarityMetric;
import nlp.interfaces.IStringCleaner;
import nlp.interfaces.IStringVectorizer;
import nlp.interfaces.IUserInterface;

/**
 * select file - IUserInterface
 * extract data - IFileReader
 * clean data - IStringCleaner
 * vectorize - IStringVectorizer
 * cosine similarity scores - ISimilarityMetric
 * rake scores - IRelevanceMetric
 * determine output - Main
 *
 * @author ethan
 */
public class TextSummarizer {
    private IUserInterface ui;
    private IFileReader fileReader;
    private IStringCleaner stringCleaner;
    private IStringVectorizer stringVectorizer;
    private ISimilarityMetric similarityMetric;
    private IRelevanceMetric relevanceMetric;
    
    public TextSummarizer(
            IUserInterface ui, 
            IFileReader fileReader, 
            IStringCleaner stringCleaner, 
            IStringVectorizer stringVectorizer, 
            ISimilarityMetric similarityMetric, 
            IRelevanceMetric relevanceMetric) {
        this.ui = ui;
        this.fileReader = fileReader;
        this.stringCleaner = stringCleaner;
        this.stringVectorizer = stringVectorizer;
        this.similarityMetric = similarityMetric;
        this.relevanceMetric = relevanceMetric;
    }
    
    public void start() {
        //find and read file
        String filePath = ui.pickFilePath();
        String contentText = fileReader.getText(filePath);
        
        //clean data
        String[] contentSentences = stringCleaner.getSentences(contentText);
        String[] wordDictionary = stringCleaner.generateWordDictionary(contentText);
        
        //vectorize data
        double[][] vectorizedSentences = stringVectorizer.getVectorizedData(contentSentences, wordDictionary);
        
        //check how similar each sentence is compared to all the others and divide by amt of sentences
        
        //calculate relevance scores
        
        //combine similarity and relevance scores
        
        //select top scoring strings
    }
}
