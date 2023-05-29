/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package nlp.interfaces;

/**
 *
 * @author ethan
 */
public interface IRelevanceMetric {
    public double[] getRelevanceScores(String[] content);
    public double getRelevance(String string);
}
