# Game Of Life

A simple game which is inspired by Conway's Game Of Life (https://playgameoflife.com/), that was created tied to a course in Objectoriented Programming at UiO.
The game is based on updating cells to be alive or dead, depending on the number of alive neighbours. The program is coded in pure Java.

## How to use

I have created a folder which separates a GUI-version, and a command-line version. 

For both of the programs, first compile using: 

javac *.java 

Then run the main-file using:

java GameOfLife

In the command-line version, you will be asked to provide information about how many rows and columns of cells to be created, followed by pressing the "ENTER"-button to
create the new generation of cells. In the GUI-version, a UI will be displayed, which allows you to click on which cells you want to start as dead or alive, a slidebar which decides 
the speed of the generational changes, and a start/end button.

### Installation

clone using: git clone https://magnusgit1/game-of-life.git
