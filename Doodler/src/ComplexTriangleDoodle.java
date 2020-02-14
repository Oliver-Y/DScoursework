import objectdraw.*;
import java.awt.Color;
/*
 * 
 * 
 * 1. Program A will take less time than Program B when the time is smaller than 29.7181 seconds. After solving for n when setting the 
 * two equations equal to each other, 2^n/1000 (Program A) passes 1000* n^2 (Program B) at t = 29.7181 
 * 
 * 2. The complexity is O(n), because the program would have to run through the entire String once to copy and then the extra 
 * characters B will take a constant time so the complexity is O(n)
 * 
 * 3. public int Binary (int decimal){
 *    if( decimal == 0){
 *        return 0;
 *    }
 *    else{
 *       return decimal % 2 + 10 * (Binary(decimal/2)); 
 *    }
 * }
 * 
 * The base case is when the number hits 0. Otherwise use modulus 2 to get the smallest bit in the number. Then divide the decimal number 
 * by 2 but multiply it by 10 to place the next bit(modulus 2) in the right position (ex: 10s, 100s, thousands)
 * 
 * 
 * 
 * 
 * 
 * 
 */

public class ComplexTriangleDoodle implements TriangleDoodle {
	//Lines of Base middle triangle
	private Line s1; 
	private Line s2; 
	private Line s3;
	//TriangleDoodle so move can be called for both ComplexTriangleDoodle + EmptyTriangleDoodle

	private TriangleDoodle t1;
	private TriangleDoodle t2; 
	private TriangleDoodle t3; 
	
	public ComplexTriangleDoodle(Location l, Location r, Location t, DrawingCanvas canvas) {
		//Figure out distances for Triangle
		double d1 = l.distanceTo(r);
		double d2 = r.distanceTo(t);
		double d3 = t.distanceTo(l); 
		//one triangle in the middle
		Location mt = midpoint(l,r); 
		Location ml = midpoint(l,t); 
		Location mr = midpoint(r,t); 
		//Drawing the middle Triangle
		s1 = new Line(mt,mr,canvas); 
		s2 = new Line(mr,ml,canvas); 
		s3 = new Line(ml,mt,canvas); 
		//Checking to see if it is 6 pixels
		if(d1 > 6.00 && d2 > 6.00 && d3 > 6.00) {
			//Recursive create 3 Triangles
			t1 = new ComplexTriangleDoodle(l,mt,ml,canvas); 
			t2 = new ComplexTriangleDoodle(mt,r,mr,canvas);
			t3 = new ComplexTriangleDoodle(ml,mr,t,canvas);
		}
		else {
			//Base Case 
			t1 = new EmptyTriangleDoodle(); 
			t2 = new EmptyTriangleDoodle(); 
			t3 = new EmptyTriangleDoodle(); 
		}
	}
	private Location midpoint(Location a, Location b) {
		return new Location ((a.getX() + b.getX())/2, (a.getY() + b.getY())/2); 
	}
	public void move(double dx, double dy){
		//Move all the triangle using recursion 
		t1.move(dx, dy);
		t2.move(dx, dy);
		t3.move(dx, dy);
		//Move the lines of the current triangle
		s1.move(dx, dy);
		s2.move(dx, dy);
		s3.move(dx, dy);
	}
	

}
