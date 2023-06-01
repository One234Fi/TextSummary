/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nlp.classes;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.Scanner;
import nlp.interfaces.IUserInterface;

/**
 *
 * @author ethan
 */
public class PDFFilePicker implements IUserInterface {
    /**
     * 
     * @return a valid pdf file path
     */
    @Override
    public String pickFilePath() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a pdf file path...");
        
        String filePath = sc.nextLine();
        while(!isValidPath(filePath)) {
            System.out.println("Couldn't find that file, please enter a valid path...");
            filePath = sc.nextLine();
        }
        
        return filePath;
    }
    
    /**
     * 
     * @param path a string representing a file location
     * @return whether or not the file exists (can be found)
     */
    private boolean isValidPath(String path) {
        try {
            Paths.get(path);
        } catch (InvalidPathException e) {
            return false;
        }
        return true;
    }
}
