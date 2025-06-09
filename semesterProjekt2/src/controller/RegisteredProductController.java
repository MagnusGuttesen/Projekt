package controller;

import java.util.List;
import db.DataAccessException;
import db.RegisteredProductDB;
import db.RegisteredProductDBIF;
import model.RegisteredProduct;

public class RegisteredProductController {

    // Singleton instans af controlleren
    private static RegisteredProductController instance;

    // Reference til databaseadgangen via interface
    private RegisteredProductDBIF registeredProductDB;

    // Privat konstruktør, som initierer databaseforbindelsen
    private RegisteredProductController() {
        try {
            registeredProductDB = new RegisteredProductDB(); 
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    // Giver adgang til singleton-instansen
    public static RegisteredProductController getInstance() {
        if (instance == null) {
            instance = new RegisteredProductController();
        }
        return instance;
    }

    // Finder alle registrerede produkter i et givent bur
    public List<RegisteredProduct> findByCageNo(int cageNo) {
        return registeredProductDB.findByCageNo(cageNo);
    }

    // Indsætter et registreret produkt i databasen
    public void insert(RegisteredProduct rp) {
        try {
            registeredProductDB.insert(rp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
