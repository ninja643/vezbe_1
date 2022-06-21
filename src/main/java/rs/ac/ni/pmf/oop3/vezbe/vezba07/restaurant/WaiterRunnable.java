package rs.ac.ni.pmf.oop3.vezbe.vezba07.restaurant;

import java.util.concurrent.CyclicBarrier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class WaiterRunnable implements Runnable
{
	private final CyclicBarrier _barrier;
	private final int _limit;

	@Override
	public void run()
	{
		log.info("Waiter is checking if enough guests have finished the meal");
		if (_barrier.getNumberWaiting() >= _limit)
		{
			System.err.println("Informing the cook to prepare the next meal");
		}
	}
}
