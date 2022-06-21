package rs.ac.ni.pmf.oop3.vezbe.vezba07.hiking_semaphore;

import java.util.concurrent.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HikingField
{
	private final Semaphore _semaphore;
	private final CyclicBarrier _barrier;
	private boolean _barrierIsBroken;

	public HikingField(final int capacity)
	{
		this(capacity, 0);
	}

	public HikingField(final int capacity, final int barrierStrength)
	{
		_semaphore = new Semaphore(capacity);
		_barrier = barrierStrength > 0 ? new CyclicBarrier(barrierStrength) : null;

		_barrierIsBroken = _barrier == null;
	}

	public void allowNextHiker() throws InterruptedException
	{
		_semaphore.acquire();

		while (!_barrierIsBroken)
		{
			if (_barrier.getNumberWaiting() < _barrier.getParties() - 1)
			{
				log.info("Need {} more hikers to break the barrier", _barrier.getParties() - 1 - _barrier.getNumberWaiting());
			}

			try
			{
				int n = _barrier.await(1, TimeUnit.SECONDS);
				if (n == 0)
				{
					log.info("Barrier has been broken");
					_barrierIsBroken = true;
				}
			}
			catch (BrokenBarrierException e)
			{
				log.error(e.getMessage());
			}
			catch (TimeoutException e)
			{
				log.info("Still waiting for barrier to break");
			}
		}
	}

	public void removeHiker()
	{
		_semaphore.release();
	}
}
