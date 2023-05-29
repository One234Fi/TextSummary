package nlp.classes;

import nlp.interfaces.ISimilarityMetric;

/**
 * A class that implements the ISimilarityMetric interface to provide  
 * the cosine similarity formula as a vector distance metric
 *
 * @author ethan
 */
public class CosineSimilarity implements ISimilarityMetric {
    
    @Override
    public double[] getSimilarityScores(double[][] vectors) {
        
        return null;
    }
    
    /**
     * Calculate the similarity of two double arrays
     * 
     * @param vector1 a double array
     * @param vector2 a double array
     * @return the similarity of the parameters as a double
     */
    @Override
    public double getSimilarity(double[] vector1, double[] vector2) {
        double similarity = getDotProduct(vector1, vector2);
        double magnitudeProduct = getMagnitude(vector1) * getMagnitude(vector2);
        if (magnitudeProduct != 0) {
            return similarity / magnitudeProduct;
        }
        
        return 0.0;
    }
    
    /**
     * Calculate the magnitude of a double array
     * 
     * @param vector a double array
     * @return the calculated magnitude as a double
     */
    private double getMagnitude(double[] vector) {
        double magnitude = 0.0;
        
        for (double num : vector) {
            magnitude += num * num;
        }
        magnitude = Math.sqrt(magnitude);
        
        return magnitude;
    }
    
    /**
     * Calculate the dot product of two double vectors of the same size
     * 
     * @param vector1 a double array
     * @param vector2 a double array
     * @return the calculated dot product as a double
     */
    private double getDotProduct(double[] vector1, double[] vector2) {
        double dotProduct = 0.0;
        for (int i = 0; i < vector1.length; i++) {
            //skip zeroes
            if (vector1[i] != 0 && vector2[i] != 0) {
                dotProduct += vector1[i] * vector2[i];
            }
        }
        return dotProduct;
    }
}
