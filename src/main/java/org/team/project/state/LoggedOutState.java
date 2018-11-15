package org.team.project.state;

import java.awt.Color;
import java.util.ArrayList;

import org.team.project.GamePanel;
import org.team.project.inputs.Button;
import org.team.project.inputs.Input;
import org.team.project.inputs.TextField;

public class LoggedOutState implements StatePanel {
  private ArrayList<Input> inputs = new ArrayList<Input>();

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

    panel.getDbg().setColor(Color.LIGHT_GRAY);
    panel.getDbg().fillRect(0, 0, panel.getPwidth(), panel.getPheight());

    panel.getDbg().setColor(Color.red);
    panel.getDbg().drawString("Inicia Sesión en el navegador", 50, 50);

    for(Input butn: inputs) {
      butn.draw(panel.getDbg());
    }
  }

  @Override
  public void checkInputs(int x, int y) {
    for(Input input: inputs) {
      if((x >= input.getX() && x < input.getX() + input.getWidth()) && (y >= input.getY() && y < input.getY() + input.getHeight())) {
        input.call();
      } else {
        input.deactivate();
      }
    }
  }

  @Override
  public void addElements(GamePanel panel) {
    Button btn = new Button(50, 80, 100, 40, Color.blue, "Iniciar Sesión") {
      @Override
      public void call() {
        this.active = true;
        panel.getPanelCtx().setStatePanel(new PlayingStatePanel());
        panel.getPanelCtx().getStatePanel().addElements(panel);
      }
    };

    TextField txtFieldUserMail = new TextField(50, 150, 100, 40) {
      @Override
      public void call() {
        this.active = true;
      }
    };

    TextField txtFieldDomain = new TextField(50, 200, 100, 40) {
      @Override
      public void call() {
        this.active = true;
      }
    };

    TextField txtFieldPass = new TextField(50, 250, 100, 40) {
      @Override
      public void call() {
        this.active = true;
      }
    };

    inputs.add(btn);
    inputs.add(txtFieldDomain);
    inputs.add(txtFieldUserMail);
    inputs.add(txtFieldPass);
  }

  @Override
  public void keyPressed(int keyCode) {
    TextField field;
    for(Input input: inputs) {
      if(TextField.class.isInstance(input) && input.isActive()) {
        field = (TextField) input;
        field.keyPressed(keyCode);
      }
    }
  }
}