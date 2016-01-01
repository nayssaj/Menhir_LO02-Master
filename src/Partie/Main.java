package Partie;

import carte.PaquetCarteIngredient;

/**
 * Created by jglem_000 on 28/11/2015.
 */

public class Main {
    public static void main (String[] args){
        /*
        Affichage mainUI = new Affichage();
        VueConfiguration vue;
        ConfigurePartie cp = new ConfigurePartie();
        vue = new VueConfiguration(cp);
        cp.addObserver(vue);
        */
        Affichage affich = new Affichage();
        PaquetCarteIngredient paquet = new PaquetCarteIngredient();
        Partie partie = new Partie(paquet, 5, affich, 15, "F");
        partie.initaliserPartie();
        VuePartie vueTest = new VuePartie(partie);
        vueTest.getFenetre().setVisible(true);
        partie.addObserver(vueTest);
        partie.getJoueurHumain().addObserver(vueTest);
        partie.lancerPartie();
        vueTest.actualiserSaison();
    }
}
