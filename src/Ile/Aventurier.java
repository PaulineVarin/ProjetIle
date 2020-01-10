/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile;

/**
 *
 * @author tardieue
 */
public class Aventurier {
    private String nomJoueur, role;
    private int nbaction;
    private boolean deplacementDiagonal, assechementDiagonal, assechementDouble, deplacementPilote, deplacementPlongeur, deplacementNavigateur;

    private Tuile tuileDeDepart, position;
    private CouleurJoueur couleur;
    
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
}
