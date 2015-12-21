package Partie;

import java.util.*;

/**
 * Created by jglem_000 on 27/11/2015.
 */
import carte.*;
import joueur.*;


public class Partie extends Observable{

    private Affichage mainUI;
    private PaquetCarteIngredient deckIngredient;
    private ArrayList<Joueur> listeJoueur;
    private Saison saison;
    private int nbManches;
    private int numManche = 1;



    //Contrôle le déroulement de la partie
    public void lancerPartie() {
            this.initaliserPartie();
            this.setChanged();
            this.notifyObservers("Message");
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
                joueur.jouerCarte(this);
                joueur.getJoueurUI().infoJoueur(joueur);
            }
            this.prochaineSaison();
        }
        Iterator<Joueur> itScore = this.getListeJoueur().iterator();
        while (itScore.hasNext()){
            Joueur joueurManche = itScore.next();
            joueurManche.modifierScore();
            System.out.println(joueurManche.getNbPoint());
        }
    }

    //Attribue a chaque joueur les ressources dont il a besoin
    public void initaliserPartie(){

        int nbCarteADistribuer = 4;

        this.setOrdreJoueur();

        //On donne deux graines a chaque joueur
        Iterator itJoueur = this.listeJoueur.iterator();
        while(itJoueur.hasNext()){
            Joueur joueur = (Joueur) itJoueur.next();
            joueur.setNbGraine(2);
        }

        this.getDeckIngredient().genererPaquetIngredient();
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

    public void setOrdreJoueur(){
        //On sépare les joueurs des joueuses
        ArrayList<Joueur> joueuses = new  ArrayList<Joueur>();
        Iterator<Joueur> itJoueur = listeJoueur.iterator();
        while(itJoueur.hasNext()){
            Joueur joueur = itJoueur.next();
            if(joueur.getSexe().equals("F")){
                joueuses.add(joueur);
            }
        }
        //On recherche le plus jeune joueur parmi les joueuses
        Iterator itJoueuses = joueuses.iterator();
        Joueur premierJoueur = (Joueur) itJoueuses.next();
        while (itJoueuses.hasNext()) {
            Joueur joueuse = (Joueur) itJoueuses.next();
            if(joueuse.getAge() < premierJoueur.getAge()){
                premierJoueur = joueuse;
            }
        }
        //On retire le premier joueur de la liste, on mélange le reste puis on rajoute le premier joueur en première position
        this.getListeJoueur().remove(premierJoueur);
        Collections.shuffle(this.getListeJoueur());
        this.getListeJoueur().add(0, premierJoueur);
    }

    public ArrayList<Joueur> aGagne(){
        //TODO check fonction max pour collections
        ArrayList<Joueur> joueursGagants = new ArrayList<Joueur>();
        joueursGagants.add(this.getListeJoueur().get(0));
        Iterator<Joueur> itJoueur = this.listeJoueur.iterator();
        itJoueur.next();//On saute le premier joueur que l'on à déja inclu arbitrairement
        while(itJoueur.hasNext()){
            Joueur joueur = itJoueur.next();
            if(joueursGagants.get(0).getNbPoint() < joueur.getNbPoint()){
                joueursGagants.clear();
                joueursGagants.add(joueur);
            }
            else if(joueursGagants.get(0).getNbPoint() == joueur.getNbPoint()){
                if(joueursGagants.get(0).getNbGraine() < joueur.getNbGraine()){
                    joueursGagants.clear();
                    joueursGagants.add(joueur);
                }
                else if(joueursGagants.get(0).getNbGraine() == joueur.getNbGraine()){
                    joueursGagants.add(joueur);
                }
            }
        }
        return joueursGagants;
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
        String nomJoueurHumain = "Joueur Humain";
        int age = mainUI.demanderAge();
        String sexe = mainUI.demanderSexe();
        Joueur joueurHumain = new Joueur(nomJoueurHumain, age, sexe, joueurUI);
        this.getListeJoueur().add(joueurHumain);
        for (int joueurCree = 2; joueurCree <= nbJoueur; joueurCree++){
            String nomJoueurIA = "Joueur " + joueurCree;
            JoueurVirtuel joueurIA = new JoueurVirtuel(nomJoueurIA, 18, "H", joueurUI);
            this.getListeJoueur().add(joueurIA);
        }
        this.mainUI = mainUI;
        this.deckIngredient = paquet;
        saison = Saison.FIN_ANNEE;
    }


}
