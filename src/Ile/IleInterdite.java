/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ile;

import Enumeration.*;
import Enumeration.TypeRole;
import Enumeration.TypeTresorTuile;
import patterns.observateur.Observe;
import patterns.observateur.Observateur;
import java.util.ArrayList;
import java.util.Collections;
import patterns.observateur.Message;
import vuesIHM.Parameters;

/**
 *
 * @author tardieue
 */
public class IleInterdite extends Observe<Message> {

    //Données
    private int niveauEau;
    private Grille grille;
    private ArrayList<TypeTresorTuile> tresorsRecuperes = new ArrayList<>();
    private ArrayList<CarteTirage> cartesTirageTire = new ArrayList<>();
    private ArrayList<CarteTirage> cartesTirageDefausse = new ArrayList<>();
    private ArrayList<CarteInondation> cartesInondeTire = new ArrayList<>();
    private ArrayList<CarteInondation> carteInondeDefausse = new ArrayList<>();
    private ArrayList<Aventurier> aventuriers = new ArrayList<>();

    //Constructeur    
    public IleInterdite(Observateur<Message> observateur) {
        this.addObservateur(observateur);
    }

    //Méthodes
    public void commencerPartie(int niveauEau, ArrayList<String> collectNomsJoueurs, int nbJoueurs, TypeMessage typeM) {
        determinationRole(collectNomsJoueurs);
        setNiveauEau(niveauEau); //faire gaffe à la valeur du niveau eau pour la distribution des cartes
        setGrille(new Grille());
        ArrayList<Tuile> collectTuiles = getGrille().creationTuiles(getCollectAventuriers());
        commencerInondation(typeM);
        distributionCartesTresor();
        Message m = Message.demarrerJeu(collectTuiles, getCollectAventuriers(), getNiveauEau(), nbJoueurs);
        notifierObservateurs(m);
    }

    private void determinationRole(ArrayList<String> collectNomsJoueurs) {
        //création de la collection de rôles
        ArrayList<TypeRole> roleshasard = new ArrayList<>();
        for (TypeRole tr : TypeRole.values()) {
            roleshasard.add(tr);
        }
        Collections.shuffle(roleshasard);

        //Création des joueurs
        for (int i = 0; i < collectNomsJoueurs.size(); i++) {
            if (roleshasard.get(i) == TypeRole.EXPLORATEUR) {
                addAventuriers(new Explorateur(collectNomsJoueurs.get(i), this));
            }
            if (roleshasard.get(i) == TypeRole.NAVIGATEUR) {
                addAventuriers(new Navigateur(collectNomsJoueurs.get(i), this));
            }
            if (roleshasard.get(i) == TypeRole.INGENIEUR) {
                addAventuriers(new Ingenieur(collectNomsJoueurs.get(i), this));
            }
            if (roleshasard.get(i) == TypeRole.MESSAGER) {
                addAventuriers(new Messager(collectNomsJoueurs.get(i), this));
            }
            if (roleshasard.get(i) == TypeRole.PILOTE) {
                addAventuriers(new Pilote(collectNomsJoueurs.get(i), this));
            }

            if (roleshasard.get(i) == TypeRole.PLONGEUR) {
                addAventuriers(new Plongeur(collectNomsJoueurs.get(i), this));
            }

        }
    }

    public void inondationPlateau(Tuile t, CarteInondation cti, TypeMessage typeM) {
        ArrayList collectTuileNouvelEtat = new ArrayList<>();
        t.miseAjourEtat(typeM);
        collectTuileNouvelEtat.add(t);

        if (t.getEtat().equals(EtatTuile.INONDEE)) {
            getCarteInondeDefausse().add(cti);
            getCartesInondeTire().remove(cti);
        } else if (t.getEtat().equals(EtatTuile.COULEE)) {
            System.out.println("Tuile coulee");
        }

    }

    private void distributionCartesTresor() {
        creationCartesTirage();
        int nbCartes = 0;
        int indice = 0;
        for (Aventurier a : getCollectAventuriers()) {
            while (nbCartes < 2) {
                CarteTirage ct = getCartesTirageTire().get(indice);
                if (ct.getNom().equals("Carte Montée des eaux") == false) {
                    a.getCollectCartesJoueur().add(ct);
                    getCartesTirageTire().remove(ct);
                    nbCartes = nbCartes + 1;
                } else {
                    indice = indice + 1;
                }
            }
            nbCartes = 0;
        }
    }

    private void commencerInondation(TypeMessage typeM) {
        creationCartesInondation();
        for (int i = 0; i < Parameters.NB_INONDATIONS_INITIALES; i++) {
            CarteInondation cti = getCartesInondeTire().get(i);
            inondationPlateau(cti.getTuile(), cti, typeM);
        }

    }

