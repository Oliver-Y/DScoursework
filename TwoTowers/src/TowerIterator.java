import structure5.*; 
import java.lang.Math; 

public class TowerIterator extends AbstractIterator<Set<Integer>> {
	private Vector<Double> list; 
	private Set<Integer> subList; 
	private long counter; 
	
	public TowerIterator(Vector<Double> i) {
		list = i; 
		counter = 0; 
	}
	
	
	
	public boolean hasNext() {
		if(counter > Math.pow(2, list.size()))
			return false; 
		return true;
	}
	public Set<Integer> next(){
		//Do the bitwise shifting
		// 0000 --> 0001 ---> 0010 --> 0011 --> 0100 
		subList = get(); 
		counter++;
		return subList;
		//Return the set
	}
	
	public Set<Integer> get() {
		subList = new SetVector<Integer>(); 
		//Loop through size of list
		for(int i = 0; i < list.size(); i++) {
			//Check bit Mask
			if((counter & (1L << i)) > 0) {
				//Add corresponding elements
				subList.add(i); 
			}
			
		}
		return subList; 
	}
	
	
	public void reset() {
		counter = 0; 
	}
}
