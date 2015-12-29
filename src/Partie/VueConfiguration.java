package Partie;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 * Created by jglem_000 on 20/12/2015.
 */
public class VueConfiguration implements Observer{

    private JFrame fenetre = new JFrame();
    private Box panneauGeneral;
    private JPanel boutonsTypePartie;
    private JPanel panneauNbJoueurs;
    private JPanel panneauAge;
    private JPanel panneauSexe;
    private JLabel questionTypePartie;
    private JLabel questionNbJoueurs;
    private JLabel questionAge;
    private JLabel questionSexe;
    private JButton rapide;
    private JButton avancee;
    private JButton homme;
    private JButton femme;
    private JButton lancerPartie;
    private Container c = fenetre.getContentPane();
    private Integer[] nbJoueurs = {2, 3, 4, 5, 6};
    private JComboBox choixNbJoueurs;
    private JTextField choixAge;

    public VueConfiguration(ConfigurePartie config) {

        this.boutonsTypePartie = new JPanel();
        this.panneauAge = new JPanel();
        this.panneauSexe = new JPanel();
        this.panneauNbJoueurs = new JPanel();
        this.questionTypePartie = new JLabel();
        this.questionNbJoueurs = new JLabel();
        this.questionAge = new JLabel();
        this.questionSexe = new JLabel();
        this.fenetre.setVisible(true);
        this.fenetre.setSize(500,500);
        fenetre.setLocationRelativeTo(null);
        this.fenetre.setTitle("VueConfiguration");
        this.rapide = new JButton("Partie rapide");
        this.avancee = new JButton("Partie avancée");
        this.homme = new JButton("Homme");
        this.femme = new JButton("Femme");
        this.lancerPartie = new JButton("Lancer la partie");
        this.choixAge = new JTextField(20);

        panneauGeneral = Box.createVerticalBox();
        fenetre.setResizable(false);
        c.add(panneauGeneral);
        panneauGeneral.add(questionTypePartie);
        questionTypePartie.setText("Quel type de partie désirez vous jouer ?");
        panneauGeneral.add(Box.createVerticalStrut(20));
        panneauGeneral.add(boutonsTypePartie);
        boutonsTypePartie.add(rapide);
        boutonsTypePartie.add(avancee);
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
        panneauGeneral.add(questionNbJoueurs);
        questionNbJoueurs.setText("Combien de joueurs pour cette partie ?");
        panneauGeneral.add(Box.createVerticalStrut(20));
        panneauNbJoueurs.setSize(new Dimension(50,50));
        choixNbJoueurs = new JComboBox(nbJoueurs);
        panneauNbJoueurs.add(choixNbJoueurs);
        panneauGeneral.add(panneauNbJoueurs);
        choixNbJoueurs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int nbJoueurs = (int) choixNbJoueurs.getSelectedItem();
                config.setNbJoueurs(nbJoueurs);
            }
        });
        panneauGeneral.add(questionAge);
        questionAge.setText("Indiquez votre âge.");
        panneauGeneral.add(Box.createVerticalStrut(20));
        panneauGeneral.add(panneauAge);
        panneauAge.setSize(new Dimension(50,50));
        panneauAge.add(choixAge);
        panneauGeneral.add(questionSexe);
        questionSexe.setText("Êtes vous un homme ou une femme ?");
        panneauGeneral.add(Box.createVerticalStrut(20));
        panneauGeneral.add(panneauSexe);
        panneauSexe.add(homme);
        panneauSexe.add(femme);
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
        panneauGeneral.add(lancerPartie);
        lancerPartie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int age = Integer.parseInt(choixAge.getText());
                config.setAgeJoueur(age);
                Partie pt = config.configurerPartie();
                VuePartie vuePartie = new VuePartie(pt);
                vuePartie.getFenetre().setVisible(true);
                fenetre.dispose();
                pt.lancerPartie();
            }
        });
    }

    public void update(Observable o, Object arg){
        questionTypePartie.setText((String)arg);
    }
}
