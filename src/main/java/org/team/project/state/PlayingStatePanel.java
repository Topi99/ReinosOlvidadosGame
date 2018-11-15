package org.team.project.state;

import java.awt.Color;
import java.awt.event.KeyEvent;

import org.team.project.Circle;
import org.team.project.GamePanel;

public class PlayingStatePanel implements StatePanel {
  Circle cir;

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
    
    cir.draw(panel.getDbg());
    
  } // gameRender()

  @Override
  public void checkInputs(int x, int y) {
  }
  
  @Override
  public void addElements(GamePanel panel) {
    cir = new Circle(0, 0);
	}

  @Override
  public void keyPressed(int keyCode) {
    if (keyCode == KeyEvent.VK_RIGHT) {
      cir.setX(20);
      cir.setY(0);
    } else if (keyCode == KeyEvent.VK_LEFT) {
      cir.setX(-20);
      cir.setY(0);
    } else if (keyCode == KeyEvent.VK_UP) {
      cir.setX(0);
      cir.setY(-20);
    } else if (keyCode == KeyEvent.VK_DOWN) {
      cir.setX(0);
      cir.setY(20);
    }
  }

}