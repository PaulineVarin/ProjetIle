 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile;

import Enumeration.TypeRole;
import Enumeration.CouleurJoueur;
import Enumeration.EtatTuile;
import Enumeration.TypeMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author tardieue
 */
public abstract class Aventurier {

    //Données
    private String nomJoueur;
    private TypeRole role;
    private int nbaction;
    private CouleurJoueur couleur; //A voir
    private Tuile tuileDeDepart;
    private Tuile tuileCourante;
    private IleInterdite ile;

    private boolean deplacementDiagonal = false;
    private boolean assechementDiagonal = false;
    private boolean assechementDouble = false;
    private boolean deplacementPilote = false;
    private boolean deplacementPlongeur = false;
    private boolean deplacementNavigateur = false;

    private ArrayList<CarteTirage> collectCartesJoueur = new ArrayList<>();

    // constructeur
    public Aventurier(String nom, IleInterdite ile) {
        setIle(ile);
        setNomJoueur(nom);
        setNbaction(3);
    }

    //Méthodes
    public ArrayList<Tuile> calculCases(TypeMessage action) {
        ArrayList<Tuile> tuiles = new ArrayList<>();
        Tuile t = getTuileCourante();
        Grille g = getIle().getGrille();

        if (action != TypeMessage.DONNER & action != TypeMessage.PRENDRE) {
            tuiles = getTuiles(t, action, this);
        } else {
            tuiles.add(getTuileCourante());
        }

        return tuiles;
    }

    //a refaire car pas prise en compte de la hasmap
    private ArrayList<Tuile> getTuiles(Tuile t, TypeMessage action, Aventurier a) {
        ArrayList<Tuile> cases = new ArrayList<>();     // pour le return
        ArrayList<Tuile> tuiles = new ArrayList<>();    // pour les calculs
        Grille g = a.getIle().getGrille();
        // tuiles = g.getCollectTuiles();
        EtatTuile etatTuile;
        int id = t.getIdTuile();

        if (action == TypeMessage.SE_DEPLACER) {
            for (int i = 0; i < tuiles.size(); i++) {
                int idb = tuiles.get(i).getIdTuile();
                if (idb == id - 1 || idb == id + 1 || idb == id + 10 || idb == id - 10) {
                    if (tuiles.get(i).getEtat() != EtatTuile.COULEE) {
                        cases.add(tuiles.get(i));
                    }
                }
            }

            if (a.isDeplacementDiagonal()) {
                for (int i = 0; i < tuiles.size(); i++) {
                    int idb = tuiles.get(i).getIdTuile();
                    if (idb == id - 11 || idb == id + 11 || idb == id - 9 || idb == id + 9) {
                        if (tuiles.get(i).getEtat() != EtatTuile.COULEE) {
                            cases.add(tuiles.get(i));
                        }
                    }
                }
            }
        } else if (action == TypeMessage.ASSECHER) {
            for (int i = 0; i < tuiles.size(); i++) {
                int idb = tuiles.get(i).getIdTuile();
                if (idb == id - 1 || idb == id + 1 || idb == id - 10 || idb == id + 10) {
                    if (tuiles.get(i).getEtat() == EtatTuile.INONDEE) {
                        cases.add(tuiles.get(i));
                    }
                }
            }

            if (a.isAssechementDiagonal()) {
                for (int i = 0; i < tuiles.size(); i++) {
                    int idb = tuiles.get(i).getIdTuile();
                    if (idb == id - 11 || idb == id + 11 || idb == id - 9 || idb == id + 9) {
                        if (tuiles.get(i).getEtat() == EtatTuile.INONDEE) {
                            cases.add(tuiles.get(i));
                        }
                    }
                }
            }
        } else if (action == TypeMessage.DEPLACEMENT_SPE) {
            for (int i = 0; i < tuiles.size(); i++) {
                if (tuiles.get(i).getEtat() != EtatTuile.COULEE) {
                    cases.add(tuiles.get(i));
                }
            }
        } else if (action == TypeMessage.ASSECHER_SPE) {
            for (int i = 0; i < tuiles.size(); i++) {
                if (tuiles.get(i).getEtat() == EtatTuile.INONDEE) {
                    cases.add(tuiles.get(i));
                }
            }
        }
        return cases;
    }

    public int MiseAJourNbActions() {

        int nbactions = 3;

        if (getNbaction() == 0) {
            nbactions = 3;
        }

        if (getNbaction() == 1) {
            nbactions = 2;
        }

        if (getNbaction() == 2) {
            nbactions = 1;
        }

        if (getNbaction() == 3) {
            nbactions = 0;
        }

        return nbactions;

    }

