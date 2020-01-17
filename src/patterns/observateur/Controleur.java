/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patterns.observateur;

import Enumeration.*;
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
    @Override
    public void traiterMessage(Message msg) {
        switch (msg.getTypeM()) {
            case INITIALISATION:
                System.out.println("Initialisation");
                ileInterdite.commencerPartie(msg.getNiveauEau(), msg.getCollectNomsJoueurs(), msg.getNbJoueurs(), msg.getTypeM());
                break;
            case DEBUT_JEU:
                System.out.println("DEBUT_JEU");
                ihm.demarrerJeu(msg.getCollectTuiles(), msg.getCollectJoueurs(), msg.getNiveauEau(), nbJoueurCourant);
                break;
            case DEBUT_TOUR:
                nbJoueurCourant = msg.getNbJoueurCourant();
                ihm.miseAjourVuesDebutTour(nbJoueurCourant);
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
            case SE_DEPLACER:
                if (msg.getTypeA() != null) {
                    if (msg.getTypeA().equals(TypeAction.CHOIX_JOUEUR)) {
                        System.out.println("Controleur CHOIX_JOUEURDEPLACER");
                        ileInterdite.tourDeJeu(nbJoueurCourant, msg.getNbActions(), msg.getTypeM());
                    } else if (msg.getTypeA().equals(TypeAction.VALIDATION_JOUEUR)) {
                        System.out.println("Controleur hello vueJeu");
                        ileInterdite.seDeplacer(msg.getNomTuile(), nbJoueurCourant);
                    }
                } else {
                    ihm.miseAjourVues(msg.getNomRole(), msg.getNomTuile(), msg.getNbActions(), msg.getTypeM(), nbJoueurCourant);
                }
                break;

            case ASSECHER:
                if (msg.getTypeA() != null) {
                    if (msg.getTypeA().equals(TypeAction.CHOIX_JOUEUR)) {
                        System.out.println("Controleur CHOIX_JOUEURAssecher");
                        ileInterdite.tourDeJeu(nbJoueurCourant, msg.getNbActions(), msg.getTypeM());
                    } else {
                        if (msg.getTypeA().equals(TypeAction.VALIDATION_JOUEUR)) {
                            ileInterdite.assecher(msg.getNomTuile(), msg.getTypeM(), nbJoueurCourant);
                        }
                    }
                } else {
                    ihm.miseAjourVues(msg.getNomRole(), msg.getNomTuile(), msg.getNbActions(), msg.getTypeM(), nbJoueurCourant);
                }
                break;

            case DONNER:
                if (msg.getTypeA().equals(TypeAction.CHOIX_JOUEUR)) {
                    System.out.println("Controleur CHOIX_JOUEURDonner");
                    ileInterdite.tourDeJeu(nbJoueurCourant, msg.getNbActions(), msg.getTypeM());
                } else {
                    if (msg.getTypeA().equals(TypeAction.VALIDATION_JOUEUR)) {
                        //ileInterdite.choixJoueurs();
                    }
                }
                break;
            case PRENDRE:
                if (msg.getTypeA().equals(TypeAction.CHOIX_JOUEUR)) {
                    System.out.println("Controleur CHOIX_JOUEURPrendre");
                    ileInterdite.tourDeJeu(nbJoueurCourant, msg.getNbActions(), msg.getTypeM());
                } else {
                    if (msg.getTypeA().equals(TypeAction.VALIDATION_JOUEUR)) {
                        //ileInterdite.recuperationTresor();
                    }
                }
                break;
            case NIVEAU_EAU:
                System.out.println("NIVEAU_EAU");
                break;
            case FIN_TOUR:
                if (msg.getTypeA() == null) {
                    ileInterdite.finTour(nbJoueurCourant);
                } else if (msg.getTypeA().equals(TypeAction.CHOIX_CARTE)) {
                    System.out.println("Hello methode finTour+choixCarte");
                    ArrayList<String> collectNoms = new ArrayList<>();
                    for (CarteTirage ct : msg.getCollectCartesJoueur()) {
                        collectNoms.add(ct.getNom());
                    }
                    ihm.afficherChoix(msg.getTypeM(), collectNoms);
                } else if (msg.getTypeA().equals(TypeAction.CHOIX_CARTE_DEFAUSSE)) {
                    System.out.println("Hello methode finTour+choixCarteDefausse");
                    ileInterdite.majCollectCartesJoueurs(msg.getNomCarte(), nbJoueurCourant);
                } else if (msg.getTypeA().equals(TypeAction.DISTRIBUTION_CARTE)) {
                    ArrayList<String> nomCartes = new ArrayList<>();
                    for (CarteTirage cti : msg.getCollectCartesJoueur()) {
                        System.out.println("controleur cartes noms");
                         System.out.println(cti.getNom());
                        nomCartes.add(cti.getNom());
                       
                    }
                    ihm.miseAjourVuesDistribution(msg.getNiveauEau(), nomCartes, nbJoueurCourant);
                } else if (msg.getTypeA().equals(TypeAction.INONDATION)) {
                    System.out.println("controleur inondation");
                    ArrayList<String> nomTuiles = new ArrayList<>();
                    for (Tuile t : msg.getCollectTuiles()) {
                        nomTuiles.add(t.getNomTuile());
                        System.out.println(t.getNomTuile());
                    }
                    ileInterdite.changementJoueur(nbJoueurCourant);
                    ihm.miseAJourVueInondation(nomTuiles);
                    
                }
                break;
            case MAUVAIS_CHOIX:
                ihm.mauvaisChoix(nbJoueurCourant);
                break;
            case FIN_JEU:
                //ileInterdite.perdrePartie();
                break;
            // traitements messages non traités pour perdrePartie et gagnerPartie
            default:
                System.out.print("Default Action non disponible");
        }
    }
}
