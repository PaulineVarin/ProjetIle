package Jeu;


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
public class IHM {
    private VueInscription inscription;
    private VueIntroduction introduction;
    private VueJeu jeu;
    

    IHM() {
        //test des vues
      this.inscription = new VueInscription(this);
      this.introduction = new VueIntroduction(this); 
    }
    
}