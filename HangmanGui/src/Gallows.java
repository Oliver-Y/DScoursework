import objectdraw.*;
import java.awt.Image;
import java.awt.Color; 

public class Gallows {

	protected FilledRect base;
	protected FilledRect beam;
	protected FilledRect crossbeam;
	protected FilledRect rope;
	protected Man man;
	
	protected static final int BEAM_WIDTH = 10;

	//Constructor 1: Takes in Gnozzdawgs head and normal stick figure lines
	public Gallows(Image i,double xPos, double yPos, DrawingCanvas canvas)
	{
		/* TODO: Initialize the instance variables that constitute the 
		 * frame of the gallows. The given (xPos, yPos) specifies the
		 * coordinates of the bottom left corner of the gallows.  
		 */
		
		base = new FilledRect(xPos-75,yPos,150,BEAM_WIDTH,canvas);
		beam = new FilledRect(xPos,yPos - 350,BEAM_WIDTH,350,canvas);
		crossbeam = new FilledRect(xPos,yPos-350,BEAM_WIDTH * 16 + 40,BEAM_WIDTH,canvas);
		rope = new FilledRect(xPos + BEAM_WIDTH * 16 + 40,yPos-350,BEAM_WIDTH,yPos - BEAM_WIDTH * 25 - 50,canvas);
		man = new Man(xPos + BEAM_WIDTH * 16, yPos - BEAM_WIDTH * 25, canvas,i);
		man.clear();
	}
	//Constructor 2: Takes in 5 Images (body parts) starting position and canvas
	public Gallows(Image i,Image ra,Image la, Image rl, Image ll, Image body, double xPos, double yPos, DrawingCanvas canvas)
	{
		/* TODO: Initialize the instance variables that constitute the 
		 * frame of the gallows. The given (xPos, yPos) specifies the
		 * coordinates of the bottom left corner of the gallows.  
		 */
		
		base = new FilledRect(xPos-75,yPos,150,BEAM_WIDTH,canvas);
		beam = new FilledRect(xPos,yPos - 350,BEAM_WIDTH,350,canvas);
		crossbeam = new FilledRect(xPos,yPos-350,BEAM_WIDTH * 16 + 40,BEAM_WIDTH,canvas);
		rope = new FilledRect(xPos + BEAM_WIDTH * 16 + 40,yPos-350,BEAM_WIDTH,yPos - BEAM_WIDTH * 25 - 50,canvas);
		man = new Man(i,ra,la,rl,ll,body,xPos + BEAM_WIDTH * 16, yPos - BEAM_WIDTH * 25, canvas);
		man.clear();
	}
	
	
	
	
	
	
	public void hang() {
		man.hang();
	}
	
	public boolean isAlive() {
		return man.isAlive();
	}
	//Hide everything
	public void clear() {
		/* TODO: Hide all of the elements of the 
		 * gallows, and clear the man.
		 */
		base.hide();
		beam.hide(); 
		crossbeam.hide();
		rope.hide(); 
		man.clear(); 

	}
	
	//Change the color of the man based off Color c
	public void changeColor(Color c) {
		man.changeColor(c);
	}
}
