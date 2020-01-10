/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile;

import patterns.observateur.Observe;
import java.util.ArrayList;



/**
 *
 * @author tardieue
 */
public class IleInterdite extends Observe {
    
    private int niveauEau;
    private ArrayList <String> tresorsRecuperes = new ArrayList<>();
    private ArrayList <CarteTirage> carteTiragesTires = new ArrayList <>();
    private ArrayList <CarteTirage> carteTiragesDefausses = new ArrayList <>();
    private ArrayList <CarteInondation> carteInondesTires = new ArrayList <>();
    private ArrayList <CarteInondation> carteInondesDefausses = new ArrayList <>();
    private Grille grille = null;
    private ArrayList <Aventurier> aventuriers = new ArrayList <>();

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
    
    private void tirageCartes(Aventurier av) {      // à finir
        System.out.println("Pensez à programmer tirageCarte");
        Aventurier av_temp = av;
        if (verificationTirage()) {
            av.getCollectCartesJoueur().addAll(tiragePossible());
            setAventurier(av);
        }
    }
    
    private boolean verificationTirage() {
        return(getCarteTiragesTires().size() <= 2);
    }
    
    private ArrayList<CarteTirage> tiragePossible() {
        ArrayList<CarteTirage> tiree = new ArrayList<>();
        for (int i=0; i<2; i++) {
            tiree.add(getCarteTiragesTires().get(0));
        }
        return tiree;
    }
    
    private void tirageCartesInondation() {
        // utiliser this.niveauEau pour le nombre de carte
        System.out.println("Pensez à programmer tirageCartesInondation");
    }
    
    
    /**
     * @return the niveauEau
     */
    public int getNiveauEau() {
        return niveauEau;
    }

    /**
     * @return the tresorsRecuperes
     */
    public ArrayList <String> getTresorsRecuperes() {
        return tresorsRecuperes;
    }

    /**
     * @return the carteTiragesTires
     */
    public ArrayList <CarteTirage> getCarteTiragesTires() {
        return carteTiragesTires;
    }

    /**
     * @return the carteTiragesDefausses
     */
    public ArrayList <CarteTirage> getCarteTiragesDefausses() {
        return carteTiragesDefausses;
    }

    /**
     * @return the carteInondesTires
     */
    public ArrayList <CarteInondation> getCarteInondesTires() {
        return carteInondesTires;
    }

    /**
     * @return the carteInondesDefausses
     */
    public ArrayList <CarteInondation> getCarteInondesDefausses() {
        return carteInondesDefausses;
    }

    /**
     * @return the grille
     */
    public Grille getGrille() {
        return grille;
    }

    /**
     * @return the aventurier
     */
    public Aventurier getAventurier() {
        return aventurier;
    }

    /**
     * @param niveauEau the niveauEau to set
     */
    public void setNiveauEau(int niveauEau) {
        this.niveauEau = niveauEau;
    }

    /**
     * @param tresorsRecuperes the tresorsRecuperes to set
     */
    public void setTresorsRecuperes(ArrayList <String> tresorsRecuperes) {
        this.tresorsRecuperes = tresorsRecuperes;
    }

    /**
     * @param carteTiragesTires the carteTiragesTires to set
     */
    public void setCarteTiragesTires(ArrayList <CarteTirage> carteTiragesTires) {
        this.carteTiragesTires = carteTiragesTires;
    }

    /**
     * @param carteTiragesDefausses the carteTiragesDefausses to set
     */
    public void setCarteTiragesDefausses(ArrayList <CarteTirage> carteTiragesDefausses) {
        this.carteTiragesDefausses = carteTiragesDefausses;
    }

    /**
     * @param carteInondesTires the carteInondesTires to set
     */
    public void setCarteInondesTires(ArrayList <CarteInondation> carteInondesTires) {
        this.carteInondesTires = carteInondesTires;
    }

    /**
     * @param carteInondesDefausses the carteInondesDefausses to set
     */
    public void setCarteInondesDefausses(ArrayList <CarteInondation> carteInondesDefausses) {
        this.carteInondesDefausses = carteInondesDefausses;
    }

    /**
     * @param grille the grille to set
     */
    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    /**
     * @param aventurier the aventurier to set
     */
    public void setAventurier(Aventurier aventurier) {
        this.aventurier = aventurier;
    }
    
}
