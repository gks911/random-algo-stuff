/**
 * 
 */
package edu.gks.random;

import java.util.LinkedList;
import java.util.List;

/**
 * @author gaurav
 *
 */
public class BlockingQueue {
	private List<Integer> queue_;
	private int limit_;
	
	public BlockingQueue(int limit){
		queue_ = new LinkedList<Integer>();
		this.limit_=limit;
	}
	
	public synchronized void put(int data){
			try {
				while(queue_.size()==limit_)
					wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.printf("Thread = %d : Pushed data = %d, Queue size = %d\n",Thread.currentThread().getId(),data,queue_.size());
			queue_.add(data);
			notifyAll();
	}
	
	public synchronized int get(){
			try {
				while(queue_.size()==0)
					wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int data=queue_.remove(0);
			System.out.printf("Thread = %d : Got data = %d, Queue size = %d\n",Thread.currentThread().getId(),data,queue_.size());
			notifyAll();
			return data;
	}
	
	public int size(){
		return queue_.size();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}

}
