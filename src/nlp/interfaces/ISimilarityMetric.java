package nlp.interfaces;

/**
 *
 * @author ethan
 */
public interface ISimilarityMetric {
    public double[] getSimilarityScores(double[][] vectors);
    public double getSimilarity(double[] vector1, double[] vector2);
}
