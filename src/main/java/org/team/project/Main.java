package org.team.project;
import java.awt.Dimension;
import java.net.URI;
import java.awt.BorderLayout;
import java.awt.Desktop;

import javax.swing.JFrame;

public class Main {
  public static void main(String[] args) {
    JFrame frame = new JFrame("Reinos Olvidados");

    Runtime rt = Runtime.getRuntime();
    try {
      Process pr = rt.exec("python -m SimpleHTTPServer");
      System.out.println(pr.toString());
      if (Desktop.isDesktopSupported()) {
        Desktop.getDesktop().browse(new URI("http://localhost:8000/web"));
      }
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
   

    GamePanel gp = new GamePanel();
    frame.setSize(new Dimension(gp.getPwidth(), gp.getPheight()));
    frame.getContentPane().add(gp, BorderLayout.CENTER);
    frame.setVisible(true);
    gp.setVisible(true);
    
  }
}