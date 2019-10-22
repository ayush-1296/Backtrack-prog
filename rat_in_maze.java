/* Java program to solve Rat in a Maze problem using backtracking */

/*
Rat in a Maze: 
A Maze is given as N*N binary matrix of blocks where source block is the upper left most block i.e., maze[0][0] and destination block is lower rightmost block i.e., maze[N-1][N-1]. A rat starts from source and has to reach the destination. The rat can move only in two directions: forward and down.
In the maze matrix, 0 means the block is a dead end and 1 means the block can be used in the path from source to destination. Note that this is a simple version of the typical Maze problem. For example, a more complex version can be that the rat can move in 4 directions and a more complex version can be with a limited number of moves.

Algorithm:
If destination is reached
    print the solution matrix
Else
   a) Mark current cell in solution matrix as 1. 
   b) Move forward in the horizontal direction and recursively check if this 
       move leads to a solution. 
   c) If the move chosen in the above step doesn't lead to a solution
       then move down and check if this move leads to a solution. 
   d) If none of the above solutions works then unmark this cell as 0 
       (BACKTRACK) and return false.
*/

public class RatMaze { 

	// Size of the maze 
	static int N; 

	/* A utility function to print solution matrix 
	sol[N][N] */
	void printSolution(int sol[][]) 
	{ 
		for (int i = 0; i < N; i++) { 
			for (int j = 0; j < N; j++) 
				System.out.print(" " + sol[i][j] + " "); 
			System.out.println(); 
		} 
	} 

	/* A utility function to check if x, y is valid 
		index for N*N maze */
	boolean isSafe(int maze[][], int x, int y) 
	{ 
		// if (x, y outside maze) return false 
		return (x >= 0 && x < N && y >= 0 && y < N && maze[x][y] == 1); 
	} 

	/* This function solves the Maze problem using 
	Backtracking. It mainly uses solveMazeUtil() 
	to solve the problem. It returns false if no 
	path is possible, otherwise return true and 
	prints the path in the form of 1s. Please note 
	that there may be more than one solutions, this 
	function prints one of the feasible solutions.*/
	boolean solveMaze(int maze[][]) 
	{ 
		int sol[][] = new int[N][N]; 

		if (solveMazeUtil(maze, 0, 0, sol) == false) { 
			System.out.print("Solution doesn't exist"); 
			return false; 
		} 

		printSolution(sol); 
		return true; 
	} 

	/* A recursive utility function to solve Maze problem */
	boolean solveMazeUtil(int maze[][], int x, int y, int sol[][]) 
	{ 
		// if (x, y is goal) return true 
		if (x == N - 1 && y == N - 1) { 
			sol[x][y] = 1; 
			return true; 
		} 

		// Check if maze[x][y] is valid 
		if (isSafe(maze, x, y) == true) { 
			// mark x, y as part of solution path 
			sol[x][y] = 1; 

			/* Move forward in x direction */
			if (solveMazeUtil(maze, x + 1, y, sol)) 
				return true; 

			/* If moving in x direction doesn't give solution then Move down in y direction */
			if (solveMazeUtil(maze, x, y + 1, sol)) 
				return true; 

			/* If none of the above movements works then BACKTRACK: unmark x, y as part of solution path */
			sol[x][y] = 0; 
			return false; 
		} 

		return false; 
	} 

	public static void main(String args[]) 
	{ 
		RatMaze rat = new RatMaze(); 
		int maze[][] = { { 1, 0, 0, 0 }, 
				 { 1, 1, 0, 1 }, 
				 { 0, 1, 0, 0 }, 
				 { 1, 1, 1, 1 } }; 

		N = maze.length; 
		rat.solveMaze(maze); 
	} 
} 