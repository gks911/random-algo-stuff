/**
 * 
 */
package edu.gks.random;


/**
 * @author gaurav
 * A min-heap
 */
public class PriorityQueue<T extends Comparable<T>> {

	T[] heap;
	int size;
	int current;
	
	@SuppressWarnings("unchecked")
	public PriorityQueue(int size){
		this.size=size;
		current=0;
		heap = (T[]) new Object[size];
	}

	
	public void insert(T obj){
		if(current<size){
			heap[current] =  obj;
			swim(current++);
		}
		else
			System.err.println("Heap full!");
		
	}
	
	public void sink(int index){
		while(index<size/2){
			int j = min(2*index+1, 2*index+2);
			if(heap[index].compareTo(heap[j])==1){
				swap(index, 2*index+1);			
			}else break;
			index=j;
		}
	}
	
	public void swim(int index){
		//index is probably of the last element added
		while(index>=0 && (heap[index].compareTo(heap[index/2])==-1)){
			swap(index, index/2);
			index=index/2;
		}
	}
	
	private void swap(int x, int y){
		T _tmp = heap[x];
		heap[x] = heap[y];
		heap[y] = _tmp;
	}
	
	private int min(int x, int y){
		if (heap[x].compareTo(heap[y]) == -1)
			return x;
		return y;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(10);
		for (Integer i=10;i<0;i--)
			pq.insert(i);
		
		System.out.println();

	}

}
