/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile;

import Enumeration.TypeTresorTuile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import vuesIHM.Parameters;

/**
 *
 * @author tardieue
 */
public class Grille {
    //Données
    private HashMap<String,Tuile> collectTuiles = new HashMap<>();
    
    //Constructeur
    public Grille() {
 
    }
    
    //Méthodes
    public ArrayList<Tuile> creationTuiles() {
        ArrayList<String> nomTuiles = new ArrayList<String>(Arrays.asList(Parameters.NOMS_TUILES));
        ArrayList<Tuile> tuiles = new ArrayList<>();
        Collections.shuffle(nomTuiles);
        
        
        //creer 1 ligne puis 6 colonnes
        for(int i=0;i<Parameters.NB_LIGNES;i++) {
            System.out.println("nb i "+i);
            for(int j=0;j<Parameters.NB_COLONNES;j++) {
                System.out.println(""+i+j+"NBTUILE"+nbTuile);
                tuiles.add(new Tuile(i, j, nomTuiles.get(nbTuile)));
                nbTuile = nbTuile+1;
                System.out.println("Fin colonne"+j);
            }
            
        }
        
        
        for (Tuile t : tuiles) {
            getCollectTuiles().put(t.getNomTuile(), t);
            System.out.println("Nom Tuile  "+t.getNomTuile());
            System.out.println("Id tuile"+t.getIdTuile());
        }
       
        
        
    

        for(int i=1;i<=6;i++) {
            for(int j=0;j<Parameters.NB_COLONNES;j++) {
                tuiles.add(new Tuile(i, j,""+i+j));
            }
        }
        
        int nb=0;
        for (int i=0;i<tuiles.size();i++) {
            if (i!=0 && i!=1 && i!=4 && i!=5 && i!=6 && i!=11 && i!=24 && i!=29 && i!=30 & i!=31 & i!=34 & i!=35) {
                Tuile t = tuiles.get(i);
                t.setNomTuile(nomTuiles.get(nb));
                nb= nb+1;
            }
        }
        return tuiles;
    }
   
    public ArrayList<Tuile> getTuilesGrille() {
        HashMap<String, Tuile> map = getCollectTuiles();
        Collection<Tuile> values = map.values();

        ArrayList<Tuile> tuiles = new ArrayList<>(values);
        return tuiles;
    }

    public Tuile getTuile(String nom) {
        Tuile t = null;
        ArrayList<Tuile> tuiles = getTuilesGrille();
        
        for (int i = 0; i< tuiles.size(); i++) {
            if (tuiles.get(i).getNomTuile() == nom) {
                t= tuiles.get(i);
            }
        }
        
        return t;
    }

    /**
     * @return the collectTuiles
     */
    public HashMap<String,Tuile> getCollectTuiles() {
        return collectTuiles;
    }
    
    
}
