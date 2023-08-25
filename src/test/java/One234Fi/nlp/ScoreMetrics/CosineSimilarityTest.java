/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package One234Fi.nlp.ScoreMetrics;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ethan
 */
public class CosineSimilarityTest {
    /**
     * Test of getSimilarity method, of class CosineSimilarity.
     */
    @Test
    public void testGetSimilarity() {
        System.out.println("getSimilarity");
        Map<String, int[]> vectors = new HashMap<>();
        vectors.put("this is a sentence", new int[]{1,1,0,0,1,1,0,0});
        vectors.put("hello world", new int[]{0,0,1,1,0,0,0,0});
        vectors.put("it is what it is", new int[]{2,0,0,0,0,0,1,2});
        CosineSimilarity instance = new CosineSimilarity(vectors);
        double[] expResults = new double[]{0, 0.3333, 0};
        double[] results = new double[]{
            instance.getSimilarity("this is a sentence", "hello world"),
            instance.getSimilarity("this is a sentence", "it is what it is"),
            instance.getSimilarity("hello world", "it is what it is")
        };
        
        for (int i = 0; i < expResults.length; i++) {
            assertEquals(expResults[i], results[i], 0.001);
        }
    }

    /**
     * Test of getScore method, of class CosineSimilarity.
     */
    @Test
    public void testGetScore() {
        System.out.println("getScore");
        Map<String, int[]> vectors = new HashMap<>();
        vectors.put("this is a sentence", new int[]{1,1,0,0,1,1,0,0});
        vectors.put("hello world", new int[]{0,0,1,1,0,0,0,0});
        vectors.put("it is what it is", new int[]{2,0,0,0,0,0,1,2});
        CosineSimilarity instance = new CosineSimilarity(vectors);
        double[] expResults = new double[]{1.3333, 1, 1.3333};
        double[] results = new double[]{
            instance.getScore("this is a sentence"),
            instance.getScore("hello world"),
            instance.getScore("it is what it is")
        };
        for (int i = 0; i < expResults.length; i++) {
            assertEquals(expResults[i], results[i], 0.001);
        }
    }
    
}
