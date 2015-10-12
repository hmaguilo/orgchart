package orgchart.controller;

import java.util.Collection;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import orgchart.model.Employee;
import orgchart.service.EmployeeServiceSpring;

@RestController
public class EmployeeController {

	@Inject
	private EmployeeServiceSpring service;
	
	@RequestMapping(value = "/employee", method = RequestMethod.POST) 
	public boolean create(@RequestBody Employee employee) {
		
		return service.create(employee);
		
	}
	
	@RequestMapping(value = "/employee/{personnelId}", method = RequestMethod.GET)
	public Employee findOne(Long personnelId) {
		
		return service.findOne(personnelId);
		
	}
	
	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public Collection<Employee> findAll() {
		
		return service.findAll();
		
	}
	
	@RequestMapping(value = "/employee/{personnelId}", method = RequestMethod.PUT)
	public boolean update(@RequestBody Employee employee) {
		
		return service.update(employee);
		
	}
	
	@RequestMapping(value = "/employee", method = RequestMethod.DELETE)
	public boolean delete(@RequestBody Employee employee) {
		
		return service.delete(employee);
		
	}
	
}
