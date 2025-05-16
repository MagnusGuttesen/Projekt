package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cage;


public class CageDB implements CageDBIF {

	private static final String FIND_BY_CAGENO_Q = "select cageno from cage";
	private PreparedStatement findByCageNoPS;
	
	private static final String INSERT_Q = "insert into cage (cageno) values (?)";
	private PreparedStatement insertPS;


	

	public CageDB() throws DataAccessException {
		//cageDB = new CageDB(this);
		init();
	}

	private void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findByCageNoPS = con.prepareStatement(FIND_BY_CAGENO_Q);
			// using identity, we'd have to add
														// Statement.RETURN_GENERATED_KEYS as a second argument
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}
	
	public Cage findByCageNo(int cageNo, boolean fullAssociation) throws DataAccessException {
		Cage res = null;
		try {
			findByCageNoPS.setInt(1, cageNo);
			ResultSet rs = findByCageNoPS.executeQuery();
			if (rs.next()) {
				res = buildObject(rs, fullAssociation);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		return res;
	}

	private Cage buildObject(ResultSet rs, boolean fullAssociation) throws DataAccessException {
		Cage currCage = new Cage();
		try {
			currCage.setCageNo(rs.getInt("cageNo"));
			
			
			/*if (fullAssociation) {
				Employee supervisor = findBySSN(currEmployee.getSupervisor().getSsn(), false); 
				currEmployee.setSupervisor(supervisor);
				Department department = this.departmentDB.findByDnumber(currEmployee.getDept().getDnumber(), false);
				currEmployee.setDepartment(department);
			}*/
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return currCage;
	}
	
	}