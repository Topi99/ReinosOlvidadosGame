package org.team.project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import javax.swing.JPanel;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import org.team.project.state.LoggedOutState;
import org.team.project.state.StatePanelCtx;

public class GamePanel extends JPanel implements Runnable {
  private static final long serialVersionUID = 1L;
  private int PWIDTH = 500;
  private int PHEIGHT = 400;
  private Dimension screnDimension;

  private Thread animator;
  private volatile boolean running = false;
  private volatile boolean gameOver = false;
  private volatile boolean isPaused = false;

  private Graphics dbg;
  private Image dbImage = null;

  private StatePanelCtx panelCtx;
  private FileInputStream serviceAccount;
  private static GamePanel instance = null;

  //private Fondo fondo1;
  // private URL url= getClass().getResource("map (2).png");
  // private Image image= new ImageIcon(url).getImage();
  public GamePanel() {
    try {
      URL file = this.getClass().getResource("../../../chatdemo-43f97-firebase-adminsdk-z5inm-7461052f81.json");
      serviceAccount = new FileInputStream(file.getPath());
  
      FirebaseOptions options = new FirebaseOptions.Builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .setDatabaseUrl("https://chatdemo-43f97.firebaseio.com/")
        .build();
  
      FirebaseApp.initializeApp(options);
    } catch(FileNotFoundException e) {
      System.out.println("File not found: "+e.getMessage());
    } catch(IOException e) {
      System.out.println("IO: "+e.getMessage());
    }

    screnDimension = Toolkit.getDefaultToolkit().getScreenSize();
    PWIDTH = (int)screnDimension.getWidth();
    PHEIGHT = (int)screnDimension.getHeight();

    instance = this;
    
    this.panelCtx = new StatePanelCtx();
    setBackground(Color.white);
    setSize(new Dimension(PWIDTH, PHEIGHT));
    setFocusable(true);
    requestFocus();
    readyForTermination();
    this.panelCtx.setStatePanel(new LoggedOutState());
    this.panelCtx.getStatePanel().addElements(this);

    addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        panelCtx.getStatePanel().checkInputs(e.getX(), e.getY());
      }
    });
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
    while (true) {
      gameUpdate();

      this.panelCtx.getStatePanel().gameRender(this);
      paintScreen();

      try {
        Thread.sleep(30);
      } catch (InterruptedException ex) {
        System.out.println(ex.toString());
      }
    }
  } // run()

  private void gameUpdate() {
    if (!isPaused && !gameOver) {

    }
  } // gameUpdate()
  // public Image getImage(){
  //   return image;
  // }
  
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (dbImage != null) {
      g.drawImage(dbImage, 0, 0, null);
    }
  } // paintComponent()

  private void readyForTermination() {
    addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        if ((e.getModifiers() & ActionEvent.CTRL_MASK) == ActionEvent.CTRL_MASK) {
          System.out.println("CTRL KEY PRESSED");
        }
        panelCtx.getStatePanel().keyPressed(e.getKeyCode());
      }
    });
  } // readyForTermination()
  
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

  public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

    Graphics2D g2d = dimg.createGraphics();
    g2d.drawImage(tmp, 0, 0, null);
    g2d.dispose();

    return dimg;
}  

  /**
   * @param dbg the dbg to set
   */
  public void setDbg(Graphics dbg) {
    this.dbg = dbg;
  }

  /**
   * @return the dbg
   */
  public Graphics getDbg() {
    return dbg;
  }

  /**
   * @return the dbImage
   */
  public Image getDbImage() {
    return dbImage;
  }

  /**
   * @param dbImage the dbImage to set
   */
  public void setDbImage(Image dbImage) {
    this.dbImage = dbImage;
  }

  /**
   * @return the pheight
   */
  public int getPheight() {
    return PHEIGHT;
  }

  /**
   * @return the pwidth
   */
  public int getPwidth() {
    return PWIDTH;
  }

  /**
   * @return the panelCtx
   */
  public StatePanelCtx getPanelCtx() {
    return panelCtx;
  }

  /**
   * @return the instance
   */
  public static GamePanel getInstance() {
    return instance;
  }
}
