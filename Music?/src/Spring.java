import org.jfugue.player.Player;
import org.jfugue.rhythm.Rhythm;
import org.jfugue.pattern.Pattern;

//Transcribed the first few bars of Spring by Vivaldi into Jfugue. 
public class Spring {
	public static void main(String[] args){
		 // This looks better on a larger display!
		 Pattern pattern = new Pattern("T[Allegro]");
		 pattern.add("V0 C5q | E5q+C5q E5q+C5q E5q+C5q D5i C5i | Eh.+Gh. G5i F5i | E5q+C5q E5q+C5q E5q+C5q D5i C5i | Eh.+Gh. G5i F5i");
		 pattern.add("V1 Rq | C4h C4h | C4h C4h | C4h C4h | C4h C4h");
		 // Now, play the music!
		 Player player = new Player();
		 player.play(pattern); 
	}
}
	