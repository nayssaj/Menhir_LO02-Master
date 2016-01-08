package Partie;

import carte.*;
import joueur.*;

import java.util.*;

public class PartieAvancee extends Partie{


    private PaquetCarteAlliee deckAllie;

    public PartieAvancee(PaquetCarteIngredient paquetIngredient, PaquetCarteAlliee paquetAllie, int nbJoueur, int ageJoueur, String sexeJoueur) {
        super(paquetIngredient,nbJoueur, ageJoueur, sexeJoueur);
        this.setNbManches(this.getListeJoueur().size());
        this.deckAllie = paquetAllie;
    }

    public PaquetCarteAlliee getDeckAllie() {
        return deckAllie;
    }

    public void initaliserPartie(){

        int nbCarteADistribuer = 4;

        this.setOrdreJoueur();

        Iterator itJoueur = this.getListeJoueur().iterator();
        while(itJoueur.hasNext()){
            Joueur joueur = (Joueur) itJoueur.next();
            joueur.setNbGraine(0);
        }

        //On mélange et distribue le paquet de cartes ingrédients
        this.getDeckIngredient().genererPaquetIngredient();
        this.getDeckAllie().genererPaquetAlliee();
        this.distribuer(this.getDeckIngredient(), nbCarteADistribuer);
    }

    public boolean finPartie(){
        return this.getSaison() == Saison.FIN_ANNEE && this.getNumManche() == this.getListeJoueur().size();
    }

    public void nettoyageManche(){

        int nbCarteADistribuer = 4;

        Iterator<Joueur> itJoueur = getListeJoueur().iterator();
        while(itJoueur.hasNext()){
            itJoueur.next().getCarteEnMain().clear();
        }
        this.getDeckIngredient().genererPaquetIngredient();
        this.getDeckAllie().genererPaquetAlliee();
        this.distribuer(this.getDeckIngredient(), nbCarteADistribuer);
    }
}
