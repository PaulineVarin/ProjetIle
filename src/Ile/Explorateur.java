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
public class Explorateur extends Aventurier {
    public Explorateur(String nom) {
        super(nom);
        super.setRole("explorateur");
        super.setCouleur(CouleurJoueur.VERT);
        super.setAssechementDiagonal(true);
        super.setDeplacementDiagonal(true);
    }
}
