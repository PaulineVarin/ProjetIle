/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vuesIHM;

import Jeu.IHM;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


/**
 *
 * @author varinp
 */
public class VueIntroduction {
    private IHM ihm;
    private JFrame window = new JFrame("Introduction");
    private JPanel contenu = new JPanel(new BorderLayout());
    private JPanel boutons = new JPanel(new GridLayout(1, 2));;
    private JButton commencer = new JButton("Commencer");
    private JButton quitter = new JButton("Quitter");
    private JLabel titre = new JLabel("Bienvenue sur l'Ile Interdite");
    

    public VueIntroduction(IHM ihm) {
        this.ihm = ihm;
        
        //Configuration panel boutons
        boutons.add(commencer);
        boutons.add(quitter);
        
        //Configuration JLabel
        titre.setHorizontalAlignment(SwingConstants.CENTER);
        
        //Configuration panel contenu
        contenu.add(titre,BorderLayout.NORTH);
        contenu.add(boutons,BorderLayout.CENTER);

        //Configuration fenetre 
        window.add(contenu);
        window.setSize(500,500);
        window.setDefaultCloseOperation(window.EXIT_ON_CLOSE); //A supprimer lors de la mise en place
        window.setVisible(true);
        
        
    }
    
    
    
}
    

