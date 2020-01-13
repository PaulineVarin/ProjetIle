/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patterns.observateur;

import Enumeration.*;
import Ile.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author tardieue
 */
public class Message implements Serializable {
    //Donnees
    private TypeMessage typeM;
    private TypeAction typeA;
    private Aventurier a;
    private int niveauEau;
    private int nbJoueurs;
    private int nbActions;
    private int nbActionsDonneur;
    private String nomRole;
    private String nomCarte;
    private String nomRoleDonneur;
    private String nomRoleReceveur;
    private String nomTuile;
    private TypeTresorTuile typeTresor;
    private ArrayList<CarteTirage> collectCartesSpe;
    private ArrayList<CarteTirage> collectCartesTresor;
    private ArrayList<CarteTirage> collectCartesJoueur;
    private ArrayList<Aventurier> collectJoueurs;
    private ArrayList<Tuile> collectTuiles;
    private ArrayList<String> collectNomsJoueurs;
   
    
    //Constructeur
    public Message(TypeMessage typeM) {
        this.typeM = typeM;
        
    }

    //Méthodes
    //Méthodes IHM
    public static Message initialisation(ArrayList collectNoms,int niveauEau,int nbJoueurs) {
       Message m = new Message(TypeMessage.INITIALISATION);
       m.collectNomsJoueurs = collectNoms;
       m.niveauEau = niveauEau;
       m.nbJoueurs = nbJoueurs;
       
       return m;
    }
    
    public static Message demarrerJeu(/*collectTuile*/ArrayList<Aventurier> collectJoueurs,int niveauEau) {
        Message m = new Message(TypeMessage.DEBUT_JEU);
        m.collectJoueurs=collectJoueurs;
        //m.collectTuiles=;
        m.niveauEau = niveauEau;
        m.nbJoueurs=collectJoueurs.size();
        return m;
    }
    
    //Méthodes IleInterdite

    /**
     * @return the typeM
     */
    public TypeMessage getTypeM() {
        return typeM;
    }

    /**
     * @return the typeA
     */
    public TypeAction getTypeA() {
        return typeA;
    }

    /**
     * @return the a
     */
    public Aventurier getA() {
        return a;
    }

    /**
     * @return the niveauEau
     */
    public int getNiveauEau() {
        return niveauEau;
    }

    /**
     * @return the nbJoueurs
     */
    public int getNbJoueurs() {
        return nbJoueurs;
    }

    /**
     * @return the nbActions
     */
    public int getNbActions() {
        return nbActions;
    }

    /**
     * @return the nbActionsDonneur
     */
    public int getNbActionsDonneur() {
        return nbActionsDonneur;
    }

    /**
     * @return the nomRole
     */
    public String getNomRole() {
        return nomRole;
    }

    /**
     * @return the nomCarte
     */
    public String getNomCarte() {
        return nomCarte;
    }

    /**
     * @return the nomRoleDonneur
     */
    public String getNomRoleDonneur() {
        return nomRoleDonneur;
    }

    /**
     * @return the nomRoleReceveur
     */
    public String getNomRoleReceveur() {
        return nomRoleReceveur;
    }

    /**
     * @return the nomTuile
     */
    public String getNomTuile() {
        return nomTuile;
    }

    /**
     * @return the typeTresor
     */
    public TypeTresorTuile getTypeTresor() {
        return typeTresor;
    }

    /**
     * @return the collectCartesSpe
     */
    public ArrayList<CarteTirage> getCollectCartesSpe() {
        return collectCartesSpe;
    }

    /**
     * @return the collectCartesTresor
     */
    public ArrayList<CarteTirage> getCollectCartesTresor() {
        return collectCartesTresor;
    }

    /**
     * @return the collectCartesJoueur
     */
    public ArrayList<CarteTirage> getCollectCartesJoueur() {
        return collectCartesJoueur;
    }

    /**
     * @return the collectJoueurs
     */
    public ArrayList<Aventurier> getCollectJoueurs() {
        return collectJoueurs;
    }

    /**
     * @return the collectTuiles
     */
    public ArrayList<Tuile> getCollectTuiles() {
        return collectTuiles;
    }

    /**
     * @return the collectNomsJoueurs
     */
    public ArrayList<String> getCollectNomsJoueurs() {
        return collectNomsJoueurs;
    }
    
    
    
  
    
}





