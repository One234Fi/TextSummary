/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nlp.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Set;
import nlp.interfaces.IRelevanceMetric;

/**
 *
 * @author ethan
 */
public class RAKERelevance implements IRelevanceMetric {
    //this class should def be refactored with a constructor or something so that getRelevance can't be called while keyPhrases is null
    private String[] keyPhrases;
    
    /**
     * 
     * @param content the main body of text that sentences and stopWords came from
     * @param stopWords a set of stop words used to split content
     * @param sentences the strings that need relevance scores calculate
     * @return an array of relevance scores corresponding to the sentences parameter
     */
    @Override
    public double[] getRelevanceScores(String content, Set<String> stopWords, String[] sentences) {
        String[] candidatePhrases = getCandidatePhrases(content, stopWords);
        this.keyPhrases = determineKeyPhrases(content, stopWords, candidatePhrases);
        
        //this could also probably be a map, will mess with later after everything is actually working
        double[] relevanceScores = new double[sentences.length];
        for (int i = 0; i < sentences.length; i++) {
            relevanceScores[i] = getRelevance(sentences[i]);
        }
        
        return relevanceScores;
    }
    
    /**
     * 
     * @param string a string to determine a relevance score for
     * @return a relevance score for the input
     */
    @Override
    public double getRelevance(String string) {
        //very barebones and probably inefficient scoring
        double sum = 0.0;
        for (String phrase : keyPhrases) {
            if (string.contains(phrase)) {
                //probably a better idea to add the score of the phrase, but I'll have to refactor this stupid class first
                sum++;
            }
        }
        
        return 1.0 - (1.0 / sum);
    }
    
    private String[] getCandidatePhrases(String content, Set<String> stopWords) {
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
        
        return (String[]) candidates.toArray();
    }
    
    //TODO: find a better (faster) way to do this that ideally doesn't use triple nested for loops
    private String[] determineKeyPhrases(String content, Set<String> stopWords, String[] candidatePhrases) {
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
        
        //I think using maps instead of arrays might be a good idea for the following two code blocks
        double [] keywordScores = new double[contentWords.size()];
        for (int i = 0; i < keywordScores.length; i++) {
            double sum = 0.0;
            for (int j = 0; j < co_occurenceMatrix[i].length; j++) {
                sum += co_occurenceMatrix[i][j];
            }
            
            if (co_occurenceMatrix[i][i] != 0) {
                keywordScores[i] = sum / co_occurenceMatrix[i][i];
            }
        }
        
        //this should def use a map actually
        double[] phraseScores = new double[candidatePhrases.length];
        for (int i = 0; i < phraseScores.length; i++) {
            String[] candidateWords = candidatePhrases[i].split(" ");
            double sum = 0.0;
            for (String s : candidateWords) {
                if (contentWords.indexOf(s) != 1) {
                    sum += keywordScores[contentWords.indexOf(s)];
                }
            }
            phraseScores[i] = sum;
        }
        
        int keyPhraseNum = candidatePhrases.length / 5;
        String[] keyPhrases = new String[keyPhraseNum];
        
        int temp;
        for (int i = 0; i < keyPhraseNum; i++) {
            temp = Utilities.getMaxIndex(phraseScores);
            keyPhrases[i] = candidatePhrases[temp];
            phraseScores[temp] = 0;
        }
        
        return keyPhrases;
    }
}
