package ui;

import model.SGame;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.*;

/* Represents window in which game is played
*  Code from SpaceInvadersBase
* */

public class ShooterGame extends JFrame {

    private static final int INTERVAl = 20;
    private SGame game;
    private GamePanel gp;
    private ScorePanel sp;

    // EFFECTS: setting up main game window
    public ShooterGame() {
        super("Shooter Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        game = new SGame();
        gp = new GamePanel(game);
        sp = new ScorePanel(game);
        add(gp);
        add(sp, BorderLayout.NORTH);
        addKeyListener(new KeyHandler());
        pack();
        centreOnScreen();
        setVisible(true);
        addTimer();
    }

    // Centres frame on desktop
    // modifies: this
    // effects:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    // Set up timer
    // MODIFIES: none
    // EFFECTS:  initializes a timer that updates game each
    //           INTERVAL milliseconds
    private void addTimer() {
        Timer t = new Timer(INTERVAl, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                game.update();
                gp.repaint();
                sp.update();
            }
        });
        t.start();
    }

    /*
     * A key handler to respond to key events
     *
     */
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e)  {
            game.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            game.keyReleased(e);
        }
    }


    /*
     * Initialising game
     */
    public static void main(String[] args) {
        new ShooterGame();
    }


}
