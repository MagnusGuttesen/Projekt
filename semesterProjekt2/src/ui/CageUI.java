package ui;

public class CageUI {
	
	private CageController cageController;
	
	public CageUI(CageController cageController) {
		this.cageController = cageController;
	}
	
	public Employee findEmployee(String employeeId) {
		return cageController.getEmployee(employeeId);
	}
	
	public Product findProduct(int productId) {
		return cageController.getProduct(productId);
	}
	
	public boolean confirmRegistration() {
		return cageController.confirmRegistration();
	}
}
