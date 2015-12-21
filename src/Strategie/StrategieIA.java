package Strategie;

import carte.*;
import joueur.*;
import Partie.*;

import java.util.*;

/**
 * Created by jglem_000 on 04/12/2015.
 */
public interface StrategieIA {

    public void choisirCarte (Joueur joueur, Partie partie);
    public void jouerTaupe(ArrayList<Joueur> cibles, Saison saison);
    public void graineOuAllie(PartieAvancee partie, Joueur joueur);
}
