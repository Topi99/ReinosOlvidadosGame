package org.team.project.buildings;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.team.project.GamePanel;
import org.team.project.inputs.Button;

public class Village extends Button {
  private BufferedImage bg;

  public Village(int x, int y, int width, int height, GamePanel panel) {
    super(x, y, width, height);
    
    try {
      bg = ImageIO.read(this.getClass().getResource("../../../../village.png"));
    } catch(Exception e) {}
    
    bg = GamePanel.resize(bg, panel.getPwidth()/2, panel.getPheight()/2);

    this.x = panel.getPwidth() - bg.getWidth() - 15;
    this.y = 0;
    this.width = bg.getWidth();
    this.height = bg.getHeight();

    this.rect = new Rectangle(this.x, this.y, this.width, this.height);
    
  }

  public void call() {
    this.active = true;
    System.out.println("Village");
  }
  
  public void draw(Graphics g) {
    g.drawImage(bg, x, y, width, height, null);
  }
}