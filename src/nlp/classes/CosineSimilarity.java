package nlp.classes;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import nlp.interfaces.IScore;
//import nlp.classes.Utilities;
import nlp.interfaces.ISimilarityMetric;

/**
 * A class that implements the ISimilarityMetric interface to provide  
 * the cosine similarity formula as a vector distance metric
 *
 * @author ethan
 */
public class CosineSimilarity implements IScore {
    private final Map<String, int[]> vectors;
    private final Map<String, Double> scores;
    private final Map<String[], Double> similarityCache;
    
    public CosineSimilarity(Map<String, int[]> vectors) {
        this.vectors = vectors;
        scores = new ConcurrentHashMap<>();
        similarityCache = new ConcurrentHashMap<>();
    }
    
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
    
    //as each sim score is calculated, add it to the scores map
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
