/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package nlp.classes;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ethan
 */
public class ParallelizedStringVectorizerTest {
    
    public ParallelizedStringVectorizerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getVectorizedData method, of class ParallelizedStringVectorizer.
     */
    @Test
    public void testGetVectorizedData() {
        System.out.println("getVectorizedData");
        String[] contentSentences = null;
        String[] wordDictionary = null;
        ParallelizedStringVectorizer instance = new ParallelizedStringVectorizer();
        double[][] expResult = null;
        double[][] result = instance.getVectorizedData(contentSentences, wordDictionary);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
