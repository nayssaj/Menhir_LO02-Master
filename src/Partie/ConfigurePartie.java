package Partie;

import carte.*;


/**
 * Created by jglem_000 on 28/11/2015.
 */
public class ConfigurePartie {

    private String typePartie;
    private Partie partie;
    private Affichage mainUI;

    public Partie configurerPartie(){
        mainUI = new Affichage();
        mainUI.acceuil();
        typePartie = mainUI.typePartie();
        int nbJoueur = mainUI.nbJoueurs();
        if(typePartie.equals("RAPIDE")){
            PaquetCarteIngredient paquetIngredient = new PaquetCarteIngredient();
            partie = new Partie(paquetIngredient,nbJoueur, mainUI);
        }
        else{
            PaquetCarteIngredient paquetIngredient = new PaquetCarteIngredient();
            PaquetCarteAlliee paquetAllie = new PaquetCarteAlliee();
            partie = new PartieAvancee(paquetIngredient, paquetAllie, nbJoueur, mainUI);

        }
        return partie;
    }
}
