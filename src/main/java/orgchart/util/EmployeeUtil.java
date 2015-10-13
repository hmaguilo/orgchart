package orgchart.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	public static boolean validatePhoneNumber(String phoneNumber) {
		
		String phoneRegex = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";
		Pattern pattern = Pattern.compile(phoneRegex);
		Matcher matcher = pattern.matcher(phoneNumber);
		
		if (matcher.matches()) {
			return true;
		}
		
		return false;
	}
	
	public static boolean validateEmail(String email) {
		
		String emailRegex =
			"^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(email);
		
		if (matcher.matches()) {
			return true;
		}
		
		return false;
	}
	
	public static boolean validateDate(String dateStr) {
		
		if (dateStr == null) {
			return false;
		}
		
		DateFormat sdf;
		
		String slashFormat = "\\d{1,2}/\\d{1,2}/\\d{4}";
		String dashFormat = "\\d{4}-\\d{1,2}-\\d{1,2}";
		
		Pattern slashPattern = Pattern.compile(slashFormat);
		Pattern dashPattern = Pattern.compile(dashFormat);
		
		Matcher slashMatcher = slashPattern.matcher(dateStr);
		Matcher dashMatcher = dashPattern.matcher(dateStr);
		
		if (slashMatcher.matches()) {
			
			sdf = new SimpleDateFormat("MM/dd/yyyy");
			sdf.setLenient(false);
			
		}
		else if (dashMatcher.matches()) {
			
			sdf = new SimpleDateFormat("yyyy-MM-dd");
			sdf.setLenient(false);

		}
		else {
			System.out.println("no matches found");
			return false;
			
		}
		
		try {
			sdf.parse(dateStr);
		} catch (ParseException e) {
			System.out.println("parse failed");
			return false;
		}
		
		return true;
	}
	
	public static boolean validateEmployee(Employee employee) {
		
		boolean hasPersonnelId = employee.getPersonnelId() != null;
		boolean hasFullName =
				employee.getFirstName() != null && 
				employee.getLastName() != null;
		boolean hasValidPhone =
				employee.getPhoneNumber() == null ||
				EmployeeUtil.validatePhoneNumber(employee.getPhoneNumber());
		boolean hasValidEmail = 
				employee.getEmail() == null ||
				EmployeeUtil.validateEmail(employee.getEmail());
		
		if (
				!hasPersonnelId 
				|| !hasFullName
				|| !hasValidPhone
				|| !hasValidEmail) {
			
			return false;
			
		}
		
		Date today = new Date(Calendar.getInstance().getTimeInMillis());
		Date startDate;
		Date endDate;
		
		if ((startDate = employee.getStartDate()) != null 
				&& startDate.after(today)) {

			return false;
			
		}
		else if ((endDate = employee.getEndDate()) != null 
				&& endDate.before(startDate)){

			return false;
			
		}
		
		return true;
	}
	
	public static Date parseDate(String dateStr) {
		
		if (dateStr == null) {
			return null;
		}
		
		Date date = null;
		
		DateFormat sdf;
		
		String slashFormat = "\\d{1,2}/\\d{1,2}/\\d{4}";
		String dashFormat = "\\d{4}-\\d{1,2}-\\d{1,2}";
		
		Pattern slashPattern = Pattern.compile(slashFormat);
		Pattern dashPattern = Pattern.compile(dashFormat);
		
		Matcher slashMatcher = slashPattern.matcher(dateStr);
		Matcher dashMatcher = dashPattern.matcher(dateStr);
		
		if (slashMatcher.matches()) {
			
			sdf = new SimpleDateFormat("MM/dd/yyyy");
			sdf.setLenient(false);
			
		}
		else if (dashMatcher.matches()) {
			
			sdf = new SimpleDateFormat("yyyy-MM-dd");
			sdf.setLenient(false);

		}
		else {
			
			return null;
			
		}
		
		try {
			date = new Date(sdf.parse(dateStr).getTime());
		} catch (ParseException e) {
			return null;
		}
		
		return date;
	}
	
	
	
	
}
