/**
 * Thought Questions:
 * 
 * 1. OnMouseMoved is used to track the position of the cursor even when the mouse
 * has not been pressed. OnMouseEntered and onMouseExited will trigger
 * whenever the mouse enters or leaves the canvas. A good example project would be controlling
 * something, take for example a snake, to move on the screen with the cursor.
 * OnMouseEntered would be triggered when the mouse enters the canvas, which would be a good
 * time to initialize the snake. Then, with every change in the cursor(without a click), 
 * the snake will be able to change the location and effectively track the cursor. Lastly,
 * when the cursor leaves the campus, onMouseExit will trigger and we can hide the snake.
 * Thus, using these three listeners, you can effectively control an object on the canvas. 
 * 
 * 
 * 2. The objectDraw.rect probably defines what a rectangle is. Since there are 2
 * different rectangle classes within object draw (FilledRect,FramedRect).
 * The rectangle class probably overwrites methods such as moveTo or setSize 
 * and has field to keeps track of the 4 points of a rectangle. 
 * moveTo and setSize should be overwritten since moving 
 * all the points on a rectangle and changing the size of a rectangle will probably
 * be different for other shapes. Thus, the purpose of objectDraw.rect
 * is probably to provide a general guideline (through fields and methods) 
 * of what a rectangle is, with a few methods common specific to rectangles, 
 * and is meant to be extended and specifically implemented
 * for different rectangle classes such as FilledRect or FramedRect.
 * 
 * 3. 
 * RemovefromCanvas means the object is permanently deleted from the Canvas. While hide will
 * only temporarily hide it from the canvas. Meaning, once an object is hidden, it can 
 * reappear using the show method. On the other hand, once an object has been removed from 
 * Canvas no method can reappear. RemovefromCanvas should be used if an object is not
 * needed anymore. Hide should be used if the object is needed in the future flow of the code.
 * 
 */


/**
 * Extension:
 * There are 2 constructors for Gallows and Man. One will create a normal stick figure with 
 * Mr.Gnozzio's head imported as an Image. This constructor will take in 1 images, along
 * with starting position, and canvas. The body of the stick figure will swap colors 
 * randomly with each incorrect guess. 
 * The second constructor will create a version of Mr.Gnozzio with a really buff body. 
 * This constructor will take 5 images in the constructor, along with starting position
 * and canvas.
 * 
 * Currently the default in the initialization code is the second constructor. 
 * 
 */

import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random; 

import javax.imageio.ImageIO;

import objectdraw.*;
public class Hangman2 extends WindowController  implements KeyListener{

	protected String word = "";
	protected char[] letters;
	protected char[] puzzleLetters;
	protected boolean setup;
	protected int lettersRemaining;
	protected int playerNum = 0;
	protected boolean found_letter;
	protected char[] reptitions; 
	protected Image i;
	protected Image ra; 
	protected Image la; 
	protected Image rl; 
	protected Image ll; 
	protected Image body; 
	protected Random rand; 
	
	// GUI Elements
	protected Text label;
	protected Text buttonText;
	protected FramedRect button;
	protected Text puzzle;
	protected Gallows gallows;
	
	protected static final int MAX_INCORRECT = 4;
	protected static final int WINDOW_SIZE = 600;
	protected static final int TEXT_OFFSET = 10;
	protected static final int PUZZLE_OFFSET = 120;
	protected static final int BUTTON_WIDTH = 200;
	protected static final int BUTTON_HEIGHT = 40;
	
	
	
