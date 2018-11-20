package org.team.project;
import java.awt.image.*;
import java.io.IOException;
public class EnergyWizard extends Entidad{
  protected BufferedImage energy;
  protected SpriteSheet ss;
  protected int ima;
  public EnergyWizard(){
    alto=32;
    ancho=15;
    BufferedImageLoader loader= new BufferedImageLoader();
    try{
        spriteSheet= loader.loadImage("../../../energyball.png");
    }catch(IOException e){
      System.out.println("No se pudo carnal000");
        e.printStackTrace();
    }
    ss= new SpriteSheet(spriteSheet);
  }
  public BufferedImage getBufferImage(){
    energy=ss.grabImage(1,1,18,12);
    return energy;
}
}