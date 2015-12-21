package Strategie;
import carte.*;
import joueur.*;
import Partie.*;

import java.util.*;

/**
 * Created by jglem_000 on 04/12/2015.
 */
public class Passive implements StrategieIA {

    public void choisirCarte(Joueur joueur, Partie partie){
        Iterator<Carte> itCartes = joueur.getCarteEnMain().iterator();
        System.out.println(joueur.getCarteEnMain());
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
            ((CarteIngredient)carteJouee).actionEngrais(joueur, partie.getSaison());
        }
        else{
            ((CarteIngredient)carteJouee).actionGeant(joueur, partie.getSaison());
        }
        joueur.getCarteEnMain().remove(carteJouee);
    }

    public void jouerTaupe(ArrayList<Joueur> cibles, Saison saison){};

    public void graineOuAllie(PartieAvancee partie, Joueur joueur){
        joueur.setNbGraine(2);
    }
}
