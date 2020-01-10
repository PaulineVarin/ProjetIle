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
public class Messager extends Aventurier {
    //il n'y a pas de booléen correspondant à son pouvoir. Penser à programmer la méthode donnerCarte()
    public Messager(String nom) {
        super(nom);
        super.setRole("messager");
        super.setCouleur(CouleurJoueur.GRIS);
    }
}
