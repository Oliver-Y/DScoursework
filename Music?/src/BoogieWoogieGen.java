import org.jfugue.pattern.Pattern;

public class BoogieWoogieGen {
	//Create left hand 12 bar blues
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
		System.out.println(music); 
		return new Pattern(music); 
	}
	//Create simple melody 
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
		return new Pattern(music); 
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
		System.out.println("variation 1:" + music); 
		return new Pattern(music); 
	}
	//Second variation with different rhythms + notes
	public  Pattern variation2(String[] notes, String key){
		String music = "Ri* " + "Rq* "; 
		music += key + "i* "; 
		music += notes[0] + "q* " + notes[1] + "i* " + notes[0] + "q* "; 
		music += key + "i* "; 
		music +=  "Rq* "; 
		return new Pattern(music); 
	}
	//Third custom variation my own creation — doesn't sound that good...
	public  Pattern variation3(String[] notes, String key){
		String music = ""; 
		music += notes[2] + "q* " + key + "i* " + notes[2] + "q* " + key + "i* " + notes[1] + "q* " + key + "i* "; 
		music +=  "Rq* Ri*"; 

		return new Pattern(music); 
	}
	//Creates random boogies
	public  Pattern randomBoogie(Pattern[] c, Pattern[] f, Pattern[] g){
		Pattern total = new Pattern(); 
		total.add(cleanPattern(c,4)); 
		total.add(cleanPattern(f,2)); 
		total.add(cleanPattern(c,2)); 
		total.add(cleanPattern(g,2)); 
		total.add(cleanPattern(c,2)); 
	
		return total; 
	}
	//Helper method for Random accounts for teh repeats 
	public  Pattern cleanPattern(Pattern[] temp, int num){
		Pattern total = new Pattern();
		for(int i = 0; i < num; ++i){
			total.add(pickPattern(temp)); 
		}
		return total; 
	}
	//Handles "randomness" in the boogie generator
	public  Pattern pickPattern(Pattern[] temp){
		int num = (int)(Math.random()*100);
		if(num > 50){
			return temp[1]; 
		}
		else{
			return temp[0]; 
		}
	}
}
