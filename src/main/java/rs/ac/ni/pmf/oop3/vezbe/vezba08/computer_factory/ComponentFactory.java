package rs.ac.ni.pmf.oop3.vezbe.vezba08.computer_factory;

import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.*;

public class ComponentFactory
{
	private final Map<ComponentType, BlockingQueue<ComponentType>> _storage = new EnumMap<>(ComponentType.class);

	private final Producers _producers;

	private final ExecutorService _productionLines = Executors.newFixedThreadPool(3);

	public ComponentFactory(final Producers producers)
	{
		_producers = producers;

		for (final ComponentType componentType : ComponentType.values())
		{
			_storage.put(componentType, new SynchronousQueue<>());
		}

		_producers.registerFactory(this);
	}

	public BlockingQueue<ComponentType> getStorage(final ComponentType componentType)
	{
		return _storage.get(componentType);
	}

	public ComponentType produceComponent(final ComponentType componentType) throws InterruptedException
	{
		final Producer producer = _producers.hireProducer(componentType);
		producer.setComponentToProduce(componentType);
		_productionLines.submit(producer);

		return _storage.get(componentType).take();
	}

	public void shutdownFactory()
	{
		_productionLines.shutdown();
	}
}
