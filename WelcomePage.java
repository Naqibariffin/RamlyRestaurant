/**
 * Program description: Develop a Java application to manage the operations of a restaurant, focusing on customer orders and billing processes.
 *                      The system will handle customer information,menu items, and transaction records, including payment calculations
 * Programmer: NURATHRISHA ELISA BINTI YUSRI, MUHAMMAD NAQIB ASYRAAF BIN ARIFFIN, NUR FARHANA BINTI FAUZI
 * Date: 3 July 2024
 */

import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WelcomePage extends JFrame implements ActionListener
{
   // declare variable
   private JFrame welcomeFrame;
   private JButton startButton;
    
    public WelcomePage()
    {  
        welcomeFrame = new JFrame();
        
        //Create a title for the window
        welcomeFrame.setTitle("Ramly Restaurant System");
        //Set window size
        welcomeFrame.setSize(500, 500);
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set the JFrame to appear at the center of the screen 
        welcomeFrame.setLocationRelativeTo(null);

        //Create a layout/panel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        welcomeFrame.add(panel);

        //Add custom image on the welcome page
        ImageIcon introImage = new ImageIcon("Ramly.png");
        JLabel introLabel = new JLabel(introImage);
        panel.add(introLabel);

        // Create a button to start
        startButton = new JButton("Start !!");
        //Add listener to the button to call for action
        startButton.addActionListener(this);
        panel.add(startButton, BorderLayout.SOUTH);
        
        //Show the window / frame with all the elements attached
        welcomeFrame.setVisible(true);
    }   
    
    //Method for action when the startButton is clicked
    public void actionPerformed(ActionEvent e)
    {   //If book now button is clicked
        if (e.getSource() == startButton) {
            // Close the welcome page window in order to open the new window RamlyCounter
            welcomeFrame.dispose();
            new RamlyCounter();
        }
    }
    //Method for the class driver/main
    public static void main(String[] args)
    {
        //Start the system
        WelcomePage startWelcomePage = new WelcomePage();
    }
}
