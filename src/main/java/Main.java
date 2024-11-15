import java.sql.Connection;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Connection à la base de donnée
        DatabaseHandler database = new DatabaseHandler();
        database.connect();

        LocalDate spdate = LocalDate.of(2024,11,15);
        Date date = Date.valueOf(spdate);
        Demande demande = new Demande(date,"çA marche", new PersonneAidee("Anya333","Meetoo333", "6 Allee", "333"));

        // Ajout d'une personne
        //List<List<String>> ListePersonne = database.ListeOrganisation();
        //for (List<String> personne : ListePersonne){
        //    System.out.println(personne);
        //}
    }
}
