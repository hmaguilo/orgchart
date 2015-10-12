package orgchart.service;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import orgchart.dao.EmployeeDao;
import orgchart.model.Employee;
import orgchart.util.EmployeeUtil;

@Named
public class EmployeeServiceSpring implements EmployeeService{

	@Inject
	private EmployeeDao dao;
	
	public boolean create(Employee employee) {
		
		if (EmployeeUtil.validateEmployee(employee)
				&& dao.findOne(employee.getPersonnelId()) == null) {
			
			dao.create(employee);
			return true;
			
		}
		
		return false;
	}
	
	public Employee findOne(Long personnelId) {
		
		return dao.findOne(personnelId);
		
	}
	
	public Collection<Employee> findAll() {
		
		return dao.findAll();
		
	}
	
	public boolean update(Employee employee) {
		
		if (EmployeeUtil.validateEmployee(employee)
				&& dao.findOne(employee.getPersonnelId()) != null) {
			
			dao.update(employee);
			return true;
			
		}
		
		return false;
	}
	
	public boolean delete(Employee employee) {
		
		if (dao.findOne(employee.getPersonnelId()) != null) {
			
			dao.delete(employee);
			return true;
			
		}
		
		return false;
	}
	
}
