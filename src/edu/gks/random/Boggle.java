/**
 * 
 */
package edu.gks.random;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author gaurav

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
 
 */
public class Boggle {
	
	private static class BoggleNode{
		int x,y;
		public BoggleNode(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
	
	char[][] grid;
	Set<String> dict;
	Set<String> res;
	
	public Boggle(char[][] grid, Set<String> dict){
		this.grid= grid;
		this.dict=dict;
		res=new HashSet<String>();
	}
	
	public void findAllWords(){
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		for(int i=0;i<grid.length;i++){
	          for(int j=0;j<grid[0].length;j++){
//	        	  boggleDfs(i,j);
	        	  boggleDfsRecursion(i, j, "",visited);
	          }
		}
	}

	/**
	 * TODO:
	 * Does not work currently.
	 * @param x
	 * @param y
	 */
	public void boggleDfs(int x, int y) {
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		Stack<BoggleNode> stack = new Stack<BoggleNode>();
		int[] xCandidates = { 1, 0, -1, 0 };
		int[] yCandidates = { 0, 1, 0, -1 };
		stack.push(new BoggleNode(x, y));
		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			BoggleNode node = stack.pop();
			if (node.x >= grid.length || node.y >= grid[0].length || node.x < 0 || node.y < 0)
				continue;
			if (!visited[node.x][node.y]) {
				visited[node.x][node.y] = true;
				sb.append(grid[node.x][node.y]);
				if (dict.contains(sb.toString()))
					res.add(sb.toString());
				for (int i = 0; i < xCandidates.length; i++)
					stack.push(new BoggleNode(node.x + xCandidates[i], node.y + yCandidates[i]));
			}
		}
	}

	/**
	 * Using recursion. May cause stack overflow errors
	 * @param x
	 * @param y
	 * @param prefix
	 * @param visited
	 */
	public void boggleDfsRecursion(int x, int y, String prefix, boolean[][] visited) {
		if (x >= grid.length || y >= grid[0].length || x < 0 || y < 0)
			return;
		if (visited[x][y])
			return;
		visited[x][y] = true;
		String word = prefix + grid[x][y];
		if (dict.contains(word))
			res.add(word.toString());
		// prepare candidates
		int[] xCandidates = { 1, 0, -1, 0 };
		int[] yCandidates = { 0, 1, 0, -1 };
		for (int i = 0; i < xCandidates.length; i++)
			boggleDfsRecursion(x + xCandidates[i], y + yCandidates[i], word, visited);
		// backtrack
		visited[x][y] = false;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		char[][] grid = new char[][] {{'C','A','Z'},
				 {'S', 'T','H'},
				 {'E', 'X', 'F'}};
		Set<String> dict = new HashSet<String>();
		dict.add("CAT");
		dict.add("CATS");
		dict.add("CSE");
		dict.add("TAZ");
		Boggle b = new Boggle(grid,dict);
		b.findAllWords();
		System.out.println(b.res);
	}
}
