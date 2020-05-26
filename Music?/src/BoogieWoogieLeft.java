import org.jfugue.pattern.Pattern;
/*
 * This is the MusicCreater class as specified in the design plan 
 * It creates the accompaniment/left-hand based on BoogieWoogie 12 bar blues principles.
 */

public class BoogieWoogieLeft implements MusicEditor {
	Pattern p; 
	public BoogieWoogieLeft(){
		p = new Pattern(); 
	}
	public  Pattern createBoogie(String[] notes, String voice){
		String music = voice; 
		for(int i = 0; i < notes.length; ++i){
			music += notes[i]; 
			if(i%2 == 0){
				music += "q* "; 
			}
			else{
				music += "i* "; 
			}
		}
		music += "| "; 
		p = new Pattern(music); 
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
