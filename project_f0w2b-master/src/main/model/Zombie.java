package model;

import java.awt.*;
import java.util.List;

/*
 * Class represents zombies
 */
public class Zombie extends Sprite {
    // MODIFIES: this
    // EFFECTS: initialises zombie
    public Zombie(int x, int y) {
        super(x, y, 30, 30, new Color(200, 0, 6));
        this.health = 10;
    }

    @Override
    // MODIFIES: this
    // EFFECTS: moves zombie by dx and dy
    public void move() {
        xpos += dx;
        ypos += dy;
    }

    // MODIFIES: this
    // EFFECTS: zombies chase after hero at position (x1, y1)
    public void chase(int x1, int y1, List<Zombie> zombies) {
        int distanceToOtherZom = 38;
        if (x1 == this.getCenterX()) { // zombie caught up to player in x position
            dx = 0;
        }
        if (y1 == this.getCenterY()) { // zombie caught up to player in y position
            dy = 0;
        }
        if (x1 > this.getCenterX()) { // chase right
            dx = zombieChaseRight(distanceToOtherZom, zombies);
        }
        if (x1 < this.getCenterX()) { // chase left
            dx = zombieChaseLeft(distanceToOtherZom, zombies);
        }
        if (y1 > this.getCenterY()) { // chase down
            dy = zombieChaseDown(distanceToOtherZom, zombies);
        }
        if (y1 < this.getCenterY()) { // chase up
            dy = zombieChaseUp(distanceToOtherZom, zombies);
        }
        move();
    }

    // REQUIRES: hero x position larger than zombie center
    // MODIFIES: this
    // EFFECTS: makes zombies go right to follow hero and checks if other zombies are too close
    public int zombieChaseRight(int distanceToOtherZom, List<Zombie> zombies) {
        dx = 2;
        for (Zombie zom : zombies) {
            if (Math.abs(getCenterX() - zom.getCenterX()) < distanceToOtherZom && (zom.getCenterX()
                    != getCenterX() && zom.getCenterY() != getCenterY()) && getCenterX() < zom.getCenterX()) {
                dx = 0;
                break;
            }
        }
        return dx;
    }

    // REQUIRES: hero x position smaller than zombie center
    // MODIFIES: this
    // EFFECTS: makes zombie go left to follow hero and checks if other zombies are too close
    public int zombieChaseLeft(int distanceToOtherZom, List<Zombie> zombies) {
        dx = -2;
        for (Zombie zom : zombies) {
            if (Math.abs(getCenterX() - zom.getCenterX()) < distanceToOtherZom && (zom.getCenterX()
                    != getCenterX() && zom.getCenterY() != getCenterY()) && getCenterX() > zom.getCenterX()) {
                dx = 0;
                break;
            }
        }
        return dx;
    }

    // REQUIRES: hero y position larger than zombie center
    // MODIFIES: this
    // EFFECTS: makes zombies go down to follow hero and checks if other zombies are too close
    public int zombieChaseUp(int distanceToOtherZom, List<Zombie> zombies) {
        dy = -2;
        for (Zombie zom : zombies) {
            if (Math.abs(getCenterY() - zom.getCenterY()) < distanceToOtherZom && (zom.getCenterX()
                    != getCenterX() && zom.getCenterY() != getCenterY()) && getCenterY() < zom.getCenterY()) {
                dy = 0;
                break;
            }
        }
        return dy;
    }

    // REQUIRES: hero y smaller larger than zombie center
    // MODIFIES: this
    // EFFECTS: make zombies go up to follow hero and checks if other zombies are too close
    public int zombieChaseDown(int distanceToOtherZom, List<Zombie> zombies) {
        dy = 2;
        for (Zombie zom : zombies) {
            if (Math.abs(getCenterY() - zom.getCenterY()) < distanceToOtherZom && (zom.getCenterX()
                    != getCenterX() && zom.getCenterY() != getCenterY()) && getCenterY() > zom.getCenterY()) {
                dy = 0;
                break;
            }
        }
        return dy;
    }
}
