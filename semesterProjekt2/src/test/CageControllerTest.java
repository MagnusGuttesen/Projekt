package test;

import controller.CageController;
import db.DataAccessException;
import model.Cage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CageControllerTest {

    private CageController controller;

    @BeforeEach
    void setUp() throws DataAccessException {
        controller = new CageController();
    }

    @Test
    void testFindAll_ShouldReturnCages() throws DataAccessException {
        List<Cage> cages = controller.findAll();
        assertNotNull(cages);
        assertTrue(cages.size() > 0, "Bur-listen bør ikke være tom");
    }

    @Test
    void testFindByCageNo_ValidNo_ShouldReturnCage() throws DataAccessException {
        int existingCageNo = 1; // Antage at bur 1 eksisterer
        Cage cage = controller.findByCageNo(existingCageNo);
        assertNotNull(cage);
        assertEquals(existingCageNo, cage.getCageNo());
    }

    @Test
    void testFindByCageNo_InvalidNo_ShouldReturnNull() throws DataAccessException {
        int nonExistentCageNo = 9999;
        Cage cage = controller.findByCageNo(nonExistentCageNo);
        assertNull(cage, "Der bør returneres null for ugyldigt bur-nummer");
    }

    @Test
    void testConfirmRegistration_ShouldReturnFalse() {
        assertFalse(controller.confirmRegistration());
    }

    @Test
    void testGetEmployee_ShouldReturnNull() {
        assertNull(controller.getEmployee("1"));
    }

    @Test
    void testGetProduct_ShouldReturnNull() {
        assertNull(controller.getProduct(1));
    }
}
