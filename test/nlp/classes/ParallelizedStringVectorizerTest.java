/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package nlp.classes;

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
        double[][] expResult = {{1,1,0,0,1,1,0,0}, {0,0,1,1,0,0,0,0}, {2,0,0,0,0,0,1,2}};
        double[][] result = instance.getVectorizedData(contentSentences, wordDictionary);
        assertArrayEquals(expResult, result);
    }
    
}
