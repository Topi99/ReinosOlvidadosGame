package org.team.project.state;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.team.project.EnergyWizard;
import org.team.project.GamePanel;
import org.team.project.Warrior;
import org.team.project.Wizard;

public class PlayingStatePanel implements StatePanel {
  Warrior warrior1, warrior2, mine, other;
  Wizard wizard1;
  EnergyWizard ew;
  int kc,i=1;
  int xw,yw;
  private boolean retador;
  private final FirebaseDatabase database = FirebaseDatabase.getInstance();
  private DatabaseReference ref;
  private DatabaseReference from, to;
  private Map<String, Object> data = new HashMap<String, Object>();

  public PlayingStatePanel(boolean retador, String from, String to) {
    this.retador = retador;
    this.ref = database.getReference("matches/"+from+"-to-"+to);
    this.from = ref.child(from);
    this.to = ref.child(to);

    this.from.addValueEventListener(new ValueEventListener(){
    
      @Override
      public void onDataChange(DataSnapshot snapshot) {
        System.out.println("update\n\n\n"+snapshot.getValue());
        System.out.println("x "+snapshot.child("x").getValue());
        System.out.println("y "+snapshot.child("y").getValue());
        mine.setX(Integer.parseInt(snapshot.child("x").getValue().toString()));
        mine.setY(Integer.parseInt(snapshot.child("y").getValue().toString()));
      }
    
      @Override
      public void onCancelled(DatabaseError error) {
        
      }
    });

    this.to.addValueEventListener(new ValueEventListener(){
    
      @Override
      public void onDataChange(DataSnapshot snapshot) {
        System.out.println("update\n\n\n"+snapshot.getValue());
        System.out.println("x "+snapshot.child("x").getValue());
        System.out.println("y "+snapshot.child("y").getValue());
        other.setX(Integer.parseInt(snapshot.child("x").getValue().toString()));
        other.setY(Integer.parseInt(snapshot.child("y").getValue().toString()));
      }
    
      @Override
      public void onCancelled(DatabaseError error) {
        
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
   
    panel.getDbg().setColor(Color.white);
    panel.getDbg().fillRect(0, 0, panel.getPwidth(), panel.getPheight());
    
    panel.getDbg().drawImage(mine.getBufferImage(), mine.getX(),mine.getY(), null);
    mine.drawVida(panel.getDbg());
    
    panel.getDbg().drawImage(other.getBufferImage(), other.getX(),other.getY(), null);
    other.drawVida(panel.getDbg());
     
    // System.out.println(warrior1.getX() + "," + warrior1.getY() +":"+ warrior1.getVida());
    //  panel.getDbg().drawImage(wizard1.getBufferImage(),wizard1.getX(),wizard1.getY(),null);
    //  wizard1.drawVida(panel.getDbg());
    //  xw=wizard1.getX()+12;
    //  yw=wizard1.getY()+16;

   //if(kc==KeyEvent.VK_M){
    //  panel.getDbg().drawImage(ew.getBufferImage(),wizard1.getX(),wizard1.getY(),null);
     // i++;
    //}
     //System.out.println(wizard1.getX()+","+wizard1.getY()+ ":"+ wizard1.getVida()+ " ima:"+wizard1.getIma());
  } // gameRender()

  @Override
  public void checkInputs(int x, int y) {
  }
  
  @Override
  public void addElements(GamePanel panel) {
    warrior1=new Warrior();
    warrior2 = new Warrior();
    
    if(retador) {
      mine = warrior1;
    } else {
      mine = warrior2;
    }

    if(retador) {
      other = warrior2;
    } else {
      other = warrior1;
    }
    //wizard1=new Wizard();
    ew=new EnergyWizard();
	}

  @Override
  public void keyPressed(int keyCode) {
    // warrior1.actions(keyCode);

    if (keyCode == KeyEvent.VK_RIGHT) {
      data.put("x", mine.getX()+7);
      data.put("y", mine.getY());
    } else if (keyCode == KeyEvent.VK_LEFT) {
      data.put("x", mine.getX()-7);
      data.put("y", mine.getY());
    } else if (keyCode == KeyEvent.VK_UP) {
      data.put("x", mine.getX());
      data.put("y", mine.getY()-7);
    } else if (keyCode == KeyEvent.VK_DOWN) {
      data.put("x", mine.getX());
      data.put("y", mine.getY()+7);
    }
    from.setValueAsync(data);
    data.clear();
  }
  
}