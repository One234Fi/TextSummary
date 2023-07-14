/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nlp.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import nlp.interfaces.*;


/**
 * 
 *
 * @author ethan
 */
public class TextSummarizer {
    private final IUserInterface ui;
    private final IFileReader fileReader;
    private final IStringCleaner stringCleaner;
    private final IStringVectorizer stringVectorizer;
    
    /**
     * Instantiates a text summarizer object that utilizes the specified modules
     * 
     * @param ui
     * @param fileReader
     * @param stringCleaner
     * @param stringVectorizer
     */
    public TextSummarizer(
            IUserInterface ui, 
            IFileReader fileReader, 
            IStringCleaner stringCleaner, 
            IStringVectorizer stringVectorizer) {
        this.ui = ui;
        this.fileReader = fileReader;
        this.stringCleaner = stringCleaner;
        this.stringVectorizer = stringVectorizer;
    }
    
    
    
    /**
     * calls the methods to generate a summary
     */
    public void start() {
        //find and read file
        String filePath = ui.pickFilePath();
        String contentText = fileReader.getText(filePath);
        Set<String> stopWords = Utilities.getStopWords();
        
        ArrayList<String> content = Stream
                .of(contentText.toLowerCase().split(" "))
                .collect(Collectors.toCollection(ArrayList<String>::new));
        
        //remove stop words from the main body of text
        content.removeAll(stopWords);
        
        //clean data
        String[] contentSentences = stringCleaner.getSentences(contentText);
        String[] wordDictionary = stringCleaner.generateWordDictionary(contentText);
        
        Map<String, int[]> contentVectors = stringVectorizer.getVectorizedData(contentSentences, wordDictionary);
        IMetric cosineSimilarity = new CosineSimilarity(contentVectors);
        IMetric RAKE = new RAKERelevance(contentText);
        StringScorer scorer = new StringScorer(contentVectors.keySet(), cosineSimilarity, RAKE);
        
        List<String> output = scorer.getTopStrings(0.4);
        for (String s : output) {
            System.out.println(s);
        }
        
    }
    
    
}
