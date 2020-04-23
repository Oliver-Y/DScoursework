import structure.*; 
import java.util.ArrayList; 
import java.awt.Color; 
import java.util.Scanner; 


/*
 * 1. 
 * for a 3x4 board there are 6003 positions, for a 3x5 board there are 215150 positions. I used a static int called count
 * to keep track each time a new GameTree(hence a new board) was constructed and incremented the count each time.
 * 
 * 2. 
 *	After testing, I think H.E.R will win more frequently. After examining the different branches being removed, the GameTree
 * browser showed that by moving second, H.E.R has a greater chance of learning the perfect way to win. Since H.E.R moves second, it
 * dictates the flow of the GameTree. In the sense that for H.I.M, the board state if completely dependent on how H.E.R moves. However
 * for H.E.R, since H.I.M moved first already, H.E.R can quickly learn the quickest/guarenteed pathway down the tree that 
 * will result in a win. Thus, as seen by the tree browser of how the tree is pruned, after a ~ 50 rounds or so H.E.R will learn the
 * perfect pathways for each corresponding H.I.M move and begin to dominate all the games. 
 *
 *
 *
 * 3. I would  have to add a few variables (varying upon implementation) that would keep track of if a certain board was a mirror
 * of another board. If it was, then that GameTree node would reference the mirror GameTree node. So that when it calls any function
 * such as PrintBoard  or setParent or removeParent, I can just reuse the code for the mirror on the current reflectoin of the node.
 * 
 * 
 * 
 * 
 * 
 */


public class GameTree {
	private HexBoard board; 
	private ArrayList<GameTree> nextBoards; 
	private GameTree parent; 
	private char player; 
	private static int count; 
	
	public GameTree(HexBoard b, char c) {
			GameTree child; 
			//inc(); 
			board = b; 
			player = c; 
			nextBoards = new ArrayList<GameTree>(); 
			//Base case 
			char opp;
			if(c == '*') opp = 'o'; 
			else { opp = '*'; } 
			//If someone has won
			if(b.win(c)) {
				//System.out.println("Count " + count++); 
				//System.out.println(c + " has won");
				//System.out.println(b); 
			}
			if (b.win(opp)) {
				inc(); 
				//System.out.println("Count " + count++); 
				//System.out.println(opp + " has won");
				//System.out.println(b);
			}
			//Otherwise keep creating new GameTrees
			else {
				//Get the moves
				Vector temp = b.moves(c);
				for(int i = 0; i < temp.size(); ++i) {
					HexBoard next = new HexBoard(b,(HexMove)temp.get(i));
					if(c == '*') {
						child = new GameTree(next,'o'); 
						child.setParent(this);
						nextBoards.add(child);
					}
					else { 
						child = new GameTree(next,'*'); 
						child.setParent(this);
						nextBoards.add(child); 
				}
				//Setting up parent 
				/*System.out.println("child's parents: " + child.getParent()); 
				System.out.println("current node " + this); 
				System.out.println("parent " + this.getParent()); */
				//Base Case
				if(parent == null) parent = this; 
			}
		}
	}
	
	private void setParent(GameTree g) {
		this.parent = g; 
	}
	
	public GameTree getParent() {
		return parent; 
	}
	
	private static void inc(){
		count++; 
	}
	
	public ArrayList<GameTree> getChildren(){
		return nextBoards; 
	}
	public HexBoard getBoard() {
		return board; 
	}
	public char getPlayer() {
		return player; 
	}
	
	public void removeChild(GameTree g) {
		if(this.getChildren().size() == 0) { 
			//Base Case at a leaf
		}
		
		else if(this.equals(g)) {
			g.getParent().nextBoards.remove(this); 
		}
		//Recursively look for the board
		else {
			for(int i = 0; i < nextBoards.size(); ++i) {
				nextBoards.get(i).removeChild(g); 
			}
		}
		
	}
	public void removeParent() {
		parent = null; 
		
	}
	public int getSize() {
		
		return 0; 
	}
	public int numLeaves() {
		return count; 
	}
	
	public static void printChildren(GameTree node) {
		System.out.println("Parent");
		System.out.println(node.getBoard()); 
		System.out.println(); 
		ArrayList<GameTree> children = node.getChildren(); 
		for(int i = 0; i < children.size(); ++i) {
			System.out.println("Child " + i);
			System.out.println(children.get(i).getBoard()); 
		}
	}
	
	public static void treeBrowser(GameTree gt)
	{
		Scanner s = new Scanner(System.in);
		int p = 0;
		do {
			printChildren(gt);
			System.out.println("Enter a node to display further children (-1 for parent):");
			p = s.nextInt();
			if(p == -1) {
				// parent case
				gt = gt.getParent();
			} else if(p >= 0) {
				// child case
				gt = (GameTree) gt.getChildren().get(p);
			}
		} while (p >= -1);
		printChildren(gt);
	}
	
	
	
	
	public static void main(String[] args) {
        HexBoard b = new HexBoard(3,3); // change me!
        GameTree g = new GameTree(b,'*');
        System.out.println("number of positions " + g.numLeaves());
      
        //Human + RandomPlayer testing
        System.out.println(b);
        HumanPlayer h = new HumanPlayer(b,'*');
        HumanPlayer c = new HumanPlayer(b,'o'); 
        System.out.println("PLayer " + h.play(g, c).getSide() + " has won");  
        
        //Comp Player test
        GameTree comp_tree = new GameTree(b,'*'); 
        GameTree normal_tree = new GameTree(b,'*'); 
        CompPlayer p = new CompPlayer(b,'*'); 
        CompPlayer r = new CompPlayer(b,'o'); 
        System.out.println(p.play(comp_tree,r).getSide());
      	GameTree.treeBrowser(comp_tree);

	}
}
