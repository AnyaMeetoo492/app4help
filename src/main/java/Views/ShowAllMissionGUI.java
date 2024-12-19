package Views;

import Database.DatabaseHandler;
import Elements.Mission;
import Elements.PersonneAidee;
import Elements.PersonneBenevole;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShowAllMissionGUI {

    public ShowAllMissionGUI(){

    }
    public static void createAndShowGUI(PersonneBenevole personneBenevole) {

        ArrayList<String> listeColumns = new ArrayList<>(); // Noms des colonnes

        boolean showIdMission = false;
        if (showIdMission) listeColumns.add("showIdMission");
        boolean showDateDebut = true;
        if (showDateDebut) listeColumns.add("showDateDebut");
        boolean showDateFin = true;
        if (showDateFin) listeColumns.add("showDateFin");
        boolean showIntitule = true;
        if (showIntitule) listeColumns.add("showIntitule");
        boolean showStatut = true;
        if (showStatut) listeColumns.add("showStatut");

        boolean showNomAidee = false;
        if (showNomAidee) listeColumns.add("showNomAidee");
        boolean showPrenomAidee = true;
        if (showPrenomAidee) listeColumns.add("showPrenomAidee");
        boolean showNomBenevole = false;
        if (showNomBenevole) listeColumns.add("showNomBenevole");
        boolean showPrenomBenevole = false;
        if (showPrenomBenevole) listeColumns.add("showPrenomBenevole");
        listeColumns.add("Accept"); // Ajouter la colonne "Accept"
        String[] columnNames = listeColumns.toArray(new String[0]);

        DatabaseHandler.connect();
        List<List<String>> missions = DatabaseHandler.ListeMissionChoix(
                showIdMission, showDateDebut, showDateFin, showIntitule, showStatut,
                showNomAidee, showPrenomAidee, showNomBenevole, showPrenomBenevole
        );

        // Ajouter une colonne "Accept" pour chaque mission
        for (List<String> mission : missions) {
            mission.add("Accept");
        }

        String[][] arrayMissions = convertListToArray(missions);

        DefaultTableModel tableModel = new DefaultTableModel(arrayMissions, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Seule la colonne "Accept" est éditable
                return column == columnNames.length - 1;
            }
        };

        JTable table = new JTable(tableModel);
        table.getColumn("Accept").setCellRenderer(new ButtonRenderer());
        table.getColumn("Accept").setCellEditor(new ButtonEditor(new JCheckBox(),table));

        JFrame frame = new JFrame("Mes Missions");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel panelSouth = new JPanel();
        panelSouth.setLayout(new BoxLayout(panelSouth, BoxLayout.Y_AXIS));

        JButton quitButton = new JButton("Quit");
        panelSouth.add(quitButton);

        quitButton.addActionListener(e -> frame.dispose());

        JButton newButton = new JButton("Look for new missions");
        panelSouth.add(newButton);

        newButton.addActionListener(e -> {
            frame.dispose();
            // Logique pour ouvrir une nouvelle fenêtre
        });

        frame.add(panelSouth, BorderLayout.SOUTH);

        frame.pack();
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    // Renderer pour afficher les boutons dans la colonne "Accept"
    static class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column
        ) {
            setText((value == null) ? "Accept" : value.toString());
            return this;
        }
    }

    // Editor pour gérer les clics sur les boutons dans la colonne "Accept"
    static class ButtonEditor extends DefaultCellEditor {
        private JButton button;


        private JTable table;
        private String label;
        private boolean clicked;

        public ButtonEditor(JCheckBox checkBox,JTable table) {
            super(checkBox);
            this.table = table;
            button = new JButton();
            button.setOpaque(true);

            button.addActionListener(e -> fireEditingStopped());
        }

        @Override
        public Component getTableCellEditorComponent(
                JTable table, Object value, boolean isSelected, int row, int column
        ) {
            label = (value == null) ? "Accept" : value.toString();
            button.setText(label);
            clicked = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (clicked) {

                // Action spécifique au clic sur le bouton
                int id=table.getSelectedRow();
                System.out.println("Mission "+ id +" acceptée !");
                table.getSelectedRows();
                //TODO obtenir la mission selectionnée dans la liste des missions

            }
            clicked = false;
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            clicked = false;
            return super.stopCellEditing();
        }
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
