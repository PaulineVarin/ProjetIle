package vuesIHM;

import Ile.Aventurier;
import Ile.CarteTirage;
import Enumeration.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JPanel contenuNbActions = new JPanel(new GridLayout(1, 2));
    private JPanel contenuBas = new JPanel(new GridLayout(2, 3));
    
    private JLabel nomJoueur = new JLabel("NomJoueur");
    private JLabel nomRole = new JLabel("nomRole");
    private JLabel listeTresors = new JLabel();
    private JLabel libelNbActions = new JLabel("Actions possibles : ");
    private JLabel nbactions = new JLabel();
    private JTextArea nomCartes = new JTextArea("Liste des cartes : ");
    
    private JButton deplacer = new JButton("Se déplacer");
    private JButton assecher = new JButton("Assécher");
    private JButton donner = new JButton("Donner trésor");
    private JButton prendre = new JButton("Prendre trésor");
    private JButton utiliserCarte = new JButton("Carte spéciale");
    private JButton finir = new JButton("Finir tour");
    
    private int x = 0;
    private int y = 110;
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
            j.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(JButton tuile : getVueJeu().getVueGrille().getListeBouttons()) {
                        tuile.setEnabled(false);
                    }
                    for(JButton j : listeBoutons) {
                        j.setEnabled(false);
                    }
                    if(e.getActionCommand().equals("Se déplacer")) {
                        TypeMessage typeM = TypeMessage.SE_DEPLACER;
                        //Recupère la vue du joueur courant 
                        VueJoueur joueur = getVueJeu().getVueJoueurCourant(getVueJeu().getNbJoueurCourant());
                        String nomRole = joueur.getNomRole().getText();
                        String s = joueur.getNbactions().getText();
                        int nbActions = Integer.parseInt(s);
                        System.out.println("Vue joueur : "+nomRole+nbActions+typeM);
                        getVueJeu().getIhm().choixJoueur(typeM,nomRole,nbActions);
                    }else if(e.getActionCommand().equals("Assécher")){
                        TypeMessage typeM = TypeMessage.ASSECHER;
                        VueJoueur joueur = getVueJeu().getVueJoueurCourant(getVueJeu().getNbJoueurCourant());
                        String nomRole = joueur.getNomRole().getText();
                        String s = joueur.getNbactions().getText();
                        int nbActions = Integer.parseInt(s);
                        System.out.println(nomRole+nbActions+typeM);
                        getVueJeu().getIhm().choixJoueur(typeM,nomRole,nbActions);
                    }else if(e.getActionCommand().equals("Donner trésor")) {
                        TypeMessage typeM = TypeMessage.DONNER;
                        VueJoueur joueur = getVueJeu().getVueJoueurCourant(getVueJeu().getNbJoueurCourant());
                        String nomRole = joueur.getNomRole().getText();
                        String s = joueur.getNbactions().getText();
                        int nbActions = Integer.parseInt(s);
                        System.out.println(nomRole+nbActions+typeM);
                        getVueJeu().getIhm().choixJoueur(typeM,nomRole,nbActions);
                    }else if (e.getActionCommand().equals("Finir tour")) {
                        TypeMessage typeM = TypeMessage.FIN_TOUR;
                        VueJoueur joueur = getVueJeu().getVueJoueurCourant(getVueJeu().getNbJoueurCourant());
                        String nomRole = joueur.getNomRole().getText();
                        String s = joueur.getNbactions().getText();
                        int nbActions = Integer.parseInt(s);
                        System.out.println(nomRole+nbActions+typeM);
                        getVueJeu().getIhm().choixJoueur(typeM,nomRole,nbActions); //a modifier
                    }
                }
            });
            j.setFont(new Font(Font.SANS_SERIF,Font.BOLD, 9));
            j.setEnabled(false);
        }
        //Configuraton de contenuHaut
        nomJoueur.setText(a.getNomJoueur());
        nomJoueur.setHorizontalAlignment(SwingConstants.CENTER);
        nomRole.setText(a.getStringRole());
        nomRole.setHorizontalAlignment(SwingConstants.CENTER);
        contenuHaut.add(nomJoueur);
        contenuHaut.add(nomRole);
        
        //Configuration de conteunuNbActions
        contenuNbActions.add(libelNbActions);
        nbactions.setText(Integer.toString(a.getNbaction()));
        contenuNbActions.add(nbactions);
        
        //Configuration de contenuMillieu
        nomCartes.setLineWrap(true);
        nomCartes.setEditable(false);
        nomCartes.setBackground(contenu.getBackground());

        contenuMillieu.add(contenuNbActions);
        for(CarteTirage ct : a.getCollectCartesJoueur()) {
            nomCartes.setText(nomCartes.getText()+" "+ct.getNom()+"; ");
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
        window.setSize(330,200);
        window.setUndecorated(Parameters.UNDECORATED);
        window.add(contenu);
        window.setVisible(true);
        
    }
    
    //Méthodes
    public void positionsFenetres() {
        setY(getY()+window.getHeight()/2);
        setX(0);
        for (VueJoueur joueur : getVueJeu().getVuesJoueurs()) {
            joueur.getWindow().setLocation(getX(), getY());
            setX(getX()+window.getWidth()+3); 
        }
        setX(0);
        if(getVueJeu().getVuesJoueurs().size()>2) {
            setY(getY()+210);
            getVueJeu().getVuesJoueurs().get(2).getWindow().setLocation(getX(),getY());
            getVueJeu().getVuesJoueurs().get(3).getWindow().setLocation(getX()+window.getWidth()+3, getY());
        }
    }
    
    public void debutTour() {
        for(JButton j : getListeBoutons()) {
            j.setEnabled(true);
        }
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

    /**
     * @return the listeBoutons
     */
    public ArrayList<JButton> getListeBoutons() {
        return listeBoutons;
    }

    /**
     * @return the nomRole
     */
    public JLabel getNomRole() {
        return nomRole;
    }

    /**
     * @return the nbactions
     */
    public JLabel getNbactions() {
        return nbactions;
    }
    
}
