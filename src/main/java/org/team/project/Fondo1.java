package org.team.project;
import javax.swing.ImageIcon;
public class Fondo1 extends Fondo{
  public Fondo1(){
    x=0;
    y=0;
    ImageIcon img= new ImageIcon (getClass().getResource("map (1).png"));
    imagen = img.getImage();
  }
  
}