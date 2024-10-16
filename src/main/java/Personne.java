public class Personne {
    private String nom;
    private String prenom;
    private String adresse;

    public Personne(String nom, String prenom, String adresse) {
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

        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
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
}
