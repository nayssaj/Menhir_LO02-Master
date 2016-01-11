package Strategie;
import Partie.*;
import carte.*;
import joueur.Joueur;

import java.util.*;

public class Aggressive implements StrategieIA{

    public void choisirCarte(Joueur joueur, Partie partie){
        Joueur meilleureCible = null;
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
                else if (((CarteIngredient) carte).getForce(2,intSaison) > ((CarteIngredient) carteJouee).getForce(2, intSaison)){
                    carteJouee = carte;
                }
            }
        }
        //On selectionne la meilleure cible si le joueur IA n'a plus de graines
        if (joueur.getNbGraine() == 0){
            //on selectionne d'abord un point de comparaison qui n'est pas le joueur IA
            Iterator<Joueur> itJoueursInit = partie.getListeJoueur().iterator();
            while (itJoueursInit.hasNext()){
                Joueur cible = itJoueursInit.next();
                if (!cible.getNom().equals(joueur.getNom())){
                    meilleureCible = cible;
                }
            }
            Iterator<Joueur> itJoueursCompar = partie.getListeJoueur().iterator();
            while (itJoueursCompar.hasNext()){
                Joueur cible = itJoueursCompar.next();
                if ((!cible.getNom().equals(joueur.getNom()))){
                    if(cible.getNbGraine() > meilleureCible.getNbGraine()){
                        meilleureCible = cible;
                    }
                }
            }
        }
        //On effectue une action de la carte selon le contexte
        if (joueur.getNbGraine() > 0){
            ((CarteIngredient)carteJouee).actionEngrais(joueur, partie.getSaison());
            joueur.setActionEffectuée("Engrais");
        }
        else{
            joueur.setActionEffectuée("Farfadet");
            ((CarteIngredient)carteJouee).actionFarfadet(joueur, meilleureCible, meilleureCible.aCarteChien(), partie.getSaison());
        }
        joueur.getCarteEnMain().remove(carteJouee);
    }

    public void graineOuAllie(PartieAvancee partie, Joueur joueur){
        joueur.setNbGraine(2);
    }
}
