package org.team.project;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Main {
  public static void main(String[] args) {
    JFrame frame = new JFrame("Reinos Olvidados");

    GamePanel gp = new GamePanel();
    frame.setSize(new Dimension(gp.getPwidth(), gp.getPheight()));
    frame.getContentPane().add(gp, BorderLayout.CENTER);
    frame.setVisible(true);
    gp.setVisible(true);
  }
}