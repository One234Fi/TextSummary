/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package One234Fi.nlp.DataProcessors;

import java.util.Map;

/**
 *
 * @author ethan
 */
public interface IStringVectorizer {
    //public double[][] getVectorizedData (String[] contentSentences, String[] wordDictionary);
    public Map<String, int[]> getVectorizedData(String[] contentSentences, String[] wordDictionary);
    public int[] getVectorizedString(String string, String[] wordDictionary);
}
