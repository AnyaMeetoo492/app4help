import java.util.Date;

public class Demande {
    private Date date;
    private String intitule;
    private Statut statut;
    private PersonneAidee aidee;
    private PersonneOrganisation organisation = null;

    public Demande(Date date, String intitule, PersonneAidee aidee){
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
        this.statut = statut;
    }

    public void setOrganisation(PersonneOrganisation organisation) {
        this.organisation = organisation;
    }
    public PersonneOrganisation getOrganisation(){
        return this.organisation;
    }
}
