package nlp;

/**
 *
 * @author ethan
 */
public interface ISimilarityMetric {
    public double getSimilarity(double[] vector1, double[] vector2);
}
