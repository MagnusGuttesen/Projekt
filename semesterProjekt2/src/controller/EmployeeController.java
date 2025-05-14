package controller;

public class EmployeeController implements EmployeeControllerIF{

	private EmployeeDB employeeDB;
	
	@Override
	public Employee getEmployee(String employeeId) throws DataAccesException {
		return employeeDB.findByEmployeeId(employeeId);
	}
}
