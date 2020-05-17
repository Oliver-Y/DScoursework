//Event Class
public class Event implements Comparable<Event> {
	private int time; 
	private char type; 
	//For multiQueue, each event has a behind the scene Teller sorted by line size to process customers
	private Teller ref; 
	public Event(int t, char c) {
		time = t; 
		type = c; 
	}
	public int getTime() {
		return time;
	}
	
	public void setTime(int t) {
		time = t; 
	}
	
	public char getType() {
		return type; 
	}
	
	//Serve method for SingleQueue, since it pretty simple
	public void serve(Customer q) {
		this.time += q.getService() + q.getArrival(); 

	}
	public String toString() {
		return time + ""; 
	}
	public void attach(Teller t) {
		ref = t; 
	}
	public Teller getTeller() {
		return ref;
	}
	//Sort Events by event times
	public int compareTo(Event other) {
		Event t = (Event)other; 
		if(this.time<t.time) return -1; 
		else if(this.time == t.time) return 0; 
		else return 1; 
	}

}
