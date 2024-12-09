import java.util.Date;

public class PersonneOrganisation extends Personne{
    public PersonneOrganisation(String nom, String prenom, String adresse, String password) {
        super(nom, prenom, adresse, password);
        DatabaseHandler.InsertOrganisation(nom,prenom,adresse,password);
    }

    public void ValiderDemande(Demande demande){
        demande.setOrganisation(this);
        demande.setStatut(Statut.VALIDEE, "Demande validée par l'organisation");

        // Demander à l'orga de donner la date de la mission et son intitulé
        Date date = null;
        String intitule = null;

        // Créer une nouvelle mission suite à la demande validée
        new Mission(date, intitule, demande.getAidee());
    }
}
