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
public class CTCI {

	/**
	 * 1.5
	 * @param text
	 */
	public void compressString(String text){
		StringBuilder sb = new StringBuilder();
		int i=0;
		while(i<text.length()){
			char c = text.charAt(i);
			int count=0;
			while(i<text.length()&&c==text.charAt(i)){
				i++;count++;
			}
			sb.append(c).append(count);		
		}
		System.out.println(text+" = "+sb.toString());
	}
	
	public void twoSum(long[] array, long sum){
		Set<Long> set = new HashSet<Long>();
		for(int i=0;i<array.length;i++){
			if(set.contains(sum-array[i]))
					System.out.printf("[%d,%d]\n",array[i],sum-array[i]);
			else
				set.add(array[i]);
		}
	}
	
	public int getNumPaths(int x, int y, int m, int n){
//		if (x>m || y>n) return 0;
//		else 
//			return 1+getNumPaths(x, y+1, m, n) +
//			1+getNumPaths(x, y+1, m, n);
		return 0;
	}
	
	public void generateSubsets(int[] input){
		assert(input.length>1);
		boolean[] candidates=new boolean[input.length];
		_subsetBacktrack(input,candidates,0);
	}
	
	private  void _subsetBacktrack(int[] input, boolean[] a, int k){
		if(k==input.length){
			//we got a solution
			System.out.print("{ ");
			for(int i=0;i<input.length;i++)
				if(a[i]==true)
					System.out.print(input[i]+" ");
			System.out.print(" }\n");
			return;
		}else{
			boolean [] candidates=new boolean[]{true,false};
			for(int i=0;i<candidates.length;i++){
				a[k]=candidates[i];
				_subsetBacktrack(input, a, k+1);
			}
		}
	}
	
	/**
	 * Algo: (from Wikipedia)
     * 1. Find the largest index k such that a[k] < a[k + 1]. If no such index exists, the permutation is the last permutation.
     * 2. Find the largest index l greater than k such that a[k] < a[l].
     *	3. Swap the value of a[k] with that of a[l].
     *	4. Reverse the sequence from a[k + 1] up to and including the final element a[n].
	 * @param arr
	 */
	public void nextPermutation(char[] arr){
		int n = arr.length;
		int k=n-2;
		while(k>=0){
			if(arr[k] < arr[k+1])
				break;
			k--;
		}
		
		if(k==-1) {
			System.out.println("No such perm exists!");
			return;
		}
		
		int l=n-1;
		while(l>k){
			if(arr[l]>arr[k])
				break;
			l--;
		}

		//swap k and l
		_swap(arr,k,l);
		//reverse elements b/w k+1 to n-1
		_reverse(arr, k+1, n-1);
		
		for(int i=0;i<n;i++)
			System.out.print(arr[i]);
		System.out.println();
	}
	
	private void _reverse( char[] arr, int from, int to){
		int i=from,j=to;
		while(i<j){
			_swap(arr,i++,j--);
		}
	}
	
	private void _swap(char[] arr, int i, int j){
		char _tmp=arr[i];
		arr[i]=arr[j];
		arr[j]=_tmp;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CTCI c = new CTCI();
		c.compressString("aabcccccaaa");
		c.twoSum(new long[]{-5,3,2,5,9,1,6,8}, -10);
//		System.out.println("Paths="+getNumPaths(0, 0, 3, 3));
//		c.generateSubsets(new int[]{1,2,3});
		
//		c.nextPermutation(new char[]{'1','2','6','5','4','3'});
		c.nextPermutation(new char[]{'1','2','3'});
	}

}
