package org.team.project.state;

import java.awt.Color;
import org.team.project.GamePanel;
import org.team.project.Warrior;
import org.team.project.Wizard;

public class PlayingStatePanel implements StatePanel {
  Warrior warrior1;
  Wizard wizard1;

  @Override
  public void gameRender(GamePanel panel) {
    if (panel.getDbImage() == null) {
      panel.setDbImage(panel.createImage(panel.getPwidth(), panel.getPheight()));
      if (panel.getDbImage() == null) {
        System.out.println("panel.getDbImage() is null");
        return;
      } else {
        panel.setDbg(panel.getDbImage().getGraphics());
      }
    }
    panel.getDbg().setColor(Color.white);
    panel.getDbg().fillRect(0, 0, panel.getPwidth(), panel.getPheight());
    
    // panel.getDbg().drawImage(warrior1.getBufferImage(), warrior1.getX(),warrior1.getY(), null);
    // warrior1.drawVida(panel.getDbg());
    // System.out.println(warrior1.getX() + "," + warrior1.getY() +":"+ warrior1.getVida());
    panel.getDbg().drawImage(wizard1.getBufferImage(),wizard1.getX(),wizard1.getY(),null);
    wizard1.drawVida(panel.getDbg());
    System.out.println(wizard1.getX()+","+wizard1.getY()+ ":"+ wizard1.getVida()+ " ima:"+wizard1.getIma());
  } // gameRender()

  @Override
  public void checkInputs(int x, int y) {
  }
  
  @Override
  public void addElements(GamePanel panel) {
    //warrior1=new Warrior();
     wizard1=new Wizard();
	}

  @Override
  public void keyPressed(int keyCode) {
    //warrior1.actions(keyCode);
    wizard1.actions(keyCode);
  }
  
}