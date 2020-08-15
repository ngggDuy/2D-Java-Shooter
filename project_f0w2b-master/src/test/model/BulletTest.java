package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BulletTest {
    private Bullet singleBullet;
    private List<Bullet> bullets = new ArrayList<>();

    @BeforeEach
    public void runBefore() {
        singleBullet = new Bullet(50, 50);
    }

    @Test
    public void testConstructor() {
        assertEquals(new Color(0xFFDC2C), singleBullet.colour);
    }

    @Test
    public void testInitialShot() {
        // bullet not fired
        singleBullet.shootBulletInDirection(Hero.DirectionFacing.NORTH);
        assertEquals(-5, singleBullet.dy);
        assertEquals(0, singleBullet.dx);
    }

    @Test
    public void testMoveNorth() {
        singleBullet.shootBulletInDirection(Hero.DirectionFacing.NORTH);
        // bullet fired
        singleBullet.move();
        assertEquals(50, singleBullet.getXpos());
        assertEquals(45, singleBullet.getYpos());
    }

    @Test
    public void testMoveSouth() {
        singleBullet.shootBulletInDirection(Hero.DirectionFacing.SOUTH);
        // bullet fired
        singleBullet.move();
        assertEquals(50, singleBullet.getXpos());
        assertEquals(55, singleBullet.getYpos());
    }

    @Test
    public void testMoveEast() {
        singleBullet.shootBulletInDirection(Hero.DirectionFacing.EAST);
        // bullet fired
        singleBullet.move();
        assertEquals(55, singleBullet.getXpos());
        assertEquals(50, singleBullet.getYpos());
    }

    @Test
    public void testMoveWest() {
        singleBullet.shootBulletInDirection(Hero.DirectionFacing.WEST);
        // bullet fired
        singleBullet.move();
        assertEquals(45, singleBullet.getXpos());
        assertEquals(50, singleBullet.getYpos());
    }


}
