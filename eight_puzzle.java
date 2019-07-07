/*
 
AUTHOR
-------
 Simranjeet Singh Randhawa
 Student No: 200412297
 ssr779@uregina.ca
 Department of Computer Science

DESCRIPTION
--------------
This Program solves 8 puzzle problem using Bounded DFS,BFS,IDS,A* with different heuristics as given in the assignment.
BONUS: Also checks that an instance of 8 puzlle is solvable or not.

Steps to run the program
---------------------------
javac eight_puzzle.java
java eight_puzzle
    
*/

import java.util.*;
import java.lang.*;

//eight_puzzle class containing all sovable methods

public class eight_puzzle {

    public static String initial_state;
    public static String goalstate;
	public static int[][] initial = new int[3][3];
	public static int[][] goal = new int[3][3];
	
	//to store nodes as explained in class
	public static Map< String,String> store_nodes =  new HashMap< String,String>(); 
	public static int node_count=0;  //calculate the number of nodes generated for IDS. For all other methods we can used Hashmap.size()
	//to store level as explained in AI class
	public static Map< String,Integer> node_level =  new HashMap< String,Integer>(); 
	//main function
	public static void main(String args[]){

            Scanner sc = new Scanner(System.in);
			
				int[][] a = new int[3][3];
				
while(true)
{
initial_state="";   
goalstate="";
	System.out.println("Enter initial state: ");
	for(int i=0;i<3;i++)
		{
		for(int j=0;j<3;j++)
	{
		
		a[i][j]=sc.nextInt();   //input initial state
	}
	
}

eight_puzzle obj = new eight_puzzle();

for(int i=0;i<3;i++)
		{
		for(int j=0;j<3;j++)
	{
		initial[i][j]=a[i][j];
		initial_state = initial_state+ Integer.toString(a[i][j]);
	}
	
}

//System.out.print(initial_state);
//String initial_state= obj.IntArrayToString(a);


//Input goal state
System.out.println("Enter final state: ");
for(int i=0;i<3;i++)
		{
		for(int j=0;j<3;j++)
	{
		
		goal[i][j]=sc.nextInt();
	}
	
}

int st;
for(int i=0;i<3;i++)
		{
		for(int j=0;j<3;j++)
	{
		
		goalstate = goalstate+ Integer.toString(goal[i][j]);
	}
	
}
//System.out.print(goalstate);

boolean check=false;
long startTime;
long endTime;
long duration;
boolean ids_check=false;
boolean solve=false;

	System.out.println("");
	System.out.println("   8 PUZZLE SOLUTION MENU   ");
	System.out.println("----------------------------");
	System.out.println(" 0. Bonus: Check 8 puzzle is solvable or not \n 1. BFS \n 2. DFS \n 3. IDS \n 4. A* (h is MISPLACED TILES) \n 5. A*(h is MANHATTAN distane) \n 6. A* (h is 2*C where C is the Chebyshev distance )\n 7. A*(h is h = manhattan + 3*seq) \n 8. Exit the program");
	System.out.print("Enter your choice : ");
int as=sc.nextInt();
switch(as)
{
	
	case 0:       //checks if a puzzle is solvable or not(BONUS)
	int a1=obj.getInvCount(a);
	////System.out.print(a1);
	int b1=obj.getInvCount(goal);
	//System.out.print(b1);
	solve =obj.isSolvable(a1,b1);
	if(solve==true)
	{
	System.out.println(" Solvable");
	}
	else
	{
	System.out.println(" Not Solvable");	
	}
	System.out.println();
	break;
	case 1:
startTime = System.nanoTime(); 
check=obj.bfs(initial_state);      //BFS
endTime = System.nanoTime();
duration = (endTime - startTime);
System.out.print(" Total Running time (nano seconds): ");     //Total running time for BFS in nano seconds
System.out.println(duration);
break;
 
 case 2:

System.out.print("Enter depth: ");
int ax=sc.nextInt();      //input depth
startTime = System.nanoTime();
//System.out.print(startTime);
check=obj.dfs(initial_state,ax);    // Bounded DFS 
endTime = System.nanoTime();
duration = (endTime - startTime);
System.out.print(" Total Running time (nano seconds) ");   //Total running time for Bounded DFS in nano seconds
System.out.println(duration);
break;

case 3:
startTime = System.nanoTime();
//System.out.print(startTime);
ids_check=obj.ids(initial_state);   //IDS
endTime = System.nanoTime();
duration = (endTime - startTime);
System.out.print(" Total Running time (nano seconds) ");   //Total running time for IDS in nano seconds
System.out.println(duration);

break;

case 4:
startTime = System.nanoTime();
check=obj.a_star(initial_state,1);        // A* USING H= misplaced tiles
endTime = System.nanoTime();
duration = (endTime - startTime);
System.out.print(" Total Running time (nano seconds) ");   //Total running time for A* USING H= misplaced tiles in nano seconds
System.out.println(duration);
break;
case 5:
startTime = System.nanoTime();
check=obj.a_star(initial_state,2);     //A* USING H= MANHATTAN DISTANCE
endTime = System.nanoTime();
duration = (endTime - startTime);
System.out.print(" Total Running time in nano seconds ");    //Total running time for A* USING H= MANHATTAN DISTANCE in nano seconds
System.out.println(duration);
break;
case 6:
startTime = System.nanoTime();
check=obj.a_star(initial_state,3);   //A* using H= (h is 2*C where C is the Chebyshev distance )
endTime = System.nanoTime();
duration = (endTime - startTime);
System.out.print(" Total Running time(nano seconds) ");    //Total running time for A* USING H= (h is 2*C where C is the Chebyshev distance ) in nano seconds
System.out.println(duration);
break;
case 7:
startTime = System.nanoTime();

check=obj.a_star(initial_state,4);  //  A* using (h is H = totdist + 3*seq)  

endTime = System.nanoTime();

duration = (endTime - startTime);
System.out.print(" Total Running time(nano seconds) ");     //Total running time for A* USING H=  A*(h is H = totdist + 3*seq) in nano seconds
System.out.println(duration);
break;
case 8:
 System.exit(0); //Exit the program 
}

if(ids_check==true)    //If solution found usind ids then display  
{
	obj.displayresult_ids();
	store_nodes.clear();
	node_level.clear();
}
else if(!check)   //If no solution
{
	store_nodes.clear();
	node_level.clear();
}

else if(check==true)   //If solution found by other searches then display
{

	obj.displayresult();
	store_nodes.clear();
	node_level.clear();
}
store_nodes.clear();
node_level.clear();

}

}


//funstion to check that it is solvable or not
public  boolean isSolvable(int a,int b)
{
	if(a%2==0 && b%2==0)
	{
		return true;
	}
	else if(a%2!=0 && b%2!=0)
	{
		return true;
	}
	
	return false;
	
}
 
//function to solve IDS 
public boolean ids(String a)
{
	int level=1;
    if(a.equals(goalstate))   //CHECK THE RESULT INITIALLY
		{
			return true;
		}
 boolean check=dfs(a,level);  //PERFORM DFS
 if(check==true)
 {
	node_count+=store_nodes.size(); 
 }
 
 level++;
	while(!check)
	{
	
    node_count+=store_nodes.size();
	store_nodes.clear();
	
    node_level.clear();
	
	check=dfs(a,level);   //perform dfs for lower level.
  
			level++;	
	}
	//System.out.print("The total number of nodes generated are "+ node_count);

	return true;
	
	
} 


//Calculating the out of place tiles from the goal state.

int cal_misplaced_tiles(int [][]a)
{
	int tiles_misplaced=0;
/*Just for checking
	for(int i=0;i<3;i++)
	{
		for(int j=0;j<3;j++)
		{System.out.print(a[i][j]);
		}
	}
	*/
	for(int i=0;i<3;i++)
	{
		for(int j=0;j<3;j++)
		{
			if(a[i][j]!=0)
			{
				
						if(a[i][j]!=goal[i][j])
							{
							tiles_misplaced+=1;
						}
					
				}
			}
		}
	
	//System.out.print(tiles_misplaced);
	return tiles_misplaced;
}

//Calculating the manhattan distance tiles from the goal state.

int manhattan(int [][]a)
{
	int man_dist=0;
	
	/*  JUST FOR CHECKING
	for(int i=0;i<3;i++)
	{
		for(int j=0;j<3;j++)
		{System.out.print(a[i][j]);
		}
	}
	
	
	System.out.println();*/
	
	for(int i=0;i<3;i++)
	{
		for(int j=0;j<3;j++)
		{
			if(a[i][j]!=0)
			{
				for(int x=0;x<3;x++)
				{
					for(int y=0;y<3;y++)
					{
						if(a[i][j]==goal[x][y])
							man_dist+=Math.abs(i-x)+Math.abs(j-y);
					}
				}
			}
		}
	}
	//System.out.println(man_dist);
	return man_dist;
}

//inversion count for checking puzzle is solvale or not(Taken help from Geek for Geeks site )
public int getInvCount(int[][] arr) 
{ 
    int inv_count = 0;
int []temp= new int[9];
	int counter=0;
    for (int i = 0; i < 3 ; i++) 
	{  for (int j = 0; j < 3; j++) 
		{
			temp[counter]=arr[i][j];
			counter++;
		}
	}
	for (int i = 0; i < 8; i++) 
	{ for (int j = i+1; j < 9; j++) 
		{ 
             if (temp[j]>0 && temp[i]>0 &&  temp[i] > temp[j]) 
			 { inv_count++;
		 }
		}
    }		
    return inv_count; 
          
           
} 

//calculating Chebyshev distance
int chebyshev(int [][]a)
{
	int sum=0;
	int c=0;
	int d=0;
	int ce=0;
	for(int i=0;i<3;i++)
	{
		for(int j=0;j<3;j++)
		{
			if(a[i][j]!=0)    //only check for non-zero tiles
			{
				for(int x=0;x<3;x++)
				{
					for(int y=0;y<3;y++)
					{
						if(a[i][j]==goal[x][y])
						{
							c=Math.abs(i-x);
							d=Math.abs(j-y);
							ce=Math.max(c,d);
							sum+=ce;
					}
					}
				}
				
			}
			
		}
	}
	
	return sum;
}

//calculating sequence score
public int seq(int [][]a)
{
	int dist=0;
	/* JUst for checking
	for(int i=0;i<3;i++)
	{
		for(int j=0;j<3;j++)
		{System.out.print(a[i][j]);
		}
	}
	
	System.out.println();
	*/
List<String> sourceList = new ArrayList<String>();
List<String> destinationList = new ArrayList<String>();

//Clockwise storing in an arraylist.
String temp1 = Integer.toString(a[0][0])+ Integer.toString(a[0][1]);
String temp2 = Integer.toString(a[0][1])+ Integer.toString(a[0][2]);
	sourceList.add(temp1);
String temp3 = Integer.toString(a[0][2])+ Integer.toString(a[1][2]);
	sourceList.add(temp2);
	
	String temp4 = Integer.toString(a[1][2])+ Integer.toString(a[2][2]);
	sourceList.add(temp3);

	String temp5 = Integer.toString(a[2][2])+ Integer.toString(a[2][1]);
	sourceList.add(temp4);

	String temp6 = Integer.toString(a[2][1])+ Integer.toString(a[2][0]);
	sourceList.add(temp5);

	String temp7 = Integer.toString(a[2][0])+ Integer.toString(a[1][0]);
	sourceList.add(temp6);

	String temp8 = Integer.toString(a[1][0])+ Integer.toString(a[0][0]);
	sourceList.add(temp7);

	sourceList.add(temp8);
//Remove the elemnt that starts with zero because that will not contritute to an order.
	for(int i=0;i<sourceList.size();i++)
	{
		if(sourceList.get(i).startsWith("0"))
		{sourceList.remove(i);
		}
		//System.out.println(s[i]);
	}
/*	for(int i=0;i<sourceList.size();i++)
	{
		
		System.out.println(sourceList.get(i));
	}
	*/
	String final1 = Integer.toString(goal[0][0])+ Integer.toString(goal[0][1]);
	
	destinationList.add(final1);
	String final2 =Integer.toString(goal[0][1])+ Integer.toString(goal[0][2]);
	
	
	String final3 = Integer.toString(goal[0][2])+ Integer.toString(goal[1][2]);
	
	
	String final4 = Integer.toString(goal[1][2])+ Integer.toString(goal[2][2]);
	
	
	String final5 = Integer.toString(goal[2][2])+ Integer.toString(goal[2][1]);
	
	
	String final6 = Integer.toString(goal[2][1])+ Integer.toString(goal[2][0]);
	

	String final7 = Integer.toString(goal[2][0])+ Integer.toString(goal[1][0]);
	
	
	String final8 = Integer.toString(goal[1][0])+ Integer.toString(goal[0][0]);
		
		destinationList.add(final2);
		destinationList.add(final3);
		destinationList.add(final4);
		destinationList.add(final5);
		destinationList.add(final6);
	destinationList.add(final7);
	destinationList.add(final8);

 sourceList.removeAll( destinationList );
 int size= sourceList.size();
 size=size*2;
 //check if center is zero or not.
 if(a[1][1]!=0)
 {
	 size=size+1;
 }
 //System.out.println(size);

return size;	
}



Queue<String> updatequeue(Queue <String> q,int st)
{
	
	String s;
	Map<String, String> tiles = new HashMap<String, String>();

	//unordered_map<string,int> tiles;
	Map<String, Integer> v = new LinkedHashMap<String, Integer>();
	ValueComparator bvc = new ValueComparator(v);
	
TreeMap<String, Integer> sorted_map = new TreeMap<String, Integer>(bvc);
	//std::vector<std::pair<int,string>> v;
	//Queue <String> q = my; 

	int f;
	
	while(q.size()!=0)
{
	
	String p= q.peek();
	//System.out.print(p);
	

	f=get_level(p); 
	
     
	q.remove();
	
	char[] stringToCharArray = p.toCharArray();
	
	int[][] arr=new int[3][3];
	int no=0;
	int sec=0;
	int count=0;
	for(int i=0;i<3;i++)
	{
		for(int j=0;j<3;j++)
		{
			char co=stringToCharArray [count];
			int ab=Integer.parseInt(String.valueOf(co));  
			arr[i][j]=ab; 
			count++;
		}
	}

if(st==1)
{	
no= cal_misplaced_tiles(arr);
}
else if(st==2)
{
no=manhattan(arr);
}
else if(st==3)
{
no=chebyshev(arr);
no=no*2;
}
else if(st==4)
{
	no=seq(arr);
	//System.out.println(no);
	sec=manhattan(arr);
	//System.out.println(no);
no=sec+3*no;
}
no=no+f;
//System.out.print(p);
v.put(p,no);

}

//Sorting the map accroding to the heuristics.
LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
v.entrySet()
    .stream()
    .sorted(Map.Entry.comparingByValue())
    .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
//System.out.println("results: " + sortedMap);
sorted_map.putAll(v);
//System.out.println("results: " + sorted_map);


//Sorted queue according to priority
for(Map.Entry<String,Integer> entry : sortedMap.entrySet()) {
  String key = entry.getKey();
  Integer value = entry.getValue();

  q.add(key);
}




return q;	

}


//basic a_star algorithm that updates queue according to heuristics.

boolean a_star(String a,int z)
{
	
	
	
	Queue<String> q = new LinkedList<String>(); 
	
	q.add(a); 
	store_nodes.put(a,""); 
	node_level.put(a,0); 
 

	while(q.size()!=0)
{
	String p= q.peek();
	q.remove();

	char[] stringToCharArray = p.toCharArray();
	int[][] arr=new int[3][3];
	int count=0;
	for(int i=0;i<3;i++)
	{
		for(int j=0;j<3;j++)
		{
			char co=stringToCharArray [count];
			int ab=Integer.parseInt(String.valueOf(co));  
			arr[i][j]=ab; 
			count++;
		}
	}
	
	if(checkgoal(p)==false)
	{
	 
	 List<String> sub = new ArrayList<String>(); 
	 sub=generate(arr);
	 
	 
	 for(int i=0;i<sub.size();i++)
	 {
	 
	 q.add(sub.get(i));
	 
	}

	
	//System.out.print("Enter 1. TILES");
	q= updatequeue(q,z);
	
	  for(int i=0;i<sub.size();i++)
	 {
	String s= sub.get(i); 
	if(s.equals(goalstate))
		{
			return true;
		}
	}
	 
	 sub.clear(); 
}
	
	
	 else
	 
	 {
		
	 	return true;
	  } 
	
}

return false;

}

//check if a node is a parent of a particular node
  
public String checkparent(String p)
{

 Set< Map.Entry< String,String> > st = store_nodes.entrySet(); 
   for (Map.Entry< String,String> me:st) 
       { 
      if(p.equals(me.getKey()))
	  {
		  return me.getValue();
	
	  }
	   }
return null;				
}

//returns level of the node
public int get_level(String p)
{


	 Set< Map.Entry< String,Integer> > st = node_level.entrySet(); 
   for (Map.Entry< String,Integer> me:st) 
       { 
      if(p.equals(me.getKey()))
	  {
		  return me.getValue();
	
	  }
	   }
			
	String s=checkparent(p);
	
	for (Map.Entry< String,Integer> me:st) 
       { 
      if(s.equals(me.getKey()))
	  {
		  int i= me.getValue();
		  i=i+1;
		  node_level.put(p,i);
		  return i;
	
	  }
	   }
	return -1;
		}
	

//DFS METHOD using stack 
public boolean dfs(String a,int maxlevel)
{
	
	Stack<String> s = new Stack<String>(); 
	s.push(a);
	int node_l=1;
	node_level.put(a,0); 
	store_nodes.put(a,""); 
	while(!s.empty() )
{
	String p=s.peek();
	node_l=get_level(p);  //get the level for the string node.
	s.pop();
	if(p.equals(goalstate))
	{
		return true;
	}
	

char[] stringToCharArray = p.toCharArray();
	
	int[][] arr=new int[3][3];
	
	int count=0;
	for(int i=0;i<3;i++)
	{
		for(int j=0;j<3;j++)
		{
			char co=stringToCharArray [count];
			int ab=Integer.parseInt(String.valueOf(co));  
			arr[i][j]=ab; 
			count++;
		}
	}
	
	

if( node_l<maxlevel)
{ 
if(checkgoal(p)==false)
	{
	 
	 List<String> sub = new ArrayList<String>(); 
	 sub=generate(arr);
	 
	 for(int i=0;i<sub.size();i++)
	 {
	 
	 s.push(sub.get(i));
	}
	 
	 sub.clear(); 
}

	
	 else
	 
	 {
	 
	 	return true;
	  } 
	
}

}


return false;
	
}  
			
