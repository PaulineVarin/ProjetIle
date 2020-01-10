/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile;

import patterns.observateur.Observe;
import java.util.ArrayList;



/**
 *
 * @author tardieue
 */
public class IleInterdite extends Observe {
    
    private int niveauEau;
    private ArrayList <String> tresorsRecuperes = new ArrayList<>();
    private ArrayList <CarteTirage> carteTiragesTires = new ArrayList <>();
    private ArrayList <CarteTirage> carteTiragesDefausses = new ArrayList <>();
    private ArrayList <CarteInondation> carteInondesTires = new ArrayList <>();
    private ArrayList <CarteInondation> carteInondesDefausses = new ArrayList <>();
    private Grille grille = null;
    private Aventurier aventurier = null;
    
    
    
}