    private void creationCartesTirage() {
        //Creation des cartes Tresor
        for (TypeTresorCarte tr : TypeTresorCarte.values()) {
            for (int i = 0; i < Parameters.NB_CARTES_TRESOR; i++) {
                getCartesTirageTire().add(new CarteTresor(tr.toString(), tr));
            }
        }

        //Creation des cartesSpe
        for (int i = 0; i < Parameters.NB_HELICOPTERES; i++) {
            getCartesTirageTire().add(new CarteHelicoptere("Carte Hélicoptère"));
        }

        for (int i = 0; i < Parameters.NB_SACS_DE_SABLE; i++) {
            getCartesTirageTire().add(new CarteSacsDeSable("Carte Sacs de Sable"));
        }

        //Creation des cartesMonteeEau
        for (int i = 0; i < Parameters.NB_MONTEES_DES_EAUX; i++) {
            getCartesTirageTire().add(new CarteMonteeDesEaux("Carte Montée des eaux"));
        }

        Collections.shuffle(getCartesTirageTire());
    }

    private void creationCartesInondation() {
        for (int i = 0; i < Parameters.NOMS_TUILES.length; i++) {
            getCartesInondeTire().add(new CarteInondation(Parameters.NOMS_TUILES[i], getGrille().getTuile(Parameters.NOMS_TUILES[i])));
        }
        Collections.shuffle(getCartesInondeTire());
    }

    public void tourDeJeu(int nbJoueurCourant, int nbActions, TypeMessage action) {
        System.out.println(nbActions);
        if (nbActions != 0) {
            System.out.print("tour de jeu");
            Aventurier a = getAventurier(nbJoueurCourant);
            ArrayList<Tuile> collectCases = a.calculCases(action);
            if (!collectCases.isEmpty()) {
                Message m = Message.afficherCases(collectCases);
                notifierObservateurs(m);
            } else {
                System.out.println("Mauvais choix");
                Message m = Message.mauvaisChoix();
                notifierObservateurs(m);
            }
        } else {
            System.out.print("Fin tour"); //mettre actif seulement le bouton fin_tour
        }
    }

    private Aventurier getAventurier(int nbJoueurCourant) {

        return getCollectAventuriers().get(nbJoueurCourant);
    }

    public void seDeplacer(String nomTuile, int numJourCourant) {
        Aventurier a = getCollectAventuriers().get(numJourCourant);
        System.out.println("se deplacer" + a.getStringRole() + nomTuile);
        Tuile t = a.getTuileCourante();
        t.getCollectAventuriers().remove(a);

        Grille g = getGrille();
        t = g.getTuile(nomTuile);
        System.out.print("Changement tuile" + t.getNomTuile());
        a.setTuileCourante(t);
        a.MiseAJourNbActions();
        a.getStringRole();
        Message m = Message.deplacer(a.getStringRole(), a.getTuileCourante().getNomTuile(), a.getNbaction());
        System.out.print("notification" + m.getTypeA() + m.getTypeM());
        notifierObservateurs(m);
    }

    public void assecher(String nomTuile, TypeMessage typeM, int nbJoueurCourant) {
        Aventurier a = getCollectAventuriers().get(nbJoueurCourant);
        Grille g = getGrille();
        Tuile t = g.getTuile(nomTuile);
        t.miseAjourEtat(typeM);
        a.MiseAJourNbActions();
        a.getStringRole();
        Message m = Message.assecher(a.getStringRole(), t.getNomTuile(), a.getNbaction());
        notifierObservateurs(m);
    }

    public void finTour(int nbJoueurCourant) {
        Aventurier a = getCollectAventuriers().get(nbJoueurCourant);
        System.out.println("tiragecartes");
        tirageCartes(a);
        System.out.println("tirageCarteinondation");
        tirageCartesInondation();
        System.out.println("condition fin");
        conditionsdeFin();

    }

    private void tirageCartes(Aventurier a) {
        System.out.println("tcverificationTirage");
        verificationTirage();
        System.out.println("tcverificationdistribution");
        if(a.getCollectCartesJoueur().size()<4){
         distributionCarte(a);   
        }else {
            Message m = Message.verificationDistribution(a.getCollectCartesJoueur());
            notifierObservateurs(m);
        }
    }

    private void verificationTirage() {
        if (getCartesTirageTire().size() < 2) {
            for (CarteTirage ct : getCartesTirageDefausse()) {
                getCartesTirageTire().add(ct);
            }
            Collections.shuffle(getCartesTirageTire());
            getCartesTirageDefausse().clear();
        }
    }

    public void majCollectCartesJoueurs(String nomCarte, int nbJoueurCourant) {
        Aventurier a = getAventurier(nbJoueurCourant);
        CarteTirage cti = a.getCarte(nomCarte);
        System.out.print("Carte perdu"+cti.getNom());
        a.getCollectCartesJoueur().remove(cti);
        getCartesTirageDefausse().add(cti);
        System.out.println("majDistributionCarte");
        distributionCarte(getAventurier(nbJoueurCourant));

    }

