package ui;

import controller.CageController;
import model.Employee;
import model.Product;

//UI klasse der formidler kald videre til CageController
public class CageUI {

	private CageController cageController;

	// Konstruktor modtager en instans af CageController, som bruges til metodekald
	public CageUI(CageController cageController) {
		this.cageController = cageController;
	}

	// Henter medarbejderinformation via controlleren
	public Employee findEmployee(String employeeId) {
		return cageController.getEmployee(employeeId);
	}

	// Henter produktinformation via controlleren
	public Product findProduct(int productId) {
		return cageController.getProduct(productId);
	}

	// Bekræfter om registreringen er gennemført
	public boolean confirmRegistration() {
		return cageController.confirmRegistration();
	}
}
