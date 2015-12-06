package carte;

import joueur.Joueur;

import java.util.ArrayList;

/**
 * Created by juliengerard on 27/11/2015.
 */

public class CarteAlliees extends Carte {

    //Contient l'effet de la carte
    private int[] effet;

    //Constructeur de la carte alliée qui prend en parametre son nom et son effet
    public CarteAlliees(String nom, int[] effet){
        super(nom);
        this.effet=effet;
    }



    public int[] getEffet() {
        return effet;
    }

    public void setEffet(int[] effet) {
        this.effet = effet;
    }

    //Convertit le contenu d'une carte en un message affichable sur la console
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

    //L'action va retirer des menhir au joueur cible
    public void actionTaupe(Joueur cible,Saison saison){
        int saisonInt = convertirSaisonInt(saison);
        int valeur = cible.getNbMenhir();
        if((valeur-this.effet[saisonInt])<=0){//Si le joueur cible n'a pas assez de menhir on enleve le max possible pour rester >0
            cible.setNbMenhir(0);
        }
        else{
            cible.modifierMenhir(-this.effet[saisonInt]);
        }
    }
}
