/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nlp.classes;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import nlp.interfaces.IStringCleaner;

/**
 *
 * @author ethan
 */
public class StringCleaner implements IStringCleaner {

    /**
     * 
     * @param content the text to be split into sentences
     * @return an array of sentences with the punctuation stripped
     */
    @Override
    public String[] getSentences(String content) {
        String[] sentences = content.split("\\.");
        for (int i = 0; i < sentences.length; i++) {
            sentences[i] = cleanString(sentences[i]);
        }
        return sentences;
    }

    /**
     * 
     * @param string a string to be cleaned
     * @return a cleaned version of the input
     */
    @Override
    public String cleanString(String string) {
        return string.replaceAll("\\p{Punct}|\\P{Print}|[0-9]", "").trim();
    }

    /**
     * 
     * @param content a body of text
     * @return an array of content words derived from the parameter
     */
    @Override
    public String[] generateWordDictionary(String content) {
        Set<String> dictionary = new HashSet<>();
        StringTokenizer st = new StringTokenizer(content);
        while (st.hasMoreTokens()) {
            dictionary.add(st.nextToken());
        }
        
        return dictionary.toArray(String[]::new);
    }
    
}
