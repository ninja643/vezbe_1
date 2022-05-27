package rs.ac.ni.pmf.oop3.vezbe.vezba08.computer_factory;

import java.util.*;
import java.util.concurrent.Semaphore;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Producers implements Producer.ProducerFinishedListener
{
	private final List<Producer> _availableProducers = new ArrayList<>();
	private final Map<ComponentType, Semaphore> _semaphores = new EnumMap<>(ComponentType.class);

	public Producers()
	{
		for (final ComponentType componentType : ComponentType.values())
		{
			_semaphores.put(componentType, new Semaphore(0));
		}
	}

	private void increaseNumberOfProducersPerComponentType(final ComponentType componentType)
	{
		_semaphores.get(componentType).release();
	}

	public void registerProducer(final Producer producer)
	{
		_availableProducers.add(producer);
		producer.registerListener(this);

		for (final ComponentType componentType : ComponentType.values())
		{
			if (producer.canProduceComponentType(componentType))
			{
				increaseNumberOfProducersPerComponentType(componentType);
			}
		}
	}

	public Producer hireProducer(final ComponentType componentType) throws InterruptedException
	{
		log.info("Trying to hire a producer for component: {}", componentType);
		Producer hiredProducer = null;

		while (hiredProducer == null)
		{
			_semaphores.get(componentType).acquire();

			synchronized (_availableProducers)
			{
				for (final Producer producer : _availableProducers)
				{
					if (producer.canProduceComponentType(componentType) && producer.tryHire())
					{
						log.info("Found the available producer {}", producer);
						hiredProducer = producer;
						break;
					}
				}
			}

			if (hiredProducer == null)
			{
				// Someone else already hired the producer for this component type, release the semaphore
				_semaphores.get(componentType).release();
			}
		}

		return hiredProducer;
	}

	@Override
	public void onProducerFinished(Producer producer)
	{
		producer.getComponentTypes().forEach(componentType -> _semaphores.get(componentType).release());
		log.info("Producer {} is now free", producer);
	}

	public void registerFactory(final ComponentFactory componentFactory)
	{
		for (final Producer producer : _availableProducers)
		{
			producer.registerFactory(componentFactory);
		}
	}
}
