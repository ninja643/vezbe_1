package rs.ac.ni.pmf.oop3.predavanja._07_executors;

import java.util.concurrent.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExecutorsDemo
{
	public static void main(String[] args)
	{
		ExecutorService executorService = Executors.newSingleThreadExecutor();

		Future<Integer> future = executorService.submit(new CallableTask(4, 0));

		executorService.shutdown();

		log.info("All tasks submitted");

		try
		{
			System.out.println("Result: " + future.get());
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		catch (ExecutionException e)
		{
			System.out.println(e.getCause().getMessage());
		}
	}
}
