package model;

import java.awt.*;

// Class represents bullet fired by hero in game
public class Bullet extends Sprite {
    protected boolean hasShot;

    // MODIFIES: this
    // EFFECTS: creates a bullet
    public Bullet(int x, int y) {
        super(x, y, 7, 7, new Color(0xFFDC2C));
        hasShot = false;
    }

    @Override
    // MODIFIES: this
    // EFFECTS: move bullet by dx and dy
    public void move() {
        xpos += dx;
        ypos += dy;
    }

    // MODIFIES: this
    // EFFECTS: fires bullets in direction of hero
    public void shootBulletInDirection(Hero.DirectionFacing d) {
        switch (d) {
            case NORTH:
                dy = (-1) * 10;
                break;
            case EAST:
                dx = 10;
                break;
            case SOUTH:
                dy = 10;
                break;
            case WEST:
                dx = (-1) * 10;
                break;
        }
    }

    // EFFECTS: sets hasShot to true
    public void hasBeenFired() {
        hasShot = true;
    }
}