    public int getNbCartes() {
        
        return collectCartesJoueur.size();

    }

    
    public CarteTirage getCarte(String nomCarte){
        for (CarteTirage ct : collectCartesJoueur){
            if (ct.getNom().equals(nomCarte)){
                return ct;
            }
        }
        return null;
    }
    
    public void removeCarteTirage(CarteTirage cti){
        collectCartesJoueur.remove(cti);
        
    }
    // getters setters :
    /**
     * @return the nomJoueur
     */
    public String getNomJoueur() {
        return nomJoueur;
    }

    /**
     * @param nomJoueur the nomJoueur to set
     */
    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }

    /**
     * @return the role
     */
    public TypeRole getRole() {
        return role;
    }

    /**
     * @return the role
     */
    public String getStringRole() {
        return getRole().toString();
    }

    /**
     * @param role the role to set
     */
    public void setRole(TypeRole role) {
        this.role = role;
    }

    /**
     * @return the nbaction
     */
    public int getNbaction() {
        return nbaction;
    }

    /**
     * @param nbaction the nbaction to set
     */
    public void setNbaction(int nbaction) {
        this.nbaction = nbaction;
    }

    /**
     * @return the couleur
     */
    public CouleurJoueur getCouleur() {
        return couleur;
    }

    /**
     * @param couleur the couleur to set
     */
    public void setCouleur(CouleurJoueur couleur) {
        this.couleur = couleur;
    }

    /**
     * @return the tuileDeDepart
     */
    public Tuile getTuileDeDepart() {
        return tuileDeDepart;
    }

    /**
     * @param tuileDeDepart the tuileDeDepart to set
     */
    public void setTuileDeDepart(Tuile tuileDeDepart) {
        this.tuileDeDepart = tuileDeDepart;
    }

    /**
     * @return the tuileCourante
     */
    public Tuile getTuileCourante() {
        return tuileCourante;
    }

    /**
     * @param tuileCourante the tuileCourante to set
     */
    public void setTuileCourante(Tuile tuileCourante) {
        this.tuileCourante = tuileCourante;
    }

    /**
     * @return the ile
     */
    public IleInterdite getIle() {
        return ile;
    }

    /**
     * @param ile the ile to set
     */
    public void setIle(IleInterdite ile) {
        this.ile = ile;
    }

    /**
     * @return the deplacementDiagonal
     */
    public boolean isDeplacementDiagonal() {
        return deplacementDiagonal;
    }

    /**
     * @param deplacementDiagonal the deplacementDiagonal to set
     */
    public void setDeplacementDiagonal(boolean deplacementDiagonal) {
        this.deplacementDiagonal = deplacementDiagonal;
    }

    /**
     * @return the assechementDiagonal
     */
    public boolean isAssechementDiagonal() {
        return assechementDiagonal;
    }

    /**
     * @param assechementDiagonal the assechementDiagonal to set
     */
    public void setAssechementDiagonal(boolean assechementDiagonal) {
        this.assechementDiagonal = assechementDiagonal;
    }

    /**
     * @return the assechementDouble
     */
    public boolean isAssechementDouble() {
        return assechementDouble;
    }

    /**
     * @param assechementDouble the assechementDouble to set
     */
    public void setAssechementDouble(boolean assechementDouble) {
        this.assechementDouble = assechementDouble;
    }

    /**
     * @return the deplacementPilote
     */
    public boolean isDeplacementPilote() {
        return deplacementPilote;
    }

    /**
     * @param deplacementPilote the deplacementPilote to set
     */
    public void setDeplacementPilote(boolean deplacementPilote) {
        this.deplacementPilote = deplacementPilote;
    }

    /**
     * @return the deplacementPlongeur
     */
    public boolean isDeplacementPlongeur() {
        return deplacementPlongeur;
    }

    /**
     * @param deplacementPlongeur the deplacementPlongeur to set
     */
    public void setDeplacementPlongeur(boolean deplacementPlongeur) {
        this.deplacementPlongeur = deplacementPlongeur;
    }

    /**
     * @return the deplacementNavigateur
     */
    public boolean isDeplacementNavigateur() {
        return deplacementNavigateur;
    }

    /**
     * @param deplacementNavigateur the deplacementNavigateur to set
     */
    public void setDeplacementNavigateur(boolean deplacementNavigateur) {
        this.deplacementNavigateur = deplacementNavigateur;
    }

    /**
     * @return the collectCartesJoueur
     */
    public ArrayList<CarteTirage> getCollectCartesJoueur() {
        return collectCartesJoueur;
    }

    public void addCollectCartesJoueur(CarteTirage ct) {
        this.getCollectCartesJoueur().add(ct);
    }
}