    private void distributionCarte(Aventurier a) {
        for (int i = 0; i < 2; i++) {
            CarteTirage cti = getCartesTirageTire().get(i);
            if (cti.getNom().equals("Carte Montée des eaux") == false) {
                a.getCollectCartesJoueur().add(cti);
                System.out.println("Taille collect"+a.getCollectCartesJoueur().size());
                getCartesTirageTire().remove(cti);
                System.out.println("Distribution joueur"+i);
            } else {
                setNiveauEau(getNiveauEau() + 1);
                getCartesTirageDefausse().add(cti);

            }

        }
        Message m = Message.distributionCarte(getNiveauEau(), a.getCollectCartesJoueur());
        notifierObservateurs(m);

    }

    public void tirageCartesInondation() {
        ArrayList<Tuile> collectTuiles = new ArrayList<>();
        int niveau = 0;
        if (getNiveauEau() <= 3) {
            niveau = 2;
        } else if (getNiveauEau() <= 6) {
            niveau = 3;
        } else if (getNiveauEau() <= 7) {
            niveau = 4;
        } else {
            niveau = 5;
        }

        if (getCartesInondeTire().size() < niveau) {
            for (CarteInondation cti : carteInondeDefausse) {
                getCartesInondeTire().add(cti);
            }
            getCarteInondeDefausse().clear();
        }

        for (int i = 0; i < niveau; i++) {
            CarteInondation cti = getCartesInondeTire().get(i);
            Tuile t = cti.getTuile();
            inondationPlateau(t, cti, TypeMessage.FIN_TOUR);
            collectTuiles.add(t);
        }
        Message m = Message.inondation(collectTuiles);
        notifierObservateurs(m);

    }

    public void conditionsdeFin() {

    }

    public void changementJoueur(int nbJoueurCourant) {
        Aventurier a = getAventurier(nbJoueurCourant);
        a.setNbaction(3);
        if (nbJoueurCourant == getCollectAventuriers().size()-1) {
            nbJoueurCourant = 0;
        } else {
            nbJoueurCourant = nbJoueurCourant + 1;
        }
        Aventurier a1 = getAventurier(nbJoueurCourant);
        Message m = Message.debutTour(nbJoueurCourant);
        notifierObservateurs(m);
    }

    // getters/setters :
    /**
     * @return the niveauEau
     */
    public int getNiveauEau() {
        return niveauEau;
    }

    /**
     * @param niveauEau the niveauEau to set
     */
    public void setNiveauEau(int niveauEau) {
        this.niveauEau = niveauEau;
    }

    /**
     * @return the grille
     */
    public Grille getGrille() {
        return grille;
    }

    /**
     * @param grille the grille to set
     */
    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    /**
     * @return the tresorsRecuperes
     */
    public ArrayList<TypeTresorTuile> getTresorsRecuperes() {
        return tresorsRecuperes;
    }

    /**
     * @param tresorsRecuperes the tresorsRecuperes to set
     */
    public void setTresorsRecuperes(TypeTresorTuile tresor) {
        getTresorsRecuperes().add(tresor);
    }

    /**
     * @return the cartesTirageTire
     */
    public ArrayList<CarteTirage> getCartesTirageTire() {
        return cartesTirageTire;
    }

    /**
     * @param cartesTirageTire the cartesTirageTire to set
     */
    public void setCartesTirageTire(CarteTirage ct) {
        getCartesTirageTire().add(ct);
    }

    /**
     * @return the cartesTirageDefausse
     */
    public ArrayList<CarteTirage> getCartesTirageDefausse() {
        return cartesTirageDefausse;
    }

    /**
     * @param cartesTirageDefausse the cartesTirageDefausse to set
     */
    public void setCartesTirageDefausse(CarteTirage ct) {
        getCartesTirageDefausse().add(ct);
    }

    /**
     * @return the cartesInondeTire
     */
    public ArrayList<CarteInondation> getCartesInondeTire() {
        return cartesInondeTire;
    }

    /**
     * @param cartesInondeTire the cartesInondeTire to set
     */
    public void setCartesInondeTire(CarteInondation cti) {
        getCartesInondeTire().add(cti);
    }

    /**
     * @return the carteInondeDefausse
     */
    public ArrayList<CarteInondation> getCarteInondeDefausse() {
        return carteInondeDefausse;
    }

    /**
     * @param carteInondeDefausse the carteInondeDefausse to set
     */
    public void setCarteInondeDefausse(CarteInondation cti) {
        getCarteInondeDefausse().add(cti);
    }

    /**
     * @return the aventuriers
     */
    public ArrayList<Aventurier> getCollectAventuriers() {
        return aventuriers;
    }

    /**
     * @param aventuriers the aventuriers to set
     */
    public void addAventuriers(Aventurier a) {
        getCollectAventuriers().add(a);
    }
}
