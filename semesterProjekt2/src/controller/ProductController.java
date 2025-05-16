package controller;

import java.util.List;

import db.DataAccessException;
import db.ProductDB;
import model.Product;


public class ProductController {
	
	ProductDB productDB;
	
	public ProductController() throws DataAccessException {
		productDB = new ProductDB();

	}

	public List<Product> findByProductName(String productName) throws DataAccessException {
		List<Product> res = productDB.findByProductName(productName, true);
		return res;
	}

	public List<Product> findAll() throws DataAccessException {
		List<Product> res = productDB.findAll(false);
		return res;
	}

	public Product findByProductId(int productId) throws DataAccessException {
		Product res = productDB.findByProductId(productId, true);
		return res;
	}

	public List<Product> findBySkuNo(int skuNo) throws DataAccessException {
		List<Product> res = productDB.findByProductSkuNo(skuNo, true);
		return res;
		
	}
	public Product insert(Product product) throws DataAccessException {
		Product res = productDB.insert(product);
		return res;
	}

}

	
