import structure.Vector;
import java.util.ArrayList;

public class RandomPlayer implements Player {
	private HexBoard board; 
	private char side; 
//	private Player winner; 
	
	public RandomPlayer(HexBoard b, char c) {
		board = b; 
		side = c; 
	}
	
	public char getSide() {
		return side; 
	}
	
	public Player play(GameTree node, Player opp) {
		Player winner = null; 
		//Generate random move
		ArrayList<GameTree> temp = node.getChildren();
		int rand = (int)(Math.random() * temp.size()); 
		board = temp.get(rand).getBoard(); 
		System.out.println(board); 
		if(board.win(side)) {
			winner = this; 
		}
		else {
			winner = opp.play(temp.get(rand), this); 
		}
		return winner; 
	}
}




/*System.out.println(board); 
System.out.println("it's " + side + " turn"); 
//Check to see if opponents turn just resulted in a win
if(node.getBoard().win(opp.getSide())) return opp; 
else {
	ArrayList<GameTree> temp = node.getChildren();
	int rand = (int)(Math.random() * temp.size()); 
	board = temp.get(rand).getBoard(); 
	return opp.play(temp.get(rand), this); 
}

}*/
