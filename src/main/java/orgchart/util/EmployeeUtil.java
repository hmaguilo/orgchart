package orgchart.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import orgchart.model.Employee;

public class EmployeeUtil {

	private EmployeeUtil() {}
	
	
	public static Map<Long, Employee> readFromCsvFile(String csvFile) throws IOException {
		
		Map<Long, Employee> employees = new HashMap<>();
		
		String line = "";
		
		InputStream in =
				Employee.class.getClassLoader().getResourceAsStream(csvFile);
		
		if (in.available() == 0) {
			throw new IOException("No such file.");
		}
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
			
			line = br.readLine();
			
			if (line == null) {
				throw new IOException("File empty.");
			}
			
			String[] dataHeaders = line.split(",");
			
			while((line = br.readLine()) != null) {
				
				StringBuilder sb;
				int index = 0;
				boolean quotes = false;
				
				Employee newEmployee = new Employee();
				
				for (int i = 0; i < dataHeaders.length; i++) {
					
					sb = new StringBuilder();
					
					while (index < line.length() && (quotes || line.charAt(index) != ',')) {
						
						if (line.charAt(index) == '"') {
							
							quotes = !quotes;
							index++;

						}
						else {
							
							sb.append(line.charAt(index));
							index++;

						}
						
					}
						
					index++;

					if (dataHeaders[i].equals("personnelId")) {
						Long personnelId =Long.parseLong(sb.toString());
						newEmployee.setPersonnelId(personnelId);
					}
					else if (dataHeaders[i].equals("firstName")) {
						newEmployee.setFirstName(sb.toString());
					}
					else if (dataHeaders[i].equals("lastName")) {
						newEmployee.setLastName(sb.toString());
					}
					else if (dataHeaders[i].equals("phoneNumber")) {
						newEmployee.setPhoneNumber(sb.toString());
					}
					else if (dataHeaders[i].equals("email")) {
						newEmployee.setEmail(sb.toString());
					}
					else if (dataHeaders[i].equals("startDate")) {
						Date startDate = stringToDate(sb.toString());
						newEmployee.setStartDate(startDate);
					}
					else if (dataHeaders[i].equals("endDate")) {
						Date endDate = stringToDate(sb.toString());
						newEmployee.setEndDate(endDate);
					}
					else if (dataHeaders[i].equals("login")) {
						newEmployee.setLogin(sb.toString());
					}
					else if (dataHeaders[i].equals("password")) {
						newEmployee.setPassword(sb.toString());
					}
					
				}
				
				Long personnelId = newEmployee.getPersonnelId();
				
				if (!employees.containsKey(personnelId)) {
					employees.put(personnelId, newEmployee);
				}
				
			}
		}
		
		return employees;
		
	}
	
	public static Date stringToDate(String dateStr) {
		
		DateFormat slashFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date dateObj = null;
		
		if (!dateStr.isEmpty() && dateStr != null) {

			try {
				dateObj = new Date(slashFormat.parse(dateStr).getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
			
		return dateObj;
	}
	
}
