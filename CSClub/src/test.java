/*
 * 3.4: Since, under the hood, the memory for the Array doubles in size everytime capacity exceeds. However, if we want to manage 
 * the memory allocation not to exceed a certain amount, however, at different stages in the program the ArrayList may have
 * different sizes, then the explicit set size method might be useful. 
 * 
 * 
 * 3.6: To have a specialized ArrayList that will only have Booleans if you know the type will only be booleans. 
 * 
 * 
 * 
 * 3.8 For a precision array size N, the amount of copy it has to do is  2 + 3 + 4 + 5 ... n. For example: 
 * for an Array size of 5. it'll start at size 1, then have to copy 2, then copy 3, then copy 4, then copy 5 so on. 
 * The equation for a sum is n*(n+1)/2 and since this sum starts at 2, we subtract one. So the equation is n*(n+1) /2 -1 
 * Which is approximatly equal to n^2 
 * 
 * 
 * 
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*; 
public class test {
	void random_method() {
		System.out.println("hello world"); 
	}
	public static void main(String[] args) {
		ArrayList <String> list = new ArrayList<String>(); 
			File input = new File("input.txt"); 
		try {
			Scanner stream;
			if(args.length > 0) {
				 stream = new Scanner(input); 
			}
			else {
				 stream = new Scanner(System.in) ;
			}
			while(stream.hasNextLine()) {
				String temp = stream.nextLine(); 
				String holder = " "; 
				int counter = 0; 
				for (int i = temp.length()-1; i >= 0; --i) {
					counter++; 
					if(temp.charAt(i) == ' ') {
						holder += temp.substring(i,i+counter);
						counter = 0; 
					}
					if (i == 0) {
						holder += " " + temp.substring(0,counter);
					}
					
					//holder += temp.charAt(i); 
				}
				list.add(0,holder.trim());
			}
			stream.close(); 
			for (int i = 0; i < list.size(); ++i) {
				System.out.println(list.get(i));
			}
		}
		catch(FileNotFoundException e){
			System.out.println("the input text is not found lol"); 
		}			
	}
	
	


}
	

