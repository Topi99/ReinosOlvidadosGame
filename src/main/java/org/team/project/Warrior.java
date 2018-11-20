package org.team.project;

import java.awt.image.*;
import java.io.IOException;

import java.awt.event.*;
import java.awt.*;


public class Warrior extends Characters{
    protected BufferedImage men;
    protected SpriteSheet ss;
    protected int ima;

    public Warrior(){
        x=150;
        y=150;
        vida=120;
        pase=7;
        ima=2;
        c=Color.green;
        rectangulo=new Rectangle(getX(),getY(),32,32);
        BufferedImageLoader loader= new BufferedImageLoader();
        try{
            spriteSheet= loader.loadImage("../../../warriorsprite.png");
        }catch(IOException e){
            e.printStackTrace();
        }
        ss= new SpriteSheet(spriteSheet);
        //men=ss.grabImage(1,3,32,32);
    }
    public BufferedImage getBufferImage(){
        return men;
    }
    public void setIma(int ima){
        this.ima+=ima;
    }
    public void updateWalkingImage(){
        if(ima<=10)
        {
            men=ss.grabImage(ima,3,32,32);
            setIma(1);
        }
        else
        ima=1;
    }
    @Override
    public void setX(int n){
        x+=n;
        updateWalkingImage();
    }
    @Override
    public void setY(int n){
        y+=n;
        updateWalkingImage();
    }
    @Override
    public void attack(){
        if(ima<=10)
        {
            men=ss.grabImage(ima,4,32,32);
            ima++;
        }
        else
        ima=1;
                   
    }
    public void die(){
        if(ima<=10)
        {
            men=ss.grabImage(ima,5,32,32);
            ima++;
        }
        else
        ima=1;
    }
    
}