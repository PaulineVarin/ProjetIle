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

    public void choixJoueur(TypeMessage action, String nomRoleCourant, int nbActions) {
        setActionEncours(action);
        //System.out.print(action);
        Message m = Message.choixJoueur(action, nomRoleCourant, nbActions);
        notifierObservateurs(m);
    }

    public void validationCaseJoueur(String nomTuile, String nomRole) {
        Message m = Message.validationJoueur(getActionEncours(), nomTuile, nomRole);
        notifierObservateurs(m);
    }

    //Partie vue
    public void demarrerJeu(ArrayList<Tuile> collectTuiles, ArrayList<Aventurier> collectAventuriers, int niveauEau, int joueurCourant) {
        this.jeu = new VueJeu(this);
        jeu.initialisationVueJeu(collectTuiles, collectAventuriers, niveauEau, joueurCourant);
    }

    public void modifierAffichage(ArrayList<String> collectNomsTuile) {
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

}
