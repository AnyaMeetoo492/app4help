import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class SelectType {
    public void createAndShowGUI() {


        JFrame frame = new JFrame("Select your status");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);




        JButton seekerButton = new JButton("Aidée");
        frame.getContentPane().add(seekerButton, BorderLayout.NORTH);
        seekerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new LoginPasswordGUI().createAndShowGUI(true,false,false);
                    }
                });
            }
        });

        JButton volunteerButton = new JButton("Bénévole");
        frame.getContentPane().add(volunteerButton, BorderLayout.CENTER);
        volunteerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new LoginPasswordGUI().createAndShowGUI(false,true,false);
                    }
                });
            }
        });

        JButton organisationButton = new JButton("Organisation");
        frame.getContentPane().add(organisationButton, BorderLayout.SOUTH);
        organisationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new LoginPasswordGUI().createAndShowGUI(false,false,true);
                    }
                });
            }
        });




        // make window's dimension fit its content
        frame.pack();
        // Display the window.
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.

    }
}

