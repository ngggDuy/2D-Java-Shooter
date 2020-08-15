package ui;

import model.Bullet;
import model.Hero;
import model.SGame;
import model.Zombie;

import javax.swing.*;
import java.awt.*;

// panel to render game
public class GamePanel extends JPanel {
    private static final String OVER = "Game Over!";
    private static final String REPLAY = "R to replay";
    private SGame game;

    // EFFECTS: sets size and background colour of panel, updates this with the game to be displayed
    public GamePanel(SGame g) {
        setPreferredSize(new Dimension(SGame.WIDTH, SGame.HEIGHT));
        setBackground(Color.LIGHT_GRAY);
        this.game = g;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGame(g);
        if (game.isOver()) {
            gameOver(g);
        }
    }

    // MODIFIES: g
    // EFFECTS:  draws the game onto g
    private void drawGame(Graphics g) {
        drawHero(g);
        drawZombies(g);
        drawBullets(g);
    }

    // MODIFIES: g
    // EFFECTS:  draws the tank onto g
    private void drawHero(Graphics g) {
        Hero hero = game.getHero();
        Color savedCol = g.getColor();
        g.setColor(hero.getColour());
        g.fillRect(hero.getXpos() - hero.getSizeW() / 2, hero.getYpos() - hero.getSizeH() / 2,
                hero.getSizeW(), hero.getSizeH());
        g.setColor(savedCol);
    }

    // MODIFIES: g
    // EFFECTS:  draws the zombies onto g
    private void drawZombies(Graphics g) {
        for (Zombie next : game.getInvaders()) {
            drawInvader(g, next);
        }
    }

    // MODIFIES: g
    // EFFECTS:  draws the zombie z onto g
    private void drawInvader(Graphics g, Zombie z) {
        Color savedCol = g.getColor();
        g.setColor(z.getColour());
        g.fillOval(z.getXpos() - z.getSizeW() / 2, z.getYpos() - z.getSizeH() / 2, z.getSizeW(), z.getSizeH());
        g.setColor(savedCol);
    }


    // MODIFIES: g
    // EFFECTS:  draws the missiles onto g
    private void drawBullets(Graphics g) {
        for (Bullet next : game.getBullets()) {
            drawMissile(g, next);
        }
    }

    // MODIFIES: g
    // EFFECTS:  draws the missile m onto g
    private void drawMissile(Graphics g, Bullet b) {
        Color savedCol = g.getColor();
        g.setColor(b.getColour());
        g.fillOval(b.getXpos() - b.getSizeW() / 2, b.getYpos() - b.getSizeH() / 2, b.getSizeW(), b.getSizeH());
        g.setColor(savedCol);
    }


    // modifies: g
    // effects:  draws "game over" and replay instructions onto g
    private void gameOver(Graphics g) {

        Color saved = g.getColor();
        g.setColor(new Color(0, 0, 0));
        g.setFont(new Font("Arial", 20, 20));
        FontMetrics fm = g.getFontMetrics();
        centreString(OVER, g, fm, SGame.HEIGHT / 2);
        centreString(REPLAY, g, fm, SGame.HEIGHT / 2 + 50);
        g.setColor(saved);
    }

    // Centres a string on the screen
    // modifies: g
    // effects:  centres the string str horizontally onto g at vertical position yPos
    private void centreString(String str, Graphics g, FontMetrics fm, int y) {
        int width = fm.stringWidth(str);
        g.drawString(str, (SGame.WIDTH - width) / 2, y);
    }
}

