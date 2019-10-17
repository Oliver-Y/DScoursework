
public class arraytest {

	public static void main (String[] args) {
		int[] a = {5,1,3,4,5}; 
		System.out.println(max_diff(a)); 
	}
	public static int diff_finder(int[] a) {
		int diff = 1000; 
		for (int i = 0; i <a.length; ++i) {
			for (int j = i+1; j < a.length; ++j) {
				int temp = Math.abs(a[j] - a[i]);  
				if (diff > temp) {
					diff = temp; 
				}
			}
		}
		return diff; 
	}
	public static int max_diff(int[] a) {
		int diff = 0; 
		for (int i = 0; i <a.length; ++i) {
			for (int j = i+1; j < a.length; ++j) {
				int temp = Math.abs(a[j] - a[i]);  
				if (diff < temp) {
					diff = temp; 
				}
			}
		}
		return diff;
	}
}
