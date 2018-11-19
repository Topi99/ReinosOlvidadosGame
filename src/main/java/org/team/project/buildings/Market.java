package org.team.project.buildings;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.team.project.GamePanel;
import org.team.project.inputs.Button;
import org.team.project.inputs.Input;
import org.team.project.state.InfoStatePanel;
import org.team.project.state.StatePanel;

public class Market extends Button {
  private BufferedImage bg;
  private GamePanel panel;
  private StatePanel city;

  public Market(int x, int y, int width, int height, GamePanel panel) {
    super(x, y, width, height);
    
    try {
      bg = ImageIO.read(this.getClass().getResource("../../../../market.png"));
    } catch(Exception e) {}
    
    bg = GamePanel.resize(bg, panel.getPwidth()/3+50, panel.getPheight()/3+30);

    this.x = panel.getPwidth()/2 - bg.getWidth()/5;
    this.y = panel.getPheight() - bg.getHeight()-50;
    this.width = bg.getWidth();
    this.height = bg.getHeight();

    this.rect = new Rectangle(this.x, this.y, this.width, this.height);
    this.panel = panel;
    this.city = panel.getPanelCtx().getStatePanel();
  }

  public void call() {
    this.active = true;
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
        panel.getDbg().drawString("Market", panel.getPwidth()/2 - this.getWidth()/2 + 30, 70);
        
        for(Input i: this.getInputs()) {
          i.draw(panel.getDbg());
        }
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
        this.addInput(close);
      }
    });
    panel.getPanelCtx().getStatePanel().addElements(panel);
  }
  
  public void draw(Graphics g) {
    g.drawImage(bg, x, y, width, height, null);
  }
}