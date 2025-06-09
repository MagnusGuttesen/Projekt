package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.ProductDB;
import model.Product;


public class ProductDB implements ProductDBIF {

	private static final String FIND_ALL_Q = "select productid, productname, skuno, productqty, productexp from product";
	private PreparedStatement findAllPS;

	private static final String FIND_BY_PRODUCTNAME_OR_SKUNO_Q = FIND_ALL_Q
			+ " where productname like ? or skuno = ?";
	private PreparedStatement findByProductNameOrSkuNoPS;

	private static final String FIND_BY_PRODUCTID_Q = FIND_ALL_Q + " where productid = ?";
	private PreparedStatement findByProductIdPS;

	private static final String INSERT_Q = "insert into product (productid, productname, skuno, productqty, productexp) values (?, ?, ?, ?, ?)";
	private PreparedStatement insertPS;
	
	private static ProductDB instance;


	// Konstruktør der initialiserer forbindelsen og PreparedStatements
	public ProductDB() throws DataAccessException {
		
		init();
	}

	private void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findAllPS = con.prepareStatement(FIND_ALL_Q);
			findByProductNameOrSkuNoPS = con.prepareStatement(FIND_BY_PRODUCTNAME_OR_SKUNO_Q);
			findByProductIdPS = con.prepareStatement(FIND_BY_PRODUCTID_Q);
			insertPS = con.prepareStatement(INSERT_Q);
			
		} catch (SQLException e) {
			 e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}

	// Søger produkter med navn, der matcher søgeteksten
	@Override
	public List<Product> findByProductName(String productName, boolean fullAssociation) throws DataAccessException {
		List<Product> res = null;
		try {
			findByProductNameOrSkuNoPS.setString(1, "%" + productName + "%");
			if (productName != null && productName.length() == 1) {
				findByProductNameOrSkuNoPS.setString(2, productName);
			} else {
				findByProductNameOrSkuNoPS.setString(2, "");
			}
			findByProductNameOrSkuNoPS.setString(3, "%" + productName + "%");
			ResultSet rs = findByProductNameOrSkuNoPS.executeQuery();
			res = buildObjects(rs, fullAssociation);
		} catch (SQLException e) {
			 e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		return res;
	}

	// Finder et produkt ud fra dets ID
	@Override
	public Product findByProductId(int productId, boolean fullAssociation) throws DataAccessException {
		Product res = null;
		try {
			findByProductIdPS.setInt(1, productId);
			ResultSet rs = findByProductIdPS.executeQuery();
			if (rs.next()) {
				res = buildObject(rs, fullAssociation);
			}
		} catch (SQLException e) {
			 e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		return res;
	}

	// Finder produkter ud fra deres SKU nummer
	public List<Product> findByProductSkuNo(int skuNo, boolean fullAssociation) throws DataAccessException {
		List<Product> res = null;
		try {
			findByProductNameOrSkuNoPS.setString(1, "%" + skuNo + "%");
			ResultSet rs = findByProductNameOrSkuNoPS.executeQuery();
			res = buildObjects(rs, fullAssociation);
		} catch (SQLException e) {
			 e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
		return res;
	}

	// Konverterer flere rækker fra resultatsættet til en liste af produkt objekter
	private List<Product> buildObjects(ResultSet rs, boolean fullAssociation) throws DataAccessException {
		List<Product> res = new ArrayList<>();
		try {
			while (rs.next()) {
				Product currProduct = buildObject(rs, fullAssociation);
				res.add(currProduct);
			}
		} catch (SQLException e) {
			 e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return res;
	}

	// Konverterer én række i resultatsættet til et produkt objekt
	private Product buildObject(ResultSet rs, boolean fullAssociation) throws DataAccessException {
		Product currProduct = new Product();
		try {
			currProduct.setProductName(rs.getString("productname"));
			currProduct.setSkuNo(rs.getInt("skuno"));
			currProduct.setProductQty(rs.getInt("productqty"));
			currProduct.setProductExp(rs.getInt("productExp"));
			currProduct.setProductId(rs.getInt("productId"));
			
		} catch (SQLException e) {
			 e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		return currProduct;
	}

	@Override
	public List<Product> findAll(boolean fullAssociation) throws DataAccessException {
		ResultSet rs;
		try {
			rs = this.findAllPS.executeQuery();
		} catch (SQLException e) {
			 e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_READ_RESULTSET, e);
		}
		List<Product> res = buildObjects(rs, fullAssociation);
		return res;
	}
	@Override
	public Product insert(Product product) throws DataAccessException {
		// productname, skuno, productqty, productid, productexp
		try {
			insertPS.setString(1, product.getProductName());
			insertPS.setInt(2, product.getSkuNo());
			insertPS.setInt(3, product.getProductQty());
			insertPS.setInt(4, product.getProductId());
			insertPS.setInt(5, product.getProductExp());

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_PS_VARS_INSERT, e);
		}
		try {
			insertPS.executeUpdate();
		} catch (SQLException e) {
			 e.printStackTrace();
			throw new DataAccessException(DBMessages.COULD_NOT_INSERT, e);
		}

		return product;
	}
	

	public List<Product> findByNameOrSku(String search) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

