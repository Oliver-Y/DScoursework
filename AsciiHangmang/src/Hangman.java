import java.io.Console;
import java.util.Scanner;

/* Thought Questions Lab 1.1 
 * 1. If a is an Integer then the expression is true. Evaluating the right side, a/7 is going to truncate the remainder 
 * and *7 isn't going to bring back the truncated numbers. Thus, a/7*7 will be less than a since the remainder was lost
 * in integer division. Thus a - (a/7*7) is going to give the remainder of a/7 which is modulus. 
 * 
 * However, in Java if a is a double, then a/7 is not going to truncate the remainder. 
 * Thus, a- a/7*7 will always be 0 which is not the remainder. More generally, as long as truncation happens,
 * the equation will be true.
 
 * 2. If u take the sum and subtract 1, then Sunday will become 0. Since subtracting 1 will shift all the remainders
 * back by 1. EX: If the sum was 8, the modulus is 1. 8-1 = 7, the modulus for 7 is 0. 
 * Thus if Sunday would have had a remainder of 1, it will now have a remainder of 0. 
 * 
 * 
 * 
 * 
 * 3. Leap years are already accounted for in the algorithm because we divide the years by 4 and truncate. Meaning on
 * leap years, the (divide the years by 4) part of the algorithm increments, effectively shifting ALL dates 
 * to the right by an extra one to account for the leap year. However the extra date, February 29th,
 * doesn't affect dates before February, and only effects dates after February 29th. Meaning that dates before February 29th
 * don't actually have to be adjusted to the right additionally because they are not affected by leap years.
 * Therefore while all the dates where shifted to the right by the (divide years
 * by 4) part of the algorithm, in reality January and February shouldn't have been. 
 * Therefore, we subtract one from all dates in January and February to compensate for the extra shift.
 * 
 * The reason the subtract one doesn't happen on non-leap years, is because February 29th will affect
 * all January and February dates in the FOLLOWING years. Thus, the leap year part of the equation becomes justified
 * for all January and February dates. Since the only dates leap years DON'T affect are the January and February dates
 * WITHIN in year, you have to subtract one from all January and February dates within a leap year.
 * 
 * 

 * 
 * 
 * 
 */




public class Hangman {
	public static void main(String[] args) {
		System.out.println("Welcome to the ASCII Version of Hangman!");
		
		Console c = System.console();
		Scanner s = new Scanner(System.in);
		char[] letters;
		String guess;
		//Keeping track of score for win condition
		int score = 0; 
		//Flag for seeing if a match has been found. If not, display the Hangman
		boolean h_flag = false;
		//Displays partially guessed word
		char[] output; 
		//Gnozz Dawg Prompting stuff 
		String prompt = "Please enter a secret word: ";
		if(c != null) {
			letters = c.readPassword(prompt);
			for(int i=0; i<letters.length; i++) {
				letters[i] = Character.toUpperCase(letters[i]);
			}
		} else {
			System.out.println("For best results, please run this from the command line.");
			System.out.print(prompt);
			letters = s.nextLine().trim().toUpperCase().toCharArray();
			for(int i=0; i<10000; i++) System.out.println();
		}
		//Array to keep track of reptitions 
		char[] a_guessed = new char[letters.length]; 
		int a_guessed_counter = 0; 
		//Word Display. Twice the length because it includes all the letters and a space in between each 
		output = new char[letters.length*2];
		//Formatting new line
		System.out.println();
		//Create Gallow
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

		//Game Loop: As long as the gall is alive or some win condition breaks out of it.
		while (gall.isAlive()) {
			//Prompt for line + Printing
			guess = s.nextLine();
			System.out.println("Please guess a letter: "+ guess.toUpperCase().charAt(0));
			
			//Check for Repetition of matched letters. A_guessed will store all already correct and repeated letters
			for (int i = 0; i < a_guessed_counter; ++i) {
				if(a_guessed[i] == guess.toUpperCase().charAt(0)) {
					h_flag = true; 
				}
			}
			
			//Check for Match
			for (int i = 0; i < letters.length; i++) {
				if(guess.toUpperCase().charAt(0) == letters[i]) {
					// *2 + 1 is to account for the spaces in between characters on the word display output
					output[i*2+1] = letters[i]; 
					//Add the letter to already_guessed array
					a_guessed[a_guessed_counter++] = letters[i]; 
					//Clear the character from the letters array so repeated characters don't continue to score
					letters[i] = 0; 
					score++;
					//Toggle H_flag because match has been found
					h_flag = true;
				}
			}
			
			//Only display Hangman if nothing is a match
			if (!h_flag) {
				gall.hang(); 
				System.out.println(); 
				System.out.print(gall.toString());
			}
			//Clear the flag for each cycle.
			h_flag = false; 
			
			//Check too see if word has been guessed. 
			if(score >= letters.length) {
				System.out.println("Success!  Player 2 wins!"); 
				break; 
			}
			//Gall method to see if its still alive
			if (!gall.isAlive()) {
				System.out.println("Game over! Player 1 wins!");
				break;
			}
			//Prints
			System.out.print("Puzzle to solve:");
			for (int i = 0; i < output.length; ++i) {
				System.out.print(output[i]);
			}
			//Formatting things
			System.out.println(" ");
		}
		
	}

}
