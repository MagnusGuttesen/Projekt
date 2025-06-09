package model;

/*
  Repræsenterer et produkt i systemet, som fx kan indsættes i databasen eller vises i brugergrænsefladen.
 */
public class Product {

	private int productId;         // Unik ID for produktet i databasen
	private String productName;    // Produktets navn
	private int skuNo;             // SKU nummer 
	private int productQty;        // Antal produkter på lager
	private int productExp;        // Udløbsinformation som et heltal (fx antal dage til udløb)

	/*
	  Denne konstruktør bruges, når et nyt produkt skal oprettes manuelt i koden, 
	  fx når et produkt registreres via et UI og derefter indsættes i databasen.
	  
	  Eksempel: bruges i ProductController.insert() for at oprette et nyt Product-objekt.
	 */
	public Product(int productId, String productName, int skuNo, int productQty, int productExp) {
		this.productId = productId;
		this.productName = productName;
		this.skuNo = skuNo;
		this.productQty = productQty;
		this.productExp = productExp;
	}
	
	/*
	  Denne tomme konstruktør bruges i databasen, når data hentes fra en ResultSet og objektet 
	  konstrueres trinvis med setters.
	  
	  Eksempel: bruges i ProductDB.buildObject() til at opbygge produktet felt for felt.
	 */
	public Product() {
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

	public int getProductExp() {
		return productExp;
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

	public void setProductExp(int productExp) {
		this.productExp = productExp;
	}

	
	//Bruges i fx lister og visninger i UI til at vise en kort beskrivelse af produktet.
	 
	@Override
	public String toString() {
	    return productName + " (SKU: " + skuNo + ", ID: " + productId + ")";
	}
}
