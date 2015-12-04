package Partie;

import joueur.Joueur;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by jglem_000 on 01/12/2015.
 */

public class Affichage {

    public void acceuil(){
        System.out.println("Bienvenue dans le jeu du menhir !");
    }

    public String typePartie(){
        System.out.println("Tout d'abord indiquez quel type de partie vous desirez jouer ? (RAPIDE/AVANCEE)");
        Scanner scPartie = new Scanner(System.in);
        return scPartie.nextLine();
    }

    public int nbJoueurs(){
        System.out.println("Indiquez maintenant le nombre de joueurs de la partie ?");
        Scanner scJoueur = new Scanner(System.in);
       return scJoueur.nextInt();
    }

    public void gagnant(HashSet<Joueur> gagants){
        Iterator itGagnant = gagants.iterator();
        while (itGagnant.hasNext()){
            System.out.println(itGagnant.next() + " à gagné");
        }
    }
}
