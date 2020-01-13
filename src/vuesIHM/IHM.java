package vuesIHM;


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
    private VueInscription inscription;
    private VueIntroduction introduction;
    private VueJeu jeu;
    

    public IHM(Observateur<Message> observateur) {
        //test des vues
      this.addObservateur(observateur);
      this.inscription = new VueInscription(this);
      this.introduction = new VueIntroduction(this); 
      VueJoueur joueur = new VueJoueur();
      AFaireVueGrille grille = new AFaireVueGrille();
      VueResume resum = new VueResume();
    }
    
}
