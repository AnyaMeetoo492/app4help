import java.sql.*;

// Classe pour
// - se connecter à la base de donnée
// - éxecuter des requêtes (ajout, modifier, enlever)
public class DatabaseHandler {

    // Les paramètres pour accéder à la base de donnée
    // jdbc:mysql pour accéder à travers mysql
    private static final String DB_URL = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_030";
    private static final String USER = "projet_gei_030";
    private static final String PASSWORD = "Shu4Rahm";

    // Établir la connection
    public static Connection connect() {
        Connection conn = null;
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
        return conn;
    }

    // Éxecuter une requête

    // Ajout d'une ligne dans tableau
    // Personne
//    public int InsertPersonne(Connection conn, Personne personne){
//        String insertStatement = "INSERT INTO Personne (nom, prenom, adresse) VALUES " +
//                "('" + personne.getNom() + "', '" +
//                personne.getPrenom() + "', '" +
//                personne.getAdresse() + "')";
//        try (PreparedStatement pstmt = conn.prepareStatement(insertStatement,PreparedStatement.RETURN_GENERATED_KEYS)) {
//            pstmt.executeUpdate(insertStatement);
//            System.out.println("New Personne inserted successfully.");
//
//            // Retrieve the generated idPersonne
//            try (ResultSet rs = pstmt.getGeneratedKeys()) {
//                if (rs.next()) {
//                    return rs.getInt(1); // Return the generated idPersonne
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return -1;
//    }

    // Insert a Personne and return the generated idPersonne
    public int InsertPersonne(Connection conn, Personne personne) {
        String insertStatement = "INSERT INTO Personne (nom, prenom, adresse) VALUES (?, ?, ?)";

        // Use Statement.RETURN_GENERATED_KEYS to retrieve the auto-incremented key
        try (PreparedStatement stmt = conn.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS)) {
            // Set parameters for the query
            stmt.setString(1, personne.getNom());
            stmt.setString(2, personne.getPrenom());
            stmt.setString(3, personne.getAdresse());

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
        return -1; // Return -1 if the insert failed
    }

    // Aidee
    public void InsertAidee(Connection conn, PersonneAidee aidee){
        int personneID = InsertPersonne(conn, aidee);
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
    public void InsertBenevole(Connection conn, PersonneBenevole benevole){
        int personneID = InsertPersonne(conn, benevole);
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
    public void InsertOrganisation(Connection conn, PersonneOrganisation organisation){
        int personneID = InsertPersonne(conn, organisation);
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
}
