import java.util.Date;

public class PersonneBenevole extends Personne{
    public PersonneBenevole(String nom, String prenom, String adresse) {
        super(nom, prenom, adresse);
    }
    public void ValiderMission(Mission mission){
        mission.setBenevole(this);
        mission.setStatut(Statut.VALIDEE);
    }

    public void MissionReussite(Mission mission){
        mission.setStatut(Statut.VALIDEE);
    }
}
