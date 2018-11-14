package org.team.project.state;

import java.awt.Color;

import org.team.project.GamePanel;

public class LoggedOutState implements StatePanel {
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

    panel.getDbg().setColor(Color.red);
    panel.getDbg().drawString("Inicia Sesión", 50, 50);
  }
}