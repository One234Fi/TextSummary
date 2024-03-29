/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package One234Fi.nlp.ScoreMetrics;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;


/**
 * This class is given a collection of strings and a list of scoring metrics,
 * then it calculates the composite scores for the strings. These scores can be
 * individually accessed through getScore() or as a whole via getScores(). The 
 * getTopStrings() method also provides a default selection criteria
 * 
 * @author ethan
 */
public class StringScorer {
    private final Collection<String> content;
    private final IMetric[] scoreMetrics;
    private final Map<String, Double> scores;
    private final double[] thresholds;
    
    /**
     * 
     * @param content the collection of strings to assign scores to
     * @param metrics a list of metrics used to composite string scores
     */
    public StringScorer(Collection<String> content, double[] thresholds, IMetric... metrics) {
        this.content = content;
        scoreMetrics = metrics;
        scores = new HashMap<>();
        this.thresholds = thresholds;
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
     * Grabs a specified percentage of the input data, omits the top 15% of data to reduce "trash" data. 
     * This is probably a method to look at more closely for future improvements
     *
     * this is probably kind of slow
     * @param percentage the fraction of strings to take
     * @return an array of the top scoring strings
     */
    public List<String> getTopStrings(double percentage) {
		int offset = (int)(scores.size() * .15);
        int numStrings = (int)(scores.size() * percentage);
        
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
            for (int i = 0; i < scoreMetrics.length; i++) {
                sum += scoreMetrics[i].getScore(s) * thresholds[i];
            }
            scores.put(s, sum);
        }
    }
}
