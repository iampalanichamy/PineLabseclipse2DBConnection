/*
 * Version1
 * Author: Ankush Kalash
 * Created Date: 16 Dec 2016
 * Details: This file is for making the connections with PostGres database. This returns the database connection.
 */

package pine.test.databasecon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private String user = PropertyFileReader.getDbUser();
	private String password = PropertyFileReader.getDbPassword();
	private String jdbcDriver=PropertyFileReader.getJdbcDriver();
	private String jdbcUrl = PropertyFileReader.getJdbcUrl();

	private Connection con = null;
	public Connection getConnection()
	{
		if(con!=null)
		{
			return con;
		}
		try
		{
			Class.forName(jdbcDriver);
		}
		catch(java.lang.ClassNotFoundException e)
		{
			System.err.print("ClassNotFoundException:");
			System.err.println(e.getMessage());
		}
		try
		{
			con=DriverManager.getConnection(jdbcUrl,user,password);
		}
		catch(SQLException ex)
		{
			System.err.println("SQLException:" +ex.getMessage());
		}
		return con;
	}
}