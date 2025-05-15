package db;

import java.util.List;

import db.DataAccessException;
import model.Product;

/**
 * 
 * @author knol
 * @version 2018-08-30
 */
public interface ProductDBIF {
	List<Product> findByProductName(String productName, boolean fullAssociation) throws DataAccessException;
	Product findByProductId(int productId, boolean fullAssociation) throws DataAccessException;
	List<Product> findAll(boolean fullAssociation) throws DataAccessException;
	Product insert(Product product) throws DataAccessException;
}
