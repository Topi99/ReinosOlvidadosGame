package org.team.project;

import javax.sound.sampled.*;
import java.awt.*;
import java.lang.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class Sound implements Runnable{
    protected Thread thread;
    private Clip clip;
    private AudioInputStream ais;
    private String name;
    public Sound(){
        thread= new Thread(this);
        name= "musicaepica.wav";
        thread.start(); //se inicia el thread
    }
    public void run(){
        //Aquí no es necesario un ciclo puesto que no se actualiza la imagen, 
        //simplemente hay que empezar a tocar la imagen 
        try{
            ais=AudioSystem.getAudioInputStream(getClass().getResource(name));
        clip=AudioSystem.getClip();
        clip.open(ais);
        //lineas para que se extraiga el clip de audio
        }catch(Exception e){
            e.printStackTrace();
        }
        try {
            clip.loop(30); //se loopea 30 veces
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/*
/*
git add *  //juntar todos los archivos que modifiqué cree
git commit -m "lslkasjdkasdkals"  // commit para comentar y decir qué hiciste
git checkout develop //cambiar a la rama develop
git pull origin develop //por si alguien hizo cosas
git push origin mirama(celis)
 git merge celis // juntar con la rama local en la que estuviste trabajando
git push origin develop  // para subir a la branch de develop
git checkout celis // regresar a mi branch
*/ 