
public class Student {
	
	private String name; 
	private int height; 
	private int ID; 
	
	public Student(String a, int b, int c) {
		name = a; 
		height = b; 
		ID = c; 
	}	
	
	public int getHeight() {
		return height; 
	}
	public String getName() {
		return name; 
	}
	
	public int getID() {
		return ID; 
	}
	
	public String toString() {
		return "name: " + name + " height " + height + " ID " + ID; 
	}
	

}
