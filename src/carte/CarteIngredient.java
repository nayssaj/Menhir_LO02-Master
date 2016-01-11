package carte;

import joueur.Joueur;

/**
 * Classe qui represente les cartes ingredients, elle herite de Carte
 * @author Le Mercier - Gerard
 * @version 1.0
 */
public class CarteIngredient extends Carte {


    public static final int GEANT = 0;
    public static final int ENGRAIS = 1;
    public static final int FARFADETS = 2;

    private int[][] effet;


    /**
     * Constructeur surcharge de la classe CarteIngredient
     * @param nom Nom de la carte ingredient
     * @param effet Tableau qui contient les effets de la carte (4 saisons et 3 roles)
     * @author Le Mercier - Gerard
     * @version 1.0
     */
    public CarteIngredient(String nom, int [][] effet) {
        super(nom);
        this.effet=effet;
    }

    /**
     * Getter pour obtenir l'effet d'une carte pour une saison et un role donne
     * @param i Role de la carte 0 (geant), 1 (engrais), 2 (farfadets)
     * @param j Saison de la carte entre 0 et 3
     * @return L'effet de la carte pour la saison et le role donne
     * @author Le Mercier - Gerard
     * @version 1.0
     */
    public int getForce(int i, int j){
        return this.effet[i][j];
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
        sb.append(super.nom);
        sb.append("\n");
        for (int i=0; i<3; i++){
            for(int j = 0;j<4;j++) {
                sb.append(this.effet[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Methode que gere l'action du farfadet (prendre des graines a un joueur)
     * @param attaquant Le joueur qui va beneficier du farfadet (Celui qui est porteur de la carte)
     * @param cible Le joueur qui va subir l'attaque du farfadet
     * @param chien Le joueur qui subit l'attaque a t'il une carte chien ?
     * @param saison La saison en cours dans la partie
     * @return Le nombre de graine(s) retirée(s) au joueur cible
     * @author Le Mercier - Gerard
     * @version 1.0
     */
    public int actionFarfadet(Joueur attaquant, Joueur cible, boolean chien, Saison saison){
        int saisonInt = convertirSaisonInt(saison);
        if(chien){
            int graineDispo = cible.getNbGraine();
            int effet = this.effet[FARFADETS][saisonInt]-cible.obtenirEffetChien(saisonInt);
            if(effet>0) {
                if (graineDispo >= effet) {
                    cible.modifierGraine(-effet);
                    attaquant.modifierGraine(effet);
                    return effet;

                } else {
                    cible.setNbGraine(0);
                    attaquant.modifierGraine(graineDispo);
                    return graineDispo;
                }
            }else{
                return 0;
            }
        }
        else{
            int graineDispo=cible.getNbGraine();
            int effet = this.effet[FARFADETS][saisonInt];
            if(graineDispo>=effet){
                cible.modifierGraine(-effet);
                attaquant.modifierGraine(effet);
                return effet;
            }
            else{
                cible.setNbGraine(0);
                attaquant.modifierGraine(graineDispo);
                return graineDispo;
            }
        }
    }

    /**
     * Methode qui gere l'action de l'engrais (transformer des graines en menhir)
     * @param acteur Le joueur qui pose la carte engrais
     * @param saison La saison en cours dans la partie
     * @return Le nombre de graine(s) transformee(s) en menhir
     * @author Le Mercier - Gerard
     * @version 1.0
     */
    public int actionEngrais(Joueur acteur,Saison saison){
        int valeur = acteur.getNbGraine();
        int saisonInt = convertirSaisonInt(saison);
        if((valeur-this.effet[ENGRAIS][saisonInt])<0){
            acteur.modifierGraine(-valeur);
            acteur.modifierMenhir(valeur);
            acteur.setNbGraine(0);
            return valeur;
        }
        else{
            acteur.modifierGraine(-this.effet[ENGRAIS][saisonInt]);
            acteur.modifierMenhir(this.effet[ENGRAIS][saisonInt]);
            return this.effet[ENGRAIS][saisonInt];
        }

    }

    /**
     * Methode qui gere l'action du geant (gagner des graines)
     * @param acteur Le joeuur qui pose la carte geant
     * @param saison La saison en cours dans la partie
     * @return Le nombre de graine(s) gagnee(s)
     * @author Le Mercier - Gerard
     * @version 1.0
     */
    public int actionGeant(Joueur acteur,Saison saison){
        int saisonInt = convertirSaisonInt(saison);
        acteur.modifierGraine(this.effet[GEANT][saisonInt]);
        return this.effet[GEANT][saisonInt];
    }
}
