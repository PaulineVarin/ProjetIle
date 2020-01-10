package vuesIHM;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
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

    public VueJoueur() {
        JFrame window = new JFrame("Vue Joueur");
        JPanel contenu = new JPanel(new BorderLayout());
        JPanel contenuHaut = new JPanel(new GridLayout(2, 1));
        JPanel contenuMillieu = new JPanel(new GridLayout(4, 1));
        JPanel contenuBas = new JPanel(new GridLayout(2, 3));
        
        //Configuraton de contenuHaut
        
        //Configuration de contenuMillieu
        
        //Configuration de contenuBas
        
        //Configuration window
        window.setSize(500,500);
        window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
        
    }
    
    
}
