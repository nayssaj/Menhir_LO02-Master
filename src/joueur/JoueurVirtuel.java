package joueur;

import Partie.*;
import Strategie.*;

/**
 * Classe qui applique les stratégies pour les IA, herite de Joueur
 * @author Le Mercier - Gerard
 * @version 1.0
 */
public class JoueurVirtuel extends Joueur {

    private StrategieIA strategie;

    /**
     * Classe qui joue une carte en accord avec la stratégie jouee
     * @param partie La partie en cours
     * @author Le Mercier - Gerard
     * @version 1.0
     */
    public void jouerCarte(Partie partie){
        strategie.choisirCarte(this,partie);
    }

    /**
     * Constructeur surcharge de la classe JoueurVirtuel
     * @param nom Le nom de l'IA
     * @param age L'age de l'IA
     * @param sexe Le sexe de l'IA
     */
    public JoueurVirtuel(String nom, int age, String sexe) {
        super(nom, age, sexe);
        if (Math.random() < 0.7)
            this.strategie = new Passive();
        else
            this.strategie = new Aggressive();
    }

    /**
     * Methode pour que l'IA choisisse entre deux graines ou une carte alliee
     * @param partie La partie en cours
     * @param joueur Le joueur IA de la classe
     */
    public void graineOuAllie(PartieAvancee partie, Joueur joueur){
        strategie.graineOuAllie(partie, joueur);
    }
}
