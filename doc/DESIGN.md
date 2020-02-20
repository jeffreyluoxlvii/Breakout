# Game Design Final
### Names
Jeffrey Luo, Roy Xiong

## Team Roles and Responsibilities

 * Team Member #1--Jeffrey Luo  
    Jeffrey worked on the Ball, Brick, CollisionManager, Powerups, Scorer,  
GameManager, and some of the Unit Tests. Overall, Jeffrey worked on design  
and implementation.

 * Team Member #2--Roy Xiong
    Roy worked on all the other classes, and wrote some of the Unit Tests.
Overall, Roy worked on design and implementation as well.


## Design goals
   
#### What Features are Easy to Add
 We wanted to make Balls be easily built upon. One specific example of keeping flexibility in mind is the implementation of the hit() in the Brick superclass. By making the method take a parameter, we could easily create a Powerup that makes the ball deal more damage to bricks. We also wanted to make it easy to add new levels, and to make different kinds of bricks and powerups. 

## High-level Design
#### Core Classes
Overall, we tried to keep the "Model" and the "View" separate. We did this by  
creating the Brick, Ball, Platform, etc. classes (model), whereas the  BreakoutGame class represented the "View". Also we tried to take advantage of  
making inheritance hierarchies to avoid duplicated code and other code smells.
Some inheritance hierarchies we made were the Brick, Powerup, and NonLevelScreens.

Regarding interaction, the LevelCreator class gets the level, which the GameManager  
keeps track of. All the statistics held by the GameManager class are displayed  
by the BreakoutGame class. The BreakoutGame class displays the current state of  
the game.
## Assumptions that Affect the Design

#### Features Affected by Assumptions
We assumed that the levels came in a specific file format, with specific numbers  
specifying different brick types.
We also generally assumed the shapes of our objects (i.e. the bricks would always  
be rectangles, the ball would be a circle, and the platform would be a rectangle).


## New Features HowTo

#### Easy to Add Features
If we want to create a new Powerup, you would extend the Powerup super class and  
override the abstract usePowerup(Scene scene, GameManager manager) method. 
To add a new level, we would create a new text file, with the name levelX where X
is the level number. In the LevelCreator, we would have to add the platform width  
and the ball speed for this new level in the respective constant arrays.
To add a new type of Brick, one would have to extend Brick and add the brick  
to the LevelCreator class.
#### Other Features not yet Done
We finished all required features (and our own new feature:>))

