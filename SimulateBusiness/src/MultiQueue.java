import java.util.ArrayList;

import structure5.PriorityQueue;
import structure5.PriorityVector;
public class MultiQueue {
	//Sorted by time
	private PriorityQueue<Event> eventQueue; 
	//Sorted by arrival time
	private PriorityQueue<Customer> customerQueue; 
	//Sorted by Line length
	private PriorityQueue<Teller> tellerLineQueue; 
	private ArrayList<Integer> waitTimes; 
	private int globalTime; 	
	public MultiQueue(){
		//Initialize
		eventQueue = new PriorityVector();
		customerQueue = new PriorityVector(); 
		tellerLineQueue = new PriorityVector(); 
		waitTimes = new ArrayList<Integer>(); 
		globalTime = 0; 
		//Add in three tellers 
		for(int i = 0; i < 3; ++i) {
			Event e = new Event(0,'t'); 
			Teller t = new Teller(); 
			e.attach(t); 
			eventQueue.add(e);
			tellerLineQueue.add(t); 
		}
		//Add in all the random customers 
		for(int i = 0; i < 20; ++i) {
			Customer c = new Customer((int)(Math.random() * 240)); 
			customerQueue.add(c);
		}
	}
	public String printTeller() {
		ArrayList<Teller> debug = new ArrayList<Teller>(); 
		String temp = "Teller list: "; 
		int count = 1; 
		while(!tellerLineQueue.isEmpty()) {
			Teller c = tellerLineQueue.remove(); 
			debug.add(c);
			temp += "Teller " + count + " " +  c + "\n"; 
			count++; 
		}
		while(!debug.isEmpty()) {
			tellerLineQueue.add(debug.remove(0));
		}
		return temp + "\n"; 
	}
	
	public String printCustomers() {
		ArrayList<Customer> debug = new ArrayList<Customer>(); 
		String temp = "Customer list: "; 
		while(!customerQueue.isEmpty()) {
			Customer c = customerQueue.remove(); 
			debug.add(c);
			temp += "Customer " + c + "\n"; 
		}
		while(!debug.isEmpty()) {
			customerQueue.add(debug.remove(0));
		}
		return temp; 
	}
	
	//Seperate Constructor to be used in Joint Test 
	public MultiQueue(PriorityQueue<Customer> c) {
		waitTimes = new ArrayList<Integer>(); 
		eventQueue = new PriorityVector();
		tellerLineQueue = new PriorityVector(); 
		globalTime = 0; 
		//Add in three tellers 
		for(int i = 0; i < 3; ++i) {
			Event e = new Event(0,'t'); 
			Teller t = new Teller(); 
			e.attach(t); 
			eventQueue.add(e);
			tellerLineQueue.add(t); 
		}
		customerQueue = c; 
	}
	
	public void simulate() {
		while(!eventQueue.isEmpty()) {
			//C event
			if(!customerQueue.isEmpty()) {
				Customer c = customerQueue.remove();
				//Customer arrived 
				if(c.getArrival() <= globalTime) {
					Teller t = tellerLineQueue.remove(); 
					//Get in line, go to the TellerQueue which is sorted by Line size
					t.getInLine(c); 
					tellerLineQueue.add(t);
				}
				else {
					customerQueue.add(c);
				}
			}
			Event temp = eventQueue.remove();
				//A teller is free
				if(temp.getTime() < globalTime || temp.getTime() == 0) {
					Teller t = temp.getTeller();
					//Serve the customer in line, the method returns the waiting time of said customer. Record that waiting time
					waitTimes.add(t.serve(globalTime)); 
					//Set up the next time the Teller is free
					temp.setTime(t.getTime());
				}					
				eventQueue.add(temp); 
			//If there are no more customers and each Teller has no more in its line --> otherwise, keep cycling through events.
			if(customerQueue.isEmpty()) {
				Event e = eventQueue.remove(); 
				if(e.getTeller().getLineSize() == 0) {
					
				}
				else {
					eventQueue.add(e); 
				}
			}
			globalTime++; 
		}	
		//Check which teller would have taken the longest to finish his line, hence serving all customers
		int max = 0; 
		while(!tellerLineQueue.isEmpty()) {
			Teller t = tellerLineQueue.remove(); 
			if(max < t.getTime()) {
				max = t.getTime(); 
			}
		}
		System.out.println(" this is the max time for Multiqueue " + max); 
		//Determine the average of waits
		int avg = 0; 
		for(int i = 0; i < waitTimes.size(); ++i) {
			avg += waitTimes.get(i); 
		}
		avg /= waitTimes.size();
		System.out.println("This is the average wait time: " + avg); 
	}
	
	public ArrayList<Integer> getWaitTimes(){
		return waitTimes; 
	}
	public static void main(String[] args) {
		MultiQueue test = new MultiQueue(); 
		test.simulate(); 
	}
	
	
	

}
