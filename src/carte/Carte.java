package carte;

/**
 * Created by juliengerard on 25/11/2015.
 */
public class Carte {

    //TODO Comprendre le private--Protected
    protected String nom;

    public Carte(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom=nom;
    }

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
