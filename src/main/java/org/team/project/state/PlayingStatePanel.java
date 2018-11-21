package org.team.project.state;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.team.project.EnergyWizard;
import org.team.project.GamePanel;
import org.team.project.inputs.Button;
import org.team.project.inputs.Input;
import org.team.project.inputs.Resources;
import org.team.project.Warrior;
import org.team.project.Wizard;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PlayingStatePanel implements StatePanel {
  Warrior warrior1, warrior2, mine, other;
  Wizard wizard1;
  EnergyWizard ew;
  int kc,i=1;
  int xw,yw;
  private BufferedImage bg = null;
  private boolean retador;
  private final FirebaseDatabase database = FirebaseDatabase.getInstance();
  private DatabaseReference ref;
  private DatabaseReference from, to;
  private Map<String, Object> data = new HashMap<String, Object>();
  private Map<String, Object> data2 = new HashMap<String, Object>();
  private ArrayList<Input> inputs = new ArrayList<Input>();

  public PlayingStatePanel(boolean retador, String from, String to) {
    this.retador = retador;
    this.ref = database.getReference("matches/"+from+"-to-"+to);
    this.from = ref.child(from);
    this.to = ref.child(to);
    warrior1=new Warrior();
    warrior2 = new Warrior();
    try{
    bg = ImageIO.read(this.getClass().getResource("../../../../mapabatalla.jpeg"));
    }catch(IOException e){
      e.printStackTrace();
    }
    if(retador) {
      mine = warrior1;
      other = warrior2;
    } else {
      mine = warrior2;
      other = warrior1;
    }

    // mine=warrior2;
    // other=warrior1;

    Button tree = new Button(100, 50, 60, 90, "../../../../tree.png") {
      @Override
      public void call() {
        this.active = true;
        data2.put("n", Resources.getInstance().getWood() + 5);
        ref.child("Wood").setValueAsync(data2);
      }
    };

    Button rock = new Button(250, 80, 50, 90, "../../../../rock.png") {
      @Override
      public void call() {
        this.active = true;
        data2.put("n", Resources.getInstance().getStone() + 5);
        ref.child("Rock").setValueAsync(data2);
      }
    };

    Button iron = new Button(400, 100, 70, 90, "../../../../iron.png") {
      @Override
      public void call() {
        this.active = true;
        data2.put("n", Resources.getInstance().getIron() + 5);
        ref.child("Iron").setValueAsync(data2);
      }
    };

    inputs.add(tree);
    inputs.add(rock);
    inputs.add(iron);

    this.ref.child("Wood").addValueEventListener(new ValueEventListener(){
    
      @Override
      public void onDataChange(DataSnapshot snapshot) {
        Resources.getInstance().setWood(Integer.parseInt(snapshot.child("n").getValue().toString()));
      }
    
      @Override
      public void onCancelled(DatabaseError error) {
        
      }
    });

    this.ref.child("Rock").addValueEventListener(new ValueEventListener(){
    
      @Override
      public void onDataChange(DataSnapshot snapshot) {
        Resources.getInstance().setStone(Integer.parseInt(snapshot.child("n").getValue().toString()));
      }
    
      @Override
      public void onCancelled(DatabaseError error) {
        
      }
    });

    this.ref.child("Iron").addValueEventListener(new ValueEventListener(){
    
      @Override
      public void onDataChange(DataSnapshot snapshot) {
        Resources.getInstance().setIron(Integer.parseInt(snapshot.child("n").getValue().toString()));
      }
    
      @Override
      public void onCancelled(DatabaseError error) {
        
      }
    });

    this.from.addValueEventListener(new ValueEventListener(){
    
      @Override
      public void onDataChange(DataSnapshot snapshot) {
        System.out.println("update\n\n\n"+snapshot.getValue());
        System.out.println("x "+snapshot.child("x").getValue());
        System.out.println("y "+snapshot.child("y").getValue());
        warrior1.setX(Integer.parseInt(snapshot.child("x").getValue().toString()));
        warrior1.setY(Integer.parseInt(snapshot.child("y").getValue().toString()));
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
        warrior2.setX(Integer.parseInt(snapshot.child("x").getValue().toString()));
        warrior2.setY(Integer.parseInt(snapshot.child("y").getValue().toString()));
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
   //ew.addObserver(warrior1);
    panel.getDbg().setColor(Color.white);
    panel.getDbg().drawImage(bg, 0, 0, panel.getPwidth(), panel.getPheight(), null);
    panel.getDbg().fillRect(0, 0, panel.getPwidth(), panel.getPheight());
    panel.getDbg().drawImage(this.getBg(), 0, 0, panel.getWidth(), panel.getHeight(), null);

    Resources.getInstance().draw(panel.getDbg());

    for(Input i: inputs) {
      i.draw(panel.getDbg());
    }
    
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
    bg = GamePanel.resize(bg, panel.getPwidth(), panel.getPheight());
    
	}

  @Override
  public void keyPressed(int keyCode) {
    // warrior1.actions(keyCode);
    data.clear();

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
    if(retador) {
      from.setValueAsync(data);
    } else {
      to.setValueAsync(data);
    }
  }
  public BufferedImage getBg(){
    return bg;
  }
  public int randNumbers(GamePanel panel,int n){ //metodo que genera numeros aleatorios para la generación de recursos
    if(n==1){
      int numero = (int) (Math.random() * panel.getPwidth()) + 1;
    return numero;
    }
    else{
      int numero = (int) (Math.random() * panel.getPheight()) + 1;
      return numero; 
    }
}
}