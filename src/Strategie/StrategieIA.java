package Strategie;

import joueur.*;
import Partie.*;

public interface StrategieIA {

    void choisirCarte (Joueur joueur, Partie partie);
    void graineOuAllie(PartieAvancee partie, Joueur joueur);
}
