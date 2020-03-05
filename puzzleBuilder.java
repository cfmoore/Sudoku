import java.util.Random;

/**
 * The class for building the puzzle board
 * The dificulty determines the amount of blank spaces
 * @author chad
 *
 */
public class puzzleBuilder 
{
	private Random rand = new Random();// Random Number Generator
	private int difficulty;//Initizalize the difficulty
	public int[][] puzzle = new int[9][9];// Initialize a blank sudoku board
	
	/**
	 * Gets the difficulty of the board
	 * for Java completeness, not used. Dificulty is only
	 * called and set in the constructotr
	 * @return the difficulty
	 */
	public int getDifficulty() {
		return difficulty;
	}
	
	/**
	 * Sets the difficulty for Java completness, not used
	 * @param difficulty the difficulty of the board
	 */
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	/**
	 * COnstructor to for the puzzle 
	 * @param difficulty the difficulty of the board
	 */
	public puzzleBuilder(int difficulty)
	{
		if(difficulty<=1)
			this.setDifficulty(1);
		else
			this.setDifficulty(difficulty-1);
	}
	
	/**
	 *Builds the 2d array puzzle using random integers
	 *and backtracking 
	 * @return a completed sudoku puzzle
	 */
	public int[][] build()
	{
		System.out.println("BUILDING");
		boolean notPossible = true;
		int tryLimit = 0;
		for(int y = 0; y<puzzle.length; y++)
		{
			for(int x = 0; x<puzzle.length; x++)
			{
				if(puzzle[y][x] == 0)
				{
					for(int solution = 1; solution<10; solution++)
					{
						if(checkPuzzle(y,x,solution))
						{
							grid[y][x] = solution;
							solver();
							grid[y][x] = 0;
							
						}
					}
					return;
			}
		}
		
		
		return puzzle;
	}
	

	
	/**
	 * Checks to see if the puzzle is an actual solvable puzzle
	 * @param y the column to be checked
	 * @param x the row to be cheked
	 * @return return true if the puzzle is unsolvable
	 */
	private boolean checkPuzzle(int y, int x)
	{
		for(int i = 0; i <puzzle.length; i++)
			if(puzzle[y][x] == puzzle[y][i] && i != x) return true;
		for(int i = 0; i<puzzle.length; i++)
			if(puzzle[y][x] == puzzle[i][x] && i != y) return true;
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
				if(puzzle[y][x] == puzzle[square_y][square_x] && square_y != y && square_x != x) return true;
				square_x++;
			}
			square_x = temp_x;
			square_y++;
		}
		return false;
	}
	
	/**
	 * Prints the 2d array for debugging purposes
	 */
	public void printer()
	{
		for(int i = 0; i<puzzle.length; i++)
		{
			if(i == 0 || i == 3 || i == 6)
				System.out.print("+ - - - + - - - + - - - +\n");
			for(int j = 0; j<puzzle.length; j++)
			{
				if(j == 0)
					System.out.print("| "+puzzle[i][j]);
				else if(j==2 || j == 5)
					System.out.print(" "+puzzle[i][j]+" |");
				else if(j == 8)
					System.out.print(" "+puzzle[i][j]+" |\n");
				else
					System.out.print(" "+puzzle[i][j]);
				
			}
		}
		System.out.print("+ - - - + - - - + - - - +\n");
		System.out.println();
	}
	
	public static void main(String[] args) {
		puzzleBuilder pb = new puzzleBuilder(10);
		pb.build();
		pb.printer();
	}

}
