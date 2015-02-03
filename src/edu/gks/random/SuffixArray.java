/**
 * 
 */
package edu.gks.random;

/**
 * @author gaurav
 *
 */
public class SuffixArray {

	char[] text;
	int[] suffixArray;
	int len;
	int[] lcp;
	
	public SuffixArray(String text){
		len=text.length();
		text=text+'\0';
		this.text=text.toCharArray();
		suffixArray=new int[len];
		lcp=new int[len];
		//initialize suffix array
		for(int i=0;i<len;i++)
			suffixArray[i]=i;
		
		_threeWayStringSort(0, len-1, 0);
		_fillLcpArray();
	}
	
	private void _threeWayStringSort(int low, int high, int n) {
		if(low>high) return;
		int lt=low,gt=high,i=low+1;
//		int _tmp=text.charAt(suffixArray[low]+n);
		int _tmp=text[(suffixArray[low]+n)];
		while(i<=gt){
//			int _tmp2=text.charAt(suffixArray[i]+n);
			int _tmp2=text[(suffixArray[i]+n)];
			if(_tmp2<_tmp){
				swap(lt,i); i++;lt++;
			}else if(_tmp2>_tmp){
				swap(gt, i); gt--;
			}else i++;
		}
		_threeWayStringSort(low, lt-1, n);
		if(_tmp > 0) _threeWayStringSort(lt, gt, n+1);
		_threeWayStringSort(gt+1, high, n);
	}
	
	private void swap(int lt, int i) {
		int _tmp=suffixArray[lt];
		suffixArray[lt]=suffixArray[i];
		suffixArray[i]=_tmp;
	}
	
	private void _fillLcpArray(){
		String _tmp=new String(text);
		for(int i=1;i<suffixArray.length;i++){
			int _lcp=0;
			String _str1=_tmp.substring(suffixArray[i-1]);
			String _str2=_tmp.substring(suffixArray[i]);
			int x=Math.min(_str1.length(), _str2.length());
			int j=-1;
			while(++j<x &&_str1.charAt(j)==_str2.charAt(j)) _lcp++;
			lcp[i]=_lcp;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String text="Ask not what your country can do for you ask what you can do for your country";
		SuffixArray sa = new SuffixArray(text);
		for(int i:sa.suffixArray)
			System.out.println(text.substring(i));
	
		int max=0;
		int index=-1;
		for(int i=0;i<sa.lcp.length;i++){
			if(sa.lcp[i]>max){
				max=sa.lcp[i];
				index=i;
			}
		}
		System.out.printf("LCP= %d, [%s,%s]",max,text.substring(sa.suffixArray[index-1]),text.substring(sa.suffixArray[index]));
	}
}
