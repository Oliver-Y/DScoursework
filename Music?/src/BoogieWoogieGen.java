import org.jfugue.pattern.Pattern;

/*
 * This is the SongOneMelody class specified in the Design plan. It creates
 * the melody for the Boogie Woogie based off variations and licks of the C
 *  F and G major keys 
 */
public class BoogieWoogieGen implements MusicEditor {
	//Create simple melody 
	Pattern p; 
	public BoogieWoogieGen(){
		p = new Pattern(); 
	}
	public Pattern melody(String[] notes, String key){
		String music = ""; 
		int counter = 0; 
		for(int i = 1; i < 9; ++i){
			if(i%2 == 0){
				music += key+"i* "; 
			}
			else{
				music += notes[counter++] + "q* "; 
			} 
		}
		//Update the recent patttern being passed
		p = new Pattern(music); 
		return p; 
		
	}
	//First variation with different rhythms + notes
	public  Pattern variation1(String[] notes, String key){
		String music = ""; 
		int octave = Integer.parseInt(key.substring(1,key.length())); 
		octave++; 
		music += notes[2].substring(0,1) + octave + notes[2].substring(2,5) + "i* "; 
		music += notes[2].substring(0,1) + octave + notes[2].substring(2,5) + "q* "; 
		music += key + "i* "; 
		music += notes[1] + "q* ";
		music += notes[0].substring(3,5) + "b+" + notes[1].substring(3,5) + "i* "; 
		music += notes[0] + "q* "; 
		music +=  key + "i* " + key + "q* "; 
		p = new Pattern(music); 
		return p;
	}
	//Second variation with different rhythms + notes
	public  Pattern variation2(String[] notes, String key){
		String music = "Ri* " + "Rq* "; 
		music += key + "i* "; 
		music += notes[0] + "q* " + notes[1] + "i* " + notes[0] + "q* "; 
		music += key + "i* "; 
		music +=  "Rq* "; 
		p = new Pattern(music); 
		return p;
	}
	//Third custom variation my own creation — doesn't sound that good...
	public  Pattern variation3(String[] notes, String key){
		String music = ""; 
		music += notes[2] + "q* " + key + "i* " + notes[2] + "q* " + key + "i* " + notes[1] + "q* " + key + "i* "; 
		music +=  "Rq* Ri*"; 
		p = new Pattern(music); 
		return p;
	}
	//Creates random boogies
	public  Pattern randomBoogie(Pattern[] c, Pattern[] f, Pattern[] g){
		Pattern total = new Pattern(); 
		total.add(cleanPattern(c,4)); 
		total.add(cleanPattern(f,2)); 
		total.add(cleanPattern(c,2)); 
		total.add(cleanPattern(g,2)); 
		total.add(cleanPattern(c,2)); 
		p = total;
		return total; 
	}
	//Helper method for Random accounts for teh repeats 
	private  Pattern cleanPattern(Pattern[] temp, int num){
		Pattern total = new Pattern();
		for(int i = 0; i < num; ++i){
			total.add(pickPattern(temp)); 
		}
		return total; 
	}
	//Handles "randomness" in the boogie generator
	private  Pattern pickPattern(Pattern[] temp){
		int num = (int)(Math.random()*100);
		if(num > 50){
			return temp[1]; 
		}
		else{
			return temp[0]; 
		}
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
