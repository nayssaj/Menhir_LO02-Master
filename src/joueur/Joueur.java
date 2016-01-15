/**
 * Package qui gere un joueur, son comportement, ses stats
 * @author Le Mercier - Gerard
 * @version 2.0
 */
package joueur;

import Partie.*;
import carte.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

/**
 * Classe qui gere un joueur humain ou IA
 * @author Le Mercier - Gerard
 * @version 2.0
 */
public class Joueur extends Observable{
    private String nom;
    private int nbMenhir;
    private int nbGraine;
    private int nbPoint;
    private ArrayList<Carte> carteEnMain;
    private int age;
    private String sexe;
    private int carteJouée;
    private String actionEffectuée = null;
    private Joueur cibleJoueur;


    /**
     * Constructeur surchargé de la classe Joueur
     * @param nom Nom du joueur
     * @param age Age du joueur
     * @param sexe Sexe du joueur
     * @author Le Mercier - Gerard
     * @version 1.0
     */
    public Joueur(String nom, int age, String sexe) {//Constructeur unique de la classe Joueur
        this.nom = nom;
        this.nbMenhir=0;
        this.nbGraine=0;
        this.nbPoint=0;
        this.carteEnMain = new ArrayList();
        this.age=age;
        this.sexe = sexe;
    }

    /**
     * Getter du nom du joueur
     * @return Le nom du joueur
     * @author Le Mercier - Gerard
     * @version 1.0
     */
    public String getNom() {
        return nom;
    }

    /**
     * Getter de la carte joue
     * @return La place de la carte jouee dans la main du joueur
     * @author Le Mercier - Gerard
     * @version 1.0
     */
    public int getCarteJouée() {
        return carteJouée;
    }

    /**
     * Getter du sexe du joueur
     * @return Le sexe du joueur
     * @author Le Mercier - Gerard
     * @version 1.0
     */
    public String getSexe() {
        return sexe;
    }

    /**
     * Setter qui parametre l'action que le joueur a choisi d'effectuer
     * @param actionEffectuée L'action du joueur choisi
     * @author Le Mercier - Gerard
     * @version 2.0
     */
    public void setActionEffectuée(String actionEffectuée) {
        this.actionEffectuée = actionEffectuée;
    }

    /**
     * Getter qui retourne l'action que le joueur a effectue
     * @return L'action effectue
     * @author Le Mercier - Gerard
     * @version 2.0
     */
    public String getActionEffectuée() {

        return actionEffectuée;
    }

    /**
     * Getter qui donne l'age du joueur
     * @return L'age du joueur
     * @author Le Mercier - Gerard
     * @version 1.0
     */
    public int getAge() {
        return age;
    }

    /**
     * Getter qui donne le nombre de menhir du joueur
     * @return Le nombre de menhir en possession du joueur
     * @author Le Mercier - Gerard
     * @version 1.0
     */
    public int getNbMenhir() {
        return nbMenhir;
    }

    /**
     * Setter qui parametre le nombre de menhir du joueur
     * @param nbMenhir Le nombre de menhir a affecter au joueur
     * @author Le Mercier - Gerard
     * @version 1.0
     */
    public void setNbMenhir(int nbMenhir) {
        this.nbMenhir = nbMenhir;
    }

    /**
     * Getter qui donne le nombre de point du joueur
     * @return Le nombre de point du joueur
     * @author Le Mercier - Gerard
     * @version 1.0
     */
    public int getNbPoint() {
        return nbPoint;
    }

    /**
     * Getter qui donne la main du joueur
     * @return La main du joueur
     * @author Le Mercier - Gerard
     * @version 1.0
     */
    public ArrayList<Carte> getCarteEnMain() {
        return carteEnMain;
    }

    /**
     * Setter qui parametre la cible du joueur lors de certains coups de cartes
     * @param cibleJoueur Le joueur cible du joueur de la classe
     * @author Le Mercier - Gerard
     * @version 2.0
     */
    public void setCibleJoueur(Joueur cibleJoueur) {
        this.cibleJoueur = cibleJoueur;
        this.setChanged();
        this.notifyObservers("La cible est" + cibleJoueur.getNom());
    }

    /**
     * Getter qui retour le nombre de graine(s) en possession du joueur
     * @return Le nombre de graine(s) en possession du joueur
     * @author Le Mercier - Gerard
     * @version 1.0
     */
    public int getNbGraine() {
        return nbGraine;
    }

