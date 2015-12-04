package carte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Created by juliengerard on 27/11/2015.
 */
public class PaquetCarteAlliee extends PaquetCarte {


    public PaquetCarteAlliee() {
        super();
    }

    public void genererPaquetAlliee() {

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
