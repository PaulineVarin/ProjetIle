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
    private AFaireVueGrille vueGrille;
    private VueResume vueResume;
    private VueNiveau vueNiveauEau;

    //Constructeur
    public VueJeu(IHM ihm) {
        this.ihm = ihm;
        
    }

    //Méthodes
    public void initialisationVueJeu(ArrayList<Tuile> collectTuiles,ArrayList<Aventurier> collectAventuriers,int niveauEau,int nbJoueurs) {
        //this.vueGrille = new AFaireVueGrille();
        //vueGrille.initialiserPlateau(//a besoin de la collectDeTuiles)
        this.vueNiveauEau = new VueNiveau(this,niveauEau);
        this.vueResume = new VueResume(this);
        
        for (int i=0;i<nbJoueurs;i++) {
            for(Aventurier a : collectAventuriers) {
                new VueJoueur(a.getNomJoueur(), this);
            }
        }
        
        
        
    }
    
    
}
