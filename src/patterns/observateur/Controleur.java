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
    private int nbJoueurCourant = 0;

    //Constructeur
    public Controleur() {
        this.ileInterdite = new IleInterdite(this);
        this.ihm = new IHM(this);
    }

    //Méthodes
    public void traiterMessage(Message msg) {
            switch (msg.getTypeM()) {
                case INITIALISATION:
                    System.out.println("Initialisation");
                    ileInterdite.commencerPartie(msg.getNiveauEau(), msg.getCollectNomsJoueurs(), msg.getNbJoueurs());
                    break;
                case DEBUT_JEU:
                    System.out.println("DEBUT_JEU");
                    ihm.demarrerJeu(msg.getCollectTuiles(),msg.getCollectJoueurs(),msg.getNiveauEau(),nbJoueurCourant);
                    break;
                case SE_DEPLACER:
                    System.out.println("SE_DEPLACER");
                    ileInterdite.seDeplacer(msg.getNomRole(), msg.getNomTuile(), msg.getNbActions());
                    break;
                case ASSECHER:
                    System.out.println("ASSECHER");
                    ileInterdite.Assecher(msg.getNomTuile());
                    break;
                case FIN_TOUR:
                    System.out.println("FIN TOUR");
                    
                    break;
                default:
                if (Parameters.LOGS) {
                    System.out.println("Action interdite : ");
                }
            }
    }
}
