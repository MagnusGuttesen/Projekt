package db;

import model.Cage;


public interface CageDBIF {

	Cage findByCageNo(int cageNo, boolean fullAssociation) throws DataAccessException;
	Cage insert(Cage cage) throws DataAccessException;
}
