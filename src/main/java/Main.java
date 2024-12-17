import Database.DatabaseHandler;

public class Main {
    public static void main(String[] args) {

        // Connection à la base de donnée
        DatabaseHandler database = new DatabaseHandler();
        database.connect();

//        LocalDate spdate = LocalDate.of(2024, 11, 15);
//        Date date = Date.valueOf(spdate);
//        App4Help.Demande demande = new App4Help.Demande(date, "çA marche", new App4Help.PersonneAidee("Anya333", "Meetoo333", "6 Allee", "333"));

        // Ajout d'une personne
        //List<List<String>> ListePersonne = App4Help.Database.DatabaseHandler.ListePersonneChoix(true,true, true, true, false);
        //ist<List<String>> ListePersonne = App4Help.Database.DatabaseHandler.ListeDemande();
//        for (List<String> personne : ListePersonne){
//            System.out.println(personne);
//        }
        database.InsertPersonne("Dupont", "Louis", "6 rue des champs 31400 Toulouse", "1234");

    }
}
