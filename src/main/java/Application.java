import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import orgchart.model.Employee;
import orgchart.util.EmployeeUtil;

public class Application {

	public static void main(String[] args) {
		
		Map<Long, Employee> employees = new HashMap<>();
		
		try {
			employees = EmployeeUtil.readFromCsvFile("personnel_10.csv");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		Iterable<Employee> employeeIterable = employees.values();
		
		for (Employee e : employeeIterable) {
			System.out.println(e);
		}
	}
	
}
