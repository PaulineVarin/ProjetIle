/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile;

import Enumeration.*;
import Enumeration.CouleurJoueur;
import Enumeration.TypeMessage;
import java.util.ArrayList;

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
    public ArrayList<Tuile> calculCases(TypeMessage typeM) {
        Tuile t = getTuileCourante();
        IleInterdite ile = getIle();
        Grille g = ile.getGrille();
        ArrayList<Tuile> collectCases = new ArrayList<>();

        if ((typeM.equals(TypeMessage.DONNER) == false) && (typeM.equals(TypeMessage.PRENDRE) == false)) {
            System.out.println("calcul casesHello gettuiles");
            collectCases = g.getTuiles(t, typeM, this);
        } else {
            System.out.println("donner+prendre");
            if (t.getCollectAventuriers().size() > 1 && typeM.equals(TypeMessage.DONNER)) {
                System.out.println("A");
                collectCases.add(t);
            } else if(t.getTresor().equals(TypeTresorTuile.NEUTRE) == false) {
                System.out.println("B");
                collectCases.add(t);
            }
            System.out.println("Taille collect"+collectCases.size());
        }
        return collectCases;
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

    public CarteTirage getCarte(String nomCarte) {
        for (CarteTirage ct : getCollectCartesJoueur()) {
            if (ct.getNom().equals(nomCarte)) {
                return ct;
            }
        }
        return null;
    }

    public void removeCarteTirage(CarteTirage cti) {
        getCollectCartesJoueur().remove(cti);

    }
    
    public CarteTresor majCarteDonneur(String nomCarte) {
        CarteTresor cte = (CarteTresor) getCarte(nomCarte);
        removeCarteTirage(cte);

        return (cte);
    }
    
    public void majCarteReceveur(CarteTresor cte) {
        getCollectCartesJoueur().add(cte);
    }
    
    public ArrayList<CarteTresor> getCartesTresors() {
        ArrayList<CarteTresor> cte = new ArrayList<>();
        for (int i=0; i<getCollectCartesJoueur().size(); i++) {
            if (getCollectCartesJoueur().get(i).getClass().toString() == "CarteTresor") {
                cte.add((CarteTresor) getCollectCartesJoueur().get(i));
            }
        }

        return (cte);
    }

    void removeCarteTirage(ArrayList<CarteTresor> cartes) {
        for (int i=0; i<cartes.size(); i++) {
            getCollectCartesJoueur().remove(cartes.get(i));
        }
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
