import objectdraw.*;
import java.awt.Color;

public class GasketController extends WindowController {

	private TriangleDoodle doodle;
	private Location current;
	
	public void begin() {
		//Initialize
		Location v1 = new Location(100, 600);
		Location v2 = new Location(500, 600);
		Location v3 = new Location(300, 264);
		doodle = new ComplexTriangleDoodle(v1, v2, v3, canvas);
		resize(800,800);	
	}
	
	public void onMousePress(Location l) {
		current = l;
	}
	
	public void onMouseDrag(Location l) {
		//Move doodle by change in Mouse position
		double dx = l.getX() - current.getX();
		double dy = l.getY() - current.getY();
		doodle.move(dx, dy);
		current = l;
	}
	//Start Controller
    public static void main(String[] args) {  
        new GasketController().startController(800, 800); 
	}
	
}
