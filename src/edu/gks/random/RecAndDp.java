/**
 * 
 */
package edu.gks.random;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author gaurav
 *
 */
public class RecAndDp {

	/**
	 * Longest common subsequence of two strings
	 * @param str1
	 * @param str2
	 */
	public void longestCommonSubsequence(String str1, String str2){
		int[][] memo = new int[str1.length()][str2.length()];
		System.out.println(_lcsRecursionMemoized(str1, str2,str1.length(),str2.length(), memo));
	}
	
	private int _lcsRecursionMemoized(String str1, String str2, int ptr1, int ptr2, int[][] memo){
		int lcs=0;
		if(ptr1==0 || ptr2==0){
			return 0;
		}else if(memo[ptr1-1][ptr2-1] > 0) {
			return memo[ptr1-1][ptr2-1];
		}else if (str1.charAt(ptr1-1)==str2.charAt(ptr2-1)){
			lcs=1 + _lcsRecursionMemoized(str1, str2, ptr1-1, ptr2-1,memo);
			memo[ptr1-1][ptr2-1]=lcs;
			return lcs;
		}else{
			return Math.max(_lcsRecursionMemoized(str1, str2, ptr1,ptr2-1,memo),
			_lcsRecursionMemoized(str1, str2, ptr1-1,ptr2,memo));
		}
	}
	
	/**
	 * max difference b/w two array elements
	 * given second element is after first
	 * Question kind of like stock buy-sell
	 * @param arr
	 */
	public void maxDiff(int[] arr){
		int idxOfMin=0;
		int max=Integer.MIN_VALUE;
		for(int i=1;i<arr.length;i++){
			if(arr[i]<arr[idxOfMin])
				idxOfMin=i;
			if(arr[i]-arr[idxOfMin]>max)
				max=arr[i]-arr[idxOfMin];
		}
		System.out.println("Max difference = "+max);
	}
	
	/**
	 * Write a program to find pattern. 
		0: 1 
		1: 11 
		2: 21 
		3: 1211 
		4: 111221 
		5: 312211 
		Iterate over the previous number, and find count for same number number. Append that count before number. 
		e.g. : If input = 4, function should return 111221.
	 * @param n
	 */
	public void findPatternAt(int n){
		String start="1";
		int i=0;
		while(i<n){
			start = condenseWord(start);
			System.out.println(start);
			i++;
		}
		System.out.printf("Word at %d, is %s",n,start);
	}
	
	private String condenseWord(String word){
		StringBuilder sb = new StringBuilder();
		int len = word.length(),i=0;
		
		while(i<len){
			int count=0;
			char cur=word.charAt(i);
			while(i<len && cur==word.charAt(i)){
				count++;i++;
			}
			sb.append(count);
			sb.append(cur);
		}
		return sb.toString();
	}
	
	
	/**
	 * Find Largest Sum Contiguous Subarray
	 * @param array
	 */
	public void largestSumContiguous(int[] array){
		int globalMax=array[0], maxUptoHere=array[0];
		for(int i=1;i<array.length;i++){
			maxUptoHere=Math.max(maxUptoHere+array[i],array[i]);
			if(globalMax<maxUptoHere)
				globalMax=maxUptoHere;
		}
		System.out.printf("\nLargest sum is %d\n",globalMax);
	}
	
	/**
	 * 
	 * @param arr
	 */
	public void longestIncreasingSubequence(int[] arr) {
		int[] memo = new int[arr.length];
		int[] sequence = new int[arr.length];
		int res = 0,endingAt=0;
		memo[0] = 1;
		sequence[0] = -1;
		for (int i = 1; i < arr.length; i++) {
			memo[i] = 1;
			for (int j = i - 1; j >= 0; j--) {
				if (arr[j] < arr[i] && memo[j] + 1 > memo[i]) {
					memo[i] = memo[j] + 1;
					sequence[i] = j;
				}
			}
			if (res < memo[i]) {
				res = memo[i];
				endingAt=i;
			}
		}
		System.out.printf("LIS = %d\n",res);
		//reconstruct the sequence
		reconstructSequence(arr, sequence, endingAt);
		System.out.println();
	}

	private void reconstructSequence(int[] arr, int[] seq, int index){
		if(index==-1) return;
		reconstructSequence(arr, seq, seq[index]);
		System.out.print(arr[index]+" ");
	}
	
