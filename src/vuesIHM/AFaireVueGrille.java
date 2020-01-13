/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vuesIHM;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author varinp
 */
public class AFaireVueGrille {
    private VueJeu vueJeu ;
    
    private JFrame window = new JFrame("Grille");
    private JPanel contenu = new JPanel(new BorderLayout());
    private JPanel contenuCentre = new JPanel(new GridLayout(6, 6));
    private ArrayList<JButton> listeBouttons  = new ArrayList<>();
    
    public AFaireVueGrille(VueJeu vueJeu) {
        this.vueJeu = vueJeu;
       //Configuration contenuCentre
       
       //Configuration contenu
       contenu.add(contenuCentre,BorderLayout.CENTER);
       
       //Configuration fenetre
       window.setSize(500,500);
       window.setDefaultCloseOperation(window.EXIT_ON_CLOSE); //A supprimer lors de la mise en place
       window.setVisible(true);
       
      
    }
    
  
    
    
}
