/**
 * 
 */
package edu.gks.random;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author gaurav
 * http://community.topcoder.com/stat?c=problem_statement&pm=1593&rd=4494&rm=&cr=266571
 */
public class Circuits {

	ArrayList<Integer>[] adjList;
	int[][] cost;
	int V;
	/**
	 * Eg i/p: 
	 *  
	 *  {"1 2 3 4 5","2 3 4 5","3 4 5","4 5","5",""}
	 *	{"2 2 2 2 2","2 2 2 2","2 2 2","2 2","2",""}
	 *
	 * @param connects
	 * @param costs
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int howLong(String[] connects, String[] costs){
		V=connects.length;
		cost = new int[V][V];
		adjList=new ArrayList[V];
		for(int i=0;i<V;i++){
			adjList[i]=new ArrayList<Integer>();
			if(connects[i].equals("")) continue;
			String[] con = connects[i].split(" ");
			String[] cos = costs[i].split(" ");
				for(int s=0;s<con.length;s++){
					int x=Integer.parseInt(con[s]);
					int c=Integer.parseInt(cos[s]);
					adjList[i].add(x);
					cost[i][x]=c;
				}
		}
		return dfs();
	}
	
	public int dfs(){
		Stack<Integer> stack = new Stack<Integer>();
		int result=0;
		for (int i=0;i<V;i++){
			int[] maxTmp=new int[V];
			stack.push(i);
			while (!stack.isEmpty()) {
				int v = stack.pop();
					for (int x : adjList[v]) {
						if(maxTmp[x]<maxTmp[v]+cost[v][x]){
							maxTmp[x]=maxTmp[v]+cost[v][x];
							stack.push(x);
						}
					}
			}
			for(int j=0;j<V;j++){
				if(maxTmp[j]>result)
					result=maxTmp[j];
			}
//			System.out.printf("%d max = %d\n",i,result);
		}
		return result;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Circuits c = new Circuits();
//		System.out.println(c.howLong(new String[]{"1 2 3 4 5","2 3 4 5","3 4 5","4 5","5",""}, new String[]{"2 2 2 2 2","2 2 2 2","2 2 2","2 2","2",""}));
//		System.out.println(c.howLong(new String[] { "", "", "", "38 35 14 40 21 19 27 4 31", "", "", "", "", "", "",
//				"", "", "", "1 27 37", "", "29 6 38", "", "1 45 28", "20 26 24 10 17 6", "", "47 1 24", "", "",
//				"13 7 40 43 8 30 48 10 19", "", "", "7 45 0 2 47", "", "", "", "", "", "", "", "", "", "", "45 44",
//				"7 12 28", "4 19 35 9", "6 22 46", "", "48 26 3 22 19 25 40 28", "18", "", "", "13 30", "",
//				"8 15 40 12 45 41 7 0 22", "" }, new String[] { "", "", "", "51 45 94 86 94 16 4 36 66", "", "", "",
//				"", "", "", "", "", "", "10 39 33", "", "83 89 12", "", "13 75 97", "52 24 30 87 21 72", "",
//				"49 15 67", "", "", "90 53 63 36 14 62 81 36 55", "", "", "96 36 87 86 90", "", "", "", "", "", "", "",
//				"", "", "", "69 100", "48 57 47", "71 27 21 36", "17 39 49", "", "74 3 17 69 100 90 3 84", "16", "",
//				"", "66 52", "", "40 31 97 67 85 89 90 62 87", "" }));
		
		System.out.println(c.howLong(new String[] { "", "42", "46", "23 39 40 21 4 5 6", "", "", "", "27 35 28 34", "",
				"", "42 29 47 32", "1 27", "49 40 39 7 31 10 48", "27 47 33 41 39", "46 11 21 13",
				"6 20 47 2 17 33 21 14 29", "", "13 36 31 6 37 7", "5 7 37 31 34 40 26", "", "", "", "", "", "",
				"38 43 22 42 1 26", "", "", "39 10", "", "32 43 14 18 47 20 45", "48 40 10 27 4", "", "", "", "", "",
				"", "16", "", "", "4 28 25 12 31 43 18 20 27", "", "", "", "29 7 21 8 24 40", "29 36 37 0 10", "",
				"2 33 47 9 45 22", "" }, new String[] { "", "70", "77", "33 33 71 79 75 51 31", "", "", "",
				"26 24 18 29", "", "", "55 37 22 43", "60 50", "90 98 11 64 44 8 22", "92 92 32 42 48", "50 30 16 86",
				"96 83 26 70 75 77 79 99 4", "", "26 83 37 77 52 9", "21 43 82 56 31 60 2", "", "", "", "", "", "",
				"13 43 83 3 66 72", "", "", "17 47", "", "21 6 77 53 95 34 75", "86 76 80 37 4", "", "", "", "", "",
				"", "1", "", "", "74 7 96 82 54 85 28 96 28", "", "", "", "70 83 22 63 94 78", "68 65 31 49 2", "",
				"86 29 73 1 55 78", "" }));

	}

}
