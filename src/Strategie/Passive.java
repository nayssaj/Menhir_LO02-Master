package Strategie;
import carte.*;
import joueur.*;
import Partie.*;

import java.util.*;

/**
 * Classe qui gere la strategie passive
 * @author Le Mercier - Gerard
 * @version 1.0
 */
public class Passive implements StrategieIA {

    /**
     * Action de choisir un carte lorsque que la strategie est passive
     * @param joueur Joueur IA
     * @param partie Partie en cours
     * @author Le Mercier - Gerard
     * @version 2.0
     */
    public void choisirCarte(Joueur joueur, Partie partie){
        Iterator<Carte> itCartes = joueur.getCarteEnMain().iterator();
        Carte carteJouee = joueur.getCarteEnMain().get(0);
        itCartes.next();
        //On selectionne la meilleure carte selon le contexte
        while(itCartes.hasNext()){
            Carte carte = itCartes.next();
            int intSaison = carte.convertirSaisonInt(partie.getSaison());
            if(carte instanceof CarteIngredient){
                if (joueur.getNbGraine() > 0){
                    if(((CarteIngredient) carte).getForce(1,intSaison) > ((CarteIngredient) carteJouee).getForce(1, intSaison)){
                        carteJouee = carte;
                    }
                }
                else if (((CarteIngredient) carte).getForce(0,intSaison) > ((CarteIngredient) carteJouee).getForce(0, intSaison)){
                    carteJouee = carte;
                }
            }
        }
        //On effectue une action de la carte selon le contexte
        if (joueur.getNbGraine() > 0){
            joueur.setActionEffectuée("ENGRAIS");
            ((CarteIngredient)carteJouee).actionEngrais(joueur, partie.getSaison());
        }
        else{
            ((CarteIngredient)carteJouee).actionGeant(joueur, partie.getSaison());
            joueur.setActionEffectuée("GEANT");
        }
        joueur.getCarteEnMain().remove(carteJouee);
    }

    /**
     * Choix de prendre des graines dans la strategie passive en partie avancee
     * @param partie Partie en cours
     * @param joueur Joueur IA
     * @author Le Mercier - Gerard
     * @version 2.0
     */
    public void graineOuAllie(PartieAvancee partie, Joueur joueur){
        joueur.setNbGraine(2);
    }
}
