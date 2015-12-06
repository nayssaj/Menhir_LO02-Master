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

    public int demanderAge(){
        System.out.println("Quel est votre age ?");
        Scanner scJoueur = new Scanner(System.in);
        return scJoueur.nextInt();
    }

    public String demanderSexe(){
        System.out.println("Etes-vous un homme ou une femme ? (H/F)");
        Scanner scPartie = new Scanner(System.in);
        return scPartie.nextLine();
    }
}
