package ui;

import model.SGame;

import javax.swing.*;
import java.awt.*;

// Panel renders score panel
public class ScorePanel extends JPanel {
    private static final String ZOMBIES_TXT = "CIRCLES KILLED: ";
    private static final int LBL_WIDTH = 200;
    private static final int LBL_HEIGHT = 30;
    private SGame game;
    private JLabel zombiesLabel;

    // Constructs a score panel
    // effects: sets the background colour and draws the initial labels;
    //          updates this with the game whose score is to be displayed
    public ScorePanel(SGame g) {
        game = g;
        setBackground(new Color(175, 180, 138));
        zombiesLabel = new JLabel(ZOMBIES_TXT + game.getNumZombiesKilled());
        zombiesLabel.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        add(zombiesLabel);
        add(Box.createHorizontalStrut(10));
    }

    // Updates the score panel
    // modifies: this
    // effects:  updates number of invaders shot and number of missiles
    //           remaining to reflect current state of game
    public void update() {
        zombiesLabel.setText(ZOMBIES_TXT + game.getNumZombiesKilled());
        repaint();
    }

}
