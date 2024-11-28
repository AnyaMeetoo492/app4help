import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
public class GUI {

    public static void createAndShowGUI() {


        JFrame frame = new JFrame("Greeting Frame");
        frame.setResizable(false);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton connectButton = new JButton("Connect");
        frame.getContentPane().add(connectButton, BorderLayout.NORTH);

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new LoginPasswordGUI().createAndShowGUI(false,false,false);
                    }
                });
                //new SelectType().createAndShowGUI();
            }
        });

        JButton registerButton = new JButton("Register");
        frame.getContentPane().add(registerButton, BorderLayout.SOUTH);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new SelectType().createAndShowGUI();
                    }
                });
                //new SelectType().createAndShowGUI();
            }
        });

//        JButton tempoButton = new JButton("DemandeTempo");
//        frame.getContentPane().add(tempoButton, BorderLayout.CENTER);
//        tempoButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                frame.dispose();
//                javax.swing.SwingUtilities.invokeLater(new Runnable() {
//                    public void run() {
//
//
//                        new MesDemandesGUI().createAndShowGUI();
//                    }
//                });
//            }
//        });


        // make window's dimension fit its content
        frame.pack();
        // Display the window.
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
