package Partie;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.*;

/**
 * Classe qui gere une vue pour la configuration de la partie
 * @author Le Mercier - Gerard
 * @version 2.0
 */
public class VueConfiguration implements Observer{

    //Fenêtre principale de la vue de la configuration
    private JFrame fenetre = new JFrame();
    //Panneau Général contenant tous les élements de la vue
    private JPanel panneauGeneral;
    //Panneau conteant les élements permettant au joueur de préciser le type de partie
    private JPanel panelQuestionTypePartie, panelBoutonsTypePartie;
    //Label indiquant la question sur le type de partie
    private JLabel questionTypePartie;
    //Bouton permettant de choisirs les options rapide ou avancée
    private JButton rapide, avancee;

    //Panneau conteant les élements permettant au joueur de préciser le nombre de joueurs dans la partie
    private JPanel panelQuestionNbJoueurs, panelReponseNbJoueurs;
    //Label indiquant une question sur le nombre de joueur
    private JLabel questionNbJoueurs;
    //Combobox permettant de selectionner le nombre de joueurs
    private Integer[] nbJoueurs = {2, 3, 4, 5, 6};
    private JComboBox choixNbJoueurs;

    //Panel contenant les élements permettant au joueur de préciser son age
    private JPanel panneauQuestionAge, panneauReponseAge;
    //Label indiquant la question sur l'age du joueur
    private JLabel questionAge;
    //zone de saisie de l'age du joueur
    private JTextField choixAge;

    //Panel contenant les élements permettant au joueur de préciser son sexe
    private JPanel panneauQuestionSexe, panneauReponseSexe;
    //Label indiquant la quetsion sur le sexe du joueur
    private JLabel questionSexe;
    //Bouton de selction du sexe du joueur
    private JButton homme, femme;

    //Panel contenant les élements permettant au joueur de lancer la partie
    private JPanel panneauLancement;
    //Bouton de lancement de la partie
    private JButton lancerPartie;

    //Conteneur de la fenêtre
    private Container conteneur = fenetre.getContentPane();
    //Permet de vérifier que tous les élements on été remplis avant de lancer la partie
    private boolean estRempli;

