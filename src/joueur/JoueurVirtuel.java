package joueur;
import Partie.*;

import Strategie.*;

public class JoueurVirtuel extends Joueur {

    private StrategieIA strategie;

    public void jouerCarte(Partie partie){
        strategie.choisirCarte(this,partie);
    }

    public JoueurVirtuel(String nom, int age, String sexe) {
        super(nom, age, sexe);
        if (Math.random() < 0.7)
            this.strategie = new Passive();
        else
            this.strategie = new Aggressive();
    }

    public void graineOuAllie(PartieAvancee partie, Joueur joueur){
        strategie.graineOuAllie(partie, joueur);
    }
}
