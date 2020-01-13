/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patterns.observateur;

import vuesIHM.*;
import Ile.*;


/**
 *
 * @author tardieue
 */
public class Controleur implements Observateur {
        
        private IHM ihm;
        private IleInterdite ileinterdite;
    
public Controleur(){
    this.ileinterdite = new IleInterdite(this);
    this.ihm = new IHM(this);
}   
    
    @Override
    public void traiterMessage(Object msg) {
         
        
        
    }
    
    
}
