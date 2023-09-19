/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package One234Fi.nlp.UserInterfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * This class will probably not be completed anytime soon. 
 * This program wasn't really designed with a proper desktop GUI in mind
 *
 * @author ethan
 */
public class SwingFilePicker implements IUserInterface, ActionListener {
    JFrame frame;
    JButton button;
    JFileChooser j;
    
    public SwingFilePicker() {
        frame = new JFrame("Content Summarizer");
        
        button = new JButton("Select a file...");
        button.setBounds(150, 200, 220, 50);
        frame.add(button);
        button.addActionListener(this);
        
        j = new JFileChooser();
        frame.add(j);
        
        frame.setSize(600, 500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        j.showOpenDialog(null);
    }
    
    //sort of busy wait so should probably find a "proper" way to do this
    @Override
    public String pickFilePath() {
        while (j.getSelectedFile() == null) {
            try {
                sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(SwingFilePicker.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        return j.getSelectedFile().getAbsolutePath();
    }
}
