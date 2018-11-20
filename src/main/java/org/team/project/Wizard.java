package org.team.project;


import java.awt.image.BufferedImage;
import java.io.IOException;

import java.awt.event.*;
import java.awt.*;


public class Wizard extends Characters{
    protected BufferedImage men;
    protected SpriteSheet ss;
    protected int ima;
    

    public Wizard(){
        x=180;
        y=180;
        vida= 100;
        pase=5;
        ima=1;
        c=Color.blue;
        BufferedImageLoader loader= new BufferedImageLoader();
        try{
            spriteSheet= loader.loadImage("../../../CrazyWizard.png");
        }catch(IOException e){
            e.printStackTrace();
        }
        ss= new SpriteSheet(spriteSheet);
       
    }
    public BufferedImage getBufferImage(){
        return men;
    }
    public void setIma(int ima){
        this.ima+=ima;
    }
    @Override
    public void attack() {
        if(ima<=10){
            men=ss.grabImage(ima,4,32,32);
            ima++;
        }
        else{
            ima=1;
        }
       
    }
    public int getIma(){
        return ima;
    }
    public void die(){
        if(ima<=10){
            men=ss.grabImage(ima,5,32,32);
                ima++;
        }
        else{
            ima=1;
        }
    }
    @Override
    public void actions(int k){
        if(k==KeyEvent.VK_UP){
            this.setY(-pase);
            this.setX(0);
            if(ima<=10)
            {
                men=ss.grabImage(ima,3,32,32);
                setIma(1);
            }
            else
            ima=1;
        }
        if(k==KeyEvent.VK_RIGHT){
            this.setY(0);
            this.setX(pase);
            if(ima<=10)
            {
                men=ss.grabImage(ima,3,32,32);
                ima++;
            }
            else
            ima=1;
        }
        if(k==KeyEvent.VK_LEFT){
            this.setY(0);
            this.setX(-pase);
            if(ima<=8)
            {
                men=ss.grabImage(ima,3,32,32);
                ima++;
            }
            else
            ima=1;
        }
        if(k==KeyEvent.VK_DOWN){
            this.setY(pase);
            this.setX(0);
            if(ima<=10)
            {
                men=ss.grabImage(ima,3,32,32);
                ima++;
            }
            else
            ima=1;
        }
        if(k==KeyEvent.VK_M){
            attack();
        }
        if(k==KeyEvent.VK_D){
            die();
        }

    }
}