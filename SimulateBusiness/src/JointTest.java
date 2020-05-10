import structure5.*; 
/*
 * 1. Through JointTest, by using the same customers for both simulations, the MultiQueue strategy seems to 
 * consistently process customers faster. 

 * 2. There isn't a significant difference between the wait times between customers. It all depends on the amount of service time the 
 * customers before it has. 
 * * 
 * 3. Yes, because that would mean for each "tick" of the simulation, I would have to be able to reference every single customer
 * within a Tellers line. Since I would have to check every customer and compare it to the lineSize of the other Tellers, and then
 * determine of they want to make a jump or not. Since the PriorityQueues only allow me to reference the very first element of the line
 * there is no way for me to make customers at the back of the line jump lines without another data structure
 * 4. 
 *  I think the average waiting time would stay the same while the overall process speed of customers would increase. 
 *  The lines dedicated to shorter service would clear up much faster and the Teller would be free but not serving any customers. 
 *  Thus the overall efficiency would increase. While customers with shorter service time would have to wait less, the customers 
 *  with longer service time will have to wait longer. In the long run, there will be more extreme wait times (very short or very long 
 *  waiting times). Thus, the average waiting time should remain the same. 
 * 
 * 
 */
public class JointTest {
	public static void main(String[] args) {
		//Since Customer Queues are modified, I just made 2 copies of the same one
		PriorityQueue<Customer> customerQueue = new PriorityVector<Customer>(); 
		PriorityQueue<Customer> customerQueue2 = new PriorityVector<Customer>(); 
		for(int i = 0; i < 20; ++i) {
			//20 customers will arrive at diff time
			Customer c = new Customer((int)(Math.random() * 240)); 
			customerQueue.add(c);
			customerQueue2.add(c);
		}
	
		SingleQueue s = new SingleQueue(customerQueue); 
		MultiQueue m = new MultiQueue(customerQueue2); 
		s.simulate();
		m.simulate(); 

	}

}
