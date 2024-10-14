import java.util.Date;

public class Mission {
    private Date date;
    private String intitule;
    private Statut statut;
    private PersonneAidee aidee;
    private PersonneBenevole benevole = null;
    public Mission(Date date, String intitule, PersonneAidee aidee) {
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

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        if (statut == null) {
            throw new IllegalArgumentException("Le statut ne peut pas être nul");
        }
        this.statut = statut;
    }

    public PersonneAidee getAidee() {
        return aidee;
    }

    public PersonneBenevole getBenevole() {
        return benevole;
    }

    public void setBenevole(PersonneBenevole benevole) {
        if (benevole == null) {
            throw new IllegalArgumentException("Le bénévole ne peut pas être nul");
        }
        this.benevole = benevole;
    }
}
