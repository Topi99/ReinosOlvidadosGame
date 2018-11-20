package org.team.project.buildings;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.firebase.auth.ExportedUserRecord;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.ListUsersPage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.team.project.GamePanel;
import org.team.project.Notification;
import org.team.project.inputs.Button;
import org.team.project.inputs.Input;
import org.team.project.state.CityViewStatePanel;
import org.team.project.state.InfoStatePanel;
import org.team.project.state.PlayingStatePanel;

public class Castle extends Button {
  private BufferedImage bg;
  private GamePanel panel;
  private CityViewStatePanel city;
  private ArrayList<Button> notificationsBtns = new ArrayList<Button>();

  public Castle(int x, int y, int width, int height, GamePanel panel) {
    super(x, y, width, height);
    
    try {
      bg = ImageIO.read(this.getClass().getResource("../../../../castle.png"));
    } catch(Exception e) {}

    this.x = panel.getPwidth()/2 - (bg.getWidth()/2)*3;
    this.y = panel.getPheight()/2 - bg.getHeight()/2;
    this.width = bg.getWidth();
    this.height = bg.getHeight();

    this.rect = new Rectangle(this.x, this.y, this.width, this.height);
    this.panel = panel;
    this.city = (CityViewStatePanel)panel.getPanelCtx().getStatePanel();
  }

  int yString = 150;

  public void call() {
    this.active = true;
    ListUsersPage page;
    ArrayList<ExportedUserRecord> users = new ArrayList<ExportedUserRecord>();
    

    try {
      page = FirebaseAuth.getInstance().listUsers(null);
      while (page != null) {
        for (ExportedUserRecord user : page.getValues()) {
          users.add(user);
        }
        page = page.getNextPage();
      }
    } catch(Exception e) { System.out.println(e.getMessage()); }
    
    panel.getPanelCtx().setStatePanel(new InfoStatePanel(panel) {
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
        panel.getDbg().drawString("Castle", panel.getPwidth()/2 - this.getWidth()/2 + 30, 70);
        
        for(Input i: this.getInputs()) {
          i.draw(panel.getDbg());
        }

        yString = 100;
        panel.getDbg().setColor(Color.white);
        
      }

      @Override 
      public void addElements(GamePanel panel) {
        Button close = new Button(panel.getPwidth()/2+this.getWidth()/2-20, 5, 50, 150, "../../../../closeBtn.png") {
          @Override
          public void call() {
            this.active = true;
            panel.getPanelCtx().setStatePanel(city);
          }
        };

        

        for(ExportedUserRecord user: users) {
          Button btn = new Button(panel.getPwidth()/2 - this.getWidth()/2 + 50, yString, 250, 50, user.getDisplayName() == null ? user.getUid():user.getDisplayName(), user.getUid()) {
            @Override
            public void call() {
              this.active = true;
              final FirebaseDatabase database = FirebaseDatabase.getInstance();
              DatabaseReference ref = database.getReference("notifications/"+this.getData());
              ref.child(city.getUID()).setValueAsync(new Notification(true, city.getUID(), user.getUid()));
              panel.getPanelCtx().setStatePanel(new PlayingStatePanel(true));
              panel.getPanelCtx().getStatePanel().addElements(panel);
            }
          };
          btn.setStrPaddLeft(50);
          this.addInput(btn);
          yString += 65;
        }
        this.addInput(close);
      }
    });
    panel.getPanelCtx().getStatePanel().addElements(panel);
  }
  
  public void draw(Graphics g) {
    g.drawImage(bg, x, y, width, height, null);
  }
}