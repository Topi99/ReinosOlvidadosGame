package org.team.project.state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;

import org.team.project.GamePanel;
import org.team.project.Panel;
import org.team.project.PanelFig;
import org.team.project.buildings.Castle;
import org.team.project.buildings.Market;
import org.team.project.buildings.Village;
import org.team.project.inputs.Button;
import org.team.project.inputs.Input;
import org.team.project.*;

public class CityViewStatePanel implements StatePanel {
  private String UID;
  private String userEmail = "";
  Warrior warrior1;
  private String userDisplayName = "";
  private ArrayList<Input> inputs = new ArrayList<Input>();
  private BufferedImage bg = null;
  private Button village, market, castle;
  private ArrayList<PanelFig> panels = new ArrayList<PanelFig>();
  
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
    warrior1 = new Warrior();
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
    market.draw(panel.getDbg());
    village.draw(panel.getDbg());
    castle.draw(panel.getDbg());
    
    
    for(Panel p: panels) {
      p.draw(panel.getDbg());
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


    PanelFig statusPanel = new PanelFig(0,0,550, 150) {
      @Override
      public void draw(Graphics g) {
        g.drawImage(this.getBg(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
        g.setColor(Color.white);
        g.drawString("UID: " + getUID(), 15, 30);
        g.drawString("E-Mail: " + getUserEmail(), 15, 55);
        g.drawString("Name: " + getUserDisplayName(), 15, 80);
        btnPlay.draw(g);
      }
    };

    panels.add(statusPanel);
  }

  /**
   * @return the uID
   */
  public String getUID() {
    return UID;
  }

  /**
   * @return the userEmail
   */
  public String getUserEmail() {
    return userEmail;
  }

  /**
   * @return the userDisplayName
   */
  public String getUserDisplayName() {
    return userDisplayName;
  }

  @Override
  public void keyPressed(int keyCode) {
		
	}

}