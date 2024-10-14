import java.util.Date;

public class Demande {
    private Date date;
    private String intitule;
    private Statut statut;
    private PersonneAidee aidee;
    private PersonneOrganisation organisation = null;

    public Demande(Date date, String intitule, PersonneAidee aidee) {
        if (date == null) {
            throw new IllegalArgumentException("La date ne peut pas être nulle");
        }
        if (intitule == null || intitule.trim().isEmpty()) {
            throw new IllegalArgumentException("L'intitulé ne peut pas être nul ou vide");
        }
        if (aidee == null) {
            throw new IllegalArgumentException("La personne aidée ne peut pas être nulle");
        }

        this.date = date;
        this.intitule = intitule;
        this.statut = Statut.EN_ATTENTE;
        this.aidee = aidee;
    }

    public Date getDate() {
        return date;
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
