package Partie;

import carte.*;


/**
 * Created by jglem_000 on 28/11/2015.
 */
public class ConfigurePartie {

    private String typePartie;
    private Partie partie;
    private Affichage mainUI;

    public Partie configurerPartie(){//configuration de la partie :
        mainUI = new Affichage();
        mainUI.acceuil();
        typePartie = mainUI.typePartie();//Si la partie est RAPIDE ou AVANCEE
        int nbJoueur = mainUI.nbJoueurs();//Le nombre de joueur
        if(typePartie.equals("RAPIDE")){
            PaquetCarteIngredient paquetIngredient = new PaquetCarteIngredient();
            partie = new Partie(paquetIngredient,nbJoueur, mainUI);//il suffit d'un paquet pour lancer la partie
        }
        else{
            PaquetCarteIngredient paquetIngredient = new PaquetCarteIngredient();
            PaquetCarteAlliee paquetAllie = new PaquetCarteAlliee();// On genere la paquet allies supplementaire
            partie = new PartieAvancee(paquetIngredient, paquetAllie, nbJoueur, mainUI);

        }
        return partie;
    }
}
