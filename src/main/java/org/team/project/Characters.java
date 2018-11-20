package org.team.project;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Observer;
import java.util.Observable;

//import java.util.Observable;

public class Characters implements Observer {
    protected int x;
    protected int y;
    protected Image imagen;
    protected Rectangle rectangulo;
    protected int vida;
    protected Rectangle Barravida;
    protected Color c;
    protected int pase;
    protected BufferedImage spriteSheet=null;
    protected Entidad enti;
    /**
     * @return the barravida
     */
    public Rectangle getBarravida() {
        return Barravida;
    }
    public void drawVida(Graphics g){
        g.setColor(c);
        g.drawRect(getX()-10,getY()-10, getVida()/2,5);
        g.fillRect(getX()-10, getY()-10, getVida()/2, 5);
    }
//hola soy pauchis :)
    public void setX(int n){
        x+=n;
        // setChanged(); //ya se cambió
        // notifyObservers();//notifica a los observers que se hayan agregado
    }
    public void setY(int n){
        y+=n;
        // setChanged(); //ya se cambió
        // notifyObservers();//notifica a los observers que se hayan agregado
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
    public void update(Observable o, Object o1) {
        this.enti= (Entidad) o;
        if(overlaps(enti))
            {
        this.setVida(-10);
        System.out.println("esta chocando");}//el cirulo se cambia de color si ya se tocaron
      
    }
    //método que verifica si ya se interceptaron los circulos, no es necesario usar intersects
    public boolean overlaps (Entidad e) {
        return x < e.x + 32 && x + 32 > e.x && y < e.y + 32 && y + 32 > e.y;
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