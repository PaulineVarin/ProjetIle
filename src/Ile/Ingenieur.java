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
public class Ingenieur extends Aventurier {
    public Ingenieur (String nom, CouleurJoueur coul) {
        super(nom);
        super.setRole("ingenieur");
        super.setAssechementDouble(true);
        super.setCouleur(CouleurJoueur.ROUGE);
    }
}
