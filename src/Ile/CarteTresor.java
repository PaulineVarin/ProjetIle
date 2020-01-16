/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile;

import Enumeration.TypeTresorCarte;
import java.util.ArrayList;

/**
 *
 * @author tardieue
 */
public class CarteTresor extends CarteTirage{
    //Données
    private TypeTresorCarte typeTresor;

    //Constructeur
    public CarteTresor (String nom,TypeTresorCarte typeTresor) {
        super(nom);
        setTypeTresor(typeTresor);        
    }
    
    public CarteTresor (CarteTresor cte) {
        super(cte.getNom());
        setTypeTresor(cte.getTypeTresor());
    }
    
    //Méthodes
    public ArrayList<CarteTresor> getCartesTresors() {
        ArrayList<CarteTresor> collectCartesTresors = new ArrayList<>();
        for (int i=0; i< getCartesTresors().size(); i++) {
            
        }

        return collectCartesTresors;
    }

    /**
     * @return the typeTresor
     */
    public TypeTresorCarte getTypeTresor() {
        return typeTresor;
    }

    /**
     * @param typeTresor the typeTresor to set
     */
    public void setTypeTresor(TypeTresorCarte typeTresor) {
        this.typeTresor = typeTresor;
    }
    

}
