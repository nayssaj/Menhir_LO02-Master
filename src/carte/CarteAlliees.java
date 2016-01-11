package carte;

import joueur.Joueur;

/**
 * Classe qui represente les cartes alliees du jeu, elle herite de carte
 * @author Le Mercier - Gerard
 * @version 1.0
 */
public class CarteAlliees extends Carte {


    private int[] effet;//QuI va stocker les 4 saisons

    /**
     * Constructeur surcharge de la carte alliee
     * @param nom Nom de la carte
     * @param effet Tableau qui contient les effets de la carte (4 saisons)
     * @author Le Mercier - Gerard
     * @version 1.0
     */
    public CarteAlliees(String nom, int[] effet){
        super(nom);
        this.effet=effet;
    }

    /**
     * Getter pour obtenir l'effet de la carte selon une saison donnee
     * @param i Represente la saison
     * @return L'effet pour la saison i à partir du tableau effet
     * @author Le Mercier - Gerard
     * @version 1.0
     */
    public int getForce(int i){
        return this.effet[i];
    }

    /**
     * Méthode qui donne sous forme d'un string les effets de la carte
     * @return L'effet de la carte  sous la forme d'un String
     * @author Le Mercier - Gerard
     * @version 1.0
     */
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("Nom : ");
        sb.append(nom);
        sb.append("\n");

        for(int j = 0;j<4;j++) {
            sb.append(this.effet[j]);
            sb.append(" ");
        }
        sb.append("\n");


        return sb.toString();
    }

    /**
     * L'action va retirer des menhir au joueur cible
     * @param cible Le joueur qui va subir l'attaque de la taupe
     * @param saison La saison en cours dans la partie
     * @author Le Mercier - Gerard
     * @version 1.0
     */

    public void actionTaupe(Joueur cible,Saison saison){
        int saisonInt = convertirSaisonInt(saison);
        int valeur = cible.getNbMenhir();
        if((valeur-this.effet[saisonInt])<=0){
            cible.setNbMenhir(0);
        }
        else{
            cible.setNbMenhir(cible.getNbMenhir()-this.effet[saisonInt]);
        }
    }
}
