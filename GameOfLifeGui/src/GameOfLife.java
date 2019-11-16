/*
 *  
 *  1. It creates a constantly repeating pattern that has no end, with two pyramid like shapes (guns?) deflecting off each other
 *  producing 4 blocks (glider?) that slowly make their way to the bottom right of the screen. It's significant because
 *  its a complex but finite pattern with many steps and cycles but it never dies and is constantly repeating. 
 *  
 *  
 *  2. Because the game is turned based, it means we cannot change the state of the array dynamically (can't change it
 *  while scanning through each element of the 2d grid). Therefore, an entirely separate 2-D array of the same size has to
 *  be used to keep track of where the markings and only update the board after the entire grid has been scanned.
 *  We need more memory because we needed to allocate a whole new chunk memory for this new 2-D array of the same size.
 *  
 *  3. Previously, Cells were used only to map the Location on the GUI to a row and col in the 2-D Array. With additional 
 *  functionality within the Cell class, the grid class now has to hold a 2-D array of Cells rather than rows and cols. 
 *  Cons of this: 
 *  - Implementing an entire hierarchy of classes. Meaning all the variables and methods (toggle or boxSize) not only have to exist
 *  in the Grid class(since grid has to be able to reference these methods) but also exist in Cell class (since these methods
 *  are actually implemented within the Cell class). Implementing this hierarchy of classes takes more time and means rewriting
 *  methods that are basically nothing but headers. For example: in Grid class it'll simply be that:
 *  public void toggle(i,j){
 *  	grid[i][j].toggle() 
 *  }
 *  
 *  Pros of this: 
 *  A bottom up implementation enhances the readability of the Code. The grid class will merely become a class meant to
 *  manage the array of Cells rather than implement the vast majority of logic in one class in addition to managing. 
 *  Furthermore, other programmers will find it easier to add code when the code is structured this way. Without having to skim through
 *  all the logic in the grid class, they can easily make modifications to how each individual cell reacts 
 *  (maybe it changes to red or blue for different scenarios) by simply changing the Cell class. Vice versa, if they want to 
 *  add different commands to manage the Cells (draw different patterns) they could implement it into the Grid class. 
 *  By changing the functionality within the classes, it aids the readability and reusability of this game for other programmers
 *  and users.
 *  
 * 
 * 
 *  4. For an board of infinite dimensions and size, we would implement an ArrayList of ArrayList (2-D ArrayList) 
 *  which can freely append new rows and new columns. The data structure would be of Generic Integer, and continue to keep track
 *  of the rows and columns of blocks. When the pattern within the game extends beyond certain bounds of the window
 *  ,we can use the add method to add rows and columns to the 2-D ArrayLists, effectively enlarging the WindowSize.
 * 
 * 
 * 
 */


import java.awt.Color;
import java.awt.Container;
import javax.swing.JRootPane;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import objectdraw.*;
public class GameOfLife extends WindowController implements KeyListener {
	
	protected static final int WINDOW_SIZE = 616;
	protected static final int BOX_SIZE = 15;
	protected Cell lastToggledCell;
	
	protected Grid grid;
	
	public void begin() {
		int yoffset = 0;
		
		/* The coordinate system of the grid is sthrown off slightly by
		 * the existance of the system menu bar.  The code below figures out
		 * the hight of the menu bar. The call to resize at the end of this
		 * method takes this offset into account when making the whole grid
		 * visible. 
		 */
		Container c = this;
		while(! (c instanceof JRootPane)) {
			yoffset += (int)(c.getParent().getY());
			c = c.getParent();
		}
		grid = new Grid(WINDOW_SIZE, BOX_SIZE, canvas);
        requestFocus();
        addKeyListener(this);
        canvas.addKeyListener(this);
        lastToggledCell = null;
        resize(WINDOW_SIZE, WINDOW_SIZE + yoffset);
	}
	
	public void onMousePress(Location point) {
		/* TODO: Toggle the cell that was clicked on
		 * and keep track of what cell you just 
		 * changed. 
		 */
		lastToggledCell = grid.toggle(point); 
	}
	
	public void onMouseDrag(Location point) {
		/* TODO: Toggle the cell under the mouse if
		 * it wasn't the last cell to be toggled. 
		 */
		if(!lastToggledCell.equals(grid.getCell(point))) {
			lastToggledCell = grid.toggle(point); 
		}
	}
	
    // Required by KeyListener Interface.
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    
    public void keyTyped(KeyEvent e)
    {
    	char letter = e.getKeyChar();
    	if(letter == 'g' && lastToggledCell != null) {
    		grid.gliderGun(lastToggledCell.getRow(), lastToggledCell.getCol());
    	} else if (letter == 'c') {
    		grid.clear(); 
    	}
    	else {
    		grid.toggleRunning(); 
    	}

    }
	
    public static void main(String[] args) { 
        new GameOfLife().startController(WINDOW_SIZE, WINDOW_SIZE); 
	}

}
