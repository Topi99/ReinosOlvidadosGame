package org.team.project.inputs;

import java.awt.Color;
import java.awt.Graphics;

public class Button extends Input {
  private String message = "Submit";
  
  public Button(int x, int y, int width, int height) {
    super(x, y, width, height);
  }

  public Button(int x, int y, int width, int height, Color color) {
    super(x, y, width, height, color);
  }

  public Button(int x, int y, int width, int height, Color color, String message) {
    super(x, y, width, height, color);

    this.message = message;
  }

  public void draw(Graphics g) {
    g.setColor(this.color);
    g.fillRect(this.x, this.y, this.width, this.height);
    g.setColor(Color.black);
    g.drawString(this.message, this.x, this.y+g.getFont().getSize()/2+this.height/2);
  }
}