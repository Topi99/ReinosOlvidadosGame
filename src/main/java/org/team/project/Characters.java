package org.team.project;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Observable;

public class Characters extends Observable {
    protected int x;
    protected int y;
    protected Image imagen;
    protected Rectangle rectangulo;
    protected int vida;
    protected Rectangle Barravida;
    protected Color c;
    protected int pase;
    protected BufferedImage spriteSheet=null;
    /**
     * @return the barravida
     */
    public Rectangle getBarravida() {
        return Barravida;
    }
    public void drawVida(Graphics g){
        g.setColor(c);
        g.drawRect(getX()-10,getY()-5, getVida(),10);
        g.fillRect(getX()-10, getY()-5, getVida(), 10);
    }
//hola soy pauchis :)
    public void setX(int n){
        x+=n;
        setChanged(); //ya se cambió
        notifyObservers();//notifica a los observers que se hayan agregado
    }
    public void setY(int n){
        y+=n;
        setChanged(); //ya se cambió
        notifyObservers();//notifica a los observers que se hayan agregado
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
    public void attack(){

    }
    public void actions(int k){
        if(k==KeyEvent.VK_UP){
            		this.setY(-pase);
            		this.setX(0);
            	}
            	if(k==KeyEvent.VK_RIGHT){
            		this.setY(0);
            		this.setX(pase);
            	}
            	if(k==KeyEvent.VK_LEFT){
            		this.setY(0);
            		this.setX(-pase);
            	}
            	if(k==KeyEvent.VK_DOWN){
            		this.setY(pase);
            		this.setX(0);
                }
                if(k==KeyEvent.VK_M){
                    attack();
                }
    }
   
    
}