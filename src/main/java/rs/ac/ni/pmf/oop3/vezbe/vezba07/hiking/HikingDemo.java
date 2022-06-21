package rs.ac.ni.pmf.oop3.vezbe.vezba07.hiking;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HikingDemo
{
	public static void main(String[] args)
	{
		final List<HikingField> hikingFields = List.of(
			new HikingField(1),
			new HikingField(1),
			new HikingField(1),
			new HikingField(1),
			new HikingField(10)
		);

		final List<Hiker> hikers = List.of(
			new Hiker(hikingFields, "Pera", 0, 1000),
			new Hiker(hikingFields, "Mika", 1, 1500)
		);

		final ExecutorService hikerService = Executors.newCachedThreadPool();

		hikers.forEach(hikerService::submit);

		hikerService.shutdown();
	}
}
