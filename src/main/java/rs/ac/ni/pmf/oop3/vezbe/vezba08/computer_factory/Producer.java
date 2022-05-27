package rs.ac.ni.pmf.oop3.vezbe.vezba08.computer_factory;

import java.util.*;
import lombok.extern.slf4j.Slf4j;
import rs.ac.ni.pmf.oop3.vezbe.WaitUtil;

@Slf4j
public class Producer implements Runnable
{
	public interface ProducerFinishedListener
	{
		public void onProducerFinished(Producer producer);
	}

	private final List<ProducerFinishedListener> _listeners = new ArrayList<>();

	private final String _name;
	private final Set<ComponentType> _availableComponentTypes;
	private final long _timeToProduceComponent;

	private boolean _busy;
	private ComponentFactory _sharedFactory;

	private ComponentType _componentToProduce;

	public Producer(final String name, final Set<ComponentType> availableComponentTypes, final long timeToProduceComponent)
	{
		_name = name;
		_availableComponentTypes = availableComponentTypes;
		_timeToProduceComponent = timeToProduceComponent;
	}

	public void registerFactory(ComponentFactory componentFactory)
	{
		_sharedFactory = componentFactory;
	}

	public boolean canProduceComponentType(final ComponentType componentType)
	{
		return _availableComponentTypes.contains(componentType);
	}

	public Set<ComponentType> getComponentTypes()
	{
		return _availableComponentTypes;
	}

	public void setComponentToProduce(final ComponentType componentType)
	{
		if (!_availableComponentTypes.contains(componentType))
		{
			throw new IllegalStateException("Producer " + _name + " cannot produce component " + componentType);
		}

		_componentToProduce = componentType;
	}

	public synchronized boolean tryHire()
	{
		if (_busy)
		{
			return false;
		}

		_busy = true;
		return true;
	}

	public void registerListener(final ProducerFinishedListener listener)
	{
		_listeners.add(listener);
	}

	@Override
	public void run()
	{
		if (_sharedFactory == null)
		{
			throw new IllegalStateException("Shared storage must not be null!");
		}

		if (_componentToProduce == null)
		{
			throw new IllegalStateException("Producer " + _name + " does not have the component to produce set!");
		}

		log.info("Producer {} - Starting to produce component: {}", _name, _componentToProduce);
		WaitUtil.sleep(_timeToProduceComponent);
		log.info("Producer {} - Finished producing component: {}", _name, _componentToProduce);

		try
		{
			_sharedFactory.getStorage(_componentToProduce).put(_componentToProduce);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		log.info("Producer {} - Component {} is in the storage", _name, _componentToProduce);

		_listeners.forEach(listener -> listener.onProducerFinished(this));
		_componentToProduce = null;

		_busy = false;
	}

	@Override
	public String toString()
	{
		return "Producer{" +
			"_name='" + _name + '\'' +
			'}';
	}
}
