package com.youtube.dao;

import java.sql.Connection;

import javax.naming.*;
import javax.sql.*;
/**
 * This class returns the Oracle database connect object from
 * a Localhost Oracle Express database
 * 
 * The method and variable in this class are static to save resources
 * You only need one instance of this class running.
 * **/
public class OracleSani005 {

	private static DataSource OracleSani005 = null;
	private static Context context = null;
	
	/**
	 * This is a public method that will return the Sani005 database connection.
	 * 
	 * On Episode 5, I discussed that this method should not be private instead of public.
	 * This will make sure all sql/database relate code be place in the dao package.
	 * I am not doing this because I do not want to break the previous code... since this
	 * is just a tutorial project.
	 * 
	 * Pre-episode 6, updated this to a private scope, as it should be. That means, V1_inventory
	 * and V1_status methods needs to be updated.
	 * 
	 * @return Database object
	 * @throws Exception
	 */
	public static DataSource OracleSani005Conn() throws Exception {
		/**
		 * check to see if the database object is already defined...
		 * if it is, then return the connection, no need to look it up again.
		 */
		
		if (OracleSani005 != null) {
			return OracleSani005;
		}
		
		try {
			
			/**
			 * This only needs to run one time to get the database object.
			 * context is used to lookup the database object in weblogic
			 * Sani005 will hold the database object
			 */
			if (context == null) {
				context = new InitialContext();
			}
			OracleSani005 = (DataSource) context.lookup("Sani005Oracle");
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return OracleSani005;
	}
	
	/**
	 * This method will return the connection to the Oracle 308tube schema
	 * Note that the scope is protected which means only java class in the
	 * dao package can use this method.
	 * 
	 * @return Connection to 308tube Oracle database.
	 */
	protected static Connection oraclePcPartsConnection() {
		Connection conn = null;
		try {
			conn = OracleSani005Conn().getConnection();
			return conn;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
}
