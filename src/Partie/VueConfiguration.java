package Partie;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.*;

/**
 * Classe qui gere la vue de la fenetre de configuration
 * @author Le Mercier - Gerard
 * @version 2.0
 */
public class VueConfiguration implements Observer{

    private JFrame fenetre = new JFrame();
    private JPanel panneauGeneral;
    private JPanel panelQuestionTypePartie, panelBoutonsTypePartie;
    private JLabel questionTypePartie;
    private JButton rapide;
    private JButton avancee;

    private JPanel panelQuestionNbJoueurs, panelReponseNbJoueurs;
    private JLabel questionNbJoueurs;
    private Integer[] nbJoueurs = {2, 3, 4, 5, 6};
    private JComboBox choixNbJoueurs;

    private JPanel panneauQuestionAge, panneauReponseAge;
    private JLabel questionAge;
    private JTextField choixAge;

    private JPanel panneauQuestionSexe, panneauReponseSexe;
    private JLabel questionSexe;
    private JButton homme, femme;

    private JPanel panneauLancement;
    private JButton lancerPartie;

    private Container conteneur = fenetre.getContentPane();
    private boolean estRempli;

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
