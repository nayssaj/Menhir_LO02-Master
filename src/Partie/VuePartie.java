package Partie;

import carte.*;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 * Created by jglem_000 on 21/12/2015.
 */
public class VuePartie{

    private JFrame fenetre = new JFrame();

    private JPanel infosJoueursIA;


    private JPanel infoMain;
    private JPanel panelNom;
    private JPanel panelGeant;
    private JPanel panelEngrais;
    private JPanel panelFarfadet;
    private JLabel nomCarte;
    private JLabel effetGeant;
    private JLabel effetEngrais;
    private JLabel effetFarfadet;

    private JPanel infoPartie;
    private JPanel panelGraine;
    private JPanel panelMenhir;
    private JLabel nomJoueur;
    private JLabel nbGraine;
    private JLabel nbMenhir;

    private JPanel infoTour;
    private Box boxTour;
    private JLabel numManche;
    private JLabel saison;

    public JFrame getFenetre() {
        return fenetre;
    }

    public VuePartie(Partie partie) {
        fenetre.setTitle("VuePartie");
        this.fenetre.setSize(1000,700);
        fenetre.setLocationRelativeTo(null);
        fenetre.setResizable(false);
        Container contenu = fenetre.getContentPane();

        infosJoueursIA = new JPanel();
        contenu.setLayout(new GridBagLayout());
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.gridwidth = 1;
        gridConstraints.gridheight = 1;
        gridConstraints.weightx = 20;
        gridConstraints.weighty = 70;
        gridConstraints.fill = GridBagConstraints.BOTH;
        contenu.add(infosJoueursIA,gridConstraints);
        infosJoueursIA.setLayout(new GridLayout(5,1));
        for (int i = 0; i < partie.getListeJoueur().size(); i++){
            JPanel joueurIA = new JPanel();
            joueurIA.setPreferredSize(new Dimension(20,20));
            joueurIA.setLayout(new GridLayout(3,1));
            panelNom = new JPanel();
            panelNom.setLayout(new FlowLayout());
            joueurIA.add(panelNom);
            nomJoueur = new JLabel();
            Font f = nomJoueur.getFont();
            nomJoueur.setText(partie.getListeJoueur().get(i).getNom());
            panelNom.add(nomJoueur);
            infosJoueursIA.add(joueurIA);
            panelGraine = new JPanel();
            panelGraine.setLayout(new FlowLayout());
            joueurIA.add(panelGraine);
            nbGraine = new JLabel();
            nbGraine.setText("Graines : " + Integer.toString(partie.getListeJoueur().get(i).getNbGraine()));
            nbGraine.setFont(f.deriveFont(f.getStyle() ^ Font.BOLD));
            panelGraine.add(nbGraine);
            panelMenhir = new JPanel();
            panelMenhir.setLayout(new FlowLayout());
            joueurIA.add(panelMenhir);
            nbMenhir = new JLabel();
            nbMenhir.setText("Menhir : " + Integer.toString(partie.getListeJoueur().get(i).getNbMenhir()));
            nbMenhir.setFont(f.deriveFont(f.getStyle() ^ Font.BOLD));
            panelMenhir.add(nbMenhir);
        }

        infoMain = new JPanel();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 1;
        gridConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridConstraints.gridheight = GridBagConstraints.REMAINDER;
        gridConstraints.weightx = 80;
        gridConstraints.weighty = 30;
        infoMain.setLayout(new GridLayout(1,5));
        contenu.add(infoMain,gridConstraints);
        //On definit la disposition au sein d'une carte
        for (int i = 0; i < partie.getJoueurHumain().getCarteEnMain().size(); i++){
            JPanel carte = new JPanel();
            //Chaque carte à une dimension de prédilection
            carte.setPreferredSize(new Dimension(40,40));
            //Chaque carte est une grille de 4 lignes et 1 colonne
            carte.setLayout(new GridLayout(4,1));
            panelNom = new JPanel();
            panelNom.setLayout(new FlowLayout());
            nomCarte = new JLabel();
            CarteIngredient carteTest = (CarteIngredient)partie.getJoueurHumain().getCarteEnMain().get(i);
            nomCarte.setText(carteTest.getNom());
            panelNom.add(nomCarte);
            carte.add(panelNom);
            panelGeant = new JPanel();
            panelGeant.setLayout(new FlowLayout());
            effetGeant = new JLabel();
            effetGeant.setText("Géant :    " + Integer.toString(carteTest.getForce(0,0)) + " " + Integer.toString(carteTest.getForce(0,1)) + " " + Integer.toString(carteTest.getForce(0,2)) + " " + Integer.toString(carteTest.getForce(0,3)));
            panelGeant.add(effetGeant);
            carte.add(panelGeant);
            panelEngrais = new JPanel();
            panelEngrais.setLayout(new FlowLayout());
            effetEngrais = new JLabel();
            effetEngrais.setText("Engrais :    " + Integer.toString(carteTest.getForce(1,0)) + " " + Integer.toString(carteTest.getForce(1,1)) + " " + Integer.toString(carteTest.getForce(1,2)) + " " + Integer.toString(carteTest.getForce(1,3)));
            panelEngrais.add(effetEngrais);
            carte.add(panelEngrais);
            panelFarfadet = new JPanel();
            panelFarfadet.setLayout(new FlowLayout());
            effetFarfadet = new JLabel();
            effetFarfadet.setText("Farfadet :    " + Integer.toString(carteTest.getForce(2,0)) + " " + Integer.toString(carteTest.getForce(2,1)) + " " + Integer.toString(carteTest.getForce(2,2)) + " " + Integer.toString(carteTest.getForce(2,3)));
            panelFarfadet.add(effetFarfadet);
            carte.add(panelFarfadet);
            infoMain.add(carte);
        }

        infoPartie = new JPanel();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        gridConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridConstraints.gridheight = 1;
        gridConstraints.weightx = 80;
        gridConstraints.weighty = 70;
        contenu.add(infoPartie,gridConstraints);
        infoPartie.setBackground(Color.YELLOW);
        JLabel test = new JLabel();
        test.setText("Infos générales sur le déroulement de la partie.");
        infoPartie.add(test);

        infoTour = new JPanel();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gridConstraints.gridwidth = 1;
        gridConstraints.gridheight = GridBagConstraints.REMAINDER;
        gridConstraints.weightx = 20;
        gridConstraints.weighty = 30;
        infoTour.setPreferredSize(new Dimension(35,40));
        contenu.add(infoTour,gridConstraints);
        infoTour.setBackground(Color.orange);
        boxTour = Box.createVerticalBox();
        infoTour.add(boxTour);
        numManche = new JLabel();
        numManche.setText("Tour numéro " + partie.getNumManche());
        boxTour.add(numManche);
        saison = new JLabel();
        saison.setText("Saison en cours : " + partie.getSaison());
        boxTour.add(saison);
    }
}
