import java.awt.Image;
import objectdraw.*;



public class Fleet extends ActiveObject {
	
	//initialize constants
	private static final int GRID_WIDTH= 58;
	private static final int GRID_HEIGHT= 50;
	private static final int ALIEN_SIZE= 40;
	
	//initialize variables
	private boolean movingRight = true;
	private int wait = 1500;
	private int moveCount = 0;
	private int height = 0;
	
	//Keeps track of how many aliens left in each column
	private int[][] rowCount; 
	//Other objects
	private VisibleImage[][] fleet = new VisibleImage[6][9];
	private SpaceInvaders sc;
	private Ship spaceship; 
	private DrawingCanvas canvas; 
	private boolean run; 
	
	//initialize fleet
	public Fleet(Image[] enemyShips, SpaceInvaders sc, DrawingCanvas canvas,Ship ship) {
		for (int i = 0; i < fleet.length; i++) {
			for (int j = 0; j < fleet[0].length; j++) {
				fleet[i][j] = new VisibleImage(enemyShips[i], 29+j*GRID_WIDTH, i*GRID_HEIGHT, 
						ALIEN_SIZE, ALIEN_SIZE, canvas);
			}
		}	
		rowCount = new int[fleet.length][fleet[0].length]; 
		for(int i = 0; i < rowCount.length; ++i) {
			for(int j = 0; j < rowCount[0].length; ++j) {
				rowCount[i][j] = 0; 
			}
		}
		this.sc = sc;
		this.canvas = canvas; 
		this.spaceship = ship; 
		run = true; 
		start();
	}
	
	public int selectRow(int random) {
		for(int i = rowCount.length-1; i >= 0 ; --i) {
			if(rowCount[i][random] == 0) {
				return i; 
			}
		}
		return -1; 
	}
	public void shoot() {
		//Chooses a random column;
		int j  = (int)(Math.random() * 9); 
		int i = selectRow(j); 
		while(i == -1) {
			j = (int)(Math.random() * 9); 
			i = selectRow(j); 
		} 
		
		//Chooses the alien that is suppose to shoot
		VisibleImage temp = fleet[i][j]; 
		new Laser(temp.getX() + ALIEN_SIZE/2, temp.getY() + ALIEN_SIZE, false, this,spaceship,canvas);
		
	}
	
	public void addShip(Ship s) {
		this.spaceship = s;
	}

	private void moveLeft() {
		for (int i = 0; i < fleet.length; i++) {
			for (int j = 0; j < fleet[0].length; j++) {
				fleet[i][j].move(-GRID_WIDTH, 0);
			}
		}
	}

	private void moveRight() {
		for (int i = 0; i < fleet.length; i++) {
			for (int j = 0; j < fleet[0].length; j++) {
				fleet[i][j].move(GRID_WIDTH, 0);
			}
		}
	}

	private void moveDown() {
		for (int i = 0; i < fleet.length; i++) {
			for (int j = 0; j < fleet[0].length; j++) {
				fleet[i][j].move(0, GRID_HEIGHT);
			}
		}
		
	}
	//Checking to see collision with laser
	public boolean checkLaser(Laser laser) {
		for (int i = 0; i < fleet.length; i++) {
			for (int j = 0; j < fleet[0].length; j++) {
				if (fleet[i][j].overlaps(laser.getPic()) && laser.fromShip()) {
					
					try {
						fleet[i][j].removeFromCanvas();
						//Decrement the row
						rowCount[i][j] = 1; 
						return true;
					}
					catch (IllegalStateException e) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public void clear() {
		for (int i = 0; i < fleet.length; i++) {
			for (int j = 0; j < fleet[0].length; j++) {
				try {
					fleet[i][j].removeFromCanvas();
				}
				catch(IllegalStateException e) {
					
				}
			}
		}
		run = false; 
	}
	//Checking alive
	private boolean fleetDead(int h) {
		if (height == 8) {
			clear();
			sc.gameOver();
			return true;
		}
		return false;
	}
	
	private boolean checkWin() {
		
		for(int i = 0; i < rowCount.length; ++i) {
			for (int j = 0; j < rowCount[0].length; ++j) {
				if(rowCount[i][j] == 0) {
					return false;
				}
			}
		}
		return true; 
	}
	public void addScore(int x) {
		sc.score += x; 
	}
	
	public void run() {
		pause(1000); 
		//move the fleet in snake pattern & increase speed slowly
		while(run) {
			if (movingRight) {
				if (moveCount == 4) {
					moveDown();
					height++;
					moveCount = 0;
					movingRight = false;
					wait -= 100;
					if (fleetDead(height)) break;
				}
				else {
					moveRight();
					moveCount ++;
				}
			}
			else {
				if (moveCount == 4) {
					moveDown();
					height ++;
					moveCount = 0;
					movingRight = true;
					wait -= 100;
					if (fleetDead(height)) break;
				}
				else {
					moveLeft();
					moveCount ++;
				}
			}
			pause(wait/2);
			shoot();
			pause(wait/2); 
			if(checkWin()) {
				sc.win(); 
			}
		}
	}
}