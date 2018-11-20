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
        x=280;
        y=280;
        vida= 100;
        pase=12;
        ima=2;
        c=Color.blue;
        rectangulo=new Rectangle(getX(),getY(),32,32);
        BufferedImageLoader loader= new BufferedImageLoader();
        try{
            spriteSheet= loader.loadImage("../../../CrazyWizard.png");
        }catch(IOException e){
            e.printStackTrace();
        }
        ss= new SpriteSheet(spriteSheet);
        //men=ss.grabImage(1,4,32,32);
       
    }
    public void setX(int n){
        x+=n;
        updateWalkingImage();
    }
    public void setY(int n){
        y+=n;
        updateWalkingImage();
    }
    public BufferedImage getBufferImage(){
        return men;
    }
    public void setIma(int ima){
        this.ima+=ima;
    }
    public void updateWalkingImage(){
        if(ima<=10){
            men=ss.grabImage(ima,4,32,32);
            ima++;
        }
        else{
            ima=1;
        }
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
   
       
}