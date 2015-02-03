/**
 * 
 */
package edu.gks.random;


/**
 * @author gaurav
 *
 */
public class BitsetOps {

	/**
	 * Compute parity of a 64-bit non negative number
	 * Parity is 1 if number of 1s is odd, else 0.
	 * @param n
	 */
	public static void getParity(long n){
		int parity=0;
		while(n>0){
			// repeatedly AND'ing with (n-1) clears one set bit each time, until
			// you finally get 0.
			n&=(n-1);
			parity^=1;
		}
		System.out.printf("Parity is %d\n",parity);
	}
	
	public static int reverse(int val) {
		// Successively swap alternating bit groups.
		System.out.println(Integer.toBinaryString(val));
		val = ((val >> 1) & 0x55555555) + ((val << 1) & ~0x55555555);
		System.out.println(Integer.toBinaryString(val));
		val = ((val >> 2) & 0x33333333) + ((val << 2) & ~0x33333333);
		System.out.println(Integer.toBinaryString(val));
		val = ((val >> 4) & 0x0f0f0f0f) + ((val << 4) & ~0x0f0f0f0f);
		System.out.println(Integer.toBinaryString(val));
		val = ((val >> 8) & 0x00ff00ff) + ((val << 8) & ~0x00ff00ff);
		System.out.println(Integer.toBinaryString(val));
		val= ((val >> 16) & 0x0000ffff) + ((val << 16) & ~0x0000ffff);
		System.out.println(Integer.toBinaryString(val));
		return val;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		getParity(127);
		System.out.println(reverse(204));
		System.out.println(Integer.reverse(204));
	}

}