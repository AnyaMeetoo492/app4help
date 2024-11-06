import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Classe pour
// - se connecter à la base de donnée
// - éxecuter des requêtes (ajout, modifier, enlever)
public class DatabaseHandler {

    // Les paramètres pour accéder à la base de donnée
    // jdbc:mysql pour accéder à travers mysql
    private static final String DB_URL = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_030";
    private static final String USER = "projet_gei_030";
    private static final String PASSWORD = "Shu4Rahm";

    public static Connection conn = null;

    // Établir la connection
    public static void connect() {
        try {
            // Essayer d'accéder à une connection
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Connection established successfully.");
        } catch (SQLException e) {
            // sinon erreurs
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }
        // retourner la connection établie
    }

    /*
    ================================================
                    LES INSERTIONS
    ================================================
     */

    // Personne
    public static int InsertPersonne(String nom, String prenom, String adresse, String password) {
        String insertStatement = "INSERT INTO Personne (nom, prenom, adresse, password) VALUES (?, ?, ?, ?)";

        // Use Statement.RETURN_GENERATED_KEYS to retrieve the auto-incremented key
        try (PreparedStatement stmt = conn.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS)) {
            // Set parameters for the query
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setString(3, adresse);
            stmt.setString(4, password);

            // Execute the update
            stmt.executeUpdate();
            System.out.println("New Personne inserted successfully.");

            // Retrieve the generated idPersonne
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // Return the generated idPersonne
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 si insert fail
    }

    // Aidee
    public static void InsertAidee(String nom, String prenom, String adresse, String password){
        int personneID = InsertPersonne(nom, prenom, adresse, password);
        System.out.println(personneID);
        if (personneID != -1){
            String insertStatement = "INSERT INTO PersonneAidee (idPersonneAidee) VALUES (?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertStatement)) {
                stmt.setInt(1, personneID);
                stmt.executeUpdate();
                System.out.println("New Personne aidee inserted successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Benevole
    public static void InsertBenevole(String nom, String prenom, String adresse, String password){
        int personneID = InsertPersonne(nom, prenom, adresse, password);
        System.out.println(personneID);
        if (personneID != -1){
            String insertStatement = "INSERT INTO PersonneBenevole (idPersonneBenevole) VALUES (?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertStatement)) {
                stmt.setInt(1, personneID);
                stmt.executeUpdate();
                System.out.println("New Personne benevole inserted successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Organisation
    public static void InsertOrganisation(String nom, String prenom, String adresse, String password){
        int personneID = InsertPersonne(nom, prenom, adresse, password);
        System.out.println(personneID);
        if (personneID != -1){
            String insertStatement = "INSERT INTO PersonneOrganisation (idPersonneOrganisation) VALUES (?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertStatement)) {
                stmt.setInt(1, personneID);
                stmt.executeUpdate();
                System.out.println("New Personne organisation inserted successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Mission
    public static int InsertDemande(Date date, String intitule, PersonneAidee aidee){
        String insertStatement = "INSERT INTO Demande (date, intitule, aidee) VALUES (?, ?, ?)";

        // Use Statement.RETURN_GENERATED_KEYS to retrieve the auto-incremented key
        try (PreparedStatement stmt = conn.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS)) {
            // Set parameters for the query
            stmt.setString(1, String.valueOf(date));
            stmt.setString(2, intitule);
            stmt.setString(3, String.valueOf(aidee));

            // Execute the update
            stmt.executeUpdate();
            System.out.println("New demande inserted successfully.");

            // Retrieve the generated idPersonne
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // Return the generated idPersonne
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 si insert fail
    }

    /*
    ================================================
                    LES SELECTS
    ================================================
     */

    // Personne - on récupère la liste des personnes
    public static List<List<String>> ListePersonne(){
        List<List<String>> Liste = new ArrayList<>();

        // Query to retrieve all necessary fields in a single query for better efficiency
        String query = "SELECT idPersonne, nom, prenom, adresse, password FROM Personne ORDER BY idPersonne;";

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Iterate over the result set and add each person's details to the list
            while (resultSet.next()) {
                List<String> personne = new ArrayList<>();


                personne.add(resultSet.getString("idPersonne"));
                personne.add(resultSet.getString("nom"));
                personne.add(resultSet.getString("prenom"));
                personne.add(resultSet.getString("adresse"));
                personne.add(resultSet.getString("password"));

                Liste.add(personne);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return personList;
    }


    }
}
