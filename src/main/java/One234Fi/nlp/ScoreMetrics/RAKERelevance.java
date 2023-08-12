/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package One234Fi.nlp.ScoreMetrics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import One234Fi.nlp.Utilities.Utilities;

/**
 * 
 * @author ethan
 */
public class RAKERelevance implements IMetric {
    //private String[] keyPhrases;
    private final Set<String> stopWords;
    private final Map<String, Double> wordScores;
    private final Map<String, Double> keyPhraseScores;
    
    //use the individual words to count occurences and the combined words for cooccurences
    private final Map<String, Integer> occurences;
    private final Map<String, Integer> coOccurences;
    
    public RAKERelevance(String content) {
        stopWords = Utilities.getStopWords();
        wordScores = new ConcurrentHashMap<>();
        //keyPhraseScores = generateKeyPhrases(content);
        keyPhraseScores = new ConcurrentHashMap<>();
        occurences = new HashMap<>();
        coOccurences = new HashMap<>();
        start(content);
    }
    
    @Override
    public double getScore(String s) {
        //compute the score for the sentence
        ArrayList<String> sentence = new ArrayList<>(Arrays.asList(s.split(" ")));
        sentence.removeAll(stopWords);
        double score = 0.0;
        for (String phrase : sentence) {
            score += keyPhraseScores.getOrDefault(phrase, 0.0);
        }
        
        return score;
    }
    
    private void start(String content) {
        Set<String> candidates = getCandidatePhrases(content);
        String[] words;
        for (String candidate : candidates) {
            words = candidate.split(" ");
            if (words.length > 1) {
                for (String word : words) {
                    //increment the occurence count for the word
                    occurences.put(word, occurences.getOrDefault(word, 0) + 1);
                    coOccurences.put(word, coOccurences.getOrDefault(word, 0) + 1);
                }
            } else if (words.length != 0) {
                occurences.put(words[0], occurences.getOrDefault(words[0], 0)+1);
            }
        }
    }
    
    //caching the word scores might be overkill since the calculation is simple
    //will test how it impacts memory and performance later
    public double getWordScore(String word) {
        if (wordScores.containsKey(word)) {
            return wordScores.get(word);
        }
        //compute word score using coOccurence matrix
        return (double) coOccurences.get(word) / occurences.get(word);
    }
    
    public double getPhraseScore(String phrase) {
        if (keyPhraseScores.containsKey(phrase)) {
            return keyPhraseScores.get(phrase);
        }
        //compute phrase score
        StringTokenizer st = new StringTokenizer(phrase, " ");
        double sum = 0.0;
        while (st.hasMoreTokens()) {
            sum += getWordScore(st.nextToken());
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
            if (!stopWords.contains(token)) {
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
}
