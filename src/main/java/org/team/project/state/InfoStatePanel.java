package org.team.project.state;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.team.project.GamePanel;
import org.team.project.inputs.Input;

public class InfoStatePanel implements StatePanel {
  private BufferedImage bg;
  private int width, height;
  private ArrayList<Input> inputs = new ArrayList<Input>();

  public InfoStatePanel(GamePanel panel) {
    try {
      bg = ImageIO.read(this.getClass().getResource("../../../../panel.png"));
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }

    this.width = (panel.getWidth()/3)*2;
    this.height = panel.getHeight();
    
    this.bg = GamePanel.resize(this.bg, width, height);
  }

  /**
   * @param inputs the inputs to set
   */
  public void setInputs(ArrayList<Input> inputs) {
    this.inputs = inputs;
  }

  public void addInput(Input input) {
    this.inputs.add(input);
  }

  /**
   * @return the inputs
   */
  public ArrayList<Input> getInputs() {
    return inputs;
  }

  /**
   * @return the width
   */
  public int getWidth() {
    return width;
  }

  /**
   * @return the height
   */
  public int getHeight() {
    return height;
  }
  
  @Override
  public void gameRender(GamePanel panel) {
    
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

  }

  @Override
  public void keyPressed(int keyCode) {
		
  }
  
  /**
   * @return the bg
   */
  public BufferedImage getBg() {
    return bg;
  }

}