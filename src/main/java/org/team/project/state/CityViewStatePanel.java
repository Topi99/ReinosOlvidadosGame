package org.team.project.state;

import java.awt.Color;
import java.util.ArrayList;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;

import org.team.project.GamePanel;
import org.team.project.inputs.Button;
import org.team.project.inputs.Input;

public class CityViewStatePanel implements StatePanel {
  private String UID;
  private String userEmail = "";
  private String userDisplayName = "";
  private ArrayList<Input> inputs = new ArrayList<Input>();
  
  public CityViewStatePanel(String uid) {
    this.UID = uid;
    
    try {
      UserRecord userRecord = FirebaseAuth.getInstance().getUser(this.UID);
      this.userEmail = userRecord.getEmail();
      this.userDisplayName = userRecord.getDisplayName();

    } catch(Exception e) {
      System.out.println("Firebase: "+e.getMessage());
    }
  }

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
    panel.getDbg().drawString("UID: " + this.UID, 50, 50);
    panel.getDbg().drawString("E-Mail: " + this.userEmail, 50, 100);
    panel.getDbg().drawString("Name: " + this.userDisplayName, 50, 150);
    
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
    Button btnPlay = new Button(350, 80, 100, 40, Color.blue, "Jugar") {
      @Override
      public void call() {
        this.active = true;
        System.out.println("HOla");
        panel.getPanelCtx().setStatePanel(new PlayingStatePanel());
        panel.getPanelCtx().getStatePanel().addElements(panel);
      }
    };

    inputs.add(btnPlay);
  }

  @Override
  public void keyPressed(int keyCode) {
		
	}

}