import org.jfugue.player.Player;
import org.jfugue.rhythm.Rhythm;
import org.jfugue.pattern.Pattern;

//Transcribed the first few bars of Spring by Vivaldi into Jfugue. 
public class Spring implements MusicEditor {
	Pattern p; 
	public Spring(){
		p = new Pattern(); 
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
	public Pattern getSong(){
		 p = new Pattern("T[Allegro]");
		 p.add("V0 C5q | E5q+C5q E5q+C5q E5q+C5q D5i C5i | Eh.+Gh. G5i F5i | E5q+C5q E5q+C5q E5q+C5q D5i C5i | Eh.+Gh. G5i F5i");
		 p.add("V1 Rq | C4h C4h | C4h C4h | C4h C4h | C4h C4h");
		 return p;

	}
		
	public static void main(String[] args){
		 Player player = new Player();
		 Spring s = new Spring(); 
		 player.play(s.getSong());
	}
	

}
	