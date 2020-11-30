package FFSSM;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Henry Camille.
 */
public class FFSSMJUnitTest {
    private Plongeur p;
    private Licence l1;
    private Licence l2;
    private Club club1; // L'objet à tester
    private Moniteur GeorgesHund;
    private Site site1;
    private Plongee plongee1;
    private Plongee plongee2;

    @BeforeEach
    public void setUp() {
        // Initialiser les objets utilisés dans les tests
        GeorgesHund = new Moniteur("2486795130756", "Georges", "Hund", "rue Jean-Jaures", "0635684951", LocalDate.of(1976, 8, 12), 4, 562);
        club1 = new Club(GeorgesHund, "Surf Paradise", "0384562496");
        p = new Plongeur("0648951237025", "Robert", "Fabien", "avenue Soult", "0634586184", LocalDate.of(1987, 5, 6), 7);
        Licence l1;
        l1 = new Licence(GeorgesHund,"6543", LocalDate.of(2020,06,15), club1);
        site1= new Site("PremierSite", "DetailDuPremierSite");
        plongee1= new Plongee (site1, GeorgesHund, LocalDate.of(2020,11,27), 2500, 2);
        plongee2= new Plongee (site1, GeorgesHund, LocalDate.of(2020,11,30), 5075, 3);
    }
    
    
    
    // Tests classe club :
    
    @Test
    public void TestPlongeeConforme(){
        l2=new Licence(p,"2658", LocalDate.of(2020,02,28), club1);
        plongee1.ajouteParticipant(l2);
        plongee2.ajouteParticipant(l2);
        club1.organisePlongee(plongee1);
        club1.organisePlongee(plongee2);
        assertTrue(club1.plongeesNonConformes().isEmpty(), "Toutes les plongées sont censées être conformes");
    }
    
    @Test
    public void TestPlongeeNonConforme(){
        l2=new Licence(p,"2658", LocalDate.of(2018,02,28), club1);
        plongee1.ajouteParticipant(l2);
        // l2 non valide
        plongee2.ajouteParticipant(l2);
        club1.organisePlongee(plongee1);
        club1.organisePlongee(plongee2);
        Set<Plongee> pltest;
        pltest = new HashSet<>();
        pltest.add(plongee2);
        pltest.add(plongee1);
        assertEquals(pltest, club1.plongeesNonConformes(), "Les plongées ne sont pas conformes.");
    }
    
    
    
    // Tests classe licence :

    @Test
    public void TestLicenceEstValide() {
        l1 = new Licence(GeorgesHund,"6543", LocalDate.of(2020,06,15), club1);
        assertTrue(l1.estValide(LocalDate.of(2020, 11, 30)), "La licence n'est pas valide");
    }

    @Test
    public void TestLicencePasValide() {
        l1 = new Licence(GeorgesHund,"6543", LocalDate.of(2020,06,15), club1);
        assertFalse(l1.estValide(LocalDate.of(2022, 1, 11)), "La licence est valide");
    }

    
    
    // Test classe Moniteur :
    
    

    @Test
    public void TestTerminerEmbauche() {
        GeorgesHund.nouvelleEmbauche(club1, LocalDate.of(2017, 10, 24));
        GeorgesHund.terminerEmbauche(LocalDate.of(2018, 11, 12));
        //on vérifie que l'emploi est bien terminé
        assertTrue(GeorgesHund.emplois().get(GeorgesHund.emplois().size()-1).estTerminee(), "L'emploi n'est pas terminé");
    }


    
    // Tests classe plongée : 
    
    
    @Test
    public void TestEstConforme(){
        // licence valide 
        l2=new Licence(p,"2658", LocalDate.of(2020,02,28), club1);
        plongee1.ajouteParticipant(l2);
        assertTrue(plongee1.estConforme(), "La plongée est conforme.");
    }
    
    @Test
    public void TestPasConforme(){
        // licence invalide 
        l2=new Licence(p,"2658", LocalDate.of(2018,02,28), club1);
        plongee1.ajouteParticipant(l2);
        assertFalse(plongee1.estConforme(), "La plongée n'est pas conforme.");
    }
    
    
    
    
   
}