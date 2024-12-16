package Views;
import App4Help.Demande;
import App4Help.PersonneAidee;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

public class DemandeGUI extends JPanel {
    public DemandeGUI(){

    }
    public static void createAndShowGUI(PersonneAidee personneAidee){
        JFrame frame = new JFrame("Create your query");

        JButton validateButton = new JButton("Validate");
        frame.add(validateButton);
        frame.pack();
        frame.setVisible(true);
        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Demande(new Date(),"",personneAidee);
            }
        });




    }


}
