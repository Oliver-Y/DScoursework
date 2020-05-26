import org.jfugue.player.Player;
import org.jfugue.pattern.Pattern;
import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;
import org.jfugue.theory.Note;
/*
 * This is another iteration of the MusicCreator class, instead of the BoogieWoogie
 * accompaniment, this creates a chord progression based off inputed chord still
 * in 12 bar blues format tho (cus it sounds nice!) 
 * 
 */
public class ChordGen implements MusicEditor {
	Pattern p; 
	public ChordGen(){
		p = new Pattern(); 
	}
	//Plays it as twelve bar blues
	  public static void main(String[] args) {
		  Player p = new Player(); 
		  ChordGen c = new ChordGen(); 
		  Pattern chordProgression = c.chords("I IV V"); 
		  p.play(chordProgression);

	  }
	  
	  public Pattern chords(String progression){
	      p =  new ChordProgression(progression)
	              .distribute("7%6")
	              .allChordsAs("$0 $0 $0 $0 $1 $1 $0 $0 $2 $1 $0 $0")
	              .eachChordAs("$0ia100 $1ia80 $2ia80 $3ia80 $4ia100 $3ia80 $2ia80 $1ia80")
	              .getPattern()
	              .setInstrument("Acoustic_Bass")
	              .setTempo(100); 
	      return p; 
	  }
		public void setVoice(int i){
			p.setVoice(i); 
		}
		public Pattern getPattern(){ 
			return p.getPattern(); 
		}
		public void setInstrument(String s){
			p.setInstrument(s); 
		}
		public void setTempo(int i){
			p.setTempo(i); 
		}
		public void repeat(int i){
			p.repeat(i); 
		}
		public void add(String s){
			p.add(s); 
		}
		public String toString(){
			return p.toString(); 
		}

}
