package org.team.project;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Characters {
    protected int x;
    protected int y;
    protected Image imagen;
    protected Rectangle rectangulo;
    protected int vida;
    protected Rectangle Barravida;
    protected Color c;
    /**
     * @return the barravida
     */
    public Rectangle getBarravida() {
        return Barravida;
    }
    public void drawVida(Graphics g){
        g.setColor(c);
        g.drawRect(80,80, getVida(),10);
        g.fillRect(80, 80, getVida(), 10);
    }
//hola soy pauchis :)
    public void setX(int n){
        x+=n;
    }
    public void setY(int n){
        y+=n;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    /**
     * @return the vida
     */
    public int getVida() {
        return vida;    
    }
    /**
     * @param vida the vida to set
     */
    public void setVida(int vida) {
        this.vida = vida;
    }
    public Rectangle getRectangle(){
        return rectangulo;
    }
    public Image getImage(){
        return imagen;
    }
     public void move(int k){
        if(k==KeyEvent.VK_UP){
			this.setY(-10);
			this.setX(0);
		}
		if(k==KeyEvent.VK_RIGHT){
			this.setY(0);
			this.setX(10);
		}
		if(k==KeyEvent.VK_LEFT){
			this.setY(0);
			this.setX(-10);
		}
		if(k==KeyEvent.VK_DOWN){
			this.setY(10);
			this.setX(0);
		}
    }
}