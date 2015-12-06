package carte;

/**
 * Created by juliengerard on 25/11/2015.
 */
public class Carte {

    //Nom de la carte
    protected String nom;
    //Constructeur d'une carte
    public Carte(String nom) {
        this.nom = nom;
    }


    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom=nom;
    }

    //Méthode qui convertit une saison en un entier utile pour les effets des cartes
    public int convertirSaisonInt(Saison saison){
        int retour =0;
        switch (saison){
            case PRINTEMPS:
                retour = 0;
            break;
            case ETE:
                retour = 1;
            break;
            case AUTOMNE:
                retour = 2;
            break;
            case HIVER:
                retour = 3;
            break;

        }
        return retour;
    }



}