	/**
	 * LIS in O(nLog(n))
	 * http://stackoverflow.com/questions/2631726/how-to-determine-the-longest-increasing-subsequence-using-dynamic-programming?rq=1
	 * @param arrs
	 */
	public void efficientLIS(int[] arr){
		assert(arr.length>0);
		int[] s = new int[arr.length];
		s[0]=arr[0];
		//point to last filled idx
		int curIdx=0;
		for(int i=1;i<arr.length;i++){
			if(arr[i] > s[curIdx])
				s[++curIdx]=arr[i];
			else{
				int swapWith = binarySearch(s, arr[i], curIdx);
				s[swapWith]=arr[i];
			}
		}
		System.out.println("Efficient LIS length = "+(curIdx+1));
	}
	
	public int binarySearch(int[] arr,int key, int max){
		int low=0,high=max;
		int mid=0;
		while(low<=high){
			mid=low+(high-low)/2;
			if(key==arr[mid])
				return mid;
			else if(key<arr[mid])
				high=mid-1;
			else
				low=mid+1;
		}
		return mid;
	}
	
	/**
	 * 
	 * @param str1
	 * @param str2
	 */
	public void editDistance(String str1, String str2){
		int[][] memo = new int[str1.length()+1][str2.length()+1];
		for(int i=0;i<memo.length;i++)
			memo[i][0]=i;
		for(int i=0;i<memo[0].length;i++)
			memo[0][i]=i;
		
		//substitution cost is 2
		for (int i=1;i<=str1.length(); i++) {
			for (int j=1;j<=str2.length(); j++) {
				memo[i][j] = Math.min(Math.min(memo[i][j-1]+1, memo[i-1][j]+1),
						memo[i-1][j-1] + (str1.charAt(i-1) == str2.charAt(j-1)?0:2));
			}
		}
		System.out.printf("Minimum edit distance = %d\n",memo[str1.length()][str2.length()]);
	}
	
	/**
	 * 
	 * @param str1
	 * @param str2
	 * @param m
	 * @param n
	 * @return
	 */
	public int editDistanceMemoized(String str1, String str2, int m, int n, int[][] memo){
		if(m<0&&n<0) return 0;
		if(m<0) return n;
		if(n<0) return m;
		if(memo[m][n]>0)
			return memo[m][n];
//		if(str1.charAt(m)==str2.charAt(n)){
//			memo[m][n]=editDistanceMemoized(str1, str2, m-1, n-1,memo);
//			return memo[m][n];
//		}
		else{
			 memo[m][n]=Math.min((Math.min(editDistanceMemoized(str1, str2, m, n-1,memo), editDistanceMemoized(str1, str2, m-1, n,memo)))+1,
					 ((str1.charAt(m)==str2.charAt(n))?0:1)+editDistanceMemoized(str1, str2, m-1, n-1,memo));
			 return memo[m][n];
		}
	}
	
	/**
	 * Starting at (0,0) find min cost path till (m,n)
	 * @param grid
	 * @param m
	 * @param n
	 */
	public void minCostPath(int[][] grid, int m, int n){
		int[][] memo = new int[grid.length][grid[0].length];
		assert(m<grid.length);
		assert(n<grid[0].length);
		//initialize
		memo[0][0]=grid[0][0];
		for(int i=1;i<grid.length;i++)
			memo[i][0]=memo[i-1][0]+grid[i][0];
		for(int i=1;i<grid[0].length;i++)
			memo[0][i]=memo[0][i-1]+grid[0][i];
		
		for(int i=1;i<=m;i++){
			for(int j=1;j<=n;j++){
				memo[i][j]=Math.min(Math.min(memo[i-1][j-1], memo[i-1][j]),memo[i][j-1])+grid[i][j];
			}
		}
		System.out.printf("Cost of Path = %d",memo[m][n]);
	}
	
