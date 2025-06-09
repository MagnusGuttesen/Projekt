package db;

import java.sql.SQLException;
import java.util.List;
import model.RegisteredProduct;

public interface RegisteredProductDBIF {
    
    // Finder alle registrerede produkter i et givent bur.
    List<RegisteredProduct> findByCageNo(int cageNo);

    //Indsætter et nyt RegisteredProduct i databasen.
    void insert(RegisteredProduct rp) throws SQLException;
}
