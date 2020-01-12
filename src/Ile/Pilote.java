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
public class Pilote extends Aventurier {
    //Données
    
    
    //Constructeur
    public Pilote(String nom,IleInterdite ile) {
        super(nom,ile);
        super.setRole(TypeRole.PILOTE);
        super.setCouleur(CouleurJoueur.BLEU);
        super.setDeplacementPilote(true);
    }
    
    //Méthodes
}
