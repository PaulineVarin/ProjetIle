/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vuesIHM;

import Enumeration.TypeRole;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 *
 * @author varinp
 */
public class VueResume {

    private VueJeu vueJeu;

    private JFrame window = new JFrame("VueRésumé");
    private JPanel contenu = new JPanel(new BorderLayout());
    private JPanel contenuCentre = new JPanel(new GridLayout(2, 1));

    private JLabel libel = new JLabel("Ile Interdite");
    private JTextArea libelDeroulementPartie = new JTextArea("");
    private JLabel libelTirage = new JLabel("Tirage Cartes");

    private JButton quitter = new JButton("Quitter");

    public VueResume(VueJeu vueJeu) {
        this.vueJeu = vueJeu;
        //Configuration boutons
        quitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                getVueJeu().fermerFenetres();
            }
        });

        //Configuration contenuCentre
        contenuCentre.add(libelDeroulementPartie);

        //Configuration contenu
        libel.setHorizontalAlignment(SwingConstants.CENTER);
        libel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));

        libelDeroulementPartie.setLineWrap(true);
        libelDeroulementPartie.setEditable(false);
        libelDeroulementPartie.setBackground(contenu.getBackground());
        contenu.add(libel, BorderLayout.NORTH);
        contenu.add(libelDeroulementPartie, BorderLayout.CENTER);
        contenu.add(quitter, BorderLayout.SOUTH);

        //Configuration fenetre
        window.setSize(330, 300);
        window.setUndecorated(true);
        window.setLocation((330 * 2) + 6, 110 + (200 / 2));
        window.add(contenu);
        window.setVisible(true);
    }

    //Méthodes
    public void initialisationVue(String nomJoueurCourant, int niveauEau) {
        libel.setText("Bienvenue dans l'Ile Interdite");
        libel.setForeground(Color.BLUE);
        if (nomJoueurCourant.equals(TypeRole.EXPLORATEUR.toString()) || nomJoueurCourant.equals(TypeRole.INGENIEUR.toString())) {
            libelDeroulementPartie.setText("L'" + nomJoueurCourant + " commence");
        } else {
            libelDeroulementPartie.setText("Le " + nomJoueurCourant + " commence");
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

}
