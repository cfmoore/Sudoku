import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Gui and Driver for sudoku game
 *
 * @author chad
 *
 */

public class SudokuPuzzle extends JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int GRID_SIZE = 9;//size of board grid 9X9
	public static final int SUBGRID_SIZE = 3;//size of sub grids 3X3
	public static final int CELL_SIZE = 60;//size of cell in pixels
	public static final int WINDOW_WIDTH = CELL_SIZE * GRID_SIZE;
	public static final int WINDOW_HEIGHT = CELL_SIZE * GRID_SIZE;
	
	//OC = Open Cell CC = Closed Cell
	//Set up variables for GUI
	public static final Color OC_BGCOLOR = Color.YELLOW;
	public static final Color OC_TEXT_CORRECT = Color.GREEN;
	public static final Color OC_TEXT_WRONG = Color.RED;
	public static final Color CC_BGCOLOR = Color.LIGHT_GRAY;
	public static final Color CC_TEXT = Color.BLACK;
	public static final Font FONT_STYLE = new Font("Helvitica",20,20);
	
	private JTextField[][] cells = new JTextField[GRID_SIZE][GRID_SIZE];

	public static void main(String[] args) {
		int difficulty = 10;
		System.out.println("Input a difficulty 1 - 10:");
		System.out.println("1 = Easy, 10 = Hard");
		puzzleBuilder puzzle = new puzzleBuilder();
		puzzle.build();
		int[][] thePuzzle = puzzle.getFinalPuzzle();
		SolvableSudoku playPuzzle = new SolvableSudoku(difficulty);
		playPuzzle.buildPuzzle(thePuzzle);
		playPuzzle.printer();

	}

}
