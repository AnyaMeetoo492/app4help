
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;
import java.util.List;

public class MesDemandes {

    public MesDemandes() {
    }
    public void createAndShowGUI(){

        String[] columnNames = { "id", "nom", "prenom","adresse" }; // Noms des colonnes

        DatabaseHandler.connect();
        List<List<String>> demandes = DatabaseHandler.ListePersonne();

        //JTable table = new JTable(convertListToArray(demandes),columnNames);
        DefaultTableModel tableModel = new DefaultTableModel(convertListToArray(demandes), columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Toutes les cellules sont non modifiables
            }
        };

        JTable table = new JTable(tableModel);
        JFrame frame = new JFrame("Données de la base");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);

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
}
