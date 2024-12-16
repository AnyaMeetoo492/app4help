import javax.lang.model.element.NestingKind;
import java.util.Date;

public class Demande {

    private int idDemande = -1;
    private Date dateDemande;
    private Date dateValidation;
    private String intitule;
    private Statut statut;
    private PersonneAidee aidee;
    private PersonneOrganisation organisation;
    private String motif;

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
        this.motif = null;

        this.idDemande = DatabaseHandler.InsertDemande(this.dateDemande, this.dateValidation, this.intitule, this.statut, this.aidee, this.organisation, this.motif);
    }

    public int getIdDemande(){return idDemande;}
    public Date getDateDemande() {
        return dateDemande;
    }
    public Date getDateValidation() {
        return dateValidation;
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
    public PersonneOrganisation getOrganisation(){
        return this.organisation;
    }

    public void setDateValidation(Date validation){
        if (dateValidation == null) {
            throw new IllegalArgumentException("La date de validation ne peut pas être nulle");
        }
        this.dateValidation = validation;
        DatabaseHandler.ChangeDateValidationDemande(idDemande,dateValidation);
    }
    public void setStatut(Statut statut, String motif) {
        if (statut == null) {
            throw new IllegalArgumentException("Le statut ne peut pas être nul");
        }
        this.statut = statut;
        DatabaseHandler.ChangeStatutDemande(idDemande,statut);

        if (statut==Statut.REJETEE){
            setMotif(motif);
        }
    }
    public void setOrganisation(PersonneOrganisation organisation) {
        if (organisation == null) {
            throw new IllegalArgumentException("L'organisation ne peut pas être nulle");
        }
        this.organisation = organisation;
        DatabaseHandler.ChangeOrganisationDemande(idDemande,organisation);
    }
    public void setMotif(String motif) {
        if (motif == null || motif.trim().isEmpty()) {
            throw new IllegalArgumentException("Le motif ne peut pas être nul ou vide");
        }
        this.motif = motif;
        DatabaseHandler.ChangeMotifDemande(idDemande,motif);
    }

}
