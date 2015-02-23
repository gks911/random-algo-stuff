/**
 * 
 */
package edu.gks.random;

/**
 * @author gaurav
 * http://community.topcoder.com/stat?c=problem_statement&pm=2829&rd=5072
 */
public class QuickSum {

	int min=Integer.MAX_VALUE;
	
	public int minSums(String numbers, int sum){
		minSumRecur(numbers,"",sum);
		if(min!=Integer.MAX_VALUE)
			return min;
		return -1;
	}
	
	private void minSumRecur(String number,String prefix, int sum){
		if(number.isEmpty()&&sum==evalExp(prefix) && (prefix.split("\\+").length-1) < min)
			min=prefix.split("\\+").length-1;
		else{
			for(int i=0;i<number.length();i++){
				String p=prefix+number.substring(0,i+1);
				String r=number.substring(i+1);
				if(r.isEmpty())
					minSumRecur(r,p, sum);
				else
					minSumRecur(r, p+"+", sum);
			}
			
		}
	}
	
	public int evalExp(String exp){
		String[] tokens = exp.split("\\+");
		int sum=0;
		for(String s:tokens)
			//prevent overflows (dirty? :{)
			sum+=Long.parseLong(s);
		return sum;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		QuickSum s = new QuickSum();
		long start=System.currentTimeMillis();
		System.out.println(s.minSums("9230560001", 71));
		long end=System.currentTimeMillis();
		System.out.println("Time taken = "+(end-start));
	}

}
