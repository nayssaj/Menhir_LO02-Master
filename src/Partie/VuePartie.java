package Partie;

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
        infoMain.setLayout(new FlowLayout());
        contenu.add(infoMain,gridConstraints);
        infoMain.setBackground(Color.RED);

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
