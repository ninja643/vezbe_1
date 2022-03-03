package rs.ac.ni.pmf.oop3.figure;

import java.util.Objects;

public class Circle extends Figure
{
	private final double r;

	public Circle(String label, double r)
	{
		super(label);
		this.r = r;
	}

	public double getR()
	{
		return r;
	}

	@Override
	public double area()
	{
		return r * r * Math.PI;
	}

	@Override
	public double circumference()
	{
		return 2 * r * Math.PI;
	}

	@Override
	public String toString()
	{
		return String.format("Circle: %4.2f", r);
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}

		if (o == null || getClass() != o.getClass())
		{
			return false;
		}

		final Circle circle = (Circle)o;
		return getLabel().equals(circle.getLabel()) && Double.compare(circle.r, r) == 0;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(r);
	}
}
