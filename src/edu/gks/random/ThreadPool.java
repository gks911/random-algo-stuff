/**
 * 
 */
package edu.gks.random;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author gaurav
 *
 */
public class ThreadPool {

	private BlockingQueue<Runnable> tasks;
	int nThreads;
	private int nTasks;
	private List<MyThread> threads;
	private boolean shutdown=false;
	
	private class MyThread extends Thread{
		BlockingQueue<Runnable> tasks;
		boolean shouldStop=false;
		
		public MyThread(BlockingQueue<Runnable> tasks){
			this.tasks=tasks;
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */ 
		public void run(){
			while(!shouldStop){
				try {
					Runnable worker=tasks.take();
					worker.run();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		public void stopThread(){
			shouldStop=true;
			this.interrupt();
		}
	}
	
	public ThreadPool(int nThreads, int nTasks){
		this.nThreads=nThreads;
		this.nTasks=nTasks;
		tasks=new ArrayBlockingQueue<Runnable>(this.nTasks);
		threads=new ArrayList<ThreadPool.MyThread>();
		
		for(int i=0;i<nThreads;i++)
			threads.add(new MyThread(this.tasks));
		
		for(MyThread t:threads)
			t.start();
	}
	
	public void execute(Runnable worker){
		if (!shutdown) {
			try {
				tasks.put(worker);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void shutdown(){
		for(MyThread t: threads)
			t.stopThread();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ProducerConsumer pc = new ProducerConsumer();
		ThreadPool executor = new ThreadPool(6, 4);
		
		for (int i = 0; i < 4; i++) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					pc.producer();
				}
			});
		}
		
		for (int i = 0; i < 2; i++) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					pc.consumer();
				}
			});
		}

//		executor.shutdown();

		System.out.println("Spawned 4 producers, 2 consumers.");
	}

}
