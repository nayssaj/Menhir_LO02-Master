package carte;

import joueur.Joueur;

import java.util.ArrayList;

public class CarteAlliees extends Carte {


    private int[] effet;//QuI va stocker les 4 saisons

    public CarteAlliees(String nom, int[] effet){
        super(nom);
        this.effet=effet;
    }



    public int[] getEffet() {
        return effet;
    }

    public int getForce(int i){
        return this.effet[i];
    }

    public void setEffet(int[] effet) {
        this.effet = effet;
    }

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
        if((valeur-this.effet[saisonInt])<=0){
            cible.setNbMenhir(0);
        }
        else{
            cible.setNbMenhir(cible.getNbMenhir()-this.effet[saisonInt]);
        }
    }
}
