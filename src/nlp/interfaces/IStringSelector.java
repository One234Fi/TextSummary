/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package nlp.interfaces;

/**
 *
 * @author ethan
 */
public interface IStringSelector {
    public double[] combineMetrics(double[] similarityScores, double[] relevanceScores);
    public String[] selectStrings(String[] content, double[] scores);
}
