/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vuesIHM;

import Ile.Aventurier;
import Ile.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author varinp
 */
public class VueJeu {
    //Données
    private IHM ihm;
    private int nbJoueurCourant;
    private ArrayList<VueJoueur> vuesJoueurs = new ArrayList<>();
    private JFrame window = new JFrame("Jeu");
    private JPanel jeu = new JPanel(new BorderLayout());
    private VueGrille vueGrille;
    private VueResume vueResume;
    private VueNiveau vueNiveauEau;

    //Constructeur
    public VueJeu(IHM ihm) {
        this.ihm = ihm;
    }

    //Méthodes
    public int tailleFenetreHauteur() {
        Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); 
        int hauteur = (int)tailleEcran.getHeight();
        return hauteur;
        
    }
    
    public int tailleFenetreLareur() {
        Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); 
        int largeur = (int)tailleEcran.getWidth();  System.out.println(largeur);
        return largeur;
    }
    
    
    public void initialisationVueJeu(ArrayList<Tuile> collectTuiles,ArrayList<Aventurier> collectAventuriers,int niveauEau,int nbJoueurCourant) {
        //Initialisation VueJeu
        setNbJoueurCourant(nbJoueurCourant);
        //Initialisation Grille
        this.vueGrille = new VueGrille(this);
        getVueGrille().initialiserPlateau(collectTuiles);
        
        //Initialisation niveauEau
        this.vueNiveauEau = new VueNiveau(this,niveauEau);
        
        //Initialisation vueResume
        this.vueResume = new VueResume(this);
        getVueResume().initialisationVue(collectAventuriers.get(nbJoueurCourant).getStringRole(),niveauEau);
        
        //Initialisation vueJoueur
        for(Aventurier a : collectAventuriers) {
           addVuesJoueurs(new VueJoueur(a, this));
        }
        //mettre les vues joueurs dans l'état pour tourDeJeu
        getVuesJoueurs().get(nbJoueurCourant).debutTour(getNbJoueurCourant());
        
        for(VueJoueur joueur : getVuesJoueurs()) {
            joueur.positionsFenetres();
        }        
    }
    
    public void fermerFenetres() {
        getVueResume().getWindow().dispose();
        getVueGrille().getWindow().dispose();
        getVueNiveauEau().getWindow().dispose();
        
        for(VueJoueur joueur : getVuesJoueurs()) {
            joueur.getWindow().dispose();
        }
        System.exit(0);
    }
    
    public VueJoueur getVueJoueurCourant(int nbJoueurCourant) {
        return getVuesJoueurs().get(nbJoueurCourant);
    }
    
    public void affichageCases(ArrayList<String> collectNomsCases) {
        getVueGrille().modifierAffichage(collectNomsCases);
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

    /**
     * @return the ihm
     */
    public IHM getIhm() {
        return ihm;
    }

    /**
     * @return the vueGrille
     */
    public VueGrille getVueGrille() {
        return vueGrille;
    }

    /**
     * @return the vueResume
     */
    public VueResume getVueResume() {
        return vueResume;
    }

    /**
     * @return the vueNiveauEau
     */
    public VueNiveau getVueNiveauEau() {
        return vueNiveauEau;
    }

    /**
     * @return the nbJoueurCourant
     */
    public int getNbJoueurCourant() {
        return nbJoueurCourant;
    }

    /**
     * @param nbJoueurCourant the nbJoueurCourant to set
     */
    public void setNbJoueurCourant(int nbJoueurCourant) {
        this.nbJoueurCourant = nbJoueurCourant;
    }
    
    
}
