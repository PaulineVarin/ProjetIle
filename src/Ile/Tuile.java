/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile;

import Enumeration.EtatTuile;
import Enumeration.TypeTresorTuile;
import java.util.ArrayList;

/**
 *
 * @author tardieue
 */
public class Tuile {
    
    private String nomTuile;
    private int idTuile;
    private int ligne;
    private int colonne;
    private EtatTuile etat = EtatTuile.ASSECHEE;
    private TypeTresorTuile tresor = TypeTresorTuile.NEUTRE;
    private Aventurier joueurDepart;
    
    private ArrayList<Aventurier> collectAventuriers = new ArrayList<>();
    

    public Tuile(int lig, int col, String nomTuile) {
        setLigne(lig);
        setColonne(col);
        setNomTuile(nomTuile);
        setTresor(tresor);
        setIdTuile();
    }
    
    // Méthodes :
    
    public void removeJoueur(Aventurier a){
        Tuile t = null; // Initialisation automatique à revoir
        if (t.collectAventuriers.contains(a)){
            collectAventuriers.remove(a);
            
        }
        
        
    }
    
    public void addJoueur(Aventurier a){
        Tuile t = null; // Initialisation automatique à revoir
        if (!t.collectAventuriers.contains(a)){
            collectAventuriers.remove(a);
        }
    }
    
   

    /**
     * @return the ligne
     */
    public int getLigne() {
        return ligne;
    }

    /**
     * @param ligne the ligne to set
     */
    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    /**
     * @return the colonne
     */
    public int getColonne() {
        return colonne;
    }

    /**
     * @param colonne the colonne to set
     */
    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    /**
     * @return the etat
     */
    public EtatTuile getEtat() {
        return etat;
    }

    /**
     * @param etat the etat to set
     */
    public void setEtat(EtatTuile etat) {
        this.etat = etat;
    }

    /**
     * @return the tresor
     */
    public TypeTresorTuile getTresor() {
        return tresor;
    }

    /**
     * @param tresor the tresor to set
     */
    public void setTresor(TypeTresorTuile tresor) {
        this.tresor = tresor;
    }

    /**
     * @return the nomTuile
     */
    public String getNomTuile() {
        return nomTuile;
    }

    /**
     * @param nomTuile the nomTuile to set
     */
    public void setNomTuile(String nomTuile) {
        this.nomTuile = nomTuile;
    }

    /**
     * @return the idTuile
     */
    public int getIdTuile() {
        return idTuile;
    }

    /**
     * @param idTuile the idTuile to set
     */
    public void setIdTuile() {
        String id = String.valueOf(getLigne())+String.valueOf(getColonne());
        this.idTuile = Integer.parseInt(id);
    }

    /**
     * @return the joueurDepart
     */
    public Aventurier getJoueurDepart() {
        return joueurDepart;
    }

    /**
     * @param joueurDepart the joueurDepart to set
     */
    public void setJoueurDepart(Aventurier joueurDepart) {
        this.joueurDepart = joueurDepart;
    }

    /**
     * @return the collectAventuriers
     */
    public ArrayList<Aventurier> getCollectAventuriers() {
        return collectAventuriers;
    }

    /**
     * @param collectAventuriers the collectAventuriers to set
     */
    public void addAventurier(Aventurier a) {
       this.getCollectAventuriers().add(a);
    }
 
}


