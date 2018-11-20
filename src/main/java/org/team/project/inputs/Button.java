package org.team.project.inputs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.team.project.GamePanel;

public class Button extends Input {
  private String message = "";
  private BufferedImage bg;
  private String data = "";

  public Button(int x, int y, int width, int height) {
    super(x, y, width, height);
  }
  
  public Button(int x, int y, int width, int height, String src) {
    super(x, y, width, height);
    try {
      bg = ImageIO.read(this.getClass().getResource(src));
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
    
    this.bg = GamePanel.resize(this.bg, this.width, this.height);
  }

  public Button(int x, int y, int width, int height, String message, String data) {
    super(x, y, width, height);
    try {
      bg = ImageIO.read(this.getClass().getResource("../../../../button.png"));
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
    
    this.message = message;
    this.data = data;
    this.bg = GamePanel.resize(this.bg, this.width, this.height);
  }

  public Button(int x, int y, int width, int height, Color color) {
    super(x, y, width, height, color);
    try {
      bg = ImageIO.read(this.getClass().getResource("../../../../button.png"));
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }

    this.bg = GamePanel.resize(this.bg, this.width, this.height);
  }

  public Button(int x, int y, int width, int height, Color color, String message) {
    super(x, y, width, height, color);
    try {
      bg = ImageIO.read(this.getClass().getResource("../../../../button.png"));
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }

    this.message = message;
    this.bg = GamePanel.resize(this.bg, this.width, this.height);
  }
  

  public void draw(Graphics g) {
    g.drawImage(bg, x, y,width, height, null);
    g.setColor(Color.black);
    g.drawString(this.message, this.x+20+this.strPaddLeft, this.y+g.getFont().getSize()/2+this.height/2-2);
  }

  /**
   * @param data the data to set
   */
  public void setData(String data) {
    this.data = data;
  }

  /**
   * @return the data
   */
  public String getData() {
    return data;
  }
}