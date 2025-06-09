package db;

import java.util.List;

import model.Employee;

public interface EmployeeDBIF {
	List<Employee> findAll(boolean fullAssociation) throws DataAccessException;
	Employee findEmployeeById(String employeeId, boolean fullAssociation) throws DataAccessException;
	Employee findByEmail(String email, boolean fullAssociation) throws DataAccessException;
	List<Employee> findByName(String name, boolean fullAssociation) throws DataAccessException;
	Employee insert(Employee employee) throws DataAccessException;
}