    /**
     * Constructeur de l'objet vueConfiguration qui viens cérer les différents élements de l'interface graphique
     * @param config Objet qui se charge de créer la partie en fonction des paramètres du joueur
     * @author Le Mercier - Gerard
     * @version 2.0
     */
    public VueConfiguration(ConfigurePartie config) {

        this.panelQuestionTypePartie = new JPanel();
        this.questionTypePartie = new JLabel();
        this.panelBoutonsTypePartie = new JPanel();
        this.rapide = new JButton("Partie rapide");
        this.avancee = new JButton("Partie avancée");
        this.panelQuestionNbJoueurs = new JPanel();
        this.questionNbJoueurs = new JLabel();
        this.panelReponseNbJoueurs = new JPanel();
        this.choixNbJoueurs = new JComboBox(nbJoueurs);
        this.panneauQuestionAge = new JPanel();
        this.panneauReponseAge = new JPanel();
        this.questionAge = new JLabel();
        this.choixAge = new JTextField(20);
        this.panneauQuestionSexe = new JPanel();
        this.panneauReponseSexe = new JPanel();
        this.questionSexe = new JLabel();
        this.homme = new JButton("Homme");
        this.femme = new JButton("Femme");
        this.fenetre.setVisible(true);
        this.fenetre.setSize(600,600);
        this.fenetre.setLocationRelativeTo(null);
        this.fenetre.setTitle("VueConfiguration");
        this.panneauLancement = new JPanel();
        this.lancerPartie = new JButton("Lancer la partie");

        panneauGeneral = new JPanel();
        fenetre.setResizable(false);
        panneauGeneral.setLayout(new GridLayout(9,1));
        conteneur.add(panneauGeneral);

        panelQuestionTypePartie.setLayout(new FlowLayout());
        panelBoutonsTypePartie.setLayout(new FlowLayout());
        questionTypePartie.setText("Quel type de partie désirez vous jouer ?");
        panelQuestionTypePartie.add(questionTypePartie);
        panneauGeneral.add(panelQuestionTypePartie);
        panelBoutonsTypePartie.add(rapide);
        panelBoutonsTypePartie.add(avancee);
        panneauGeneral.add(panelBoutonsTypePartie);
        /**
         * ActionListener qui après l'utilisation d'un bouton par le joueur de préciser le type de partie
         * @see ConfigurePartie
         * @author Le Mercier - Gerard
         * @version 1.0
         */
        rapide.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                config.setTypePartie("RAPIDE");
            }
        });
        avancee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                config.setTypePartie("AVANCEE");
            }
        });

        panelQuestionNbJoueurs.setLayout(new FlowLayout());
        panelReponseNbJoueurs.setLayout(new FlowLayout());
        questionNbJoueurs.setText("Combien de joueurs pour cette partie ?");
        panelQuestionNbJoueurs.add(questionNbJoueurs);
        panneauGeneral.add(panelQuestionNbJoueurs);
        panelReponseNbJoueurs.add(choixNbJoueurs);
        panneauGeneral.add(panelReponseNbJoueurs);
        /**
         * ActionListener qui après l'utilisation d'un bouton par le joueur de préciser le nombre de joueurs
         * @see ConfigurePartie
         * @author Le Mercier - Gerard
         * @version 1.0
         */
        choixNbJoueurs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int nbJoueurs = (int) choixNbJoueurs.getSelectedItem();
                config.setNbJoueurs(nbJoueurs);
            }
        });

        panneauQuestionAge.setLayout(new FlowLayout());
        panneauReponseAge.setLayout(new FlowLayout());
        questionAge.setText("Indiquez votre âge.");
        panneauQuestionAge.add(questionAge);
        panneauGeneral.add(panneauQuestionAge);
        panneauReponseAge.add(choixAge);
        estRempli = false;
        choixAge.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                estRempli = true;
            }
            public void removeUpdate(DocumentEvent e) {
                estRempli = false;
            }
            public void insertUpdate(DocumentEvent e) {
                estRempli = true;
            }
        });
        panneauGeneral.add(panneauReponseAge);

        panneauQuestionSexe.setLayout(new FlowLayout());
        panneauReponseSexe.setLayout(new FlowLayout());
        questionSexe.setText("Êtes vous un homme ou une femme ?");
        panneauQuestionSexe.add(questionSexe);
        panneauGeneral.add(panneauQuestionSexe);
        panneauReponseSexe.add(homme);
        panneauReponseSexe.add(femme);
        panneauGeneral.add(panneauReponseSexe);
        /**
         * ActionListener qui après l'utilisation d'un bouton par le joueur de préciser son sexe
         * @see ConfigurePartie
         * @author Le Mercier - Gerard
         * @version 1.0
         */
        homme.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                config.setSexeJoueur("H");
            }
        });
        femme.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                config.setSexeJoueur("F");
            }
        });

        panneauLancement.add(lancerPartie);
        panneauGeneral.add(panneauLancement);
        /**
         * ActionListener qui après l'utilisation d'un bouton par le joueur de lancer la partie si et seulement si l'ensemble des champs a été rempli
         * @see ConfigurePartie
         * @author Le Mercier - Gerard
         * @version 1.0
         */
        lancerPartie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if((config.getTypePartie() != null) && (config.getSexeJoueur() != null) && estRempli){
                    int age = Integer.parseInt(choixAge.getText());
                    config.setAgeJoueur(age);
                    Partie pt = config.configurerPartie();
                    pt.initaliserPartie();
                    VuePartie vuePartie = new VuePartie(pt);
                    vuePartie.getFenetre().setVisible(true);
                    pt.addObserver(vuePartie);
                    fenetre.dispose();
                    pt.lancerPartie();
                    vuePartie.actualiserSaison();
                    vuePartie.actualiserJoueurs();
                }
            }
        });
    }

    public void update(Observable o, Object arg){

    }
}
