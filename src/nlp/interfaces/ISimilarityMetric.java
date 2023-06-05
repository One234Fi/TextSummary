package nlp.interfaces;

/**
 *
 * @author ethan
 */
public interface ISimilarityMetric {
    public double[] getSimilarityScores(int[][] vectors);
    public double getSimilarity(int[] vector1, int[] vector2);
}
