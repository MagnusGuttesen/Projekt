package controller;

import java.util.List;

import db.DataAccessException;
import db.EmployeeDB;
import model.Employee;


public class EmployeeController {
	
	EmployeeDB employeeDB;
	
	public EmployeeController() throws DataAccessException {
		employeeDB = new EmployeeDB();

	}

	public List<Employee> findByPassword(String password) throws DataAccessException {
		List<Employee> res = employeeDB.findByPassword(password, true);
		return res;
	}

	public List<Employee> findAll() throws DataAccessException {
		List<Employee> res = employeeDB.findAll(false);
		return res;
	}

	public Employee findByEmployeeId(int employeeId) throws DataAccessException {
		Employee res = employeeDB.findByEmployeeId(employeeId, true);
		return res;
	}

	public List<Employee> findByName(String name) throws DataAccessException {
		List<Employee> res = employeeDB.findByName(name, true);
		return res;
	}
	
	public Employee findByEmail(String email) throws DataAccessException {
		Employee res = employeeDB.findByEmail(email, true);
		return res;
	}
	
	public Employee insert(Employee employee) throws DataAccessException {
		Employee res = employeeDB.insert(employee);
		return res;
	}

}
