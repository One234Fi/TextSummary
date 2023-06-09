/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nlp.classes;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import nlp.interfaces.IMetric;


/**
 * This class is given a collection of strings and a list of scoring metrics,
 * then it calculates the composite scores for the strings. These scores can be
 * individually accessed through getScore() or as a whole via getScores() 
 * 
 * @author ethan
 */
public class StringScorer {
    private final Collection<String> content;
    private final IMetric[] scoreMetrics;
    private final Map<String, Double> scores;
    
    /**
     * 
     * @param content the collection of strings to assign scores to
     * @param metrics a list of metrics used to composite string scores
     */
    public StringScorer(Collection<String> content, IMetric... metrics) {
        this.content = content;
        scoreMetrics = metrics;
        scores = new HashMap<>();
        generateScores();
    }
    
    /**
     * 
     * @return the map of strings and their scores
     */
    public Map<String, Double> getScores() {
        return scores;
    }
    
    /**
     * this is probably kind of slow
     * @param percentage the fraction of strings to take
     * @return an array of the top scoring strings
     */
    public List<String> getTopStrings(double percentage) {
		int offset = (int)(scores.size() * .15);
        int numStrings = (int)(scores.size() * percentage);
        /**Comparator<Entry<String, Double>> entryCompare = 
                (entry1, entry2) -> Double.compare(entry1.getValue(), entry2.getValue());
        Entry<String, Double>[] entries = (Entry<String, Double>[])scores.entrySet().toArray();
        Collections.sort(entries, entryCompare);
        
        String[] sortedStrings = new TreeMap<>(scores).keySet().toArray(new String[scores.size()]);
        */
        
        List<String> sortedStrings = scores.entrySet().stream()
                .sorted(Comparator.comparing(Entry::getValue))
                .map(Entry::getKey)
                .collect(Collectors.toList());
        
        
        return sortedStrings.subList(offset, numStrings);
    }
    
    
    
    /**
     * 
     * @param s a string
     * @return the string's score
     */
    public double getScore(String s) {
        return scores.get(s);
    }
    
    //TODO: have a separate thread calculate each score and merge them
    private void generateScores() {
        for (String s : content) {
            double sum = 0.0;
            for (IMetric score : scoreMetrics) {
                sum += score.getScore(s);
            }
            //System.out.println("String " + s + " has a score of: " + sum);
            scores.put(s, sum);
        }
    }
}
