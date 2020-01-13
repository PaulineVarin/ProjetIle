/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vuesIHM;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author varinp
 */
public class VueResume {
    private VueJeu vueJeu;
    
    private JFrame window = new JFrame("VueRésumé");
    private JPanel contenu = new JPanel(new BorderLayout());
    private JPanel contenuCentre = new JPanel(new GridLayout(3, 1));
    
    private JLabel libel = new JLabel("Ile Interdite");
    private JLabel libeljoueurcourant = new JLabel("Joueur courant");
    private JLabel libelNiveauEau = new JLabel("NiveauEau");
    private JLabel libelTirage = new JLabel("Tirage Cartes");

    public VueResume(VueJeu vueJeu) {
        this.vueJeu = vueJeu;
        //Configuration contenuCentre
        contenuCentre.add(libeljoueurcourant);
        contenuCentre.add(libelNiveauEau);
        contenuCentre.add(libelTirage);
        
        //Configuration contenu
        contenu.add(libel, BorderLayout.NORTH);
        contenu.add(contenuCentre,BorderLayout.CENTER);
        
        //Configuration fenetre
        window.setSize(500,500);
        window.add(contenu);
        window.setVisible(true);
    }
    
    
    
    
    
    
}
