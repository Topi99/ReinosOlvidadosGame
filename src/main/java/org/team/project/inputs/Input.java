package org.team.project.inputs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Input {
  protected int x, y;
  protected int width, height;
  protected Rectangle rect;
  protected Color color;

  public Input(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.color = Color.LIGHT_GRAY;

    this.rect = new Rectangle(this.x, this.y, this.width, this.height);
  }

  public Input(int x, int y, int width, int height, Color color) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    
    this.rect = new Rectangle(this.x, this.y, this.width, this.height);
    this.color = color;
  }

  public void draw(Graphics g) {
    g.setColor(this.color);
    g.fillRect(this.x, this.y, this.width, this.height);
  }

  public void call() {
    
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