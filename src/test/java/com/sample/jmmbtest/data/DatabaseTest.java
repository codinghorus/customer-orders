package com.sample.jmmbtest.data;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

public class DatabaseTest {

	@Test
	public void getConnectionTest() throws SQLException {
		Connection conn = Database.getConnection();
		
		assertTrue(conn != null);
	}

}
