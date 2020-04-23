import java.util.ArrayList;

public class CompPlayer implements Player {
	private HexBoard board;
	private char side; 
	private Player winner; 
	private GameTree recent;
	
	
	
	public CompPlayer(HexBoard b, char c) {
		board = b;
		side = c; 
	}
	
	public char getSide(){
		return side; 
	}
	
	public Player play(GameTree node, Player opp) {
		//Make the move first then check if I've won 
		ArrayList<GameTree> temp = node.getChildren(); 
		int rand = (int)(Math.random()*temp.size());
		//Update the board based off the move.. cant be local or else it'll be overwritten each time ; kpjo
		System.out.println(temp); 
		System.out.println("the number " + rand); 
		recent = temp.get(rand); 
		board = temp.get(rand).getBoard(); 
		System.out.println(board); 
		//Check to see if there's a win
		if(board.win(side)) {
			winner = this; 
		}
		else {
			//Pass off the play
			winner = opp.play(temp.get(rand), this);
			//Prune the tree is ur not the winner
			if(winner != this) {
				(node.getParent()).removeChild(recent);
			}
		}
		return winner; 
	}
	

}
