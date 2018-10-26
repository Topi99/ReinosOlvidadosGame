package org.team.project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
  private static final long serialVersionUID = 1L;
  private static final int PWIDTH = 500;
  private static final int PHEIGHT = 400;

  private Thread animator;
  private volatile boolean running = false;
  private volatile boolean gameOver = false;
  private volatile boolean isPaused = false;
  private Circle cir;

  public GamePanel() {
    setBackground(Color.white);
    setSize(new Dimension(PWIDTH, PHEIGHT));
    setFocusable(true);
    requestFocus();
    readyForTermination();

    cir = new Circle(0, 0);
  } // GamePanel()

  public void addNotify() {
    super.addNotify();
    startGame();
  } // addNotify()

  private void startGame() {
    if (animator == null || !running) {
      animator = new Thread(this);
      animator.start();
    }
  } // startGame()

  public void stopGame() {
    running = false;
  } // stopGame()

  public void run() {
    running = true;
    while (running) {
      gameUpdate();

      gameRender();
      paintScreen();

      try {
        Thread.sleep(50);
      } catch (InterruptedException ex) {
        System.out.println(ex.toString());
      }
    }
    System.exit(0);
  } // run()

  private void gameUpdate() {
    if (!isPaused && !gameOver) {

    }
  } // gameUpdate()

  private Graphics dbg;
  private Image dbImage = null;

  private void gameRender() {
    if (dbImage == null) {
      dbImage = createImage(PWIDTH, PHEIGHT);
      if (dbImage == null) {
        System.out.println("dbImage is null");
        return;
      } else {
        dbg = dbImage.getGraphics();
      }
    }
    dbg.setColor(Color.white);
    dbg.fillRect(0, 0, PWIDTH, PHEIGHT);
    cir.draw(dbg);
  } // gameRender()

  /*
   * private void gameOverMessage() { Graphics g; g = this.getGraphics();
   * g.drawString("Game Over", 10, 10); }
   */

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (dbImage != null) {
      g.drawImage(dbImage, 0, 0, null);
    }
  } // paintComponent()

  private void readyForTermination() {
    addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if ((keyCode == KeyEvent.VK_ESCAPE) || (keyCode == KeyEvent.VK_Q) || (keyCode == KeyEvent.VK_END)
            || ((keyCode == KeyEvent.VK_C) && e.isControlDown())) {
          running = false;
        }
        eval(keyCode);
      }
    });
  } // readyForTermination()

  private void eval(int keyCode) {
    if (keyCode == KeyEvent.VK_RIGHT) {
      cir.setX(20);
      cir.setY(0);
    } else if (keyCode == KeyEvent.VK_LEFT) {
      cir.setX(-20);
      cir.setY(0);
    } else if (keyCode == KeyEvent.VK_UP) {
      cir.setX(0);
      cir.setY(-20);
    } else if (keyCode == KeyEvent.VK_DOWN) {
      cir.setX(0);
      cir.setY(20);
    }
  }

  private void paintScreen() {
    Graphics g;
    try {
      g = this.getGraphics();
      if ((g != null) && (dbImage != null))
        g.drawImage(dbImage, 0, 0, null);
      // Sync the display on some systems.
      // (on Linux, this fixes event queue problems)
      Toolkit.getDefaultToolkit().sync();
      g.dispose();
    } catch (Exception e) {
      System.out.println("Graphics error: " + e);
    }
  } // end of paintScreen()
}
