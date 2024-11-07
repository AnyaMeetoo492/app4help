import java.util.Date;

public class Mission {
    private Date dateDebut;
    private Date dateFin;
    private String intitule;
    private Statut statut;
    private PersonneAidee aidee;
    private PersonneBenevole benevole;
    public Mission(Date dateDebut, String intitule, PersonneAidee aidee) {
        if (dateDebut == null) {
            throw new IllegalArgumentException("La date de début ne peut pas être nulle");
        }
        if (intitule == null || intitule.trim().isEmpty()) {
            throw new IllegalArgumentException("L'intitulé ne peut pas être nul ou vide");
        }
        if (aidee == null) {
            throw new IllegalArgumentException("La personne aidée ne peut pas être nulle");
        }
        this.dateDebut = dateDebut;
        this.dateFin = null;
        this.intitule = intitule;
        this.statut = Statut.EN_ATTENTE;
        this.aidee = aidee;
        this.benevole = null;
    }

    public Date getDateDebut() {
        return dateDebut;
    }
    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date fin){
        if (fin == null) {
            throw new IllegalArgumentException("La date de fin ne peut pas être nulle");
        }
        this.dateFin = fin;
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
