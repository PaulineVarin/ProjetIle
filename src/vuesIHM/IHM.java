package vuesIHM;


import Enumeration.TypeMessage;
import Ile.*;
import java.util.ArrayList;
import patterns.observateur.Message;
import patterns.observateur.*;
import vuesIHM.*;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author varinp
 */
public class IHM extends Observe<Message>  {
    //Donnees
    private VueInscription inscription;
    private VueIntroduction introduction;
    private VueJeu jeu;
    
    //Constructeur
    public IHM(Observateur<Message> observateur) {
      this.addObservateur(observateur);
      this.introduction = new VueIntroduction(this); 
      
    }
    
    //Méthodes
    
    public void inscriptionJoueurs() {
        this.inscription = new VueInscription(this);
    }
    
    public void debutJeu(ArrayList<String> collectNoms,int niveauEau,int nbJoueurs) {
        Message m = Message.initialisation(collectNoms,niveauEau,nbJoueurs);
        notifierObservateurs(m);
    }
    
    public void demarrerJeu(ArrayList<Tuile> collectTuiles,ArrayList<Aventurier> collectAventuriers,int niveauEau) {
        this.jeu = new VueJeu(this);
        jeu.initialisationVueJeu(collectTuiles,collectAventuriers,niveauEau);
    }
    
    
    
}
