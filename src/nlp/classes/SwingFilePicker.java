/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nlp.classes;

import javax.swing.JFileChooser;
import nlp.interfaces.IUserInterface;

/**
 *
 * @author ethan
 */
public class SwingFilePicker implements IUserInterface {
    JFileChooser j = new JFileChooser();
    
    @Override
    public String pickFilePath() {
        j.showSaveDialog(null);
        return j.getSelectedFile().getAbsolutePath();
    }
}