	/**
	 * Length of the longest substring without repeating characters
	 * @param str
	 */
	public int longestNonRepeatingSubstring(String s){
		//base cases
		if(s.length()==0) return 0;
		if(s.length()==1) return 1;

		Map<Character,Integer> set = new HashMap<Character,Integer>();
		int max=Integer.MIN_VALUE, cur=0, left=0;

		for(int i=0;i<s.length();i++){
		    char c=s.charAt(i);
			if(!set.containsKey(c)){
				cur+=1;
				set.put(c,i);
			}else{
			    int tmp=set.get(c)+1;
			    if(tmp>left)
			        left=tmp;
			    set.put(c,i);
				cur=i-left+1;
			}

			if(max<cur)
				max=cur;
		}
		System.out.println("Longest Non repeating substring = "+max);
		return max;
	}
	
	
	/**
	 * Greedy approach
	 * Assuming no negative integers in array
	 * @param arr
	 */
	public int arrayJumpGame(int[] arr){
	      if(arr.length==1) return 0;
	      int maxSoFar=arr[0],curMax=arr[0];
	      int hops=1;
	      for(int i=1;i<arr.length;i++){
	          curMax=Math.max(curMax,arr[i]+i);
	          if(i==arr.length-1)
	        	  return (maxSoFar>=arr.length-1)?hops:-1; 
	          if(i==maxSoFar){
	              ++hops;
	              maxSoFar=curMax;
	          }
	      }
	      return 0;
	    }
	
	 public boolean canJump(int[] A) {
	        if(A.length==1) return true;
	        int maxSoFar=A[0],curMax=A[0];
	        for(int i=0;i<A.length-1;i++){
	            curMax=Math.max(A[i]+i,curMax);
	            if(i==maxSoFar)
	                maxSoFar=curMax;
	        }
	        return maxSoFar>=A.length-1;
	    }
	 
	/**
	 * Assuming no negative numbers
	 * @param arr
	 * @param target
	 * @return
	 */
	public boolean coinChange(int[] arr, int sum){
		int[] memo=new int[sum+1];
		//initialize
		memo[0]=1;
		for(int i=0;i<arr.length;i++){
			for(int j=arr[i];j<=sum;j++){
				memo[j]+=memo[j-arr[i]];
			}
		}
		if(memo[sum]>=1){
			System.out.printf("Sum exists in sub array, and %d ways to reach to it.",memo[sum]);
			return true;
		}
		return false;
	}
	
	public void subsets(int[] arr, int target){
		
	}
	
	private void _subsets(int[] arr, int target, int idx){
		if(idx>arr.length)
			return;
		
	}
	
	/**
	 * Find N(c,r)
	 * @param n
	 * @param r
	 */
	public int binomialCoefficient(int n, int r){
		if(n==r || r==0) return 1;
		else{
			return binomialCoefficient(n-1, r-1)+binomialCoefficient(n-1, r);
		}
	}
	
	/**
	 * 
	 * @param wt
	 * @param val
	 * @param W
	 */
	public void knapsackDynamic(int[] wt, int[] val, int W){
		int[][] memo = new int[wt.length+1][W+1];
		for(int i=0;i<=wt.length;i++){
			for(int w=0;w<=W;w++){
				if(i==0||w==0)
					memo[i][w]=0;
				else if(wt[i-1]<=w)
					memo[i][w]=Math.max(val[i-1]+memo[i-1][w-wt[i-1]], memo[i-1][w]);
				else
					memo[i][w]=memo[i-1][w];
			}
		}
		System.out.println("Max value in knapsack = "+memo[wt.length][W]);
	}
	
	/**
	 * Naively, using recursion
	 * @param str
	 * @return
	 */
	public int minInsertionsForPalindrome(String str){
		int x = _minInsertionsForPalindrome(str.toCharArray(), 0, str.length()-1);
		System.out.printf("Min insertions for a palindrome = %d\n",x);
		return x;
	}
	
	private int _minInsertionsForPalindrome(char[] charArray, int start, int end){
		if(end<start) return Integer.MAX_VALUE;
		//eg. 'q'
		if((end-start)==0) return 0;
		//eg. ['a','b'] or ['a','a']
		if(end-start==1) return (charArray[start]==charArray[end])?0:1;
		//eg. '[a,b,c,a]', now recurse only on '[b,c]'
		if(charArray[start]==charArray[end])
			return _minInsertionsForPalindrome(charArray, start+1, end-1);
		else 
			return Math.min(_minInsertionsForPalindrome(charArray, start + 1, end),
					_minInsertionsForPalindrome(charArray, start, end - 1)) + 1;
	}
	
