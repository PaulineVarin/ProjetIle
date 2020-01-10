/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile;

import java.util.ArrayList;

/**
 *
 * @author tardieue
 */
public abstract class Aventurier {
    private final String nomJoueur;
    private String role;
    private int nbaction;

    private boolean deplacementDiagonal = false;
    private boolean assechementDiagonal = false;
    private boolean assechementDouble = false;
    private boolean deplacementPilote = false;
    private boolean deplacementPlongeur = false;
    private boolean deplacementNavigateur;

    private Tuile tuileDeDepart;
    private Tuile position;
    private CouleurJoueur couleur;
    private ArrayList<CarteTirage> collectCartesJoueur = new ArrayList<>();
    
    // constructeur
    public Aventurier(String nom) {
        this.nomJoueur = nom;
        this.position = this.tuileDeDepart;
    }
    
    // méthodes :
    // getters :
    /**
     * @return the nomJoueur
     */
    public String getNomJoueur() {
        return nomJoueur;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @return the nbaction
     */
    public int getNbaction() {
        return nbaction;
    }

    /**
     * @return the deplacementDiagonal
     */
    public boolean isDeplacementDiagonal() {
        return deplacementDiagonal;
    }

    /**
     * @return the assechementDiagonal
     */
    public boolean isAssechementDiagonal() {
        return assechementDiagonal;
    }

    /**
     * @return the assechementDouble
     */
    public boolean isAssechementDouble() {
        return assechementDouble;
    }

    /**
     * @return the deplacementPilote
     */
    public boolean isDeplacementPilote() {
        return deplacementPilote;
    }

    /**
     * @return the deplacementPlongeur
     */
    public boolean isDeplacementPlongeur() {
        return deplacementPlongeur;
    }

    /**
     * @return the deplacementNavigateur
     */
    public boolean isDeplacementNavigateur() {
        return deplacementNavigateur;
    }

    /**
     * @return the tuileDeDepart
     */
    public Tuile getTuileDeDepart() {
        return tuileDeDepart;
    }

    /**
     * @return the position
     */
    public Tuile getPosition() {
        return position;
    }

    /**
     * @return the couleur
     */
    public CouleurJoueur getCouleur() {
        return couleur;
    }

    // setter :
    /**
     * @param nbaction the nbaction to set
     */
    public void setNbaction(int nbaction) {
        this.nbaction = nbaction;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Tuile position) {
        this.position = position;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @param deplacementDiagonal the deplacementDiagonal to set
     */
    public void setDeplacementDiagonal(boolean deplacementDiagonal) {
        this.deplacementDiagonal = deplacementDiagonal;
    }

    /**
     * @param assechementDiagonal the assechementDiagonal to set
     */
    public void setAssechementDiagonal(boolean assechementDiagonal) {
        this.assechementDiagonal = assechementDiagonal;
    }

    /**
     * @param assechementDouble the assechementDouble to set
     */
    public void setAssechementDouble(boolean assechementDouble) {
        this.assechementDouble = assechementDouble;
    }

    /**
     * @param deplacementPilote the deplacementPilote to set
     */
    public void setDeplacementPilote(boolean deplacementPilote) {
        this.deplacementPilote = deplacementPilote;
    }

    /**
     * @param deplacementPlongeur the deplacementPlongeur to set
     */
    public void setDeplacementPlongeur(boolean deplacementPlongeur) {
        this.deplacementPlongeur = deplacementPlongeur;
    }

    /**
     * @param deplacementNavigateur the deplacementNavigateur to set
     */
    public void setDeplacementNavigateur(boolean deplacementNavigateur) {
        this.deplacementNavigateur = deplacementNavigateur;
    }

    /**
     * @param tuileDeDepart the tuileDeDepart to set
     */
    public void setTuileDeDepart(Tuile tuileDeDepart) {
        this.tuileDeDepart = tuileDeDepart;
    }

    /**
     * @param couleur the couleur to set
     */
    public void setCouleur(CouleurJoueur couleur) {
        this.couleur = couleur;
    }

    /**
     * @return the collectCartesJoueur
     */
    public ArrayList<CarteTirage> getCollectCartesJoueur() {
        return collectCartesJoueur;
    }

    /**
     * @param collectCartesJoueur the collectCartesJoueur to set
     */
    public void setCollectCartesJoueur(ArrayList<CarteTirage> collectCartesJoueur) {
        this.collectCartesJoueur = collectCartesJoueur;
    }
}
