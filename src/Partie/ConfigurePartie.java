/**
 * Package qui gere la partie
 * @author Le Mercier - Gerard
 * @version 2.0
 */
package Partie;

import carte.*;
import java.util.*;

/**
 * Classe qui configure les parametres de la partie elle est
 * @author Le Mercier - Gerard
 * @version 2.0
 */
public class ConfigurePartie extends Observable {

    private String typePartie = null;
    private Partie partie;
    private int nbJoueurs;
    private int ageJoueur = -1;
    private String sexeJoueur = null;

    /**
     * Prepare la configuration d'une partie
     * @return Une partie prete a etre jouee
     * @author Le Mercier - Gerard
     * @version 2.0
     */
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

    /**
     * Setter du type de la partie
     * @param typePartie le type de la partie
     * @author Le Mercier - Gerard
     * @version 2.0
     */
    public void setTypePartie(String typePartie){
        this.typePartie = typePartie;
    }

    /**
     * Setter du nombre de joueurs
     * @param nbJoueurs le nombre de joueurs de la partie
     * @author Le Mercier - Gerard
     * @version 2.0
     */
    public void setNbJoueurs(int nbJoueurs){
        this.nbJoueurs = nbJoueurs;
    }

    /**
     * Setter age du joueur
     * @param ageJoueur L'age d'un joueur de la partie
     * @author Le Mercier - Gerard
     * @version 2.0
     */
    public void setAgeJoueur(int ageJoueur) {
        this.ageJoueur = ageJoueur;
    }

    /**
     * Setter du sexe du joueur
     * @param sexeJoueur Le sexe d'un joueur
     * @author Le Mercier - Gerard
     * @version 2.0
     */
    public void setSexeJoueur(String sexeJoueur) {
        this.sexeJoueur = sexeJoueur;
    }

    /**
     * Getter du type de partie
     * @return Le type de partie
     * @author Le Mercier - Gerard
     * @version 2.0
     */
    public String getTypePartie() {
        return typePartie;
    }

    /**
     * Getter du sexe du joueur
     * @return Le sexe du joueur
     * @author Le Mercier - Gerard
     * @version 2.0
     */
    public String getSexeJoueur() {
        return sexeJoueur;
    }
}
