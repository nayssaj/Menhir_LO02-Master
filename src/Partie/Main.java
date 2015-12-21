package Partie;

/**
 * Created by jglem_000 on 28/11/2015.
 */

public class Main {
    public static void main (String[] args){

        Affichage mainUI = new Affichage();
        VueConfiguration vue;
        ConfigurePartie cp = new ConfigurePartie();
        vue = new VueConfiguration(cp);
        cp.addObserver(vue);
    }
}
