package Partie;

import jdk.nashorn.internal.runtime.regexp.joni.Config;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 * Created by jglem_000 on 20/12/2015.
 */
public class VueConfiguration implements Observer{

    private JFrame fenetre = new JFrame();
    private JPanel panneau;
    private JLabel questionTypePartie;
    private JLabel questionNbJoueurs;
    private JButton rapide;
    private JButton avancee;
    private JButton lancerPartie;
    private Container c = fenetre.getContentPane();
    private Integer[] nbJoueurs = {1, 2, 3, 4, 5, 6};
    private JComboBox choixNbJoueurs;

    public VueConfiguration(ConfigurePartie config) {
        this.panneau = new JPanel();
        this.questionTypePartie = new JLabel();
        this.questionNbJoueurs = new JLabel();
        this.fenetre.setVisible(true);
        this.fenetre.setSize(300,150);
        this.fenetre.setTitle("VueConfiguration");
        this.rapide = new JButton("Partie rapide");
        this.avancee = new JButton("Partie avancée");
        this.lancerPartie = new JButton("Lancer la partie");
        c.setLayout(new FlowLayout());
        c.add(questionTypePartie);
        questionTypePartie.setText("Quel type de partie désirez vous jouer ?");
        c.add(rapide);
        c.add(avancee);
        rapide.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                config.setPartieRapide();
            }
        });
        avancee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                config.setPartieAvancee();
            }
        });
        c.add(questionNbJoueurs);
        questionNbJoueurs.setText("Quel type de partie désirez vous jouer ?");
        choixNbJoueurs = new JComboBox(nbJoueurs);
        c.add(choixNbJoueurs);
        choixNbJoueurs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int nbJoueurs = (int) choixNbJoueurs.getSelectedItem();
                config.setNbJoueurs(nbJoueurs);
            }
        });
        c.add(lancerPartie);
        lancerPartie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Partie pt = config.configurerPartie();
                pt.lancerPartie();
            }
        });
    }

    public void update(Observable o, Object arg){
        questionTypePartie.setText((String)arg);
    }
}
