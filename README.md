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
