/**
 * 
 */
package edu.gks.random;

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
	 * @param args
	 */
	public static void main(String[] args) {
		RecAndDp r = new RecAndDp();
		r.longestCommonSubsequence("aggtab", "gxtxayb");
//		r.longestCommonSubsequence("abc", "ac");

	}

}
