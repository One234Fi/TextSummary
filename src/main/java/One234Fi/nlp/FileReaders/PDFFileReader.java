/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package One234Fi.nlp.FileReaders;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 *
 * @author ethan
 */
public class PDFFileReader implements IFileReader {
    
    /**
     * Get the text from a pdf file
     * 
     * @param filePath a string representing the file path
     * @return the text from the file or null
     */
    @Override
    public String getText(String filePath) {
        PDDocument document = null;
        
        try {
            document = PDDocument.load(new File(filePath));
            PDFTextStripper textStripper = new PDFTextStripper();
            String pdfText = textStripper.getText(document);
            return pdfText;
        }
        catch (IOException ex) {
            Logger.getLogger(PDFFileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            if (document != null) {
                try {
                    document.close();
                } catch (IOException ex) {
                    Logger.getLogger(PDFFileReader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return null;
    }
    
}
