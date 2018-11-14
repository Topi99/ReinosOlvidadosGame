package org.team.project.inputs;

import java.awt.Color;

public class Button extends Input {
  private String message;
  
  public Button(int x, int y, int width, int height) {
    super(x, y, width, height);
  }

  public Button(int x, int y, int width, int height, Color color) {
    super(x, y, width, height, color);
  }

  public Button(int x, int y, int width, int height, Color color, String message) {
    super(x, y, width, height, color);

    this.message = message;
  }
}