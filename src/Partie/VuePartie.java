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
    private JPanel carte1 = new JPanel();
    private JPanel panelNom;
    private JPanel panelGeant;
    private JPanel panelEngrais;
    private JPanel panelFarfadet;
    private JLabel nomCarte1;
    private JLabel effetGeant;
    private JLabel effetEngrais;
    private JLabel effetFarfadet;
    private JPanel carte2 = new JPanel();
    private JLabel nomCarte3;
    private JPanel carte3 = new JPanel();
    private JLabel nomCarte4;
    private JPanel carte4 = new JPanel();
    private JLabel nomCarte5;
    private JPanel carte5 = new JPanel();
    private JLabel nomCarte2;
    private JPanel infoPartie;
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
        infosJoueursIA.setBackground(Color.BLUE);

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
        //Chaque carte à une dimension de prédilection
        carte1.setPreferredSize(new Dimension(40,40));
        infoMain.add(carte1);
        //Chaque carte est une grille de 4 lignes et 1 colonne
        carte1.setLayout(new GridLayout(4,1));
        panelNom = new JPanel();
        panelNom.setLayout(new FlowLayout());
        nomCarte1 = new JLabel();
        CarteIngredient carteTest = (CarteIngredient)partie.getJoueurHumain().getCarteEnMain().get(0);
        nomCarte1.setText(carteTest.getNom());
        panelNom.add(nomCarte1);
        carte1.add(panelNom);
        panelGeant = new JPanel();
        panelGeant.setLayout(new FlowLayout());
        effetGeant = new JLabel();
        effetGeant.setText(Integer.toString(carteTest.getForce(0,0)) + " " + Integer.toString(carteTest.getForce(0,1)) + " " + Integer.toString(carteTest.getForce(0,2)) + " " + Integer.toString(carteTest.getForce(0,3)));
        panelGeant.add(effetGeant);
        carte1.add(panelGeant);
        panelEngrais = new JPanel();
        panelEngrais.setLayout(new FlowLayout());
        effetEngrais = new JLabel();
        effetEngrais.setText(Integer.toString(carteTest.getForce(1,0)) + " " + Integer.toString(carteTest.getForce(1,1)) + " " + Integer.toString(carteTest.getForce(1,2)) + " " + Integer.toString(carteTest.getForce(1,3)));
        panelEngrais.add(effetEngrais);
        carte1.add(panelEngrais);
        panelFarfadet = new JPanel();
        panelFarfadet.setLayout(new FlowLayout());
        effetFarfadet = new JLabel();
        effetFarfadet.setText(Integer.toString(carteTest.getForce(2,0)) + " " + Integer.toString(carteTest.getForce(2,1)) + " " + Integer.toString(carteTest.getForce(2,2)) + " " + Integer.toString(carteTest.getForce(2,3)));
        panelFarfadet.add(effetFarfadet);
        carte1.add(panelFarfadet);
        carte2.setLayout(new GridLayout(4,1));
        carte2.setPreferredSize(new Dimension(40,40));
        nomCarte2 = new JLabel();
        nomCarte2.setText(partie.getJoueurHumain().getCarteEnMain().get(1).getNom());
        carte2.add(nomCarte2);
        infoMain.add(carte2);
        nomCarte3 = new JLabel();
        nomCarte3.setText(partie.getJoueurHumain().getCarteEnMain().get(2).getNom());
        carte3.add(nomCarte3);
        carte3.setLayout(new GridLayout(4,1));
        carte3.setPreferredSize(new Dimension(40,40));
        infoMain.add(carte3);
        nomCarte4 = new JLabel();
        nomCarte4.setText(partie.getJoueurHumain().getCarteEnMain().get(3).getNom());
        carte4.add(nomCarte4);
        carte4.setLayout(new GridLayout(4,1));
        carte4.setPreferredSize(new Dimension(40,40));
        infoMain.add(carte4);
        infoMain.add(carte5);

        infoPartie = new JPanel();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        gridConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridConstraints.gridheight = 1;
        gridConstraints.weightx = 80;
        gridConstraints.weighty = 70;
        contenu.add(infoPartie,gridConstraints);
        infoPartie.setBackground(Color.YELLOW);

        infoTour = new JPanel();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gridConstraints.gridwidth = 1;
        gridConstraints.gridheight = GridBagConstraints.REMAINDER;
        gridConstraints.weightx = 20;
        gridConstraints.weighty = 30;
        infoTour.setPreferredSize(new Dimension(10,40));
        contenu.add(infoTour,gridConstraints);
        infoTour.setBackground(Color.GREEN);
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
