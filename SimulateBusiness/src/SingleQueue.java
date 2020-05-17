//Queue
import java.util.ArrayList;

import structure5.*; 
public class SingleQueue {
	private PriorityQueue<Event> eventQueue; 
	private PriorityQueue<Customer> customerQueue; 
	private ArrayList<Integer> wait; 
	private int globalTime;
	
	
	public SingleQueue() {
		//Intialize
		eventQueue = new PriorityVector();
		customerQueue = new PriorityVector(); 
		wait = new ArrayList<Integer>(); 
		globalTime = 0; 
		//Add in three tellers 
		for(int i = 0; i < 3; ++i) {
			Event e = new Event(0,'t'); 
			eventQueue.add(e);
		}
		//Add in all the random customers 
		for(int i = 0; i < 20; ++i) {
			Customer c = new Customer((int)(Math.random() * 240)); 
			customerQueue.add(c);
		}
		
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
	//Seperate constructor used in Joint Test
	public SingleQueue(PriorityQueue<Customer> c) {
		eventQueue = new PriorityVector();
		wait = new ArrayList<Integer>(); 
		globalTime = 0; 
		//Add in three tellers 
		for(int i = 0; i < 3; ++i) {
			Event e = new Event(0,'t'); 
			eventQueue.add(e);
		}
		customerQueue = c; 

	}
	
	//will update based off of global time every 1 second. 
	public void simulate() {
		int waitTime = 0; 
		while(!customerQueue.isEmpty()) {
			Customer c = customerQueue.remove();
			//A customer has arrived
			if(c.getArrival() <= globalTime) {
					Event temp = eventQueue.remove(); 
					//Check to see if there are tellers free
					if(temp.getTime() < globalTime || temp.getTime() == 0) {
						//record wait time
						wait.add(waitTime);
						waitTime = 0; 
						//if the Teller time lags behind the global Time, reset it. 
						temp.setTime(globalTime); 
						temp.serve(c);
					}
					else {
						//If a teller isn't free, make sure to add the customer back to the Queue. 
						customerQueue.add(c);
						waitTime++; 
					}
					//Add teller event back in event Queue
					eventQueue.add(temp);
			}
			//Customer has not arrived yet
			else {
				customerQueue.add(c);
			}
		globalTime++; 
		}
		//Calculate Max Time
		int max = 0; 
		while(!eventQueue.isEmpty()) {
			Event t = eventQueue.remove(); 
			if(max < t.getTime()) {
				max = t.getTime(); 
			}
		}
		System.out.println("this is the max time for SingleQueue " + max); 
		//Calculate wait time Average
		int avg = 0; 
		for(int i = 0; i < wait.size(); ++i) {
			avg += wait.get(i); 
		}
		avg /= wait.size();
		System.out.println("This is the average wait time: " + avg); 
	}
	
	public ArrayList<Integer> waitingTimes(){
		return wait; 
	}
	
	public static void main(String[] args) {
		SingleQueue s = new SingleQueue(); 
		s.simulate(); 

	}
	

}
