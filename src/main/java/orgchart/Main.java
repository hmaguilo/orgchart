package orgchart;

import orgchart.model.Employee;
import orgchart.util.EmployeeUtil;

public class Main {

	public static void main(String[] args) {
		
		Employee employee = new Employee(111111L, "asdfa", "asdfasdf");
	
		System.out.println(EmployeeUtil.validateEmployee(employee));
	
	}
	
}
