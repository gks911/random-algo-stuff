/**
 * 
 */
package edu.gks.random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author gaurav
 *
 */
public class CTCI {

	int path = 0;

	/**
	 * 1.5
	 * 
	 * @param text
	 */
	public void compressString(String text) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while (i < text.length()) {
			char c = text.charAt(i);
			int count = 0;
			while (i < text.length() && c == text.charAt(i)) {
				i++;
				count++;
			}
			sb.append(c).append(count);
		}
		System.out.println(text + " = " + sb.toString());
	}

	public void twoSum(long[] array, long sum) {
		Set<Long> set = new HashSet<Long>();
		for (int i = 0; i < array.length; i++) {
			if (set.contains(sum - array[i]))
				System.out.printf("[%d,%d]\n", array[i], sum - array[i]);
			else
				set.add(array[i]);
		}
	}

	public void getNumPaths(int[][] grid, int m, int n) {
		// x-y is current location
		// Do we need additional grid? Probably to reconstruct the path
		_numPathsBacktrack(0, 0, m, n, grid);
	}

	private void _numPathsBacktrack(int x, int y, int m, int n, int[][] grid) {
		if (x > m - 1 || y > n - 1)
			return;
		if (x == m - 1 && y == n - 1) {
			// we have a solution
			path++;
			return;
		} else {
			// generate candidates, should be at a class level
			int[] _x = new int[] { 0, 1 };
			int[] _y = new int[] { 1, 0 };
			for (int i = 0; i < 2; i++) {
				// move
				x += _x[i];
				y += _y[i];
				_numPathsBacktrack(x, y, m, n, grid);
				// unmove
				x -= _x[i];
				y -= _y[i];
			}
		}
	}
	
	public void numPathsDP(int x, int y, int m, int n, int[][] obstacleGrid){
		int[][] memo = new int[m][n];
		
		for(int i=0;i<m;i++)
			memo[i][0]=1;
		
		for(int i=0;i<n;i++)
			memo[0][i]=1;
		
		for(int i=1;i<m;i++){
			for (int j=1;j<n;j++){
				if(obstacleGrid[i][j]!=1)
					memo[i][j]=memo[i-1][j]+memo[i][j-1];
			}
		}
		System.out.println("Number of paths = "+memo[m-1][n-1]);
	}

	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int m=obstacleGrid.length;
		int n=obstacleGrid[0].length;
		
		if(obstacleGrid[0][0]==1 || obstacleGrid[m-1][n-1]==1)
		    return 0;
		if (m==1 && n==1){
		    if (obstacleGrid[m-1][n-1]==0)
		        return 1;
		    else return 0;
		}
        return numPaths(0,0,m,n,obstacleGrid);
    }
    
    public int numPaths(int x, int y, int m, int n, int[][] obstacleGrid){
		int[][] memo = new int[m][n];
		
		for(int i=0;i<m;i++){
			if(obstacleGrid[i][0]==1)
				break;
				memo[i][0]=1;
		}
		
		for(int i=0;i<n;i++){
			if(obstacleGrid[0][i]==1)
				break;
				memo[0][i]=1;
		}
		
		for(int i=1;i<m;i++){
			for (int j=1;j<n;j++){
				if(obstacleGrid[i][j]!=1)
					memo[i][j]=memo[i-1][j]+memo[i][j-1];
			}
		}
		return memo[m-1][n-1];
	}

    
	public void generateSubsets(int[] input) {
		assert (input.length > 1);
		boolean[] candidates = new boolean[input.length];
		_subsetBacktrack(input, candidates, 0);
	}

	private void _subsetBacktrack(int[] input, boolean[] a, int k) {
		if (k == input.length) {
			// we got a solution
			System.out.print("{ ");
			for (int i = 0; i < input.length; i++)
				if (a[i] == true)
					System.out.print(input[i] + " ");
			System.out.print(" }\n");
			return;
		} else {
			boolean[] candidates = new boolean[] { true, false };
			for (int i = 0; i < candidates.length; i++) {
				a[k] = candidates[i];
				_subsetBacktrack(input, a, k + 1);
			}
		}
	}

	/**
	 * Algo: (from Wikipedia) 1. Find the largest index k such that a[k] < a[k +
	 * 1]. If no such index exists, the permutation is the last permutation. 2.
	 * Find the largest index l greater than k such that a[k] < a[l]. 3. Swap
	 * the value of a[k] with that of a[l]. 4. Reverse the sequence from a[k +
	 * 1] up to and including the final element a[n].
	 * 
	 * @param arr
	 */
	public void nextPermutation(char[] arr) {
		int n = arr.length;
		int k = n - 2;
		while (k >= 0) {
			if (arr[k] < arr[k + 1])
				break;
			k--;
		}

		if (k == -1) {
			System.out.println("No such perm exists!");
			return;
		}

		int l = n - 1;
		while (l > k) {
			if (arr[l] > arr[k])
				break;
			l--;
		}

		// swap k and l
		_swap(arr, k, l);
		// reverse elements b/w k+1 to n-1
		_reverse(arr, k + 1, n - 1);

		for (int i = 0; i < n; i++)
			System.out.print(arr[i]);
		System.out.println();
	}

	/**
	 * Print all combintations of a string
	 * @param str
	 */
	public void printAllSubsets(String str){
		_printAllSubsets(str,"");
	}
	
	private void _printAllSubsets(String str,String prefix){
		if(str.isEmpty())
			System.out.println(prefix);
		else{
				_printAllSubsets(str.substring(1), prefix+str.charAt(0));
				_printAllSubsets(str.substring(1), prefix);
		}
	}
	
	private void _reverse(char[] arr, int from, int to) {
		int i = from, j = to;
		while (i < j) {
			_swap(arr, i++, j--);
		}
	}

	private void _swap(char[] arr, int i, int j) {
		char _tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = _tmp;
	}

	public void recursivePermutation(String prefix, String rest){
		if(rest.isEmpty()) System.out.println(prefix);
		else{
			for(int i=0;i<rest.length();i++){
				String newPrefix=prefix+rest.charAt(i);
				String newRest = rest.substring(0,i)+rest.substring(i+1);
//				System.out.printf("Recursing for prefix=%s, rest=%s\n",newPrefix,newRest);
				recursivePermutation(newPrefix, newRest);
			}
		}
	}
	
	/**
	 * 9. Given an unsorted, even-numbered array of integers, divide the array
	 * into two lists of the equal sizes such that their total is as close as
	 * possible
	 * 
	 * @param arr
	 */
	public void divideIntoTwo(int[] arr) {
		// handle for two entries
		Arrays.sort(arr);
		int n = arr.length;
		int[] arr1 = new int[n / 2];
		int[] arr2 = new int[n / 2];
		// split the middle two elements in one array each?
		// otherwise, put first and last element in arr1,
		// Similarly put first+1 last-1 in arr2 etc.
		boolean divideOdd = true;
		if ((n / 2) % 2 == 0)
			divideOdd = false;

		int _idx1 = 0, _idx2 = 0, i = 0, j = n - 1, totalIter = n / 2, iter = 0;

		if (divideOdd)
			totalIter--;
		while (i < j && iter++ < totalIter) {
			if (i % 2 == 0) {
				arr1[_idx1++] = arr[i++];
				arr1[_idx1++] = arr[j--];
			} else {
				arr2[_idx2++] = arr[i++];
				arr2[_idx2++] = arr[j--];
			}
		}

		if (divideOdd) {
			arr1[_idx1] = arr[i];
			arr2[_idx2] = arr[j];
		}

		int sum1 = 0, sum2 = 0;
		for (int k = 0; k < n / 2; k++) {
			sum1 += arr1[k];
			sum2 += arr2[k];
		}
		System.out.printf("Sum 1= %d\tSum 2=%d\n", sum1, sum2);
	}

	/**
	 * Find first non repeating char from stream
	 */
	private class Node {
		char c;

		public Node(char c) {
			this.c = c;
		};
	}

	public void nonRepeatingCharFromStream() {
		Queue<Node> q = new LinkedBlockingDeque<Node>();
		Map<Character, Node> map = new HashMap<Character, Node>();
		Scanner scanner = new Scanner(System.in);
		while (true) {
			char c = scanner.next().charAt(0);
			if (c == ' ')
				break;
			if (map.containsKey(c)) {
				q.remove(map.get(c));
				map.remove(c);
			} else {
				Node x = new Node(c);
				q.add(x);
				map.put(c, x);
			}
			if (q.size() > 0)
				System.out.printf("First non repeating char: %c\n", q.peek().c);
		}
		scanner.close();
	}

	/**
	 * Find longest consecutive char: 
	 * 'this is a test sentence' => [t, h, i, s,i, s, a, t, e, s, t, s, e, n, t, e, n, c, e] 
	 * 'thiiiis iss a teeest seeentennncccce' => [i, c] 
	 * 'thiiis iss aa teeest seentennnce' => [i, e, n]
	 * @param text
	 */
	public void getLongestConsecutiveChar(String text) {
		List<Character> res = new ArrayList<Character>();
		int ptr = 0;
		int max = 1;
		while (ptr < text.length()) {
			int smallPtr = ptr + 1;
			int curMax = 1;
			while (smallPtr < text.length() && text.charAt(smallPtr - 1) == text.charAt(smallPtr)) {
				curMax++;
				smallPtr++;
			}
			if (max < curMax) {
				max = curMax;
				res.clear();
				res.add(text.charAt(ptr));
			} else if (max == curMax) {
				res.add(text.charAt(ptr));
			}
			// max > curMax, ignore the character
			ptr = ptr + curMax;
		}
		// account for spaces. Not taken care of here.
		System.out.printf("Longest Consecutive character = %s", res);
	}

	/**
	 * You are given a set of unique characters and a string.
		Find the smallest substring of the string containing all the characters in the set.
		ex:
		Set : [a, b, c]
		String : "abbcbcba"
		Result: "cba"
	 */
	public void shortestDistinctSubstring(String text){
		Set<Character> distinct = new HashSet<Character>();
		char[] arr=text.toCharArray();
		for(char c: arr)
			distinct.add(c);
	
		//Brute force
		int min=0,max=arr.length-1;
		for(int i=0;i<arr.length;i++){
			Set<Character> tmpDistinct = new HashSet<Character>();
			tmpDistinct.add(arr[i]);
			for(int j=i;j<arr.length;j++){
				tmpDistinct.add(arr[j]);
				if(tmpDistinct.size() == distinct.size()){
					if((j-i) < (max-min)){
						min=i;max=j;
					}
					break;
				}
			}
		}
		System.out.print("Shortest distinct string := ");
		for(int i=min;i<=max;i++)
			System.out.print(arr[i]);
		System.out.println("");
	}
	
	
	public void findOddAmongDuplicates(int[] arr){
		int x=arr[0];
		for (int i=1;i<arr.length;i++)
			x=x^arr[i];
		System.out.println("Odd one out: "+x);
	}
	
	public int medianOfTwoArrays(int[] arr1, int[] arr2, int l1,int r1, int l2,int r2){
//		if(m>n)
//			medianOfTwoArrays(arr2, arr1, Math.ma, n);
//		int i = m/2;
//		int j= (m+n)/2 - i;
//			
//		assert(i<m); assert(j<n);
//		
//		if (arr1[i] > arr2[j] && arr1[i] < arr2[j+1]){
//			System.out.printf("Median is [ %d ]\n",arr1[i]);
//			return;
//		}else if(arr1[i] < arr2[j] && arr1[i]<arr2[j+1])
//			medianOfTwoArrays(arr1, arr2, i+1, n);
//		else
//			medianOfTwoArrays(arr1, arr2, m, j-1);
		
		int i = l1+(r1-l1+1)/2;
		int j= ((r1-l1+1)+(r2-l2+1)) - i-1;
		
		if((r1-l1)==0) return arr2[(r2-l2)/2];
		if((r2-l2)==0) return arr1[(r1-l1)/2];
		if(arr1[i] == arr2[j]){
//			System.out.println("Median = "+arr1[i]);
			return arr1[i];
		}else if(arr1[i] <= arr2[j] && arr1[i] <= arr2[j+1] )
			return medianOfTwoArrays(arr1, arr2, i+1,r1, l2,j);
		else
			return medianOfTwoArrays(arr1, arr2, l1, i, j+1, r2);
	}

	/**
	 * Finding the Minimum Window in text which Contains All Elements from pattern
	 * @param text
	 * @param pattern
	 * @return
	 */
	public String getMinWindow(String text, String pattern){	
		int[] found = new int[26];
		int[] toFind = new int[26];
		int[] window = new int[2];
		int left=0,right=0,minWindow=Integer.MAX_VALUE;

		while(left < pattern.length()){
			found[pattern.charAt(left++)-'a']++;
		}

		//re-initialize
		left=0;
		for (; right < text.length(); right++) {
			if (found[text.charAt(right) - 'a'] == 0)
				continue;
			toFind[text.charAt(right) - 'a']++;

			if (allCharsCovered(found, toFind)) {
				while (toFind[text.charAt(left) - 'a'] == 0
						|| toFind[text.charAt(left) - 'a'] - 1 >= found[text.charAt(left) - 'a']) {
					// shrink possible
					if (toFind[text.charAt(left) - 'a'] - 1 >= found[text.charAt(left) - 'a'])
						toFind[text.charAt(left) - 'a'] -= 1;
					left++;
				}

				if ((right - left) < minWindow) {
					// update our window
					window[0] = left;
					window[1] = right + 1;
					minWindow = right - left;
				}
			}
		}
		//Old Solution
		//int txtPtr=0;
//		while(txtPtr<text.length()){
//			if(allCharsCovered(found,toFind)) break;
//			//increase the size of window until all characters are found
//			toFind[text.charAt(txtPtr++)-'a']++;
//		}
//
//		//Did we find nothing?
//		if(txtPtr>text.length()) return "Not Found!";
//
//		//found the first min window, [left,right]
//		right=txtPtr;
//		
//		while(true){
//			while (toFind[text.charAt(left)-'a'] - 1 >= found[text.charAt(left)-'a']){
//				//shrink possible
//				toFind[text.charAt(left)-'a']-=1;
//				left ++;
//			}		
//			if((right-left) < minWindow && allCharsCovered(found, toFind)){
//				//update our window
//				window[0]=left;
//				window[1]=right;
//				minWindow=right-left;
//			}
//			if(right>=text.length()) break;
//			toFind[text.charAt(right++)-'a']++;
//		}
//		
//		
		return new String(text.substring(window[0],window[1]));
	}

	private boolean allCharsCovered(int[] found, int[] toFind){
		for(int i=0;i<26;i++){
			if(toFind[i]<found[i])
				return false;
		}
		return true;
	}

	public int findKsmallest(int[] arr1, int[] arr2, int k){
		return _findKSmallest(arr1, arr2, k-1, 0, arr1.length, 0, arr2.length);
	}
	
	/**
	 * Not Working !
	 * @return
	 */
	public int _findKSmallest(int[] arr1, int[] arr2, int k, int left1, int right1, int left2, int right2){		
		while(true){
			if(right2==left2) return arr1[left1+k];
			if(right1==left1) return arr2[left2+k];

			int i=(right1-left1)/2;
			int j=(right2-left2)/2;
			if(i+j<k){
				if(arr1[i]>arr2[j]){
					left2+=j+1;
					k-=(j+1);
				}else{
					left1+=i+1;
					k-=(i+1);
				}
			}else{
				if(arr1[i]>arr2[j]){
					//ignore second half of a
					right1=left1+i;
				}else{
					//ignore second half of b
					right2=left2+j;
				}
			}	
		}
	}
	
	/**
	 * Not Working!
	 * @return
	 */
	public int findKsmallest(int[] arr1, int[] arr2, int l1, int r1, int l2, int r2, int k){
		int len1=r1-l1;
		int len2=r2-l2;
//		int i=l1+(r1-l1)/2;
		int i=(int) ((double) len1/(len1+len2) * (k-1));
		int j=k-i-1;
		
		int _ai=(i==0)?Integer.MIN_VALUE:arr1[i-1];
		int ai=(i==arr1.length-1)?Integer.MAX_VALUE:arr1[i];
		int _bj=(j==0)?Integer.MIN_VALUE:arr2[j-1];
		int bj=(j==arr2.length-1)?Integer.MAX_VALUE:arr2[j];
		
		if(ai<bj && ai > _bj) return ai;
		if(bj<ai && bj>_ai) return bj;
		
		if(ai>bj){
			// eliminate Ai and above
			// and Bj and below
			// adjust k
			return findKsmallest(arr1, arr2, l1, i, j+1, r2, k-(j+1));
		}else{
			// eliminate Ai and below
			// and Bj and above
			// adjust k
			return findKsmallest(arr1, arr2, i+1, r1, l2, j, k-(i+1));
		}
	}
	

	/**
	 * http://algorithmsandme.blogspot.in/2014/12/fins-kth-smallest-element-in-two-sorted.html
	 *
	 * @return
	 */
	public int findKSmallestNumber(int a[], int b[], int size_a, int size_b, int k){		
		return _findKSmallestNumber(a, b, 0, size_a, 0, size_b, k);
	}
	
	private int _findKSmallestNumber(int a[], int b[], int l1, int r1, int l2, int r2, int k){
        //array a should be small
		if( (r2-l2) < (r1-l1)) return _findKSmallestNumber(b, a, l2, r2, l1, r1, k);
		if((r1-l1)==0 && (r2-l2)>0)
			return b[k-1];
		if(k==1) return Math.min(a[l1], b[l2]);
		//prevent array index out of bounds
		int i=Math.min(r1-l1,k/2);
		int j=Math.min(r2-l2,k/2);
		//disard upper half of b, else discard upper half of a. Adjust k accordingly	
		if(a[l1+i-1]>b[l2+j-1]) return _findKSmallestNumber(a, b, l1, r1, l2+j, r2, k-j);
		else return _findKSmallestNumber(a, b, l1+i, r1, l2, r2, k-i);
	}
	
	public void decimalToBinary(double x){
		int LIMIT=500,count=0;
		StringBuilder sb = new StringBuilder();
		while(count++<LIMIT){
			if(x==1) break;
			double _x=x*2;
			String[] split = Double.toString(_x).split("\\.");
			sb.append(split[0]);
			if(_x>1)
				x = _x-1;
			else
				x=_x;
		}
		System.out.println("."+sb.toString());
	}
	
	/**
	 * Insert spaces between valid words in a 
	 * sentence.
	 * @param word
	 * @param dict
	 */
	public void breakWordBacktrack(String word, Set<String> dict){
		System.out.println(_breakWord(word, dict));
	}
	
	private String _breakWord(String word, Set<String> dict) {
		if (dict.contains(word))
			return word;
		for (int i = 1; i < word.length(); i++) {
			String prefix = word.substring(0, i);
			if (dict.contains(prefix)) {
				String suffix = _breakWord(word.substring(i), dict);
				if (suffix!=null) 
					return prefix+" "+suffix;
				}
			}
		return null;
	}

	public String breakWordDP(String word, Set<String> dict, Map<String,String> memo){
//		boolean[] memo = new boolean[word.length()];
		if (dict.contains(word))
			return word;
		if (memo.containsKey(word))
			return memo.get(word);
		for (int i = 1; i < word.length(); i++) {
			String prefix = word.substring(0, i);
			if (dict.contains(prefix)) {
				String suffix = _breakWord(word.substring(i), dict);
				if (suffix!=null){ 
					memo.put(word, prefix+" "+suffix);
					return prefix+" "+suffix;
				}
			}
			}
		memo.put(word, null);
		return null;
	}
	
	/**
	 * 9.6 CTCI
	 * @param array
	 */
	public int getMagicIndex(int[] array, int low, int high){
		if(low<high || low > 0 || high < array.length)
			return -1;
		
		int mid=low+(high-low)/2;
		if(array[mid]==mid)
			return mid;
		
		int left = getMagicIndex(array, low, Math.min(mid-1, array[mid]));
		if (left!=-1)
			return left;
		
		int right = getMagicIndex(array, low, Math.max(mid+1, array[mid]));
		if (right!=-1)
			return right;
		
		return -1;
	}
	
	/**
	 * Generate Valid pairs of parenthesis
	 * @param n
	 * @return
	 */
	public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        if (n<=0) return null;
        if(n==1){
            list.add("()");
            return list;
        }
        _genParen(list,n,0,"");
        return list;
    }
    
    private void _genParen(List<String> list, int openParen, int closedParen, String str){
        if(openParen==0 && closedParen==0){
            list.add(str);
            System.out.println(str);
        }
        if(openParen>0)
            _genParen(list,openParen-1,closedParen+1,str+"(");
        if(closedParen>0)
            _genParen(list,openParen,closedParen-1,str+")");
    }
	
    /**
     * 9.8 CTCI
     * @param n
     */
    public int makeChange(int n, int[] denominations){
    	return makeChange(n, denominations,denominations.length);
    	
    }
    
    public int makeChange(int n,int[] denominations, int curDenom){
    	if(n<0) return 0;
    	if(n==0) return 1;
    	if(curDenom<=0) return 0;
    	//including current denomination
    	int count1 = makeChange(n-denominations[curDenom-1], denominations, curDenom);
    	//excluding current denomination
    	int count2 = makeChange(n, denominations, curDenom-1);
    	return count1+count2;
    }
    
    /**
     * Exponentiation by squaring.
     * Does not handle base cases, eg: 1, -1 etc. Trivial.
     * @param base
     * @param exp
     * @return
     */
    public double pow(double base, int exp) {
    	int result = 1;
        while (exp != 0)
        {
        	//is exp odd?
            if ((exp & 1) == 1)
                result *= base;
            exp >>= 1;
            base *= base;
        }
        return result;
    }
    
    /**
     * NQueens Problem
     */
    int[][] grid;
    int[] queenX;
    int res=0;

    public int totalNQueens(int n) {
//    	List<String[]> fin = new ArrayList<String[]>();
        grid = new int[n][n];
        queenX = new int[n];;
        _solveNQueens(n, 0);
        return res;
    }
    
     private void _solveNQueens(int n, int col){
        if (col==n){
            //we have a solution
            res++;
        } else{
            //Find other solutions, if any
            //for all positions in a row, starting from bottom
            for(int i=0;i<n;i++){
                if(canPlaceQueenHere(i,col)){
                    //safe to place queen here
                    grid[i][col]=1;
                    queenX[col]=i;
                    _solveNQueens(n,col+1);
                    //backtrack
                    grid[i][col]=0;
                    queenX[col]=0;
                }
            }
        }
    }
    
     private boolean canPlaceQueenHere(int row, int col){
         for(int i=0;i<col;i++){
             //same row?
             if (grid[row][i]==1)
              return false;
              
             //same diagonal
             int rowX=queenX[i];
             if (Math.abs(col-i)==Math.abs(row-rowX))
                  return false;
         }
         return true;
      }
    
     
     /**
      * Can a number be reperesented as a^b
      * @param n
      */
     public void getPerfectPower(long  n){
    	 int expLimit = BinaryLogarithm.binaryLogarithm(n) + 1;
    	 Integer[] candidates = new Integer[expLimit-2];
    	 for(int i=2;i<expLimit;i++)
    		 candidates[i-2]=i;
    	 
    	 for(int i=0;i<candidates.length;i++){
    		 Integer base=candidates[i];
    		 for(int j=2;j<expLimit;j++){
    			 if (pow(base, j)==n){
    				 System.out.printf("Found: %d ^ %d\n",base,j);
    				 return;
    			 }
    		 }
    	 }
    	 System.out.println("Perfect Number not found!");
     }
     
     
     /**
 	 * print combinations of numbers that add upto target
 	 * @param arr
 	 * @param target
 	 */
 	public void setAddUpToN(int[] arr, int target){
// 		List<Integer> list = new ArrayList<Integer>();
 		System.out.println(_setAddUptoN(arr, target, arr.length-1));
 	}
 	
 	private boolean _setAddUptoN(int[]arr, int target, int m){
 		if(m<0) return false;
 		if (target<0) return false;
 		if(target==0){
 			//we have a solution
 			return true;
 		}else{
 			//including element at m
 			return _setAddUptoN(arr, target - arr[m], m) ||
 			//not including element at m
 			_setAddUptoN(arr, target, m-1);
 		}
 	}
 	
 	/**
 	 * 
 	 * @param digits
 	 * @return
 	 */
 	Map<Integer,String> t9Map;
	public List<String> letterCombinations(String digits) {
		t9Map = new HashMap<Integer, String>();
		t9Map.put(2, "abc");
		t9Map.put(3, "def");
		t9Map.put(4, "ghi");
		t9Map.put(5, "jkl");
		t9Map.put(6, "mno");
		t9Map.put(7, "pqrs");
		t9Map.put(8, "tuv");
		t9Map.put(9, "wxyz");
		List<String> res = new ArrayList<String>();
		_letterCombinations(digits,0,new StringBuilder(),res);
		return res;
	}

	private void _letterCombinations(String digits,int pos, StringBuilder prefix, List<String> res) {
		//base case
		if(pos>=digits.length()){
			//we have a possible combination
			res.add(prefix.toString());
			return;
		}else{
			int n = Integer.parseInt(Character.toString(digits.charAt(pos)));
			String candidates = t9Map.get(n);
			for(int i=0;i<candidates.length();i++){
				prefix.append(candidates.charAt(i));
				_letterCombinations(digits, pos+1, prefix, res);
				prefix.deleteCharAt(prefix.length()-1);
			}
		}
	}

	public int[] knuthShuffle(int[] array){
		int n = array.length;
		if(n<=1) return array;
		for(int i=0;i<n;i++){
			int _randIdx = i+(int)(Math.random()*(n-i));
			int _tmp = array[_randIdx];
			array[_randIdx]=array[i];
			array[i]=_tmp;
		}
		return array;
	}
	
	 public String longestPalindrome(String s) {

		 int n=s.length();
		   	if(n==1) return s;

		 	int max=1;
		     String longest=s.substring(0,1);

		 	for(int i=0;i<n-1;i++){
		 		String str = getLongestPalindrome(s,i,i);
		 		if(str.length()>max){
		 			max=str.length();
		 			longest=str;
		 		}
		         String str2 = getLongestPalindrome(s, i, i+1);
		         if(str2.length()>max){
		         	max=str2.length();
		         	longest=str2;
		         }		
		 	}
		 	return longest;
		  }

		 private String getLongestPalindrome(String str, int l, int r){	
		 	while(l>=0 && r<str.length() && str.charAt(l)==str.charAt(r)){
		 		l--;r++;
		 	}
		 	assert(l+1>=0);
		 	assert(r-1<str.length());
		 		return str.substring(l+1,r);
		 }
		 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CTCI c = new CTCI();
		System.out.println(c.longestPalindrome("abcdadb"));
		int[] shuffArray = new int[]{1,2,3,4,5,6,7,8,9};
		c.knuthShuffle(shuffArray);
		for (int i:shuffArray) System.out.print(i+",");
		System.out.println();
		System.out.println(c.letterCombinations("428728"));
