package Partie;

import carte.*;
import joueur.Joueur;
import joueur.JoueurVirtuel;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.*;

public class VuePartie implements Observer{

    private JFrame fenetre = new JFrame();
    private Container contenu;

    private JPanel infosJoueursIA;
    private ArrayList<JButton> boutonsNomJoueurs;
    private ArrayList<JLabel> grainesJoueurs;
    private ArrayList<JLabel> menhirsJoueurs;
    private JPanel panelGraine, panelMenhir;

    private JPanel infoMain, panelNom, panelGeant, panelEngrais, panelFarfadet, panelAllie;
    private JLabel effetGeant, effetEngrais, effetFarfadet, effetAllie;
    private ArrayList<JButton> nomsCartes, géants, engrais, farfadets;
    private JButton boutonEffetAllie, boutonNomAllie;

    private JPanel infoPartie;
    private JTextArea deroulementPartie;

    private JPanel infoTour, grainesCartesPanel, panelGrainesCarteQ;
    private JLabel numManche, saison, grainesOuCartes;
    private JButton graines,cartes;

    private Border bordure;
    private Partie partie;

    private int texteAffiché = 0;
    private boolean premièreSaison = true;

    public JFrame getFenetre() {
        return fenetre;
    }

    public void remplirMain(){
        infoMain.removeAll();
        bordure = BorderFactory.createLineBorder(Color.black);
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.fill = GridBagConstraints.BOTH;
        infoMain.setBorder(bordure);
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 1;
        gridConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridConstraints.gridheight = GridBagConstraints.REMAINDER;
        gridConstraints.weightx = 80;
        gridConstraints.weighty = 30;
        infoMain.setLayout(new GridLayout(1,5));
        contenu.add(infoMain,gridConstraints);
        géants = new ArrayList<>();
        nomsCartes = new ArrayList<>();
        farfadets = new ArrayList<>();
        engrais = new ArrayList<>();
        //On definit la disposition au sein d'une carte
        for (int i = 0; i < partie.getJoueurHumain().getCarteEnMain().size(); i++){
            int numCarte = i;
            JPanel carte = new JPanel();
            carte.setBorder(bordure);
            //Chaque carte à une dimension de prédilection
            carte.setPreferredSize(new Dimension(40,40));
            panelNom = new JPanel();
            panelNom.setLayout(new FlowLayout());
            Carte carteBase = partie.getJoueurHumain().getCarteEnMain().get(i);
            if (carteBase instanceof CarteIngredient){
                //Chaque carte est une grille de 4 lignes et 1 colonne
                carte.setLayout(new GridLayout(4,1));
                CarteIngredient carteIngredient = (CarteIngredient)carteBase;
                JButton boutonCarte = new JButton(carteIngredient.getNom());
                if(partie instanceof PartieAvancee && premièreSaison){
                    boutonCarte.setEnabled(false);
                }else{
                    boutonCarte.setEnabled(true);
                }
                nomsCartes.add(boutonCarte);
                boutonCarte.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        deroulementPartie.append("Choisissez une action\n");
                        partie.getJoueurHumain().choisirCarte(numCarte);
                        géants.get(nomsCartes.indexOf(boutonCarte)).setEnabled(true);
                        engrais.get(nomsCartes.indexOf(boutonCarte)).setEnabled(true);
                        farfadets.get(nomsCartes.indexOf(boutonCarte)).setEnabled(true);
                        Iterator<JButton> itNomsCartes = nomsCartes.iterator();
                        while (itNomsCartes.hasNext()){
                            itNomsCartes.next().setEnabled(false);
                        }
                    }
                });
                panelNom.add(boutonCarte);
                carte.add(panelNom);
                panelGeant = new JPanel();
                panelGeant.setLayout(new FlowLayout());
                effetGeant = new JLabel();
                JButton boutonGeant = new JButton("Géant");
                géants.add(boutonGeant);
                boutonGeant.setEnabled(false);
                boutonGeant.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (texteAffiché >= 2){
                            deroulementPartie.setText(null);
                            texteAffiché = 0;
                        }
                        partie.getJoueurHumain().setActionEffectuée("GEANT");
                        deroulementPartie.append("Vous effectuez l'action Géant\n");
                        partie.getJoueurHumain().jouerCarte(partie);
                        actualiserJoueurs();
                        partie.jouerUneSaison(partie.getListeJoueurApresHumain());
                        actualiserJoueurs();
                        actualiserSaison();
                        texteAffiché++;
                        if(partie.finPartie()){
                            afficherGagnants();
                            Iterator<JButton> itBoutonsGeant = géants.iterator();
                            while (itBoutonsGeant.hasNext()){
                                itBoutonsGeant.next().setEnabled(false);
                            }
                            Iterator<JButton> itBoutonsEngrais = engrais.iterator();
                            while (itBoutonsEngrais.hasNext()){
                                itBoutonsEngrais.next().setEnabled(false);
                            }
                            Iterator<JButton> itBoutonsFarfadet = farfadets.iterator();
                            while (itBoutonsFarfadet.hasNext()){
                                itBoutonsFarfadet.next().setEnabled(false);
                            }
                        }
                        else if(partie instanceof PartieAvancee && partie.getSaison().toString().equals("FIN_ANNEE")){
                            nouvelleManche();
                        }
                        else{
                            partie.jouerUneSaison(partie.getListeJoueurAvantHumain());
                            actualiserJoueurs();
                            deroulementPartie.append("C'est à votre tour, Choisissez votre carte\n");
                        }
                    }
                });
                panelGeant.add(boutonGeant);
                effetGeant.setText(Integer.toString(carteIngredient.getForce(0,0)) + " " + Integer.toString(carteIngredient.getForce(0,1)) + " " + Integer.toString(carteIngredient.getForce(0,2)) + " " + Integer.toString(carteIngredient.getForce(0,3)));
                panelGeant.add(effetGeant);
                carte.add(panelGeant);
                panelEngrais = new JPanel();
                panelEngrais.setLayout(new FlowLayout());
                effetEngrais = new JLabel();
                JButton boutonEngrais = new JButton("Engrais");
                engrais.add(boutonEngrais);
                boutonEngrais.setEnabled(false);
                boutonEngrais.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (texteAffiché >= 2){
                            deroulementPartie.setText(null);
                            texteAffiché = 0;
                        }
                        partie.getJoueurHumain().setActionEffectuée("ENGRAIS");
                        deroulementPartie.append("Vous effectuez l'action Engrais\n");
                        partie.getJoueurHumain().jouerCarte(partie);
                        actualiserJoueurs();
                        partie.jouerUneSaison(partie.getListeJoueurApresHumain());
                        actualiserJoueurs();
                        actualiserSaison();
                        texteAffiché++;
                        if(partie.finPartie()){
                            afficherGagnants();
                            Iterator<JButton> itBoutonsGeant = géants.iterator();
                            while (itBoutonsGeant.hasNext()){
                                itBoutonsGeant.next().setEnabled(false);
                            }
                            Iterator<JButton> itBoutonsEngrais = engrais.iterator();
                            while (itBoutonsEngrais.hasNext()){
                                itBoutonsEngrais.next().setEnabled(false);
                            }
                            Iterator<JButton> itBoutonsFarfadet = farfadets.iterator();
                            while (itBoutonsFarfadet.hasNext()){
                                itBoutonsFarfadet.next().setEnabled(false);
                            }
                        }
                        else if(partie instanceof PartieAvancee && partie.getSaison().toString().equals("FIN_ANNEE")){
                            nouvelleManche();
                        }
                        else{
                            partie.jouerUneSaison(partie.getListeJoueurAvantHumain());
                            actualiserJoueurs();
                            deroulementPartie.append("C'est à votre tour, Choisissez votre carte\n");
                        }
                    }
                });
                panelEngrais.add(boutonEngrais);
                effetEngrais.setText(Integer.toString(carteIngredient.getForce(1,0)) + " " + Integer.toString(carteIngredient.getForce(1,1)) + " " + Integer.toString(carteIngredient.getForce(1,2)) + " " + Integer.toString(carteIngredient.getForce(1,3)));
                panelEngrais.add(effetEngrais);
                carte.add(panelEngrais);
                panelFarfadet = new JPanel();
                panelFarfadet.setLayout(new FlowLayout());
                effetFarfadet = new JLabel();
                JButton boutonFarfadet = new JButton("Farfadet");
                farfadets.add(boutonFarfadet);
                boutonFarfadet.setEnabled(false);
                boutonFarfadet.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        deroulementPartie.append("Choisissez une cible\n");
                        partie.getJoueurHumain().setActionEffectuée("FARFADET");
                        boutonGeant.setEnabled(false);
                        boutonEngrais.setEnabled(false);
                        boutonFarfadet.setEnabled(false);
                        Iterator<JButton> itNomsJoueurs = boutonsNomJoueurs.iterator();
                        while (itNomsJoueurs.hasNext()){
                            itNomsJoueurs.next().setEnabled(true);
                        }
                    }
                });
                panelFarfadet.add(boutonFarfadet);
                effetFarfadet.setText(Integer.toString(carteIngredient.getForce(2,0)) + " " + Integer.toString(carteIngredient.getForce(2,1)) + " " + Integer.toString(carteIngredient.getForce(2,2)) + " " + Integer.toString(carteIngredient.getForce(2,3)));
                panelFarfadet.add(effetFarfadet);
                carte.add(panelFarfadet);
                infoMain.add(carte);
                deroulementPartie.append("");
            }
            //La disposition est différente si c'est une carte alliée
            if (carteBase instanceof CarteAlliees){
                //Chaque carte est une grille de 2 lignes et 1 colonne
                carte.setLayout(new GridLayout(2,1));
                CarteAlliees carteAllie = (CarteAlliees)carteBase;
                boutonEffetAllie = new JButton();
                boutonNomAllie = new JButton(carteAllie.getNom());
                nomsCartes.add(boutonNomAllie);
                panelNom.add(boutonNomAllie);
                carte.add(panelNom);
                if(partie instanceof PartieAvancee && ((partie.getNumManche() == 1) || (partie.getNumManche()%4 == 0)) && premièreSaison){
                    boutonNomAllie.setEnabled(false);
                }else{
                    boutonNomAllie.setEnabled(true);
                }
                boutonNomAllie.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        deroulementPartie.append("Choisissez une action\n");
                        partie.getJoueurHumain().choisirCarte(numCarte);
                        Iterator<JButton> itNomsCartes = nomsCartes.iterator();
                        while (itNomsCartes.hasNext()){
                            itNomsCartes.next().setEnabled(false);
                        }
                        boutonNomAllie.setEnabled(false);
                        boutonEffetAllie.setEnabled(true);
                    }
                });
                panelAllie = new JPanel();
                panelAllie.setLayout(new FlowLayout());
                effetAllie = new JLabel();
                if(carteAllie.getNom().equals("Taupe geante"))
                {
                    boutonEffetAllie.setText("Taupe");
                    boutonEffetAllie.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            deroulementPartie.append("Choisissez une cible\n");
                            partie.getJoueurHumain().setActionEffectuée("TAUPE");
                            boutonEffetAllie.setEnabled(false);
                            Iterator<JButton> itNomsJoueurs = boutonsNomJoueurs.iterator();
                            while (itNomsJoueurs.hasNext()){
                                itNomsJoueurs.next().setEnabled(true);
                            }
                        }
                    });
                }
                else {
                    boutonEffetAllie.setText("Chien");
                    boutonEffetAllie.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            Iterator<JButton> itNomsCartes = nomsCartes.iterator();
                            while (itNomsCartes.hasNext()){
                                itNomsCartes.next().setEnabled(true);
                            }
                        }
                    });
                }
                boutonEffetAllie.setEnabled(false);
                panelAllie.add(boutonEffetAllie);
                carte.add(panelAllie);
                effetAllie.setText(Integer.toString(carteAllie.getForce(0)) + " " + Integer.toString(carteAllie.getForce(1)) + " " + Integer.toString(carteAllie.getForce(2)) + " " + Integer.toString(carteAllie.getForce(3)));
                panelAllie.add(effetAllie);
                infoMain.add(carte);
                deroulementPartie.append("");
            }
        }
        deroulementPartie.append("\n ");
    }

    public VuePartie(Partie partie) {

        this.partie = partie;
        fenetre.setTitle("VuePartie");
        this.fenetre.setSize(1000,700);
        fenetre.setLocationRelativeTo(null);
        fenetre.setResizable(false);
        contenu = fenetre.getContentPane();
        bordure = BorderFactory.createLineBorder(Color.black);

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
        grainesJoueurs = new ArrayList<>();
        menhirsJoueurs = new ArrayList<>();
        boutonsNomJoueurs = new ArrayList();
        for (int i = 0; i < partie.getListeJoueur().size(); i++){
            JPanel joueurIA = new JPanel();
            joueurIA.setBorder(bordure);
            joueurIA.setPreferredSize(new Dimension(20,20));
            joueurIA.setLayout(new GridLayout(3,1));
            panelNom = new JPanel();
            panelNom.setLayout(new FlowLayout());
            joueurIA.add(panelNom);
            JLabel nbMenhir = new JLabel();
            JLabel nbGraine = new JLabel();
            grainesJoueurs.add(nbGraine);
            menhirsJoueurs.add(nbMenhir);
            if(partie.getListeJoueur().get(i) instanceof JoueurVirtuel){
                int numJoueur = i;
                JButton nomJoueur = new JButton(partie.getListeJoueur().get(numJoueur).getNom());
                boutonsNomJoueurs.add(nomJoueur);
                nomJoueur.setEnabled(false);
                panelNom.add(nomJoueur);
                Font f = nomJoueur.getFont();
                nbMenhir.setFont(f.deriveFont(f.getStyle() ^ Font.BOLD));
                nbGraine.setFont(f.deriveFont(f.getStyle() ^ Font.BOLD));
                nomJoueur.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (texteAffiché >= 2){
                            deroulementPartie.setText(null);
                            texteAffiché = 0;
                        }
                        partie.getJoueurHumain().setCibleJoueur(partie.getListeJoueur().get(numJoueur));
                        partie.getJoueurHumain().jouerCarte(partie);
                        actualiserJoueurs();
                        deroulementPartie.append("Vous effectuez l'action Farfadet\n");
                        partie.jouerUneSaison(partie.getListeJoueurApresHumain());
                        actualiserJoueurs();
                        actualiserSaison();
                        texteAffiché++;
                        if(partie.finPartie()){
                            afficherGagnants();
                            Iterator<JButton> itBoutonsGeant = géants.iterator();
                            while (itBoutonsGeant.hasNext()){
                                itBoutonsGeant.next().setEnabled(false);
                            }
                            Iterator<JButton> itBoutonsEngrais = engrais.iterator();
                            while (itBoutonsEngrais.hasNext()){
                                itBoutonsEngrais.next().setEnabled(false);
                            }
                            Iterator<JButton> itBoutonsFarfadet = farfadets.iterator();
                            while (itBoutonsFarfadet.hasNext()){
                                itBoutonsFarfadet.next().setEnabled(false);
                            }
                        }
                        else if(partie instanceof PartieAvancee && partie.getSaison().toString().equals("FIN_ANNEE")){
                            nouvelleManche();
                        }
                        else{
                            partie.jouerUneSaison(partie.getListeJoueurAvantHumain());
                            actualiserJoueurs();
                            deroulementPartie.append("C'est à votre tour, Choisissez votre carte\n");
                        }
                    }
                });
            }
            else{
                JLabel nomJoueur = new JLabel();
                nomJoueur.setText(partie.getListeJoueur().get(i).getNom());
                panelNom.add(nomJoueur);
                Font f = nomJoueur.getFont();
                nbMenhir.setFont(f.deriveFont(f.getStyle() ^ Font.BOLD));
                nbGraine.setFont(f.deriveFont(f.getStyle() ^ Font.BOLD));
            }
            infosJoueursIA.add(joueurIA);
            panelGraine = new JPanel();
            panelGraine.setLayout(new FlowLayout());
            joueurIA.add(panelGraine);
            nbGraine.setText("Graines : " + Integer.toString(partie.getListeJoueur().get(i).getNbGraine()));
            panelGraine.add(nbGraine);
            panelMenhir = new JPanel();
            panelMenhir.setLayout(new FlowLayout());
            joueurIA.add(panelMenhir);
            nbMenhir.setText("Menhir : " + Integer.toString(partie.getListeJoueur().get(i).getNbMenhir()));
            panelMenhir.add(nbMenhir);
        }


        deroulementPartie = new JTextArea();

        infoMain = new JPanel();
        remplirMain();

        infoPartie = new JPanel();
        infoPartie.setBorder(bordure);
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        gridConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridConstraints.gridheight = 1;
        gridConstraints.weightx = 80;
        gridConstraints.weighty = 70;
        contenu.add(infoPartie,gridConstraints);
        infoPartie.setBackground(Color.WHITE);
        infoPartie.setPreferredSize(new Dimension(200,100));
        deroulementPartie.setEditable(false);
        infoPartie.add(deroulementPartie);

        infoTour = new JPanel();
        infoTour.setBorder(bordure);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gridConstraints.gridwidth = 1;
        gridConstraints.gridheight = GridBagConstraints.REMAINDER;
        gridConstraints.weightx = 20;
        gridConstraints.weighty = 30;
        infoTour.setPreferredSize(new Dimension(35,40));
        contenu.add(infoTour,gridConstraints);
        infoTour.setBackground(Color.green);
        infoTour.setLayout(new GridLayout(6,1));
        numManche = new JLabel();
        numManche.setText("Tour numéro " + partie.getNumManche());
        JPanel panelNumTour = new JPanel();
        panelNumTour.setLayout(new FlowLayout());
        panelNumTour.add(numManche);
        panelNumTour.setBackground(Color.GREEN);
        infoTour.add(panelNumTour);
        saison = new JLabel();
        saison.setText(partie.getSaison().toString());
        infoTour.add(saison);
        JPanel panelSaison = new JPanel();
        panelSaison.setLayout(new FlowLayout());
        panelSaison.add(saison);
        panelSaison.setBackground(Color.GREEN);
        infoTour.add(panelSaison);
        grainesOuCartes = new JLabel();
        grainesOuCartes.setText("Graines ou carte ?");
        panelGrainesCarteQ = new JPanel();
        panelGrainesCarteQ.setLayout(new FlowLayout());
        panelGrainesCarteQ.add(grainesOuCartes);
        panelGrainesCarteQ.setBackground(Color.GREEN);
        infoTour.add(panelGrainesCarteQ);
        grainesCartesPanel = new JPanel();
        if(!(partie instanceof PartieAvancee)){
            grainesCartesPanel.setVisible(false);
            panelGrainesCarteQ.setVisible(false);
        }
        graines = new JButton("Graines");
        graines.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                partie.getJoueurHumain().setNbGraine(partie.getJoueurHumain().getNbGraine() + 2);
                actualiserJoueurs();
                grainesCartesPanel.setVisible(false);
                panelGrainesCarteQ.setVisible(false);
                Iterator<JButton> itNomsCartes = nomsCartes.iterator();
                while (itNomsCartes.hasNext()){
                    itNomsCartes.next().setEnabled(true);
                }
                premièreSaison = false;
                Iterator<Joueur>  itJoueurs= partie.getListeJoueur().iterator();
                    while (itJoueurs.hasNext()){
                        Joueur joueur = itJoueurs.next();
                        if(joueur instanceof JoueurVirtuel){
                            ((JoueurVirtuel)joueur).graineOuAllie((PartieAvancee)partie, joueur);
                    }
                }
            }
        });
        cartes = new JButton("Carte");
        cartes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                partie.distribuer(partie.getJoueurHumain(), ((PartieAvancee)partie).getDeckAllie(), 1);
                remplirMain();
                actualiserJoueurs();
                grainesCartesPanel.setVisible(false);
                panelGrainesCarteQ.setVisible(false);
                Iterator<JButton> itNomsCartes = nomsCartes.iterator();
                while (itNomsCartes.hasNext()){
                    itNomsCartes.next().setEnabled(true);
                }
                boutonNomAllie.setEnabled(true);
                premièreSaison = false;
            }
        });
        grainesCartesPanel.setBackground(Color.GREEN);
        grainesCartesPanel.setLayout(new FlowLayout());
        grainesCartesPanel.add(graines);
        grainesCartesPanel.add(cartes);
        infoTour.add(grainesCartesPanel);
    }

    public void actualiserJoueurs(){
        for (int i = 0; i < partie.getListeJoueur().size(); i++){
            Iterator<JButton> itNomsJoueurs = boutonsNomJoueurs.iterator();
            while (itNomsJoueurs.hasNext()){
                itNomsJoueurs.next().setEnabled(false);
            }
            grainesJoueurs.get(i).setText("Graines : " + Integer.toString(partie.getListeJoueur().get(i).getNbGraine()));
            menhirsJoueurs.get(i).setText("Menhir : " + Integer.toString(partie.getListeJoueur().get(i).getNbMenhir()));
        }
    }

    public void afficherGagnants(){
        Iterator<Joueur> itScore = partie.getListeJoueur().iterator();
        while (itScore.hasNext()){
            Joueur joueurManche = itScore.next();
            joueurManche.modifierScore();
        }
        deroulementPartie.setText(null);
        texteAffiché = 0;
        ArrayList<Joueur> gagnants = partie.aGagne();
        Iterator<Joueur> itGagnant = gagnants.iterator();
        while (itGagnant.hasNext()){
            deroulementPartie.append(itGagnant.next() + " a gagné.\n");
        }
    }

    public void actualiserSaison(){
       saison.setText(partie.getSaison().toString());
        numManche.setText("Tour numéro " + partie.getNumManche());
    }

    public void update(Observable o, Object arg){
        deroulementPartie.append((String)arg);
        remplirMain();
    }

    public void nouvelleManche(){
            premièreSaison = true;
            partie.prochaineSaison();
            actualiserSaison();
            partie.setNumManche(partie.getNumManche()+1);
            ((PartieAvancee) partie).nettoyageManche();
            this.actualiserJoueurs();
            this.remplirMain();
            grainesCartesPanel.setVisible(true);
            panelGrainesCarteQ.setVisible(true);
            partie.jouerUneSaison(partie.getListeJoueurAvantHumain());

    }
}
