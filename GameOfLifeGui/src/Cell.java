import java.lang.ClassCastException;
public class Cell {

	protected int row;
	protected int col;
	
	/* TODO: Initialize instance variables */
	public Cell(int row, int col) {
		this.row = row; 
		this.col = col; 
	}
	
	/* TODO: Write the following accessor methods */
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col; 
	}
	
	/* @returns true if o is a Cell and o has the same
	 * row and col as the current cell
	 * Hint: Recall that java has an instanceof keyword
	 */
	public boolean equals(Object o) {
		try {
			Cell temp = (Cell)o; 
			if(temp.getRow() == this.row && temp.getCol() == this.col)
				return true; 
			return false; 
			
		}
		catch (ClassCastException E) {
			System.out.println("Not a Cell type"); 
			return false; 
		}
	}
}
