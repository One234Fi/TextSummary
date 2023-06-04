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
public class ParallelizedCosineSimilarityTest {
    
    public ParallelizedCosineSimilarityTest() {
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
     * Test of getSimilarityScores method, of class ParallelizedCosineSimilarity.
     */
    @Test
    public void testGetSimilarityScores() {
        System.out.println("getSimilarityScores");
        double[][] vectors = null;
        ParallelizedCosineSimilarity instance = new ParallelizedCosineSimilarity();
        double[] expResult = null;
        double[] result = instance.getSimilarityScores(vectors);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSimilarity method, of class ParallelizedCosineSimilarity.
     */
    @Test
    public void testGetSimilarity() {
        System.out.println("getSimilarity");
        double[] vector1 = null;
        double[] vector2 = null;
        ParallelizedCosineSimilarity instance = new ParallelizedCosineSimilarity();
        double expResult = 0.0;
        double result = instance.getSimilarity(vector1, vector2);
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
