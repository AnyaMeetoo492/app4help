import java.util.Date;

public class PersonneAidee extends Personne{
    public PersonneAidee(String nom, String prenom, String adresse, String password) {
        super(nom, prenom, adresse, password);
        // Insérer dans la table PersonneAidee
        DatabaseHandler.InsertAidee(nom, prenom, adresse,password);
    }

    public void CreerDemande(Date dateDemande, String intitule, PersonneAidee aidee){
        // Créer nouvelle demande et automatiquement rajoute dans table Demande
        Demande demande = new Demande(dateDemande, intitule, aidee);
    }
}
