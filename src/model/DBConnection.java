package model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

public class DBConnection {
	
	static Connection getDBConnection(){
		Connection connection=null;
		DataSource ds=null;
   
	    try{
	    	//Retrieve the DataSource from JNDI
	    
	        Context ctx=new InitialContext();
	        if(ctx==null){
		        throw new RuntimeException("JNDI Context could not be found.");
	        }
	        ds=(DataSource)ctx.lookup("java:comp/env/jdbc/emDB");
	        if(ds==null){
		        throw new RuntimeException("DataSource could not be found.");
	        }	       
	        //Get a database connection
	        connection=ds.getConnection();
	        
	    }catch(SQLException e){
	    	System.out.println(e);
	    }catch(NamingException e){
	    	System.out.println(e);
	    }
	    return connection;
	}
	
public static void close(ResultSet rs, PreparedStatement pstmt, Statement stmt, Connection conn){
		
		
		if(rs !=null){
			try{
				rs.close();
			}catch(SQLException se){
				se.printStackTrace(System.err);
			}
		}
		if(pstmt != null){
			try{
				pstmt.close();
			}catch(SQLException se){
				se.printStackTrace(System.err);
			}
		}
		if(stmt != null){
			try{
				stmt.close();
			}catch(SQLException se){
				se.printStackTrace(System.err);
			}
		}
		if(conn !=null){
			try{
				if(!conn.isClosed())
				    conn.close();
			}catch(SQLException e){
				e.printStackTrace(System.err);
			}
		}
	}
	
	public static void close(CallableStatement cstmt){
		if(cstmt != null){
			try{
				cstmt.close();
			}catch(SQLException se){
				se.printStackTrace(System.err);
			}
		}
	}


}
