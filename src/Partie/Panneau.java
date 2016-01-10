package Partie;

import java.awt.Graphics;
import javax.swing.JPanel;

public class Panneau extends JPanel {
    public void paintComponent(Graphics g){
        g.drawString("Bonjour", 10, 20);
    }
}
