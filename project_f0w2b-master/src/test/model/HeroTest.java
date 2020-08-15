package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeroTest {
    private Hero hero;

    @BeforeEach
    public void runBefore() {
        hero = new Hero(400, 300);
        hero.dx = 4;
        hero.dy = 4;
    }

    @Test
    public void testConstruct() {
        assertEquals(Hero.DirectionFacing.NORTH, hero.getDirection());
        assertEquals(400, hero.getXpos());
        assertEquals(300, hero.getYpos());
        assertEquals(20, hero.getSizeH());
        assertEquals(20, hero.getSizeW());
        assertEquals(20, hero.getHealth());
        assertEquals(0, hero.getScore());
    }

    @Test
    public void testMoveNormal() {
        hero.move();
        assertEquals(404, hero.getXpos());
        assertEquals(304, hero.getYpos());
    }

    @Test
    public void testMoveBoundaryEast() {
        hero = new Hero(SGame.WIDTH, 300);
        hero.dx = 4;
        hero.dy = 4;
        hero.move();
        assertEquals(780, hero.getXpos());
        assertEquals(304, hero.getYpos());
        assertEquals(0, hero.dx);
    }

    @Test
    public void testMoveBoundaryWest() {
        hero = new Hero(1, 300);
        hero.dx = -4;
        hero.dy = 4;
        hero.move();
        assertEquals(1, hero.getXpos());
        assertEquals(304, hero.getYpos());
        assertEquals(0, hero.dx);
    }

    @Test
    public void testMoveBoundaryNorth() {
        hero = new Hero(300, 1);
        hero.dx = 4;
        hero.dy = -4;
        hero.move();
        assertEquals(304, hero.getXpos());
        assertEquals(1, hero.getYpos());
        assertEquals(0, hero.dy);
    }

    @Test
    public void testMoveBoundarySouth() {
        hero = new Hero(300, SGame.HEIGHT);
        hero.dx = 4;
        hero.dy = 4;
        hero.move();
        assertEquals(304, hero.getXpos());
        assertEquals(580, hero.getYpos());
        assertEquals(0, hero.dy);
    }
}
