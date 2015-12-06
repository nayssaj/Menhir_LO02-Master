package carte;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by juliengerard on 27/11/2015.
 */
public class PaquetCarte {


    public PaquetCarte() {
        paquetCarte = new LinkedList<Carte>();
    }

    protected LinkedList<Carte> paquetCarte;//Linked list car pas besoin d'acceder a un indice precis

    public LinkedList<Carte> getPaquetCarte() {
        return paquetCarte;
    }



}
