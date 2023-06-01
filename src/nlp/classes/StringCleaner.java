/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nlp.classes;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import nlp.interfaces.IStringCleaner;

/**
 *
 * @author ethan
 */
public class StringCleaner implements IStringCleaner {

    @Override
    public String[] getSentences(String content) {
        String[] sentences = content.split(".");
        for (int i = 0; i < sentences.length; i++) {
            sentences[i] = cleanString(sentences[i]);
        }
        
        return sentences;
    }

    @Override
    public String cleanString(String string) {
        return string.replaceAll("\\p{Punct}|\\P{Print}|[0-9]", "").trim();
    }

    @Override
    public String[] generateWordDictionary(String content) {
        Set<String> dictionary = new HashSet<>();
        StringTokenizer st = new StringTokenizer(content);
        while (st.hasMoreTokens()) {
            dictionary.add(st.nextToken());
        }
        
        return (String[]) dictionary.toArray();
    }
    
}
