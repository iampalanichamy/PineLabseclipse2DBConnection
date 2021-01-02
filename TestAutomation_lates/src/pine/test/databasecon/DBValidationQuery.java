/*
 * Version1
 * Author: Ankush Kalash
 * Created Date: 16 Dec 2016
 * Details: This file is for using the database connection to run a SQL query.
 * 			This returns the SQL query result in a resultset.
 */

package pine.test.databasecon;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBValidationQuery {
	static DBConnection dc = new DBConnection();
	public static ResultSet Queryresult (final String Query) throws SQLException
	{
		Connection con = dc.getConnection();
		ResultSet rslt = null;
		if(con!=null)
		{
			Statement stmt;
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rslt = stmt.executeQuery(Query);
			rslt.next();
		}
		else
		{
			System.out.println("Could not get connection");
		}
		return(rslt);
	}
	public static void UpdateQuery(final String Query) throws SQLException
	{
		Connection con = dc.getConnection();
		if(con!=null)
		{
			Statement stmt;
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			stmt.executeUpdate(Query);
		}
		else
		{
			System.out.println("Could not get connection");
		}
	}

}
