# Sudoku solver and creator
Java app to solver or create Sudoku puzzles

Made during the holidays to get familiar with Java for my Object-Oriented Programming Course at Edinburgh.

# Solving
To solve a Sudoku puzzle, you will need to create a csv file that contains your puzzle. It will have to be a 9x9 grid containing either values from 1-9 or empty spaces. Run Sudoku.java and select your csv in the dialogue.

# Creating
Simply compile and run SudokuCreator and it will try to randomly create a sudoku puzzle for you. It will randomly create puzzles and then try to solve them in every possible ways. If there are more than 1 possible way of solving the puzzle, then it will make a new puzzle. You can see this process happen in the console before it finally finds and shows you a working puzzle.
You can tweak the number of starting numbers on the grid on line 11 in SudokuCreator.java by giving it a lower and upper bound. Theoretically the lower bound can be as low as 17, as this is the minimum number of numbers you can have to create a solvable Sudoku puzzle, however, this might take a long time to return an answer. An lower bound of at least 19 is recommended. The number of starting numbers roughly corresponds to the difficulty of the puzzle, however, it is not always the case.
