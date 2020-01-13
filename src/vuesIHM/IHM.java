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
    //Donnees
    private VueInscription inscription;
    private VueIntroduction introduction;
    private VueJeu jeu;
    
    //Constructeur
    public IHM(Observateur<Message> observateur) {
        //test des vues
      this.addObservateur(observateur);
      this.introduction = new VueIntroduction(this); 
      
    }
    
    //Méthodes
    
    
    
}
