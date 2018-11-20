package org.team.project;

import java.awt.*;
import java.awt.image.*;
import java.util.Observable;
import java.util.Observer;


public class Entidad implements Observer{
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
		this.x += n;
	}
	public void setY(int n){
		this.y += n;
	}
	public Rectangle getRectangle() {
		return rect;
	  }
	@Override
	public void update(Observable o, Object o1) {
		this.person= (Characters) o;
		if(overlaps(person))
			{
		person.setVida(-10);
		System.out.println("esta chocando");
	}//el cirulo se cambia de color si ya se tocaron
		else
			this.c=Color.blue;
	}
		//método que verifica si ya se interceptaron los circulos, no es necesario usar intersects
		public boolean overlaps (Characters r) {
			return getRectangle().intersects(r.getRectangle());
		}
}