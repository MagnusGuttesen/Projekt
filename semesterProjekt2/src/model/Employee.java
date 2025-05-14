package model;

public class Employee {

	private String employeeId;
	private String password;
	private String name;
	
	public Employee(String employeeId, String password, String name) {
		
		this.employeeId = employeeId;
		this.password = password;
		this.name = name; 
	}
		
	public String getEmployeeId() {
		return employeeId;
			
	}
		
	public String getPassword() {
		return password;
			
	}
		
	public String getname() {
		return name;
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
}
