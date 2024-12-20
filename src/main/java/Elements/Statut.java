package Elements;

public enum Statut {
    EN_ATTENTE,//demande pas encore validee ou mission pas encore acceptée par un bénévole
    VALIDEE,//demande validee par une organisation
    NON_REALISEE,//mission acceptée par un bénévole donc en cours de réalisation
    REALISEE,//mission réalisée = réalisation validée par le demandeur
    REJETEE//quand une demande est rejetée
}
