package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ZombieTest {
    private Zombie zombie;
    private List<Zombie> zombies;

    @BeforeEach
    public void runBefore() {
        zombie = new Zombie(20, 20);
        zombies = new ArrayList<>();
    }

    @Test
    public void testChaseRight() {
        zombies.add(new Zombie(50,50)); // does not satisfy condition
        zombie.chase(100, 10, zombies); // player on RIGHT
        assertEquals(2, zombie.dx);
        assertEquals(22, zombie.getXpos());
        zombies.remove(0);
        zombies.add(new Zombie(25, 25)); // satisfies condition
        zombie.chase(100, 10, zombies);
        assertEquals(0, zombie.dx);
        assertEquals(22, zombie.getXpos());
    }

    @Test
    public void testChaseLeft() {
        zombies.add(new Zombie(50,50)); // does not satisfy condition
        zombie.chase(10, 10, zombies); // player on LEFT
        assertEquals(-2, zombie.dx);
        assertEquals(18, zombie.getXpos());
        zombies.remove(0);
        zombies.add(new Zombie(15, 25)); // satisfies condition
        zombie.chase(10, 10, zombies);
        assertEquals(0, zombie.dx);
        assertEquals(18, zombie.getXpos());
    }

    @Test
    public void testChaseUp() {
        zombies.add(new Zombie(50,50)); // does not satisfy condition
        zombie.chase(100, 1, zombies); // player on TOP
        assertEquals(-2, zombie.dy);
        assertEquals(18, zombie.getYpos());
        zombies.remove(0);
        zombies.add(new Zombie(25, 25)); // satisfies condition
        zombie.chase(100, 1, zombies);
        assertEquals(0, zombie.dy);
        assertEquals(18, zombie.getYpos());
    }

    @Test
    public void testChaseDown() {
        zombies.add(new Zombie(50,50)); // does not satisfy condition
        zombie.chase(100, 100, zombies); // player on BOTTOM
        assertEquals(2, zombie.dy);
        assertEquals(22, zombie.getYpos());
        zombies.remove(0);
        zombies.add(new Zombie(25, 15)); // satisfies condition
        zombie.chase(100, 100, zombies);
        assertEquals(0, zombie.dy);
        assertEquals(22, zombie.getYpos());
    }

    @Test
    public void testCaughtUp() {
        zombies.add(new Zombie(50,50));
        zombie.chase(35,35, zombies);
        assertEquals(0, zombie.dx);
        assertEquals(20, zombie.getXpos());
        assertEquals(0, zombie.dy);
        assertEquals(20, zombie.getYpos());
    }

}
