package joueur;
import Partie.*;
import carte.*;

import Strategie.*;

import java.util.ArrayList;

/**
 * Created by jglem_000 on 04/12/2015.
 */
public class JoueurVirtuel extends Joueur {

    private StrategieIA strategie;

    public void jouerCarte(Partie partie){
        strategie.choisirCarte(this,partie);
    }

    public void jouerTaupe(ArrayList<Joueur> cibles, Saison saison){
        strategie.jouerTaupe(cibles, saison);
    }

    public JoueurVirtuel(String nom, int age, String sexe, AffichageJoueur joueurUI) {
        super(nom, age, sexe, joueurUI);
        this.strategie = new Passive();
    }

    public void graineOuAllie(PartieAvancee partie, Joueur joueur){
        strategie.graineOuAllie(partie, joueur);
    }
}
