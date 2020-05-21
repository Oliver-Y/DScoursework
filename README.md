# oye20

## Grade for File Reverse Lab:

Criterion | Points
--- | ---
Automated Testing | 3/3
Input can be passed as a command line argument | 0.5/1
Input can be passed via stdin | 1/1
Good style and documentation | 1.5/2
Thought Questions | 1.75/3

Notes:  
You have a hard coded value of input.txt in your file.  You need to use args to allow the user to pass in a file.  You should only try to create the File object when you know you need one.  Perhaps a comment or two?

You haven't answered the second part of 3.4 (are there other ways to specify the size?).  

For 3.6, you haven't provided an actual implementation.  


#### Total:  7.75/10

## Grade for Hexapawn Lab:

Criterion | Points
--- | ---
Game Tree | 2.75 / 3
Human Player | 2.75 / 3
Random Player | 3 / 3
Computer Player | 3 / 3
Thought Questions | 8/9

Notes:  For the second thought question, what are the "perfect pathways" of which you speak? Describe the winning strategy.  What about for larger boards? 

Within the GameTree class, you should use the constants provided by HexBoard for the colors.  Your use of a static variable count isn't ideal.  What if someone made two game trees?  Count recursively.  

In the HumanPlayer class, it would be better to make a single Scanner and store it as an instance variable.  This avoids creating a new one every time play is called. More importantly, why are you reinventing the wheel with Moves?  Use the existing methods provided by the HexBoard class.  

#### Total:  19.5/21

## Grade for Simulating Business Lab:

Criterion | Points
--- | ---
Use of a PriorityQueue | 2 / 2
Correct use of comparators or natural sorting order | 2/2
Computes weight times appopriately | 2/2
Correct logic for processing single queue events | 1 / 2
Correct logic for processing multi queue events | 2 / 2
Simulation compares the same arrivals across both simulations | 1 / 1
Style and efficiency | 1/2
Thought Questions | 5/8

Notes:  For the compareTo method, no need for an if/else if/else.  Just subtract the two times and return the result.  
The overall design of a time counter that increments by 1 largely misses the point of the simulation.  
There's no need to spend cycles on time slots when there's no event.  
The passage of time is represented simply from the popping of events off of the event queue.  
Furthermore, it's problematic, since you advance the counter one tick every cycle.  
What if multiple events happen at the same time?  Moreover, your "plenty simple" serve method is incorrect.  
You shouldn't add the arrivalTime of the customer to the event, which may be taking place after the arrival.  
For example, if the "event" occurs at time 5 and the customer arrived at time 3.  
If the customer is going to need 4 seconds, the teller will be free at time 9, not time 12.  
Try running your code with only a single teller.  The performance should be the same for the two simulations, 
but your code shows that the single teller takes longer.  This has lead you to give nonsensical answers in the
thought questions.  Think about which of the two structures allows for customers to be waiting while tellers are free.  

#### Total:  16/21