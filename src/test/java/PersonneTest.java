import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PersonneTest {

    @Test
    public void testPersonneValide() {
        // Créer un objet Personne valide
        Personne personne = new Personne("Dupont", "Jean", "12 rue Principale");

        // Vérifier que les champs sont correctement définis
        assertEquals("Dupont", personne.getNom());
    }

    @Test
    public void testNomNullLanceException() {
        // Vérifier qu'une IllegalArgumentException est lancée si le nom est null
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Personne(null, "Jean", "12 rue Principale"));

        // Vérifier le message d'exception correct
        assertEquals("Le nom ne peut pas être nul ou vide", exception.getMessage());
    }

    @Test
    public void testPrenomNullLanceException() {
        // Vérifier qu'une IllegalArgumentException est lancée si le prénom est null
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Personne("Dupont", null, "12 rue Principale"));

        // Vérifier le message d'exception correct
        assertEquals("Le prénom ne peut pas être nul ou vide", exception.getMessage());
    }

    @Test
    public void testAdresseNullLanceException() {
        // Vérifier qu'une IllegalArgumentException est lancée si l'adresse est null
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Personne("Dupont", "Jean", null));

        // Vérifier le message d'exception correct
        assertEquals("L'adresse ne peut pas être nulle ou vide", exception.getMessage());
    }

    @Test
    public void testNomVideLanceException() {
        // Vérifier qu'une IllegalArgumentException est lancée si le nom est vide
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Personne("", "Jean", "12 rue Principale"));

        // Vérifier le message d'exception correct
        assertEquals("Le nom ne peut pas être nul ou vide", exception.getMessage());
    }

    @Test
    public void testPrenomVideLanceException() {
        // Vérifier qu'une IllegalArgumentException est lancée si le prénom est vide
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Personne("Dupont", "", "12 rue Principale"));

        // Vérifier le message d'exception correct
        assertEquals("Le prénom ne peut pas être nul ou vide", exception.getMessage());
    }

    @Test
    public void testAdresseVideLanceException() {
        // Vérifier qu'une IllegalArgumentException est lancée si l'adresse est vide
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Personne("Dupont", "Jean", ""));

        // Vérifier le message d'exception correct
        assertEquals("L'adresse ne peut pas être nulle ou vide", exception.getMessage());
    }

    @Test
    public void testTousChampsNulsLanceException() {
        // Vérifier qu'une IllegalArgumentException est lancée si tous les champs sont nuls
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Personne(null, null, null));

        // Vérifier le message d'exception correct
        assertEquals("Aucun champ ne peut être vide", exception.getMessage());
    }
}