    /**
     * Setter qui parametre le nombre de graine(s) en possession du joueur
     * @param nbGraine Le nombre de graine(s) a affecter au joueur
     * @author Le Mercier - Gerard
     * @version 1.0
     */
    public void setNbGraine(int nbGraine) {
        this.nbGraine = nbGraine;
    }

    /**
     * Methode qui inscrit la valeur de la derniere carte jouee
     * @param place La place de la carte jouee
     * @author Le Mercier - Gerard
     * @version 1.0
     */
    public void choisirCarte (int place){
        //On choisit une carte
        carteJouée = place;
    }

    /**
     * Methode qui va appliquer l'action de la carte jouee
     * @param partie La partie en cours
     * @author Le Mercier - Gerard
     * @version 2.0
     */
    public void jouerCarte (Partie partie){
        this.setChanged();
        this.notifyObservers("Vous effectuez l'action " + actionEffectuée + " de la carte " + carteEnMain.get(carteJouée).getNom());
        if(this.getCarteEnMain().get(this.getCarteJouée()) instanceof CarteAlliees){
            ((CarteAlliees) this.getCarteEnMain().get(this.getCarteJouée())).actionTaupe(this.cibleJoueur,partie.getSaison());
        }
        else{
            switch(actionEffectuée){
                case "ENGRAIS":
                    ((CarteIngredient)this.getCarteEnMain().get(this.getCarteJouée())).actionEngrais(this,partie.getSaison());
                    break;
                case "GEANT":
                    ((CarteIngredient) this.getCarteEnMain().get(this.getCarteJouée())).actionGeant(this,partie.getSaison());
                    break;
                case "FARFADET":
                    ((CarteIngredient) this.getCarteEnMain().get(this.getCarteJouée())).actionFarfadet(this,cibleJoueur,cibleJoueur.aCarteChien(),partie.getSaison());
                    break;
                case"TAUPE":
                    ((CarteAlliees) this.getCarteEnMain().get(this.getCarteJouée())).actionTaupe(this.cibleJoueur, partie.getSaison());
            }

        }
        carteEnMain.remove(carteJouée);
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Methode qui verifie la presence d'un carte chien dans la main du joueur
     * @return Un booleen qui indique si le joueur a une carte chien ou non
     */
    public boolean aCarteChien(){//Le joueur dispose t'il d'une carte chien
        boolean retour = false;
        ArrayList<Carte> main = this.getCarteEnMain();
        Iterator<Carte> it =main.iterator();
        while(it.hasNext()){
            Carte carte = it.next();
            if(carte.getNom().equals("Chien de garde")){retour=true;}
        }
        return retour;
    }

    /**
     * Methode qui va rajouter ou enlever une(des) graine(s) au joueur
     * @param quantite Valeur positive ou negative pour modifier le nombre de graine(s) du joueur
     * @author Le Mercier - Gerard
     * @version 1.0
     */
    public void modifierGraine(int quantite){//Modifie le nombre de graine par ajout d'une quantite
        this.nbGraine+=quantite;
    }

    /**
     * Methode qui va rajouter ou enlever un(des) menhir(s) au joueur
     * @param quantite Valeur positive ou negative pour modifier le nombre de menhir(s) du joueur
     * @author Le Mercier - Gerard
     * @version 1.0
     */
    public void modifierMenhir(int quantite){//Modifie le nombre de menhir par ajout d'une quantite
        this.nbMenhir+=quantite;
    }

    /**
     * Methode qui va convertir le(s) menhir(s) en point et les incrementer au score du joueur
     * @author Le Mercier - Gerard
     * @version 1.0
     */
    public void modifierScore(){//Ajoute un certain nombre de point au joueur et remet les menhirs à 0
        this.nbPoint+=this.nbMenhir;
        this.nbMenhir=0;
    }
    
    /**
     * Methode qui donne le rang de la carte chien dans la main
     * @param saison L'effet de la carte
     * @return La valeur de la carte chien pour la saison donnee
     * @author Le Mercier - Gerard
     * @version 1.0
     */
    public int obtenirEffetChien(int saison){
        Iterator<Carte> it = this.carteEnMain.iterator();
        int effet = 0;
        while(it.hasNext()){
            Carte carte = it.next();
            if(carte.getNom().equals("Chien de garde")){
                effet = ((CarteAlliees)carte).getForce(saison);
                it.remove();
            }
        }
        return effet;
    }

    /**
     * Donne le nom du joueur sous la forme String
     * @return Le nom du joueur
     * @author Le Mercier - Gerard
     * @version 1.0
     */
    public String toString(){
        return this.getNom();
    }
}
