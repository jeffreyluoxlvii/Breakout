game
====

This project implements the game of Breakout.

Names: Jeffrey Luo and Roy Xiong

### Timeline

Start Date: 30 January 2020

Finish Date: 16 February 2020

Hours Spent: 20

### Resources Used


### Running the Program

Main class: BreakoutGame

Data files needed: 

Level files containing brick configurations in resources root

Key/Mouse inputs:

Mouse used to control game platform
Q - slow down time for 3 seconds

Cheat keys:

SPACE - pauses game  
R - resets ball  
L - adds a life  
S - speeds up ball  
P - give a powerup  
D - destroy a block  
1 - skip to level 1  
2 - skip to level 2  
3 - skip to level 3  
W - give ability to slow time


Known Bugs:

Extra credit:

Substantial feature:  
Ability to slow down time for three seconds after picking up a certain power-up.
To do this, a Timer was implemented, and the GameManager class holds a boolean in charge of whether the ability is able
to be used.

### Notes/Assumptions

The scores file holds 1100 as a high score for testing purposes. Remove 1100 from the scores file
before playing the game.

The levels get harder due to level design (the ball goes through designs that result in more unpredictable bounces), the
type of bricks in levels (later levels have bricks that must be hit more times), and the faster speed of ball in 
later levels.

### Impressions

This project was definitely on a much larger scale than the previous Data project. However, it was also good practice
in working with and designing inheritance hierarchies, something that was not done in the previous project.