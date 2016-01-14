package Partie;

import java.util.*;

import carte.*;
import joueur.*;

/**
 * Classe qui gere la partie en cours
 * @author Le Mercier - Gerard
 * @version 2.0
 */
public class Partie extends Observable{

    private Joueur joueurHumain;
    private PaquetCarteIngredient deckIngredient;
    private ArrayList<Joueur> listeJoueur;
    private ArrayList<Joueur> listeJoueurAvantHumain;
    private ArrayList<Joueur> listeJoueurApresHumain;
    private Saison saison;
    private int nbManches;
    private int numManche = 1;


    /**
     * Contrôle le déroulement de la partie
     * Contrôle le déroulement de la partie
     */
    public void lancerPartie() {
        this.prochaineSaison();
        this.jouerUneSaison(this.getListeJoueurAvantHumain());
        this.setChanged();
        this.notifyObservers("C'est à votre tour, Choisissez votre carte\n");
    }

    /**
     * Gere un tour de jeu
     * @param joueurVirtuels La liste des joueurs IA
     * @author Le Mercier - Gerard
     * @version 2.0
     */
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


    /**
     * Attribue a chaque joueur les ressources dont il a besoin
     * @author Le Mercier - Gerard
     * @version 2.0
     */
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

    /**
     * Distribution d'un nombre précis de cartes d'un paquet quelconque
     * La méthode renvoi une exception si elle ne peut pas distribuer un nombre n de carte à CHAQUE joueur
     * @param paquet Le paquet de carte a distribuer
     * @param nbCarte le nombre de carte a distribuer
     * @author Le Mercier - Gerard
     * @version 2.0
     */
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

    /**
     * Distribution d'un nombre précis de cartes d'un paquet quelconque à un joueur quelconque
     * @param joueur
     * @param paquet
     * @param nbCarte
     * @author Le Mercier - Gerard
     * @version 2.0
     */
    public void distribuer(Joueur joueur, PaquetCarte paquet, int nbCarte){
        int nbDistribue = 0;
        while(nbDistribue < nbCarte){
            joueur.getCarteEnMain().add(paquet.getPaquetCarte().getFirst());
            paquet.getPaquetCarte().removeFirst();
            nbDistribue++;
        }
    }

    /**
     * Changement de la saison (passage a la suivante)
     * @author Le Mercier - Gerard
     * @version 2.0
     */
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

    /**
     * Definir l'ordre des joueurs selon l'age et le sexe
     * @author Le Mercier - Gerard
     * @version 2.0
     */
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

    /**
     * Renvoyer un tableau contenant le(s) gagnant(s) de la partie
     * @return Tableau avec le(s) gagnant(s)
     * @author Le Mercier - Gerard
     * @version 2.0
     */
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

    /**
     * Permet de gérér la condition de fin de partie
     * @author Le Mercier - Gerard
     * @version 2.0
     */
    public boolean finPartie(){
        return saison == Saison.FIN_ANNEE;
    }

    /**
     * Getter de la liste de joueurs
     * @return La liste de joueurs
     * @author Le Mercier - Gerard
     * @version 2.0
     */
    public ArrayList<Joueur> getListeJoueur() {
        return listeJoueur;
    }

    /**
     * Getter de la saison actuelle
     * @return La saison en cours
     * @author Le Mercier - Gerard
     * @version 2.0
     */
    public Saison getSaison() {
        return saison;
    }

    /**
     * Getter du joueur humain de la partie
     * @return Le joueur humain
     * @author Le Mercier - Gerard
     * @version 2.0
     */
    public Joueur getJoueurHumain() {
        return joueurHumain;
    }

    /**
     * Getter de la liste des IA jouant apres l'humain
     * @return Une liste de
     * @author Le Mercier - Gerard
     * @version 2.0
     */
    public ArrayList<Joueur> getListeJoueurApresHumain() {
        return listeJoueurApresHumain;
    }

    /**
     * Getter de la liste des IA jouant avant l'humain
     * @return Une liste de joueurs
     * @author Le Mercier - Gerard
     * @version 2.0
     */
    public ArrayList<Joueur> getListeJoueurAvantHumain() {

        return listeJoueurAvantHumain;
    }

    /**
     * Setter du nombre de manches de la partie
     * @param nbManches dans la partie
     * @author Le Mercier - Gerard
     * @version 2.0
     */
    public void setNbManches(int nbManches) {
        this.nbManches = nbManches;
    }

    /**
     * Getter du numero de la manche en cours
     * @return Le numero de la manche en cours
     * @author Le Mercier - Gerard
     * @version 2.0
     */
    public int getNumManche() {
        return numManche;
    }

    /**
     * Setter du numero de la manche en cours
     * @param numManche Le numero de la manche a jouer
     * @author Le Mercier - Gerard
     * @version 2.0
     */
    public void setNumManche(int numManche) {
        this.numManche = numManche;
    }

    /**
     * Getter pour recuperer le paquet de cartes ingredients
     * @return Le paquet de cartes ingredients
     * @author Le Mercier - Gerard
     * @version 2.0
     */
    public PaquetCarteIngredient getDeckIngredient() {
        return deckIngredient;
    }

    /**
     * Constructeur de la classe partie
     * @param paquet Le paquet de carte ingredient a utiliser
     * @param nbJoueur Le nombre de joueur
     * @param age L'age du joueur humain
     * @param sexe Le sexe du joueur humain
     */
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
