/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nlp.classes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import nlp.interfaces.*;


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
    private final IUserInterface ui;
    private final IFileReader fileReader;
    private final IStringCleaner stringCleaner;
    private final IStringVectorizer stringVectorizer;
    private final IStringSelector stringSelector;
    
    /**
     * Instantiates a text summarizer object that utilizes the specified modules
     * 
     * @param ui
     * @param fileReader
     * @param stringCleaner
     * @param stringVectorizer
     * @param similarityMetric
     * @param relevanceMetric
     * @param stringSelector 
     */
    public TextSummarizer(
            IUserInterface ui, 
            IFileReader fileReader, 
            IStringCleaner stringCleaner, 
            IStringVectorizer stringVectorizer, 
            IStringSelector stringSelector) {
        this.ui = ui;
        this.fileReader = fileReader;
        this.stringCleaner = stringCleaner;
        this.stringVectorizer = stringVectorizer;
        this.stringSelector = stringSelector;
    }
    
    
    
    /**
     * calls the methods to generate a summary
     */
    public void start() {
        //find and read file
        System.out.println("Picking file...");
        String filePath = ui.pickFilePath();
        System.out.println("Parsing text...");
        String contentText = fileReader.getText(filePath);
        System.out.println("Loading stop words...");
        Set<String> stopWords = getStopWords();
        System.out.println("Removing stop words from content...");
        //remove stop words from the main body of text
        for(String s : stopWords) {
            contentText = contentText.replaceAll(s, "");
        }
        
        System.out.println("Cleaning content...");
        //clean data
        String[] contentSentences = stringCleaner.getSentences(contentText);
        System.out.println("Determining word dictionary...");
        String[] wordDictionary = stringCleaner.generateWordDictionary(contentText);
        System.out.println("Vectorizing data...");
        
        
        //vectorize data, TODO: finish refactoring this
        Map<String, int[]> contentVectors = stringVectorizer.getVectorizedData(contentSentences, wordDictionary);
        IScore cosineSimilarity = new CosineSimilarity(contentVectors);
        StringScorer scorer = new StringScorer(contentVectors.keySet(), cosineSimilarity);
        Map<String, Double> sentenceScores = scorer.getScores();
        
        System.out.println("Sentence Scores finished!");
        
        
        /*System.out.println("Selecting output...");
        //String[] output = stringSelector.selectStrings(contentSentences, combinedScores);
        for (String s : output) {
            System.out.println(s);
        }*/
    }
    
    private Set<String> getStopWords() {
        Set<String> stopWords = new HashSet<>();
        
        try {
            stopWords.addAll(Files.readAllLines(Paths.get("src/nlp/stopWords.txt")));
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }
        
        return stopWords;
    }
}
