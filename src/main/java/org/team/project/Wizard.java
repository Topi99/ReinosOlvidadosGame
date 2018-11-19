package org.team.project;

import javax.swing.ImageIcon;



public class Wizard extends Characters{
    protected Entidad rec1;
    protected Entidad rec2;
    protected Entidad rec3;


    public Wizard(){
        x=100;
        y=100;

        ImageIcon img= new ImageIcon (this.getClass().getResource("hechicero.png"));
        imagen = img.getImage(); 
        vida= 100;
        pase=12;
        rec1=new Entidad();
        rec2=new Entidad();
        rec3=new Entidad();
        
    }
    @Override
    public void attack() {
        
       
    }
    public void actions(int k){
       
    }
}