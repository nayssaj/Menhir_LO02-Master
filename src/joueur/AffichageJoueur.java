package joueur;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jglem_000 on 01/12/2015.
 */
//interaction avec les choix de l'utilsiateur
public class AffichageJoueur {

    public Joueur choixCible(ArrayList listeJoueur){
        System.out.println("Quel Joueur voulez vous designer ? (Entrez le numéro du joueur)");
        System.out.println(listeJoueur);
        Scanner sc = new Scanner(System.in);
        return (Joueur) listeJoueur.get(sc.nextInt()-1);
    }

    public int choixMain(Joueur joueur){
        System.out.println("Quelle carte voulez vous jouer ? (Entrez le numéro de la carte)");
        System.out.println(joueur.getCarteEnMain());
        Scanner sc = new Scanner(System.in);
        return (sc.nextInt()-1);
    }

    public String choixAction(){
        System.out.println("Quelle action voulez vous effectuer ? (GEANT/ENGRAIS/FARFADET)");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public String proposerGrainesOuAllie(){
        System.out.println("Que choisissez vous ? (GRAINES/ALLIE)");
        Scanner scPartie = new Scanner(System.in);
        return scPartie.nextLine();
    }

    public boolean proposerTaupe(){
        System.out.println("Voulez vous jouer une carte taupe ? (TRUE/FALSE)");
        Scanner sc = new Scanner(System.in);
        return sc.nextBoolean();
    }

    public void infoJoueur(Joueur joueur){
        System.out.println("Nb graines : "+joueur.getNbGraine()+"\nNb menhir(s) : "+joueur.getNbMenhir());
    }

    public void infoGeant(int nbEffet){
        System.out.println("Le géant vous donne "+ nbEffet+" graine(s)");
    }

    public void infoEngrais(int nbEffet){
        System.out.println("L'engrais a fait pousse "+nbEffet+" graines(s) en menhir");
    }

    public void infoFarfadet(int nbEffet){
        System.out.println("Les farfadets ont pris " + nbEffet +" a votre adversaire");
    }

}
