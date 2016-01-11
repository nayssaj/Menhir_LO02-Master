package carte;


import java.util.Collections;

/**
 * Classe qui represente le paquet de carte alliee qui herite de PaquetCarte
 * @author Le Mercier - Gerard
 * @version 1.0
 */
public class PaquetCarteAlliee extends PaquetCarte {


    /**
     * Le constructeur de la classe est identique a celui de la classe mere, il genere un paquet vide
     * @author Le Mercier - Gerard
     * @version 1.0
     */
    public PaquetCarteAlliee() {
        super();
    }

    /**
     * La methode remplie le paquet de carte vide et melange le paquet de carte
     * @author Le Mercier - Gerard
     * @version 1.0
     */
    public void genererPaquetAlliee() { //Vide le paquet existant et génére un nouevau paquet

        this.paquetCarte.clear();

        String nom = new String("Taupe geante");
        int[] effet = new int[]{1, 1, 1, 1};
        this.paquetCarte.add(new CarteAlliees(nom, effet));

        nom = new String("Taupe geante");
        effet = new int[]{0, 2, 2, 0};
        this.paquetCarte.add(new CarteAlliees(nom, effet));

        nom = new String("Taupe geante");
        effet = new int[]{0, 1, 2, 1};
        this.paquetCarte.add(new CarteAlliees(nom, effet));

        nom = new String("Chien de garde");
        effet = new int[]{2, 0, 2, 0};
        this.paquetCarte.add(new CarteAlliees(nom, effet));

        nom = new String("Chien de garde");
        effet = new int[]{1, 2, 0, 1};
        this.paquetCarte.add(new CarteAlliees(nom, effet));

        nom = new String("Chien de garde");
        effet = new int[]{0, 1, 3, 0};
        this.paquetCarte.add(new CarteAlliees(nom, effet));

        Collections.shuffle(this.paquetCarte);
    }
}
