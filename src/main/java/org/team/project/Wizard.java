package org.team.project;

import javax.swing.ImageIcon;
public class Wizard extends Characters{
    public Wizard(){
        x=100;
        y=100;
        ImageIcon img= new ImageIcon (this.getClass().getResource("hechicero.png"));
        imagen = img.getImage(); 
    }
}