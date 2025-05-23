package db;

import java.sql.SQLException;
import java.util.List;
import model.RegisteredProduct;

public interface RegisteredProductDBIF {
    
    /**
     * Finder alle registrerede produkter i et givent bur.
     * 
     * @param cageNo nummeret på buret
     * @return en liste over matchende RegisteredProduct objekter
     */
    List<RegisteredProduct> findByCageNo(int cageNo);

    /**
     * Indsætter et nyt RegisteredProduct i databasen.
     * 
     * @param rp objektet der skal indsættes
     * @throws SQLException hvis indsættelsen fejler
     */
    void insert(RegisteredProduct rp) throws SQLException;
}
