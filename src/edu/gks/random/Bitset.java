/**
 * 
 */
package edu.gks.random;


/**
 * @author gaurav
 *
 */
public class Bitset {

	byte[] n;
	
	public Bitset(int n){
		this.n=new byte[((n-1)>>3)+1];
	}
	
	public void set(int pos){
		int index=(pos-1)>>3;
		int bitPos=pos%7;
		n[index]|=1<<bitPos;
	}
	
	public int get(int pos){
		int index=(pos-1)>>3;
		int bitPos=pos%7;
		return ((n[index]&(1<<bitPos))!=0)?1:0;
	}
	
//	public int nextSetBit(int start){
//		int i=start;
//	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Bitset bs = new Bitset(20);
		System.out.println(bs.get(12));
		bs.set(12);
		for(int i=1;i<20;i++)
		System.out.println(bs.get(i));
	}

}
