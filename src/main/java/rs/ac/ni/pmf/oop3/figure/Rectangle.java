package rs.ac.ni.pmf.oop3.figure;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
@SuperBuilder
public class Rectangle extends Figure
{
	private final double a;
	private final double b;

	protected Rectangle(final String label, final double a, final double b)
	{
		super(label);
		this.a = a;
		this.b = b;
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
}
