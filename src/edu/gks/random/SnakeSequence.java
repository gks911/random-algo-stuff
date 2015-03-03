/**
 * 
 */
package edu.gks.random;

/**
 * @author gaurav
 *
 */
public class SnakeSequence {

	int[][] grid;
	int[][] memo;
	int m,n;
	public SnakeSequence(int[][] grid){
		this.grid=grid;
		this.m=grid.length;
		this.n=grid[0].length;
		this.memo=new int[grid.length][grid[0].length];
	}
	
	/**
	 * Calculates length of the snake sequence
	 * Difference between current element and the 
	 * next element should be 1. Only then do we 
	 * move forward.
	 */
	public void getLengthOfSnakeSeq(){
		int globalMax=1;
		int[][] visited = new int[m][n];
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				int[] max={1};
				dfsGrid(i,j,visited,max);
				globalMax=Math.max(max[0],globalMax);
//				System.out.printf("[%d,%d] => %d\n",i,j,globalMax);
			}
		}
		System.out.println("Max length = "+globalMax);
	}
	
	
	private void dfsGrid(int x, int y, int[][] visited, int[] max) {
		int[] xCand = { 0, 1, 0, -1, 1, -1, 1, -1 };
		int[] yCand = { 1, 0, -1, 0, -1, 1, 1, -1 };
		for (int i = 0; i < xCand.length; i++) {
			int nx = x + xCand[i];
			int ny = y + yCand[i];
			if (isOk(x, y, nx, ny, visited)) {
				max[0] += 1;
				visited[nx][ny] = max[0];
				dfsGrid(nx, ny, visited, max);
//				visited[nx][ny] = 0;
			}
		}
	}
	
	private boolean isOk(int x, int y, int nx, int ny, int[][] visited){
		if(nx>=0 && ny>=0 && nx<m && ny<n && visited[nx][ny]==0 && grid[nx][ny]-grid[x][y]>=1)
			return true;
		return false;
	}
	
	public int snakeLengthDP(){
		//initialize
		for(int i=0;i<m;i++)
			for(int j=0;j<n;j++)
				memo[i][j]=-1;
		
		int max=0;
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				int curLen = snakeLengthRecursive(i,j);
				max = Math.max(max, curLen);
			}
		}
		System.out.println("Longest snake DP = "+max);
		return max;
	}
	
	private int snakeLengthRecursive(int x, int y) {
		if(memo[x][y]!=-1)
			return memo[x][y];
		int cur=0;
		int max=0;
		int[] xCand = { 0, 1, 0, -1, 1, -1, 1, -1 };
		int[] yCand = { 1, 0, -1, 0, -1, 1, 1, -1 };
		for (int i = 0; i < xCand.length; i++) {
			int nx = x+xCand[i];
			int ny = y+yCand[i];
			if(isOk(x, y, nx, ny)){
				cur = snakeLengthRecursive(nx, ny);
				max=Math.max(max, cur);
			}
		}
		memo[x][y]=max+1;
		return memo[x][y];
	}

	private boolean isOk(int x, int y, int nx, int ny){
		if(nx>=0 && ny>=0 && nx<m && ny<n && grid[nx][ny]-grid[x][y]>=1)
			return true;
		return false;
	}
	
	public static int[][] createGrid(){
//		int[][] grid = {{2,3,4,5},{4,5,10,11},{20,6,9,12},{6,7,8,40}};
		int[][] grid = {{97,47,56,36,60,31,57,54,12,55},
				{35,57,41,13,82,80,71,93,31,62},
				{89,36,98,75,91,46,95,53,37,99},
				{25,45,26,17,15,82,80,73,96,17},
				{75,22,63,96,96,36,64,31,99,86},
				{12,80,42,74,54,14,93,17,14,55},
				{14,15,20,71,34,50,22,60,32,41},
				{90,69,44,52,54,73,20,12,55,52},
				{39,33,25,31,76,45,44,84,90,52},
				{94,35,55,24,41,63,87,93,79,24}};
		return grid;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SnakeSequence seq = new SnakeSequence(createGrid());
//		seq.getLengthOfSnakeSeq();
		seq.snakeLengthDP();
	}

}
