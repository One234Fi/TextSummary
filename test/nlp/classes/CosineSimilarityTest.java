/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package nlp.classes;

import java.text.DecimalFormat;
import java.util.Arrays;
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
public class CosineSimilarityTest {
    
    public CosineSimilarityTest() {
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
     * Test of getSimilarityScores method, of class CosineSimilarity.
     */
    @Test
    public void testGetSimilarityScores() {
        System.out.println("getSimilarityScores");
        double[][] vectors = null;
        CosineSimilarity instance = new CosineSimilarity();
        double[] expResult = null;
        double[] result = instance.getSimilarityScores(vectors);
        assertTrue(Arrays.equals(expResult, result));
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSimilarity method, of class CosineSimilarity.
     */
    @Test
    public void testGetSimilarity() {
        System.out.println("getSimilarity");
        double[] vector1 = {1, 5, 3};
        double[] vector2 = {6, 1, 0};
        CosineSimilarity instance = new CosineSimilarity();
        String expResult = "0.305674";
        DecimalFormat format = new DecimalFormat("#.######");
        double output = instance.getSimilarity(vector1, vector2);
        String result = format.format(output);
        assertEquals(expResult, result);
        
    }
    
}
