/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vuesIHM;

import Enumeration.*;
import Ile.Tuile;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Popup;

/**
 *
 * @author varinp
 */
public class VueGrille implements MouseListener{
    //Données
    private VueJeu vueJeu ;
    private JFrame window = new JFrame("Grille");
    private JPanel contenu = new JPanel(new BorderLayout());
    private JPanel contenuCentre = new JPanel(new GridLayout(6, 6));

    private ArrayList<JButton> listeBouttons  = new ArrayList<>();
    
    
    //Constructeur
    public VueGrille(VueJeu vueJeu) {
        this.vueJeu = vueJeu;
       //Configuration contenuCentre

       for (int i=0;i<36;i++) {
           listeBouttons.add(new JButton("")); 
       }
       
       for(JButton j : listeBouttons) {
           j.addMouseListener(this);
       }
       
       for (int i=0;i<36;i++) {
           JButton j = listeBouttons.get(i);
           if (i==0 || i==1 || i==4 || i==5 || i==6 || i==11 ||i==24 ||i==29 || i==30 || i==31 || i==34 || i==35) {
               j.setVisible(false);
           }
           contenuCentre.add(j);
       }
       contenuCentre.setBackground(Parameters.PLATEAU_BG);

       //Configuration contenu
       contenu.add(contenuCentre,BorderLayout.CENTER);
       
       //Configuration fenetre
       
       window.add(contenu);
       window.setUndecorated(Parameters.UNDECORATED);
       window.setSize(1250, 500);
       window.setLocation(0,0);
       window.setVisible(true);
    }
    
    //Méthodes
    public void initialiserPlateau(ArrayList<Tuile> collectTuiles) {
        JButton j = new JButton();
        for (int i=0;i<collectTuiles.size();i++) {
            //Ajout du nom des tuiles sur les boutons
            if (i!=0 && i!=1 && i!=4 && i!=5 && i!=6 && i!=11 && i!=24 && i!=29 && i!=30 && i!=31 && i!=34 && i!=35) {
               j = getListeBouttons().get(i);
               j.setText(collectTuiles.get(i).getNomTuile());
               j.setBackground(Parameters.TUILE_ASSECHEE_BG);
               j.setFont(new Font(Font.SERIF,Font.ITALIC, 12));
           }
            //Ajout de la couleur pour les tuiles inondées
            if(collectTuiles.get(i).getEtat().equals(EtatTuile.INONDEE)) {
              j.setBackground(Parameters.TUILE_INONDEE_BG);
            }
            
            //Ajout de la couleur pour les joueurs 
            if(collectTuiles.get(i).getJoueurDepart()!=null) {
                if(collectTuiles.get(i).getJoueurDepart().getRole().equals(TypeRole.EXPLORATEUR)) {
                    j.setForeground(Color.GREEN);
                    j.setFont(new Font(Font.SANS_SERIF,Font.BOLD, 15));
                    
                }else if(collectTuiles.get(i).getJoueurDepart().getRole().equals(TypeRole.INGENIEUR)) {
                    j.setForeground(Color.RED); 
                    j.setFont(new Font(Font.SANS_SERIF,Font.BOLD, 15));
                    
                }else if(collectTuiles.get(i).getJoueurDepart().getRole().equals(TypeRole.MESSAGER)) {
                    j.setForeground(Color.GRAY);
                    j.setFont(new Font(Font.SANS_SERIF,Font.BOLD, 15));
                    
                }else if(collectTuiles.get(i).getJoueurDepart().getRole().equals(TypeRole.NAVIGATEUR)) {
                    j.setForeground(Color.YELLOW);
                    j.setFont(new Font(Font.SANS_SERIF,Font.BOLD, 15));
                    
                }else if(collectTuiles.get(i).getJoueurDepart().getRole().equals(TypeRole.PILOTE)) {
                    j.setForeground(Color.BLUE);
                    j.setFont(new Font(Font.SANS_SERIF,Font.BOLD, 15));
                    
                }else {
                    j.setForeground(Color.BLACK);
                    j.setFont(new Font(Font.SANS_SERIF,Font.BOLD, 15));
                }
            }
        } 
    }  
    @Override
    public void mouseClicked(MouseEvent arg0) {
        JButton j =(JButton) arg0.getSource();
        int x = arg0.getXOnScreen();
        int y = arg0.getYOnScreen();
        
        //Creation du pop-up (a mettre dans une méthode
        JFrame popUp = new JFrame("Trésor");
        JPanel contenu = new JPanel(new BorderLayout());
        JLabel nomTresor = new JLabel("TEST");
        JButton fermer = new JButton("Fermer");
        contenu.add(nomTresor,BorderLayout.CENTER);
        contenu.add(fermer,BorderLayout.SOUTH);
        contenu.setBackground(Color.red);
        popUp.add(contenu);
        popUp.setSize(200,200);
        popUp.setLocation(x, y);
        popUp.setUndecorated(true);
        popUp.setVisible(true);

        if(j.getText().equals("Le Palais des Marées") || j.getText().equals("Le Palais de Corail")) {
            nomTresor.setText("CALICE");
        }
        fermer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                popUp.dispose();
            }
        });
    }
    
    /**
     * @return the window
     */
    public JFrame getWindow() {
        return window;
    }
    
    /**
     * @return the listeBouttons
     */
    public ArrayList<JButton> getListeBouttons() {
        return listeBouttons;
    }
    @Override
    public void mousePressed(MouseEvent arg0) {}
    @Override
    public void mouseReleased(MouseEvent arg0) {}
    @Override
    public void mouseEntered(MouseEvent arg0) {}
    @Override
    public void mouseExited(MouseEvent arg0) {}
}
