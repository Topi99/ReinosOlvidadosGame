package org.team.project;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class PanelFig implements Panel {
  private BufferedImage bg;
  private int x, y, width, height;
  
  public PanelFig(int x, int y, int width, int height) {
    try {
      bg = ImageIO.read(this.getClass().getResource("../../../../panel.png"));
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }

    this.x = x;
    this.x = y;
    this.width = width;
    this.height = height;
    
    this.bg = GamePanel.resize(this.bg, this.width, this.height);
  }
  
  public void draw(Graphics g) {
    g.drawImage(bg, x, y, width, height, null);
  }

  /**
   * @return the bg
   */
  public BufferedImage getBg() {
    return bg;
  }

  /**
   * @return the x
   */
  public int getX() {
    return x;
  }

  /**
   * @return the y
   */
  public int getY() {
    return y;
  }

  /**
   * @return the width
   */
  public int getWidth() {
    return width;
  }

  /**
   * @return the height
   */
  public int getHeight() {
    return height;
  }
}