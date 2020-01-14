/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile;

import Enumeration.*;
import Enumeration.TypeRole;
import Enumeration.TypeTresorTuile;
import patterns.observateur.Observe;
import patterns.observateur.Observateur;
import java.util.ArrayList;
import java.util.Collections;
import patterns.observateur.Message;
import vuesIHM.Parameters;

/**
 *
 * @author tardieue
 */
public class IleInterdite extends Observe<Message> {

    //Données
    private int niveauEau;
    private Grille grille;
    private ArrayList<TypeTresorTuile> tresorsRecuperes = new ArrayList<>();
    private ArrayList<CarteTirage> cartesTirageTire = new ArrayList<>();
    private ArrayList<CarteTirage> cartesTirageDefausse = new ArrayList<>();
    private ArrayList<CarteInondation> cartesInondeTire = new ArrayList<>();
    private ArrayList<CarteInondation> carteInondeDefausse = new ArrayList<>();
    private ArrayList<Aventurier> aventuriers = new ArrayList<>();

    //Constructeur    
    public IleInterdite(Observateur<Message> observateur) {
        this.addObservateur(observateur);
    }

    //Méthodes
    public void commencerPartie(int niveauEau, ArrayList<String> collectNomsJoueurs, int nbJoueurs) {
        determinationRole(collectNomsJoueurs);
        setNiveauEau(niveauEau); //faire gaffe à la valeur du niveau eau pour la distribution des cartes
        setGrille(new Grille());
        ArrayList<Tuile>collectTuiles =getGrille().creationTuiles(getAventuriers());
        commencerInondation();
        distributionCartesTresor();
        Message m = Message.demarrerJeu(collectTuiles,getAventuriers(),getNiveauEau());
        notifierObservateurs(m);
    }
    
    public void determinationRole(ArrayList<String> collectNomsJoueurs){
        //création de la collection de rôles
        ArrayList<TypeRole> roleshasard = new ArrayList<>();
        for (TypeRole tr : TypeRole.values()) {
            roleshasard.add(tr);
        }
        Collections.shuffle(roleshasard);
        
        //Création des joueurs
        for (int i = 0; i < collectNomsJoueurs.size(); i++) {
            if (roleshasard.get(i) == TypeRole.EXPLORATEUR) {
                addAventuriers(new Explorateur(collectNomsJoueurs.get(i), this));
            }
            if (roleshasard.get(i) == TypeRole.NAVIGATEUR) {
                addAventuriers(new Navigateur(collectNomsJoueurs.get(i), this));
            }
            if (roleshasard.get(i) == TypeRole.INGENIEUR) {
                addAventuriers(new Ingenieur(collectNomsJoueurs.get(i), this));
            }
            if (roleshasard.get(i) == TypeRole.MESSAGER) {
                addAventuriers(new Messager(collectNomsJoueurs.get(i), this));
            }
            if (roleshasard.get(i) == TypeRole.PILOTE) {
                addAventuriers(new Pilote(collectNomsJoueurs.get(i), this));
            }

            if (roleshasard.get(i) == TypeRole.PLONGEUR) {
                addAventuriers(new Plongeur(collectNomsJoueurs.get(i), this));
            }

        }
    }
    
    public void distributionCartesTresor() {
        creationCartesTirage();
        int nbCartes = 0;
        int indice = 0;
        for(Aventurier a : getAventuriers()) {
            while (nbCartes<2) {
                CarteTirage ct = getCartesTirageTire().get(indice);
                System.out.println(ct.getNom());
                if(ct.getNom().equals("Carte Montée des eaux")==false) {
                    a.getCollectCartesJoueur().add(ct);
                    getCartesTirageTire().remove(ct);
                    nbCartes = nbCartes+1;
                }else {
                    indice = indice+1;
                }  
            }
            nbCartes = 0;
        }
    }
    
    
    public void commencerInondation() {
        creationCartesInondation();
        for(int i=0;i<Parameters.NB_INONDATIONS_INITIALES;i++) {
            CarteInondation cti = getCartesInondeTire().get(i);
            System.out.println(cti.getTuile().getNomTuile());
            inondationPlateau(cti.getTuile(),cti);  
        }
        
    }
    
    public void creationCartesTirage() {
        //Creation des cartes Tresor
        for (TypeTresorCarte tr : TypeTresorCarte.values()){
            for(int i=0;i<Parameters.NB_CARTES_TRESOR;i++){
                getCartesTirageTire().add(new CarteTresor(tr.toString(), tr));
            }
        }
        
        //Creation des cartesSpe
        for(int i=0;i<Parameters.NB_HELICOPTERES;i++) {
            getCartesTirageTire().add(new CarteHelicoptere("Carte Hélicoptère"));
        }
        
        for(int i=0;i<Parameters.NB_SACS_DE_SABLE;i++) {
            getCartesTirageTire().add(new CarteSacsDeSable("Carte Sacs de Sable"));
        }
        
        //Creation des cartesMonteeEau
        for(int i=0;i<Parameters.NB_MONTEES_DES_EAUX;i++) {
            getCartesTirageTire().add(new CarteMonteeDesEaux("Carte Montée des eaux"));
        }
        
        Collections.shuffle(getCartesTirageTire());
    }
    
    public void creationCartesInondation() {
        for(int i=0;i<Parameters.NOMS_TUILES.length;i++) {
            getCartesInondeTire().add(new CarteInondation(Parameters.NOMS_TUILES[i],getGrille().getTuile(Parameters.NOMS_TUILES[i])));
        }
        Collections.shuffle(getCartesInondeTire());
    }
    
