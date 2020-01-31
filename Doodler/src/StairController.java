import objectdraw.*; 
import java.awt.Color;

public class StairController extends WindowController {
	private StairInterface doodle;
	private Location current;
	
	public void begin() {
		//Initialize
		Location v1 = new Location(100, 300);
		doodle = new VariegatedStairs(v1,128,canvas,new Color(225,225,255)); 
		resize(800,800);	
	}
	
	//Stays the same as Triangle Controller
	public void onMousePress(Location l) {
		current = l;
	}
	
	public void onMouseDrag(Location l) {
		double dx = l.getX() - current.getX();
		double dy = l.getY() - current.getY();
		doodle.move(dx, dy);
		current = l;
	}
	
    public static void main(String[] args) {  
        new StairController().startController(800, 800); 
	}
	
}
