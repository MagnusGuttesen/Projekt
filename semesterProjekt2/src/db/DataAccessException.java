package db;

// Exception der bruges til at håndtere databasefejl
public class DataAccessException extends Exception {
	private static final long serialVersionUID = 1L;

	// Konstruktør der tillader at tilføje en fejlbesked
	public DataAccessException(String message, Throwable e) {
		super(message, e);
	}
}