//		c.printAllSubsets("abc");
		c.setAddUpToN(new int[]{2,5,10,25}, 1);
		System.out.println(c.makeChange(11, new int[]{1,5,10,25}));
		c.getPerfectPower(125);
		System.out.println(c.totalNQueens(3));
		System.out.println(c.pow(4, 6));
		c.compressString("aabcccccaaa");
		c.twoSum(new long[] { -5, 3, 2, 5, 9, 1, 6, 8 }, -10);
//		 System.out.println("Paths="+getNumPaths(0, 0, 3, 3));
		// c.generateSubsets(new int[]{1,2,3});

		c.numPathsDP(0, 0, 6, 6,new int[6][6]);
		c.getNumPaths(new int[][]{}, 6, 6);
		System.out.printf("Paths=%d\n",c.path);

//		int[][] grid = {
//						{0,0,0},
//						{0,1,0},
//						{0,0,0}
//						};
		
//		int[][] grid = {
//				{0,0},
//				{1,1},
//				{0,0}
//				};
		
//		System.out.println("With Obstacles : "+c.uniquePathsWithObstacles(grid));
		
		// c.nextPermutation(new char[]{'1','2','6','5','4','3'});
		// c.nextPermutation(new char[]{'1','2','3'});

//		c.divideIntoTwo(new int[] { 10, 20, 30, 5, 40, 50, 40, 15 });
		// 3,1,1,2,2,1
