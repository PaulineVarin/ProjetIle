package vuesIHM;

import Enumeration.*;
import Ile.*;
import java.util.ArrayList;
import patterns.observateur.Message;
import patterns.observateur.*;
import java.io.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author varinp
 */
public class IHM extends Observe<Message> {
    //Donnees
    private VueInscription inscription;
    private VueIntroduction introduction;
    private VueJeu jeu;
    private TypeMessage actionEncours = TypeMessage.ATTENTE;

    //Constructeur
    public IHM(Observateur<Message> observateur) {
        this.addObservateur(observateur);
        this.introduction = new VueIntroduction(this);

    }

    //Méthodes
    //PartieControleur
    public void inscriptionJoueurs() {
        this.inscription = new VueInscription(this);
    }

    public void initialisationJeu(ArrayList<String> collectNoms, int niveauEau, int nbJoueurs) {
        Message m = Message.initialisation(collectNoms, niveauEau, nbJoueurs);
        notifierObservateurs(m);
    }

    public void choixJoueur(TypeMessage typeM, String nomRoleCourant, int nbActions) {
        //Mise à jour de l'action
        setActionEncours(typeM);
        System.out.println("IHM choix joueur : "+typeM);
        Message m = Message.choixJoueur(typeM, nomRoleCourant, nbActions);
        notifierObservateurs(m);
    }

    public void validationCaseJoueur(String nomTuile, String nomRole) {
        Message m = Message.validationJoueur(getActionEncours(), nomTuile, nomRole);
        notifierObservateurs(m);
    }

    //Partie vue
    public void demarrerJeu(ArrayList<Tuile> collectTuiles, ArrayList<Aventurier> collectAventuriers, int niveauEau, int joueurCourant) {
        this.jeu = new VueJeu(this);
        getJeu().initialisationVueJeu(collectTuiles, collectAventuriers, niveauEau, joueurCourant);
    }

    public void modifierAffichage(ArrayList<String> collectNomsTuile) {
        setActionEncours(TypeMessage.ATTENTE);
        getJeu().affichageCases(collectNomsTuile);
    }
    
    public void mauvaisChoix(int nbJoueurCourant) {
        getJeu().getVueGrille().mauvaisChoix();
        getJeu().getVueJoueurCourant(nbJoueurCourant).debutTour();
    }

    /**
     * @return the actionEncours
     */
    public TypeMessage getActionEncours() {
        return actionEncours;
    }

    /**
     * @param actionEncours the actionEncours to set
     */
    public void setActionEncours(TypeMessage actionEncours) {
        this.actionEncours = actionEncours;
    }

    /**
     * @return the jeu
     */
    public VueJeu getJeu() {
        return jeu;
    }

}
