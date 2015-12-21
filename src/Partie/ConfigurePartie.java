package Partie;

import carte.*;

import java.util.*;


/**
 * Created by jglem_000 on 28/11/2015.
 */
public class ConfigurePartie extends Observable {

    private String typePartie;
    private Partie partie;
    private Affichage mainUI;
    private int nbJoueurs;
    private int ageJoueur;
    private String sexeJoueur;

    public Partie configurerPartie(){
        mainUI = new Affichage();
        mainUI.acceuil();
        if(typePartie.equals("RAPIDE")){
            PaquetCarteIngredient paquetIngredient = new PaquetCarteIngredient();
            partie = new Partie(paquetIngredient,nbJoueurs, mainUI, ageJoueur, sexeJoueur);
        }
        else{
            PaquetCarteIngredient paquetIngredient = new PaquetCarteIngredient();
            PaquetCarteAlliee paquetAllie = new PaquetCarteAlliee();
            partie = new PartieAvancee(paquetIngredient, paquetAllie, nbJoueurs, mainUI, ageJoueur, sexeJoueur);

        }
        return partie;
    }

    public void setTypePartie(String typePartie){
        this.typePartie = typePartie;
    }

    public void setNbJoueurs(int nbJoueurs){
        this.nbJoueurs = nbJoueurs;
    }

    public void setAgeJoueur(int ageJoueur) {
        this.ageJoueur = ageJoueur;
    }

    public void setSexeJoueur(String sexeJoueur) {
        this.sexeJoueur = sexeJoueur;
    }

    public String getTypePartie() {
        return typePartie;
    }

    public int getNbJoueurs() {
        return nbJoueurs;
    }

    public int getAgeJoueur() {
        return ageJoueur;
    }

    public String getSexeJoueur() {
        return sexeJoueur;
    }
}
