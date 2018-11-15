package org.team.project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import javax.swing.JPanel;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
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

  public GamePanel() {
    try {
      URL file = this.getClass().getResource("chatdemo-43f97-firebase-adminsdk-z5inm-7461052f81.json");
      serviceAccount = new FileInputStream(file.getPath());
  
      FirebaseOptions options = new FirebaseOptions.Builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .setDatabaseUrl("https://chatdemo-43f97.firebaseio.com/")
        .build();
  
      FirebaseApp.initializeApp(options);
    } catch(FileNotFoundException e) {
      System.out.println(e.getMessage());
    } catch(IOException e) {
      System.out.println(e.getMessage());
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }

    screnDimension = Toolkit.getDefaultToolkit().getScreenSize();
    PWIDTH = (int)screnDimension.getWidth();
    PHEIGHT = (int)screnDimension.getHeight();
    
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

    try {
      GoogleCredential googleCred = GoogleCredential.fromStream(serviceAccount);

      // Add the required scopes to the Google credential
      GoogleCredential scoped = googleCred.createScoped(
          Arrays.asList(
            "https://www.googleapis.com/auth/firebase.database",
            "https://www.googleapis.com/auth/userinfo.email"
          )
      );
      
      // Use the Google credential to generate an access token
      scoped.refreshToken();
      String token = scoped.getAccessToken();
      System.out.println("token "+token);  
    } catch(Exception e) {
      System.out.println("Error: "+e.getMessage());      
    }
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
        Thread.sleep(40);
      } catch (InterruptedException ex) {
        System.out.println(ex.toString());
      }
    }
  } // run()

  private void gameUpdate() {
    if (!isPaused && !gameOver) {

    }
  } // gameUpdate()

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
}
