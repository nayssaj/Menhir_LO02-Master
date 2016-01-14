package Strategie;

import joueur.*;
import Partie.*;

/**
 * Interface qui gere les differentes strategies de l'IA
 * @author Le Mercier - Gerard
 * @version 2.0
 */
public interface StrategieIA {

    void choisirCarte (Joueur joueur, Partie partie);
    void graineOuAllie(PartieAvancee partie, Joueur joueur);
}
