package test;

import controller.ProductController;
import db.DataAccessException;
import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductControllerTest {

    private ProductController controller;

    @BeforeEach
    void setUp() throws DataAccessException {
        controller = new ProductController();
    }

    @Test
    void testFindAll_ShouldReturnProducts() throws DataAccessException {
        List<Product> products = controller.findAll();
        assertNotNull(products);
        assertTrue(products.size() > 0, "Produktlisten bør ikke være tom");
    }

    @Test
    void testFindByProductId_ValidId_ShouldReturnProduct() throws DataAccessException {
        int validId = 1; // Antag produkt med ID 1 eksisterer i databasen
        Product product = controller.findByProductId(validId);
        assertNotNull(product);
        assertEquals(validId, product.getProductId());
    }

    @Test
    void testFindByProductId_InvalidId_ShouldReturnNull() throws DataAccessException {
        int invalidId = 9999;
        Product product = controller.findByProductId(invalidId);
        assertNull(product);
    }

    @Test
    void testFindByProductName_ShouldReturnMatchingProducts() throws DataAccessException {
        String name = "Mælk"; // Antag produkt med navn "Mælk" eksisterer
        List<Product> results = controller.findByProductName(name);
        assertNotNull(results);
        assertTrue(results.stream().anyMatch(p -> p.getProductName().contains(name)));
    }

    @Test
    void testFindBySkuNo_ShouldReturnProducts() throws DataAccessException {
        int validSku = 1234; // Antag SKU eksisterer
        List<Product> results = controller.findBySkuNo(validSku);
        assertNotNull(results);
        assertTrue(results.size() > 0);
    }

    @Test
    void testFindByNameOrSku_ShouldReturnProducts() {
        List<Product> results = controller.findByNameOrSku("Mælk");
        assertNotNull(results);
    }

    @Test
    void testInsert_ShouldReturnInsertedProduct() throws DataAccessException {
        Product p = new Product();
        p.setProductId(999); // Sørg for unik ID
        p.setProductName("TestProdukt");
        p.setSkuNo(9999);
        p.setProductQty(100);
        p.setProductExp(7);

        Product inserted = controller.insert(p);
        assertNotNull(inserted);
        assertEquals("TestProdukt", inserted.getProductName());
    }
}