//		c.divideIntoTwo(new int[] { 3, 1, 1, 2, 2, 1 });

		// c.nonRepeatingCharFromStream();

//		c.getLongestConsecutiveChar("this is a test sentence");
		
//		c.shortestDistinctSubstring("abbcbcba");
//		c.shortestDistinctSubstring("caaababc");
		
//		c.findOddAmongDuplicates(new int[]{3,1,4,2,1,9,4,2,3});
//				c.shortestDistinctSubstring("caaababc");
		
//		System.out.println("Median of two arrays :=  "+c.medianOfTwoArrays(new int[]{1,2,4,8,9,10}, new int[]{3,5,6,7}, 0,5,0,3));
		
		
//		System.out.println(c.getMinWindow("acbbaca", "aba"));
//		System.out.println(c.getMinWindow("thisisateststring", "tist"));
//		System.out.println(c.getMinWindow("gaurav", "ava"));
//		for(int i=1;i<=10;i++)
//			System.out.printf("%d smallest = %d\n",i,c.findKSmallestNumber(new int[]{1,2,4,8,9,10}, new int[]{3,5,6,7}, 6, 4, i));
	
		c.decimalToBinary(.8125);
		
		Set<String> dict = new HashSet<String>();
//		dict.add("dog");
//		dict.add("arkdo");
//		dict.add("ant");
//		dict.add("antark");
//		dict.add("an");
//		dict.add("do");
		dict.add("a");
		dict.add("aa");
		dict.add("aaa");
		dict.add("aaaa");
		dict.add("aaaaa");
		dict.add("aaaaaa");
		dict.add("aaaaaaa");
		dict.add("aaaaaaaa");
		dict.add("aaaaaaaaa");
		dict.add("aaaaaaaaaa");
		dict.add("aaaaaaaaaaa");
		dict.add("aaaaaaaaaaaa");
		dict.add("aaaaaaaaaaaa");
		dict.add("aaaaaaaaaaaa");
//		c.breakWordBacktrack("dogantarkdo", dict);
		
		long start = System.currentTimeMillis();
		c.breakWordBacktrack("aaaaaaaaaaaab", dict);
		System.out.println("Backtracking word = "+(System.currentTimeMillis()-start));
		
		Map<String,String> memo = new HashMap<String, String>();
		
		start = System.currentTimeMillis();
		System.out.println(c.breakWordDP("aaaaaaaaaaaab", dict, memo));
		System.out.println("DP word = "+(System.currentTimeMillis()-start));
//		
//		c.recursivePermutation("", "ABC");
		
//		System.out.println(c.generateParenthesis(3));
	}
}
