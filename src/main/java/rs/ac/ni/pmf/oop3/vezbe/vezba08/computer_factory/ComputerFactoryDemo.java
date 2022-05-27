package rs.ac.ni.pmf.oop3.vezbe.vezba08.computer_factory;

import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class ComputerFactoryDemo
{
	public static void main(String[] args) throws ExecutionException, InterruptedException
	{
		final Producer producer1 = new Producer("Mika", Set.of(ComponentType.CPU, ComponentType.GPU), 2000);
		final Producer producer2 = new Producer("Tasa", Set.of(ComponentType.RAM, ComponentType.GPU), 1000);
		final Producer producer3 = new Producer("Pera", Set.of(ComponentType.RAM), 4000);
		final Producer producer4 = new Producer("Laza", Set.of(ComponentType.CPU), 3000);

		final Producers producers = new Producers();
		producers.registerProducer(producer1);
		producers.registerProducer(producer2);
		producers.registerProducer(producer3);
		producers.registerProducer(producer4);

		final ComponentFactory sharedFactory = new ComponentFactory(producers);

		final Consumer consumer1 = new Consumer("Žika", List.of(ComponentType.CPU, ComponentType.GPU, ComponentType.RAM), sharedFactory);
		final Consumer consumer2 = new Consumer("Đoka", List.of(ComponentType.GPU, ComponentType.GPU, ComponentType.RAM), sharedFactory);
		final Consumer consumer3 = new Consumer("Mile", List.of(ComponentType.RAM, ComponentType.RAM, ComponentType.RAM), sharedFactory);

		final ExecutorService consumerService = Executors.newCachedThreadPool();
		final Future<Long> future1 = consumerService.submit(consumer1);
		final Future<Long> future2 = consumerService.submit(consumer2);
		final Future<Long> future3 = consumerService.submit(consumer3);

		final long totalTime1 = future1.get();
		final long totalTime2 = future2.get();
		final long totalTime3 = future3.get();

		consumerService.shutdown();
		sharedFactory.shutdownFactory();
	}
}
