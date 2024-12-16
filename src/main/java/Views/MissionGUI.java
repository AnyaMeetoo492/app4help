package Views;

import App4Help.Demande;
import App4Help.Mission;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class MissionGUI extends JPanel {
    public MissionGUI(){

    }
    public static void createAndShowGUI(Demande demande){
        JFrame frame = new JFrame("Validate the query");

        JTextField inituleField = new JTextField("remplissez l'intitulé de la mission");


        JButton validateButton = new JButton("Validate");
        frame.add(validateButton);
        frame.pack();
        frame.setVisible(true);
        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Mission(new Date(),"",demande.getAidee());
                //TODO drop la requête acceptée
            }
        });









    }


}
