import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        // Connection à la base de donnée
        DatabaseHandler database = new DatabaseHandler();
        database.connect();

        // Ajout d'une personne
        PersonneAidee personne = new PersonneAidee("Rousseau", "Firmin", "6 Allee des sciences, 31400, Toulouse");
    }
}
