import java.util.Date;

public class Mission {
    private Date date;
    private String intitule;
    private Statut statut;
    private PersonneAidee aidee;
    private PersonneBenevole benevole = null;
    public Mission(Date date, String intitule, PersonneAidee aidee){
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

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public PersonneAidee getAidee() {
        return aidee;
    }

    public PersonneBenevole getBenevole() {
        return benevole;
    }

    public void setBenevole(PersonneBenevole benevole) {
        this.benevole = benevole;
    }
}
