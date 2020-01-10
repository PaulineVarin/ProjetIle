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
public class Plongeur extends Aventurier {
    public Plongeur(String nom) {
        super(nom);
        super.setDeplacementPlongeur(true);
        super.setCouleur(CouleurJoueur.NOIR);
    }
}
