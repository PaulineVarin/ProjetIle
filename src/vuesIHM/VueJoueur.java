package vuesIHM;

import java.awt.BorderLayout;
import java.awt.GridLayout;
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
public class VueJoueur {
    private VueJeu jeu = new VueJeu();
    private JFrame window = new JFrame("Vue Joueur");
    private JPanel contenu = new JPanel(new BorderLayout());
    private JPanel contenuHaut = new JPanel(new GridLayout(2, 1));
    private JPanel contenuMillieu = new JPanel(new GridLayout(4, 1));
    private JPanel contenuBas = new JPanel(new GridLayout(2, 3));
    
    private JLabel nomJoueur = new JLabel("NomJoueur");
    private JLabel nomRole = new JLabel("nomRole");
    private JLabel nbactions = new JLabel("nbactions");
    private JLabel listeTresors = new JLabel("Trésors");
    private JLabel listeCartes = new JLabel("listeCartes");
    
    
    private JButton deplacer = new JButton("Se déplacer");
    private JButton assecher = new JButton("Assécher");
    private JButton donner = new JButton("Donner");
    private JButton prendre = new JButton("Prendre");
    private JButton utiliserPouvoir = new JButton("");
    

    public VueJoueur() {
        //Configuraton de contenuHaut
        
        
        //Configuration de contenuMillieu
        
        //Configuration de contenuBas
        
        //Configuration window
        window.setSize(500,500);
        window.add(contenu);
        window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
        
    }
    
    
}
