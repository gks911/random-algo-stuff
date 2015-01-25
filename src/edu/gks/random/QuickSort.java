package edu.gks.random;

import java.util.Random;

/**
 * @author gaurav
 *
 */
public class QuickSort<T extends Comparable<T>> {

	// When to resort to insertion sort for smaller arrays
	private static final int THRESHOLD =0;

	public void sort(T[] array) {
		// Maybe shuffle here to make sure we don't hit the worst case
		// complexity?
		if (array.length > 1)
			_sort(array, 0, array.length-1);
	}
	
	public void _sort(T[] array,int low,int high){
		if(low>=high) return;
		if(high-low<=THRESHOLD){
			insertionSort(array,low,high);
			return;
		}
		int pivot=_partition(array,low,high);
		_sort(array, low, pivot-1);
		_sort(array, pivot+1, high);
	}
	
	private int _partition(T[] array, int low, int high) {
		int i=low,j=high+1;
		T _pivot=array[low];
		while(true){
			while((array[++i].compareTo(_pivot)==-1) && i<high);
			while((array[--j].compareTo(_pivot)==1) && j>low);
			if(i>=j) break;
			swap(array,i,j);
		}
		swap(array, low, j);
		return j;
	}

	private void swap(T[] arr,int i, int j){
		T _tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = _tmp;
	}
	
	public void insertionSort(T[] arr,int low,int high){
		for(int i=low;i<high;i++){
			T _tmp=arr[i];
			int j=i;
			while( j>low && _tmp.compareTo(arr[j-1])==-1){
				arr[j]=arr[j-1];
				j--;
			}
			arr[j]=_tmp;
		}
	}
	
	public void threeWayQuickSort(T[] arr){
		if(arr.length>1)
			_threeWayQuickSort(arr, 0, arr.length-1);
	}
	
	private void _threeWayQuickSort(T[] arr,int low, int high){
		if(low>=high) return;
		int lt=low,i=low+1,gt=high;
		while(i<=gt){
			if (arr[i].compareTo(arr[lt]) == -1) {
				swap(arr, lt, i);lt++;	i++;
			}else if(arr[i].compareTo(arr[lt])==1){
				swap(arr, i, gt);gt--;
			}else i++;
		}
		_threeWayQuickSort(arr, low, lt-1);
		_threeWayQuickSort(arr, gt+1, high);
	}
	
	/**
	 * Refer Sedgewick.
	 * Reference: http://ptgmedia.pearsoncmg.com/images/exc01_9780133799118/elementLinks/05fig17.jpg
	 * @param array
	 */
	public void threeWayStringSort(String[] array){
		if(array.length>1)
			_threeWayStringSort(array,0,array.length-1,0);
	}
	
	private void _threeWayStringSort(String[] array, int low, int high, int n) {
		if(low>=high) return;
		int lt=low,gt=high,i=low+1;
		int _tmp=charAt(array[low],n);
		while(i<=gt){
			int _tmp2=charAt(array[i],n);
//			if(charAt(array[i],n)<charAt(array[low],n)) {
			if(_tmp2<_tmp){
				swapStr(array,lt,i); i++;lt++;
//			}else if (charAt(array[i],n)>charAt(array[low],n)){
			}else if(_tmp2>_tmp){
				swapStr(array, gt, i); gt--;
			}else i++;
		}
//		for(String s:array)
//			System.out.print(s+" ");
//		System.out.print("["+low+","+high+"],digit="+n+"\n");
		// a[low..lt-1] < v = a[lt..gt] < a[gt+1..high]. 
		_threeWayStringSort(array, low, lt-1, n);
		if(_tmp > 0) _threeWayStringSort(array, lt, gt, n+1);
		_threeWayStringSort(array, gt+1, high, n);
	}
	
	private int charAt(String str,int d){
		if(d==str.length()) return -1;
		return str.charAt(d);
	}

	private void swapStr(String[] array, int lt, int i) {
		String _tmp=array[lt];
		array[lt]=array[i];
		array[i]=_tmp;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		Integer[] arr={6,4,5,9,1,7,2,0,3,4,5,9,1,7,2,0,3};
		Integer[] arr=new Integer[10000];//{6,4,5,9,1,7,2,0,3,4,5,9,1,7,2,0,3};
		Random rand=new Random();
		for(int i=0;i<10000;i++)
			arr[i]=rand.nextInt(100000);
		QuickSort<Integer> qSort=new QuickSort<Integer>();
		long start=System.currentTimeMillis();
//		qSort.sort(arr);
//		qSort.threeWayQuickSort(arr);
//		qSort.insertionSort(arr, 0,arr.length);
		String[] strs={"the","quick","brown","fox","jumpes","jumped","over","the","lazy","dog","lame","lame","lame"};
//		String[] strs={"cde","abc","fgh","xyz","mno"};
		qSort.threeWayStringSort(strs);
		System.out.println("Time taken = "+(System.currentTimeMillis()-start)+"ms");
//		for (int x:arr)
//			System.out.print(x+" ");
		for(String s:strs)
			System.out.print(s+" ");
	}
}
