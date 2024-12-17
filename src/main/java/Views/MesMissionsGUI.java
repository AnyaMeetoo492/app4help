package Views;

import Database.DatabaseHandler;
import Elements.PersonneBenevole;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MesMissionsGUI {

    public static void createAndShowGUI(PersonneBenevole personneBenevole){

        ArrayList<String> listeColumns = new ArrayList<String>(); // Noms des colonnes

        boolean showIdPersonne = false;
        if (showIdPersonne) listeColumns.add("idPersonne");

        boolean showNom = true;
        if (showNom) listeColumns.add("nom");
        boolean showPrenom = true;
        if (showPrenom) listeColumns.add("prenom");
        boolean showAdresse = false;
        if (showAdresse) listeColumns.add("adresse");
        boolean showPassword = false;
        if (showPassword) listeColumns.add("password");
        String[] columnNames = listeColumns.toArray(new String[0]);
        DatabaseHandler.connect();
        List<List<String>> demandes = DatabaseHandler.ListePersonneChoix(showIdPersonne,showNom,showPrenom,showAdresse,showPassword);



        //JTable table = new JTable(convertListToArray(demandes),columnNames);
        DefaultTableModel tableModel = new DefaultTableModel(convertListToArray(demandes), columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Toutes les cellules sont non modifiables
            }
        };

        JTable table = new JTable(tableModel);
        JFrame frame = new JFrame("Mes Missions");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane,BorderLayout.NORTH);

        JPanel panelSouth = new JPanel();
        panelSouth.setLayout(new BoxLayout(panelSouth, BoxLayout.Y_AXIS));

        JButton quitButton = new JButton("quit");
        panelSouth.add(quitButton);

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        frame.dispose();
                    }
                });
                //new Views.SelectType().createAndShowGUI();
            }
        });
        frame.add(panelSouth);


        frame.pack();
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
    public static String[][] convertListToArray(List<List<String>> listOfLists) {
        // Initialiser le tableau
        String[][] array = new String[listOfLists.size()][];

        // Remplir le tableau
        for (int i = 0; i < listOfLists.size(); i++) {
            List<String> row = listOfLists.get(i);
            array[i] = row.toArray(new String[0]); // Convertir chaque liste en tableau
        }

        return array;
    }
    public static void main(String[] args) {
        // Lancement de l'interface graphique
        DatabaseHandler.connect();
        PersonneBenevole personneBenevole = new PersonneBenevole("John", "Doe","rue des pins","abba");
        SwingUtilities.invokeLater(() -> createAndShowGUI(personneBenevole));
    }
}
