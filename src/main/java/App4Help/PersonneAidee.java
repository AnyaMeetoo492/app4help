package App4Help;

import java.util.Date;

public class PersonneAidee extends Personne{
    public PersonneAidee(String nom, String prenom, String adresse, String password) {
        super(nom, prenom, adresse, password);
        // Insérer dans la table App4Help.PersonneAidee
        //TODO aled faut dissocier les inserts partout
        //App4Help.DatabaseHandler.InsertAidee(nom, prenom, adresse,password);
    }
    public void CreerDemande(Date dateDemande, String intitule, PersonneAidee aidee){
        // Créer nouvelle demande et automatiquement rajoute dans table App4Help.Demande
        Demande demande = new Demande(dateDemande, intitule, aidee);
    }
}
