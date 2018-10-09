package org.team.project;

import java.awt.Color;
import java.awt.Graphics;
// import java.awt.Graphics2D;
// import java.awt.event.KeyEvent;
import java.awt.Rectangle;

public abstract class Shape {
  protected int x, y;
  protected Color c;
  protected Rectangle rect;

  public abstract void draw(Graphics g);

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public boolean collision() {
    Rectangle rec = this.getRectangle();

    System.out.println(rec.getX() + rec.getY());

    return rec.contains(rec.getX(), rec.getY());
  }

  public Rectangle getRectangle() {
    return rect;
  }
}