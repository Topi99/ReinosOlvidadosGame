package org.team.project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Circle extends Shape {
  private int radio;

  public Circle(int x, int y) {
    this.x = x;
    this.y = y;

    this.c = Color.green;
    this.radio = 50;
    this.rect = new Rectangle(x, y, radio, radio);
  }

  public void draw(Graphics g) {
    g.setColor(c);
    g.fillOval(x, y, radio, radio);
  }

  public void setX(int x) {
    this.x += x;
  }

  public void setY(int y) {
    this.y += y;
  }
}