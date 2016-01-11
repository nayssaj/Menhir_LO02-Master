package Partie;

public class Main {
    public static void main (String[] args){

        VueConfiguration vue;
        ConfigurePartie cp = new ConfigurePartie();
        vue = new VueConfiguration(cp);
        cp.addObserver(vue);
    }
}
