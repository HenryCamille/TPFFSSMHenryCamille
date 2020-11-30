package FFSSM;
        
import java.time.LocalDate;

import java.util.*;


public class Plongeur extends Personne{
    
    private int numero;
    
    public final List<Licence> licences = new LinkedList<>();
    

    public Plongeur(String numeroINSEE, String nom, String prenom, String adresse, String telephone, LocalDate naissance, int numero) {
        super(numeroINSEE, nom, prenom, adresse, telephone, naissance);
        this.numero = numero;
     }
	
    
    public void ajouteLicence(String numero, LocalDate delivrance, Club club){
        Licence l = new Licence(this, numero, delivrance, club);
        licences.add(l);
    }
}
