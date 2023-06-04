/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package nlp.classes;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ethan
 */
public class ParallelizedStringVectorizerTest {
    /**
     * Test of getVectorizedData method, of class ParallelizedStringVectorizer.
     */
    @Test
    public void testGetVectorizedData() {
        System.out.println("getVectorizedData");
        String[] contentSentences = {"this is a sentence", "hello world", "it is what it is"};
        String[] wordDictionary = {"is", "a", "hello", "world", "sentence", "this", "what", "it"};
        ParallelizedStringVectorizer instance = new ParallelizedStringVectorizer();
        Map<String, int[]> expResult = new ConcurrentHashMap<>();
        expResult.put("this is a sentence", new int[]{1,1,0,0,1,1,0,0});
        expResult.put("hello world", new int[]{0,0,1,1,0,0,0,0});
        expResult.put("it is what it is", new int[]{2,0,0,0,0,0,1,2});
        Map<String, int[]> result = instance.getVectorizedData(contentSentences, wordDictionary);
        
        for (int i = 0; i < result.size(); i++) {
            assertArrayEquals(expResult.get(contentSentences[i]), result.get(contentSentences[i]));
        }
    }
    
}
