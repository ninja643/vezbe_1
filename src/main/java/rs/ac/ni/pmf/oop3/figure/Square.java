package rs.ac.ni.pmf.oop3.figure;

public class Square extends Rectangle
{
	public Square(String label, double a)
	{
		super(label, a, a);
	}

	@Override
	public String toString()
	{
		return String.format("Square: (%4.2f, %4.2f)", getA(), getB());
	}
}
