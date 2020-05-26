import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

public class BoogieWoogieDriver {
	public static void main(String[] args){
		//has all the helper tools to generate melody
		BoogieWoogieGen b = new BoogieWoogieGen();
		Player player = new Player(); 

		//Specify Chords to be used
		String[] boogie_left1 = new String[]{
				"C4", "C4", "Eb4", "E4","G4","C4","A4","G4"
		}; 
		String[] boogie_left2 = new String[] {
				"F4", "F4", "Ab4", "A4", "C5", "F4", "D5", "C5",
		}; 
		String[] boogie_left3 = new String[] {
				"G4", "G4", "Bb4", "B4", "D5", "G4", "E5", "D5",
		}; 
		//Create corresponding left hand w/ Boogie generator
		Pattern first = b.createBoogie(boogie_left1,"V0 "); 
		Pattern second = b.createBoogie(boogie_left2,"V0 "); 
		Pattern third = b.createBoogie(boogie_left3,"V0 "); 
		//Compile into a song
		Pattern total = new Pattern(); 
		//Offset the Rhythm by a rest
		total.add("Ri* "); 
		total.add(first,4); 
		total.add(second,2);
		total.add(first,2); 
		total.add(third,2); 
		total.add(first,2); 
		//Set the tempo
		total.setTempo(150); 
		
		//Version of right hand with only chords
		String chord = "C6maj6w | C6maj6w | ";
		String E_chord = "Eb6w+G6w+A6w+C7w | "; 
		String F_chord = "F6w+G6w+B6w+D7w | ";
		Pattern cc = new Pattern();
		cc.add(chord,2); 
		cc.add(E_chord,2);
		cc.add(chord); 
		cc.add(F_chord,2); 
		cc.add(chord);
		cc.setVoice(1); 
		
		//Uncomment to play chords
		//player.play(total,cc);

		
		//Chords for right hand 
		String[] temp = new String[]{"G6+E6","A6+F6","C6+G6", "A6+F6", "G6+E6"}; 
		String[] f = new String[]{"A6q*+C7","B6+D7","F6+C7", "B6+D7", "A6+C7"}; 
		String[] g = new String[]{"B6+D7","C7+E7","G6+D7", "C7+E7", "B6+D7"}; 

		//Create right hand Lick #2
		Pattern m_var = b.variation2(temp,"C6");
		m_var.setVoice(1); 
		m_var.setTempo(150); 
		
		//Creat right-hand Lick #1
		Pattern m = b.variation1(temp,"C6"); 
		m.setVoice(1); 
		m.setTempo(150);
		//Repeat for different Keys
		Pattern f_triad = b.variation1(f,"F6"); 
		Pattern f_triad_var = b.variation2(f,"F6"); 
		Pattern g_triad = b.variation1(g,"G6"); 
		Pattern g_triad_var = b.variation2(g,"G6"); 
		
		//Append into total
		Pattern m_total = new Pattern(); 
		//This is my own custom boogie woogie where I chose which licks to use 
		m_total.add(m); 
		m_total.add(m_var); 
		m_total.add(m); 
		m_total.add(m_var); 
		m_total.add(f_triad_var); 
		m_total.add(f_triad_var); 
		m_total.add(m); 
		m_total.add(m_var); 
		m_total.add(g_triad,g_triad); 
		m_total.add(m_var);
		m_total.add(m); 
		m_total.add("C6q*"); 
		m_total.setTempo(150); 
		m_total.setVoice(1); 
		//Uncomment to play my version
		//player.play(total,m_total);

		
		//Random Boogie Woogie generator
		Pattern c[] = new Pattern[]{m,m_var}; 
		Pattern fCluster[] = new Pattern[]{f_triad,f_triad_var}; 
		Pattern gCluster[] = new Pattern[]{g_triad,g_triad_var}; 
		Pattern random = b.randomBoogie(c,fCluster,gCluster); 
		//Uncomment to play Computer versin
		//player.play(total,random);
	}
}
