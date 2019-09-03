# Eight-Puzzle_Problem (Artificial Intelligence)
 8 puzzle problem using DFS,BFS,IDS,A* with different heuristics.
 https://repl.it/@simranjeetsing5/8puzzleproblem<br>
 <hr>
 <img src="https://media.geeksforgeeks.org/wp-content/uploads/puzzle8init.jpg"/>
<h3> Program Description </h3>
This implementation is for the Search Algorithms Applied to the 8-Puzzle which
includes the following algorithms:
<ol>
 <li>
  Breath-First Search (BFS) </li>
 <li>
  Depth-First Search (DFS) </li>
 <li>
  Best-First Search(A*) using misplaced tiles // H = no. of tiles out of place (H = heuristic function)</li>
<li>
Iterative Deepening search (IDS) </li>
<li>
 A* using Manhattan_distance // H = manhattan dist </li>
<li> A* using Chebyshev_distance // H = 2*C where C is the Chebyshev distance</li>
<li> A* using Sequence_score // H = totdist + 3*seq </li>
<li> Also checks whether puzzle is solvable or not? </li>
</ol>

<h3> Steps to run the program </h3>

javac eight_puzzle.java <br />
java eight_puzzle

<h3> Steps to execute </h3>
<pre>
Example
Enter intial state of 8 puzzle problem :
1 3 4 
8 0 2 
7 6 5

Enter goal state of 8 puzzle problem:
1 2 3
8 0 4 
7 6 5
</pre>
<h3> Program Returns</h1>
<ul>
 <li>
  The series of states (path) from the initial to the goal configuration </li>
 <li>
  The total number of generated nodes (states)</li>
 <li> The total running time </li>
 </ul>
