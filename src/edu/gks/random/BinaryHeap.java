/**
 * 
 */
package edu.gks.random;

/**
 * @author gaurav
 * Min Heap
 */
public class BinaryHeap {

	int[] heap;
	int N;
	
	public BinaryHeap(int[] n){
		heap = n.clone();
		N=n.length;
		for(int i=N/2-1;i>=0;i--){
			pushDown(i);
		}
	}
	
	public int extractMin(){
		if(N<=0) return 0;
		int _min=heap[0];
		swap(0,--N);
		heap[N]=0;
		pushDown(0);
		return _min;
	}
	
	public void add(int val){
		heap[N]=val;
		swimUp(N++);
	}
	
	private void pushDown(int k) {
			while(N>2*k+1){
				int _lesser=2*k+1;
				if(_lesser+1<N && heap[_lesser]>heap[_lesser+1])
					_lesser+=1;
				if(heap[_lesser] >= heap[k])
					break;
				swap(_lesser,k);
				k=_lesser;
			}
	}
	
	private void swimUp(int k){
		while(k>0 && heap[(k-1)/2] > heap[k]){
			swap((k-1)/2,k);
			k=(k-1)/2;
		}
	}

	private void swap(int i, int j){
		int _tmp = heap[i];
		heap[i] = heap[j];
		heap[j] = _tmp;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = {2,4,3,1,6,7,8,9,1,7};
		BinaryHeap bh = new BinaryHeap(arr);
		System.out.println(bh.extractMin());
		System.out.println(bh.extractMin());
		System.out.println(bh.extractMin());
		System.out.println(bh.extractMin());
		System.out.println(bh.extractMin());
		System.out.println(bh.extractMin());
		System.out.println(bh.extractMin());
		System.out.println(bh.extractMin());
		System.out.println(bh.extractMin());
		System.out.println(bh.extractMin());
	}
}
