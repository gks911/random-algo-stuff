/**
 * 
 */
package edu.gks.random;

import java.util.Random;
//import java.util.concurrent.ArrayBlockingQueue;
//import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author gaurav 
 * 		    A producer-consumer demonstration using Thread pools and
 *         BlockingQueue. Runs indefinitely, until stopped manually, for
 *         demonstration purposes
 */
public class ProducerConsumer {

	private static final int LIMIT = 12;
//	private BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(LIMIT);
	private BlockingQueue customQueue = new BlockingQueue(LIMIT);

	public void producer() {
		Random rand = new Random();
		while (true) {
			try {
				// Simulate some useful work
				Thread.sleep(500);
				int x = rand.nextInt(1024);
//				queue.put(x);
				customQueue.put(x);
//				System.out.printf("Thread %d : Pushed data = %d, Queue size = %d\n", Thread.currentThread().getId(), x,
//						customQueue.size());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void consumer() {
		while (true) {
			try {
				// Simulate some useful work
				Thread.sleep(1000);
//				System.out.printf("Thread %d : Got data = %d, Queue size = %d\n", Thread.currentThread().getId(),
//						queue.take(), queue.size());
//				System.out.printf("Thread %d : Got data = %d, Queue size = %d\n", Thread.currentThread().getId(),
//						customQueue.get(), customQueue.size());
				customQueue.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ProducerConsumer pc = new ProducerConsumer();
		ExecutorService executor = Executors.newFixedThreadPool(6);

		for (int i = 0; i < 4; i++) {
			executor.submit(new Runnable() {
				@Override
				public void run() {
					pc.producer();
				}
			});
		}

//		executor.submit(new Runnable() {
//			@Override
//			public void run() {
//				pc.consumer();
//			}
//		});

		 for(int i=0;i<2;i++){
		 executor.submit(new Runnable() {
		 @Override
		 public void run() {
		 pc.consumer();
		 }
		 });
		 }

		executor.shutdown();

		System.out.println("Spawned 4 producers, 2 consumers.");

		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
