/**
 * 
 */
package edu.gks.random;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author gaurav
 *Hi Gaurav - 

/**
* Given a grid of 'grid_size' * 'grid_size' letters, find the set of words that can be formed by starting at
* any letter in the grid and repeatedly moving one column or row at a time in any
* direction.
*
* So for the grid:
*
* C A Z
* S T H
* E F F
*
* The only valid word is CAT. Diagonal moves are not allowed, so RAT, ARC, CAR, ART, CART
* and TAR are not valid words. Grid positions may not be used more than once in a word 
* (i.e. no doubling back).
 

class WordFinder {
  public Set<String> FindAllWords(Set<String> dict, char[][] grid) {
      //NUM_ROWS,NUM_COLS
      for(int i=0;i<NUM_ROWS;i++){
          for(int j=0;j<NUM_COLS;j++){
                  //start afresh with each cell
                 _findAllWords("",i,j);
          }        
      }
  }
  
  
  // _findAllWords("",0,0)
  // _findAllWords(String prefix, int i, int j):
  // 1> infinite recursion> 2.> str.rev 3.> recurrence rel. 
     //
    //      if (i>NUM_ROWS||j>NUM_COLS||i>prev.row -1 ||j>prev.col-1) return;
  //        if (dict.contains(prefix)) : Output the prefix into the result set
   //       _findAllWords(prefix+grid[i][j], i,j+1);   -->
  //        _findAllWords(prefix+grid[i][j], i+1,j);
   //        _findAllWords(prefix+grid[i][j], i,j-1);
    //        _findAllWords(prefix+grid[i][j], i-1,j);
    //
   //                    
   
   
}
 */
public class BADGrid {
	
	Graph<Node> graph;
	char[][] grid;
	int[][] marked;
	Set<String> dict;
	int rows, cols;
	
	public BADGrid(){
		grid= new char[][]      {
											{'C','A','Z'},
											{'S', 'T','H'},
											{'E', 'X', 'F'}};
		dict=new HashSet<String>();
		marked=new int[grid.length][grid[0].length];
		dict.add("CAT");
		dict.add("CATS");
		rows=3;
		cols=3;
		graph = new Graph<Node>(9);
		List<Node> _tmp=new ArrayList<Node>();
		int count=0;
		for(int i=0;i<rows;i++){
			for(int j=0;j < cols ;j++){
				_tmp.add(new Node(++count, grid[i][j], i, j));
			}
		}
		
		for(int i=0;i<_tmp.size();i++){
			graph.addEdge(_tmp.get(i), _tmp.get(index));
		}
	}
	
	public void findItMofo(){
		for(int i=0;i<rows;i++){
	          for(int j=0;j<cols;j++){
	        	  _findItMofo("", 0, 0);
	          }
		}
	}
	
	public void _findItMofo(String pre, int i, int j){
//		System.out.println(pre);
		if(i>rows-1|| j>cols-1) return;
		if(dict.contains(pre)) System.out.println(pre);
		_findItMofo(pre+grid[i][j], i, j+1);
		_findItMofo(pre+grid[i][j], i+1, j);
//		_findItMofo(pre+grid[i][j], i-1, j);
//		_findItMofo(pre+grid[i][j], i, j-1);
	}
	
	
	public void dfs(int m, int n){
		marked[m][n]=1;
		for(int i : new int[]{m+1,n+1,m-1,n-1}){
			
		}
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BADGrid b = new BADGrid();
		b.findItMofo();
	}
}
