package controller;

import java.util.List;
import db.DataAccessException;
import db.RegisteredProductDB;
import db.RegisteredProductDBIF;
import model.RegisteredProduct;

public class RegisteredProductController {

    private static RegisteredProductController instance;
    private RegisteredProductDBIF registeredProductDB;

    private RegisteredProductController() {
        try {
            registeredProductDB = new RegisteredProductDB(); 
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public static RegisteredProductController getInstance() {
        if (instance == null) {
            instance = new RegisteredProductController();
        }
        return instance;
    }

    public List<RegisteredProduct> findByCageNo(int cageNo) {
        return registeredProductDB.findByCageNo(cageNo);
    }

    public void insert(RegisteredProduct rp) {
        try {
            registeredProductDB.insert(rp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
