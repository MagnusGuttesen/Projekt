package db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	private Connection connection = null;
	private static DBConnection dbConnection;

	// Oplysninger til forbindelsen til SQL Server databasen
	private static final String DBNAME = "DMA-CSD-S243_10641029";
	private static final String SERVERNAME = "hildur.ucn.dk";
	private static final int PORTNUMBER = 1433;
	private static final String USERNAME = "DMA-CSD-S243_10641029";
	private static final String PASSWORD = "Password1!";

	// Opretter forbindelse til databasen når objektet instansieres
	private DBConnection() throws DataAccessException {

		String urlString = String.format("jdbc:sqlserver://%s:%s;databaseName=%s;encrypt=false", SERVERNAME, PORTNUMBER,
				DBNAME);
		try {
			connection = DriverManager.getConnection(urlString, USERNAME, PASSWORD);
			
		} catch (SQLException e) {
			throw new DataAccessException(String.format("Could not connect to database %s@%s:%d user %s", DBNAME,
					SERVERNAME, PORTNUMBER, USERNAME), e);
		}
	}

	// Singleton pattern: sikrer at der kun oprettes én instans af DBConnection
	public static synchronized DBConnection getInstance() throws DataAccessException {
		if (dbConnection == null) {
			dbConnection = new DBConnection();
		}
		return dbConnection;
	}

	// Returnerer den aktive databaseforbindelse
	public Connection getConnection() {
		return connection;
	}

	// Lukker forbindelsen til databasen
	public void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}