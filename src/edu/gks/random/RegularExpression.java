/**
 * 
 */
package edu.gks.random;

/**
 * @author gaurav
 *http://www.cs.princeton.edu/courses/archive/spr09/cos333/beautiful.html
 */
public class RegularExpression {

	
	public boolean isMatch(String s, String p) {
        return _isMatch(s,p,0,0);
    }
    
    private boolean _isMatch(String s, String p, int sPtr, int pPtr){
        //both exhausted
        if(sPtr>=s.length() && pPtr>=p.length())
            return true;
        if(sPtr>=s.length() && (pPtr+1<=p.length()-1 && p.charAt(pPtr+1)=='*'))
            return _isMatch(s,p,sPtr,pPtr+2);
        //one of the two strings exhausted,while other remains
        if(sPtr>=s.length()||pPtr>=p.length()) 
            return false;
        else{
            //is the next char '*'?
            if(pPtr+1 < p.length() && p.charAt(pPtr+1)=='*'){
                //backtracking
                char c = p.charAt(pPtr);
                while(sPtr<s.length() && (c=='.' || s.charAt(sPtr)==c)){
                    if(_isMatch(s,p,sPtr,pPtr+2))
                        return true;
                    sPtr++;
                }
                return _isMatch(s,p,sPtr,pPtr+2);
            }
            //pPtr+1 is not '*'
            else
                return ((s.charAt(sPtr)==p.charAt(pPtr) || p.charAt(pPtr)=='.') && _isMatch(s,p,sPtr+1,pPtr+1));
        }
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RegularExpression r = new RegularExpression();
//		System.out.println(r.isMatch("abcbcd", "a.*c.*d"));
		System.out.println(r.isMatch("aa", "a*"));
		
	}

}
