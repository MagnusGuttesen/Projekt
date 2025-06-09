package controller;

import java.util.List;

import db.DataAccessException;
import db.ProductDB;
import model.Product;

public class ProductController {

    // Reference til ProductDB
    ProductDB productDB;

    public ProductController() throws DataAccessException {
        productDB = new ProductDB();
    }

    // Søger produkter baseret på navn med fulde relationer
    public List<Product> findByProductName(String productName) throws DataAccessException {
        List<Product> res = productDB.findByProductName(productName, true);
        return res;
    }

    // Returnerer alle produkter uden fulde relationer
    public List<Product> findAll() throws DataAccessException {
        List<Product> res = productDB.findAll(false);
        return res;
    }

    // Finder ét produkt baseret på dets ID
    public Product findByProductId(int productId) throws DataAccessException {
        Product res = productDB.findByProductId(productId, true);
        return res;
    }

    // Søger produkter baseret på SKU nummer
    public List<Product> findBySkuNo(int skuNo) throws DataAccessException {
        List<Product> res = productDB.findByProductSkuNo(skuNo, true);
        return res;
    }

    // Indsætter et nyt produkt i databasen
    public Product insert(Product product) throws DataAccessException {
        Product res = productDB.insert(product);
        return res;
    }

    // Søger produkter baseret på enten navn eller SKU
    public List<Product> findByNameOrSku(String search) {
        return productDB.findByNameOrSku(search);
    }
}
