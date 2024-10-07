import java.util.Date;

public class PersonneOrganisation extends Personne{
    public PersonneOrganisation(String nom, String prenom, String adresse) {
        super(nom, prenom, adresse);
        DatabaseHandler.InsertOrganisation(nom,prenom,adresse);
    }

    public void ValiderDemande(Demande demande){
        demande.setOrganisation(this);
        demande.setStatut(Statut.VALIDEE);

        // Demander à l'orga de donner la date de la mission et son intitulé
        Date date = null;
        String intitule = null;

        // Créer une nouvelle mission suite à la demande validée
        new Mission(date, intitule, demande.getAidee());
    }
}
