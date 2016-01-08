package Partie;

import java.util.*;

import carte.*;
import joueur.*;


public class Partie extends Observable{

    private Joueur joueurHumain;
    private PaquetCarteIngredient deckIngredient;
    private ArrayList<Joueur> listeJoueur;
    private ArrayList<Joueur> listeJoueurAvantHumain;
    private ArrayList<Joueur> listeJoueurApresHumain;
    private Saison saison;
    private int nbManches;
    private int numManche = 1;



    //Contrôle le déroulement de la partie
    public void lancerPartie() {
        this.prochaineSaison();
        this.jouerUneSaison(this.getListeJoueurAvantHumain());
        this.setChanged();
        this.notifyObservers("C'est à votre tour, Choisissez votre carte\n");
    }

    public void jouerUneSaison(ArrayList<Joueur> joueurVirtuels){
            Iterator<Joueur> itJoueur = joueurVirtuels.iterator();
            while(itJoueur.hasNext()) {
                Joueur joueur = itJoueur.next();
                if(joueur instanceof JoueurVirtuel){
                    joueur.jouerCarte(this);
                    this.setChanged();
                    this.notifyObservers(joueur.getNom() + " effectue l'action " + joueur.getActionEffectuée() + "\n");
                }
            }
            if(this.getSaison().equals("FIN_ANNEE")){
                this.numManche++;
            }
            if(joueurVirtuels == listeJoueurApresHumain){
                prochaineSaison();
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

        listeJoueurAvantHumain = new ArrayList<>();
        listeJoueurApresHumain = new ArrayList<>();

        //On sépare les joueurs des joueuses
        ArrayList<Joueur> joueuses = new  ArrayList<>();
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
        int place;
        place = this.getListeJoueur().indexOf(joueurHumain);
        for(int i = 0; i < place; i++){
            listeJoueurAvantHumain.add(listeJoueur.get(i));
        }
        for(int i = place + 1; i < listeJoueur.size(); i++){
            listeJoueurApresHumain.add(listeJoueur.get(i));
        }
    }

    public ArrayList<Joueur> aGagne(){
        ArrayList<Joueur> joueursGagants = new ArrayList<>();
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

    public Joueur getJoueurHumain() {
        return joueurHumain;
    }

    public ArrayList<Joueur> getListeJoueurApresHumain() {
        return listeJoueurApresHumain;
    }

    public ArrayList<Joueur> getListeJoueurAvantHumain() {

        return listeJoueurAvantHumain;
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

    public PaquetCarteIngredient getDeckIngredient() {
        return deckIngredient;
    }

    public Partie(PaquetCarteIngredient paquet, int nbJoueur, int age, String sexe) {
        listeJoueur = new ArrayList();
        String nomJoueurHumain = "Joueur Humain";
        joueurHumain = new Joueur(nomJoueurHumain, age, sexe);
        this.getListeJoueur().add(joueurHumain);
        for (int joueurCree = 2; joueurCree <= nbJoueur; joueurCree++){
            String nomJoueurIA = "Joueur " + joueurCree;
            JoueurVirtuel joueurIA = new JoueurVirtuel(nomJoueurIA, 13, "F");
            this.getListeJoueur().add(joueurIA);
        }
        this.deckIngredient = paquet;
        saison = Saison.FIN_ANNEE;
    }


}
