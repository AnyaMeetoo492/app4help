import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPasswordGUI {
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
        // Champ de texte pour le mot de passe
        JPasswordField passwordField = new JPasswordField("******");
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
                if (DatabaseHandler.isInDatabase(firstnameField.getText(),nameField.getText(),String.valueOf(passwordField.getPassword()))){
                    frame.dispose();
                    Personne user = DatabaseHandler.getPersonneWithLogin(firstnameField.getText(),nameField.getText(),String.valueOf(passwordField.getPassword()));


                    if (isSeeker){
                        PersonneAidee personneAidee = new PersonneAidee(firstnameField.getText(),nameField.getText(),user.getAdresse(),String.valueOf(passwordField.getPassword()));
                        new MesDemandesGUI().createAndShowGUI(personneAidee);//affiche les demandes de l'utilisateur aidé
                    }
                    if (isVolunteer){
                        PersonneAidee personneAidee = new PersonneAidee(firstnameField.getText(),nameField.getText(),user.getAdresse(),String.valueOf(passwordField.getPassword()));
                        new MesDemandesGUI().createAndShowGUI(personneAidee);//affiche les mission disponibles
                    }
                    if (isOrganisation){
                        //affiche les demandes disponibles
                    }
                }
                else {
                    //printf
                }

            }
        });


        // Display the window.
        frame.setVisible(true);
    }
}
