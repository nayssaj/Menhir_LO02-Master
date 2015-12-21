package Partie;

import joueur.Joueur;

import java.util.*;

/**
 * Created by jglem_000 on 01/12/2015.
 */

public class Affichage {

    public void acceuil(){
        System.out.println("Bienvenue dans le jeu du menhir !");
    }

    public void gagnant(ArrayList<Joueur> gagants){
        Iterator itGagnant = gagants.iterator();
        while (itGagnant.hasNext()){
            System.out.println(itGagnant.next() + " à gagné");
        }
}
    public boolean reJouer(){
        System.out.println("Voulez vous refaire une partie ? (TRUE/FALSE)");
        Scanner scReJouer = new Scanner(System.in);
        return scReJouer.nextBoolean();
    }
}
