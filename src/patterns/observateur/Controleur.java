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
public class Controleur implements Observateur<Message> {
        
        private IHM ihm;
        private IleInterdite ileinterdite;
    
public Controleur(){
    this.ileinterdite = new IleInterdite(this);
    this.ihm = new IHM(this);
}   

    @Override
    public void traiterMessage(Message msg) {
        if (msg.getTypeM() != null)
            switch(msg.getTypeM()){
                case INITIALISATION:
                    System.out.println("Noms des joueurs : J'attend collectnoms");
                    System.out.println("Niveau de l'eau : ");
                    System.out.println("Nombre de joueurs : ");
                    break;
                  
                case DEBUT_JEU:
                    System.out.println("Les tuiles sont :");
                    System.out.println("Les joueurs sont :");
                    System.out.println("Le niveau d'eau est :");
                
                 
                    
                   
                    
                
                
             
                    
            
        }

    }

    

    
    
}
