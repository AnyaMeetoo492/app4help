import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class LoginPasswordGUI {

    public void createAndShowGUI(boolean isSeeker, boolean isVolunteer, boolean isOrganisation){
        JFrame frame = new JFrame("Select your status");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField loginField = new JTextField();
        loginField.setToolTipText("entrez votre identifiant");
        frame.getContentPane().add(loginField, BorderLayout.NORTH);

        JPasswordField passwordField = new JPasswordField();
        frame.getContentPane().add(passwordField, BorderLayout.CENTER);

        JButton validateButton = new JButton("Validate");
        frame.getContentPane().add(validateButton, BorderLayout.SOUTH);
        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });


        // make window's dimension fit its content
        frame.pack();
        // Display the window.
        frame.setVisible(true);
    }























}
