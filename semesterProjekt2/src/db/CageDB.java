package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cage;
import model.Product;


public class CageDB implements CageDBIF {

	private static final String FIND_BY_CAGENO_Q = "select cageno from cage";
	private PreparedStatement findByCageNoPS;

	private static final String INSERT_Q = "insert into cage (cageno) values (?)";
	private PreparedStatement insertPS;
	
	private static final String FIND_ALL_Q = "select * from cage";
	private PreparedStatement findAllPS;


	public CageDB() throws DataAccessException {
		init();
	}

	private void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findByCageNoPS = con.prepareStatement(FIND_BY_CAGENO_Q);
			findAllPS = con.prepareStatement(FIND_ALL_Q);
			// using identity, we'd have to add
														// Statement.RETURN_GENERATED_KEYS as a second argument
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}
	
	private List<Cage> buildObjects(ResultSet rs, boolean fullAssociation) throws DataAccessException {
		List<Cage> res = new ArrayList<>();
		try {
			while (rs.next()) {
				Cage currCage = buildObject(rs, fullAssociation);
				res.add(currCage);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return res;
	}
	
	public List<Cage> findAll(boolean fullAssociation) throws DataAccessException {
		ResultSet rs;
		try {
			rs = this.findAllPS.executeQuery();
		} catch (SQLException e) {
			// e.printStackTsrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		List<Cage> res = buildObjects(rs, fullAssociation);
		return res;
	}

	@Override
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

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return currCage;
	}
	
	}

