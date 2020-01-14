/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vuesIHM;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
    //Données
    private IHM ihm;
    private JFrame window = new JFrame("Inscription");
    private JPanel contenu = new JPanel(new BorderLayout());
    private JPanel contenuMillieu = new JPanel(new GridLayout(6, 2));
    private JLabel titre = new JLabel("Inscription des joueurs");
    private JLabel labelNbjoueurs = new JLabel("Nombre de joueurs");
    private JLabel labelNomJoueur1 = new JLabel("Nom du joueur n°1");
    private JLabel labelNomJoueur2 = new JLabel("Nom du joueur n°2");
    private JLabel labelNomJoueur3 = new JLabel("Nom du joueur n°3");
    private JLabel labelNomJoueur4 = new JLabel("Nom du joueur n°4");
    private JLabel labelNiveauEau = new JLabel("Niveau d'eau");
    private final JLabel [] labelsJoueurs = new JLabel[4];
    
    private JTextField nomJoueur1 = new JTextField();
    private JTextField nomJoueur2 = new JTextField();
    private JTextField nomJoueur3 = new JTextField();
    private JTextField nomJoueur4 = new JTextField();
    private final JTextField [] nomsJoueurs = new JTextField[4];
    
    private JButton jouer = new JButton("Jouer");

    private final Integer [] nbjoueurs = {2,3,4};
    private final String [] nomsNiveauEau = {"Novice","Normal","Elite","Légendaire"};
    private JComboBox listeNbJoueurs = new JComboBox(nbjoueurs);
    private JComboBox listeNiveauEau = new JComboBox(nomsNiveauEau);
   
    //Constructeur
    public VueInscription(IHM ihm) {
        //Configuration collections
        labelsJoueurs[0] = labelNomJoueur1;
        labelsJoueurs[1] = labelNomJoueur2;
        labelsJoueurs[2] = labelNomJoueur3;
        labelsJoueurs[3] = labelNomJoueur4;
        
        nomsJoueurs[0] = nomJoueur1;
        nomsJoueurs[1] = nomJoueur2;
        nomsJoueurs[2] = nomJoueur3;
        nomsJoueurs[3] = nomJoueur4;

        listeNbJoueurs.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent arg0) {
                //Modifier les choix possibles en fonction du nombre de joueurs
                int nbJoueurs = (int)listeNbJoueurs.getSelectedItem();
                
                for(int i = 0; i < labelsJoueurs.length; i++) {
                    labelsJoueurs[i].setEnabled(i < nbJoueurs);
                    nomsJoueurs[i].setEnabled(i < nbJoueurs);
                }
            }
        });
        
        //Configuration boutons
        jouer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                window.dispose();
                ArrayList<String> collectNomsJoueurs = new ArrayList<>();
                
                //Récupération du nombre de joueurs
                int nbJoueurs = (int)listeNbJoueurs.getSelectedItem();

                //Récupération du nom des joueurs
                for(int i =0;i<nbJoueurs;i++) {
                    String s = nomsJoueurs[i].getText();
                    if(s.equals("")) {
                        s = "Joueur"+i;
                    }
                    collectNomsJoueurs.add(s); 
                }
                //Récupération du niveau d'eau
                
                int niveauEau = 0;
                String niveau = listeNiveauEau.getSelectedItem().toString();
                if(niveau.equals("Novice")) {
                    niveauEau=1;
                }else if (niveau.equals("Normal")) {
                    niveauEau = 2;
                }else if(niveau.equals("Elite")) {
                    niveauEau = 3;
                }else {
                    niveauEau = 4;
                }
                
                ihm.initialisationJeu(collectNomsJoueurs, niveauEau, nbJoueurs);  
            }
        });
        
        //Configuration JLabel
        titre.setHorizontalAlignment(SwingConstants.CENTER);
      
        //Configuration panel contenuMillieu
        labelNomJoueur3.setEnabled(false);
        labelNomJoueur4.setEnabled(false);
        nomJoueur3.setEnabled(false);
        nomJoueur4.setEnabled(false);
        
        contenuMillieu.add(labelNbjoueurs);
        contenuMillieu.add(listeNbJoueurs);
        contenuMillieu.add(labelNomJoueur1);
        contenuMillieu.add(nomJoueur1);
        contenuMillieu.add(labelNomJoueur2);
        contenuMillieu.add(nomJoueur2);
        contenuMillieu.add(labelNomJoueur3);
        contenuMillieu.add(nomJoueur3);
        contenuMillieu.add(labelNomJoueur4);
        contenuMillieu.add(nomJoueur4);
        contenuMillieu.add(labelNiveauEau);
        contenuMillieu.add(listeNiveauEau);
        
        //Configuration panel contenu
        contenu.add(titre,BorderLayout.NORTH);
        contenu.add(contenuMillieu,BorderLayout.CENTER);
        contenu.add(jouer,BorderLayout.SOUTH);
        //Configuration fenetre
        window.setSize(500,500);
        window.add(contenu);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
    }
    //Méthodes
    
}
