/**
 * 
 */
package edu.gks.random;

/**
 * @author gaurav
 *
 */
public class KMP {

	String pattern_;
	int[] prefix_;
	
	public KMP(String pattern){
	pattern_=pattern;
	prefix_=new int[pattern.length()];
	_prefixTable();
	}
	
	private void _prefixTable(){
		int j=0;
		for(int i=1;i<pattern_.length();i++){
			while(j>0 && pattern_.charAt(j)!=pattern_.charAt(i))
				j=prefix_[j-1];
			
			if(pattern_.charAt(j)==pattern_.charAt(i))
				j++;
			prefix_[i]=j;
		}
	}
	
	public int patternSearch(String text){
		int len=text.length();
		int p=0,i;
		for(i=0;i<len;i++){
			if(text.charAt(i)==pattern_.charAt(p)){
				p++;
			}else if (p>0){
				p=p-prefix_[p-1];
			}	
			if(p==pattern_.length()) break;
		}
		return i-pattern_.length()+1;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KMP kmp = new KMP("ababacabca");
		System.out.println(kmp.patternSearch("abcdab abcdabcdabde"));
	}

}
