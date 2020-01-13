package Jeu;

import Ile.*;
import Ile.Tuile;
import Enumeration.TypeTresorTuile;
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
       
       
       ArrayList <String> test = new ArrayList<>();
       test.add("Navigateur");
       test.add("Plongeur");
       test.add("Explorateur");
       test.add("Pilote");
       test.add("messager");
       test.add("Ingenieur");
  
       ile.attributionRole(test,4);
       
       Aventurier nono = new Aventurier("nonolecon", ile);
      
       
       
       
     
       
    } 
}
