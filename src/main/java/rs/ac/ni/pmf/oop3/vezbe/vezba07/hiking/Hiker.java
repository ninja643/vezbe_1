package rs.ac.ni.pmf.oop3.vezbe.vezba07.hiking;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import rs.ac.ni.pmf.oop3.vezbe.WaitUtil;

@Slf4j
public class Hiker implements Runnable
{
	private final List<HikingField> _hikingFields;
	private final String _name;
	private int _currentField;
	private final long _timeout;

	public Hiker(
		final List<HikingField> hikingFields,
		final String name,
		final int startingField,
		final long timeout)
	{
		_hikingFields = hikingFields;
		_name = name;
		_currentField = startingField;
		_timeout = timeout;

		_hikingFields.get(_currentField).getQueue().add(_name);
	}

	@Override
	public void run()
	{
		while (_currentField < _hikingFields.size() - 1)
		{
			// Free the current field
			_hikingFields.get(_currentField).getQueue().remove();
			log.info("Hiker {} is climbing to {}", _name, _currentField + 1);
			WaitUtil.sleep(_timeout);

			try
			{
				_hikingFields.get(_currentField + 1).getQueue().put(_name);
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
