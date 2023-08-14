/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package One234Fi.nlp;

import java.util.HashMap;

import One234Fi.nlp.DataProcessors.IStringVectorizer;
import One234Fi.nlp.DataProcessors.ParallelizedStringVectorizer;
import One234Fi.nlp.DataProcessors.StringCleaner;
import One234Fi.nlp.FileReaders.PDFFileReader;
import One234Fi.nlp.UserInterfaces.IUserInterface;
import One234Fi.nlp.UserInterfaces.PDFFilePicker;
import One234Fi.nlp.Utilities.TextSummarizer;


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
        
        HashMap <String, String> parsedArgs = parseArgs(args);
        textSummarizer.start(parsedArgs);
    }

    static HashMap<String, String> parseArgs(String[] args) {
        HashMap<String, String> flags = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            if (args[i].contains("-h")) {
                printHelp();
                System.exit(0);
            }

            if (args[i].startsWith("-")) {
                flags.put(args[i], args[++i]);
            }
        }
        return flags;
    }

    static void printHelp() {
        System.out.println("Usage: java -jar nlpts.jar [filepath] [-flag|option]");
        System.out.println("Leave flags empty for default options");

        System.out.println("Available options:");
        System.out.println("\t-h, -help:\tprint this message");
        System.out.println("\t-f [filepath]:\tredirect the output to a file");
        System.out.println("\t-cst [0.00-1.00]\tspecify the Cosine Similarity Threshold, numbers outside of 0-1 will be rounded to either 0 or 1");
        System.out.println("\t-rst [0.00-1.00]\tspecify the RAKE Score Threshold, numbers outside of 0-1 will be rounded to either 0 or 1");
        System.out.println("\t-st [0.00-1.00]\tspecify the Selection Threshold, numbers outside of 0-1 will be rounded to either 0 or 1");
    }
}
