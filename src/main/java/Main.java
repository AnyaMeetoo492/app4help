import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        // Connection à la base de donnée
        DatabaseHandler database = new DatabaseHandler();
        Connection conn = database.connect();
    }
}