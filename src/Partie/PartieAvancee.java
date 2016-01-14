package Partie;

import carte.*;
import joueur.*;
import java.util.*;

/**
 * Classe qui gere la partie avancee, elle herite de Partie
 * @author Le Mercier - Gerard
 * @version 2.0
 */
public class PartieAvancee extends Partie{


    private PaquetCarteAlliee deckAllie;

    /**
     * Constructeur surcharge de la partie avancee
     * @param paquetIngredient Le paquet de cartes ingredients utilise dans le jeu
     * @param paquetAllie Le paquet de cartes allies utilise dans le jeu
     * @param nbJoueur Le nombre de joueur de la partie
     * @param ageJoueur L'age du joueur humain
     * @param sexeJoueur Le sexe du joueur humain
     */
    public PartieAvancee(PaquetCarteIngredient paquetIngredient, PaquetCarteAlliee paquetAllie, int nbJoueur, int ageJoueur, String sexeJoueur) {
        super(paquetIngredient,nbJoueur, ageJoueur, sexeJoueur);
        this.setNbManches(this.getListeJoueur().size());
        this.deckAllie = paquetAllie;
    }

    /**
     * Getter pour obtenir le tas de cartes allies
     * @return Le paquet de cartes allies
     */
    public PaquetCarteAlliee getDeckAllie() {
        return deckAllie;
    }

    /**
     * Initialise les paquets, le nombre de graines pour chaque joueurs ainsi que distribuer les cartes en debut de parties
     */
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

    /**
     * Test si la partie est reelement terminee
     * @return Un boolean qui indique si c'est la fin de la aprtie
     */
    public boolean finPartie(){
        return this.getSaison() == Saison.FIN_ANNEE && this.getNumManche() == this.getListeJoueur().size();
    }

    /**
     * Methode pour nettoyer les mains en fin de manche
     */
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
