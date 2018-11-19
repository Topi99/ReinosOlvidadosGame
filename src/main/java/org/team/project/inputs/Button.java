package org.team.project.inputs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.team.project.GamePanel;

public class Button extends Input {
  private String message = "Submit";
  private BufferedImage bg;
  
  public Button(int x, int y, int width, int height) {
    super(x, y, width, height);
  }

  public Button(int x, int y, int width, int height, Color color) {
    super(x, y, width, height, color);
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
    g.drawString(this.message, this.x+20, this.y+g.getFont().getSize()/2+this.height/2-2);
  }
}