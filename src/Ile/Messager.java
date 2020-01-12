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
public class Messager extends Aventurier {
    //Données
    
    
    //Constructeur
    public Messager(String nom,IleInterdite ile) {
        super(nom,ile);
        super.setRole(TypeRole.MESSAGER);
        super.setCouleur(CouleurJoueur.GRIS);
        //On ne gère pas le pouvoir du messager
    }
    
    //Méthodes
}
