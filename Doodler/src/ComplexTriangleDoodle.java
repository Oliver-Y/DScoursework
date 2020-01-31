import objectdraw.*;
import java.awt.Color;

public class ComplexTriangleDoodle implements TriangleDoodle {
	//Lines of Base middle triangle
	Line s1; 
	Line s2; 
	Line s3;
	//TriangleDoodle so move can be called for both ComplexTriangleDoodle + EmptyTriangleDoodle

	TriangleDoodle t1;
	TriangleDoodle t2; 
	TriangleDoodle t3; 
	
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
