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
	public static void compressString(String text){
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
	
	public static void twoSum(long[] array, long sum){
		Set<Long> set = new HashSet<Long>();
		for(int i=0;i<array.length;i++){
			if(set.contains(sum-array[i]))
					System.out.printf("[%d,%d]\n",array[i],sum-array[i]);
			else
				set.add(array[i]);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		compressString("aabcccccaaa");
		twoSum(new long[]{-5,3,2,5,9,1,6,8}, -10);
	}

}
