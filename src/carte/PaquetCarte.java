package carte;


import java.util.LinkedList;

/**
 * Classe mere qui represente un paquet de carte
 * @author Le Mercier - Gerard
 * @version 1.0
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
