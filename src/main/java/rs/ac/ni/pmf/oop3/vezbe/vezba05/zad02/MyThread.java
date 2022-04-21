package rs.ac.ni.pmf.oop3.vezbe.vezba05.zad02;

public class MyThread extends Thread
{
	@Override
	public void run()
	{
		System.out.println("custom run()");
	}

	@Override
	public synchronized void start()
	{
		System.out.println("start ()");
		run();
		super.start();
	}

	public static void main(String[] args)
	{
		MyThread myThread = new MyThread();
		myThread.start();
		System.out.println("main");
	}
}
