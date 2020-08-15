package model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * Represents a zombie shooter game.
 */
public class SGame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int SPAWN_TIME = 200; // 8 zombies spawns every 100 ticks
    private static final Random RND = new Random();

    private Hero hero;
    private boolean isGameOver;
    private int zombiesKilled;
    private List<Zombie> zombies;
    private List<Bullet> bullets;

    // Constructs a zombie shooter game
    // EFFECTS: creates empty lists of zombies and bullets and places hero at window centre
    public SGame() {
        zombies = new ArrayList<>();
        bullets = new ArrayList<>();
        setUp();
    }

    // MODIFIES: this
    // EFFECTS: evaluates the key pressed
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_R && isGameOver) {
            setUp();
        } else if (key == KeyEvent.VK_X) {
            System.exit(0);
        } else {
            heroControl(key);
        }
    }

    // EFFECTS: moves player if arrow keys are pressed
    //          changes player direction and shoots if WASD keys are pressed
    private void heroControl(int key) {
        if (key == KeyEvent.VK_LEFT) {
            hero.dx = -4;
        } else if (key == KeyEvent.VK_RIGHT) {
            hero.dx = 4;
        } else if (key == KeyEvent.VK_UP) {
            hero.dy = -4;
        } else if (key == KeyEvent.VK_DOWN) {
            hero.dy = 4;
        } else if (key == KeyEvent.VK_W) {
            hero.direction = Hero.DirectionFacing.NORTH;
            shoot();
        } else if (key == KeyEvent.VK_A) {
            hero.direction = Hero.DirectionFacing.WEST;
            shoot();
        } else if (key == KeyEvent.VK_S) {
            hero.direction = Hero.DirectionFacing.SOUTH;
            shoot();
        } else if (key == KeyEvent.VK_D) {
            hero.direction = Hero.DirectionFacing.EAST;
            shoot();
        }
    }


    // MODIFIES: this
    // EFFECTS: stop player movement in current direction if key is released.
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            hero.dx = 0;
        } else if (key == KeyEvent.VK_RIGHT) {
            hero.dx = 0;
        } else if (key == KeyEvent.VK_UP) {
            hero.dy = 0;
        } else if (key == KeyEvent.VK_DOWN) {
            hero.dy = 0;
        }
    }

    // MODIFIES: this
    // EFFECTS: clears list of bullets and invaders, initialises hero
    private void setUp() {
        zombies.clear();
        bullets.clear();
        hero = new Hero((WIDTH / 2), (HEIGHT / 2));
        isGameOver = false;
        zombiesKilled = 0;
    }

    // MODIFIES: this
    // EFFECTS: updates hero, bullets, and zombies
    public void update() {
        moveBullets();
        moveZombies();
        hero.move();

        checkBullets();
        spawn(); // very random spawning, not incremental so is quite rudimentary
        checkBites();
        checkCollisions();
        checkGameOver();
    }


    // MODIFIES: this
    // EFFECTS: adds 8 zombies on average every 100 ticks
    private void spawn() {
        List<Zombie> zombiesToSpawn = new ArrayList<>();
        zombiesToSpawn.add(new Zombie(RND.nextInt(SGame.WIDTH), 0));
        zombiesToSpawn.add(new Zombie(RND.nextInt(SGame.WIDTH), 0));
        zombiesToSpawn.add(new Zombie(0, RND.nextInt(SGame.HEIGHT)));
        zombiesToSpawn.add(new Zombie(0, RND.nextInt(SGame.HEIGHT)));
        if (RND.nextInt(SPAWN_TIME) < 1) {
            zombies.addAll(zombiesToSpawn);
        }
    }

    // MODIFIES: this
    // EFFECTS: fires bullet, add fired bullet to list of bullets
    public void shoot() {
        Bullet beingShot = new Bullet(hero.getCenterX(), hero.getCenterY());
        beingShot.shootBulletInDirection(hero.getDirection());
        beingShot.hasBeenFired();
        bullets.add(beingShot);
    }

    // MODIFIES: this
    // EFFECTS: moves all bullets in their current direction.
    private void moveBullets() {
        for (Bullet b : bullets) {
            b.move();
        }
    }

    // MODIFIES: this
    // EFFECTS: makes zombies chase
    private void moveZombies() {
        for (Zombie z : zombies) {
            z.chase(hero.xpos, hero.ypos, zombies);
        }
    }

    // MODIFIES: this
    // EFFECTS:  removes any bullets that has travelled out of the screen
    private void checkBullets() {
        List<Bullet> bulletsOutOfBound = new ArrayList<>();
        for (Bullet next : bullets) {
            if (next.getXpos() > WIDTH) {
                bulletsOutOfBound.add(next);
            } else if (next.getXpos() < 1) {
                bulletsOutOfBound.add(next);
            } else if (next.getYpos() > HEIGHT) {
                bulletsOutOfBound.add(next);
            } else if (next.getYpos() < 1) {
                bulletsOutOfBound.add(next);
            }
        }
        bullets.removeAll(bulletsOutOfBound);
    }

    // MODIFIES: this
    // EFFECTS: removes any zombies that has been hit with a bullet as
    //          as well as that corresponding bullet
    private void checkCollisions() {
        List<Zombie> zombiesToRemove = new ArrayList<>();
        List<Bullet> bulletsToRemove = new ArrayList<>();
        for (Zombie check : zombies) {
            if (checkZombieHit(check, bulletsToRemove)) {
                zombiesToRemove.add(check);
            }
        }
        zombies.removeAll(zombiesToRemove);
        bullets.removeAll(bulletsToRemove);
    }

    // EFFECTS: returns true if bullet collided with zombie, increment zombies killed, and
    //          add hit bullet to bulletsToRemove. Ow false
    private boolean checkZombieHit(Zombie check, List<Bullet> bulletsToRemove) {
        for (Bullet next : bullets) {
            if (check.checkCollision(next)) {
                bulletsToRemove.add(next);
                zombiesKilled++;
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: if a zombie has touched the hero, game is marked as over and the board is cleared
    private void checkGameOver() {
        if (hero.getHealth() == 0) {
            isGameOver = true;
        }
        if (isGameOver) {
            zombies.clear();
            bullets.clear();
        }
    }

    // MODIFIES: this
    // EFFECTS: If zombie touches hero, hero's health is deducted
    private void checkBites() {
        for (Zombie zom : zombies) {
            if (hero.checkCollision(zom)) {
                hero.shotAt();
            }
        }
    }

    // EFFECTS: returns the value of isGameOver
    public boolean isOver() {
        return isGameOver;
    }

    public Hero getHero() {
        return hero;
    }

    public List<Zombie> getInvaders() {
        return zombies;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public int getNumZombiesKilled() {
        return zombiesKilled;
    }
}
