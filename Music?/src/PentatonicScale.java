import java.util.ArrayList;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player; 

//This ended up just being a scale builder, use the scaleBuilder then octave definer
//Then you can transpose it to a different key
public class PentatonicScale {
	
	public static String scaleBuilder(String scale){
		int n = scale.charAt(0); 
		String temp = ""; 
		for(int i = 0; i < 7; ++i){
			temp+= (char)n;
			n++; 
			if(n>71)
				n = 65; 
		}
		temp += scale.charAt(0); 
		return temp; 
	}
	public static String transpose(String music){
		String temp = ""; 
		char octave = music.charAt(music.length()-2); 
		boolean flag = false; 
		System.out.println(octave); 
		for(int i = 0; i < music.length(); ++i){
			int note = music.charAt(i)+1; 
			if(music.charAt(i) >= 65 && music.charAt(i) <= 71){
				if(note == 72){
					note = 65;
					flag = true;
					
				}
				temp += (char)note;
			}
			
			else{
				if(flag){
					temp += music.charAt(i); 
				}else{
					temp += music.charAt(i); 
				}
				
			}	
		}
		return temp; 
	}
	public static String octaveDefiner(String scale,String octave){
		String temp =  ""; 
		for(int i = 0; i <scale.length()*2; ++i){
			if((i%2) == 0)
				temp+=scale.charAt(i/2); 
			else{
				temp+=octave + " "; 
			}
		}
		return temp; 
	}
	public static String addDuration(String scale,String length){
		String temp =  ""; 
		int counter = 0; 
		for(int i = 0; i <scale.length()*2; ++i){
			if((i%3) < 2)
				temp+=scale.charAt(i/2); 
			else{
				temp+=length.charAt(counter++); 
			}
		}
		return temp; 
	}
	
	public static void main(String[] args){
		//Scale builder
		//C Major
		String C_scale = octaveDefiner(scaleBuilder("C"),"6");
		System.out.println(C_scale); 
		//Transpose C Major to D Major
		String D_major = transpose(C_scale); 
		System.out.println(D_major); 
		Player player = new Player(); 
		Pattern d = new Pattern(D_major); 
		//Defines the Key Signature
		d.prepend("Kdmaj "); 
		Pattern c = new Pattern(C_scale); 
		//Play C Major
		player.play(c);
		//Play D Major
		player.play(d); 
		
	}

}