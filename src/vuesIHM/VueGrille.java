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
       for (int i=0;i<36;i++) {
           listeBouttons.add(new JButton("")); 
       }
       
       for (int i=0;i<36;i++) {
           JButton j = listeBouttons.get(i);
           if (i==0 || i==1 || i==4 || i==5 || i==6 || i==11 ||i==24 ||i==29 || i==30 || i==31 || i==34 || i==35) {
               j.setVisible(false);
           }
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
        for (int i=0;i<collectTuiles.size();i++) {
            if (i!=0 && i!=1 && i!=4 && i!=5 && i!=6 && i!=11 && i!=24 && i!=29 && i!=30 && i!=31 && i!=34 && i!=35) {
               JButton j = listeBouttons.get(i);
               j.setText(collectTuiles.get(i).getNomTuile());
           }
        } 
    }  
}
