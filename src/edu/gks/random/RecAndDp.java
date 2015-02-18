/**
 * 
 */
package edu.gks.random;

import java.util.HashSet;
import java.util.Set;

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
		System.out.printf("Minimum edit distance = %d",memo[str1.length()][str2.length()]);
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
	public void longestNonRepeatingSubstring(char[] str){
		Set<Character> set = new HashSet<Character>(str.length);
		int maxTillHere=1;
		int maxSoFar=1;
		//initialize
		set.add(str[0]);
		for(int i=1;i<str.length;i++){
			if(set.contains(str[i])){
				set.clear();
				maxTillHere=1;
				set.add(str[i]);
			}else{
				maxTillHere=maxTillHere+1;
				set.add(str[i]);
			}
			
			if(maxTillHere>maxSoFar)
				maxSoFar=maxTillHere;
		}
		System.out.println("Longest Non-repeating substring = "+maxSoFar);
	}
	
	/**
	 * Greedy approach
	 * Assuming no negative integers in array
	 * @param arr
	 */
	public void arrayJumpGame(int[] arr){
		int marker=0;
		int hops=0;
		while(marker<arr.length){
			if(arr[marker]==0)
				break;
			if(marker+arr[marker]>=arr.length-1){
				marker=arr.length-1;
				break;
			}
			marker=getIndexOfBestChoice(marker,arr);
			hops++;
		}
		if(marker==arr.length-1)
			System.out.println("Number of jumps = "+(hops+1));
		else
			System.out.println("Jump not possible!");
	}
	
	private int getIndexOfBestChoice(int marker, int[] arr) {
		if(arr[marker]==1) return marker+1;
		else{
			int max=marker+1;
			for(int i=marker+2;i<=marker+arr[marker] && i<arr.length;i++){
				if(arr[i]>arr[max])
					max=i;
			}
			return max;
		}
	}

	/**
	 * Assuming no negative numbers
	 * @param arr
	 * @param target
	 * @return
	 */
	public boolean subsetSum(int[] arr, int sum){
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
	 * @param args
	 */
	public static void main(String[] args) {
		RecAndDp r = new RecAndDp();
		System.out.println(r.binomialCoefficient(5, 2));
//		r.longestCommonSubsequence("aggtab", "gxtxayb");
//		r.longestCommonSubsequence("abc", "ac");
//		r.maxDiff(new int[]{2,1,2,0,1});
//		r.findPatternAt(4);
//		r.largestSumContiguous(new int[]{-2, -3, 4, -1, -2, 1, 5, -3});
//		r.longestIncreasingSubequence(new int[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15});
//		r.efficientLIS(new int[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15});
//		r.efficientLIS(new int[]{1,3,5,8,9,2,6,7,6,8,9});
//		r.editDistance("intention", "execution");
//		int[][] grid = {{1,2,3},
//						{4,8,2},
//						{1,5,3}};
//		r.minCostPath(grid, 2, 2);
//		r.longestNonRepeatingSubstring("geeksforgeeks".toCharArray());
//		int[] arr = new int[25001];
//		for(int i=25000;i>=0;i--){
//			arr[25000-i]=i;
//		}
//		r.arrayJumpGame(arr);
//		 r.arrayJumpGame(new int[]{4,11,1,1,1,1,1,1,1,1,1,1,1});
//		r.subsetSum(new int[]{1,5,10,25},100);
	}

}
