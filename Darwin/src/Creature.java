/**
 * This class represents one creature on the board.
 * Each creature remembers its species, position, direction,
 * and the world in which it is living.
 * <p>
 * In addition, the Creature remembers the next instruction
 * out of its program to execute.
 * <p>
 * The creature is also repsonsible for making itself appear in
 * the WorldMap.  In fact, you should only update the WorldMap from
 * inside the Creature class. 
 */


public class Creature {
	
	private Species specie; 
	private World<Creature> world; 
	private Position position; 
	private int dir; 
	private int ip; 
	private char c; 
	private int mp; 
	
    /**
     * Create a creature of the given species, with the indicated
     * position and direction.  Note that we also pass in the
     * world-- remember this world, so that you can check what
     * is in front of the creature and to update the board
     * when the creature moves.
	 * @param species The species of the creature
	 * @param world The world in which the creature lives
	 * @param pos The position of the creature
	 * @param dir The direction the creature is facing
	 * @pre species, world, and pos must be non-null
	 * @pre pos must be within the bounds of world
	 * @pre dir must be one of: Position.NORTH, Position.SOUTH, Position.EAST
	 *                          or Positon.WEST
	 * @pre the world map must have been created
	 * @post creates a creature of species species in world world at position
	 *       pos facing direction dir
	 * @post initializes instance variables so that the creature knows what
	 *		 instruction to begin executing
	 * @post displays the creature on the world map
     */
    public Creature(Species s, World<Creature> w, Position p, int d) {
    	//Intialize Variables
    	specie = s; 
    	world = w; 
    	position = p;
    	dir = d;
    	c = specie.getName().charAt(0); 
    	ip = 1; 
    	mp = 0; 
    	//Display on GUI
    	WorldMap.displaySquare(p, c, d, s.getColor());
	}

    /**
     * Return the species of the creature.
     */
    public Species species() {
    	return specie; 
	}

    /**
     * Return the current direction of the creature.
     */
    public int direction() {
    	return dir; 
    }

    /**
     * Return the position of the creature.
     */
    public Position position() {
    	return position; 
    }
    
    //Change the specie of the creature
    public void changeSpecie(Species s) {
    	this.specie = s; 
    }
    //Helper method to clear a WorldMap block
    private void clear(Position p) {
		WorldMap.displaySquare(p, ' ', dir, specie.getColor());
		world.set(p,null); 
    }
    //Helper Method to Set a WorldMap block
    private void set(Position p) {
		WorldMap.displaySquare(p, specie.getName().charAt(0), dir, specie.getColor()); 
		world.set(p, this); 
    }
    public void reset_ip(int n) {
    	ip = n;
    }
    //Helper + Debugger
    public int ip() {
    	return ip; 
    }
    /**
     * Execute steps from the Creature's program until 
     * a hop, left, right, or infect instruction is executed.
	 * @post Creature takes one turn's worth of instructions
	 * @post display is updated to reflect movement of this creature
	 *
     */
    
    public void takeOneTurn() {
		//Program keeps running until hop infect left right
    	boolean t_flag = false; 
    	//Switch Case, T_Flag will be enabled when an end_of_turn move has been reached (Infected, hop, left, right) 
    	while(!t_flag) {

        	Instruction temp = specie.programStep(ip-1); 
    	   	switch(temp.getOpcode()) {
    		case Instruction.HOP:
    			//Position is never updated.
    			if(world.inRange(position.getAdjacent(dir)) && world.get(position.getAdjacent(dir)) == null) {
    				//clear block
    				clear(position); 
    				//Update position
    				position = position.getAdjacent(dir); 
    				//Display it
    				set(position); 
    			}
    			ip++; 
    			t_flag = true; 
    			break; 
    		case Instruction.LEFT: 
    			clear(position); 
    			//manipulating actual number repersentation of Macro to get right direction
    			dir = (dir+3) %4; 
    			set(position); 
    			ip++; 
    			t_flag = true; 
    			break; 
    		case Instruction.RIGHT:
    			clear(position); 
    			dir = (dir + 1) % 4; 
    			set(position); 
    			ip++;
    			t_flag = true; 
    			break; 
    		case Instruction.INFECT: 
    			if(world.inRange(position.getAdjacent(dir)) && world.get(position.getAdjacent(dir))!= null) {
    				Creature infected = world.get(position.getAdjacent(dir)); 
    				infected.changeSpecie(this.specie);
    				//Reset the IP once a creature has been infected. 
    				infected.reset_ip(temp.getAddress()); 
    				clear(position.getAdjacent(dir)); 
    				world.set(position.getAdjacent(dir), infected); 
    				WorldMap.displaySquare(position.getAdjacent(dir), infected.species().getName().charAt(0), infected.direction(), infected.species().getColor());
    			}
    			ip++; 
    			t_flag = true; 
    			break; 
    		//Changes the IP
    		case Instruction.IFEMPTY: 
    			if(world.inRange(position.getAdjacent(dir)) && world.get(position.getAdjacent(dir)) == null) {
    				ip = temp.getAddress(); 
    			}
    			else {
    				ip++;
    			}
    			break; 
    		case Instruction.IFWALL: 
    			//Check the bounds
    			if(!world.inRange(position.getAdjacent(dir))) {
    				ip = temp.getAddress(); 
    			}
    			else {
    				ip++;
    			}
    			break; 
    		case Instruction.IFSAME: 
    			if(world.inRange(position.getAdjacent(dir)) && !(world.get(position.getAdjacent(dir)) == null) && world.get(position.getAdjacent(dir)).species().equals(this.specie)) {
    				ip = temp.getAddress(); 
    			}
    			else {
    				ip++;
    			}
    			break; 
    		case Instruction.IFENEMY:
    			if(world.inRange(position.getAdjacent(dir)) && !(world.get(position.getAdjacent(dir)) == null) && !world.get(position.getAdjacent(dir)).species().equals(this.specie)) {
    				ip = temp.getAddress(); 
    			}
    			else {
    				ip++;
    			}
    			break; 
    		case Instruction.IFRANDOM: 
    			int n = (int)(Math.random() * 100); 
    			if (n < 50 ) {
    				ip = temp.getAddress(); 
    			}
    			else {
    				ip++; 
    			}
    			break; 
    			
    		//Extensions:
    			//See 2 enemies ahead
    		case Instruction.IFTWOENEMY: 
    			if(!(world.get(position.getAdjacent(dir)) == null) && !world.get(position.getAdjacent(dir).getAdjacent(dir)).equals(this.specie)) {
    				ip = temp.getAddress(); 
    			}  	
    			else {
    				ip++;
    			}
    			break; 
    			
    			//Single bit Memory Operations
    		case Instruction.IFEQ: 
    			if(mp == temp.getTestCase()) {
    				ip = temp.getAddress(); 
    			}
    			else {
    				ip++;
    			}
    			break; 
    		case Instruction.INC: 
    			mp++; 
    			break; 
    		case Instruction.DEC: 
    			mp--;
    			break; 
    		case Instruction.SET: 
    			mp = temp.getAddress(); 
    			break; 
    		case Instruction.GO:
    			ip = temp.getAddress();
    			break; 
    		default: 
    			break; 
    	}
    	} 
    	//Left Right changes position 
    	//Hop move changes 
    	//If statements change the counter
	}

}
