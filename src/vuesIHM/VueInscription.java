/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vuesIHM;
import Enumeration.TypeMessage;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import patterns.observateur.Message;

/**
 *
 * @author varinp
 */
public class VueInscription {
    private IHM ihm;
    private JFrame window = new JFrame("Inscription");
    private JPanel contenu = new JPanel(new BorderLayout());
    private JPanel contenuMillieu = new JPanel(new GridLayout(6, 2));
    private JLabel titre = new JLabel("Inscription des joueurs");
    private JLabel libelNbjoueurs = new JLabel("Nombre de joueurs");
    private JLabel libelNomJoueur1 = new JLabel("Nom du joueur n°1");
    private JLabel libelNomJoueur2 = new JLabel("Nom du joueur n°2");
    private JLabel libelNomJoueur3 = new JLabel("Nom du joueur n°3");
    private JLabel libelNomJoueur4 = new JLabel("Nom du joueur n°4");
    private JLabel libelNiveauEau = new JLabel("Niveau d'eau");
    private JTextField nomJoueur1 = new JTextField();
    private JTextField nomJoueur2 = new JTextField();
    private JTextField nomJoueur3 = new JTextField();
    private JTextField nomJoueur4 = new JTextField();
    private final String [] nbjoueurs = {"2","3","4"};
    private JComboBox listeNbJoueurs = new JComboBox(nbjoueurs);
    
    private final String [] niveauEau = {"E","1"};
    private JComboBox listeNiveauEau = new JComboBox(niveauEau);
    
    private JButton jouer = new JButton("Jouer");
    
    public VueInscription(IHM ihm) {
        //Configuration boutons
        jouer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
              int nbJoueurs = (int)listeNbJoueurs.getSelectedItem();
              for (int i=0;i<nbJoueurs;i++) {
                  //collect avec nomjoueurs + vérifier que tout les noms sont initialisées
                  //recupère niveau eau (transformation du texte en int)
                  //
                  
              }
              
              
                
            }
        });
        
        //Configuration JLabel
        titre.setHorizontalAlignment(SwingConstants.CENTER);
      
        //Configuration panel contenuMillieu
        libelNomJoueur3.setEnabled(false);
        libelNomJoueur4.setEnabled(false);
        nomJoueur3.setEnabled(false);
        nomJoueur4.setEnabled(false);
        
        contenuMillieu.add(libelNbjoueurs);
        contenuMillieu.add(listeNbJoueurs);
        contenuMillieu.add(libelNomJoueur1);
        contenuMillieu.add(nomJoueur1);
        contenuMillieu.add(libelNomJoueur2);
        contenuMillieu.add(nomJoueur2);
        contenuMillieu.add(libelNomJoueur3);
        contenuMillieu.add(nomJoueur3);
        contenuMillieu.add(libelNomJoueur4);
        contenuMillieu.add(nomJoueur4);
        contenuMillieu.add(libelNiveauEau);
        contenuMillieu.add(listeNiveauEau);
        
        //Configuration panel contenu
        contenu.add(titre,BorderLayout.NORTH);
        contenu.add(contenuMillieu,BorderLayout.CENTER);
        contenu.add(jouer,BorderLayout.SOUTH);
        //Configuration fenetre
        window.setSize(500,500);
        window.setDefaultCloseOperation(window.EXIT_ON_CLOSE); //A supprimer lors de la mise en place
        window.add(contenu);
        window.setVisible(true);
        
    }
    
}
