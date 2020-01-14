/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vuesIHM;

import Ile.Aventurier;
import Ile.*;
import java.util.ArrayList;

/**
 *
 * @author varinp
 */
public class VueJeu {
    //Données
    private IHM ihm;
    private ArrayList<VueJoueur> vuesJoueurs = new ArrayList<>();
    private VueGrille vueGrille;
    private VueResume vueResume;
    private VueNiveau vueNiveauEau;

    //Constructeur
    public VueJeu(IHM ihm) {
        this.ihm = ihm;
    }

    //Méthodes
    public void initialisationVueJeu(ArrayList<Tuile> collectTuiles,ArrayList<Aventurier> collectAventuriers,int niveauEau) {
        this.vueGrille = new VueGrille(this);
        vueGrille.initialiserPlateau(collectTuiles);
        this.vueNiveauEau = new VueNiveau(this,niveauEau);
        this.vueNiveauEau.getColoredNiveau();
        //this.vueResume = new VueResume(this);
        for(Aventurier a : collectAventuriers) {
           // addVuesJoueurs(new VueJoueur(a, this));
        }
    }

    /**
     * @return the vuesJoueurs
     */
    public ArrayList<VueJoueur> getVuesJoueurs() {
        return vuesJoueurs;
    }

    /**
     * @param vuesJoueurs the vuesJoueurs to set
     */
    public void addVuesJoueurs(VueJoueur joueur) {
        getVuesJoueurs().add(joueur);
    }
    
    
}
