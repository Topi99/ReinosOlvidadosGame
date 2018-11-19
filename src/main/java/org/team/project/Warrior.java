package org.team.project;

import java.awt.image.BufferedImage;
import java.io.IOException;

import java.awt.event.*;


public class Warrior extends Characters{
    protected BufferedImage men;
    protected SpriteSheet ss;

    public Warrior(){
        x=100;
        y=100;
        vida=120;
        pase=7;
        BufferedImageLoader loader= new BufferedImageLoader();
        try{
            spriteSheet= loader.loadImage("warriorsprite");
        }catch(IOException e){
            e.printStackTrace();
        }
        ss= new SpriteSheet(spriteSheet);
        men=ss.grabImage(1,3,32,32);
    }
    public BufferedImage getBufferImage(){
        return men;
    }
    @Override
    public void actions(int k){
        if(k==KeyEvent.VK_UP){
            		this.setY(-pase);
                    this.setX(0);
                    men=ss.grabImage(1,3,32,32);
                    men=ss.grabImage(2,3,32,32);
                    men=ss.grabImage(3,3,32,32);
                    men=ss.grabImage(4,3,32,32);
            	}
            	if(k==KeyEvent.VK_RIGHT){
            		this.setY(0);
                    this.setX(pase);
                    men=ss.grabImage(1,3,32,32);
                    men=ss.grabImage(2,3,32,32);
                    men=ss.grabImage(3,3,32,32);
                    men=ss.grabImage(4,3,32,32);
            	}
            	if(k==KeyEvent.VK_LEFT){
            		this.setY(0);
                    this.setX(-pase);
                    men=ss.grabImage(1,3,32,32);
                    men=ss.grabImage(2,3,32,32);
                    men=ss.grabImage(3,3,32,32);
                    men=ss.grabImage(4,3,32,32);
            	}
            	if(k==KeyEvent.VK_DOWN){
            		this.setY(pase);
                    this.setX(0);
                    men=ss.grabImage(1,3,32,32);
                    men=ss.grabImage(2,3,32,32);
                    men=ss.grabImage(3,3,32,32);
                    men=ss.grabImage(4,3,32,32);
                }
                if(k==KeyEvent.VK_M){
                    attack();
                }
    }
    @Override
    public void attack(){
                    men=ss.grabImage(4,4,32,32);
                    men=ss.grabImage(5,4,32,32);
                    men=ss.grabImage(6,4,32,32);
                    men=ss.grabImage(7,4,32,32);
                    men=ss.grabImage(8,4,32,32);
    }
    
}