    public void inondationPlateau(Tuile t, CarteInondation cti) {
        t.miseAjourEtat();
        if(t.getEtat().equals(EtatTuile.INONDEE)) {
            getCarteInondeDefausse().add(cti);
            getCartesInondeTire().remove(cti);
        }else {
            //A faire
        }
        
    }

    public Aventurier getAventurier(String nomRole) {
        for (Aventurier a : aventuriers) {
            if (a.getStringRole().equals(nomRole)) {
                return a;
            }
        }
        return null;
    }
    
    

    public void tourDeJeu(String nomRole, int nbActions) {

        Aventurier temp;
        temp = getAventurier(nomRole);
        ArrayList<Tuile> collectCases = new ArrayList<>();
        collectCases = temp.calculCases(TypeMessage.AFFICHAGE_CASE); // à revoir

        Message m = Message.tourJeu(temp.getStringRole(), collectCases);
        notifierObservateurs(m);
    }
    /*
    private ArrayList<Aventurier>void choixJoueur(String nomTuile) {
        ArrayList<Aventurier> joueursPoss = new ArrayList<>();
        Grille g = this.getGrille();
        Tuile t = g.getTuile(nomTuile);
        
        joueursPoss = g.getCollectJoueurs(t);

        return joueursPoss;
    }
    */
    
    public void seDeplacer(String nomRole, String nomTuile, int nbActions){
        
        Aventurier temp;
        temp = getAventurier(nomRole);
        
        Tuile t;
        t = temp.getTuileCourante();
        t.removeJoueur(temp);
        
        Grille g = new Grille();
        
        Tuile t1 = g.getTuile(nomTuile);
        temp.setTuileCourante(t1);
        t1.addJoueur(temp);
        
        temp.MiseAJourNbActions();
        temp.getStringRole();
        
        Message m = Message.deplace(temp.getStringRole(), t1.getNomTuile(), temp.getNbaction());
        notifierObservateurs(m);
   
        
        
    }

    /* à faire quand le diagramme de séquence sera fait
    public void finDeTour(String nomAventurier) {
    Aventurier av = null;
    for (int i = 0; i < aventuriers.size(); i++) {
    if (aventuriers.get(i).getNomJoueur().equals(nomAventurier)) {
    av = aventuriers.get(i);
    }
    }
    tirageCarte(av);
    tirageCartesInondation();
    if (!conditionVictoire()) {     // si la partie n'est pas fini,
    changementJoueur();                 // on change de joueur
    }
    }*/
    /*
    private void tirageCartes(Aventurier av) {
        // à finir
        System.out.println("Pensez à programmer tirageCarte");
        Aventurier av_temp = av;
        if (verificationTirage()) {
            av.getCollectCartesJoueur().addAll(tiragePossible());
            setAventuriers(av);
        }
    }
    
    private boolean verificationTirage() {
        return(getCartesTirageTire().size() <= 2);
    }
    
    private ArrayList<CarteTirage> tiragePossible() {
        ArrayList<CarteTirage> tiree = new ArrayList<>();
        for (int i=0; i<2; i++) {
            tiree.add(getCartesTirageTire().get(i));
        }
        return tiree;
    }
    
    private void tirageCartesInondation() {
        // utiliser this.niveauEau pour le nombre de carte
        System.out.println("Pensez à programmer tirageCartesInondation");
    }


    // getters/setters :
    /**
     * @return the niveauEau
     */
    public int getNiveauEau() {
        return niveauEau;
    }

    /**
     * @param niveauEau the niveauEau to set
     */
    public void setNiveauEau(int niveauEau) {
        this.niveauEau = niveauEau;
    }

    /**
     * @return the grille
     */
    public Grille getGrille() {
        return grille;
    }

    /**
     * @param grille the grille to set
     */
    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    /**
     * @return the tresorsRecuperes
     */
    public ArrayList<TypeTresorTuile> getTresorsRecuperes() {
        return tresorsRecuperes;
    }

    /**
     * @param tresorsRecuperes the tresorsRecuperes to set
     */
    public void setTresorsRecuperes(TypeTresorTuile tresor) {
        getTresorsRecuperes().add(tresor);
    }

    /**
     * @return the cartesTirageTire
     */
    public ArrayList<CarteTirage> getCartesTirageTire() {
        return cartesTirageTire;
    }

    /**
     * @param cartesTirageTire the cartesTirageTire to set
     */
    public void setCartesTirageTire(CarteTirage ct) {
        getCartesTirageTire().add(ct);
    }

    /**
     * @return the cartesTirageDefausse
     */
    public ArrayList<CarteTirage> getCartesTirageDefausse() {
        return cartesTirageDefausse;
    }

    /**
     * @param cartesTirageDefausse the cartesTirageDefausse to set
     */
    public void setCartesTirageDefausse(CarteTirage ct) {
        getCartesTirageDefausse().add(ct);
    }

    /**
     * @return the cartesInondeTire
     */
    public ArrayList<CarteInondation> getCartesInondeTire() {
        return cartesInondeTire;
    }

    /**
     * @param cartesInondeTire the cartesInondeTire to set
     */
    public void setCartesInondeTire(CarteInondation cti) {
        getCartesInondeTire().add(cti);
    }

    /**
     * @return the carteInondeDefausse
     */
    public ArrayList<CarteInondation> getCarteInondeDefausse() {
        return carteInondeDefausse;
    }

    /**
     * @param carteInondeDefausse the carteInondeDefausse to set
     */
    public void setCarteInondeDefausse(CarteInondation cti) {
        getCarteInondeDefausse().add(cti);
    }

    /**
     * @return the aventuriers
     */
    public ArrayList<Aventurier> getAventuriers() {
        return aventuriers;
    }

    /**
     * @param aventuriers the aventuriers to set
     */
    public void addAventuriers(Aventurier a) {
        getAventuriers().add(a);
    }

}
