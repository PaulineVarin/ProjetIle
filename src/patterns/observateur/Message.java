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
    private Tuile t;
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
    public static Message initialisation(ArrayList collectNoms, int niveauEau, int nbJoueurs) {
        Message m = new Message(TypeMessage.INITIALISATION);
        m.collectNomsJoueurs = collectNoms;
        m.niveauEau = niveauEau;
        m.nbJoueurs = nbJoueurs;        
        return m;
    }
    
    public static Message choixJoueur(TypeMessage action, String nomRoleCourant, int nbActions) {
        System.out.print("choix-joueur"+action);
        Message m = new Message(action);
        System.out.println("Message choix joueur"+m.getTypeM());
        m.typeA = TypeAction.CHOIX_JOUEUR;
        m.nomRole = nomRoleCourant;
        m.nbActions = nbActions;
        return m;
    }
    
    public static Message validationJoueur(TypeMessage action, String nomTuile, String nomRoleJoueur) {
        Message m = new Message(action);
        System.out.println("Validation joueur"+action);
        m.typeA = TypeAction.VALIDATION_JOUEUR;
        m.nomTuile = nomTuile;
        m.nomRole = nomRoleJoueur;
        return m;
    }

    //Méthodes IleInterdite
    public static Message demarrerJeu(ArrayList<Tuile> collectTuiles, ArrayList<Aventurier> collectJoueurs, int niveauEau, int nbjoueurs) {
        Message m = new Message(TypeMessage.DEBUT_JEU);
        m.collectJoueurs = collectJoueurs;
        m.collectTuiles = collectTuiles;
        m.niveauEau = niveauEau;
        m.nbJoueurs = nbjoueurs;
        return m;
    }

    public static Message afficherCases(ArrayList<Tuile> collectCases) {
        Message m = new Message(TypeMessage.AFFICHAGE_CASE);
        System.out.print("Afficher cases"+m.typeM);
        m.collectTuiles = collectCases;
        return m;
    }
    
    public static Message mauvaisChoix() {
        Message m = new Message(TypeMessage.MAUVAIS_CHOIX);
        return m;
    }
    
    public static Message deplacer (String nomRole, Tuile t, int nbActions){
       Message m = new Message(TypeMessage.SE_DEPLACER);
       m.t = t;
       m.nomRole = nomRole;
       m.nbActions = nbActions;
       return m;
   }
    
    
    public static Message asseche (String nomTuile){
        Message m = new Message(TypeMessage.ASSECHER);
        m.nomTuile = nomTuile;
        return m;
    }
   
    public static Message tirecartes (ArrayList<CarteTirage>collectCartesJoueur){
        Message m  = new Message (TypeMessage.FIN_TOUR);
        m.collectCartesJoueur = collectCartesJoueur;
        m.typeA = TypeAction.CHOIX_CARTE;
        return m;
    }
    /*
    
    public static Message donner (ArrayList<Aventurier> collectJoueurs, ArrayList<CarteTirage> collectCartesJoueur){
        Message m = new Message(TypeMessage.DONNER);
        m.typeA = TypeAction.POSSIBILITEES_JOUEUR;
        m.collectJoueurs = collectJoueurs;
        return m;
    }*/
    
    public static Message distribue (int niveauEau){
        Message m = new Message(TypeMessage.NIVEAU_EAU);
        m.niveauEau = niveauEau;
        return m;
    }
    
    
    public static Message ppartie (){
        Message m = new Message (TypeMessage.FIN_JEU);
        m.typeA = TypeAction.PERDRE;
        return m;
    }
    
    public static Message gpartie(){
        Message m = new Message (TypeMessage.FIN_JEU);
        m.typeA = TypeAction.GAGNER;
        return m;
       
    }
    
    
    
    public static Message donner (int nbActionsDonneur,String nomRoleDonneur,String nomRoleReceveur,String nomCarte){ //donner1 rename pour differencier de donner
        Message m = new Message(TypeMessage.DONNER);
        m.nbActionsDonneur = nbActionsDonneur;
        m.nomRoleDonneur = nomRoleDonneur;
        m.nomRoleReceveur =nomRoleReceveur;
        m.nomCarte =nomCarte;
        return m;
    }
    
    public static Message donner (ArrayList<CarteTresor> collectCartesTresors){
        Message m = new Message(TypeMessage.DONNER);
//        m.collectCartesTresor = collectCartesTresors; affectation ArrayList carteTirage , carteTresor
        return m;
    }
        

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

    /**
     * @return the t
     */
    public Tuile getT() {
        return t;
    }
}
    
