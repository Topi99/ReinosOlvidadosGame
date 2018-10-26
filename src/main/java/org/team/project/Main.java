package org.team.project;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Main {
  private static final int PWIDTH = 500;
  private static final int PHEIGHT = 400;
  
  public static void main(String[] args) {
    JFrame frame = new JFrame("Reinos Olvidados");

    GamePanel gp = new GamePanel();
    frame.setSize(new Dimension(PWIDTH, PHEIGHT));
    frame.getContentPane().add(gp, BorderLayout.CENTER);
    frame.setVisible(true);
    gp.setVisible(true);
  }
}