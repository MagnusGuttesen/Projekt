 package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;


public class EmployeeDB implements EmployeeDBIF {

	private static final String FIND_ALL_Q = "select employeeid, password, name, email from employee";
	private PreparedStatement findAllPS;

	private static final String FIND_BY_PASSWORD_OR_NAME_Q = FIND_ALL_Q
			+ " where employeeid like ? or name = ?";
	private PreparedStatement findByPasswordOrNamePS;

	private static final String FIND_BY_EMPLOYEEID_Q = FIND_ALL_Q + " where employeeid = ?";
	private PreparedStatement findByEmployeeIdPS;

	private static final String FIND_BY_EMAIL_Q = FIND_ALL_Q + " where employeeid = ?";
	private PreparedStatement findByEmailPS;

	private static final String INSERT_Q = "insert into employee (employeeid, password, name, email) values (?, ?, ?, ?)";
	private PreparedStatement insertPS;



	public EmployeeDB() throws DataAccessException {
		init();
	}
	
	public Employee findEmployeeById(String id, boolean fullAssociation) throws DataAccessException {
	    Employee emp = null;
	    try {
	    	Connection connection = DBConnection.getInstance().getConnection();
	        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Employee WHERE employeeid = ?");
	        ps.setString(1, id);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            emp = new Employee();
	            emp.setEmployeeId(rs.getString("employeeid"));
	            emp.setPassword(rs.getString("password"));
	            emp.setName(rs.getString("name"));
	            emp.setEmail(rs.getString("email"));
	        }
	    } catch (SQLException e) {
	        throw new DataAccessException("Fejl ved opslag af medarbejder", e);
	    }
	    return emp;
	}


	private void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findAllPS = con.prepareStatement(FIND_ALL_Q);
			findByPasswordOrNamePS = con.prepareStatement(FIND_BY_PASSWORD_OR_NAME_Q);
			findByEmployeeIdPS = con.prepareStatement(FIND_BY_EMPLOYEEID_Q);
			insertPS = con.prepareStatement(INSERT_Q);// using identity, we'd have to add
														// Statement.RETURN_GENERATED_KEYS as a second argument
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}

	@Override
	public List<Employee> findByPassword(String password, boolean fullAssociation) throws DataAccessException {
		List<Employee> res = null;
		try {
			findByPasswordOrNamePS.setString(1, "%" + password + "%");
			if (password != null && password.length() == 1) {
				findByPasswordOrNamePS.setString(2, password);
			} else {
				findByPasswordOrNamePS.setString(2, "");
			}
			findByPasswordOrNamePS.setString(3, "%" + password + "%");
			ResultSet rs = findByPasswordOrNamePS.executeQuery();
			res = buildObjects(rs, fullAssociation);
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		return res;
	}


	@Override
	public Employee findByEmail(String email, boolean fullAssociation) throws DataAccessException {
		Employee res = null;
		try {
			findByEmailPS.setString(1, email);
			ResultSet rs = findByEmailPS.executeQuery();
			if (rs.next()) {
				res = buildObject(rs, fullAssociation);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		return res;
	}


	public List<Employee> findByName(String name, boolean fullAssociation) throws DataAccessException {
		List<Employee> res = null;
		try {
			findByPasswordOrNamePS.setString(1, "%" + name + "%");
			ResultSet rs = findByPasswordOrNamePS.executeQuery();
			res = buildObjects(rs, fullAssociation);
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		return res;
	}

	private List<Employee> buildObjects(ResultSet rs, boolean fullAssociation) throws DataAccessException {
		List<Employee> res = new ArrayList<>();
		try {
			while (rs.next()) {
				Employee currEmployee= buildObject(rs, fullAssociation);
				res.add(currEmployee);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return res;
	}

	private Employee buildObject(ResultSet rs, boolean fullAssociation) throws DataAccessException {
		Employee currEmployee = new Employee();
		try {
			currEmployee.setPassword(rs.getString("password"));
			currEmployee.setName(rs.getString("name"));
			currEmployee.setEmail(rs.getString("email"));
			currEmployee.setEmployeeId(rs.getString("employeeId")); 

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return currEmployee;
	}

	@Override
	public List<Employee> findAll(boolean fullAssociation) throws DataAccessException {
		ResultSet rs;
		try {
			rs = this.findAllPS.executeQuery();
		} catch (SQLException e) {
			// e.printStackTsrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		List<Employee> res = buildObjects(rs, fullAssociation);
		return res;
	}
	@Override
	public Employee insert(Employee employee) throws DataAccessException {
		
		try {
			insertPS.setString(1, employee.getPassword());
			insertPS.setString(2, employee.getName());
			insertPS.setString(2, employee.getEmail());


		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_PS_VARS_INSERT, e);
		}
		try {
			insertPS.executeUpdate();
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_INSERT, e);
		}

		return employee;
	}
}