import java.awt.Color;

import objectdraw.*;
public class VariegatedStairs implements StairInterface {
	private FramedRect sq;
	//Stair Interface so move can be called for both VariegatedStairs + EmptyStairs
	private StairInterface s1; 
	private StairInterface s2; 
	public VariegatedStairs(Location origin,double length, DrawingCanvas canvas, Color c) {
		//Draw a Rectangle
		sq = new FramedRect(origin,length,length,canvas); 
		sq.setColor(c);
		//Find the 2 origins (top left corner) for the next 2 recursive Squares
		Location l1 = new Location(origin.getX()+length,origin.getY() + length/2); 
		Location l2 = new Location(origin.getX(),origin.getY()-length/2); 
		//New color
		Color c1 = new Color(c.getRed()-30,c.getGreen()-30,c.getBlue());
		//Recursion while pixel > 3
		if(length > 3) {
			s1 = new VariegatedStairs(l1,length/2,canvas,c1);
			s2 = new VariegatedStairs(l2,length/2,canvas,c1); 
		}
		//Stop Recursion by drawing empty squares
		else {
			s1 = new EmptyStairs(); 
			s2 = new EmptyStairs(); 
		}
		
		
	}
	
	public void move(double dx, double dy) {
		//Move the current square then recursivley move the rest of the squares.
		sq.move(dx, dy);
		s1.move(dx, dy);
		s2.move(dx, dy);
	}

}
