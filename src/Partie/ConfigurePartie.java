package Partie;

import carte.*;

import java.util.*;

public class ConfigurePartie extends Observable {

    private String typePartie = null;
    private Partie partie;
    private int nbJoueurs;
    private int ageJoueur = -1;
    private String sexeJoueur = null;

    public Partie configurerPartie(){
        if(typePartie.equals("RAPIDE")){
            PaquetCarteIngredient paquetIngredient = new PaquetCarteIngredient();
            partie = new Partie(paquetIngredient,nbJoueurs, ageJoueur, sexeJoueur);
        }
        else{
            PaquetCarteIngredient paquetIngredient = new PaquetCarteIngredient();
            PaquetCarteAlliee paquetAllie = new PaquetCarteAlliee();
            partie = new PartieAvancee(paquetIngredient, paquetAllie, nbJoueurs, ageJoueur, sexeJoueur);

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

    public String getSexeJoueur() {
        return sexeJoueur;
    }
}
