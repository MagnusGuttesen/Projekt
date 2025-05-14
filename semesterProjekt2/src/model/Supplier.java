package model;

public class Supplier {
	
	private int cvrNo;
	private int phoneNo;
	private String email;
	
	public Supplier(int cvrNo, int phoneNo, String email) {
		
		this.cvrNo = cvrNo;
		this.phoneNo = phoneNo;
		this.email = email;
	}
	
	public int getCvrNo() {
		return cvrNo;
	}
	
	public int getPhoneNo() {
		return phoneNo;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setCvrNo(int cvrNo) {
		this.cvrNo = cvrNo;
	}
	
	public void setPhoneNo(int phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}
