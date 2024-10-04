import java.util.Date;

public class PersonneAidee extends Personne{
    public PersonneAidee(String nom, String prenom, String adresse) {
        super(nom, prenom, adresse);
    }

    public void CreerDemande(Date date, String intitule, PersonneAidee aidee){
        Demande demande = new Demande(date, intitule, aidee);
    }
}
