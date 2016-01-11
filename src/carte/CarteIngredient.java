package carte;

import joueur.Joueur;

public class CarteIngredient extends Carte {


    public static final int GEANT = 0;
    public static final int ENGRAIS = 1;
    public static final int FARFADETS = 2;

    private int[][] effet;



    public CarteIngredient(String nom, int [][] effet) {
        super(nom);
        this.effet=effet;
    }

    public int getForce(int i, int j){
        return this.effet[i][j];
    }

    public int[][] getEffet() {
        return effet;
    }

    public void setEffet(int[][] effet) {
        this.effet = effet;
    }

    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("Nom : ");
        sb.append(super.nom);
        sb.append("\n");
        for (int i=0; i<3; i++){
            for(int j = 0;j<4;j++) {
                sb.append(this.effet[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
    public int actionFarfadet(Joueur attaquant, Joueur cible, boolean chien, Saison saison){
        int saisonInt = convertirSaisonInt(saison);
        if(chien){
            int graineDispo = cible.getNbGraine();
            int effet = this.effet[FARFADETS][saisonInt]-cible.obtenirEffetChien(saisonInt);
            if(effet>0) {
                if (graineDispo >= effet) {
                    cible.modifierGraine(-effet);
                    attaquant.modifierGraine(effet);
                    return effet;

                } else {
                    cible.setNbGraine(0);
                    attaquant.modifierGraine(graineDispo);
                    return graineDispo;
                }
            }else{
                return 0;
            }
        }
        else{
            int graineDispo=cible.getNbGraine();
            int effet = this.effet[FARFADETS][saisonInt];
            if(graineDispo>=effet){
                cible.modifierGraine(-effet);
                attaquant.modifierGraine(effet);
                return effet;
            }
            else{
                cible.setNbGraine(0);
                attaquant.modifierGraine(graineDispo);
                return graineDispo;
            }
        }
    }

    public int actionEngrais(Joueur acteur,Saison saison){
        int valeur = acteur.getNbGraine();
        int saisonInt = convertirSaisonInt(saison);
        if((valeur-this.effet[ENGRAIS][saisonInt])<0){
            acteur.modifierGraine(-valeur);
            acteur.modifierMenhir(valeur);
            acteur.setNbGraine(0);
            return valeur;
        }
        else{
            acteur.modifierGraine(-this.effet[ENGRAIS][saisonInt]);
            acteur.modifierMenhir(this.effet[ENGRAIS][saisonInt]);
            return this.effet[ENGRAIS][saisonInt];
        }

    }

    public int actionGeant(Joueur acteur,Saison saison){
        int saisonInt = convertirSaisonInt(saison);
        acteur.modifierGraine(this.effet[GEANT][saisonInt]);
        return this.effet[GEANT][saisonInt];
    }
}
