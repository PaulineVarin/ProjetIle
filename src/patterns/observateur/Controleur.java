/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patterns.observateur;

import Enumeration.TypeAction;
import Enumeration.TypeMessage;
import vuesIHM.*;
import Ile.*;
import java.util.ArrayList;

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
                ihm.demarrerJeu(msg.getCollectTuiles(), msg.getCollectJoueurs(), msg.getNiveauEau(), nbJoueurCourant);
                break;
            case SE_DEPLACER:
                if (msg.getTypeA().equals(TypeAction.CHOIX_JOUEUR)) {
                    System.out.println("Controleur CHOIX_JOUEURDEPLACER");
                    ileInterdite.tourDeJeu(msg.getNomRole(), msg.getNbActions(), msg.getTypeM());
                } else if (msg.getTypeA().equals(TypeAction.VALIDATION_JOUEUR)) {
                    ileInterdite.seDeplacer(msg.getNomTuile(), msg.getNomRole());
                } else {
                    System.out.println("Hello");

                }

            case ASSECHER:
                if (msg.getTypeA().equals(TypeAction.CHOIX_JOUEUR)) {
                    System.out.println("Controleur CHOIX_JOUEURAssecher");
                    ileInterdite.tourDeJeu(msg.getNomRole(), msg.getNbActions(), msg.getTypeM());
                } else {
                    if (msg.getTypeA().equals(TypeAction.VALIDATION_JOUEUR)) {
                        //ileInterdite.assecher();
                    }
                }
            case DONNER:
                if (msg.getTypeA().equals(TypeAction.CHOIX_JOUEUR)) {
                    System.out.println("Controleur CHOIX_JOUEURDonner");
                    ileInterdite.tourDeJeu(msg.getNomRole(), msg.getNbActions(), msg.getTypeM());
                } else {
                    if (msg.getTypeA().equals(TypeAction.VALIDATION_JOUEUR)) {
                        //ileInterdite.choixJoueurs();
                    }
                }
            case PRENDRE:
                if (msg.getTypeA().equals(TypeAction.CHOIX_JOUEUR)) {
                    System.out.println("Controleur CHOIX_JOUEURPrendre");
                    ileInterdite.tourDeJeu(msg.getNomRole(), msg.getNbActions(), msg.getTypeM());
                } else {
                    if (msg.getTypeA().equals(TypeAction.VALIDATION_JOUEUR)) {
                        //ileInterdite.recuperationTresor();
                    }
                }
                break;
            case AFFICHAGE_CASE:
                System.out.println("Affich cases");
                ArrayList<String> nomCases = new ArrayList<>();
                for (Tuile t : msg.getCollectTuiles()) {
                    if (t.getNomTuile().length() > 2) {
                        nomCases.add(t.getNomTuile());
                    }
                }
                ihm.modifierAffichage(nomCases);
                break;
            case NIVEAU_EAU:
                System.out.println("NIVEAU_EAU");
                //ileInterdite.distributionCarte();
                break;
            case FIN_JEU:
                System.out.println("FIN_JEU");
                //ileInterdite.perdrePartie();
                break;
            case MAUVAIS_CHOIX:
                ihm.mauvaisChoix(nbJoueurCourant);
            case FIN_TOUR:
                System.out.println("patterns.observateur.Controleur.traiterMessage()");
            default:
                System.out.print("Default Action non disponible");
        }
    }
}
