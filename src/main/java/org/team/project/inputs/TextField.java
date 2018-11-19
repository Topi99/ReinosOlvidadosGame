package org.team.project.inputs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.team.project.GamePanel;

public class TextField extends Input {
  private Color color = Color.white;
  private StringBuilder value = new StringBuilder("");
  private BufferedImage bg;

  public TextField(int x, int y, int width, int height) {
    super(x, y, width, height);
    try {
      bg = ImageIO.read(this.getClass().getResource("../../../../input.png"));
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
    this.bg = GamePanel.resize(this.bg, this.width, this.height);
  }

  public void draw(Graphics g) {
    g.drawImage(bg, x, y, width, height, null);
    g.setColor(Color.white);
    g.drawString(this.value == null ? "" : this.value.toString(), this.x+10, this.y+g.getFont().getSize()/2+this.height/2);
  }

  /**
   * @param value the value to set
   */
  public void setValue(String value) {
    this.value.append(value);
  }

  /**
   * @return the value
   */
  public String getValue() {
    return value.toString();
  }

  public void keyPressed(int keyCode) {
    if(keyCode == 8) {
      try {
        this.value.deleteCharAt(this.value.length()-1);
      } catch(Exception e) {}
    } else {
      this.value.append((char)keyCode);
    }
  }
}