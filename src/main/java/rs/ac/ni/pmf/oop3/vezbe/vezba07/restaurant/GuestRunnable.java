package rs.ac.ni.pmf.oop3.vezbe.vezba07.restaurant;

import java.util.concurrent.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import rs.ac.ni.pmf.oop3.vezbe.WaitUtil;

@RequiredArgsConstructor
@Slf4j
public class GuestRunnable implements Runnable
{
	private final CyclicBarrier _barrier;
	private final CountDownLatch _latch;
	private final String _name;
	private final long _appetizerTimeout;
	private final long _mainCourseTimeout;
	private final long _desertTimeout;

	@Override
	public void run()
	{
		log.info("Guest {}, starting the appetizer", _name);
		WaitUtil.sleep(_appetizerTimeout);
		log.info("Guest {}, finished the appetizer", _name);

		try
		{
			log.info("Guest {} is waiting for others to finish the appetizer", _name);
			log.info("There are currently {} / {} guests waiting to continue", _barrier.getNumberWaiting(), _barrier.getParties());
			int toWait = _barrier.await();

			if (toWait == 0)
			{
				_barrier.reset();
			}
		}
		catch (InterruptedException | BrokenBarrierException e)
		{
			e.printStackTrace();
		}

		log.info("Guest {}, starting the main course", _name);
		WaitUtil.sleep(_mainCourseTimeout);
		log.info("Guest {}, finished the main course", _name);

		try
		{
			log.info("Guest {} is waiting for others to finish the main course", _name);
			_barrier.await();
		}
		catch (InterruptedException | BrokenBarrierException e)
		{
			e.printStackTrace();
		}

		log.info("Guest {}, starting desert", _name);
		WaitUtil.sleep(_desertTimeout);
		log.info("Guest {}, finished the desert", _name);

		_latch.countDown();
	}
}
