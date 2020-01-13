package vuesIHM;

import Ile.Aventurier;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author varinp
 */
public class VueJoueur{
    //Données
    private VueJeu vueJeu;
    private JFrame window = new JFrame("Vue Joueur");
    private JPanel contenu = new JPanel(new BorderLayout());
    private JPanel contenuHaut = new JPanel(new GridLayout(2, 1));
    private JPanel contenuMillieu = new JPanel(new GridLayout(4, 1));
    private JPanel contenuBas = new JPanel(new GridLayout(2, 3));
    
    private JLabel nomJoueur = new JLabel("NomJoueur");
    private JLabel nomRole = new JLabel("nomRole");
    private JLabel listeTresors = new JLabel("Trésors");
    private JLabel listeCartes = new JLabel("listeCartes");
    private JLabel nbactions = new JLabel("nbactions");
    
    private JButton deplacer = new JButton("Se déplacer");
    private JButton assecher = new JButton("Assécher");
    private JButton donner = new JButton("Donner");
    private JButton prendre = new JButton("Prendre");
    private JButton utiliserCarte = new JButton("Utiliser carte spéciale");
    private JButton finir = new JButton("Finir tour");
    
    private ArrayList<JButton> listeBoutons = new ArrayList<>();
    
    //Constructeur
    public VueJoueur(Aventurier a,VueJeu jeu) {
        this.vueJeu = jeu;
        //Initialisation collection
        listeBoutons.add(deplacer);
        listeBoutons.add(assecher);
        listeBoutons.add(donner);
        listeBoutons.add(prendre);
        listeBoutons.add(utiliserCarte);
        listeBoutons.add(finir);
        
        //Configuraton de contenuHaut
        nomJoueur.setText(a.getNomJoueur());
        nomRole.setText(a.getStringRole());
        contenuHaut.add(nomJoueur);
        contenuHaut.add(nomRole);
        
        //Configuration de contenuMillieu
        nbactions.setText(Integer.toString(a.getNbaction()));
        contenuMillieu.add(nbactions);
        contenuMillieu.add(listeTresors);
        contenuMillieu.add(listeCartes);
    
        //Configuration de contenuBas
        for (JButton j : listeBoutons) {
            contenuBas.add(j);
        }
        
        //Configuration de contenu
        contenu.add(contenuHaut, BorderLayout.NORTH);
        contenu.add(contenuMillieu, BorderLayout.CENTER);
        contenu.add(contenuBas,BorderLayout.SOUTH);
        
        //Configuration window
        window.setSize(500,500);
        window.add(contenu);
        window.setVisible(true);
        
    }
    
    
}
