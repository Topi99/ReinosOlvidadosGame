package org.team.project;

import java.awt.*;
import java.awt.image.*;
import java.util.Observable;


public class Entidad extends Observable{
	protected int alto;
	protected int ancho;
	protected int x, y;
	protected Color c;
	protected Rectangle rect;
	protected Characters person;
	protected BufferedImage spriteSheet=null;

	public void draw(Graphics g){
		g.setColor(c);
		g.fillRect(x,y,ancho,alto);
	}
	public int getX() {
		return x;
	  }
	
	  public int getY() {
		return y;
	  }

	public void setX(int n){
		setChanged();
		notifyObservers();
		this.x += n;
	}
	public void setY(int n){
		setChanged();
		notifyObservers();
		this.y += n;
	}
	public Rectangle getRectangle() {
		return rect;
	  }
		//método que verifica si ya se interceptaron los circulos, no es necesario usar intersects
		public boolean overlaps (Characters r) {
			return getRectangle().intersects(r.getRectangle());
		}
}