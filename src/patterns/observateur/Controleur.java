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
    //Données
    private IHM ihm;
    private IleInterdite ileInterdite;
    
    //Constructeur
    public Controleur(){
        this.ileInterdite = new IleInterdite(this);
        this.ihm = new IHM(this);
    }   

    
    public void traiterMessage(Message msg) {
        if (msg.getTypeM() != null) {
            switch(msg.getTypeM()) {
                case INITIALISATION:
                    System.out.println("Noms des joueurs :");
                    System.out.println("Niveau d'eau :");
                    System.out.println("Nombre de joueurs :");
                    break;
                case DEBUT_JEU:
                    System.out.println("Les Tuiles :");
                    System.out.println("Les joueurs :");
                    System.out.println("Niveau d'eau :");
                    System.out.println("Nombre de joueurs :");
                    break;
                
                case DEBUT_TOUR:
                
                break;
            
                

            
        }


    }


}

    }

