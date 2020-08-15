# A Zombie Shooter Game

## Shoot shoot  shoot!

A 2D zombie shooter. Shoot them zombies down and stay alive for as long as possible! 

**Mechanics:**
- WASD keys to fire
- Arrow keys to move
- Zombies chasing you
- A gun with unlimited ammunition

My program is to be used (played) by those looking for a quick stress relief where they can maul down zombies.
This project is of interest to me as I've always wanted to 
experience what it is like to be developing my own game. It may seem cliche but I think that a game like this is quite
similar to more practical systems in that it helps me work with UI and experiment with what would work best for a user. 
Working on such a large project also teaches me how to work large projects with discipline and by myself. This has 
overall been a valuable learning experience even though it took a very long time and costed me many hairs pulled out of 
my head.  

Note: Please regrade my Phase3!

## User Stories:
- I want to move my hero (blue square) using the arrow keys
- I want my hero to be able to shoot using the WASD keys
- I want zombies (red circles) to chase my hero
- I want zombies to die when shot at
- I want my hero to die when enough zombies catch up with my hero.

Because I revamped my entire project into this type of game, I accidentally made the mistake of realising too late that 
there is actually nothing to save. To actually have useful information to save I have to implement a ton of new features 
in my game like new weapons, levels, different characters and zombies and that's just way too much. Therefore I'm unable 
to complete this last task of implementing save/load functions. Hopefully the scale of my project makes up for this and 
I don't get penalised too much. Have fun marking my work!
- I want the option to be able to save my gamedata when I select quit from the game menu
- I want to be able to resume the game from where I left off when I last quit 

###Phase 4: Task 2
For task 2 I've already previously implemented a type hierarchy where the abstract class sprite represented any mobile 
entity in my game. Therefore my superclass is sprite, and the subclasses that extend it are Hero, Bullet, and Zombie and 
they each have a different implementation of the abstract method move() that allows objects of that class to move in the 
game. 

###Phase 4: Task 3
Problems:
1. Hero, Bullet, Zombie have similar method: move().
2. GamePanel calling 3 similar drawX() method, where X is the subclass name.
3. Hero, Bullet, Zombie share the same fields (xpos, ypos, sizeW, sizeH, colour, dx, dy).
4. SGame class calling 3 similar move() methods (chase() in the case of Zombies).

Solutions:
I didn't actually solve these problems during phase 4. I started my project with these problems in mind so instead of 
starting with three different classes (Hero, Bullet, Zombie), I began with the Sprite superclass. Therefore I was able 
to tackle problems 1 and 3. 

For problems 2 and 4 however, even though Zombie and Bullet have the same supertype Sprite, I can't refactor 2 lists of 
Zombies and Bullets into an encompassing list of Sprites as they call different move() methods. Bullets call the regular 
move() and zombies call chase(params) which calls the abstract method move(). As the zombie's movements are determined 
by the position of the player, I can't call Override the Sprite's abstract move() method to implement the zombie's 
movements and I need to pass in parameters to calculate the zombie's movements. Because I can't refactor the list of 
zombies and list of bullets into an encompassing list of sprites, this also prevents me from refactoring the three 
similar drawX() methods. I can make a list of sprites that just included the bullets and a refernece to the hero however
this is not worth it as there would still be two separate lists that need to be iterated over: sprites and zombies, and 
this is just a redundant change.
