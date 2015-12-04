package joueur;

import Partie.Affichage;
import carte.*;


import java.util.ArrayList;
import java.util.Iterator;


/**
 * Created by juliengerard on 27/11/2015.
 */
public class Joueur{
    private AffichageJoueur joueurUI;
    private String nom;
    private boolean tour;
    private int nbMenhir;
    private int nbGraine;
    private int nbPoint;
    private ArrayList<Carte> carteEnMain;
    private int age;



    public Joueur(String nom, int age, AffichageJoueur joueurUI) {//Constructeur unique de la classe Joueur
        this.nom = nom;
        this.tour=false;
        this.nbMenhir=0;
        this.nbGraine=0;
        this.nbPoint=0;
        this.carteEnMain = new ArrayList();
        this.age=age;
        this.joueurUI = joueurUI;
    }



    public String getNom() {
        return nom;
    } //getter et setter

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean isTour() {
        return tour;
    }

    public void setTour(boolean tour) {
        this.tour = tour;
    }

    public int getNbMenhir() {
        return nbMenhir;
    }

    public void setNbMenhir(int nbMenhir) {
        this.nbMenhir = nbMenhir;
    }

    public int getNbPoint() {
        return nbPoint;
    }

    public void setNbPoint(int nbPoint) {
        this.nbPoint = nbPoint;
    }

    public ArrayList<Carte> getCarteEnMain() {
        return carteEnMain;
    }

    public void setCarteEnMain(ArrayList<Carte> carteEnMain) {
        this.carteEnMain = carteEnMain;
    }

    public int getNbGraine() {
        return nbGraine;
    }

    public void setNbGraine(int nbGraine) {
        this.nbGraine = nbGraine;
    }

    public AffichageJoueur getJoueurUI() {
        return joueurUI;
    }

    public Carte choisirCarte (){
        //On choisit une carte
        int place = joueurUI.choixMain(this);
        return this.carteEnMain.remove(place);
    }

    public void choisirAction (Carte carte, ArrayList<Joueur> joueurs, Saison saison ){
        if(carte instanceof CarteAlliees){
            ((CarteAlliees) carte).actionTaupe(joueurUI.choixCible(joueurs),saison);
        }
        else{
            switch(joueurUI.choixAction()){
                case "ENGRAIS":
                    joueurUI.infoEngrais(((CarteIngredient) carte).actionEngrais(this,saison));
                    break;
                case "GEANT":
                    joueurUI.infoGeant(((CarteIngredient) carte).actionGeant(this,saison));
                    break;
                case "FARFADET":
                    Joueur cible = joueurUI.choixCible(joueurs);
                    joueurUI.infoFarfadet(((CarteIngredient) carte).actionFarfadet(this,cible,cible.aCarteChien(),saison));
                    break;
            }

        }
    }


    public boolean aCarteChien(){//Le joueur dispose t'il d'une carte chien
        boolean retour = false;
        ArrayList<Carte> main = this.getCarteEnMain();
        Iterator<Carte> it =main.iterator();
        while(it.hasNext()){
            Carte carte = (Carte) it.next();
            if(carte.getNom().equals("Chien de garde")){retour=true;}
        }
        return retour;
    }


    public void modifierGraine(int quantite){//Modifie le nombre de graine par ajout d'une quantite
        this.nbGraine+=quantite;
    }

    public void modifierMenhir(int quantite){//Modifie le nombre de menhir par ajout d'une quantite
        this.nbMenhir+=quantite;
    }

    public void modifierScore(){//Ajoute un certain nombre de point au joueur et remet les menhirs à 0
        this.nbPoint+=this.nbMenhir;
        this.nbMenhir=0;
    }

    public int obtenirEffetChien(int rang){
        Iterator<Carte> it =this.carteEnMain.iterator();
        int index=0;
        int i =0;
        while(it.hasNext()){
            Carte carte = (Carte) it.next();
            if(carte.getNom().equals("Chien de garde")){index=i;}
            i++;
        }
        return index;
    }

    public String toString(){
        return this.getNom();
    }
}
