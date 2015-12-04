package Partie;

import Partie.Partie;
import carte.*;
import joueur.*;

import java.util.*;
/**
 * Created by jglem_000 on 28/11/2015.
 */
public class PartieAvancee extends Partie{


    private PaquetCarteAlliee deckAllie;

    public PartieAvancee(PaquetCarteIngredient paquetIngredient, PaquetCarteAlliee paquetAllie, int nbJoueur, Affichage mainUI) {
        super(paquetIngredient,nbJoueur, mainUI);
        this.setNbManches(this.getListeJoueur().size());
        this.deckAllie = paquetAllie;
        System.out.println(getNbManches());
    }

    public PaquetCarteAlliee getDeckAllie() {
        return deckAllie;
    }

    public void lancerPartie() {
        this.initaliserPartie();
        repet : for (int i = 0; i <= this.getNbManches(); i++){
            this.prochaineSaison();
            Iterator<Joueur> itJoueursManche = this.getListeJoueur().iterator();
            while (itJoueursManche.hasNext()) {
                Joueur joueurManche = itJoueursManche.next();
                //TODO modifier pour ce ne soit que le joueur réel qui ait cette option
                this.donnerGraineOuAllie(joueurManche,joueurManche.getJoueurUI().proposerGrainesOuAllie());
            }
            this.jouerSaisons();
            if(this.finPartie()){
                System.out.println("FIN DE LA PARTIE");
                break repet;
            }
            this.setNumManche(getNumManche()+1);
            this.nettoyageManche();
        }
        this.getMainUI().gagnant(aGagne());
    }

    public void donnerGraineOuAllie(Joueur joueur, String answer){
        if(answer.equals("GRAINES")){
            joueur.setNbGraine(2);
        }
        else{
            this.distribuer(joueur, getDeckAllie(), 1);
        }
    }

    public void initaliserPartie(){

        int nbCarteADistribuer = 4;

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
