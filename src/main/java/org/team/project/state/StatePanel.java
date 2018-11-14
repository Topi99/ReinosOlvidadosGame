package org.team.project.state;

import org.team.project.GamePanel;

public interface StatePanel {
  public void gameRender(GamePanel panel);
  public void checkButtons(int x, int y);
  public void addElements(GamePanel panel);
}