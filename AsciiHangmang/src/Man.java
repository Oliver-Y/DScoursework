import java.util.Arrays;
public class Man {
	
	/*  An ASCII Hangman looks like this:
	 *   O 
	 *  \|/
	 *  / \
	 */

	protected static final int MAX_INCORRECT = 6;
	protected int numIncorrect;
	protected char[] body;
	
	public Man() {
		/* TODO: Intiaialize the Man object */
		numIncorrect = 0; 
		body = new char[12];
		int counter = 0;
		for (int i = 0; i < 12; ++i) {
			if (counter == 3) {
				body[i] = '\n';
				counter = 0;
			}
			else {
				body[i] = ' '; 
				counter++; 
			}
		}
	}
	
	public boolean isAlive() {
		/* TODO: Check if the man is alive */
		if(numIncorrect >= MAX_INCORRECT) {
			return false;
		}
		return true; 
	}
	
	public void hang() {
		/* TODO: modify the man data to reflect
		 * a new incorrect guess. 
		 */
		switch (numIncorrect) {
		case 0: 
			body[1] = 'O'; 
			break;
		case 1: 
			body[5] = '|'; 
			break;
		case 2: 
			body[4] = '\\'; 
			break;
		case 3: 
			body[6] = '/'; 
			break;
		case 4:
			body[8] = '/'; 
			break;
		case 5: 
			body[10] = '\\';
			break;
		}
		numIncorrect++;
	}
	
	public String toString() {
		/* TODO: Render the man as a string. */
		String s = new String(body);
		return s;
	}
	
	protected char[] toCharArray() {
		/* TODO: Return the relevant data */
		return body;
	}
	
	/* Again, this main method is provided to assist
	 * with testing. 
	 */
	public static void main(String[] args) {
		Man m = new Man();
		for(int i=0; i<Man.MAX_INCORRECT; i++) {
			m.hang();
		}
		System.out.println(m);
	}
	
}
