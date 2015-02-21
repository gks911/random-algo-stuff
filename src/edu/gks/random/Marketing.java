/**
 * 
 */
package edu.gks.random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * @author gaurav
 *http://community.topcoder.com/stat?c=problem_statement&pm=1524&rd=4472
 *
 */
public class Marketing {

	ArrayList<Integer>[] adjList;
	int V;
	
	@SuppressWarnings("unchecked")
	public long howMany(String[] compete){
		V=compete.length;
		adjList=new ArrayList[V];
		for(int i=0;i<V;i++){
			if(adjList[i]==null)
				adjList[i]=new ArrayList<Integer>();
			if(compete[i].equals("")) continue;
			String[] con = compete[i].split(" ");
			for(int s=0;s<con.length;s++){
				int x=Integer.parseInt(con[s]);
				adjList[i].add(x);
				if(adjList[x]==null)
					adjList[x]=new ArrayList<Integer>();
				adjList[x].add(i);
			}
		}
		return dfs();
	}

	private long dfs(){
		Stack<Integer> stack = new Stack<Integer>();
		//-1=un-visited, 0=adult, 1=teen
		int[] assigned=new int[V];
		int[] prev=new int[V];
		Arrays.fill(assigned, -1);
		Arrays.fill(prev, -1);
		long total=0;
		for(int i=0;i<V;i++){
			//will be called again for a disconnected component
			if(assigned[i]!=-1) continue;
			stack.push(i);
			total++;
			//start with a random assignment
			assigned[i]=0;
			while (!stack.isEmpty()) {
				int v = stack.pop();
				for (int adj : adjList[v]) {
					prev[adj]=v;
					// if unassigned
					if (assigned[adj]==-1) {				
						assigned[adj]=assigned[prev[adj]]^1;
						stack.push(adj);
					} else {
						// it's already assigned.
						// check if it's a different color
						if(assigned[adj]==assigned[prev[adj]])
							return -1;
					}
				}
			}
		}
		return 1<<total;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Marketing m = new Marketing();
		System.out.println(m.howMany(new String[]{"1 2 4", "5 3", "3 6", "7", "5 6", "7", "7", "", "", "", "11 12 14", "15 13", "13 16", "17", "15 16", "17", "17", "", "", "", "", "", "", "", ""}));
	}

}
