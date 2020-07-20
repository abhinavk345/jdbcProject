package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*create or replace procedure p_get_EmpDetailsByNo(no in number,
        name out varchar,
        salary out number,
         desg out varchar)as
begin
select  ename,job,sal  into name,desg,salary  from emp where empno=no;
end;
/*/


public class CsProcedureTest {
  private static final  String CALL_PROCEDURE="{CALL P_GET_EMPDETAILSBYNO(?,?,?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		CallableStatement cs=null;
		try {
		  //read inputs
		  sc=new Scanner(System.in);
		  if(sc!=null) {
			  System.out.println("Enter Employee number::");
			  no=sc.nextInt();
		  }
		   //register JDBC driver s/w
		  Class.forName("oracle.jdbc.driver.OracleDriver");
		  //establish the connection
		  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		  //create CallableStatement obj
		  if(con!=null)
			  cs=con.prepareCall(CALL_PROCEDURE);
		  //set values to query params
		  if(cs!=null) {
			  cs.registerOutParameter(2, Types.VARCHAR);
			  cs.registerOutParameter(3,Types.INTEGER);
			  cs.registerOutParameter(4,Types.VARCHAR);
		  }
		  //set values to IN param
		  if(cs!=null) {
			  cs.setInt(1, no);
		  }
		  //call PL/SQL Procedure
		  if(cs!=null)
			  cs.execute();
		  //gather results from OUT params
		  if(cs!=null) {
			  System.out.println(" emp name::"+cs.getString(2));
			  System.out.println("emp salary::"+cs.getInt(3));
			  System.out.println("emp desg::"+cs.getString(4));
		  }
		}//try
		catch(SQLException se) {
			if(se.getErrorCode()==1403)
				System.out.println("No Data found");
			//se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close jdbc objs
			try {
				if(cs!=null)
					cs.close();
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
