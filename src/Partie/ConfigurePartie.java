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

    public Partie configurerPartie(){
        mainUI = new Affichage();
        mainUI.acceuil();
        if(typePartie.equals("RAPIDE")){
            PaquetCarteIngredient paquetIngredient = new PaquetCarteIngredient();
            partie = new Partie(paquetIngredient,nbJoueurs, mainUI);
        }
        else{
            PaquetCarteIngredient paquetIngredient = new PaquetCarteIngredient();
            PaquetCarteAlliee paquetAllie = new PaquetCarteAlliee();
            partie = new PartieAvancee(paquetIngredient, paquetAllie, nbJoueurs, mainUI);

        }
        return partie;
    }

    public void setPartieRapide(){
        this.typePartie = "RAPIDE";
        this.setChanged();
        this.notifyObservers("Vous avez choisis une partie rapide");
    }

    public void setPartieAvancee(){
        this.typePartie = "AVANCEE";
        this.setChanged();
        this.notifyObservers("Vous avez choisis une partie avancee");
    }

    public void setNbJoueurs(int nbJoueurs){
        this.nbJoueurs = nbJoueurs;
    }
}
