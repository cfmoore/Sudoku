import java.util.*;
/**
 * Class that takes in a difficulty and makes a solvable sudoku puzzle
 * Blank spaces are filled in as 0's which will be turned into blanks on the GUI
 * @author Chad
 *
 */
public class SolvableSudoku {
	private int difficulty;//The difficulty of the puzzle, how many blank spaces
	private Random rand = new Random();//For random number generation
	public int[][] gamePuzzle = new int[9][9];//The puzzle that will be shown in the GUI
	private int symmetry = 0;//Define the symmetry of the puzzle 1= horizontal, 2 = vertical, 3 = diagonal
	
	
	/**
	 * Standard Constructor
	 */
	public SolvableSudoku(int difficulty)
	{
		if(difficulty <= 1)
			this.difficulty = 2;
		else if(difficulty >=10)
			this.difficulty = 10;
		else
			this.difficulty = difficulty;
		defineSymmetry();
	}
	
	/**
	 * Sets the symmetry line
	 * 1 = horizontal symmetry across the middle row
	 * 2 = vertical symmetry across the middle column
	 * 3 = diagonal symmetry across the longest line from either 0,0 or 0,9
	 */
	private void defineSymmetry()
	{
		while(symmetry == 0)
			symmetry = rand.nextInt(4);
		setSymmetryLine();
	}
	
	/**
	 * Fills the line with -1 for so when the puzzle is built
	 * the line stays persistent
	 */
	private void setSymmetryLine()
	{
		int diagonalMover = 0;//Way to go from left to right or right to left
		if(symmetry == 1)
			for(int x = 0; x<gamePuzzle.length; x++)
				gamePuzzle[4][x] = -1;
		else if(symmetry == 2)
			for(int y = 0; y<gamePuzzle.length; y++)
				gamePuzzle[y][4] = -1;
		else
		{
			if(rand.nextInt(2) == 1)//If random int ==  1 go from 0,0, If == 0 go from 0,9
			{
				for(int y = 0; y<gamePuzzle.length; y++)
				{
					gamePuzzle[y][diagonalMover] = -1;
					diagonalMover++;
				}
			}
			else
			{
				diagonalMover = gamePuzzle.length-1;
				for(int y = 0; y<gamePuzzle.length; y++)
				{
					gamePuzzle[y][diagonalMover] = -1;
					diagonalMover--;
				}
			}
		}
	}
	/**
	 * Takes a solved puzzle and adds blanks to make it an actual playable sudoku board
	 * @param puzzle the solved puzzle
	 * @return a playable sudoku puzzle with blank spaces
	 */
	public int[][] buildPuzzle(int[][] puzzle)
	{
		Stack<Integer> mirrorStack = new Stack<>();
		//Generate Random Ints on left side of symmetry line
		for(int y = 0; y < gamePuzzle.length; y++)
		{
			for(int x = 0; x<gamePuzzle.length; x++)
			{
				if(gamePuzzle[y][x] == -1)
					break;
				gamePuzzle[y][x]= rand.nextInt(difficulty);
			}
			if(gamePuzzle[y][0] == -1 && y == 4 )
				break;
		}
		return gamePuzzle;
	}
	
	/**
	 * Prints the 2d array for debugging purposes
	 */
	public void printer()
	{
		for(int i = 0; i<gamePuzzle.length; i++)
		{
			if(i == 0 || i == 3 || i == 6)
				System.out.print("+ - - - + - - - + - - - +\n");
			for(int j = 0; j<gamePuzzle.length; j++)
			{
				if(j == 0)
					System.out.print("| "+gamePuzzle[i][j]);
				else if(j==2 || j == 5)
					System.out.print(" "+gamePuzzle[i][j]+" |");
				else if(j == 8)
					System.out.print(" "+gamePuzzle[i][j]+" |\n");
				else
					System.out.print(" "+gamePuzzle[i][j]);
				
			}
		}
		System.out.print("+ - - - + - - - + - - - +\n");
		System.out.println();
	}

}
