package org.team.project.state;

import org.team.project.GamePanel;

public class StatePanelCtx implements StatePanel {
  private StatePanel statePanel;

  @Override
  public void gameRender(GamePanel panel) {
		this.statePanel.gameRender(panel);
  }
  
  /**
   * @param statePanel the statePanel to set
   */
  public void setStatePanel(StatePanel statePanel) {
    this.statePanel = statePanel;
  }

  /**
   * @return the statePanel
   */
  public StatePanel getStatePanel() {
    return statePanel;
  }
}