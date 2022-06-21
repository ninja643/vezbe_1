package rs.ac.ni.pmf.oop3.vezbe.vezba07.hiking;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class HikingField
{
	private final BlockingQueue<String> _queue;

	public HikingField(final int capacity)
	{
		_queue = new ArrayBlockingQueue<>(capacity);
	}

	public BlockingQueue<String> getQueue()
	{
		return _queue;
	}
}
