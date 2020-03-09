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
	private int[][] puzzle = new int[9][9];// Initialize a blank sudoku board
	private boolean firstRow = true;//Used to build first row as random numbers
	private boolean doneFlag = false;//So recursion doesnt continue to produce different boards
	public int[][]finalPuzzle = new int[9][9];//The final puzzle that starts as solved

	/**
	 *Builds the first row using random integers
	 *Possible combinations = 9! = 362880 just for first row
	 * @return a completed sudoku puzzle
	 */
	private void buildFirstRow()
	{
		boolean notPossible = true;
		int number = 0;
		for(int start = 0; start < puzzle.length; start ++)
		{
			//Keep trying until a number is there
			while(puzzle[0][start] == 0)
			{
				number = rand.nextInt(10);
				notPossible = startRow(number);
				if(notPossible)
				{
					puzzle[0][start]=number;
							break;
				}
			}
			
		}
	}
	
	/**
	 * Checks to make sure there are no duplicates in the first row
	 * @param number the number to be inserted into the puzzle
	 * @return either true the number can go there or false;
	 */
	private boolean startRow(int number)
	{
		for(int i = 0; i < puzzle.length; i++)
			if(number == puzzle[0][i])
				return false;
		return true;
			
	}
	
	/**
	 * Solidify the completed puzzle
	 * Due to backtracking, all progress is erased when the loop exits
	 * Then fill in 0's for the actual puzzle based on difficulty
	 * @param completedPuzzle the puzzle that was finished via the back tracking
	 */
	private void buildFinalPuzzle(int[][] completedPuzzle)
	{
		for(int y = 0; y<finalPuzzle.length; y++)
		{
			for(int x = 0; x<finalPuzzle.length; x++)
			{
					finalPuzzle[y][x] = completedPuzzle[y][x];
			}
		}
	}
	
	/**
	 * Main Driver of the builder
	 * Uses recursion and backtracking to solve the puzzle to make sure puzzle is possible
	 */
	public void build()
	{
		//If puzzle becomes complete, stop recursion calls
		if(doneFlag)
			return;
		//Needs to buid the first row 
		if(firstRow)
			buildFirstRow();
		firstRow = false;
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
							puzzle[y][x] = solution;
							if(y==8 & x == 8)
								buildFinalPuzzle(puzzle);
							build();
							puzzle[y][x] = 0;
							
						}
					}
					return;
				}
			}
		}
		doneFlag = true;
	}
	
	/**
	 * Checks to see if the puzzle is an actual solvable puzzle
	 * @param y the column to be checked
	 * @param x the row to be cheked
	 * @return return true if the puzzle is unsolvable
	 */
	private boolean checkPuzzle(int y, int x, int number)
	{
		for(int i = 0; i <puzzle.length; i++)
			if(number == puzzle[y][i] && i != x) return false;
		for(int i = 0; i<puzzle.length; i++)
			if(number == puzzle[i][x] && i != y) return false;
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
				if(number == puzzle[square_y][square_x] && square_y != y && square_x != x) return false;
				square_x++;
			}
			square_x = temp_x;
			square_y++;
		}
		return true;
	}
	
	/**
	 * Prints the 2d array for debugging purposes
	 */
	public void printer()
	{
		for(int i = 0; i<finalPuzzle.length; i++)
		{
			if(i == 0 || i == 3 || i == 6)
				System.out.print("+ - - - + - - - + - - - +\n");
			for(int j = 0; j<finalPuzzle.length; j++)
			{
				if(j == 0)
					System.out.print("| "+finalPuzzle[i][j]);
				else if(j==2 || j == 5)
					System.out.print(" "+finalPuzzle[i][j]+" |");
				else if(j == 8)
					System.out.print(" "+finalPuzzle[i][j]+" |\n");
				else
					System.out.print(" "+finalPuzzle[i][j]);
				
			}
		}
		System.out.print("+ - - - + - - - + - - - +\n");
		System.out.println();
	}
	
	/**
	 * Gets the final solved puzzle, stores it in memory since all the hard work has been done
	 * @return the final solved puzzle
	 */
	public int[][] getFinalPuzzle()
	{
		return finalPuzzle;
	}
	
	/**public static void main(String[] args) throws InterruptedException {
		puzzleBuilder pb = new puzzleBuilder();
		pb.build();
		pb.printer();
	}*/

}
