package db;

import java.util.List;

import model.Product;

public interface ProductDBIF {
	List<Product> findAll(boolean fullAssociation) throws DataAccessException;
	List<Product> findByProductName(String productName, boolean fullAssociation) throws DataAccessException;
	Product findByProductId(int productId, boolean fullAssociation) throws DataAccessException;
	Product insert(Product product) throws DataAccessException;
}