/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vuesIHM;

import Ile.Tuile;
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
public class VueGrille {
    //Données
    private VueJeu vueJeu ;
    private JFrame window = new JFrame("Grille");
    private JPanel contenu = new JPanel(new BorderLayout());
    private JPanel contenuCentre = new JPanel(new GridLayout(6, 6));
    private JPanel contenuTest = new JPanel(new GridLayout(1, 1));
    
    
    private ArrayList<JButton> listeBouttons  = new ArrayList<>();
    
    //Constructeur
    public VueGrille(VueJeu vueJeu) {
        this.vueJeu = vueJeu;
       //Configuration contenuCentre
       for (int i=0;i<Parameters.NB_TUILES;i++) {
           listeBouttons.add(new JButton(""+i));  
       }
       
       for (JButton j : listeBouttons) {
           j.setVisible(true);
           contenuCentre.add(j);
       }
       
       //Configuration contenu
       contenu.add(contenuCentre,BorderLayout.CENTER);
       
       //Configuration fenetre
       window.add(contenu);
       window.setSize(500,500);
       window.setVisible(true);
    }
    
    //Méthodes
    public void initialiserPlateau(ArrayList<Tuile> collectTuiles) {
        for (int i=0;i<listeBouttons.size();i++) {
            JButton j = listeBouttons.get(i);
            j.setText(collectTuiles.get(i).getNomTuile());
        }    
    }  
}
