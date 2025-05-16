package controller;

import db.DataAccessException;
import model.Cage;
import model.Product;
import db.CageDB;

public class CageController {

	CageDB	cageDB;
	
	public CageController() throws DataAccessException {
		cageDB = new CageDB();

	}
	
	public Cage findByCageNo(int cageNo) throws DataAccessException {
		Cage res = cageDB.findByCageNo(cageNo, true);
		return res;
	}
	
	public Cage insert(Cage cage) throws DataAccessException {
		Cage res = cageDB.insert(cage);
		return res;
	}

}
