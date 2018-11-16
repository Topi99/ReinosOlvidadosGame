package org.team.project.state;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
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

    panel.getDbg().setColor(Color.black);
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
    TextField txtFieldUID = new TextField(50, 150, 200, 40);

    Button btnLogin = new Button(50, 80, 100, 40, Color.blue, "Iniciar Sesión") {
      @Override
      public void call() {
        this.active = true;
        panel.getPanelCtx().setStatePanel(new CityViewStatePanel(txtFieldUID.getValue()));
        panel.getPanelCtx().getStatePanel().addElements(panel);
      }
    };

    Button btnPasteUID = new Button(50, 200, 100, 40, Color.blue, "Pegar UID") {
      @Override
      public void call() {
        this.active = true;
        Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable t = c.getContents(this);
        if (t == null)
            return;
        try {
          txtFieldUID.setValue((String) t.getTransferData(DataFlavor.stringFlavor));
        } catch (Exception e){
            e.printStackTrace();
        }//try
      }
    };

    inputs.add(btnLogin);
    inputs.add(txtFieldUID);
    inputs.add(btnPasteUID);
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