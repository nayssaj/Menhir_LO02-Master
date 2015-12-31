package joueur;

import Partie.*;

import Partie.Affichage;
import carte.*;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;


/**
 * Created by juliengerard on 27/11/2015.
 */
public class Joueur extends Observable{
    private AffichageJoueur joueurUI;
    private String nom;
    private boolean tour;
    private int nbMenhir;
    private int nbGraine;
    private int nbPoint;
    private ArrayList<Carte> carteEnMain;
    private int age;
    private String sexe;
    private int carteJouée;
    private String actionEffectuée = null;
    private Joueur cibleJoueur;



    public Joueur(String nom, int age, String sexe, AffichageJoueur joueurUI) {//Constructeur unique de la classe Joueur
        this.nom = nom;
        this.tour=false;
        this.nbMenhir=0;
        this.nbGraine=0;
        this.nbPoint=0;
        this.carteEnMain = new ArrayList();
        this.age=age;
        this.joueurUI = joueurUI;
        this.sexe = sexe;
    }



    public String getNom() {
        return nom;
    } //getter et setter

    public int getCarteJouée() {
        return carteJouée;
    }

    public String getSexe() {
        return sexe;
    }

    public void setActionEffectuée(String actionEffectuée) {
        this.actionEffectuée = actionEffectuée;
    }

    public String getActionEffectuée() {

        return actionEffectuée;
    }

    public int getAge() {
        return age;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public void setCibleJoueur(Joueur cibleJoueur) {
        this.cibleJoueur = cibleJoueur;
        this.setChanged();
        this.notifyObservers("La cible est" + cibleJoueur.getNom());
    }

    public void setCarteEnMain(ArrayList<Carte> carteEnMain) {
        this.carteEnMain = carteEnMain;
    }

    public int getNbGraine() {
        return nbGraine;
    }

    public Joueur getCibleJoueur() {
        return cibleJoueur;
    }

    public void setNbGraine(int nbGraine) {
        this.nbGraine = nbGraine;
    }

    public AffichageJoueur getJoueurUI() {
        return joueurUI;
    }

    public void choisirCarte (int place){
        //On choisit une carte
        carteJouée = place;
    }

    public void jouerCarte (Partie partie){
        this.setChanged();
        this.notifyObservers("Vous effectuez l'action " + actionEffectuée + "de la carte " + carteEnMain.get(carteJouée).getNom());
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
            }

        }
        carteEnMain.remove(carteJouée);
        this.setChanged();
        this.notifyObservers();
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

    public void jouerTaupe(ArrayList<Joueur> cibles, Saison saison){
        boolean answer = this.joueurUI.proposerTaupe();
        Carte carteJouee = null;
        if(answer){
            Iterator<Carte> it = this.getCarteEnMain().iterator();
            while(it.hasNext()){
                Carte carte = it.next();
                if(carte.getNom().equals("Taupe geante")){
                    ((CarteAlliees)carte).actionTaupe(this.getJoueurUI().choixCible(cibles), saison);
                    carteJouee = carte;
                }
            }
            this.carteEnMain.remove(carteJouee);
        }
    }

    public void graineOuAllie(PartieAvancee partie, Joueur joueur){
        String answer = this.joueurUI.proposerGrainesOuAllie();
        if(answer.equals("GRAINES")){
            this.nbGraine = nbGraine + 2;
        }
        else{
            partie.distribuer(this, partie.getDeckAllie(), 1);
        }
    }

    public boolean aCarteTaupe(){
        boolean retour = false;
        ArrayList<Carte> main = this.getCarteEnMain();
        Iterator<Carte> it =main.iterator();
        while(it.hasNext()){
            Carte carte = (Carte) it.next();
            if(carte.getNom().equals("Taupe geante")){retour=true;}
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
