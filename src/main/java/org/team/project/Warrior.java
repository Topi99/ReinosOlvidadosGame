package org.team.project;

import javax.swing.ImageIcon;
import java.awt.*;
public class Warrior extends Characters{
    public Warrior(){
        x=100;
        y=100;
        c= Color.green;
        ImageIcon img= new ImageIcon (this.getClass().getResource("warrior.png"));
        imagen = img.getImage(); 
        vida=120;
    }
    
}