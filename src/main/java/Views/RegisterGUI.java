package Views;

import App4Help.DatabaseHandler;
import App4Help.PersonneAidee;
import App4Help.PersonneBenevole;
import App4Help.PersonneOrganisation;
import Views.MesDemandesGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class RegisterGUI {

    public void createAndShowGUI(boolean isSeeker, boolean isVolunteer, boolean isOrganisation){

        JFrame frame = new JFrame("Select your status");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Création d'un JPanel pour organiser les composants
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        // Champ de texte pour le prenom
        JTextField firstnameField = new JTextField("Louis");
        firstnameField.setToolTipText("Entrez votre prénom");
        panel.add(firstnameField);
        // Champ de texte pour le nom
        JTextField nameField = new JTextField("Dupont");
        nameField.setToolTipText("Entrez votre nom");
        panel.add(nameField);
        // Champ de texte pour l'adresse
        JTextField adressField = new JTextField("5 rue des champs 31400 Toulouse");
        adressField.setToolTipText("Entrez votre adresse");
        panel.add(adressField);
        // Champ de texte pour le mot de passe
        JPasswordField passwordField = new JPasswordField("**");
        passwordField.setToolTipText("Entrez votre mot de passe");
        panel.add(passwordField);
        // Bouton de validation
        JButton validateButton = new JButton("Validate");
        panel.add(validateButton);

        // Ajout du panel au frame
        frame.add(panel);

        frame.pack();
        frame.setVisible(true);

        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseHandler.connect();
                frame.dispose();
                if (isSeeker){
                    PersonneAidee personneAidee = new PersonneAidee(nameField.getText(),firstnameField.getText(),adressField.getText(),String.valueOf(passwordField.getPassword()));
                    new MesDemandesGUI().createAndShowGUI(personneAidee);
                }
                if (isVolunteer){
                    new PersonneBenevole(nameField.getText(),firstnameField.getText(),adressField.getText(),String.valueOf(passwordField.getPassword()));
                }
                if (isOrganisation){
                    new PersonneOrganisation(nameField.getText(),firstnameField.getText(),adressField.getText(),String.valueOf(passwordField.getPassword()));
                }
            }
        });


        // Display the window.
        frame.setVisible(true);
    }























}
