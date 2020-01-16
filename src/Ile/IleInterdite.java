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
    public void commencerPartie(int niveauEau, ArrayList<String> collectNomsJoueurs, int nbJoueurs,TypeMessage typeM) {
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

    public void inondationPlateau(Tuile t, CarteInondation cti,TypeMessage typeM) {
        ArrayList collectTuileNouvelEtat = new ArrayList<>();
        t.miseAjourEtat(typeM);
        collectTuileNouvelEtat.add(t);

        if (t.getEtat().equals(EtatTuile.INONDEE)) {
            getCarteInondeDefausse().add(cti);
            getCartesInondeTire().remove(cti);
        } else {
            //a faire
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
            inondationPlateau(cti.getTuile(), cti,typeM);
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

    public void tourDeJeu(String nomRole, int nbActions, TypeMessage action) {
        System.out.println(nbActions);
        if (nbActions != 0) {
            System.out.print("tour de jeu");
            Aventurier a = getAventurier(nomRole);
            ArrayList<Tuile> collectCases = a.calculCases(action);
            if (collectCases.size() != 0) {
                Message m = Message.afficherCases(collectCases);
                notifierObservateurs(m);
            } else {
                System.out.println("Mauvais choix");
                Message m = Message.mauvaisChoix();
                notifierObservateurs(m);
            }
        } else {
            System.out.print("Fin tour");
        }
    }

    private Aventurier getAventurier(String nomRole) {
        for (Aventurier a : getCollectAventuriers()) {
            if (a.getStringRole().equals(nomRole)) {
                return a;
            }
        }
        return null;
    }

    public void seDeplacer(String nomTuile, String nomRole) {
        System.out.println("se deplacer" + nomRole + nomTuile);
        Aventurier a = getAventurier(nomRole);
        Tuile t = a.getTuileCourante();
        t.getCollectAventuriers().remove(a);

        Grille g = getGrille();
        t = g.getTuile(nomTuile);
        System.out.print("Changement tuile" + t.getNomTuile());
        a.setTuileCourante(t);
        a.MiseAJourNbActions();
        a.getStringRole();
        Message m = Message.deplacer(a.getStringRole(), a.getTuileCourante(), a.getNbaction());
        System.out.print("notification" + m.getTypeA() + m.getTypeM());
        notifierObservateurs(m);
    }

    public void assecher(String nomTuile,TypeMessage typeM) {
        //Aventurier a = getAventu
        Grille g = getGrille();
        Tuile t=g.getTuile(nomTuile);
        t.miseAjourEtat(typeM);
        //notifierObservateurs(m);
    }

    /*
    private ArrayList<Aventurier>void choixJoueur(String nomTuile) {
        ArrayList<Aventurier> joueursPoss = new ArrayList<>();
        Grille g = this.getGrille();
        Tuile t = g.getTuile(nomTuile);        
        joueursPoss = g.getCollectJoueurs(t);
        
        Aventurier temp= aventuriers.get(nbJoueurCourant);         
        Message m =  Message.donner(t.getCollectAventuriers(),temp.getCollectCartesJoueur());
        notifierObservateurs(m);
    }

    public Aventurier getReceveur(String nomRoleReceveur) {
        Aventurier receveur = null;
        boolean encore = true;
        for (int i = 0; i< aventuriers.size() && encore; i++) {
            if (aventuriers.get(i).getRole().toString().equals(nomRoleReceveur)) {
                receveur = aventuriers.get(i);
                encore = false;
            }
        }
        
        return receveur;
    }*/
    public void choixCarte(int nbJoueurCourant) {
        Aventurier temp = getCollectAventuriers().get(nbJoueurCourant);
        ArrayList<CarteTresor> collectCartesTresors = new ArrayList<>();
        //collectCartesTresors = temp.getCartesTresors();
        /*Message m = Message.donner();     Précisez le message        
        notifierObservateurs(m);*/
    }

    public void donnerCarte(String nomCarte, Aventurier receveur, int nbJoueurCourant) {
        Aventurier temp = getCollectAventuriers().get(nbJoueurCourant);
        CarteTresor cte = new CarteTresor(temp.majCarteDonneur(nomCarte));
        TypeRole nomRoleDonneur = temp.getRole();
        int nbActionsDonneur = temp.MiseAJourNbActions();

        receveur.majCarteReceveur(cte);
        TypeRole nomRoleReceveur = receveur.getRole();

        Message m = Message.donner(nbActionsDonneur, nomCarte, nomCarte, nomCarte);
        notifierObservateurs(m);
    }

    /*
    J'ai du changer les primitive de recuperationTresor (ajout
        de joueurActif), et de possedeCartes (ajout de
        typeTresor)
            */
    public void recuperationTresor(String nomtuile, int joueurActif) {
        Grille g = getGrille();
        Tuile t = g.getTuile(nomtuile);
        TypeTresorTuile typeTresor = t.getTresor();
        Aventurier temp = aventuriers.get(joueurActif);
        ArrayList<CarteTresor> collectCarteTresor = temp.getCartesTresors();

        if (rep(collectCarteTresor, typeTresor)) {
            ArrayList<TypeTresorTuile> collectNomsTresors = getTresorsRecuperes();
            collectNomsTresors.add(typeTresor);

            temp.MiseAJourNbActions();
            TypeRole nomRole = temp.getRole();
            temp.removeCarteTirage(collectCarteTresor);

            getCartesTirageDefausse().addAll(collectCarteTresor);
            
            // Il faut encore créer le message et l'envoyer
            //      (commande 13 et 14 du diagramme de séquence - prendre_tresor)
        }
    }

    private boolean rep(ArrayList<CarteTresor> cartesTresor, TypeTresorTuile typeTresorTuile) {
        for (int i=0; i< cartesTresor.size()-3; i++) {
            for (int j=i+1; j< cartesTresor.size()-2; j++) {
                for (int k=j+1; k< cartesTresor.size()-1; k++) {
                    for (int l= k+1; l< cartesTresor.size(); l++) {
                        if ((cartesTresor.get(i).getTypeTresor().toString().equals(typeTresorTuile.toString())) && (i==j && j==k && k==l)) {
                            return(true);
                        }
                    }
                }
            }
        }
        return (false);
    }

    

    
    // diag seq tirageCartes
    private boolean tiragePossible(ArrayList<CarteTirage> cartesTirageTire) {
        return (getCartesTirageTire().size() >= 2);
    }

    /*private ArrayList<CarteTirage> tiragePossible() {
        ArrayList<CarteTirage> tiree = new ArrayList<>();
        for (int i=0; i<2; i++) {
            tiree.add(getCartesTirageTire().get(i));
        }
        return tiree;

private void majCollectCartesTire() {
        Collections.shuffle(cartesTirageDefausse);
        cartesTirageTire.addAll(cartesTirageDefausse);
        cartesTirageDefausse.clear();
    }

    private void verificationTirage() {
        if (!tiragePossible()) {
            majCollectCartesTire();
        }
    }

    private void verificatinDistribution(Aventurier a) { // bonne version
        if (a.getNbCartes() == 4) {
            a.getCollectCartesJoueur();
            Message m = Message.tirecartes(a.getCollectCartesJoueur());
            notifierObservateurs(m);
        }
    }

    private void addCarteDefausse(CarteTirage cti) {
        cartesTirageDefausse.add(cti);
    }

    private void majCollectCartesJoueur(String nomCarte) {
        Aventurier temp = null; // à revoir
        CarteTirage cti;
        cti = temp.getCarte(nomCarte);
        temp.removeCarteTirage(cti);
        addCarteDefausse(cti);

    }

    public void tirageCartes(Aventurier a) {
        verificationTirage();
        verificatinDistribution(a);

    }

    // diag seq Distribution
    public void distributionCarte() {
        cartesTirageTire = getCartesTirageTire();
        for (int i = 0; i < 2; i++) {
            getCarte(i);
            String nomcarte = getCarte(i).getNom();
            if (nomcarte == "carteMonteeEau") {
                int niveau = getNiveauEau();
                majNiveauEau();
                setCartesTirageDefausse(getCarte(i));
                Message m = Message.distribue(getNiveauEau());
                notifierObservateurs(m);

            } else {
                Aventurier temp = null; // à revoir
                temp.addCollectCartesJoueur(getCarte(i));
                temp.removeCarteTirage(getCarte(i));

            }
        }
    }

    private void majNiveauEau() { // à revoir
        CarteMonteeDesEaux cm = null;
        if (getCartesTirageTire().equals(cm.getNom())) {
            setNiveauEau(getNiveauEau() + 1);
        }
    }

    public CarteTirage getCarte(int i) { // à faire
        return getCartesTirageTire().get(i);
    }

    // diag seq tirageCartesInnondation
    public void majCollectCartesInnondationTire(ArrayList<CarteInondation> collectCarteInondationDefausse) {
        Collections.shuffle(carteInondeDefausse);
        cartesInondeTire.addAll(carteInondeDefausse);
        carteInondeDefausse.clear();
    }

    public CarteInondation getCarteI(int i) { // à faire
        return getCartesInondeTire().get(i);
    }

    public void inondationPlateau(Tuile t, CarteInondation cti) {

    }

    public void tirageCartesInnondation() {
        int niveau = getNiveauEau();
        ArrayList collectCarteInnondationTire = getCartesInondeTire();
        if (collectCarteInnondationTire.size() < niveau) {
            // ArrayList collectCarteInnondationDefausse = getCarteInondeDefausse();
            majCollectCartesInnondationTire();
        }

        for (int i = 0; i < getNiveauEau(); i++) {
            CarteInondation cti = getCarteI(i);
            cti.getTuile();

        }
    }

    // diag seq conditions de fin
    public void partiePerdu() {

    }
    // tresorRecup vérifie si le trésor a été récupéré
    private boolean tresorRecup(TypeTresorTuile typeTresor){
        boolean rep = false;
        for (int i = 0; i<tresorsRecuperes.size(); i++){
            if (typeTresor == getTresorsRecuperes().get(i)){
                rep = true;
            }
        }
        return rep;
    }
    

    
    private boolean peutEncoreRecup(TypeTresorTuile typeTresor){ // à faire
        return false;

    }

    public void perdrePartie() {
        int niveau = getNiveauEau();
        // opt niveau >5
        if (niveau > 5) {
            Message m = Message.ppartie();
            notifierObservateurs(m);

            // opt niveau <5
        } else {
            Grille g = null; // à revoir
            Tuile t = g.getTuile("Heliport");
            // opt etat = COULEE
            if (t.getEtat().equals(EtatTuile.COULEE)) {
                Message m = Message.ppartie();
                notifierObservateurs(m);
                // opt etat !=COULEE    
            } else {
                ArrayList<Tuile> collectTuilesTresor = new ArrayList<>();
                for (int i = 0; i < collectTuilesTresor.size(); i++) {
                    if (!t.getTresor().equals(TypeTresorTuile.NEUTRE)) {
                        collectTuilesTresor.add(t);
                    }
                    // loop t : collectTuilesTresor
                    for (int k = 0; k < collectTuilesTresor.size(); k++) {
                        TypeTresorTuile typeTresor = t.getTresor();
                        // opt rep = false
                        if (!tresorRecup(typeTresor)){
                            // opt etatTuile = COULEE
                            if (t.getEtat().equals(EtatTuile.COULEE)){
                                if (!peutEncoreRecup(typeTresor)){
                                    Message m = Message.ppartie();
                                    notifierObservateurs(m);
                                }
                                
                            }
                        }
 
                        
                            
                    }

                }
            }

        }

    }
    
    
    
    public void gagnerPartie(){
         ArrayList collectTresorsRecup = getTresorsRecuperes();
         if (collectTresorsRecup.size() == 4){
             Grille g = null;
             Tuile t = g.getTuile("Heliport");
             ArrayList collectJoueursTuile = t.getJoueurs();
             ArrayList collectJoueursIle = getAventuriers();
             if (collectJoueursIle.size()==collectJoueursTuile.size()){
                 for(int i = 0; i<collectJoueursTuile.size(); i++){
                     Aventurier temp = null;
                     if(temp.possedeCarteHelico()){
                         Message m = Message.gpartie();
                         notifierObservateurs(m);
                 }
             }
                     
         }
    }
    

    /* à faire quand le diagramme de séquence sera fait
    public void finDeTour(String nomAventurier) {
    Aventurier av = null;
    for (int i = 0; i < aventuriers.size(); i++) {
    if (aventuriers.get(i).getNomJoueur().equals(nomAventurier)) {
    av = aventuriers.get(i);
    }
    }
    tirageCarte(av);
    tirageCartesInondation();
    if (!conditionVictoire()) {     // si la partie n'est pas fini,
    changementJoueur();                 // on change de joueur
    }
    }*/
 /*
    private void tirageCartes(Aventurier av) {
        // à finir
        System.out.println("Pensez à programmer tirageCarte");
        Aventurier av_temp = av;
        if (verificationTirage()) {
            av.getCollectCartesJoueur().addAll(tiragePossible());
            aventuriers.add(av);
        }
    }
    
    private boolean verificationTirage() {
        return(getCartesTirageTire().size() <= 2);
    }
    
    private ArrayList<CarteTirage> tiragePossible() {
        ArrayList<CarteTirage> tiree = new ArrayList<>();
        for (int i=0; i<2; i++) {
            tiree.add(getCartesTirageTire().get(i));
        }
        return tiree;
    }
    
    private void tirageCartesInondation() {
        // utiliser this.niveauEau pour le nombre de carte
        System.out.println("Pensez à programmer tirageCartesInondation");
    }*/
    /*private void choixCarteSpe() {
        ArrayList<CarteTresor> collectCartesTresors = new ArrayList<>();
        collectCartesTresors = null;

    }
*/
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
