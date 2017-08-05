# Unblock Me Generator
Java implementation of a Puzzle generator, based off the famous App [UnblockMe](https://www.google.com/#q=unblockme&*). This project will attempt to generate puzzles of variable length and store them in a database, if needed.

Below is an example of a possible constraint puzzle:
![alt text](https://github.com "Example Puzzle")

## Background
The program is made up of two functions:
- Solver
- Generator

The solver is actually used in the generation of puzzles to enforce that the puzzles are completable. 

## Requirements:
- Java
- MySQL (if storing puzzles)

## Usage
This program is ran from command line by the invocation of the Main.java class file, generating the command prompt:

```
Would you like to solve or generate puzzles?
1) - Solve a puzzle (Requires puzzle in 2D array form)
2) - Generate puzzles

Enter command: 
```

### Generating Puzzles
Puzzles can be generated and saved to a SQL database. The specified tables are provided [here](TODO).

In order to understand the variables in this generator, we'll go over the algorithm:
- Step #1: The prison block is first placed randomly on the board in the correct row.
- Step #2: We attempt to place a new block in a random position, and evaluate the board (solve it). That path length is saved as the value of the board.
- Step #3: We do Step #2 for variable amount of times, to try and get a permutation of good boards. For example, if we do this 10 times, we get 10 boards, each with their own value (path length).
- Step #4: We pick the best board in terms of path length, and repeat Steps #2-4 until we get a board of the default path length.

### Tips for Generating
- If you specify a high number of blocks, like 14+, the algorithm will fill up the board relatively quickly. This may or may not lead to a high path length.
- The lower the number of blocks placed, the more straight forward the puzzle most likely will be.
- The higher the path length you are trying to find, the longer the program will take to generate. Upwards of 30+ minutes for one game board of 25+ moves.

