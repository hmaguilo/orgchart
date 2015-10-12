package orgchart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import orgchart.controller.EmployeeController;
import orgchart.dao.EmployeeDao;
import orgchart.dao.EmployeeDaoJdbcTemplate;
import orgchart.service.EmployeeService;
import orgchart.service.EmployeeServiceSpring;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
		
	}
	
}
