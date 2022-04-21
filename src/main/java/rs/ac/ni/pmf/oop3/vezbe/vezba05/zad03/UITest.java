package rs.ac.ni.pmf.oop3.vezbe.vezba05.zad03;

import java.io.IOException;

public class UITest
{
	public static void main(String[] args) throws IOException
	{
		final UnresponsiveUI ui = new UnresponsiveUI();

		System.out.println("Press enter to finish");
		System.in.read();
		System.out.println("d = " + ui.getD());
	}
}
