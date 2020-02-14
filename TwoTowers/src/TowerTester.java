import structure5.*; 
import java.lang.Math;
/*
 * 1. the best solution to the 16 block challenge is 4 5 6 10 11 12 13 blocks. I had to add 1 to my subset because the indices 
 * for the subset start at 0. But at index 0 its block 1 and at index 3 the block is 4, thus since the subset is 3 4 5 9 
 * 10 11 12, then the blocks would be 4 5 6 10 11 12 13. 
 * 
 * 2. I would use a pseudo random number generator to both randomly determine the length of the subset (so that subsets of a variety
 * of lengths are examined) but also generate random numbers given the length. Thus, when the second has elapsed and the interrupt/time
 * out is called, I'll find the best subset out of subsets of random length and random indices.
 * 
 * 
 */

public class TowerTester {
	
	public static void main(String[] args) {
		//Initialize 
		Vector<Double> test = new Vector<Double>();  
		double max = 0; 
		for(int i = 1; i < 16; ++i) {
			test.add(Math.sqrt(i));
			max += Math.sqrt(i); 
		}

		
		double close =  max; 
		//To store the best set
		Set<Integer> x = new SetVector<Integer>(); 
		//Temporary Integer Set
		Set<Integer> y = new SetVector<Integer>(); 
		TowerIterator t = new TowerIterator(test); 
		//Iterate through set
		while(t.hasNext()) {		
			double sum = 0.0; 
			y = t.next(); 
			for (Integer temp: y) {
				sum += test.get(temp); 
			}
			
			//If its smaller than max/2 and its closer to max/2. Update close and store that subset in x
			if(sum < max/2 && max/2 - sum < close) {
				close = max/2 - sum; 
				x = y;
			}
			
		}
		System.out.println(x); 
	}

}
