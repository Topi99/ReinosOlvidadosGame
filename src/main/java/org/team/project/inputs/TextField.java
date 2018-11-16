package org.team.project.inputs;

import java.awt.Color;
import java.awt.Graphics;

public class TextField extends Input {
  private Color color = Color.white;
  private StringBuilder value = new StringBuilder("");

  public TextField(int x, int y, int width, int height) {
    super(x, y, width, height);
  }

  public void draw(Graphics g) {
    g.setColor(this.color);
    g.fillRect(this.x, this.y, this.width, this.height);
    g.setColor(Color.black);
    g.drawString(this.value == null ? "" : this.value.toString(), this.x, this.y+g.getFont().getSize()/2+this.height/2);
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