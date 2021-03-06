import structure5.*; 

/**
 * This module includes the functions necessary to keep track of the creatures
 * in a two-dimensional world. In order for the design to be general, the
 * interface adopts the following design:
 * <p>1. The contents are unspecified objects.
 * <p>2. The dimensions of the world array are specified by the client. <p>
 * There are many ways to implement this structure. HINT:
 * look at the structure.Matrix class. You should need to add no more than 
 * about ten lines of code to this file.
 */

public class World<E>
{
	private Matrix<E> data; 
	/**
	 * This function creates a new world consisting of width 
	 * columns and height rows, each of which is numbered beginning at 0. 
	 * A newly created world contains no objects.
	 * @param w The width of the world that is to be created
	 * @param h The height of the world that is to be created
	 * @pre w > 0
	 * @pre h > 0
	 */
	public World(int w, int h)  {
		data = new Matrix<E>(h,w); 
	}

	/**
	 * Returns the height of the world.
	 */
	public int height() {
		return data.height(); 
	}

	/**
	 * Returns the width of the world.
	 */
	public int width() {
		return data.width(); 
	}

	/**
	 * Returns whether pos is in the world or not. 
	 * @pre pos is a non-null position. 
	 * @post returns true if pos is an (x,y) location in the bounds of
	 *       the board.
	 */
	
	boolean inRange(Position pos)  {
		//Between X-Range and Y-Range
		if((pos.getX() >= 0 && pos.getX() < width()) && (pos.getY() >= 0 && pos.getY() < height())) {
			return true;
		}
		return false;
	}

	/**
	 * Sets a position on the board to contain c.
	 * @param c The object that is to be added
	 * @param pos Where c is to be added
	 * @pre pos is a non-null position on the board.
	 */
	public void set(Position pos, E c) {
		data.set(pos.getY(),pos.getX(),c); 
	}

	/**
	 * Return the contents of a position on the board. 
	 * @pre pos is a non-null position on the board.
	 */
	public E get(Position pos) {
		return data.get(pos.getY(),pos.getX()); 
	}

	public static void main(String[] args) {
		World<Integer> w = new World<Integer>(10,10); 
		Position inp = new Position(8,8); 
		Position outp = new Position(10,15); 
		//Getter Methods
		Assert.condition(w.height() == 10, "height doesn't work");
		Assert.condition(w.width() == 10, "width doesn't work");
		//In Range
		Assert.condition(w.inRange(inp), "Range doesn't work");
		Assert.condition(!w.inRange(outp), "Range doesn't work");
		//Setting
		Integer i = 5; 
		w.set(inp, i);
		Assert.condition(w.get(inp).equals(i),"Setting or Getting doesn't work"); 

	}

}
