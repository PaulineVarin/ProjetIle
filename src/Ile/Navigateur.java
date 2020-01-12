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
    //Données
    
    //Constructeur
    public Navigateur(String nom,IleInterdite ile) {
        super(nom,ile);
        super.setRole("Navigateur");
        super.setCouleur(CouleurJoueur.JAUNE);
        super.setDeplacementNavigateur(true);
    }
    
    //Méthodes
}
