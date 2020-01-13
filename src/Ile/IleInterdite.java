/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile;

import Enumeration.TypeRole;
import Enumeration.TypeTresorTuile;
import patterns.observateur.Observe;
import patterns.observateur.Observateur;
import java.util.ArrayList;
import patterns.observateur.Controleur;
import patterns.observateur.Message;




/**
 *
 * @author tardieue
 */
public class IleInterdite extends Observe<Message> {
    //Données
    private int niveauEau;
    private Grille grille;
    private ArrayList <TypeTresorTuile> tresorsRecuperes = new ArrayList<>();
    private ArrayList <CarteTirage> cartesTirageTire = new ArrayList <>();
    private ArrayList <CarteTirage> cartesTirageDefausse = new ArrayList <>();
    private ArrayList <CarteInondation> cartesInondeTire = new ArrayList <>();
    private ArrayList <CarteInondation> carteInondeDefausse = new ArrayList <>();
    private ArrayList <Aventurier> aventuriers = new ArrayList <>();
    
    
    
    //Constructeur    
    public IleInterdite(Observateur<Message> observateur) {
        // commencerPartie();
        this.addObservateur(observateur);
    }
    



    //Méthodes
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
    
    private void initialisationPartie() {
        
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
    public ArrayList <TypeTresorTuile> getTresorsRecuperes() {
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
    public ArrayList <CarteTirage> getCartesTirageTire() {
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
    public ArrayList <CarteTirage> getCartesTirageDefausse() {
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
    public ArrayList <CarteInondation> getCartesInondeTire() {
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
    public ArrayList <CarteInondation> getCarteInondeDefausse() {
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
    public ArrayList <Aventurier> getAventuriers() {
        return aventuriers;
    }

    /**
     * @param aventuriers the aventuriers to set
     */
    public void setAventuriers(Aventurier a) {
        getAventuriers().add(a);
    }
    
    public void CommencerPartie(int niveau, ArrayList<String> collectNom, int nbJoueur){
        this.niveauEau = niveau;
        for (int i = 0; i < nbJoueur; i++) {
            
            ArrayList <TypeRole> collectJoueurs = new ArrayList<>();
            
            int r1[] = {1, 1, 1, 1, 1, 1};
            
            int r = 0;
            
            do {
                r = (int) Math.random()*6;
            } while(r1[r] == 0);
            
            
            switch(r){
                case 0 : aventuriers.add(new Ingenieur(collectNom.get(i), this));
                break;
                case 1 : aventuriers.add(new Messager(collectNom.get(i), this));
                break;
                case 2 : aventuriers.add(new Plongeur(collectNom.get(i), this));
                break;
                case 3 : aventuriers.add(new Explorateur(collectNom.get(i), this));
                break;
                case 4 : aventuriers.add(new Pilote(collectNom.get(i), this));
                break;
                case 5 : aventuriers.add(new Navigateur(collectNom.get(i), this));
                break;
            }
            r1[r] = 0;
        }
            
           
    }
    

}
    




