package org.team.project.state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.team.project.GamePanel;
import org.team.project.Panel;
import org.team.project.PanelFig;
import org.team.project.buildings.Castle;
import org.team.project.buildings.Market;
import org.team.project.buildings.Village;
import org.team.project.inputs.Button;
import org.team.project.inputs.Input;
import org.team.project.*;
import org.team.project.Notification;

public class CityViewStatePanel implements StatePanel {
  private String UID;
  private String userEmail = "";
  Warrior warrior1;
  private String userDisplayName = "";
  private ArrayList<Input> inputs = new ArrayList<Input>();
  private BufferedImage bg = null;
  private Button village, market, castle;
  private ArrayList<PanelFig> panels = new ArrayList<PanelFig>();
  private CityViewStatePanel city;
  private ArrayList<Notification> notifications = new ArrayList<Notification>();
  final FirebaseDatabase database = FirebaseDatabase.getInstance();
  DatabaseReference myNotifRef;
  private int yString = 0;
  
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
    this.city = this;
    myNotifRef = database.getReference("notifications/"+UID);


    myNotifRef.addValueEventListener(new ValueEventListener(){
      @Override
      public void onDataChange(DataSnapshot snapshot) {
        notifications.clear();
        System.out.println("Update:*********\n\n\n"+snapshot.getValue().toString());
        for(DataSnapshot snap: snapshot.getChildren()) {
          Notification noti = new Notification(snap.child("pending").getValue().toString().matches("true"), snap.child("from").getValue().toString(), snap.child("to").getValue().toString());
          notifications.add(noti);
        }
      }
    
      @Override
      public void onCancelled(DatabaseError error) {
        System.out.println("The read failed: " + error.getCode());
      }
    });
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

  // GamePanel addElements
  @Override
  public void addElements(GamePanel panel) {
    Button btnPlay = new Button(350, 80, 130, 40, Color.blue, "Notificaciones") {
      @Override
      // public void call() {
      //   this.active = true;
      //   panel.getPanelCtx().setStatePanel(new PlayingStatePanel());
      //   panel.getPanelCtx().getStatePanel().addElements(panel);
      // }

      //Button Call
      public void call() {
        this.active = true;
        panel.getPanelCtx().setStatePanel(new InfoStatePanel(panel) {
          // InfoStatePanel gameRender
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
    
            panel.getDbg().drawImage(this.getBg(), panel.getPwidth()/2 - this.getWidth()/2, 0, this.getWidth(), this.getHeight(), null);
    
            panel.getDbg().setColor(Color.white);
            panel.getDbg().drawString("Notificaciones", panel.getPwidth()/2 - this.getWidth()/2 + 30, 70);
            
            for(Input i: this.getInputs()) {
              i.draw(panel.getDbg());
            }
          }// InfoStatePanel gameRender
    
          // InfoStatePanel addElements
          @Override 
          public void addElements(GamePanel panel) {
            yString = 150;
            Button close = new Button(panel.getPwidth()/2+this.getWidth()/2-20, 5, 50, 150, "../../../../closeBtn.png") {
              @Override
              public void call() {
                this.active = true;
                panel.getPanelCtx().setStatePanel(city);
              }
            };
            this.addInput(close);
            for(Notification notif: notifications) {
              this.addInput(getButton(notif, panel, notif.to));
              yString += 65;
            }
          } // addElements InfoStatePanel
        }); // InfoStatePanel
        panel.getPanelCtx().getStatePanel().addElements(panel);
      }
    };
    btnPlay.setStrPaddLeft(6);
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

  public Button getButton(Notification notif, GamePanel panel, String to) {
    Button btn = new Button(200, yString, 150, 50, notif.from, notif.from) {
      @Override
      public void call() {
        Map<String, Object> update = new HashMap<>();
        update.put("pending", false);
        myNotifRef.child(notif.from).updateChildrenAsync(update);
        panel.getPanelCtx().setStatePanel(new PlayingStatePanel(false, city.getUID(), to));
        panel.getPanelCtx().getStatePanel().addElements(panel);
      }
    };

    return btn;
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