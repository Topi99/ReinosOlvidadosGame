import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import javax.swing.ImageIcon;
public class Warrior extends Characters{
    public Warrior(){
        x=100;
        y=100;
        ImageIcon img= new ImageIcon (this.getClass().getResource("hechicero.png"));
        imagen = img.getImage(); 
    }
}