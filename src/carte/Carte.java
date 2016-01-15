/**
 * Package qui gere les cartes et les actions qui y sont liées
 * @author Le Mercier - Gerard
 * @version 1.0
 */
package carte;

/**
 * Classe mere qui gere represente les cartes du jeu
 * @author Le Mercier - Gerard
 * @version 1.0
 */
public class Carte {

    protected String nom;
    /**
     * Constructeur surcharge de la classe Carte
     * @param nom Le nom de la carte
     * @author Le Mercier - Gerard
     * @version 1.0
     */
    public Carte(String nom) {
        this.nom = nom;
    }

    /**
     * Getter qui retourne le nom de la carte
     * @return Le nom de la carte
     * @author Le Mercier - Gerard
     * @version 1.0
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Méthode qui transforme la saion en un entier compris en 0 (printemps) et 3 (hiver)
     * @param saison La saison qui doit etre convertie
     * @return Un entier qui représente la saison en cours
     * @author Le Mercier - Gerard
     * @version 1.0
     */
    public int convertirSaisonInt(Saison saison){
        int retour =0;
        switch (saison){
            case PRINTEMPS:
                    retour = 0;
            break;
            case ETE:
                retour = 1;
            break;
            case AUTOMNE:
                retour = 2;
            break;
            case HIVER:
                retour = 3;
            break;

        }
        return retour;
    }



}
