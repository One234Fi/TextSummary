package nlp.classes;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import nlp.interfaces.IMetric;

/**
 * A class that calculates and stores the cosine similarity scores for a set of vectors.
 * getScore() overrides the IMetric interface to make this class easier to plug into
 * StringScorer objects. An overall cosineSimilarity score is calculated by summing up
 * the individual cosine similarities of a given string with every other string in the map
 *
 * @author ethan
 */
public class CosineSimilarity implements IMetric {
    private final Map<String, int[]> vectors;
    private final Map<String, Double> scores;
    private final Map<String[], Double> similarityCache;
    
    /**
     * 
     * @param vectors a map of strings to their vector representations
     */
    public CosineSimilarity(Map<String, int[]> vectors) {
        this.vectors = vectors;
        scores = new ConcurrentHashMap<>();
        similarityCache = new ConcurrentHashMap<>();
    }
    
    /**
     * 
     * @param s the string to get a score for
     * @return the overall cosine similarity score of the string
     */
    @Override
    public double getScore(String s) {
        if (scores.containsKey(s)) {
            return scores.get(s);
        }
        
        double score = vectors.keySet()
                .parallelStream()
                .mapToDouble(entry -> getSimilarity(s, entry))
                .sum();
        
        scores.put(s, score);
        return score;
    }
    
    /**
     * 
     * @param s1
     * @param s2
     * @return the cosine similarity of the parameters
     */
    public double getSimilarity(String s1, String s2) {
        String[] key = {s1, s2};
        if (similarityCache.containsKey(key)) {
            return similarityCache.get(key);
        }
        
        double result = Utilities.getDotProduct(vectors.get(s1), vectors.get(s2));
        double magnitudeProduct = Utilities.getMagnitude(vectors.get(s1)) * Utilities.getMagnitude(vectors.get(s2));
        if (magnitudeProduct != 0) {
            result /= magnitudeProduct;
            return similarityCache.put(key, result);
        }
        
        return 0.0;
    }
    
}
