import structure5.*; 
import java.util.Comparator; 
public class MyVector<E> extends Vector<E> {

	public MyVector (){
		super(); 
	} 
	
	public void sort (Comparator<E> c) {
		int n = this.elementCount;
        // One by one move boundary of unsorted sub-array 
        for (int i = 0; i < n-1; i++) 
        {  
        	// Find the minimum element in unsorted array 
            int min_idx = i; 
            for (int j = i+1; j < n; j++) 
                if (c.compare(this.get(j), this.get(min_idx)) < 0)
                    min_idx = j; 
            // Swap the found minimum element with the first 
            // element 
            E temp = this.get(min_idx); 
            this.set(min_idx, this.get(i));
            this.set(i,temp);
        } 
		
	}
	public static void main(String[] args) {
		
	}

}
