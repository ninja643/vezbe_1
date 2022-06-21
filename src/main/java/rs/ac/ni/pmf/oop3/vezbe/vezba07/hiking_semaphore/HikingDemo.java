package rs.ac.ni.pmf.oop3.vezbe.vezba07.hiking_semaphore;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HikingDemo
{
	public static void main(String[] args) throws InterruptedException
	{
		final List<HikingField> hikingFields = List.of(
			new HikingField(10),
			new HikingField(2),
			new HikingField(4, 3),
			new HikingField(1),
			new HikingField(10)
		);

		final List<Hiker> hikers = List.of(
			new Hiker(hikingFields, "Pera", 0, 1000, 1000),
			new Hiker(hikingFields, "Mika", 1, 500, 4000),
			new Hiker(hikingFields, "Laza", 0, 1500, 1000),
			new Hiker(hikingFields, "Tasa", 0, 2500, 2000),
			new Hiker(hikingFields, "Đoka", 0, 1500, 1500),
			new Hiker(hikingFields, "Mile", 0, 3500, 2000)
		);

		final ExecutorService hikerService = Executors.newCachedThreadPool();

		hikers.forEach(hikerService::submit);

		hikerService.shutdown();
	}
}
