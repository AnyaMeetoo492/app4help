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
    }

    /*
    ================================================
                    LES INSERTIONS
    ================================================
     */

    // Personne
    public static int InsertPersonne(String nom, String prenom, String adresse, String password) {

        List<List<String>> listePersonne = ListePersonne();
        if (!listePersonne.isEmpty()) {
            for (List<String> personne : listePersonne) {
                if (personne.get(1).equals(nom) && personne.get(2).equals(prenom) && personne.get(3).equals(adresse)) {
                    System.out.println("Personne déjà existante.");
                    return -1;
                }
            }
        }

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

    public static String GetIDPersonne(Personne personne) {
        String id = null;
        String query = "SELECT idPersonne FROM Personne WHERE nom = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            // Set the parameter for the query
            preparedStatement.setString(1, personne.getNom());

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process the result
            while (resultSet.next()) {
                id = resultSet.getString("idPersonne");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    // Aidee
    public static void InsertAidee(String nom, String prenom, String adresse, String password) {
        int personneID = InsertPersonne(nom, prenom, adresse, password);
        System.out.println(personneID);
        if (personneID != -1) {
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
    public static void InsertBenevole(String nom, String prenom, String adresse, String password) {
        int personneID = InsertPersonne(nom, prenom, adresse, password);
        System.out.println(personneID);
        if (personneID != -1) {
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
    public static void InsertOrganisation(String nom, String prenom, String adresse, String password) {
        int personneID = InsertPersonne(nom, prenom, adresse, password);
        System.out.println(personneID);
        if (personneID != -1) {
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

    // Demande
    public static int InsertDemande(Date dateDemande, Date dateValidation, String intitule, Statut statut, PersonneAidee aidee, PersonneOrganisation organisation, String motif) {
        String idAidee = null;
        String idOrganisation = null;
        java.sql.Date dateVal = null;

        // il faut récup l'id de Aidee et Organisation pour pouvoir ajouter dans le tableau
        if (aidee != null) {
            idAidee = GetIDPersonne(aidee);
        }

        if (organisation != null) {
            idOrganisation = GetIDPersonne(organisation);
        }

        if (dateValidation != null) {
            dateVal = new java.sql.Date(dateValidation.getTime());
        }

        String insertStatement = "INSERT INTO Demande (dateDemande, dateValidation, intitule, statut, idAidee, idOrganisation, motif) VALUES (?, ?, ?, ?, ?, ?, ?)";

        // Use Statement.RETURN_GENERATED_KEYS to retrieve the auto-incremented key
        try (PreparedStatement stmt = conn.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDate(1, new java.sql.Date(dateDemande.getTime()));
            stmt.setDate(2, dateVal);
            stmt.setString(3, intitule);
            stmt.setString(4, statut.toString());
            stmt.setString(5, idAidee);
            stmt.setString(6, idOrganisation);
            stmt.setString(7, motif);


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

    public static int ChangeDateValidationDemande(int idDemande, Date dateValidation) {
        String updateStatement = "UPDATE Demande SET dateValidation = ? WHERE idDemande = ?";

        try (PreparedStatement stmt = conn.prepareStatement(updateStatement)) {
            java.sql.Date sqlDateValidation = null;
            if (dateValidation != null) {
                sqlDateValidation = new java.sql.Date(dateValidation.getTime());
            }

            stmt.setDate(1, sqlDateValidation);
            stmt.setInt(2, idDemande);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Date de validation mise à jour avec succès.");
                return rowsUpdated;
            } else {
                System.out.println("Aucune ligne n'a été mise à jour. Veuillez vérifier l'idDemande.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static int ChangeStatutDemande(int idDemande, Statut statut) {
        String updateStatement = "UPDATE Demande SET statut = ? WHERE idDemande = ?";

        try (PreparedStatement stmt = conn.prepareStatement(updateStatement)) {
            String statutValue = null;
            if (statut != null) {
                statutValue = statut.toString();
            }
            stmt.setString(1, statutValue);
            stmt.setInt(2, idDemande);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Statut de la demande mis à jour avec succès.");
                return rowsUpdated;
            } else {
                System.out.println("Aucune ligne n'a été mise à jour. Veuillez vérifier l'idDemande.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static int ChangeOrganisationDemande(int idDemande, PersonneOrganisation organisation) {
        String updateStatement = "UPDATE Demande SET idOrganisation = ? WHERE idDemande = ?";

        try (PreparedStatement stmt = conn.prepareStatement(updateStatement)) {
            String idOrganisation = null;
            if (organisation != null) {
                idOrganisation = GetIDPersonne(organisation);
            }
            stmt.setString(1, idOrganisation);
            stmt.setInt(2, idDemande);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Organisation de la demande mise à jour avec succès.");
                return rowsUpdated;
            } else {
                System.out.println("Aucune ligne n'a été mise à jour. Veuillez vérifier l'idDemande.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    // Mission
    public static int InsertMission(Date dateDebut, Date dateFin, String intitule, Statut statut, PersonneAidee aidee, PersonneBenevole benevole) {
        String idAidee = null;
        String idBenevole = null;
        java.sql.Date sqlDateDebut = null;
        java.sql.Date sqlDateFin = null;

        if (aidee != null) {
            idAidee = GetIDPersonne(aidee);
        }

        if (benevole != null) {
            idBenevole = GetIDPersonne(benevole);
        }

        if (dateDebut != null) {
            sqlDateDebut = new java.sql.Date(dateDebut.getTime());
        }

        if (dateFin != null) {
            sqlDateFin = new java.sql.Date(dateFin.getTime());
        }

        String insertStatement = "INSERT INTO Mission (dateDebut, dateFin, intitule, statut, idAidee, idBenevole) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDate(1, sqlDateDebut);
            stmt.setDate(2, sqlDateFin);
            stmt.setString(3, intitule);
            stmt.setString(4, statut.toString());
            stmt.setString(5, idAidee);
            stmt.setString(6, idBenevole);

            stmt.executeUpdate();
            System.out.println("Nouvelle mission insérée avec succès.");

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static int ChangeDateFinMission(int idMission, Date dateFin) {
        String updateStatement = "UPDATE Mission SET dateFin = ? WHERE idMission = ?";

        try (PreparedStatement stmt = conn.prepareStatement(updateStatement)) {
            java.sql.Date sqlDateFin = null;
            if (dateFin != null) {
                sqlDateFin = new java.sql.Date(dateFin.getTime());
            }

            stmt.setDate(1, sqlDateFin);
            stmt.setInt(2, idMission);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Date de fin de mission mise à jour avec succès.");
                return rowsUpdated;
            } else {
                System.out.println("Aucune ligne n'a été mise à jour. Veuillez vérifier l'idMission.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static int ChangeStatutMission(int idMission, Statut statut) {
        String updateStatement = "UPDATE Mission SET statut = ? WHERE idMission = ?";

        try (PreparedStatement stmt = conn.prepareStatement(updateStatement)) {
            String statutValue = null;
            if (statut != null) {
                statutValue = statut.toString();
            }

            stmt.setString(1, statutValue);
            stmt.setInt(2, idMission);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Statut de la mission mis à jour avec succès.");
                return rowsUpdated;
            } else {
                System.out.println("Aucune ligne n'a été mise à jour. Veuillez vérifier l'idMission.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static int ChangeBenevoleMission(int idMission, PersonneBenevole benevole) {
        String updateStatement = "UPDATE Mission SET idBenevole = ? WHERE idMission = ?";

        try (PreparedStatement stmt = conn.prepareStatement(updateStatement)) {
            String idBenevole = null;
            if (benevole != null) {
                idBenevole = GetIDPersonne(benevole);
            }

            stmt.setString(1, idBenevole);
            stmt.setInt(2, idMission);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Bénévole de la mission mis à jour avec succès.");
                return rowsUpdated;
            } else {
                System.out.println("Aucune ligne n'a été mise à jour. Veuillez vérifier l'idMission.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    /*
    ================================================
                    LES SELECTS
    ================================================
     */

    // Personne - on récupère la liste des personnes
    public static List<List<String>> ListePersonne() {
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

        return Liste;
    }
    //TODO : boolean isSeeker(Personne user); boolean isOrganisation(Personne user); boolean isVolunteer(Personne user);

    public static List<List<String>> ListePersonneChoix(boolean showIdPersonne, boolean showNom, boolean showPrenom, boolean showAdresse, boolean showPassword) {
        List<List<String>> personList = new ArrayList<>();
        List<String> selectedColumns = new ArrayList<>();

        // Add selected columns based on flags
        if (showIdPersonne) selectedColumns.add("idPersonne");
        if (showNom) selectedColumns.add("nom");
        if (showPrenom) selectedColumns.add("prenom");
        if (showAdresse) selectedColumns.add("adresse");
        if (showPassword) selectedColumns.add("password");

        // Build the query dynamically
        String query = "SELECT " + String.join(", ", selectedColumns) + " FROM Personne ORDER BY idPersonne;";
        System.out.println(query);

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Process the result set
            while (resultSet.next()) {
                List<String> personDetails = new ArrayList<>();

                for (String column : selectedColumns) {
                    personDetails.add(resultSet.getString(column));
                }

                personList.add(personDetails);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return personList;
    }

    public static boolean isInDatabase(String nom, String prenom, String password) {
        List<List<String>> listePersonne = ListePersonne();
        if (!listePersonne.isEmpty()) {
            for (List<String> p : listePersonne) {
                if (p.get(1).equals(nom) && p.get(2).equals(prenom) && p.get(3).equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Personne getPersonneWithLogin(String nom, String prenom, String password) {
        List<List<String>> listePersonne = ListePersonne();
        if (!listePersonne.isEmpty()) {
            for (List<String> p : listePersonne) {
                if (p.get(1).equals(nom) && p.get(2).equals(prenom) && p.get(4).equals(password)) {
                    return new Personne(p.get(1), p.get(2), p.get(3), p.get(4));
                }
            }
        }
        return null;
    }

    // Aidee - on récupère la liste des personnes aidee
    public static List<List<String>> ListeAidee() {
        List<List<String>> Liste = new ArrayList<>();

        // Query to retrieve all necessary fields in a single query for better efficiency
        String query = "SELECT idPersonne, nom, prenom, adresse, password " +
                "FROM Personne " +
                "INNER JOIN PersonneAidee ON Personne.idPersonne=PersonneAidee.idPersonneAidee " +
                "ORDER BY idPersonne;";

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

        return Liste;
    }

    public static List<List<String>> ListeAideeChoix(boolean showIdPersonne, boolean showNom, boolean showPrenom, boolean showAdresse, boolean showPassword) {
        List<List<String>> liste = new ArrayList<>();
        List<String> selectedColumns = new ArrayList<>();

        // Add selected columns based on flags
        if (showIdPersonne) selectedColumns.add("idPersonne");
        if (showNom) selectedColumns.add("nom");
        if (showPrenom) selectedColumns.add("prenom");
        if (showAdresse) selectedColumns.add("adresse");
        if (showPassword) selectedColumns.add("password");

        // If no columns are selected, return an empty list
        if (selectedColumns.isEmpty()) {
            System.out.println("No columns selected.");
            return liste;
        }

        // Build the query dynamically
        String query = "SELECT " + String.join(", ", selectedColumns) +
                " FROM Personne " +
                "INNER JOIN PersonneAidee ON Personne.idPersonne = PersonneAidee.idPersonneAidee " +
                "ORDER BY idPersonne;";
        System.out.println(query);

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Process the result set
            while (resultSet.next()) {
                List<String> personne = new ArrayList<>();

                for (String column : selectedColumns) {
                    personne.add(resultSet.getString(column));
                }

                liste.add(personne);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return liste;
    }

    // Benevole - on récupère la liste des personnes benevole
    public static List<List<String>> ListeBenevole() {
        List<List<String>> Liste = new ArrayList<>();

        // Query to retrieve all necessary fields in a single query for better efficiency
        String query = "SELECT idPersonne, nom, prenom, adresse, password " +
                "FROM Personne " +
                "INNER JOIN PersonneBenevole ON Personne.idPersonne=PersonneBenevole.idPersonneBenevole " +
                "ORDER BY idPersonne;";

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

        return Liste;
    }

    public static List<List<String>> ListeBenevoleChoix(boolean showIdPersonne, boolean showNom, boolean showPrenom, boolean showAdresse, boolean showPassword) {
        List<List<String>> liste = new ArrayList<>();
        List<String> selectedColumns = new ArrayList<>();

        // Add selected columns based on flags
        if (showIdPersonne) selectedColumns.add("idPersonne");
        if (showNom) selectedColumns.add("nom");
        if (showPrenom) selectedColumns.add("prenom");
        if (showAdresse) selectedColumns.add("adresse");
        if (showPassword) selectedColumns.add("password");

        // If no columns are selected, return an empty list
        if (selectedColumns.isEmpty()) {
            System.out.println("No columns selected.");
            return liste;
        }

        // Build the query dynamically
        String query = "SELECT " + String.join(", ", selectedColumns) +
                " FROM Personne " +
                "INNER JOIN PersonneBenevole ON Personne.idPersonne = PersonneBenevole.idPersonneBenevole " +
                "ORDER BY idPersonne;";
        System.out.println(query);

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Process the result set
            while (resultSet.next()) {
                List<String> personne = new ArrayList<>();

                for (String column : selectedColumns) {
                    personne.add(resultSet.getString(column));
                }

                liste.add(personne);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return liste;
    }

    // Organisation - on récupère la liste des personnes
    public static List<List<String>> ListeOrganisation() {
        List<List<String>> Liste = new ArrayList<>();

        // Query to retrieve all necessary fields in a single query for better efficiency
        String query = "SELECT idPersonne, nom, prenom, adresse, password " +
                "FROM Personne " +
                "INNER JOIN PersonneOrganisation ON Personne.idPersonne=PersonneOrganisation.idPersonneOrganisation " +
                "ORDER BY idPersonne;";

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

        return Liste;
    }

    public static List<List<String>> ListeOrganisationChoix(boolean showIdPersonne, boolean showNom, boolean showPrenom, boolean showAdresse, boolean showPassword) {
        List<List<String>> liste = new ArrayList<>();
        List<String> selectedColumns = new ArrayList<>();

        // Add selected columns based on flags
        if (showIdPersonne) selectedColumns.add("idPersonne");
        if (showNom) selectedColumns.add("nom");
        if (showPrenom) selectedColumns.add("prenom");
        if (showAdresse) selectedColumns.add("adresse");
        if (showPassword) selectedColumns.add("password");

        // If no columns are selected, return an empty list
        if (selectedColumns.isEmpty()) {
            System.out.println("No columns selected.");
            return liste;
        }

        // Build the query dynamically
        String query = "SELECT " + String.join(", ", selectedColumns) +
                " FROM Personne " +
                "INNER JOIN PersonneOrganisation ON Personne.idPersonne = PersonneOrganisation.idPersonneOrganisation " +
                "ORDER BY idPersonne;";
        System.out.println(query);

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Process the result set
            while (resultSet.next()) {
                List<String> personne = new ArrayList<>();

                for (String column : selectedColumns) {
                    personne.add(resultSet.getString(column));
                }

                liste.add(personne);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return liste;
    }

    // Demande
    public static List<List<String>> ListeDemande() {
        List<List<String>> Liste = new ArrayList<>();

        String query = "SELECT d.idDemande, d.dateDemande, d.dateValidation, d.intitule, d.statut, a.nom AS nomAidee, a.prenom AS prenomAidee, o.nom AS nomOrganisation, o.prenom AS prenomOrganisation, d.motif " +
                "FROM Demande d " +
                "LEFT JOIN PersonneAidee pa ON pa.idPersonneAidee = d.idAidee " +
                "LEFT JOIN PersonneOrganisation po ON po.idPersonneOrganisation = d.idOrganisation " +
                "LEFT JOIN Personne a ON a.idPersonne = pa.idPersonneAidee " +
                "LEFT JOIN Personne o ON o.idPersonne = po.idPersonneOrganisation " +
                "ORDER BY d.idDemande;";

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Iterate over the result set and add each person's details to the list
            while (resultSet.next()) {
                List<String> demande = new ArrayList<>();


                demande.add(resultSet.getString("dateDemande"));
                demande.add(resultSet.getString("idDemande"));
                demande.add(resultSet.getString("dateValidation"));
                demande.add(resultSet.getString("intitule"));
                demande.add(resultSet.getString("statut"));
                demande.add(resultSet.getString("nomAidee"));
                demande.add(resultSet.getString("prenomAidee"));
                demande.add(resultSet.getString("nomOrganisation"));
                demande.add(resultSet.getString("prenomOrganisation"));
                demande.add(resultSet.getString("motif"));

                Liste.add(demande);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Liste;
    }
    public static List<List<String>> ListeDemandeChoix(boolean showIdDemande, boolean showDateDemande, boolean showDateValidation, boolean showIntitule, boolean showStatut, boolean showNomAidee, boolean showPrenomAidee, boolean showNomOrganisation, boolean showPrenomOrganisation, boolean showMotif) {

        List<List<String>> liste = new ArrayList<>();
        List<String> colonnesSelectionnees = new ArrayList<>();

        if (showIdDemande) colonnesSelectionnees.add("d.idDemande");
        if (showDateDemande) colonnesSelectionnees.add("d.dateDemande");
        if (showDateValidation) colonnesSelectionnees.add("d.dateValidation");
        if (showIntitule) colonnesSelectionnees.add("d.intitule");
        if (showStatut) colonnesSelectionnees.add("d.statut");
        if (showNomAidee) colonnesSelectionnees.add("a.nom AS nomAidee");
        if (showPrenomAidee) colonnesSelectionnees.add("a.prenom AS prenomAidee");
        if (showNomOrganisation) colonnesSelectionnees.add("o.nom AS nomOrganisation");
        if (showPrenomOrganisation) colonnesSelectionnees.add("o.prenom AS prenomOrganisation");
        if (showMotif) colonnesSelectionnees.add("d.motif");

        if (colonnesSelectionnees.isEmpty()) {
            System.out.println("Aucune colonne sélectionnée.");
            return liste;
        }

        String query = "SELECT " + String.join(", ", colonnesSelectionnees) +
                " FROM Demande d " +
                "LEFT JOIN PersonneAidee pa ON pa.idPersonneAidee = d.idAidee " +
                "LEFT JOIN PersonneOrganisation po ON po.idPersonneOrganisation = d.idOrganisation " +
                "LEFT JOIN Personne a ON a.idPersonne = pa.idPersonneAidee " +
                "LEFT JOIN Personne o ON o.idPersonne = po.idPersonneOrganisation " +
                "ORDER BY d.idDemande;";
        System.out.println(query);

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                List<String> demande = new ArrayList<>();

                for (String colonne : colonnesSelectionnees) {
                    String nomColonne = colonne.contains(" AS ") ? colonne.split(" AS ")[1] : colonne;
                    demande.add(resultSet.getString(nomColonne));
                }

                liste.add(demande);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return liste;
    }

    // Mission
    public static List<List<String>> ListeMission() {
        List<List<String>> Liste = new ArrayList<>();

        String query = "SELECT m.idMission, m.dateDebut, m.dateFin, m.intitule, m.statut, a.nom AS nomAidee, a.prenom AS prenomAidee, b.nom AS nomBenevole, b.prenom AS prenomBenevole " +
                "FROM Mission m " +
                "LEFT JOIN PersonneAidee pa ON pa.idPersonneAidee = m.idAidee " +
                "LEFT JOIN PersonneBenevole pb ON pb.idPersonneBenevole = m.idBenevole " +
                "LEFT JOIN Personne a ON a.idPersonne = pa.idPersonneAidee " +
                "LEFT JOIN Personne b ON b.idPersonne = pb.idPersonneBenevole " +
                "ORDER BY m.idMission;";

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                List<String> mission = new ArrayList<>();

                mission.add(resultSet.getString("idMission"));
                mission.add(resultSet.getString("dateDebut"));
                mission.add(resultSet.getString("dateFin"));
                mission.add(resultSet.getString("intitule"));
                mission.add(resultSet.getString("statut"));
                mission.add(resultSet.getString("nomAidee"));
                mission.add(resultSet.getString("prenomAidee"));
                mission.add(resultSet.getString("nomBenevole"));
                mission.add(resultSet.getString("prenomBenevole"));

                Liste.add(mission);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Liste;
    }
    public static List<List<String>> ListeMissionChoix (boolean showIdMission, boolean showDateDebut, boolean showDateFin, boolean showIntitule, boolean showStatut, boolean showNomAidee, boolean showPrenomAidee, boolean showNomBenevole, boolean showPrenomBenevole){

        List<List<String>> liste = new ArrayList<>();
        List<String> colonnesSelectionnees = new ArrayList<>();

        if (showIdMission) colonnesSelectionnees.add("m.idMission");
        if (showDateDebut) colonnesSelectionnees.add("m.dateDebut");
        if (showDateFin) colonnesSelectionnees.add("m.dateFin");
        if (showIntitule) colonnesSelectionnees.add("m.intitule");
        if (showStatut) colonnesSelectionnees.add("m.statut");
        if (showNomAidee) colonnesSelectionnees.add("a.nom AS nomAidee");
        if (showPrenomAidee) colonnesSelectionnees.add("a.prenom AS prenomAidee");
        if (showNomBenevole) colonnesSelectionnees.add("b.nom AS nomBenevole");
        if (showPrenomBenevole) colonnesSelectionnees.add("b.prenom AS prenomBenevole");

        if (colonnesSelectionnees.isEmpty()) {
            System.out.println("Aucune colonne sélectionnée.");
            return liste;
        }

        String query = "SELECT " + String.join(", ", colonnesSelectionnees) +
                " FROM Mission m " +
                "LEFT JOIN PersonneAidee pa ON pa.idPersonneAidee = m.idAidee " +
                "LEFT JOIN PersonneBenevole pb ON pb.idPersonneBenevole = m.idBenevole " +
                "LEFT JOIN Personne a ON a.idPersonne = pa.idPersonneAidee " +
                "LEFT JOIN Personne b ON b.idPersonne = pb.idPersonneBenevole " +
                "ORDER BY m.idMission;";
        System.out.println(query);

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                List<String> mission = new ArrayList<>();

                for (String colonne : colonnesSelectionnees) {
                    String nomColonne = colonne.contains(" AS ") ? colonne.split(" AS ")[1] : colonne;
                    mission.add(resultSet.getString(nomColonne));
                }

                liste.add(mission);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return liste;
    }
}
