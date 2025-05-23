package model;

public class Employee {

	private String employeeId;
	private String password;
	private String name;
	private String email;

	public Employee(String employeeId, String password, String name, String email) {

		this.employeeId = employeeId;
		this.password = password;
		this.name = name;
		this.email = email;
	}

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
