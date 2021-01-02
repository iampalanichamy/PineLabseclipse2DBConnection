package com.test.main;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.sql.SQLException;

import com.test.utility.DBQueries;
import com.test.utility.DBValidationQuery;

public class DBConnectionTest {
	
	@BeforeTest
	public void doNothing() {
		System.out.println("Doing nothing");
	}
	
	@Test
	public void testDatabase() {

		try {
			DBValidationQuery.Queryresult(DBQueries.usePlutusHUBDB());
			String result = DBValidationQuery.Queryresult(DBQueries.getHardwareIdfromClient("100003343")).getString(1);
			System.out.println(result);
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
