package rs.ac.ni.pmf.oop3.figure;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
@SuperBuilder
public class Square extends Rectangle
{
	public Square(String label, double a)
	{
		super(label, a, a);
	}
}
