/**
 * Solves any sudoku board via backtracking
 * @author Chad Moore
 *
 */
public class SudokuSolver {
	public static int[][] grid = new int[9][9];// Blank sudoku board
	private static int[][] solvedPuzzle = new int[9][9];//SOlved puzzle for display
	
	/**
	 * COnstructor to initzialize sudoku board
	 * @param grid a 2d partially filled out sudoku board
	 */
	public SudokuSolver(int[][] grid)
	{
		SudokuSolver.grid = grid;
	}
	
	/**
	 * Prints the board for debugging pruposes
	 */
	public static void boardPrinter()
	{
		for(int i = 0; i<grid.length; i++)
		{
			if(i == 0 || i == 3 || i == 6)
				System.out.print("+ - - - + - - - + - - - +\n");
			for(int j = 0; j<grid.length; j++)
			{
				if(j == 0)
					System.out.print("| "+grid[i][j]);
				else if(j==2 || j == 5)
					System.out.print(" "+grid[i][j]+" |");
				else if(j == 8)
					System.out.print(" "+grid[i][j]+" |\n");
				else
					System.out.print(" "+grid[i][j]);
				
			}
		}
		System.out.print("+ - - - + - - - + - - - +\n");
		System.out.println();
	}
	
	/**
	 * Prints the solution for debug purposes
	 */
	public void solutionPrinter()
	{
		System.out.println("Printing");
		for(int i = 0; i<solvedPuzzle.length; i++)
		{
			if(i == 0 || i == 3 || i == 6)
				System.out.print("+ - - - + - - - + - - - +\n");
			for(int j = 0; j<solvedPuzzle.length; j++)
			{
				if(j == 0)
					System.out.print("| "+solvedPuzzle[i][j]);
				else if(j==2 || j == 5)
					System.out.print(" "+solvedPuzzle[i][j]+" |");
				else if(j == 8)
					System.out.print(" "+solvedPuzzle[i][j]+" |\n");
				else
					System.out.print(" "+solvedPuzzle[i][j]);
				
			}
		}
		System.out.print("+ - - - + - - - + - - - +\n");
		System.out.println();
	}
	
	/**
	 * TEMPORARY CODE, debugging purposes
	 * @param y the column to add
	 * @param x the row to add
	 * @param solution the actual solution
	 */
	public static void buildSolution(int y, int x, int solution)
	{
		solvedPuzzle[y][x]=solution;
	}
	
	/**
	 * Main driver calls its self and implements back tracking over bad guess
	 */
	public static void solver()
	{
		for(int y = 0; y<grid.length; y++)
		{
			for(int x = 0; x<grid.length; x++)
			{
				if(grid[y][x] == 0)
				{
					for(int solution = 1; solution<10; solution++)
					{
						if(isPossible(y,x,solution))
						{
							grid[y][x] = solution;
							buildSolution(y,x,solution);
							solver();
							grid[y][x] = 0;
							
						}
					}
					if(y==8 && x ==8)
						return;
				}
			}
		}
	}
		
	/**
	 * Checks to see if a given solution 
	 * in a given location is possibe
	 * @param y the row to check
	 * @param x the column to check
	 * @param solution the actual solution to be used
	 * @return True if solution works, fakse otherwise
	 */
	private static boolean isPossible(int y,int x, int solution)
	{
		for(int i = 0; i <grid.length; i++)
			if(solution == grid[y][i]) return false;
		for(int i = 0; i<grid.length; i++)
			if(solution == grid[i][x]) return false;
		//determine square to check
		int square_x = -1;
		int square_y = -1;
		if(x<3) square_x = 0;
		else if(x<6) square_x = 3;
		else square_x = 6;
		if(y<3) square_y = 0;
		else if(y<6) square_y = 3;
		else square_y = 6;
		int temp_x = square_x;
		int temp_y = square_y;
		while(square_y<temp_y+3)
		{
			while(square_x<temp_x+3)
			{
				if(solution == grid[square_y][square_x]) return false;
				square_x++;
			}
			square_x = temp_x;
			square_y++;
		}
		return true;
	}

}
