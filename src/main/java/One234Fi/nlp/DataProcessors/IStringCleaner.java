/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package One234Fi.nlp.DataProcessors;

/**
 *
 * @author ethan
 */
public interface IStringCleaner {
    public String[] getSentences(String content);
    public String cleanString(String string);
    public String[] generateWordDictionary(String content);
}
