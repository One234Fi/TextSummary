/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package One234Fi.nlp.Utilities;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import One234Fi.nlp.DataProcessors.IStringCleaner;
import One234Fi.nlp.DataProcessors.IStringVectorizer;
import One234Fi.nlp.FileReaders.IFileReader;
import One234Fi.nlp.ScoreMetrics.CosineSimilarity;
import One234Fi.nlp.ScoreMetrics.IMetric;
import One234Fi.nlp.ScoreMetrics.RAKERelevance;
import One234Fi.nlp.ScoreMetrics.StringScorer;
import One234Fi.nlp.UserInterfaces.IUserInterface;


/**
 * Helper class that puts together a bunch of functions into an easy to use
 * object with default presets that automatically summarizes pdf content
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
    public void start(HashMap<String, String> flags) {
        double compositeThreshold = Double.parseDouble(flags.getOrDefault("-st", ".3"));
        double cst = Double.parseDouble(flags.getOrDefault("-cst", "1"));
        double rst = Double.parseDouble(flags.getOrDefault("-rst", "1"));
        String outputDescision = flags.getOrDefault("-f", null);


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
        double[] thresholds = {cst, rst};

        StringScorer scorer = new StringScorer(contentVectors.keySet(), thresholds, cosineSimilarity, RAKE);
        
        List<String> output = scorer.getTopStrings(compositeThreshold);

        if (outputDescision == null) {

            for (String s : output) {
                System.out.println(s);
            }
        }
        else {
            //print to the file path or throw an error if the file can't be accessed
            File f = new File(Paths.get(outputDescision).toUri());
            try {
                FileWriter fw = new FileWriter(f);
                for (String s : output) {
                    fw.write(s);
                    fw.write("\n");
                }
                fw.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        
    }
    
    
}
