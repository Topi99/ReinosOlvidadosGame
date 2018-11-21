package org.team.project.inputs;

import java.awt.Color;
import java.awt.Graphics;

import org.team.project.PanelFig;

public class Resources {
  private static Resources instance = null;
  private int wood = 0;
  private int stone = 0;
  private int iron = 0;
  private PanelFig statusPanel;

  public Resources() {
    statusPanel = new PanelFig(0,0,300, 100) {
      @Override
      public void draw(Graphics g) {
        g.drawImage(this.getBg(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
        g.setColor(Color.white);
        g.drawString("Wood: " + wood, 15, 30);
        g.drawString("Stone: " + stone, 15, 55);
        g.drawString("Iron: " + iron, 15, 80);
      }
    };
  }
  
  /**
   * @return the instance
   */
  public static Resources getInstance() {
    if(instance == null) {
      instance = new Resources();
    }
    
    return instance;
  }
  
  /**
   * @return the iron
   */
  public int getIron() {
    return iron;
  }
  
  /**
   * @return the stone
   */
  public int getStone() {
    return stone;
  }
  
  /**
   * @return the wood
   */
  public int getWood() {
    return wood;
  }
  
  /**
   * @param iron the iron to set
   */
  public void setIron(int iron) {
    this.iron = iron;
  }
  
  /**
   * @param stone the stone to set
   */
  public void setStone(int stone) {
    this.stone = stone;
  }
  
  /**
   * @param wood the wood to set
   */
  public void setWood(int wood) {
    this.wood = wood;
  }

  /**
   * @param g the graphics where to draw
   */
  public void draw(Graphics g) {
    statusPanel.draw(g);
  }
}