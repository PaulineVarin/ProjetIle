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
    public static Message  test() {
        return new Message(TypeMessage.INITIALISATION);
    }
    
    
    
  
    
}
