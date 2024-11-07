import javax.swing.*;
import java.util.List;

public class DemandeGUI extends JPanel {
    public DemandeGUI(List<String> row){
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        for (String value : row) {  // Pour chaque valeur dans la ligne
            this.add(new JLabel(value));
        }
    }


}
