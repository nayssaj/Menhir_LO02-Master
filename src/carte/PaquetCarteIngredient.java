package carte;

import java.util.*;

/**
 * Classe qui represente le paquet de carte ingredient qui herite de PaquetCarte
 * @author Le Mercier - Gerard
 * @version 1.0
 */
public class PaquetCarteIngredient extends PaquetCarte {


    /**
     * Le constructeur de la classe est identique a celui de la classe mere, il genere un paquet vide
     * @author Le Mercier - Gerard
     * @version 1.0
     */
    public PaquetCarteIngredient() {
        super();
    }

    /**
     * La methode remplie le paquet de carte vide et melange le paquet de carte
     * @author Le Mercier - Gerard
     * @version 1.0
     */
    public void genererPaquetIngredient(){//Vide le paquet existant et génére un nouevau paquet
        this.getPaquetCarte().clear();

        //On rentre toutes les cartes de manière manuelle dans la base
        String nom = new String("Rayon de lune");
        int[][] effet = new int[][]{{1,1,1,1},{2,0,1,1},{2,0,2,0}};
        CarteIngredient carteIng = new CarteIngredient("Rayon de lune",effet);
        this.paquetCarte.add(new CarteIngredient(nom,effet));

        nom = "Rayon de lune";
        effet = new int[][] {{2,0,1,1},{1,3,0,0},{0,1,2,1}};
        this.paquetCarte.add(new CarteIngredient(nom,effet));

        nom = "Rayon de lune";
        effet = new int[][] {{0,0,4,0},{0,2,2,0},{0,0,1,3}};
        this.paquetCarte.add(new CarteIngredient(nom,effet));

        nom = "Chant de sirene";
        effet = new int[][] {{1,3,1,0},{1,2,1,1},{0,1,4,0}};
        this.paquetCarte.add(new CarteIngredient(nom,effet));

        nom = "Chant de sirene";
        effet = new int[][] {{2,1,1,1},{1,0,2,2},{3,0,0,2}};
        this.paquetCarte.add(new CarteIngredient(nom,effet));

        nom = "Chant de sirene";
        effet = new int[][] {{1,2,2,0},{1,1,2,1},{2,0,1,2}};
        this.paquetCarte.add(new CarteIngredient(nom,effet));

        nom = "Larmes de dryade";
        effet = new int[][] {{2,1,1,2},{1,1,1,3},{2,0,2,2}};
        this.paquetCarte.add(new CarteIngredient(nom,effet));

        nom = "Larmes de dryade";
        effet = new int[][] {{0,3,0,3},{2,1,3,0},{1,1,3,1}};
        this.paquetCarte.add(new CarteIngredient(nom,effet));

        nom = "Larmes de dryade";
        effet = new int[][] {{1,2,1,2},{1,0,1,4},{2,4,0,0}};
        this.paquetCarte.add(new CarteIngredient(nom,effet));

        nom = "Fontaine d'eau pure";
        effet = new int[][] {{1,3,1,2},{2,1,2,2},{0,0,3,4}};
        this.paquetCarte.add(new CarteIngredient(nom,effet));

        nom = "Fontaine d'eau pure";
        effet = new int[][] {{2,2,0,3},{1,1,4,1},{1,2,1,3}};
        this.paquetCarte.add(new CarteIngredient(nom,effet));

        nom = "Fontaine d'eau pure";
        effet = new int[][] {{2,2,3,1},{2,3,0,3},{1,1,3,3}};
        this.paquetCarte.add(new CarteIngredient(nom,effet));

        nom = "Poudre d'or";
        effet = new int[][] {{2,2,3,1},{2,3,0,3},{1,1,3,3}};
        this.paquetCarte.add(new CarteIngredient(nom,effet));

        nom = "Poudre d'or";
        effet = new int[][] {{2,2,2,2},{0,4,4,0},{1,3,2,2}};
        this.paquetCarte.add(new CarteIngredient(nom,effet));

        nom = "Poudre d'or";
        effet = new int[][] {{3,1,3,1},{1,4,2,1},{2,4,1,1}};
        this.paquetCarte.add(new CarteIngredient(nom,effet));

        nom = "Racines d'arc en ciel";
        effet = new int[][] {{4,1,1,1},{1,2,1,3},{1,2,2,2}};
        this.paquetCarte.add(new CarteIngredient(nom,effet));

        nom = "Racines d'arc en ciel";
        effet = new int[][] {{2,3,2,0},{0,4,3,0},{2,1,1,3}};
        this.paquetCarte.add(new CarteIngredient(nom,effet));

        nom = "Racines d'arc en ciel";
        effet = new int[][] {{2,2,3,0},{1,1,1,4},{2,0,3,2}};
        this.paquetCarte.add(new CarteIngredient(nom,effet));

        nom = "Esprit de dolmen";
        effet = new int[][] {{3,1,4,1},{2,1,3,3},{2,3,2,2}};
        this.paquetCarte.add(new CarteIngredient(nom,effet));

        nom = "Esprit de dolmen";
        effet = new int[][] {{2,4,1,2},{2,2,2,3},{1,4,3,1}};
        this.paquetCarte.add(new CarteIngredient(nom,effet));

        nom = "Esprit de dolmen";
        effet = new int[][] {{3,3,3,0},{1,3,3,2},{2,3,1,3}};
        this.paquetCarte.add(new CarteIngredient(nom,effet));

        nom = "Rire de fees";
        effet = new int[][] {{1,2,2,1},{1,2,3,0},{0,2,2,2}};
        this.paquetCarte.add(new CarteIngredient(nom,effet));

        nom = "Rire de fees";
        effet = new int[][] {{4,0,1,1},{1,1,3,1},{0,0,3,3}};
        this.paquetCarte.add(new CarteIngredient(nom,effet));

        nom = "Rire de fees";
        effet = new int[][] {{2,0,1,3},{0,3,0,3},{1,2,2,1}};
        this.paquetCarte.add(new CarteIngredient(nom,effet));

        Collections.shuffle(this.getPaquetCarte());
    }

}
