package rs.ac.ni.pmf.oop3.figure;

public abstract class Figure implements Comparable<Figure>
{
	private final String label;

	public Figure(String label)
	{
		this.label = label;
	}

	public String getLabel()
	{
		return label;
	}

	public abstract double area();
	public abstract double circumference();

	@Override
	public int compareTo(Figure other)
	{
		return Double.compare(circumference(), other.circumference());
	}
}
