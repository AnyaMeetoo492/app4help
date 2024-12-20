package Views;

import Database.DatabaseHandler;
import Elements.PersonneAidee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MesDemandesGUI {

    public static void createAndShowGUI(PersonneAidee personneAidee){

        ArrayList<String> listeColumns = new ArrayList<String>(); // Noms des colonnes

        boolean showIdDemande = false;
        if (showIdDemande) listeColumns.add("IdDemande");
        boolean showDateDemande = true;
        if (showDateDemande) listeColumns.add("Date Demande");
        boolean showDateValidation = false;
        if (showDateValidation) listeColumns.add("Date Validation");
        boolean showIntitule = true;
        if (showIntitule) listeColumns.add("Intitule");
        boolean showNomAidee = true;
        if (showNomAidee) listeColumns.add("Nom Aidee");
        boolean showPrenomAidee = true;
        if (showPrenomAidee) listeColumns.add("Prenom Aidee");
        boolean showStatut = false;
        if (showStatut) listeColumns.add("Statut");
        boolean showNomOrganisation = false;
        if (showNomOrganisation) listeColumns.add("Nom Valideur");
        boolean showPrenomOrganisation = false;
        if (showPrenomOrganisation) listeColumns.add("Prenom Valideur");
        boolean showMotif = false;
        if (showMotif) listeColumns.add("Motif");

        String[] columnNames = listeColumns.toArray(new String[0]);
        DatabaseHandler.connect();

        List<List<String>> demandes = DatabaseHandler.ListeDemandeChoix(showIdDemande, showDateDemande, showDateValidation, showIntitule,showStatut, showNomAidee, showPrenomAidee, showNomOrganisation,  showPrenomOrganisation, showMotif);
        //TODO filter les demandes en fonction de la personneAidee



        //JTable table = new JTable(convertListToArray(demandes),columnNames);
        DefaultTableModel tableModel = new DefaultTableModel(convertListToArray(demandes), columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Toutes les cellules sont non modifiables
            }
        };

        JTable table = new JTable(tableModel);
        JFrame frame = new JFrame("Mes Demandes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane,BorderLayout.NORTH);

        JPanel panelSouth = new JPanel();
        panelSouth.setLayout(new BoxLayout(panelSouth, BoxLayout.Y_AXIS));
        JButton newDemandeButton = new JButton("new");
        panelSouth.add(newDemandeButton, BorderLayout.CENTER);

        newDemandeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new DemandeGUI().createAndShowGUI(personneAidee);
                    }
                });
                //new Views.SelectType().createAndShowGUI();
            }
        });

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
        PersonneAidee personneAidee = new PersonneAidee("John", "Doe","rue des pins","abba");
        SwingUtilities.invokeLater(() -> createAndShowGUI(personneAidee));
    }
}
