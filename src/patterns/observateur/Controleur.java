/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patterns.observateur;


import vuesIHM.*;
import Ile.*;



/**
 *
 * @author tardieue
 */
public class Controleur implements Observateur<Message> {
    //Données
    private IHM ihm;
    private IleInterdite ileInterdite;
    
    //Constructeur
    public Controleur(){
        this.ileInterdite = new IleInterdite(this);
        this.ihm = new IHM(this);
    }   

    //Méthodes
    public void traiterMessage(Message msg) {
        if (msg.getTypeM() != null) {
            switch(msg.getTypeM()) {
                case INITIALISATION:
                    System.out.println("Initialisation");
                    ileInterdite.commencerPartie(msg.getNiveauEau(), msg.getCollectNomsJoueurs(), msg.getNbJoueurs());
                    break;
                case DEBUT_JEU:
                    System.out.println("DEBUT_JEU");
                    ihm.demarrerJeu(msg.getCollectTuiles(),msg.getCollectJoueurs(),msg.getNiveauEau());
                    break;
                
                case DEBUT_TOUR:
                
                break;  
            }
        }
    }
}

