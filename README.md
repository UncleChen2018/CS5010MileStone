# Milestone Projects of  CS5010
## MileStone2 

### How to use

1. **Open the directory ./res**. There are three files:
   1. **NewDriver.jar,** which is used to run the MVC demo.
   2. **map.txt**, the plain txt file which store the sample data of the world. Can also offer other files (like *dungeon.txt*).
   3. **WorldMap.png**, a sample picture representation that is generated according to the world data. Which will be overwrite automatically if you successfully runs the jar. Can also save in other name (like dungeon.png).
2. **Execute the jar,** this run recommend using file for reading .
   1. **Read from file:** java - jar Driver.jar -f [filename] [max Turn], 
   2. **e. g.** java -jar Driver.jar -f map.txt 100
   3. **The last 100 means the max turn:** it should be positive 
3. **Interact with the program:**
   1. First, a window will prompt out, show the map. The window can be put aside as reference as you further interact with the program. ***The window would turn off automatically after the game is ended or you choose to quit.*** 
   2. Then, you can choose whether to save it to a png file. You can also name the file, if not , it by default will be saved in the WorldMap.png.
   3. **Before playing:** you need to add at least one player follow the instruction.
   4. **Turn begin:** input option to  make the player move, pick up item or stay and look around. 
   5. **End game:** choose 7 to Exit when its human player's turn or wait for the turn to end.

git hub: https://github.com/UncleChen2018/CS5010MileStone

### Assumption

1. Player choose the item they want to pick up and if no item in the room or the capacity is reached, they can not choose to spend this turn in picking up (applied to computer, too).

### Limitations

1. The game is still not have a target to win
2. The interaction is mainly for console user, and it is hard to display information, users should memorize certain things by themselves.

### Example run

1. begin the program: ***java -jar NewDriver.jar -f map.txt 15***, this means use the default map, run for 15 turn. You will see the the loading info, if finished, a brief word description will show, as well as the map window pop out, you can drag it aside and choose if save it to a png file.

2. set up the player: here we add three player, "Jimmy", location = 0, capacity = 2, human player; "AI", location = 0, capacity = 1,  computer player;   "Penny", location = 3,  capacity = 2,  human player. Remember to enter y to let them be added, and enter y again to begin game.

3. Every turn, first it gives out information who's turn it is, if is human player, also shows a simple menu, like 

   *[TURN 1]*
   *Player No.0 "Jimmy"'s turn*
   *Please select one of the option below*
   *MENU|1.Move|2.Pickup|3.LookAround|4.PlayerInfo|5.RoomInfo|6.Target|7.Exit*

4. Choose option, like move

   *-------------------Neighbor Info-------------------*
   *No.1 "Billiard Room"*
   *No.3 "Dining Hall"*
   *No.4 "Drawing Room"*
   *Enter the room index to move to*

   Enter 4
   Player No.0 "Jimmy" try to move to No.4 "Drawing Room"
   Move to neighbor successfully.
   -------------------PLAYER DETAILS-------------------
   Player: No.0 "Jimmy"
   Control Type: HUMAN
   Location: 4
   Stock|Capacity: 0|2
   Items: []

5. the computer will action automatically

   [TURN 2]
   Player No.1 "AI"'s turn
   Player No.1 "AI" try to move to No.4 "Drawing Room"
   Move to neighbor successfully.
   -------------------PLAYER DETAILS-------------------
   Player: No.1 "AI"
   Control Type: COMPUTER
   Location: 4
   Stock|Capacity: 0|1
   Items: []

6. if no item,  there will be prompt to tell you this

7. [TURN 3]
   Player No.2 "Penny"'s turn
   Please select one of the option below
   MENU|1.Move|2.Pickup|3.LookAround|4.PlayerInfo|5.RoomInfo|6.Target|7.Exit
   2
   Room has not item, choose other option.

8. When Max turn(15) reached, game exit

   

   

   

## MileStone1 backup

**How to use:**

1. **Open the directory ./res**. There are three files:

   1. **Driver.jar,** which is used to run the demo.
   2. **map.txt**, the plain txt file which store the sample data of the world. Can also offer other files (like *dungeon.txt*).
   3. **WorldMap.png**, a sample picture representation that is generated according to the world data. Which will be overwrite automatically if you successfully runs the jar. Can also save in other name (like dungeon.png).

2. **Execute the jar,** there are two ways.

   1. **Read from file:** java - jar Driver.jar -f [filename], e. g. java -jar Driver.jar -f map.txt

   2. **Read from String:** java - jar Driver.jar -s [world data],  on windows, the terminal can accept multiple lines with ^ as one argument, so an example is  

      1. first type : java -jar Driver.jar -s ^
   2. paste all the following data to the command line (Ctrl+c, then right mouse click)
      
      "36 30 Doctor Lucky's Mansion^^n50 Doctor Lucky^^n21^^n22 19 23 26 Armory^^n16 21 21 28 Billiard Room^^n28 0 35 5 Carriage House^^n12 11 21 20 Dining Hall^^n22 13 25 18 Drawing Room^^n26 13 27 18 Foyer^^n28 26 35 29 Green House^^n30 20 35 25 Hedge Maze^^n16 3 21 10 Kitchen^^n0 3 5 8 Lancaster Room^^n4 23 9 28 Library^^n2 9 7 14 Lilac Room^^n2 15 7 22 Master Suite^^n0 23 3 28 Nursery^^n10 5 15 10 Parlor^^n28 12 35 19 Piazza^^n6 3 9 8 Servants' Quarters^^n8 11 11 20 Tennessee Room^^n10 21 15 26 Trophy Room^^n22 5 23 12 Wine Cellar^^n30 6 35 11 Winter Garden^^n20^^n8 3 Crepe Pan^^n4 2 Letter Opener^^n12 2 Shoe Horn^^n8 3 Sharp Knife^^n0 3 Revolver^^n15 3 Civil War Cannon^^n2 4 Chain Saw^^n16 2 Broom Stick^^n1 2 Billiard Cue^^n19 2 Rat Poison^^n6 2 Trowel^^n2 4 Big Red Hammer^^n6 2 Pinking Shears^^n18 3 Duck Decoy^^n13 2 Bad Cream^^n18 2 Monkey Hand^^n11 2 Tight Hat^^n19 2 Piece of Rope^^n9 3 Silken Cord^^n7 2 Loud Noise"
   4. note all the line is break with ^^n, and that can only be executed on windows, mac not compatible yet
   
3. **Interact with the program:**
   
      1. First, a window will prompt out, show the map. The window can be put aside as reference as you further interact with the program. ***But remember to close it at the end of the program in order to get back to command line.*** 
      2. Then, you can choose whether to save it to a png file. You can also name the file, if not , it by default will be saved in the WorldMap.png.
      3. Interact with the menu to display various of information about the world, target character moving, the room and the item.
      4. Note: since the  visibility is not clearly defined, the logic here is two room has overlap either in X or Y axis, assuming all walls between them are transparent. 
      

git hub: https://github.com/UncleChen2018/CS5010MileStone

