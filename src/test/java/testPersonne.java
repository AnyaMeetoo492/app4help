import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class testPersonne {

    static Personne tester ;
    @BeforeClass
    public static void testSetup() {
        tester = new Personne("Meetoo", "Anya", "INSA");
    }

    // Le constructeur
    @Test
    public void testEntree() {
        try {new Personne(null, null, null); fail();}
        catch (Exception e){assertEquals("Teste Entrée : TOUT NULL","Aucun champ ne peut être vide", e.getMessage());}

        try {new Personne(null, "Anya", "INSA"); fail();}
        catch (Exception e){assertEquals("Teste Entrée : Nom NULL","Le nom ne peut pas être nul ou vide", e.getMessage());}

        try {new Personne("Meetoo", null, "INSA"); fail();}
        catch (Exception e){assertEquals("Teste Entrée : Prénom NULL","Le prénom ne peut pas être nul ou vide", e.getMessage());}

        try {new Personne("Meetoo", "Anya", null); fail();}
        catch (Exception e){assertEquals("Teste Entrée : Adresse NULL","L'adresse ne peut pas être nulle ou vide", e.getMessage());}
    }

    // GET NOM
    @Test
    public void testGetNom(){
        assertEquals("Teste Get Nom : OK","Meetoo", tester.getNom());
    }


}
