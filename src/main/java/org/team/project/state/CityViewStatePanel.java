package org.team.project.state;

import java.awt.Color;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;

import org.team.project.GamePanel;

public class CityViewStatePanel implements StatePanel {
  private String UID;
  private String userEmail = "";
  private String userDisplayName = "";
  
  public CityViewStatePanel(String uid) {
    this.UID = uid;
    
    try {
      UserRecord userRecord = FirebaseAuth.getInstance().getUser(this.UID);
      this.userEmail = userRecord.getEmail();
      this.userDisplayName = userRecord.getDisplayName();

    } catch(Exception e) {
      System.out.println(e.getMessage());
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
  }

  @Override
  public void checkInputs(int x, int y) {

  }

  @Override
  public void addElements(GamePanel panel) {

  }

  @Override
  public void keyPressed(int keyCode) {
		
	}

}