/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nlp.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import nlp.interfaces.IMetric;

/**
 *
 * @author ethan
 */
public class RAKERelevance implements IMetric {
    //private String[] keyPhrases;
    private final Set<String> stopWords;
    private final Map<String, Double> scores;
    private final Map<String, Double> keyPhraseScores;
    
    public RAKERelevance(String content) {
        stopWords = Utilities.getStopWords();
        scores = new ConcurrentHashMap<>();
        keyPhraseScores = generateKeyPhrases(content);
    }
    
    @Override
    public double getScore(String s) {
        if (scores.containsKey(s)) {
            return scores.get(s);
        }
        double score = getRelevance(s);
        scores.put(s, score);
        return score;
    }
    
    //TODO: this is probably needless abstraction that should be removed for simplicity
    private Map<String, Double> generateKeyPhrases(String content) {
        Set<String> candidates = getCandidatePhrases(content);
        return determineKeyPhrases(content, candidates);
    }
    
    /**
     * 
     * @param string a string to determine a relevance score for
     * @return a relevance score for the input
     */
    public double getRelevance(String string) {
        //this might be faster as a stream or something but I'll leave it for now
        double sum = 0.0;
        for (String phrase : keyPhraseScores.keySet()) {
            if (string.contains(phrase)) {
                sum += keyPhraseScores.get(phrase);
            }
        }
        
        return sum;
    }
    
    private Set<String> getCandidatePhrases(String content) {
        Set<String> candidates = new HashSet<>();
        StringBuilder candidate = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(content, " ");
        String token;
        while (st.hasMoreTokens()) {
            token = st.nextToken();
            if (stopWords.contains(token)) {
                candidate.append(token).append(" ");
            }
            else {
                if (!candidate.toString().trim().isBlank()) {
                    candidates.add(candidate.toString().trim());
                    candidate.setLength(0);
                }
            }
        }
        
        return candidates;
    }
    
    //splitting this into separate methods to make it easier to see which segments are slowing down the program
    private Map<String, Double> determineKeyPhrases(String content, Set<String> candidatePhrases) {
        List<String> contentWords = new ArrayList<>(Arrays.asList(content.split("\\W+")));
        //removeAll returns a boolean and not a list for some reason
        contentWords.removeAll(stopWords);
        
        //coocurrence and coOccurence both look bad to me so I'm breaking name rules and mixing camelcase with underscores
        int[][] co_occurenceMatrix = new int[contentWords.size()][contentWords.size()];
        
        for (int i = 0; i < contentWords.size(); i++) {
            for (int j = 0; j < contentWords.size(); j++) {
                for (String candidate : candidatePhrases) {
                    if (candidate.contains(contentWords.get(i)) && candidate.contains(contentWords.get(j))) {
                        co_occurenceMatrix[i][j]++;
                    }
                }
            }
        }
        
        
        Map<String, Double> keywordScores = getKeywordScores(contentWords, co_occurenceMatrix);
        
        Map<String, Double> phraseScores = getPhraseScores(candidatePhrases, keywordScores);
        
        return phraseScores;
    }
    
    private Map<String, Double> getKeywordScores(List<String> contentWords, int[][]co_occurenceMatrix) {
        Map<String, Double> keywordScores = new HashMap<>();
        for (int i = 0; i < contentWords.size(); i++) {
            double sum = 0.0;
            for (int j = 0; j < co_occurenceMatrix[i].length; j++) {
                sum += co_occurenceMatrix[i][j];
            }
            
            if (co_occurenceMatrix[i][i] != 0) {
                keywordScores.put(contentWords.get(i), sum / co_occurenceMatrix[i][i]);
            }
        }
        return keywordScores;
    }
    
    private Map<String, Double> getPhraseScores(Set<String> candidatePhrases, Map<String, Double> keywordScores) {
        Map<String, Double> phraseScores = new ConcurrentHashMap<>();
        candidatePhrases.parallelStream().forEach(candidate -> {
            String[] candidateWords = candidate.split(" ");
            double sum = 0.0;
            for (String s : candidateWords) {
                if (keywordScores.get(s) != null) {
                    sum += keywordScores.get(s);
                }
            }
            phraseScores.put(candidate, sum);
        });
        return phraseScores;
    }
}
