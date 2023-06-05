/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nlp.classes;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import nlp.interfaces.IScore;

/**
 *
 * @author ethan
 */
public class StringScorer {
    private final Collection<String> content;
    private final IScore[] scoreMetrics;
    private final Map<String, Double> scores;
    
    public StringScorer(Collection<String> content, IScore... metrics) {
        this.content = content;
        scoreMetrics = metrics;
        scores = new HashMap<>();
        generateScores();
    }
    
    public Map<String, Double> getScores() {
        return scores;
    }
    
    public double getScore(String s) {
        return scores.get(s);
    }
    
    //TODO: have a separate thread calculate each score and merge them
    private void generateScores() {
        for (String s : content) {
            double sum = 0.0;
            for (IScore score : scoreMetrics) {
                sum += score.getScore(s);
            }
            scores.put(s, sum);
        }
    }
}
