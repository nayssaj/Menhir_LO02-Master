package Partie;

import java.util.*;

/**
 * Created by jglem_000 on 27/11/2015.
 */
import carte.*;
import joueur.*;


public class Partie {

    private Affichage mainUI;
    private PaquetCarteIngredient deckIngredient;
    private ArrayList<Joueur> listeJoueur;
    private Saison saison;
    private int nbManches;
    private int numManche = 1;



    //Contrôle le déroulement de la partie
    public void lancerPartie() {
        this.initaliserPartie();
        this.prochaineSaison();
        this.jouerSaisons();
        this.mainUI.gagnant(aGagne());
    }

    public void jouerSaisons(){
        while (saison != Saison.FIN_ANNEE){
            System.out.println(this.getSaison());
            Iterator itJoueur = listeJoueur.iterator();
            while(itJoueur.hasNext()) {
                Joueur joueur = (Joueur) itJoueur.next();
                System.out.println("C'est au tour de " + joueur);
                joueur.getJoueurUI().infoJoueur(joueur);
                Carte carte = joueur.choisirCarte();
                joueur.choisirAction(carte,this.getListeJoueur(),this.saison);
                joueur.getJoueurUI().infoJoueur(joueur);
            }
            this.prochaineSaison();
        }
    }

    //Attribue a chaque joueur les ressources dont il a besoin
    public void initaliserPartie(){

        int nbCarteADistribuer = 4;

        //On donne deux graines a chaque joueur
        Iterator itJoueur = this.listeJoueur.iterator();
        while(itJoueur.hasNext()){
            Joueur joueur = (Joueur) itJoueur.next();
            joueur.setNbGraine(2);
        }

        //On mélange et distribue le paquet de cartes ingrédients
        Collections.shuffle(this.deckIngredient.getPaquetCarte());
        this.distribuer(this.deckIngredient, nbCarteADistribuer);
    }

    //Distribution d'un nombre précis de cartes d'un paquet quelconque
    //La méthode renvoi une exception si elle ne peut pas distribuer un nombre n de carte à CHAQUE joueur
    public void distribuer(PaquetCarte paquet, int nbCarte){
        int nbDistribue = 0;
        while(nbDistribue < nbCarte){
            Iterator itJoueur = this.listeJoueur.iterator();
            while(itJoueur.hasNext()){
                Joueur joueur = (Joueur) itJoueur.next();
                //TODO verifier implémentation classe joueur attribut main
                joueur.getCarteEnMain().add(paquet.getPaquetCarte().getFirst());
                paquet.getPaquetCarte().removeFirst();
            }
            nbDistribue++;
        }
    }

    //Distribution d'un nombre précis de cartes d'un paquet quelconque à un joueur quelconque
    public void distribuer(Joueur joueur, PaquetCarte paquet, int nbCarte){
        int nbDistribue = 0;
        while(nbDistribue < nbCarte){
            //TODO verifier implémentation classe joueur attribut main
            joueur.getCarteEnMain().add(paquet.getPaquetCarte().getFirst());
            paquet.getPaquetCarte().removeFirst();
            nbDistribue++;
        }
    }

    public void prochaineSaison(){
        switch(this.saison){
            case PRINTEMPS:
                this.saison = Saison.ETE;
                break;
            case ETE:
                this.saison = Saison.AUTOMNE;
                break;
            case AUTOMNE:
                this.saison = Saison.HIVER;
                break;
            case HIVER:
                this.saison = Saison.FIN_ANNEE;
                break;
            case FIN_ANNEE:
                this.saison = Saison.PRINTEMPS;
                break;
        }
    }

    //Permet de gérér la condition de fin de partie
    public boolean finPartie(){
        return saison == Saison.FIN_ANNEE;
    }

    public ArrayList<Joueur> getListeJoueur() {
        return listeJoueur;
    }

    public Saison getSaison() {
        return saison;
    }

    public int getNbManches() {
        return nbManches;
    }

    public void setNbManches(int nbManches) {
        this.nbManches = nbManches;
    }

    public int getNumManche() {
        return numManche;
    }

    public void setNumManche(int numManche) {
        this.numManche = numManche;
    }

    public Affichage getMainUI() {
        return mainUI;
    }

    public PaquetCarteIngredient getDeckIngredient() {
        return deckIngredient;
    }

    public Partie(PaquetCarteIngredient paquet, int nbJoueur, Affichage mainUI) {
        listeJoueur = new ArrayList();
        AffichageJoueur joueurUI = new AffichageJoueur();
        for (int joueurCree = 1; joueurCree <= nbJoueur; joueurCree++){
            String nomJoueur = "Joueur " + joueurCree;
            Joueur joueur = new Joueur(nomJoueur, 18, joueurUI);
            this.getListeJoueur().add(joueur);
        }
        System.out.println(listeJoueur);
        this.mainUI = mainUI;
        this.deckIngredient = paquet;
        saison = Saison.FIN_ANNEE;
    }

    public HashSet<Joueur> aGagne(){
        HashSet<Joueur> joueursGagants = new HashSet<>();
        joueursGagants.add(this.getListeJoueur().get(0));
        Iterator<Joueur> itJoueur = this.listeJoueur.iterator();
        while(itJoueur.hasNext()){
            Joueur joueur = itJoueur.next();
            if(this.getListeJoueur().get(0).getNbMenhir() < joueur.getNbMenhir()){
                joueursGagants.clear();
                joueursGagants.add(joueur);
            }
            else if(this.getListeJoueur().get(0).getNbMenhir() == joueur.getNbMenhir()){
                    if(this.getListeJoueur().get(0).getNbGraine() <joueur.getNbGraine()){
                        joueursGagants.clear();
                        joueursGagants.add(joueur);
                }
            }
        }
        return joueursGagants;
    }
}
