package org.team.project;
import java.awt.image.*;
import java.awt.*;
import javax.swing.ImageIcon;

public class EnergyWizard extends Entidad{
  protected BufferedImage energy;
  protected SpriteSheet ss;
  Image imagen;
  protected int ima;
  public EnergyWizard(int xwizard, int ywizard){
    x= xwizard+16;
    y= ywizard+16;
    alto=12;
    ancho=18;
    rect= new Rectangle();
    ImageIcon img= new ImageIcon (this.getClass().getResource("cohetex.png"));
    imagen = img.getImage();
    move();
  }
  public Image getImage(){
    return imagen;
  }
  public void move(){
    setX(5);
  }
}