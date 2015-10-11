package orgchart.model;

import java.sql.Date;

public class Employee {

	private Long personnelId;
	
	private String firstName;
	
	private String lastName;
	
	private String phoneNumber;
	
	private String email;
	
	private Date startDate;
	
	private Date endDate;
	
	private String login;
	
	private String password;
	
	public Employee() {}
	
	public Employee(Long personnelId) {
		
		this.personnelId = personnelId;
		
	}
	
	public Employee(Long personnelId, String firstName, String lastName) {
		
		this.personnelId = personnelId;
		this.firstName = firstName;
		this.lastName = lastName;
		
	}
	
	public Employee(
			Long personnelId,
			String firstName,
			String lastName,
			String phoneNumber,
			String email,
			Date startDate,
			Date endDate,
			String login,
			String password) {
		
		this.personnelId = personnelId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.startDate = startDate;
		this.endDate = endDate;
		this.login = login;
		this.password = password;
		
	}

	public Long getPersonnelId() {
		return personnelId;
	}

	public void setPersonnelId(Long personnelId) {
		this.personnelId = personnelId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((personnelId == null) ? 0 : personnelId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (personnelId == null) {
			if (other.personnelId != null)
				return false;
		} else if (!personnelId.equals(other.personnelId))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		
		return personnelId + ": " + lastName + ", " + firstName;
		
	}
	
	
}
