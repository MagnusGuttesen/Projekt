package controller;

import java.util.List;

import db.CageDB;
import db.DataAccessException;
import model.Cage;
import model.Employee;
import model.Product;

public class CageController {

	CageDB cageDB;

	// Constructor der initialiserer forbindelse til databasen via CageDB
	public CageController() throws DataAccessException {
		cageDB = new CageDB();
	}
	
	// Returnerer en liste med alle bure fra databasen
	public List<Cage> findAll() throws DataAccessException {
		List<Cage> res = cageDB.findAll(true);
		return res;
	}

	// Søger efter et specifikt bur baseret på bur-nummer
	public Cage findByCageNo(int cageNo) throws DataAccessException {
		Cage res = cageDB.findByCageNo(cageNo, true);
		return res;
	}

	public Employee getEmployee(String employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Product getProduct(int productId) {
		// TODO Auto-generated method stub
		return null;
	}

	// Metode er placeholder og returnerer altid false - kunne bruges til bekræftelse
	public boolean confirmRegistration() {
		// TODO Auto-generated method stub
		return false;
	}
}