package rs.ac.ni.pmf.oop3.vezbe.vezba04.employees;

import java.util.Arrays;
import java.util.List;

public class EmployeesRepository
{
	public static final EmployeesRepository INSTANCE = new EmployeesRepository();

	private EmployeesRepository()
	{
	}

	private final List<Employee> _employees = Arrays.asList(
		Employee.builder().id(1).name("Pera").title(Employee.Title.CEO).build(),
		Employee.builder().id(1).name("Mika").title(Employee.Title.CTO).build(),
		Employee.builder().id(1).name("Mara").title(Employee.Title.CTO).build(),
		Employee.builder().id(1).name("Sonja").title(Employee.Title.DEVELOPER).build(),
		Employee.builder().id(1).name("Rada").title(Employee.Title.DEVELOPER).build(),
		Employee.builder().id(1).name("Djoka").title(Employee.Title.DEVELOPER).build(),
		Employee.builder().id(1).name("Sale").title(Employee.Title.QA).build(),
		Employee.builder().id(1).name("Dule").title(Employee.Title.QA).build(),
		Employee.builder().id(1).name("Tasa").title(Employee.Title.DEVELOPER).build()
	);

	public List<Employee> findAll()
	{
		return _employees;
	}
}