	/**
	 * Using dynamic programming
	 * @param str
	 */
	public int minInsertForPalindromeDP(String str){
		if(str.length()==0) {
			System.out.println("DP Min insertions for a palindrome = "+Integer.MAX_VALUE);
			return Integer.MAX_VALUE;
		}
		int[][] memo = new int[str.length()][str.length()];
		memo[0][0]=0;
		for(int i=1;i<str.length();i++){
			for(int start=0,end=i;end<str.length();start++,end++){
				if(str.charAt(start)==str.charAt(end))
					memo[start][end]=memo[start+1][end-1];
				else
					memo[start][end]= Math.min(memo[start+1][end], memo[start][end-1])+1;
			}
		}
		System.out.println("DP Min insertions for a palindrome = "+memo[0][str.length()-1]);
		return memo[0][str.length()-1];
	}
	
	
	/**
	 * Calculate the dist to a destination 
	 * @author gaurav
	 *
	 */
	private class Pair{
		int x; 
		int y;
		public Pair(int x, int y){
			this.x=x;this.y=y;
		}
		@Override
		public String toString(){
			return String.format("[%d,%d]", x,y);
		}
		@Override
		public boolean equals(Object obj) {
			Pair p = (Pair) obj;
			if(x==p.x&&y==p.y)
				return true;
			return false;
		}
	}
	public void knightShortestPath(int srcX, int srcY, int destX, int destY, int m, int n){
		//handle base cases before proceeding (!)
		int[] xCand={1,1,2,2,-1,-1,-2,-2};
		int[] yCand={2,-2,1,-1,2,-2,1,-1};
		int[][] dist = new int[m][n];
		boolean[][] visited= new boolean[m][n];
		Queue<Pair> q = new LinkedList<Pair>();
		Map<Pair,Pair> path= new HashMap<Pair,Pair>();
		Pair src = new Pair(srcX,srcY);
		q.add(src);
		path.put(src, null);
		visited[srcX][srcY]=true;
		
		while(!q.isEmpty()){
			Pair p = q.poll();
			for(int i=0;i<xCand.length;i++){
				int nx=p.x+xCand[i];
				int ny=p.y+yCand[i];
				if (nx >= 0 && ny >= 0 && nx < m && ny < n && !visited[nx][ny]) {
					Pair neighbor = new Pair(nx, ny);
					q.add(neighbor);
					dist[nx][ny] = dist[p.x][p.y] + 1;
					visited[nx][ny] = true;
					if (nx == destX && ny == destY)
						System.out.println("Distance to destination " + dist[nx][ny]);
				}
			}
		}
		System.out.println();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RecAndDp r = new RecAndDp();
		r.knightShortestPath(0, 0, 0, 2, 3, 3);
		r.minInsertionsForPalindrome("topcoder");
//		r.minInsertForPalindromeDP("abcda");
//		r.knapsackDynamic(new int[]{1,2,3,4}, new int[]{10,30,40,30}, 4);
//		System.out.println(r.binomialCoefficient(10, 4));
//		r.longestCommonSubsequence("aggtab", "gxtxayb");
//		r.longestCommonSubsequence("abc", "ac");
//		r.maxDiff(new int[]{2,1,2,0,1});
//		r.findPatternAt(4);
//		r.largestSumContiguous(new int[]{-2, -3, 4, -1, -2, 1, 5, -3});
//		r.longestIncreasingSubequence(new int[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15});
//		r.efficientLIS(new int[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15});
//		r.efficientLIS(new int[]{1,3,5,8,9,2,6,7,6,8,9});
		r.editDistance("intention", "execution");
		int[][] memo = new int[9][9];
		System.out.println("Edit Distance recursion = "+r.editDistanceMemoized("intention", "execution",8,8,memo));
//		int[][] grid = {{1,2,3},
//						{4,8,2},
//						{1,5,3}};
//		r.minCostPath(grid, 2, 2);
		r.longestNonRepeatingSubstring("dvdf");
//		int[] arr = new int[25001];
//		for(int i=25000;i>=0;i--){
//			arr[25000-i]=i;
//		}
//		r.arrayJumpGame(arr);
		 System.out.println("No of Jumps = "+r.arrayJumpGame(new int[]{7,0,9,6,9,6,1,7,9,0,1,2,9,0,3}));
		 System.out.println("Can Jump = "+r.canJump(new int[]{3,2,1,0,4}));
		r.coinChange(new int[]{2,5,10,25},11);
	}

}
