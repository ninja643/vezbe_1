package rs.ac.ni.pmf.oop3.figure;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Figure implements Comparable<Figure>
{
	private final String label;

	public abstract double area();
	public abstract double circumference();

	@Override
	public int compareTo(Figure other)
	{
		return Double.compare(circumference(), other.circumference());
	}
}
