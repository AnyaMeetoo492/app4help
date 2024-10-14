import java.util.Date;

public class PersonneAidee extends Personne{
    public PersonneAidee(String nom, String prenom, String adresse) {
        super(nom, prenom, adresse);
        // Insérer dans la table PersonneAidee
        DatabaseHandler.InsertAidee(nom, prenom, adresse);
    }

    public void CreerDemande(Date date, String intitule, PersonneAidee aidee){
        // Créer nouvelle demande et automatiquement rajoute dans table Demande
        Demande demande = new Demande(date, intitule, aidee);
    }
}
