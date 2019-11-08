import java.awt.Color;
import objectdraw.*;

public class Grid extends ActiveObject {

	protected FilledRect[][] grid;
	protected FramedRect[][] borders;
	protected Cell[][] cells; 
	protected int box_size;
	protected boolean running;
	protected static final int XOFFSET=8;
	protected static final int YOFFSET=8;
	protected int[][] round; 

	
	public Grid(int window_size, int box_size, DrawingCanvas canvas) {
		this.box_size = box_size;
		grid = new FilledRect[(window_size-2*YOFFSET)/box_size][(window_size-2*XOFFSET)/box_size];
		borders = new FramedRect[(window_size-2*YOFFSET)/box_size][(window_size-2*XOFFSET)/box_size];
		for(int row=0; row<grid.length; row++) {
			for(int col=0; col<grid[0].length; col++) {
				grid[row][col] = new FilledRect(col*box_size+XOFFSET, row*box_size+YOFFSET, box_size, box_size, canvas);
				grid[row][col].setColor(Color.WHITE);
				borders[row][col] = new FramedRect(col*box_size+XOFFSET, row*box_size+YOFFSET, box_size, box_size, canvas);
			}
		}
		running = false;
		round = new int[grid.length][grid[0].length]; 
    	this.start();
	}
	
	/* TODO: Update this method to return the cell in which the
	 * given point resides.  return the row and col
	 */
	public Cell getCell(Location point) {
		int temp_x = (int)((point.getX() - XOFFSET) / box_size);
		int temp_y = (int)((point.getY() - YOFFSET) / box_size);
		return new Cell(temp_y,temp_x);

	}
	
	/* TODO: Update this method to make a black cell white or a 
	 * white cell black.  Also return the cell that you toggled.  should return the row and col. 
	 */
	public Cell toggle(Location point) {
		int temp_x = (int)((point.getX() - XOFFSET) / box_size);
		int temp_y = (int)((point.getY() - YOFFSET) / box_size);
		toggle(temp_y,temp_x); 
		return new Cell(temp_y,temp_x);
	}
	
	/* TODO: Given a row and column in the grid, switch the
	 * color of the cell at that position. 
	 */
	public void toggle(int row, int col) {
		if(grid[row][col].getColor() == Color.WHITE) {
			grid[row][col].setColor(Color.BLACK); 
		}
		else {
			grid[row][col].setColor(Color.WHITE);
		}
		
	}
	
	public void toggleRunning() {
		running = !running;
	}
	
	/* TODO: Return true if the cell at the given row and col is alive.
	 * NB: row and col may be values that are outside the grid. 
	 * Cells outside the grid are not alive. 
	 */
	protected boolean isAlive(int row, int col) {
		if(grid[row][col].getColor() == Color.BLACK) {
			return true;
		}
		return false;
	}
	
	/* TODO: Return the number of alive cells that are adjacent
	 * to the given row and col. 
	 */
	protected int liveNeighbors(int row, int col) {
		int neighbors = 0; 
		int new_row = 0; 
		int new_col = 0; 
		boolean dcount_flag = false; 
		for (int i = -1; i < 2; ++i) {
			for (int j = -1; j < 2; ++j) {
				dcount_flag = false; 
				if(row + i >= 0 && row+i < grid.length) new_row = row + i; 
				else {
					dcount_flag = true; 
				}
				if(col + j >= 0 && col+j < grid[0].length) new_col = col + j; 
				else {
					dcount_flag = true; 
				}
				if(!dcount_flag) {
					if(isAlive(new_row, new_col)) {
						neighbors++;
					}
				}
			}
			
		}
		if(isAlive(row,col)) {
			neighbors--;
		}
	
		return neighbors;
	}
	
	
	/* TODO: Set all of the cells in the grid to WHITE/off/dead
	 */
	public void clear() {
		for (int i = 0; i < grid.length; ++i) {
			for (int j = 0; j < grid[0].length; ++j) {
				grid[i][j].setColor(Color.WHITE);
			}
		}
	}
	
	/* TODO: Set a given cell to BLACK/alive/on if it is within
	 * the grid. 
	 */
	private void on(int row, int col) {
		grid[row][col].setColor(Color.BLACK); 
	}
	private void off(int row, int col) {
		grid[row][col].setColor(Color.WHITE);
	}
	
	/* Mystery method.  Figure out when it gets used and why it's 
	 * interesting.  
	 */
	public void gliderGun(int row, int col) {
		on(row,col);
		on(row,col+1);
		on(row+1,col);
		on(row+1,col+1);
		on(row,col+10);
		on(row+1,col+10);
		on(row+2,col+10);
		on(row+3,col+11);
		on(row-1,col+11);
		on(row-2,col+12);
		on(row-2,col+13);
		on(row+4,col+12);
		on(row+4,col+13);
		on(row+1,col+14);
		on(row-1,col+15);
		on(row+3,col+15);
		on(row,col+16);
		on(row+1,col+16);
		on(row+2,col+16);
		on(row+1,col+17);
		on(row,col+20);
		on(row,col+21);
		on(row-1,col+20);
		on(row-1,col+21);
		on(row-2,col+20);
		on(row-2,col+21);
		on(row-3,col+22);
		on(row+1,col+22);
		on(row-3,col+24);
		on(row+1,col+24);
		on(row-4,col+24);
		on(row+2,col+24);
		on(row-1,col+34);
		on(row-2,col+34);
		on(row-1,col+35);
		on(row-2,col+35);

	}
	public void update(int[][] round) {
		for(int i = 0; i < round.length; ++i) {
			for (int j = 0; j < round[0].length; ++j) {
				if(round[i][j] == 1) {
					//Kill
					off(i,j); 
					round[i][j] = 0; 
					
				}
				else if (round[i][j] == 2) {
					//Birth
					on(i,j);
					round[i][j] = 0; 
				}
				else {
					
				}
			}
		}
	}
	
	public void run() {
		//update the thing in one go. 
		while(true) {
			if(running) {			
				for(int i = 0; i < grid.length; ++i) {
					for (int j = 0; j < grid[0].length; ++j) {
						//If its alive check to see if it dies
						if(isAlive(i,j)) {
							if(liveNeighbors(i,j) >= 4 || liveNeighbors(i,j) <= 1){
								round[i][j] = 1; 
							}
						}
						//If its dead check to see if there is  a birth
						else {
							if(!isAlive(i,j)) {
								if(liveNeighbors(i,j) == 3) {
									round[i][j] = 2; 
								}
							}
						}	
					}
				}
				//System.out.println("finished with loops"); 
				// TODO:  Insert the logic to play the Game of Life
				update(round); 
			}
			pause(100);	
		}
	}
}
