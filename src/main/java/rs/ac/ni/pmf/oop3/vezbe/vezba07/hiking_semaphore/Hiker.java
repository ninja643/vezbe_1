package rs.ac.ni.pmf.oop3.vezbe.vezba07.hiking_semaphore;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import rs.ac.ni.pmf.oop3.vezbe.WaitUtil;

@Slf4j
public class Hiker implements Runnable
{
	private final List<HikingField> _hikingFields;
	private final String _name;
	private int _currentField;
	private final long _climbTimeout;
	private final long _restTimeout;

	public Hiker(
		final List<HikingField> hikingFields,
		final String name,
		final int startingField,
		final long climbTimeout,
		final long restTimeout) throws InterruptedException
	{
		_hikingFields = hikingFields;
		_name = name;
		_currentField = startingField;
		_climbTimeout = climbTimeout;
		_restTimeout = restTimeout;

		log.info("Hiker {} is initially on field {}", _name, _currentField);
		_hikingFields.get(_currentField).allowNextHiker();
	}

	@Override
	public void run()
	{
		while (_currentField < _hikingFields.size() - 1)
		{
			log.info("Hiker {} is currently waiting on field {}", _name, _currentField);
			WaitUtil.sleep(_restTimeout);

			// Free the current field
			_hikingFields.get(_currentField).removeHiker();
			log.info("Hiker {} is climbing to {}", _name, _currentField + 1);
			WaitUtil.sleep(_climbTimeout);

			try
			{
				_hikingFields.get(_currentField + 1).allowNextHiker();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			log.info("Hiker {} is on {}", _name, _currentField + 1);

			_currentField++;
		}

		log.info("Hiker {} has finished the climb!", _name);
	}
}
