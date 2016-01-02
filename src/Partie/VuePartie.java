package Partie;

import carte.*;
import joueur.Joueur;
import joueur.JoueurVirtuel;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * Created by jglem_000 on 21/12/2015.
 */
public class VuePartie implements Observer{

    private JFrame fenetre = new JFrame();
    private Container contenu;

    private JPanel infosJoueursIA;
    private ArrayList<JLabel> grainesJoueurs;
    private ArrayList<JLabel> menhirsJoueurs;

    private JPanel infoMain;
    private JPanel panelNom, panelGeant, panelEngrais, panelFarfadet;
    private JLabel effetGeant;
    private JLabel effetEngrais;
    private JLabel effetFarfadet;
    private ArrayList<JButton> nomsCartes;
    private ArrayList<JButton> boutonsGeant;
    private ArrayList<JPanel> cartes;
    private JButton boutonEngrais;
    private JButton boutonFarfadet;

    private JPanel infoPartie;
    private JTextArea deroulementPartie;
    private JPanel panelGraine;
    private JPanel panelMenhir;

    private JPanel infoTour;
    private Box boxTour;
    private JLabel numManche;
    private JLabel saison;

    private Border bordure;
    private Partie partie;

    private int texteAffiché = 0;
    private int saisonAffichées;

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
        infoMain.setLayout(new GridLayout(1,6));
        contenu.add(infoMain,gridConstraints);
        //On definit la disposition au sein d'une carte
        for (int i = 0; i < partie.getJoueurHumain().getCarteEnMain().size(); i++){
            JPanel carte = new JPanel();
            cartes = new ArrayList<>();
            cartes.add(carte);
            int numCarte = i;
            carte.setBorder(bordure);
            //Chaque carte à une dimension de prédilection
            carte.setPreferredSize(new Dimension(40,40));
            //Chaque carte est une grille de 4 lignes et 1 colonne
            carte.setLayout(new GridLayout(4,1));
            panelNom = new JPanel();
            panelNom.setLayout(new FlowLayout());
            CarteIngredient carteTest = (CarteIngredient)partie.getJoueurHumain().getCarteEnMain().get(i);
            nomsCartes = new ArrayList<>();
            JButton boutonCarte = new JButton(carteTest.getNom());
            nomsCartes.add(boutonCarte);
            boutonsGeant = new ArrayList<>();
            boutonCarte.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    partie.getJoueurHumain().choisirCarte(numCarte);
                    deroulementPartie.append(Integer.toString(cartes.indexOf(carte)));
                }
            });
            panelNom.add(boutonCarte);
            carte.add(panelNom);
            panelGeant = new JPanel();
            panelGeant.setLayout(new FlowLayout());
            effetGeant = new JLabel();
            JButton boutonGeant = new JButton("Géant");
            boutonsGeant.add(boutonGeant);
            boutonGeant.setEnabled(false);
            boutonGeant.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (texteAffiché >= 2){
                        deroulementPartie.setText(null);
                        texteAffiché = 0;
                    }
                    partie.getJoueurHumain().setActionEffectuée("GEANT");
                    partie.getJoueurHumain().jouerCarte(partie);
                    actualiserJoueurs();
                    partie.jouerUneSaison();
                    actualiserJoueurs();
                    actualiserSaison();
                    texteAffiché++;
                    if(partie.finPartie()){
                        afficherGagnants();
                    }
                }
            });
            panelGeant.add(boutonGeant);
            effetGeant.setText(Integer.toString(carteTest.getForce(0,0)) + " " + Integer.toString(carteTest.getForce(0,1)) + " " + Integer.toString(carteTest.getForce(0,2)) + " " + Integer.toString(carteTest.getForce(0,3)));
            panelGeant.add(effetGeant);
            carte.add(panelGeant);
            panelEngrais = new JPanel();
            panelEngrais.setLayout(new FlowLayout());
            effetEngrais = new JLabel();
            boutonEngrais = new JButton("Engrais");
            boutonEngrais.setEnabled(false);
            boutonEngrais.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (texteAffiché >= 2){
                        deroulementPartie.setText(null);
                        texteAffiché = 0;
                    }
                    partie.getJoueurHumain().setActionEffectuée("ENGRAIS");
                    partie.getJoueurHumain().jouerCarte(partie);
                    actualiserJoueurs();
                    partie.jouerUneSaison();
                    actualiserJoueurs();
                    actualiserSaison();
                    texteAffiché++;
                    if(partie.finPartie()){
                        afficherGagnants();
                    }
                }
            });
            panelEngrais.add(boutonEngrais);
            effetEngrais.setText(Integer.toString(carteTest.getForce(1,0)) + " " + Integer.toString(carteTest.getForce(1,1)) + " " + Integer.toString(carteTest.getForce(1,2)) + " " + Integer.toString(carteTest.getForce(1,3)));
            panelEngrais.add(effetEngrais);
            carte.add(panelEngrais);
            panelFarfadet = new JPanel();
            panelFarfadet.setLayout(new FlowLayout());
            effetFarfadet = new JLabel();
            boutonFarfadet = new JButton("Farfadet");
            boutonFarfadet.setEnabled(false);
            boutonFarfadet.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    partie.getJoueurHumain().setActionEffectuée("FARFADET");
                }
            });
            panelFarfadet.add(boutonFarfadet);
            effetFarfadet.setText(Integer.toString(carteTest.getForce(2,0)) + " " + Integer.toString(carteTest.getForce(2,1)) + " " + Integer.toString(carteTest.getForce(2,2)) + " " + Integer.toString(carteTest.getForce(2,3)));
            panelFarfadet.add(effetFarfadet);
            carte.add(panelFarfadet);
            infoMain.add(carte);
            deroulementPartie.append("");
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
        grainesJoueurs = new ArrayList<JLabel>();
        menhirsJoueurs = new ArrayList<JLabel>();
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
                        actualiserJoueurs();partie.jouerUneSaison();
                        actualiserJoueurs();
                        actualiserSaison();
                        texteAffiché++;
                        if(partie.finPartie()){
                            afficherGagnants();
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
        infoPartie.setBackground(Color.YELLOW);
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

    public void actualiserJoueurs(){
        for (int i = 0; i < partie.getListeJoueur().size(); i++){
                grainesJoueurs.get(i).setText("Graines : " + Integer.toString(partie.getListeJoueur().get(i).getNbGraine()));
                menhirsJoueurs.get(i).setText("Menhir : " + Integer.toString(partie.getListeJoueur().get(i).getNbMenhir()));
        }
    }

    public void afficherGagnants(){
        Iterator<Joueur> itScore = partie.getListeJoueur().iterator();
        while (itScore.hasNext()){
            Joueur joueurManche = itScore.next();
            joueurManche.modifierScore();
            System.out.println(joueurManche.getNbPoint());
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
    }

    public void update(Observable o, Object arg){
        deroulementPartie.append((String)arg);
        remplirMain();
    }
}
