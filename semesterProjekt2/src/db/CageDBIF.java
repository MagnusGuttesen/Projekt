package db;

import model.Cage;


public interface CageDBIF {

    // Finder et bur baseret på cageNo. fullAssociation bruges til at kontrollere om relaterede objekter også skal hentes.
    Cage findByCageNo(int cageNo, boolean fullAssociation) throws DataAccessException;

    // Indsætter et nyt bur i databasen
    Cage insert(Cage cage) throws DataAccessException;
}
