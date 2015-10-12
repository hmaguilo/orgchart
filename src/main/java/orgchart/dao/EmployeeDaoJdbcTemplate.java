package orgchart.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import orgchart.model.Employee;

@Named
public class EmployeeDaoJdbcTemplate implements EmployeeDao {

	@Inject
	private JdbcTemplate jdbc;
	
	public void create(Employee employee) {
		
		jdbc.update("INSERT INTO employee("
			+ "personnel_id,"
			+ "first_name,"
			+ "last_name,"
			+ "phone_number,"
			+ "email,"
			+ "start_date,"
			+ "end_date,"
			+ "login,"
			+ "password)"
			+ "VALUES (?,?,?,?,?,?,?,?,?)",
				employee.getPersonnelId(),
				employee.getFirstName(),
				employee.getLastName(),
				employee.getPhoneNumber(),
				employee.getEmail(),
				employee.getStartDate(),
				employee.getEndDate(),
				employee.getLogin(),
				employee.getPassword()
		);
		
	}

	public Employee findOne(Long personnelId) {

		List<Employee> results = 
			jdbc.query("SELECT * FROM employee WHERE personnel_id = ?",
					new Object[] {personnelId}, new ResultRowMapper());
		
		if (!results.isEmpty()) {
			return results.get(0);
		}
		
		return null;
	}

	static class ResultRowMapper
		implements RowMapper<Employee> {

		public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Long personnelId = Long.parseLong(rs.getString("personnel_id"));
			String firstName = rs.getString("last_name");
			String lastName = rs.getString("first_name");
			String phoneNumber = rs.getString("phone_number");
			String email = rs.getString("email");
			String startString = rs.getString("start_date");
			String endString = rs.getString("end_date");
			String login = rs.getString("login");
			String password = rs.getString("password");
			
			Date startDate = null;
			Date endDate = null;
			
			DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
			try {
				startDate = (Date) df.parse(startString);
				endDate = (Date) df.parse(endString);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return new Employee(
					personnelId,
					firstName,
					lastName,
					phoneNumber,
					email,
					startDate,
					endDate,
					login,
					password);
		}

		
				
	}
			
			
	public Collection<Employee> findAll() {
		
		List<Employee> results =
				jdbc.query("SELECT * FROM employee", new ResultRowMapper());
		
		return results;
	}

	public void update(Employee employee) {
		
		Long personnelId = employee.getPersonnelId();
		String firstName = employee.getFirstName();
		String lastName = employee.getLastName();
		String phoneNumber = employee.getPhoneNumber();
		String email = employee.getEmail();
		Date startDate = employee.getStartDate();
		Date endDate = employee.getEndDate();
		String login = employee.getLogin();
		String password = employee.getPassword();
		
		jdbc.update("UPDATE employee SET "
				+ "first_name = ?, "
				+ "last_name = ?, "
				+ "phone_number = ?, "
				+ "email = ?, "
				+ "start_date = ?, "
				+ "end_date = ?, "
				+ "login = ?, "
				+ "password = ?"
				+ "WHERE personnel_id = ?", 
				firstName,
				lastName,
				phoneNumber,
				email,
				startDate,
				endDate,
				login,
				password,
				personnelId);
		
	}

	public void delete(Employee employee) {

		jdbc.update("DELETE FROM employee WHERE personnel_id = ?",
				employee.getPersonnelId());
		
	}

}
