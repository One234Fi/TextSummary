/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nlp;

import nlp.classes.*;
import nlp.interfaces.IStringVectorizer;
import nlp.interfaces.IUserInterface;

/**
 *
 * @author ethan
 */
public class Main {
    /**
     * initialize a text summarizer object and call its start method
     * 
     */
    public static void main(String[] args) {
        IUserInterface ui = new PDFFilePicker();
        PDFFileReader reader = new PDFFileReader();
        StringCleaner cleaner = new StringCleaner();
        IStringVectorizer vectorizer = new ParallelizedStringVectorizer();
        
        TextSummarizer textSummarizer = new TextSummarizer(
                ui, reader, cleaner, vectorizer
        );
        
        textSummarizer.start();
    }
}
