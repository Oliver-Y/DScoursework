import java.util.ArrayList;

/**
 * Thought Questions: 
 * 1. I would write a class called simulator that'll remove all the WorldMap functionalities (since a visual repersentation probably
 * isn't necessary) and instead write a method that would determine when 1 species has won (is the lone species populating the world). 
 * Then I would simulate the matchup between two species starting the selection of
 *  Creatures at the same position everytime and seeing who wins
 * more often. These simulations may not provide a result, as perhaps there might be deadlock situations where species will co-inhabit/
 * coexists in the world. For example perhaps flyTraps and Food will just sit in their respective corner and nothing will happen. 
 * To deal with these scenarios, I'll probably write a time-out function so that it restarts the simulation 
 * when such scenarios occur. With a big enough sample size, I hope the simulation class will be able to give out an answer (as long
 * as repeated deadlocks don't occur, in which case, I'll have to rethink the starting positions and the given set up won't return
 * an answer)
 * 
 * 
 * 2. the JIT compiler takes Bytecode and converts it into Native machine language. This machine language is unique to the architecture
 * of each computer. JIT is apart of the virtual machine and often integrated in Java coding environments to optimize the process
 * of reading Java bytecode. The JIT turns frequently used byte code into intermediary machine code allowing the computer to
 * finally process the program. However
 * the JIT doesn't compile everything, but rather compiles the most commonly often-used methods. The more dynamic/constantly
 * changing parts of the program remain interpreted by the JVM, while more-oft used functions are directly called by their native
 * machine code. In doing so, the JIT is able to optimize parts of the program and reduce runtime. 
 * 
 * 
 * 3. Yes I agree with him, because often, having direct access and being able to mess with the instruction pointer of a program can be 
 * dangerous. By mishandling the pointer, it could point to empty spots in memory (in my case, index out of bound for the Creatures 
 * program set, thank god!) or even worse could point to spots in memory that actually have important instructions
 * for the operating system. In the process, perhaps executing dangerous commands or even crashing your computer. Thus, 
 * the go command simply offers too much flexibilty and risks in manipulating the instruction pointer of a given program. 
 * When working with the Creature class, problems with the instruction pointer were especially hard to debug, simply because
 * it wasn't clear what branching command or IP increment was sending it to the wrong places. And, go commands, due to its
 * ease to use and flexibility in changing the IP, offers a great degree of variance and places for error.
 * Therefore,because of how easy the gotto command makes it to mess up the instruction pointer, I agree with Edsgar Dijska. 
 * 
 */



/**
* This class controls the simulation. The design is entirely up to
* you. You should include a main method that takes the array of
* species file names passed in and populates a world with species of 
* each type.
* <p>
* Be sure to call WorldMap.pause every time
* through the main simulation loop or else the simulation will be too fast
* and keyboard / mouse input will be slow. For example: 
* <pre>
*	public void simulate() {
*	for (int rounds = 0; rounds < numRounds; rounds++) {
*           giveEachCreatureOneTurn();
*           WorldMap.pause(100);
*         }
*	}
* </pre>
*/

class Darwin {
	

	/**
	* The array passed into main will include the arguments that 
	* appeared on the command line. For example, running "java 
	* Darwin Hop.txt Rover.txt" will call the main method with s 
	* being an array of two strings: "Hop.txt" and "Rover.txt". 
	*/
	public static void main(String s[]) {
		WorldMap.createWorldMap(30,30); 
		WorldMap.pause(100); 
		//Create a world 
		//Load in the Species 
		//Create the creatures based on species 
		
		//Intialize Varaibles
		World<Creature> w = new World<Creature>(30,30); 
		Species test = new Species("Flytrap.txt"); 
		Species test2 = new Species("Rover.txt"); 
		ArrayList<Creature> creatins = new ArrayList<Creature>(); 
		//Create 7 random Flytrap
		for(int i = 0; i < 7; ++i) {
			int x = (int)(Math.random() * 30); 
			int y = (int)(Math.random() * 30);
			Position start = new Position(x,y); 
			creatins.add(new Creature(test,w,start,Position.WEST)); 
		}
		
		//Create 7 random Rover
		for(int i = 0; i < 8; ++i) {
			int x = (int)(Math.random() * 30); 
			int y = (int)(Math.random() * 30);
			Position start = new Position(x,y); 
			creatins.add(new Creature(test2,w,start,Position.WEST)); 
		}
		
		/* Position start = new Position(15,29); 
		Position start2 = new Position(10,10); 
		Creature c = new Creature(test,w,start,Position.WEST);
		Creature c1 = new Creature(test2,w,start2,Position.EAST);
		creatins.add(c); 
		creatins.add(c1); */
		//Program keeps running until hop infect left right
		
		//Take a turn 
		while(true) {
			for(int i = 0; i < creatins.size(); ++i) {
				creatins.get(i).takeOneTurn(); 
				WorldMap.pause(1);
			}
		}
		
	}
	
}
