package rs.ac.ni.pmf.oop3.predavanja._07_executors;

import java.util.concurrent.Callable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class CallableTask implements Callable<Integer>
{
	private final int a;
	private final int b;

	@Override
	public Integer call() throws Exception
	{
		log.info("Computing {}/{}", a, b);
		Thread.sleep(50);
		return a / b;
	}
}
