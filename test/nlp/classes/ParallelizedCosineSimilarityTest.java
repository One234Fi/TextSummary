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
public class ParallelizedCosineSimilarityTest {
    /**
     * Test of getSimilarityScores method, of class ParallelizedCosineSimilarity.
     */
    @Test
    public void testGetSimilarityScores() {
        System.out.println("getSimilarityScores");
        double[][] vectors = {{1,1,1},{1,0,1},{0,0,1}};
        ParallelizedCosineSimilarity instance = new ParallelizedCosineSimilarity();
        double[] expResult = {1.39,1.52,1.28};
        double[] result = instance.getSimilarityScores(vectors);
        for (int i = 0; i < result.length; i++) {
            assertEquals(expResult[i], result[i], 0.01);
        }
    }

    /**
     * Test of getSimilarity method, of class ParallelizedCosineSimilarity.
     */
    @Test
    public void testGetSimilarity() {
        System.out.println("getSimilarity");
        double[] vector1 = {1, 1, 1};
        double[] vector2 = {1, 1, 1};
        ParallelizedCosineSimilarity instance = new ParallelizedCosineSimilarity();
        double expResult = 1;
        double result = instance.getSimilarity(vector1, vector2);
        assertEquals(expResult, result, 0.0001);
    }
}
