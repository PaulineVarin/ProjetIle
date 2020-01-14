/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vuesIHM;

import Ile.Aventurier;
import Ile.*;
import java.awt.BorderLayout;
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
    public void initialisationVueJeu(ArrayList<Tuile> collectTuiles,ArrayList<Aventurier> collectAventuriers,int niveauEau) {
        this.vueGrille = new VueGrille(this);
        vueGrille.initialiserPlateau(collectTuiles);
        this.vueNiveauEau = new VueNiveau(this,niveauEau);
        this.vueResume = new VueResume(this);
        for(Aventurier a : collectAventuriers) {
           addVuesJoueurs(new VueJoueur(a, this));
        }
        //mettre les vues joueurs dans l'état pour tourDeJeu
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
