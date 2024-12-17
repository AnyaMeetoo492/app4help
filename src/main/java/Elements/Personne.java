package Elements;
import Database.DatabaseHandler;

public class Personne {
    private String nom;
    private String prenom;
    private String adresse;
    private String password;

    public Personne(String nom, String prenom, String adresse, String password) {
        if (nom == null && prenom == null && adresse == null){
            throw new IllegalArgumentException("Aucun champ ne peut être vide");
        }
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom ne peut pas être nul ou vide");
        }
        if (prenom == null || prenom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le prénom ne peut pas être nul ou vide");
        }
        if (adresse == null || adresse.trim().isEmpty()) {
            throw new IllegalArgumentException("L'adresse ne peut pas être nulle ou vide");
        }
        if (password == null || password.trim().isEmpty()){
            throw new IllegalArgumentException("Le mot de passe ne peut pas être vide");
        }

        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getPassword() {
        return password;
    }
}
