/**
 * 
 */
package edu.gks.random;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
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

	int path=0;
	
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
	
	 public void getNumPaths(int[][] grid, int m, int n){
		    //x-y is current location
		    //Do we need additional grid? Probably to reconstruct the path
		    _numPathsBacktrack(0, 0, m, n, grid);
		  }
		  
		  
		  private void _numPathsBacktrack(int x, int y, int m, int n, int[][] grid){
			  if(x>m-1|| y>n-1) return;
			  if(x==m-1 && y==n-1){
		      //we have a solution
		      path++;
		      return;
		    }else{
		      //generate candidates, should be at a class level
		      int[] _x=new int[]{0,1};
		      int[] _y=new int[]{1,0};
		      for(int i=0;i<2;i++){
		    	  //move
		        x+=_x[i];
		        y+=_y[i];
		        _numPathsBacktrack(x,y,m,n,grid);
		        //unmove
		        x-=_x[i];
		        y-=_y[i];
		      }
		    }
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
	 * 9. Given an unsorted, even-numbered array of integers, divide the array into two lists of the equal sizes such that their total is as close as possible
	 * @param arr
	 */
	public void  divideIntoTwo(int[] arr){
		//handle for two entries
		Arrays.sort(arr);
		int n=arr.length;
		int[] arr1=new int[n/2];
		int[] arr2=new int[n/2];
		//split the middle two elements in one array each?
		//otherwise, put first and last element in arr1, 
		//Similarly put first+1 last-1 in arr2 etc.
		boolean divideOdd=true;
		if((n/2)%2==0)
			divideOdd=false;
			
		int _idx1=0,_idx2=0,i=0,j=n-1,totalIter=n/2,iter=0;
		
		if(divideOdd) totalIter--;
		while(i<j && iter++<totalIter){
			if(i%2==0){
				arr1[_idx1++]=arr[i++];
				arr1[_idx1++]=arr[j--];
			}else{
				arr2[_idx2++]=arr[i++];
				arr2[_idx2++]=arr[j--];
			}
		}
		
		if(divideOdd){
			arr1[_idx1]=arr[i];
			arr2[_idx2]=arr[j];
		}
		
		int sum1=0,sum2=0;
		for(int k=0;k<n/2;k++){
			sum1+=arr1[k];
			sum2+=arr2[k];
		}
		System.out.printf("Sum 1= %d\tSum 2=%d\n",sum1,sum2);
	}
	
	/**
	 * Find first non repeating char from stream
	 */
	private class Node{
		char c;
		public Node(char c){ this.c=c;};
	}
	public void nonRepeatingCharFromStream(){
		Queue<Node> q = new LinkedBlockingDeque<Node>();
		Map<Character,Node> map = new HashMap<Character,Node>();
		Scanner scanner = new Scanner(System.in);
		while(true){
			char c = scanner.next().charAt(0);
			if (c==' ') break;
			if(map.containsKey(c)){
				q.remove(map.get(c));
				map.remove(c);
			}else{
				Node x = new Node(c);
				q.add(x);
				map.put(c, x);
			}
			System.out.printf("First non repeating char: %c\n",q.peek().c);
		}
		scanner.close();
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
//		c.nextPermutation(new char[]{'1','2','3'});
		
//		c.getNumPaths(new int[][]{}, 6, 6);
//		System.out.printf("Paths=%d",c.path);
		
		c.divideIntoTwo(new int[]{10, 20 , 30 , 5 , 40 , 50 , 40 , 15});
		//3,1,1,2,2,1
		c.divideIntoTwo(new int[]{3,1,1,2,2,1});
		
		c.nonRepeatingCharFromStream();
	}

}
