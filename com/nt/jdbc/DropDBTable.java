package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DropDBTable {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		int count=0;
		String tabName=null;
		Scanner sc=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter table name::");
				tabName=sc.next();
			}
		  //register JDBC driver
			Class.forName("oracle1.jdbc.driver.OracleDriver");
		  //Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		   //create STatement object
			if(con!=null)
				st=con.createStatement();
			//send and execute SQL Query in Db s/w
			if(st!=null) 
				count=st.executeUpdate("DROP TABLE "+tabName);
			//process the Result
			if(count==0)
				System.out.println("table dropped");
		}//try
		catch(SQLException | ClassNotFoundException e) {
		  e.printStackTrace();
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close jdbc objs
			try {
				if(st!=null)
					st.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finally

	}//main
}//class
