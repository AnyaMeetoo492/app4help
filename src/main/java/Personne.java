public class Personne {
    private String nom;
    private String prenom;
    private String adresse;

    public Personne(String nom, String prenom, String adresse){
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
