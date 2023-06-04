/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package nlp.classes;

import java.util.Set;
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
public class RAKERelevanceTest {
    
    public RAKERelevanceTest() {
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
     * Test of getRelevanceScores method, of class RAKERelevance.
     */
    @Test
    public void testGetRelevanceScores() {
        System.out.println("getRelevanceScores");
        String content = "";
        Set<String> stopWords = null;
        String[] sentences = null;
        RAKERelevance instance = new RAKERelevance();
        double[] expResult = null;
        double[] result = instance.getRelevanceScores(content, stopWords, sentences);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRelevance method, of class RAKERelevance.
     */
    @Test
    public void testGetRelevance() {
        System.out.println("getRelevance");
        String string = "";
        RAKERelevance instance = new RAKERelevance();
        double expResult = 0.0;
        double result = instance.getRelevance(string);
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
