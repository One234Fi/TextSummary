/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package nlp.classes;

import java.text.DecimalFormat;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ethan
 */
public class CosineSimilarityTest {
    /**
     * Test of getSimilarityScores method, of class CosineSimilarity.
     */
    @Test
    public void testGetSimilarityScores() {
        System.out.println("getSimilarityScores");
        double[][] vectors = {{1,1,1},{1,0,1},{0,0,1}};
        CosineSimilarity instance = new CosineSimilarity();
        double[] expResult = {1.39,1.52,1.28};
        double[] result = instance.getSimilarityScores(vectors);
        for (int i = 0; i < result.length; i++) {
            assertEquals(expResult[i], result[i]);
        }
    }

    /**
     * Test of getSimilarity method, of class CosineSimilarity.
     */
    @Test
    public void testGetSimilarity() {
        System.out.println("getSimilarity");
        double[] vector1 = {1, 0, 1};
        double[] vector2 = {1, 1, 1};
        CosineSimilarity instance = new CosineSimilarity();
        double expResult = 0.81;
        double result = instance.getSimilarity(vector1, vector2);
        assertEquals(expResult, result, 0.01);
    }
    
}
