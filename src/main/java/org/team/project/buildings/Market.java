package org.team.project.buildings;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.team.project.GamePanel;
import org.team.project.inputs.Button;

public class Market extends Button {
  private BufferedImage bg;

  public Market(int x, int y, int width, int height, GamePanel panel) {
    super(x, y, width, height);
    
    try {
      bg = ImageIO.read(this.getClass().getResource("../../../../market.png"));
    } catch(Exception e) {}
    
    bg = GamePanel.resize(bg, panel.getPwidth()/3+50, panel.getPheight()/3+30);

    this.x = panel.getPwidth()/2 - bg.getWidth()/5;
    this.y = panel.getPheight() - bg.getHeight()-50;
    this.width = bg.getWidth();
    this.height = bg.getHeight();

    this.rect = new Rectangle(this.x, this.y, this.width, this.height);
    
  }

  public void call() {
    this.active = true;
    System.out.println("Market");
  }
  
  public void draw(Graphics g) {
    g.drawImage(bg, x, y, width, height, null);
  }
}