 //display the final result.
public void displayresult()
{
	
String s1,s2;
s1="";
s2="";	
System.out.print("The total number of generated nodes ");
System.out.println(store_nodes.size());
ArrayList<String> states = new ArrayList<String>();
  
    
    
   for (Map.Entry< String,String> me:store_nodes.entrySet()) 
       { 
      if(goalstate.equals(me.getKey()))
	  {
		  s1=me.getKey();
		  s2=me.getValue();
		  break;
	  }
	   }
	 
		 


while(s2!="")
{
	
	states.add(s1);
	//System.out.println(s1);
	//System.out.println("hello");
	 for (Map.Entry< String,String> me:store_nodes.entrySet()) 
       { 
      if(s2.equals(me.getKey()))
	  {
		  s1=me.getKey();
		  s2=me.getValue();
		 
		  break;
	  }
	   }
	
	
	
	
}

Collections.reverse(states);
    System.out.println("The series of states (path) from the initial to the goal configuration, :");      
	
	int[][] arr=new int[3][3];
	for(int x=0;x<3;x++)
	{
		for(int y=0;y<3;y++)
		{
			System.out.print(initial[x][y]);
		}
		System.out.println();
	}
	System.out.println();
	
for (int i = 0; i < states.size(); i++) {
	
	int count=0;
	char[] stringToCharArray = states.get(i).toCharArray();
	for(int x=0;x<3;x++)
	{
		for(int y=0;y<3;y++)
		{
			char co=stringToCharArray [count];
			int ab=Integer.parseInt(String.valueOf(co));  
			arr[x][y]=ab; 
			count++;
		}
	}
	
	for(int x=0;x<3;x++)
	{
		for(int y=0;y<3;y++)
		{
			System.out.print(arr[x][y]);
		}
		System.out.println();
	}
	
			System.out.println();
		}
         
    System.out.print("No of steps to solve the puzzle: ");     
System.out.println(states.size());  	
System.out.println();  
	
         
       // System.out.println(states);

 store_nodes.clear();
node_level.clear(); 
}       


//display result for ids
public void displayresult_ids()
{
	
String s1,s2;
s1="";
s2="";	
System.out.print(" The total number of generated nodes ");
System.out.println(node_count);
ArrayList<String> states = new ArrayList<String>();
  
    
  
   for (Map.Entry< String,String> me:store_nodes.entrySet()) 
       { 
      if(goalstate.equals(me.getKey()))
	  {
		  s1=me.getKey();
		  s2=me.getValue();
		  break;
	  }
	   }
	 
		 


while(s2!="")
{
	
	states.add(s1);
	
	 for (Map.Entry< String,String> me:store_nodes.entrySet()) 
       { 
      if(s2.equals(me.getKey()))
	  {
		  s1=me.getKey();
		  s2=me.getValue();
		  break;
	  }
	   }
	
	
	
	
}

Collections.reverse(states);
    System.out.println(" The series of states (path) from the initial to the goal configuration :");      
int[][] arr=new int[3][3];
	for(int x=0;x<3;x++)
	{
		for(int y=0;y<3;y++)
		{
			System.out.print(initial[x][y]);
		}
		System.out.println();
	}
	System.out.println();
	
	
for (int i = 0; i < states.size(); i++) {
	
	int count=0;
	char[] stringToCharArray = states.get(i).toCharArray();
	for(int x=0;x<3;x++)
	{
		for(int y=0;y<3;y++)
		{
			char co=stringToCharArray [count];
			int ab=Integer.parseInt(String.valueOf(co));  
			arr[x][y]=ab; 
			count++;
		}
	}
	
	for(int x=0;x<3;x++)
	{
		for(int y=0;y<3;y++)
		{
			System.out.print(arr[x][y]);
		}
		System.out.println();
	}
	
			System.out.println();
		}
         
    System.out.print("No of steps to solve the puzzle: ");     
System.out.println(states.size());  	
System.out.println();  
	
         
       // System.out.println(states);

 store_nodes.clear();
node_level.clear(); 
}             
 
public String IntArrayToString(int [][] array) {
        String r="";
		for(int i=0;i<3;i++)
		{
		for(int j=0;j<3;j++)
	{
		
		r = r+ array[i][j];
	}
	
}
return r;
    }


public boolean checkgoal(String p)
{
	
	if(p.equals(goalstate))
	{return true;
	}
	else
	{
		return false;
	}
	
}


public boolean checkinresult(String s)
{
 
    Set< Map.Entry< String,String> > st = store_nodes.entrySet(); 
   for (Map.Entry< String,String> me:st) 
       { 
      
	 
		   if(s.equals(me.getKey()))
        {
        	return true;
		}
       }     
    return false;

}


public void store(int[][] a,int[][] b)
{
String parent=IntArrayToString(a);
String child=IntArrayToString(b);

	store_nodes.put(child,parent); 

}


public String move_left(int[][] a,int i,int j)
{

	if(j!=0)
{
	int[][] b= new int[3][3];
		for(int x=0;x<3;x++)
	{
		for(int y=0;y<3;y++)
		{
			
				b[x][y]=a[x][y];
		}
	}
b[i][j]=a[i][j-1];
b[i][j-1]=0;
	String su="";
		for(int x=0;x<3;x++)
		{
		for(int y=0;y<3;y++)
	{
		
		su = su+ b[x][y];
	}
	
}
	if(!checkinresult(su))
	{

store(a,b);
return su;
}
else
{
	return null;
}
}
else
{
return null;}
//return null;
}

public String movedown(int[][] a,int i,int j)
{
	
	int[][] b= new int[3][3];
		for(int x=0;x<3;x++)
	{
		for(int y=0;y<3;y++)
		{
			
				b[x][y]=a[x][y];
		}
	}
	
	 if(i==2)
	 {
	 return null;	 
	}
	else 
	{	
	b[i][j]=a[i+1][j];
	b[i+1][j]=0;
	
	String su="";
		for(int x=0;x<3;x++)
		{
		for(int y=0;y<3;y++)
	{
		
		su = su+ b[x][y];
	}
	
}
	if(!checkinresult(su))
	{

store(a,b);
return su;
}
else
{
	return null;
}
}
//return null;
}


public String moveup(int[][] a,int i,int j)
{
	int[][] b= new int[3][3];
		for(int x=0;x<3;x++)
	{
		for(int y=0;y<3;y++)
		{
			
				b[x][y]=a[x][y];
		}
	}
	
	if(i==0)
	 {
	 	return null;	
}
else 
{

b[i][j]=a[i-1][j];
b[i-1][j]=0;
	
	String su="";
		for(int x=0;x<3;x++)
		{
		for(int y=0;y<3;y++)
	{
		
		su = su+ b[x][y];
	}
	
}
	
	if(!checkinresult(su))
	{

store(a,b);
return su;
}
else
{
	return null;
}
}
//return null;
}






public String move_right(int[][] a,int i,int j)
{

if(j!=2)
{
int[][] b= new int[3][3];
		for(int x=0;x<3;x++)
	{
		for(int y=0;y<3;y++)
		{
			
				b[x][y]=a[x][y];
		}
	}
b[i][j]=a[i][j+1];
b[i][j+1]=0;
	
	String su="";
		for(int x=0;x<3;x++)
		{
		for(int y=0;y<3;y++)
	{
		
		su = su+ b[x][y];
	}
	
}
	if(!checkinresult(su))
	{

store(a,b);
return su;
}
else
{
	return null;
}
}
else{
return null;}
//return null;
}


public List <String> generate(int[][] a)
{
List <String> l2=new ArrayList<String>();

	int i=0;
	int j=0;
	for(int x=0;x<3;x++)
	{
		for(int y=0;y<3;y++)
		{
			
			if(a[x][y]==0)
			{
			i=x;
			j=y;
				
		}
		}
	}
/*Testing-If you change this pattern answer might change */	
l2.add(move_left(a,i,j)); 
        l2.add(movedown(a,i,j)); 
        l2.add(move_right(a,i,j)); 
		l2.add(moveup(a,i,j));

/*
l2.add(move_left(a,i,j)); 
l2.add(moveup(a,i,j));
l2.add(move_right(a,i,j)); 
l2.add(movedown(a,i,j)); 
	*/	
        
while(l2.remove(null)){}

return l2;
}

//BFS function making use of queue
public boolean bfs(String a)
{
	
	Queue<String> q = new LinkedList<String>(); 

	q.add(a); 
	store_nodes.put(a,""); 
 
int counter=0;
	while(q.size()!=0)
{
	
	String p= q.peek();	
	q.remove();
	char[] stringToCharArray = p.toCharArray();
	
	int[][] arr=new int[3][3];
	
	int count=0;
	for(int i=0;i<3;i++)
	{
		for(int j=0;j<3;j++)
		{
			char co=stringToCharArray [count];
			int ab=Integer.parseInt(String.valueOf(co));  
			arr[i][j]=ab; 
			count++;
		}
	}
	/*
	for(int i=0;i<3;i++)
	{
		for(int j=0;j<3;j++)
		{ 
	System.out.print(" " + arr[i][j]);
		}
	}
	System.out.println();
	*/

	
	if(checkgoal(p)==false)
	{
	 
	 List<String> sub = new ArrayList<String>(); 
	 sub=generate(arr);
	 
	 for(int i=0;i<sub.size();i++)
	 {
	 
	 q.add(sub.get(i));
	}
	 
	  for(int i=0;i<sub.size();i++)
	 {
	String s= sub.get(i); 
	if(s.equals(goalstate))
		{
			return true;
		}
	}
	 
	 sub.clear(); 
}

	
	 else
	 
	 {
	 	
		 		
	 	return true;
	  } 
	
}

return false;

}


}

//comparing the values(Was done for testing)

class ValueComparator implements Comparator<String> {
    Map<String, Integer> base;

    public ValueComparator(Map<String, Integer> base) {
        this.base = base;
    }
    public int compare(String a, String b) {
        if (base.get(a) < base.get(b)) {
            return -1;
        } else {
            return 1;
        } 
    }
}