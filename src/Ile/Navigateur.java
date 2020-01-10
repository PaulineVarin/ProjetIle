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
public class Navigateur extends Aventurier {
    public Navigateur(String nom) {
        super(nom);
        super.setCouleur(CouleurJoueur.JAUNE);
        super.setRole("navigateur");
        super.setDeplacementNavigateur(true);
    }
}
