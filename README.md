# Flappy Bird Clone

### Author: [I_Alakey](https://www.instagram.com/i_alakey)

#### Menu.java - creating a window
>Creating a game window, assigning it a length, width, and title

#### Game.java - Game logic
>Initializing all objects(images, buttons). Setting the initial coordinates of the player and pipes. In the Game constructor, we start the timer and the Play() and Rules () methods, and add a listener to our frame so that the player can move when the button is clicked. In the Dead() method, we write the condition under which the player dies. We have to make a check that the player collided with our objects(pipes). If this happens, we call the endGame() method, which displays a message about our current account and record. If our score is higher than the record, then we write to the file. In paint (), all objects that are located on the frame are drawn.

#### Player.java - player management
>We make it so that the player can move by entering 3 variables xPos, yPos, gravity. Creating the movieplayer () method so that the player falls down. When you press the SPACE key, our player goes up.

#### Pipe.java - the generation and motion of obstacles
>In order for our obstacles to move, enter the page Speed variable. Since we have two obstacles and they are on the same level X, we create one variable x and two variables Y (pos 1, pos 2). Next, we create a public method random Position() where we assign a new position to pipes if they have disappeared. Here we create the myPoints () scoring function.



