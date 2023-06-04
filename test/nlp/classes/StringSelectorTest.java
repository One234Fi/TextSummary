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
public class StringSelectorTest {
    
    public StringSelectorTest() {
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
     * Test of combineMetrics method, of class StringSelector.
     */
    @Test
    public void testCombineMetrics() {
        System.out.println("combineMetrics");
        double[] similarityScores = null;
        double[] relevanceScores = null;
        StringSelector instance = new StringSelector();
        double[] expResult = null;
        double[] result = instance.combineMetrics(similarityScores, relevanceScores);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of selectStrings method, of class StringSelector.
     */
    @Test
    public void testSelectStrings() {
        System.out.println("selectStrings");
        String[] content = null;
        double[] scores = null;
        StringSelector instance = new StringSelector();
        String[] expResult = null;
        String[] result = instance.selectStrings(content, scores);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
