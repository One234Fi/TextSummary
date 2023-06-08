/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package nlp.classes;

import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author ethan
 */
public class StringVectorizerTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    /**
     * Test of getVectorizedData method, of class StringVectorizer.
     */
    @Test
    public void testGetVectorizedData() {
        System.out.println("getVectorizedData");
        String[] contentSentences = {"this is a sentence", "hello world", "it is what it is"};
        String[] wordDictionary = {"is", "a", "hello", "world", "sentence", "this", "what", "it"};
        StringVectorizer instance = new StringVectorizer();
        Map<String, int[]> expResult = new HashMap<>();
        expResult.put("this is a sentence", new int[]{1,1,0,0,1,1,0,0});
        expResult.put("hello world", new int[]{0,0,1,1,0,0,0,0});
        expResult.put("it is what it is", new int[]{2,0,0,0,0,0,1,2});
        Map<String, int[]> result = instance.getVectorizedData(contentSentences, wordDictionary);
        for (String s : expResult.keySet()) {
            assertArrayEquals(expResult.get(s), result.get(s));
        }
    }

    /**
     * Test of getVectorizedString method, of class StringVectorizer.
     */
    @Test
    public void testGetVectorizedString() {
        System.out.println("getVectorizedString");
        String sentence = "it is what it is";
        String[] wordDictionary = {"is", "a", "hello", "world", "sentence", "this", "what", "it"};
        StringVectorizer instance = new StringVectorizer();
        int[] expResult = {2,0,0,0,0,0,1,2};
        int[] result = instance.getVectorizedString(sentence, wordDictionary);
        assertArrayEquals(expResult, result);
    }
    
}
