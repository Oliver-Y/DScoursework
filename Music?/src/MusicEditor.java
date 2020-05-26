import org.jfugue.pattern.Pattern;

public interface MusicEditor {
	
	public void setVoice(int i); 
	public Pattern getPattern(); 
	public void setInstrument(String s); 
	public void setTempo(int i); 
	public void repeat(int i); 
	public void add(String s); 
	public String toString(); 
	

}
