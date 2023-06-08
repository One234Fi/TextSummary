/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package nlp.classes;

import java.util.Map;
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
public class StringScorerTest {
    
    public StringScorerTest() {
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
     * Test of getScores method, of class StringScorer.
     */
    @Test
    public void testGetScores() {
        System.out.println("getScores");
        StringScorer instance = null;
        Map<String, Double> expResult = null;
        Map<String, Double> result = instance.getScores();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTopStrings method, of class StringScorer.
     */
    @Test
    public void testGetTopStrings() {
        System.out.println("getTopStrings");
        double percentage = 0.0;
        StringScorer instance = null;
        String[] expResult = null;
        String[] result = instance.getTopStrings(percentage);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getScore method, of class StringScorer.
     */
    @Test
    public void testGetScore() {
        System.out.println("getScore");
        String s = "";
        StringScorer instance = null;
        double expResult = 0.0;
        double result = instance.getScore(s);
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
