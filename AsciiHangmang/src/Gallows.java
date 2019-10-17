import java.util.Arrays;

public class Gallows {
	
	/* Gallows look like this:
	 *    ____
	 *   |    |
	 *   |    O
	 *   |   \|/
	 *   |   / \
	 * __|__
	 */
	
	protected Man man;
	protected char[] frame;

	public Gallows() {
		/* TODO: Initialize instance variables and 
		 * otherwise construct the Gallows object.
		 */
		int counter = 0;
		man = new Man();
		frame = new char[60];
		for (int i = 0; i < 60; ++i) {
			if (counter == 9) {
				frame[i] = '\n';
				counter = 0;
			}
			else {
				frame[i] = ' '; //(char) ((int)'a' + i);  //' '; 
				counter++;
			}
		}
		//Construct the frame when initialized
		this.makeCenterPost();
		this.makeBeam();
		this.makeBase();
		this.makeRope();
		System.out.print(this);
	}
	
	public void makeCenterPost() {
		/* TODO: Modify the frame to include
		 * the central post.
		 */
		for (int i = 12; i < 53; i+=10) {
			frame[i] = '|';
		}
		
	}
	
	public void makeBeam() {
		/* TODO: Modify the frame to include
		 * the top beam. 
		 */
		for (int i = 3; i < 7; ++i) {
			frame[i] = '_';
		}
		
	}
	
	public void makeBase() {
		for (int i = 50; i < 55; ++i) {
			if (i != 52) {
				frame[i] = '_';
			}
		}
		/* TODO: Modify the frame to include the
		 * base of the gallows. 
		 */
		
	}
	
	public void makeRope() {
		frame[17] = '|'; 
		/* TODO: Modify the frame to include the rope. */
	}
	
	public void hang() {
		man.hang(); 
		char[] temp = man.toCharArray(); 
		int counter = 0;
		for (int i = 24; i < 49; ++i) {
			if ((i%10) > 5) {
				frame[i] = temp[counter++];
			}
		}
	}
	
	public boolean isAlive() {
		return man.isAlive();
		
	}
	
	public String toString() {
		/* TODO: Render the hangman as a string
		 */
		String s = new String(frame);
		return s;
	}
	
	/* This code is included to allow you to test the
	 * Gallows independently from the Hangman code. 
	 */
	public static void main(String[] args) {
		Gallows g = new Gallows();
		for(int i=0; i< Man.MAX_INCORRECT; i++) {
			g.hang();
			System.out.println(g);
		}
	}
}
