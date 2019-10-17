import objectdraw.*;
import java.awt.Image; 
import java.awt.Color; 

public class Man {
	protected FramedOval head;
	protected DrawableInterface[] bodyParts;
	
	protected static final int MAX_INCORRECT = 6;
	protected static final int HEAD_SIZE = 80;
	protected static final int BODY_SIZE = 80;
	protected static final int ARM_LENGTH = 50;
	protected int numIncorrect;
	
	//Constructor 1: Creates a stickman with Gnozzio's head
	public Man(double xPos, double yPos, DrawingCanvas canvas,Image i) {
		//Initialize all lines and circles.
		/* TODO: Initialize all of the man's body parts.
		 * Then, use the clear method to hide them all. 
		 * The given (xPos, yPos) specifies the coordinates
		 * for the top of the man's head.
		 */
		bodyParts = new DrawableInterface[6]; 
		//bodyParts[0] = new FramedOval(xPos,yPos,HEAD_SIZE,HEAD_SIZE,canvas);
		bodyParts[0] = new VisibleImage(i,xPos,yPos,HEAD_SIZE,HEAD_SIZE,canvas); 
		bodyParts[1] = new Line(xPos+HEAD_SIZE/2,yPos+HEAD_SIZE,xPos+HEAD_SIZE/2,yPos+BODY_SIZE+HEAD_SIZE,canvas);
		bodyParts[2] = new AngLine(xPos+HEAD_SIZE/2,yPos+HEAD_SIZE+40,(double)ARM_LENGTH,Math.PI/6,canvas); 
		bodyParts[3] = new AngLine(xPos+HEAD_SIZE/2,yPos+HEAD_SIZE+40,(double)ARM_LENGTH,2.61,canvas); 
		bodyParts[4] = new AngLine(xPos+HEAD_SIZE/2,yPos+HEAD_SIZE+BODY_SIZE,(double)ARM_LENGTH,5.2,canvas); 
		bodyParts[5] = new AngLine(xPos+HEAD_SIZE/2,yPos+HEAD_SIZE+BODY_SIZE,(double)ARM_LENGTH,3.9,canvas); 
		

	}
	//Constructor 2: Creates a man with 5 passed in Images of body parts , along with position and canvas
	public Man(Image i,Image ra,Image la,Image rl,Image ll,Image body,double xPos, double yPos, DrawingCanvas canvas) {
		//Initialize all lines and circles.
		
		/* TODO: Initialize all of the man's body parts.
		 * Then, use the clear method to hide them all. 
		 * The given (xPos, yPos) specifies the coordinates
		 * for the top of the man's head.
		 */
		
		bodyParts = new DrawableInterface[6]; 
		//bodyParts[0] = new FramedOval(xPos,yPos,HEAD_SIZE,HEAD_SIZE,canvas);
		bodyParts[0] = new VisibleImage(i,xPos,yPos,HEAD_SIZE,HEAD_SIZE,canvas); 
		bodyParts[1] = new VisibleImage(body,xPos+HEAD_SIZE/2-80,yPos+HEAD_SIZE,160,80,canvas); 
		bodyParts[2] = new VisibleImage(ra,xPos+HEAD_SIZE/2+80,yPos+HEAD_SIZE,80,80,canvas);
		bodyParts[3] = new VisibleImage(la,xPos+HEAD_SIZE/2-160,yPos+HEAD_SIZE,80,80,canvas); 
		bodyParts[4] = new VisibleImage(rl,xPos+HEAD_SIZE/2,yPos+HEAD_SIZE+80,80,160,canvas); 
		bodyParts[5] = new VisibleImage(ll,xPos+HEAD_SIZE/2-80,yPos+HEAD_SIZE+80,80,160,canvas); 

	}
	
	public void clear() {
		/* TODO: Hide all of the man's body parts */
		for (int i = 0; i < bodyParts.length; ++i) {
			bodyParts[i].hide(); 
		}
	}
	
	public void hang() {
		/* TODO: Hang the man */
		bodyParts[numIncorrect++].show(); 
	}
	
	public boolean isAlive() {
		/* TODO: Return true if the man is not fully
		 * hanged.  Otherwise, return false. 
		 */
		if(numIncorrect >= MAX_INCORRECT) {
			return false;
		}
		return true;
	}
	//Changes the color of each body part randomly
	public void changeColor(Color c) {
		for (int i = 0; i < bodyParts.length; ++i) {
			bodyParts[i].setColor(c);
		}
	}
	
}
