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
public abstract class CarteTirage {
    //Données
    private String nom;
    
    //Constructeur
    public CarteTirage(String nom) {
        this.nom = nom;
    }
    
    
    //Méthodes
    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }


}
