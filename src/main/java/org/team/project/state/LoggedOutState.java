package org.team.project.state;

import java.awt.Color;
import java.util.ArrayList;

import org.team.project.GamePanel;
import org.team.project.inputs.Input;

public class LoggedOutState implements StatePanel {
  private ArrayList<Input> buttons = new ArrayList<Input>();

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

    for(Input butn: buttons) {
      butn.draw(panel.getDbg());
    }
  }

  @Override
  public void checkButtons(int x, int y) {
    for(Input btn: buttons) {
      if((x >= btn.getX() && x < btn.getX() + btn.getWidth()) && (y >= btn.getY() && y < btn.getY() + btn.getHeight())) {
        btn.call();
      }
    }
  }

  @Override
  public void addElements(GamePanel panel) {
    Input btn = new Input(50, 80, 100, 40, Color.red) {
      @Override
      public void call() {
        panel.getPanelCtx().setStatePanel(new PlayingStatePanel());
        panel.getPanelCtx().getStatePanel().addElements(panel);
      }
    };

    buttons.add(btn);
  }

  @Override
  public void keyPressed(int keyCode) {

  }
}