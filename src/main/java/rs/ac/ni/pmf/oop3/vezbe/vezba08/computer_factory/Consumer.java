package rs.ac.ni.pmf.oop3.vezbe.vezba08.computer_factory;

import java.util.List;
import java.util.concurrent.Callable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class Consumer implements Callable<Long>
{
	private final String _name;
	private final List<ComponentType> _requiredComponents;
	private final ComponentFactory _sharedFactory;

	@Override
	public Long call() throws Exception
	{
		final long start = System.currentTimeMillis();

		for (final ComponentType componentType : _requiredComponents)
		{
			log.info("Consumer {} - requesting component {}", _name, componentType);
			_sharedFactory.produceComponent(componentType);
			log.info("Consumer {} - got the component {}", _name, componentType);
		}

		return System.currentTimeMillis() - start;
	}
}