    public void begin()
    {
 
            // Get ready to handle key focuses
            requestFocus();
            addKeyListener(this);
            canvas.addKeyListener(this);
            
            //Setting up Images to be passed to constructors
            i = getImage("gnozzio.jpg");
            ra = getImage("RightArm.png");
            la = getImage ("LeftArms.png"); 
            ll = getImage ("Leftleg.png"); 
            rl = getImage ("RightLeg.png"); 
            body = getImage("Body.png"); 
            
            //Setting up Random numbers for Color generator
            rand = new Random(); 
            label = new Text("Player " + getPlayerNum() + ", please enter a word.", TEXT_OFFSET, TEXT_OFFSET, canvas);
            label.setFontSize(20);
            
            setup = true;
            
            button = new FramedRect(
            		WINDOW_SIZE/2 - BUTTON_WIDTH/2,
            		WINDOW_SIZE/2 - BUTTON_HEIGHT,
            		BUTTON_WIDTH,
            		BUTTON_HEIGHT,
            		canvas);
            button.setColor(Color.RED);
            button.hide();
            
            buttonText = new Text("Click when finished.", 
            		button.getX() + BUTTON_WIDTH/2, 
            		button.getY() + BUTTON_HEIGHT/2, 
            		canvas);
            buttonText.move(buttonText.getWidth()/-2.0, buttonText.getHeight()/-2.0);
            buttonText.hide();
            
            puzzle = new Text("Puzzle to Solve: ", WINDOW_SIZE/2, WINDOW_SIZE - PUZZLE_OFFSET, canvas);
            puzzle.setFontSize(30);
    		puzzle.moveTo(WINDOW_SIZE/2-puzzle.getWidth()/2, puzzle.getY());
    		
    
    }
 
    
    // Required by KeyListener Interface.
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    
    public void keyTyped(KeyEvent e)
    {
    	if(setup) {
    		if(word.isEmpty()) {
    			puzzle.setText("Puzzle to Solve:");
    	
    		}
    		char letter = e.getKeyChar();
    		if (Character.isLetter(letter)) {
    			puzzle.setText(puzzle.getText() + " _");
    			word += letter; 
	    		/* TODO: Update the puzzle text with the letter
	    		 * that was just entered.
	    		 */
	    		puzzle.moveTo(WINDOW_SIZE/2-puzzle.getWidth()/2, puzzle.getY());
	    		if(word.length() == 1) {
	    			button.show();
	    			buttonText.show();
	    		}
    		} else if (e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE && ! word.isEmpty()) {
	    		puzzle.moveTo(WINDOW_SIZE/2-puzzle.getWidth()/2, puzzle.getY());
    			puzzle.setText(puzzle.getText().substring(0,puzzle.getText().length()-3));
    			word = word.substring(0,word.length()-1);
    			/* TODO: Add logic to process the delete key having 
    			 * been pressed, adjusting the possition of the puzzle
    			 * text accordingly.  Hide the "Click when finished" button 
    			 * if the word has been deleted entirely. 
    			 */
    		}
    	} else if (gallows.isAlive() ){ 
    		/* TODO: Add logic to check if the letter
    		 * is in the word. Update the guess word
    		 * if the letter is found, otherwise hang
    		 * the man.
    		 */
    		
    		// -32 to get from lowercase to uppercase from ASCII
    		char guessedLetter = (char)((int)e.getKeyChar() - 32);
    		
    		//initialize array
    		if(lettersRemaining == word.length()) {
    			for (int i = 0; i < word.length() * 2; ++i) { 
    				if(i % 2 == 0) {
        				puzzleLetters[i] = ' ';
    				}
    				else {
    					puzzleLetters[i] = '_'; 
    				}
    			}
    		}
    		//Game loop
    		if(gallows.isAlive()) {
    			//Keeping track of Reptitions
    			for (int i = 0; i < reptitions.length; ++i) {
    				if (guessedLetter == reptitions[i]) {
    					//to counteract the decrement done in the match_finding loop below
    					lettersRemaining++; 
    					found_letter = false;
    				}
    			}
    			for (int i = 0; i < word.length(); ++i) {
    				//If a match is found update the words
    				if(word.charAt(i) == guessedLetter) {
    					//Keeps track of the display letters. 
    					//Has to be 2*i + 1 is the offset element because it also holds _ 
    					//and spaces for the words.
    					puzzleLetters[i*2+1] = guessedLetter; 
    					String temp = new String(puzzleLetters); 
    					
    				
    					// 0 - 17 is the "Puzzle to solve" part" temp is just the
    					//String created based off puzzle letters
    					puzzle.setText(puzzle.getText().substring(0,17) + temp);
    					
    					
    					//Set up counters and flags, keep track of repetitions
    					lettersRemaining--;
    					found_letter = true; 
    					reptitions[i] = guessedLetter; 
    				}
    			}
    			//Flag if a match is not found, hang the man 
    			if(!found_letter) {
    				//Random Color Generator!
    				gallows.changeColor(new Color(rand.nextInt(254)+1,rand.nextInt(254)+1,rand.nextInt(254)+1));
    				gallows.hang();
    			}
    			
				found_letter = false;
				//Win condition: if no letters remain... well you guessed them all
				if(lettersRemaining == 0) {
					//Restart game loop
					updateGuessWord(guessedLetter); 

				}
				
    		}
    		//Lose Condition: The gallows is dead, Change the graphics.
    		if (!gallows.isAlive()) {
    			label.setText("Game Over! Player " + ((getPlayerNum() + 1) % 2)  + " wins."); 
    			puzzle.setText("The word was " + word.toLowerCase());
    			
    		}

    	}
    }
    
    public void onMousePress(Location point) {
		/* TODO:  Add logic to exit setup mode and
		 * start Gameplay
		 */
    	if (button.contains(point) && ! word.isEmpty()) {
    	
    		//2 options of constructing Gallows. 
    		// One is with the normal constructor that will give 
    		//the relatively normal stickman (with Gnozzio head), 
    		//the 2nd constructor one will release GnozzDawgs true form. (The buff form)
    		
    		
    		// gallows = new Gallows(i,WINDOW_SIZE/4.0, WINDOW_SIZE * 2.0/3, canvas); normal constructor
    		gallows = new Gallows(i,ra,la,rl,ll,body,WINDOW_SIZE/4.0, WINDOW_SIZE * 2.0/3, canvas); // Buff constructor
    		word = word.toUpperCase(); 
    		setup = false;
 
    		//Keep track of ongoing alternating player number 
    		playerNum += 1; 
    		playerNum = playerNum % 2; 
    		
    		// Hide + Set up  graphics
    		button.hide();
            buttonText.hide();
    		label.setText( "Player " + getPlayerNum() + ", please type a key to guess a letter");

            //Set up necessary arrays for HangMan logic 
            lettersRemaining = word.length(); 
    		puzzleLetters = new char[word.length()*2];
    		reptitions = new char[word.length()] ;


    	}
    }
    
    public int getPlayerNum() {
    	return playerNum + 1;
    }
	
  
    
    public void updateGuessWord(char guessedLetter) {
    	/* TODO:  Add logic to update the guessed word.
    	 * Also include logic to test if the puzzle has
    	 * been solved (allowing the user to enter a new
    	 * word for their opponent if the puzzle is complete). 
    	 */	
    	
    	//Change the graphics
		label.setText("Congradulations! You solve the puzzle. Enter the new word.");
		gallows.clear();
		//restart the setup 
		setup = true; 
		word = "";
    }
    
    public static void main(String[] args) { 
        new Hangman2().startController(WINDOW_SIZE, WINDOW_SIZE); 
	}
	
}
