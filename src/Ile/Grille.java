/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile;

import Enumeration.TypeTresorTuile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import javax.swing.JButton;
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
   
}
