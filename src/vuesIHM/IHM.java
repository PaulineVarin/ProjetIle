package vuesIHM;

import Enumeration.*;
import Ile.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import patterns.observateur.Message;
import patterns.observateur.*;
import java.io.*;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

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
        System.out.println("IHM choix joueur : " + typeM);
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
        getJeu().affichageCases(collectNomsTuile);
    }

    public void mauvaisChoix(int nbJoueurCourant) {
        getJeu().getVueGrille().mauvaisChoix();
        getJeu().getVueJoueurCourant(nbJoueurCourant).debutTour(nbJoueurCourant);
    }

    public void miseAjourVues(String nomRole, String nomTuile, int nbActions, TypeMessage typeM, int nbJoueurCourant) {
        if (typeM.equals(TypeMessage.SE_DEPLACER) || typeM.equals(TypeMessage.ASSECHER)) {
            getJeu().getVueGrille().miseAjourVueGrille(nomTuile, nomRole, typeM);
            getJeu().getVueJoueurCourant(nbJoueurCourant).miseAjourVueJoueur(nbActions, nomRole);
            getJeu().getVueResume().miseAjourVueResume(nomRole, nomTuile, typeM);
        }
        setActionEncours(TypeMessage.ATTENTE);
        System.out.print("Action en attente");
    }

    public void afficherChoix(TypeMessage TypeM, ArrayList<String> collectNoms) {
        //créer une fenetre temporaire et choisir la carte (liste à puce)
        fenetreChoix(collectNoms);
    }
    
    public void miseAjourVuesDistribution(int niveauEau,ArrayList<String>collectNoms,int nbJoueurCourant){
        getJeu().getVueJoueurCourant(nbJoueurCourant).miseAjourVueJoueurCartes(collectNoms);
        getJeu().getVueNiveauEau().setNiveau(niveauEau);
    }
    
    public void miseAJourVueInondation(ArrayList<String> collectNoms){
        getJeu().getVueGrille().inondation(collectNoms);
    }
    
    public void miseAjourVuesDebutTour(int nbJoueurCourant){
        setActionEncours(TypeMessage.ATTENTE);
        getJeu().setNbJoueurCourant(nbJoueurCourant);
        getJeu().getVueJoueurCourant(nbJoueurCourant).debutTour(nbJoueurCourant);
        //ne revient jamais pour mettre a jour l'affichage de la vue précédente pour le nbAction et
        //l'attribution des cartes déconne
        
    }

    public void fenetreChoix(ArrayList<String> collectNoms) {
        JFrame window = new JFrame("Choix");
        JPanel contenu = new JPanel(new BorderLayout());
        JPanel contenuBoutons = new JPanel(new GridLayout(collectNoms.size(), 1));
        JButton valider = new JButton("Valider");
        ButtonGroup collectRadio = new ButtonGroup();
        ArrayList<JRadioButton> boutonsRadios = new ArrayList<>();
        //ConfigurationBoutton
        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom="";
                for (JRadioButton button : boutonsRadios) {
                    if (button.isSelected()) {
                        nom = button.getText();
                    }
                }
                window.dispose();
                //getJeu().getVueJoueurCourant(getJeu().getNbJoueurCourant()).miseAjourVueJoueurCartes(collectNoms);
                Message m = Message.defausseCarte(nom);
                notifierObservateurs(m);
            }

        });

        //Configuration contenuBouton
        for (int i = 0; i < collectNoms.size(); i++) {
            boutonsRadios.add((new JRadioButton()));
        }
        boutonsRadios.get(0).setSelected(true);
        for (int i = 0; i < collectNoms.size(); i++) {
            boutonsRadios.get(i).setText(collectNoms.get(i));
        }
        for (JRadioButton radio : boutonsRadios) {
            collectRadio.add(radio);
            contenuBoutons.add(radio);
        }

        //Configuration contenu
        contenu.add(contenuBoutons, BorderLayout.CENTER);
        contenu.add(valider, BorderLayout.SOUTH);
        window.add(contenu);
        window.setSize(500, 500);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
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
