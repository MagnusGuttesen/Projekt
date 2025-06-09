package controller;

import java.util.List;

import db.DataAccessException;
import db.EmployeeDB;
import model.Employee;

public class EmployeeController {

    // Reference til databaselagets EmployeeDB
    EmployeeDB employeeDB;

    public EmployeeController() throws DataAccessException {
        employeeDB = new EmployeeDB();
    }

    // Returnerer en liste med alle ansatte uden fulde relationer
    public List<Employee> findAll() throws DataAccessException {
        List<Employee> res = employeeDB.findAll(false);
        return res;
    }

    // Søger ansatte ud fra navn med fulde relationer
    public List<Employee> findByName(String name) throws DataAccessException {
        List<Employee> res = employeeDB.findByName(name, true);
        return res;
    }

    // Finder én ansat ud fra e-mail
    public Employee findByEmail(String email) throws DataAccessException {
        Employee res = employeeDB.findByEmail(email, true);
        return res;
    }

  

    // Indsætter en ny ansat i databasen
    public Employee insert(Employee employee) throws DataAccessException {
        Employee res = employeeDB.insert(employee);
        return res;
    }

    // Finder én ansat ud fra ID, håndterer eventuelle fejl internt
    public Employee findEmployeeById(String id) {
        try {
            return employeeDB.findEmployeeById(id, true);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
