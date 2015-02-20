/**
 * 
 */
package edu.gks.random;

/**
 * @author gaurav
 *	http://community.topcoder.com/stat?c=problem_statement&pm=12300
 */
public class JumpFurther {

	/**
	 * greedy
	 * @param N
	 * @param badStep
	 * @return
	 */
	public int furthest(int N, int badStep){
		int cur=0;
		for(int i=1;i<=N;i++){
			cur=cur+i;
			if(cur==badStep) 
				cur=cur-1;
		}
		return cur;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JumpFurther j = new JumpFurther();
		System.out.println(j.furthest(1313, 5858));
	}

}
