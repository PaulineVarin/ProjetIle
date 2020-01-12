/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile;

import Enumeration.TypeRole;
import Enumeration.CouleurJoueur;

/**
 *
 * @author tardieue
 */
public class Explorateur extends Aventurier {
    //Données
    
    
    //Constructeur
    public Explorateur(String nom,IleInterdite ile) {
        super(nom,ile);
        super.setRole(TypeRole.EXPLORATEUR);
        super.setCouleur(CouleurJoueur.VERT);
        super.setAssechementDiagonal(true);
        super.setDeplacementDiagonal(true);
    }
    //Méthodes
}
