package orgchart.dao;

import java.util.Collection;

import orgchart.model.Employee;

public interface EmployeeDao {

	public void create(Employee employee);
	
	public Employee findOne(Long personnelId);
	
	public Collection<Employee> findAll();
	
	public void update(Employee employee);
	
	public void delete(Employee employee);
	
}
