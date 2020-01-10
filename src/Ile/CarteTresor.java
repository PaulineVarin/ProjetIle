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
public class CarteTresor extends CarteTirage{

    /**
     * @return the type
     */
    public TypeTresorCarte getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(TypeTresorCarte type) {
        this.type = type;
    }
    private TypeTresorCarte type;
}
