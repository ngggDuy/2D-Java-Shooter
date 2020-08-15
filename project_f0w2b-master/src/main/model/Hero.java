package model;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/*
 * Represents the player character
 */
public class Hero extends Sprite {
    // enum represents direction player is facing
    public enum DirectionFacing {
        NORTH, EAST, SOUTH, WEST
    }

    public DirectionFacing direction;

    /*
     * MODIFIES: this
     * EFFECTS: initialises hero facing north
     */
    public Hero(int x, int y) {
        super(x, y, 20, 20, new Color(25,26, 255));
        this.health = 20;
        direction = DirectionFacing.NORTH;
    }

    public DirectionFacing getDirection() {
        return direction;
    }

    @Override
    /* MODIFIES: this
     * EFFECTS: moves players xpos and ypos by dx and dy respectively
     *          if player is on boundary, keep them in bounds and make dx/dy = 0
     */
    public void move() {
        xpos += dx;
        ypos += dy;
        if ((getCenterX() + (getSizeW() / 2))  > SGame.WIDTH) { // EAST
            xpos = SGame.WIDTH - getSizeW();
            dx = 0;
        } else if ((getCenterX() - (getSizeW() / 2)) < 1) { // WEST
            xpos = 1;
            dx = 0;
        } else if ((getCenterY() - (getSizeH() / 2)) < 1) { // NORTH
            ypos = 1;
            dy = 0;
        } else if ((getCenterY() + (getSizeH() / 2)) > SGame.HEIGHT) { // SOUTH
            ypos = SGame.HEIGHT - getSizeH();
            dy = 0;
        }
    }

    // MODIFIES: this
    // EFFECTS: reduces player health by 2
    public void shotAt() {
        health -= 2;
    }
}
