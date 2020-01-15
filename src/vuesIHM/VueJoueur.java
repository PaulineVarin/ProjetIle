package vuesIHM;

import Ile.Aventurier;
import Ile.CarteTirage;
import Enumeration.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

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
    private JPanel contenuMillieu = new JPanel(new GridLayout(3, 1));
    private JPanel contenuBas = new JPanel(new GridLayout(2, 3));
    
    private JLabel nomJoueur = new JLabel("NomJoueur");
    private JLabel nomRole = new JLabel("nomRole");
    private JLabel listeTresors = new JLabel();
    private JTextArea nomCartes = new JTextArea("Liste des cartes : ");
    private JLabel nbactions = new JLabel("Actions possibles : ");
    
    private JButton deplacer = new JButton("Se déplacer");
    private JButton assecher = new JButton("Assécher");
    private JButton donner = new JButton("Donner trésor");
    private JButton prendre = new JButton("Prendre trésor");
    private JButton utiliserCarte = new JButton("Carte spéciale");
    private JButton finir = new JButton("Finir tour");
    
    private int x = 0;
    private int y = 500;
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
        //Configuration boutons 
        for (JButton j : listeBoutons) {
            j.setFont(new Font(Font.SANS_SERIF,Font.BOLD, 9));
        }
         
        
        
        
        //Configuraton de contenuHaut
        nomJoueur.setText(a.getNomJoueur());
        nomJoueur.setHorizontalAlignment(SwingConstants.CENTER);
        nomRole.setText(a.getStringRole());
        nomRole.setHorizontalAlignment(SwingConstants.CENTER);
        contenuHaut.add(nomJoueur);
        contenuHaut.add(nomRole);
        
        //Configuration de contenuMillieu
        nbactions.setText(nbactions.getText()+Integer.toString(a.getNbaction()));
        nomCartes.setLineWrap(true);
        nomCartes.setEditable(false);
        nomCartes.setBackground(contenu.getBackground());

        contenuMillieu.add(nbactions);
        for(CarteTirage ct : a.getCollectCartesJoueur()) {
            nomCartes.setText(nomCartes.getText()+" "+ct.getNom()+";");
        }
        contenuMillieu.add(nomCartes);
        listeTresors.setText("Aucun trésor");
        contenuMillieu.add(listeTresors);

        //Configuration de contenuBas
        for (JButton j : listeBoutons) {
            contenuBas.add(j);
        }
        
        //Configuration de contenu
        if (a.getRole().equals(TypeRole.EXPLORATEUR)) {
            Border lineborder = BorderFactory.createLineBorder(Color.GREEN, Parameters.SWING_BORDERS_HEIGHT);
            contenu.setBorder(lineborder);
        }else if(a.getRole().equals(TypeRole.INGENIEUR)) {
            Border lineborder = BorderFactory.createLineBorder(Color.RED, Parameters.SWING_BORDERS_HEIGHT);
            contenu.setBorder(lineborder);
        }else if(a.getRole().equals(TypeRole.MESSAGER)) {
            Border lineborder = BorderFactory.createLineBorder(Color.GRAY, Parameters.SWING_BORDERS_HEIGHT);
            contenu.setBorder(lineborder);
        }else if(a.getRole().equals(TypeRole.NAVIGATEUR)) {
            Border lineborder = BorderFactory.createLineBorder(Color.YELLOW, Parameters.SWING_BORDERS_HEIGHT);
            contenu.setBorder(lineborder); 
        }else if(a.getRole().equals(TypeRole.PILOTE)) {
            Border lineborder = BorderFactory.createLineBorder(Color.BLUE, Parameters.SWING_BORDERS_HEIGHT);
            contenu.setBorder(lineborder);
        }else {
            Border lineborder = BorderFactory.createLineBorder(Color.BLACK, Parameters.SWING_BORDERS_HEIGHT);
            contenu.setBorder(lineborder);
        }
         
        contenu.add(contenuHaut, BorderLayout.NORTH);
        contenu.add(contenuMillieu, BorderLayout.CENTER);
        contenu.add(contenuBas,BorderLayout.SOUTH);
        
        
        //Configuration window
        window.setSize(330,300);
        window.setUndecorated(Parameters.UNDECORATED);
        window.add(contenu);
        window.setVisible(true);
        
    }
    
    //Méthodes
    public void positionsFenetres() {
        for (VueJoueur joueur : getVueJeu().getVuesJoueurs()) {
            joueur.getWindow().setLocation(getX(), getY());
            setX(getX()+window.getWidth()+3);
        }
        setX(0);
    }

    /**
     * @return the vueJeu
     */
    public VueJeu getVueJeu() {
        return vueJeu;
    }

    /**
     * @return the window
     */
    public JFrame getWindow() {
        return window;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }
    
}
