import structure.*; 
import java.util.Scanner;
import java.util.ArrayList; 
public class HumanPlayer implements Player {
	private HexBoard board; 
	private char side; 
	private Player winner; 
	
	public HumanPlayer(HexBoard b, char s) {
		board = b; 
		side = s;
	}
	
	public char getSide() {
		return side; 
	}
	
	public Player play(GameTree node, Player opp) {
		Scanner input = new Scanner(System.in); 
		//Read in the start + end pos moves
		System.out.println("please enter a start position (0 is the topleft corner, 8 is the botoom right"); 
		int start = input.nextInt(); 
		System.out.println("please enter an end position (0 is the topleft corner, 8 is the botoom right"); 
		int end = input.nextInt(); 
		//Construct a HexMove 
		HexMove player_move = new HexMove(start,end,3); 
		ArrayList<GameTree> temp = node.getChildren(); 
		int index = -2; 
		Vector moves = node.getBoard().moves(side);
		for(int i = 0; i < moves.size(); ++i) {
			if(player_move.equals((HexMove)(moves.get(i)))) {
				index = i; 					
			}
		}
		if(index >= 0) {
			//When constructing GameTree, the order of GameTrees are the same as the order of moves. Which is why
			//the order of moves will correspond to the order of GameTrees and order of boards. 
		//	System.out.println(temp.get(index).getBoard());
			board = temp.get(index).getBoard(); 
			System.out.println(board); 
			if(board.win(side)) winner = this; 
			else {
				winner = opp.play(temp.get(index),this); 
			}
			return winner; 
		//	return opponent.play(node.getChildren().get(index), this); 
		}
		else {
			System.out.println("invalid entry"); 
			return this.play(node, opp); 
		}

		
		
		//Check to see if opopnent won before the move is done
		/*if(node.getBoard().win(opponent.getSide())) return opponent; 
		else {
			System.out.println(node.getBoard());
			Scanner input = new Scanner(System.in); 
			//Read in the start + end pos moves
			System.out.println("please enter a start position (0 is the topleft corner, 8 is the botoom right"); 
			int start = input.nextInt(); 
			System.out.println("please enter an end position (0 is the topleft corner, 8 is the botoom right"); 
			int end = input.nextInt(); 
			//Construct a HexMove 
			HexMove player_move = new HexMove(start,end,3); 
			System.out.println("constructed move:" + player_move); 
			//compare HexMove to all possible moves in GameTree
			int index = -2; 
			Vector moves = node.getBoard().moves(side);
			for(int i = 0; i < moves.size(); ++i) {
				if(player_move.equals((HexMove)(moves.get(i)))) {
					index = i; 					
				}
			}
			if(index >= 0) {
				//When constructing GameTree, the order of GameTrees are the same as the order of moves. Which is why
				//the order of moves will correspond to the order of GameTrees and order of boards. 
				System.out.println(node.getChildren().get(index).getBoard());
				return opponent.play(node.getChildren().get(index), this); 
			}
			else {
				System.out.println("invalid entry"); 
				return this.play(node, opponent); 
			}
			
		}*/
		//Select correct child and pass it onto the opponent. 		
	
	}
	
	
}
