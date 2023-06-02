/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nlp.classes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
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
    private final ISimilarityMetric similarityMetric;
    private final IRelevanceMetric relevanceMetric;
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
            ISimilarityMetric similarityMetric, 
            IRelevanceMetric relevanceMetric,
            IStringSelector stringSelector) {
        this.ui = ui;
        this.fileReader = fileReader;
        this.stringCleaner = stringCleaner;
        this.stringVectorizer = stringVectorizer;
        this.similarityMetric = similarityMetric;
        this.relevanceMetric = relevanceMetric;
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
        //vectorize data
        double[][] vectorizedSentences = stringVectorizer.getVectorizedData(contentSentences, wordDictionary);
        System.out.println("Calculating similarity scores...");
        //check how similar each sentence is compared to all the others and divide by amt of sentences
        double[] similarityScores = similarityMetric.getSimilarityScores(vectorizedSentences);
        System.out.println("Determining relevance scores...");
        //calculate relevance scores
        double[] relevanceScores = relevanceMetric.getRelevanceScores(contentText, stopWords, contentSentences);
        System.out.println("Combining metrics...");
        //combine similarity and relevance scores
        double[] combinedScores = stringSelector.combineMetrics(similarityScores, relevanceScores);
        //select top scoring strings
        System.out.println("Selecting output...");
        String[] output = stringSelector.selectStrings(contentSentences, combinedScores);
        for (String s : output) {
            System.out.println(s);
        }
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
