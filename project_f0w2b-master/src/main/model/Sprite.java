package model;

import javax.swing.*;
import java.awt.*;

/*
 * Represents a mobile entity on the game
 */
public abstract class Sprite {
    // positions of sprite
    protected int xpos;
    protected int ypos;
    // dimensions of sprite
    protected int sizeW;
    protected int sizeH;
    // colour of sprite
    protected Color colour;
    // movement speed of sprite
    protected int dx;
    protected int dy;
    // sprite stats
    protected int health;
    protected int score = 0;

    /*
     * MODIFIES: this
     * EFFECTS: Initialises a sprite at location (x,y) and renders sprite
     */
    public Sprite(int x, int y, int sizeW, int sizeH, Color colour) {
        this.xpos = x;
        this.ypos = y;
        this.sizeW = sizeW;
        this.sizeH = sizeH;
        this.colour = colour;
    }

    // EFFECTS: returns x position of center of sprite
    public int getCenterX() {
        return xpos + (sizeW / 2);
    }

    // EFFECTS: returns y position of center of sprite
    public int getCenterY() {
        return ypos + (sizeH / 2);
    }

    // EFFECTS: returns sprites x position
    public int getXpos() {
        return xpos;
    }

    // EFFECTS: returns sprites x position
    public int getYpos() {
        return ypos;
    }

    // EFFECTS: returns sprites w dimension
    public int getSizeW() {
        return sizeW;
    }

    // EFFECTS: returns sprites h dimension
    public int getSizeH() {
        return sizeH;
    }

    // EFFECTS: returns sprite health
    public int getHealth() {
        return health;
    }

    // EFFECTS: returns score of sprite
    public int getScore() {
        return score;
    }

    // EFFECTS: returns the bounds of the sprite
    public Rectangle getBounds() {
        return new Rectangle(xpos, ypos, sizeW, sizeH);
    }

    public Color getColour() {
        return colour;
    }

    // EFFECTS: returns true if sprite is colliding with other sprites
    public boolean checkCollision(Sprite obj) {
        Rectangle active = new Rectangle(xpos, ypos, sizeW, sizeH);
        Rectangle passive = obj.getBounds();
        return active.intersects(passive);
    }

    // MODIFIES: this
    // EFFECTS: moves the sprite
    public abstract void move();
}
