package org.team.project.state;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;

import org.team.project.GamePanel;
import org.team.project.buildings.Castle;
import org.team.project.buildings.Market;
import org.team.project.buildings.Village;
import org.team.project.inputs.Button;
import org.team.project.inputs.Input;

public class CityViewStatePanel implements StatePanel {
  private String UID;
  private String userEmail = "";
  private String userDisplayName = "";
  private ArrayList<Input> inputs = new ArrayList<Input>();
  private BufferedImage bg = null;
  private Button village, market, castle;
  
  public CityViewStatePanel(String uid) {
    this.UID = uid;
    
    try {
      UserRecord userRecord = FirebaseAuth.getInstance().getUser(this.UID);
      this.userEmail = userRecord.getEmail();
      this.userDisplayName = userRecord.getDisplayName();
      bg = ImageIO.read(this.getClass().getResource("../../../../map2 (1).png"));
    } catch(Exception e) {
      System.out.println("Firebase: "+e.getMessage());
    }
    village = new Button(0,0,0,0);
    market = new Button(0,0,0,0);
    castle = new Button(0,0,0,0);
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
    panel.getDbg().drawImage(bg, 0, 0, panel.getPwidth(), panel.getPheight(), null);
    // panel.getDbg().drawImage(market, panel.getPwidth()/2 - market.getWidth()/5, panel.getPheight() - market.getHeight()-50, market.getWidth(), market.getHeight(), null);
    // panel.getDbg().drawImage(village, panel.getPwidth() - village.getWidth() - 15, 0, village.getWidth(), village.getHeight(), null);
    // panel.getDbg().drawImage(castle, panel.getPwidth()/2 - (castle.getWidth()/2)*3, panel.getPheight()/2 - market.getHeight()/2, castle.getWidth(), castle.getHeight(), null);
    market.draw(panel.getDbg());
    village.draw(panel.getDbg());
    castle.draw(panel.getDbg());
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

    market = new Market(panel.getPwidth()/2 - market.getWidth()/5, panel.getPheight() - market.getHeight()-50, market.getWidth(), market.getHeight(), panel);
    village = new Village(panel.getPwidth() - village.getWidth() - 15, 0, village.getWidth(), village.getHeight(), panel);
    castle = new Castle(panel.getPwidth()/2 - (castle.getWidth()/2)*3, panel.getPheight()/2 - castle.getHeight()/2, castle.getWidth(), castle.getHeight(), panel);

    bg = GamePanel.resize(bg, panel.getPwidth(), panel.getPheight());

    inputs.add(btnPlay);
    inputs.add(village);
    inputs.add(market);
    inputs.add(castle);
  }

  @Override
  public void keyPressed(int keyCode) {
		
	}

}