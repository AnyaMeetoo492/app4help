import java.util.Date;

public class Demande {
    private Date date;
    private String intitule;
    private PersonneAidee aidee;
    private Statut statut;

    public Demande(Date date, String intitule, PersonneAidee aidee){
        this.date = date;
        this.intitule = intitule;
        this.aidee = aidee;
        this.statut = Statut.EN_ATTENTE;
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
}
