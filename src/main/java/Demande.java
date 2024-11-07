import java.util.Date;

public class Demande {
    private Date dateDemande;
    private Date dateValidation;
    private String intitule;
    private Statut statut;
    private PersonneAidee aidee;
    private PersonneOrganisation organisation;

    public Demande(Date dateDemande, String intitule, PersonneAidee aidee) {
        if (dateDemande == null) {
            throw new IllegalArgumentException("La date de demande ne peut pas être nulle");
        }
        if (intitule == null || intitule.trim().isEmpty()) {
            throw new IllegalArgumentException("L'intitulé ne peut pas être nul ou vide");
        }
        if (aidee == null) {
            throw new IllegalArgumentException("La personne aidée ne peut pas être nulle");
        }

        this.dateDemande = dateDemande;
        this.dateValidation = null;
        this.intitule = intitule;
        this.statut = Statut.EN_ATTENTE;
        this.aidee = aidee;
        this.organisation = null;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public Date getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(Date validation){
        if (dateValidation == null) {
            throw new IllegalArgumentException("La date de validation ne peut pas être nulle");
        }
        this.dateValidation = validation;
    }
    public String getIntitule() {
        return intitule;
    }

    public PersonneAidee getAidee() {
        return aidee;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        if (statut == null) {
            throw new IllegalArgumentException("Le statut ne peut pas être nul");
        }
        this.statut = statut;
    }

    public void setOrganisation(PersonneOrganisation organisation) {
        if (organisation == null) {
            throw new IllegalArgumentException("L'organisation ne peut pas être nulle");
        }
        this.organisation = organisation;
    }
    public PersonneOrganisation getOrganisation(){
        return this.organisation;
    }
}
