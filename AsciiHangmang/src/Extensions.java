import java.io.Console;
import java.util.Scanner;

/*
 * Extensions 
 * 1. Displays previous incorrect guesses
 * 2. If you type "CS" as your first guess, a CPU will randomly guess everything for you
 * 3. If you type "Sacrifice", it will reveal a character but also take one of your Hangman Lives. (can only be used once)
 * 4. Implemented option to play again to restart game w/ recursion
 * 5. Keeps track of the scores of overall games between 2 players. If they choose to play again, the roles of player1
 * and player 2 are swapped (meaning that player 1 is now the guesser and player 2 is the word_creator). This will happen
 * each round you type play again. at the end of each round, the score will be displayed.
 * 
 */

public class Extensions {
	public static void main(String[] args) {
		play(0,0);	
	}
	
	public static void play(int a, int b) {
		System.out.println("Welcome to the ASCII Version of Hangman!");
		Console c = System.console();
		Scanner s = new Scanner(System.in);
		//Sacrifice can only be used once
		boolean sacrifice_used = false; 
		char[] letters;
		String guess = "";
		//Keeping track of player a and b scores
		int player1 = a;
		int player2 = b; 
		//Keeping track of score for win condition
		int score = 0; 
		//Flag for seeing if a match has been found. If not, display the Hangman
		boolean h_flag = false;
		boolean r_flag = false;
		boolean CS_flag = false;
		//Displays partially guessed word
		char[] output; 
		char[] incorr_guess = new char[26]; 
		int incorr_guess_counter = 0; 
		
		//Gnozz Dawg Prompting stuff 
		String prompt = "Please enter a secret word: ";
		if(c != null) {
			letters = c.readPassword(prompt);
			for(int i=0; i<letters.length; i++) {
				letters[i] = Character.toUpperCase(letters[i]);
			}
			
		} 
		
		else {
		}

		
		System.out.println("For best results, please run this from the command line.");
		System.out.print(prompt);
		letters = s.nextLine().trim().toUpperCase().toCharArray();
		for(int i=0; i<10; i++) System.out.println();

		
		
		
		//Array to keep track of Repetitions 
		char[] a_guessed = new char[letters.length]; 
		int a_guessed_counter = 0; 
		//Word Display. Twice the length because it includes all the letters and a space in between each 
		output = new char[letters.length*2];
		//Formatting new line
		System.out.println();
		//Make Gallows
		Gallows gall = new Gallows();
		//Filling in Word Display.
		for (int i = 0; i < output.length; ++i) {
			if(i%2 == 0) {
				output[i] = ' ';
			}
			else {
				output[i] = '_';
			}
		}
		//Prompting + Printing
		System.out.print("Puzzle to solve:");
		for (int i = 0; i < output.length; ++i) {
			System.out.print(output[i]);
		}
		System.out.println(" ");

		
		char user_guess = ' ';
		
		//Game Loop: As long as the gall is alive or some win condition breaks out of it.
		while (gall.isAlive()) {
			//Prompt for line + Printing
			if(!CS_flag) {
				guess = s.nextLine();
				user_guess = guess.toUpperCase().charAt(0);
			}
			if(guess.equals("CS")) {
				CS_flag = true;
				user_guess = (char) (Math.random() *25 + 65);
			}
			if(guess.equals("sacrifice") && !sacrifice_used) {
				user_guess = letters[(int)(Math.random()*letters.length)];
				sacrifice_used = true;
				
			}

			
			System.out.println("Please guess a letter: "+ user_guess);
			
			//Check for Repetition of matched letters. 
			for (int i = 0; i < a_guessed_counter; ++i) {
				if(a_guessed[i] == user_guess) {
					h_flag = true; 
				}
			}
			
			//Check for Match
			for (int i = 0; i < letters.length; i++) {
				if(user_guess == letters[i]) {
					output[i*2+1] = letters[i]; 
					a_guessed[a_guessed_counter++] = letters[i]; 
					letters[i] = 0; 
					score++;
					//Toggle H_flag because match has been found
					h_flag = true;
				}
			}
			
			if(guess.equals("sacrifice")) {
				h_flag = false;
			}
			
			//Only display Hangman if nothing is a match
			if (!h_flag) {
				for (int i = 0; i < incorr_guess_counter; ++i) {
					//Checking for already guessed incorrect guesses
					if (incorr_guess[i] == user_guess) {
						r_flag = true;
					}
				}
				if(!r_flag) {
					incorr_guess[incorr_guess_counter++] = user_guess; 
				}
				gall.hang(); 
				System.out.println(); 
				System.out.print(gall.toString());
			}
			//Reset Every time
			h_flag = false; 
			
			//Check too see if word has been guessed. 
			if(score >= letters.length) {
				System.out.println("Success!  Guesser wins!"); 
				player2++;
				break; 
			}
			//Gall method to see if its still alive
			if (!gall.isAlive()) {
				System.out.println("Game over! Word_creator wins!");
				player1++;
				break;
			}
			//Prints
			System.out.print("Puzzle to solve:");
			for (int i = 0; i < output.length; ++i) {
				System.out.print(output[i]);
			}
			//Formatting things
			System.out.println(" ");
			System.out.print("Incorrect guess: ");
			for(int i = 0; i < incorr_guess_counter; ++i) {
				System.out.print(incorr_guess[i] +" ");
			}
			System.out.println();
		}
		System.out.println("Player 1 score: " + player1);
		System.out.println("Player 2 score: " + player2);
		System.out.println("play again?"); 
		String temp;
		temp = s.nextLine();
		if(temp.equals("yes")) {
			System.out.println("Swap Spots!");
			play(player2,player1);
		}
	}

}
