package org.team.project;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Characters {
    protected int x;
    protected int y;
    protected Image imagen;
    protected Rectangle rectangulo;

    public void setX(int n){
        x+=n;
    }
    public void setY(int n){
        y+=y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
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