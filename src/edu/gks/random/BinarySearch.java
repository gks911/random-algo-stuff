/**
 * 
 */
package edu.gks.random;


/**
 * @author gaurav
 *
 */
public class BinarySearch<T extends Comparable<T>> {

	T[] haystack;
	public BinarySearch(T[] haystack){
		this.haystack = haystack;
	}
	/**
	 * Sorted array of objects as input
	 * @param data
	 * @return position of needle in haystack
	 */
	public int binarySearch(T needle){
		if (haystack.length<=0) return -1;
		int n=haystack.length;
		int start=0,end=n-1;
		while(start<=end){
			// Avoid integer overflow here
			// Wrong: (start + end) / 2
			//int mid=start+((end-start)/2); OR
			int mid=(start+end) >>> 1;
			if(needle.compareTo(haystack[mid]) == -1)
				end=mid-1;
			else if (needle.compareTo(haystack[mid]) == 1)
				start=mid+1;
			else return mid;
		}
		return -1;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Integer[] arr = {1,2,4,6,8};
		BinarySearch<Integer> bs = new BinarySearch<Integer>(arr);
		System.out.println(bs.binarySearch(9));
	}
}
