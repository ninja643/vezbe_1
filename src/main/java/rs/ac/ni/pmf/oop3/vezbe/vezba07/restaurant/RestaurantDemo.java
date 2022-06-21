package rs.ac.ni.pmf.oop3.vezbe.vezba07.restaurant;

import java.util.concurrent.*;

public class RestaurantDemo
{
	public static void main(String[] args) throws InterruptedException
	{
		final ExecutorService guestsService = Executors.newCachedThreadPool();
		final ScheduledExecutorService waiterService = Executors.newSingleThreadScheduledExecutor();
		final CountDownLatch countDownLatch = new CountDownLatch(3);

		final CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

		guestsService.submit(new GuestRunnable(cyclicBarrier, countDownLatch, "Mika", 4000, 3000, 1000));
		guestsService.submit(new GuestRunnable(cyclicBarrier, countDownLatch, "Pera", 1000, 1000, 500));
		guestsService.submit(new GuestRunnable(cyclicBarrier, countDownLatch, "Laza", 6000, 2000, 1000));

		waiterService.scheduleAtFixedRate(new WaiterRunnable(cyclicBarrier, 2), 1000, 1000, TimeUnit.MILLISECONDS);

		countDownLatch.await();

		guestsService.shutdown();
		waiterService.shutdown();
	}
}
