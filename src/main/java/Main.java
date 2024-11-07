import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Connection à la base de donnée
        DatabaseHandler database = new DatabaseHandler();
        database.connect();

        // Ajout d'une personne
        List<List<String>> ListePersonne = database.ListeOrganisation();
        for (List<String> personne : ListePersonne){
            System.out.println(personne);
        }
    }
}
