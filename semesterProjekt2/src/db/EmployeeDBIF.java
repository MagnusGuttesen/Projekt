package db;

import java.util.List;

import db.DataAccessException;
import model.Employee;

/**
 * 
 * @author knol
 * @version 2018-08-30
 */
public interface EmployeeDBIF {
	List<Employee> findByPassword(String password, boolean fullAssociation) throws DataAccessException;
	Employee findByEmployeeId(int employeeId, boolean fullAssociation) throws DataAccessException;
	Employee findByEmail(String email, boolean fullAssociation) throws DataAccessException;
	List<Employee> findAll(boolean fullAssociation) throws DataAccessException;
	Employee insert(Employee employee) throws DataAccessException;
}