
import javax.swing.*;
import java.util.List;

public class MesDemandes {

    public MesDemandes() {
    }
    public void createandshowGUI(){

        String[] columnNames = {"Colonne1", "Colonne2", "Colonne3"}; // Noms des colonnes

        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);

        JFrame frame = new JFrame("Données de la base");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(scrollPane);

        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
