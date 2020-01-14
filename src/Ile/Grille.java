/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile;

import Enumeration.TypeRole;
import Enumeration.TypeTresorTuile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.JButton;
import patterns.observateur.Controleur;
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
    public ArrayList<Tuile> creationTuiles(ArrayList<Aventurier> collectJoueurs) {
        
        ArrayList<String> nomTuiles = new ArrayList<String>(Arrays.asList(Parameters.NOMS_TUILES));
        ArrayList<Tuile> tuiles = new ArrayList<>();
        Collections.shuffle(nomTuiles);
        //Creation Tuiles vides
        for(int i=1;i<=6;i++) {
            for(int j=0;j<Parameters.NB_COLONNES;j++) {
                tuiles.add(new Tuile(i, j,""));
            }
        }
        //Ajout des nomsTuiles
        int nb=0;
        for (int i=0;i<tuiles.size();i++) {
            if (i!=0 && i!=1 && i!=4 && i!=5 && i!=6 && i!=11 && i!=24 && i!=29 && i!=30 & i!=31 & i!=34 & i!=35) {
                Tuile t = tuiles.get(i);
                t.setNomTuile(nomTuiles.get(nb));
                nb= nb+1;
            }
        }
        
        //Ajout à la HashMap
        for (Tuile t : tuiles) {
            if(t.getNomTuile().equals("")==false) {
                getCollectTuiles().put(t.getNomTuile(), t);
            }
        }
        
        //Ajout des aventuriers sur les tuiles départ
        Tuile t = new Tuile(0, 0, "default");
        for(Aventurier a : collectJoueurs) {
            System.out.println("Debut ajout");
            if(a.getRole().equals(TypeRole.EXPLORATEUR)) {
                System.out.println("JOueur trouvé"+a.getNomJoueur());
                
                t = getCollectTuiles().get("La Porte de Cuivre");
                
                System.out.println(t.getNomTuile());
                
                t.setJoueurDepart(a);
                t.addAventurier(a);
                a.setTuileDeDepart(t);
                a.setTuileCourante(t);
            }
         
            if(a.getRole().equals(TypeRole.INGENIEUR)){
                System.out.println("JOueur trouvé"+a.getNomJoueur());
                t = getCollectTuiles().get("La Porte de Bronze");
                t.setJoueurDepart(a);
                t.addAventurier(a);
                a.setTuileDeDepart(t);
                a.setTuileCourante(t);
            }
            if (a.getRole().equals(TypeRole.MESSAGER)) {
                System.out.println("JOueur trouvé"+a.getNomJoueur());
                t=getCollectTuiles().get("La Porte d'Argent");
                t.setJoueurDepart(a);
                t.addAventurier(a);
                a.setTuileDeDepart(t);
                a.setTuileCourante(t);
            }
            if(a.getRole().equals(TypeRole.NAVIGATEUR)) {
                System.out.println("JOueur trouvé"+a.getNomJoueur());
                t=getCollectTuiles().get("La Porte d'Or");
                t.setJoueurDepart(a);
                t.addAventurier(a);
                a.setTuileDeDepart(t);
                a.setTuileCourante(t);
            }
            if(a.getRole().equals(TypeRole.PILOTE)) {
                System.out.println("JOueur trouvé"+a.getNomJoueur());
                t=getCollectTuiles().get("L'héliport");
                t.setJoueurDepart(a);
                t.addAventurier(a);
                a.setTuileDeDepart(t);
                a.setTuileCourante(t);
            }
            if(a.getRole().equals(TypeRole.PLONGEUR)) {
                System.out.println("JOueur trouvé"+a.getNomJoueur());
                t=getCollectTuiles().get("La Porte de Fer");
                t.setJoueurDepart(a);
                t.addAventurier(a);
                a.setTuileDeDepart(t);
                a.setTuileCourante(t);
            }
        }
      
        //Ajout des trésors
        for (Tuile tu : tuiles) {
            if(tu.getNomTuile().equals("Le Temple de la Lune") || tu.getNomTuile().equals("Le Temple du soleil")) {
                tu.setTresor(TypeTresorTuile.PIERRE);
            }else if(tu.getNomTuile().equals("Jardin des murmures") || tu.getNomTuile().equals("Jardin des Hurlements")) {
                tu.setTresor(TypeTresorTuile.ZEPHYR);
            }else if(tu.getNomTuile().equals("La Caverne des Ombres") || tu.getNomTuile().equals("La Caverne du Brasier")) {
                tu.setTresor(TypeTresorTuile.CRISTAL);
            }else if(tu.getNomTuile().equals("Le Palais des Marées") || tu.getNomTuile().equals("Le Palais de Corail")) {
                tu.setTresor(TypeTresorTuile.CALICE);
            }
        }
        
        
        
        for (Tuile tu : tuiles) {
            System.out.println(tu.getIdTuile()+tu.getNomTuile()+tu.getTresor());
            if(tu.getJoueurDepart()!=null) {
                System.out.print(tu.getIdTuile()+tu.getNomTuile()+tu.getTresor());
                System.out.println(tu.getJoueurDepart().getNomJoueur());
            }
        }
        return tuiles;
    }
    
    public ArrayList<Aventurier> getCollectJoueurs(Tuile t) {
        ArrayList<Aventurier> aventuriers = new ArrayList<>();
        
        aventuriers = t.getCollectAventuriers();
        
        return aventuriers;
    }
    
    public ArrayList<collectCartesJoueur> getJoueurs() {
        ArrayList<collectCartesJoueur> collectCartesJoueur = new ArrayList<>();
        collectCartesJoueur = t.getcollectCartesJoueur;
        if (collectCartesJoueur.size() > 5){
            return System.out.println("échange inderdit");           
        }
        else 
        return t.getCollectAventuriers;        
    }
    
    
    // getters
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
    
    /**
     * @param collectTuiles the collectTuiles to set
     */
    public void addCollectTuiles(Tuile t) {
        getCollectTuiles().put(t.getNomTuile(), t);
    }
    
    
}
