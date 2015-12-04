package Partie;

/**
 * Created by jglem_000 on 28/11/2015.
 */

public class Main {
    public static void main (String[] args){

        Affichage mainUI = new Affichage();

        do {
            ConfigurePartie cp = new ConfigurePartie();
            Partie pt = cp.configurerPartie();
            pt.lancerPartie();
        }while(mainUI.reJouer());







    }
}
