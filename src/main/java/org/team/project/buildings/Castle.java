package org.team.project.buildings;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.team.project.GamePanel;
import org.team.project.inputs.Button;

public class Castle extends Button {
  private BufferedImage bg;

  public Castle(int x, int y, int width, int height, GamePanel panel) {
    super(x, y, width, height);
    
    try {
      bg = ImageIO.read(this.getClass().getResource("../../../../castle.png"));
    } catch(Exception e) {}

    this.x = panel.getPwidth()/2 - (bg.getWidth()/2)*3;
    this.y = panel.getPheight()/2 - bg.getHeight()/2;
    this.width = bg.getWidth();
    this.height = bg.getHeight();

    this.rect = new Rectangle(this.x, this.y, this.width, this.height);
  }

  public void call() {
    this.active = true;
    System.out.println("Castle");
  }
  
  public void draw(Graphics g) {
    g.drawImage(bg, x, y, width, height, null);
  }
}