package test;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

import db.DBConnection;

public class DbConnectionTest {


	@Test
	public void testGetConnection() {

		try {
			Connection c = DBConnection.getInstance().getConnection();
			assertNotNull(c);
		} catch (Exception e) {
			fail("issues with dbconnection");
		}
	}

}
