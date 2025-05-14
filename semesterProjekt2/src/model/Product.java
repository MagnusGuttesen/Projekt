package model;

public class Product {

	private int productId;
	private String productName;
	private int skuNo;
	private int productQty;
	
	public Product(int productId, String productName, int skuNo, int productQty) {
		
		this.productId = productId;
		this.productName = productName;
		this.skuNo = skuNo;
		this.productQty = productQty;
	}
	
	public int getProductId() {
		return productId;
	}
	
	public String getProductName() {
		return productName;
		
	}
	
	public int getSkuNo() {
		return skuNo;
	}
	
	public int getProductQty() {
		return productQty;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public void setSkuNo(int skuNo) {
		this.skuNo = skuNo;
	}
	
	public void setProductQty(int productQty) {
		this.productQty = productQty;
	}
}
