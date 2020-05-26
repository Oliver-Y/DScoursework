import org.jfugue.player.Player;
import org.jfugue.pattern.Pattern;
import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;
import org.jfugue.theory.Note;
public class ChordGen {
	  public static void main(String[] args) {
		  Player p = new Player(); 
		  ChordGen c = new ChordGen(); 
		  Pattern chordProgression = c.chords("I IV V"); 
		  p.play(chordProgression);

	  }
	  
	  public Pattern chords(String progression){
	      return new ChordProgression(progression)
	              .distribute("7%6")
	              .allChordsAs("$0 $0 $0 $0 $1 $1 $0 $0 $2 $1 $0 $0")
	              .eachChordAs("$0ia100 $1ia80 $2ia80 $3ia80 $4ia100 $3ia80 $2ia80 $1ia80")
	              .getPattern()
	              .setInstrument("Acoustic_Bass")
	              .setTempo(100); 
	  }

}
