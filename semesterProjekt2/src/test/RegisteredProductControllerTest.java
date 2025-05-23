package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import controller.RegisteredProductController;
import model.RegisteredProduct;
import java.sql.Date;
import java.util.List;

public class RegisteredProductControllerTest {

    private RegisteredProductController controller;

    @Before
    public void setUp() {
        controller = RegisteredProductController.getInstance();
    }

    @Test
    public void testFindByCageNoReturnsList() {
        List<RegisteredProduct> result = controller.findByCageNo(1);
        assertNotNull("Listen skal ikke være null", result);
    }

    @Test
    public void testInsertRegisteredProduct() {
        RegisteredProduct rp = new RegisteredProduct();
        rp.setRegisteredid((int)(Math.random() * 100000));
        rp.setProductid(1); // Skal matche et gyldigt produktid i databasen
        rp.setCageNo(1);    // Skal matche et gyldigt cageno
        rp.setQuantity(5);
        rp.setRegistrationdate(new Date(System.currentTimeMillis()));
        rp.setProductExp(Date.valueOf("2025-12-31"));

        try {
            controller.insert(rp);
        } catch (Exception e) {
            fail("Indsættelse fejlede: " + e.getMessage());
        }
    }
}
