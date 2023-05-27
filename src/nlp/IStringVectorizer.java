/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package nlp;

/**
 *
 * @author ethan
 */
public interface IStringVectorizer {
    public double[][] getVectorizedData (String[] contentSentences, String[] wordDictionary);
    public double[] getVectorizedString(String string, String[] wordDictionary);
}
