package Jeu;

import Ile.Tuile;
import Enumeration.TypeTresorTuile;
import Ile.Aventurier;
import Ile.IleInterdite;
import java.util.ArrayList;
import patterns.observateur.Controleur;
import vuesIHM.IHM;
import vuesIHM.VueIntroduction;
//commentaire
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author varinp
 */
public class ProjetIle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
       
       
       IleInterdite ile = new IleInterdite(new Controleur());
        ArrayList<String> noms = new ArrayList<>();
        noms.add("P");
        noms.add("E");
        noms.add("E");
        noms.add("A");
        
       
        ArrayList<Aventurier> test = ile.determinationRole(noms);
        
        for(Aventurier a : test) {
            System.out.println(a.getNomJoueur());
            System.out.println(a.getRole());
        }
       
      
       
       
       
     
       
    } 
}
