package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JTableWithButtons {

    public static void main(String[] args) {
        // Lancer l'interface graphique dans le thread de l'EDT (Event Dispatch Thread)
        SwingUtilities.invokeLater(() -> CreateAndShowGUI());
    }

    public static void CreateAndShowGUI() {
        JFrame frame = new JFrame("JTable avec JButton");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Création du modèle de données pour la JTable
        DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Nom", "Prenom", "Accept"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3; // Seule la colonne "Accept" est éditable (pour le bouton)
            }
        };

        // Ajouter des données de démonstration
        model.addRow(new Object[]{"1", "Dupont", "Jean", "Accept"});
        model.addRow(new Object[]{"2", "Durand", "Marie", "Accept"});
        model.addRow(new Object[]{"3", "Martin", "Paul", "Accept"});

        // Créer le JTable avec le modèle
        JTable table = new JTable(model);

        // Définir un renderer pour la colonne "Accept" pour afficher un JButton
        table.getColumn("Accept").setCellRenderer(new ButtonRenderer());

        // Définir un editor pour la colonne "Accept" pour permettre les clics sur les boutons
        table.getColumn("Accept").setCellEditor(new ButtonEditor(new JCheckBox(), table));

        // Ajouter le tableau à un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);

        // Configuration de la fenêtre
        frame.setSize(600, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Méthode appelée par les boutons de la colonne "Accept"
    public static void accept(String ID) {
        System.out.println("Accept clicked for ID: " + ID);
    }

    // Renderer pour afficher des boutons dans la JTable
    static class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value == null ? "" : value.toString());
            return this;
        }
    }

    // Editor pour gérer les clics sur les boutons
    static class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean isPushed;
        private JTable table;

        public ButtonEditor(JCheckBox checkBox, JTable table) {
            super(checkBox);
            this.table = table;
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                    // Récupérer l'ID de la ligne correspondant au bouton cliqué
                    int row = table.convertRowIndexToModel(table.getEditingRow());
                    String ID = (String) table.getModel().getValueAt(row, 0);
                    accept(ID);
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = value == null ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            isPushed = false;
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
}
