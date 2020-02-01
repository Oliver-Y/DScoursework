import java.io.FileReader; 
import structure5.*; 
import com.opencsv.*; 
import java.io.IOException; 
import java.io.*; 
import java.util.*; 

/*
 * 1. the RevComparator just stores a comparator object and modifies the compare object by returning a negative integer. 
 * Meaning all the previous comparisons from the baseComparator will be put into reverse order. In the case of the Integer comparator, 
 * it will sort all the numbers in Integers in reverse order (presumably largest to smallest if the original Integer comparator
 * sorted from smallest to largest) 
 * 
 * 2. A comparator could store a collection of protected Comparators that each is responsible for
 * comparing in a different field. There'll be a method that will allow the user to select which Comparator 
 * they want to use at the moment.  And by adding a variety of different comparators
 * to this compound_comparator class, the compare method will switch between the different Comparators. 
 * 
 * There could also be a state variable called origin, that specifies when the sorting starts. So now the user 
 * can use a method to decide which elements they want to exclude from the sorting algorithm. 
 * 
 */


public class VectorReader {
	public static void main(String[] args) {
	
		//Initializing Reader + Creating Vector of Students
		MyVector<Student> v = new MyVector<Student>(); 
		//Modify this path to sort your CSV
		//The sample CSV is attached in the bin + src move it to your User
        String csvFile = "/Users/oye20/sample.csv"; 
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        //Reading in Value + Splitting by commas
        try {
        	
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
            	String[] temp = line.split(cvsSplitBy);
            	for(int i = 0; i < temp.length; i = i+3) {
            		v.add(new Student(temp[i],Integer.parseInt(temp[i+1]),Integer.parseInt(temp[i+2])));	
            	}
            	
            }
        }
        catch(FileNotFoundException e) {
        	
        	System.out.println("can't find the file"); 

        }
        catch(IOException e) {
        	System.out.println("IO Exception");
        }
        //Printing out original order
        System.out.println("original sorting"); 
        for(int i= 0; i < v.size(); ++i) {
        	System.out.println(v.get(i)); 
        }
        System.out.println("\n\n\n");
        System.out.println("sorted by Alphabet"); 
        
        //Sorting by Alphabet
        alphabetFirst a = new alphabetFirst(); 
        v.sort(a);
        for(int i= 0; i < v.size(); ++i) {
        	System.out.println(v.get(i)); 
        }
        System.out.println("\n\n\n");
        System.out.println("sorted by ID"); 
        
        //Sorting by ID 
        IDFirst i = new IDFirst(); 
        v.sort(i);
        for(int j= 0; j < v.size(); ++j) {
        	System.out.println(v.get(j)); 
        }
        //Sorting by height
        System.out.println("\n\n\n");
        System.out.println("sorted by Height");
        HeightFirst h = new HeightFirst(); 
        v.sort(h);
        for(int j = 0; j < v.size(); ++j) {
        	System.out.println(v.get(j)); 
        }

		
		
	}

}
//Comparing Students by the alphabet
class alphabetFirst implements Comparator<Student>{
	public int compare(Student a, Student b) {
		return a.getName().charAt(0) - b.getName().charAt(0);
	}
	
}
//Comparing students by their ID's
class IDFirst implements Comparator<Student>{
	public int compare(Student a, Student b) {
		return a.getID() - b.getID(); 
	}
}
//Comparing students by their height
class HeightFirst implements Comparator<Student>{
	public int compare(Student a, Student b) {
		return a.getHeight() - b.getHeight(); 
	}
}


