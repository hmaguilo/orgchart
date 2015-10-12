package orgchart.service;

import java.util.Collection;

import orgchart.model.Employee;

public interface EmployeeService {
	
	public boolean create(Employee employee);
	
	public Employee findOne(Long personnelId) ;
	
	public Collection<Employee> findAll();
	
	public boolean update(Employee employee);
	
	public boolean delete(Employee employee);
}
