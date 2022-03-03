package rs.ac.ni.pmf.oop3.figure;

import java.util.Objects;

public class Rectangle extends Figure
{
	private final double a;
	private final double b;

	public Rectangle(String label, double a, double b)
	{
		super(label);
		this.a = a;
		this.b = b;
	}

	public double getA()
	{
		return a;
	}

	public double getB()
	{
		return b;
	}

	@Override
	public double area()
	{
		return a * b;
	}

	@Override
	public double circumference()
	{
		return 2 * (a + b);
	}

	@Override
	public String toString()
	{
		return String.format("Rectangle: (%4.2f, %4.2f)", a, b);
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}

		if (o == null || !(o instanceof Rectangle))
		{
			return false;
		}

		final Rectangle rectangle = (Rectangle)o;
		return Double.compare(rectangle.a, a) == 0 && Double.compare(rectangle.b, b) == 0;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(a, b);
	}
}
