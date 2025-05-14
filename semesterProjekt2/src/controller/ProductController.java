package controller;

public class ProductController implements ProductControllerIF {
	
	ProductDB productDB;
	
	@Override
	public Product findProductById(int id) throws DataAccessException {
		return productDB.findProductById(id);
	}
	
}
