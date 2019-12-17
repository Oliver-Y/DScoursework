import java.io.*; 
import java.util.Scanner;

import structure.Assert;

import java.util.ArrayList;
import java.util.NoSuchElementException; 
/**
 * The individual creatures in the world are all representatives of some
 * species class and share certain common characteristics, such as the species
 * name and the program they execute. Rather than copy this information into
 * each creature, this data is recorded once as part of the description for
 * a species and then each creature can simply include the appropriate species
 * pointer as part of its internal data structure.
 * <p>
 * 
 * To encapsulate all of the operations operating on a species within this
 * abstraction, this provides a constructor that will read a file containing
 * the name of the creature and its program, as described in the earlier part
 * of this assignment. To make the folder structure more manageable, the
 * species files for each creature are stored in a subfolder named Creatures.
 * Thus, creating the Species for the file Hop.txt will causes the program to
 * read in "Creatures/Hop.txt".
 * 
 * <p>
 * 
 * Note: The instruction addresses start at one, not zero.
 */

public class Species {
	private String name; 
	private String color; 
	private ArrayList<Instruction> program; 
	
	/**
	 * Create a species for the given file. 
	 * @param fileName the name of the file containing the data for the species
	 * @pre fileName exists in the Creature subdirectory.
	 */
	public Species(String fileName) {
		program = new ArrayList<Instruction>();
		File file = new File ("/Users/oye20/Documents/GitHub/datastructures/DarwinStarter/Creatures/"+fileName); 
		try {
			Scanner s = new Scanner(file); 
			//Name and Color
			name = s.nextLine(); 
			color = s.nextLine(); 
			while(true){
				String temp; 
				try {
					temp = s.next(); 
				}
				// Break out if no elements can be found. 
				catch(NoSuchElementException e) {

					break;
				}
				Instruction i;
				switch(temp) {
				case "hop":
					i = new Instruction(Instruction.HOP,0,-1);
					break;
				case "left": 
					i = new Instruction(Instruction.LEFT,0,-1); 
					break;
				case "right": 
					i = new Instruction(Instruction.RIGHT,0,-1); 
					break;
				case "infect": 
					if(s.hasNextInt()) {
						i = new Instruction(Instruction.INFECT,0,Integer.parseInt(s.next())); 
					}
					else {
						i = new Instruction(Instruction.INFECT,0,1); 
					}
					break;
				case "ifempty": 
					i = new Instruction(Instruction.IFEMPTY,0,Integer.parseInt(s.next()));
					break;
				case "ifwall":
					i = new Instruction(Instruction.IFWALL,0,Integer.parseInt(s.next()));
					break;
				case "ifsame": 
					i = new Instruction(Instruction.IFSAME,0,Integer.parseInt(s.next()));
					break;
				case "ifenemy":
					i = new Instruction(Instruction.IFENEMY,0,Integer.parseInt(s.next()));
					break;
				case "ifrandom":
					i = new Instruction(Instruction.IFRANDOM,0,Integer.parseInt(s.next()));
					break;
				case "go":
					i = new Instruction(Instruction.GO,Integer.parseInt(s.next()));
					break;
				case "iftwoenemy":
					i = new Instruction(Instruction.IFTWOENEMY,0,Integer.parseInt(s.next()));
					break;
				case "ifeq":
					i = new Instruction(Instruction.IFEQ,Integer.parseInt(s.next()),Integer.parseInt(s.next()));
					break;
				case "inc":
					i = new Instruction(Instruction.INC,0,0);
					break;
				case "dec":
					i = new Instruction(Instruction.DEC,0,0);
					break;
				case "set": 
					i = new Instruction(Instruction.SET,0,Integer.parseInt(s.next()));
					break;
				default:
					i = null;  
				}
				//Add the instruction set
				program.add(i); 
			}
			
		}
		
		catch(FileNotFoundException e) {
			System.out.println("the file was not found"); 
		}
		
	}
	

	/**
	 * Return the name of the species.
	 */
	public String getName() {
		return name; 
	}

	/**
	 * Return the color of the species.
	 */
	public String getColor() {
		return color; 
	}

	/**
	 * Return the number of instructions in the program.
	 */
	public int programSize() {
		return program.size(); 
	}

	/**
	 * Return an instruction from the program. 
	 * @pre 1 <= i <= programSize().
	 * @post returns instruction i of the program.
	 */
	public Instruction programStep(int i) {
		return program.get(i); 
		
	}

	/**
	 * Return a String representation of the program.
	 */
	public String programToString() {
		String temp = ""; 
		for(int i = 0; i < program.size(); ++i) {
			temp += program.get(i).toString() + "\n";
		}
		return temp; 
	}
	
	
	public static void main(String[] args) {
		
		Species s = new Species("Hop.txt"); 
		Assert.condition(s.getName().equals("Hop"), "Return name doestn' works");
		Assert.condition(s.getColor().equals("blue"), "Return doestn' name works");
		//Testing Instruction
		Instruction i = new Instruction(Instruction.HOP,0,0); 
		Assert.condition(s.programStep(0).toString().equals(i.toString()), "Instruction doesnt' step works");		
		//Testing Program Size 
		Assert.condition(s.programSize() == 2, "Program Size Doesn't work");
		//String Literal test this is absurd Mr.Gnozzio 
		Assert.condition(s.programToString().equals("hop\ngo 1\n"), "ProgramStep doesn't work");
	//	System.out.println(s.programToString());
	

		
	}
}
