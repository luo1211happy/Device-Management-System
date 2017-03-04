package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class KeeperDAO {
	
	private Connection connection=null;
	private PreparedStatement pstmt=null;
	private Statement stmt = null;
	private ResultSet rs=null;
	
	public KeeperDAO(){
		connection = DBConnection.getDBConnection();
	}
	
	public Keeper query_keeper(String sn, String password){
		
		Keeper keeper = null;
		try {
			pstmt = connection.prepareStatement(QUERY_PSTMT);
			pstmt.setString(1, sn);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			while(rs.next()){
				keeper = new Keeper(rs.getString("sn"),rs.getString("name"),rs.getString("password"),rs.getString("authority"),rs.getInt("department_sn"));
			}
					
			return keeper;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured." + e.getMessage());
		}finally{
			DBConnection.close(rs, pstmt, stmt, connection);
		}
	}
	
    
    public ArrayList<Keeper> query_keeper(){
		
		Keeper keeper = null;
		ArrayList<Keeper> keeper_list = new ArrayList<Keeper>();
		try {			
			pstmt = connection.prepareStatement(SELECT_ALL_KEEPER);			
			rs = pstmt.executeQuery();
			while(rs.next()){
				keeper = new Keeper(rs.getString("sn"),rs.getString("name"),rs.getString("password"),rs.getString("authority"),rs.getInt("department_sn"));
				keeper_list.add(keeper);
			}
					
			return keeper_list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured." + e.getMessage());
		}finally{
			DBConnection.close(rs, pstmt, stmt, connection);
		}
	}
    
    public ArrayList<Keeper> query_adminer(){
		
		Keeper keeper = null;
		ArrayList<Keeper> keeper_list = new ArrayList<Keeper>();
		try {			
			pstmt = connection.prepareStatement(SELECT_ALL_ADMIN);			
			rs = pstmt.executeQuery();
			while(rs.next()){
				keeper = new Keeper(rs.getString("sn"),rs.getString("name"),rs.getString("password"),rs.getString("authority"),rs.getInt("department_sn"));
				keeper_list.add(keeper);
			}
					
			return keeper_list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured." + e.getMessage());
		}finally{
			DBConnection.close(rs, pstmt, stmt, connection);
		}
	}
    
    public ArrayList<Keeper> query_keeper(int dep_sn){
		
		Keeper keeper = null;
		ArrayList<Keeper> keeper_list = new ArrayList<Keeper>();
		try {			
			pstmt = connection.prepareStatement(SELECT_DEP_KEEPER);	
			pstmt.setInt(1, dep_sn);
			rs = pstmt.executeQuery();
			while(rs.next()){
				keeper = new Keeper(rs.getString("sn"),rs.getString("name"),rs.getString("password"),rs.getString("authority"),rs.getInt("department_sn"));
				keeper_list.add(keeper);
			}
					
			return keeper_list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured." + e.getMessage());
		}finally{
			DBConnection.close(rs, pstmt, stmt, connection);
		}
	}
	
	
    public void insert_keeper(Keeper keeper){
		
		try {
			pstmt = connection.prepareStatement(INSERT_PSTMT);
			pstmt.setString(1, keeper.getSn());
			pstmt.setString(2, keeper.getName());
			pstmt.setString(3, keeper.getPassword());
			pstmt.setString(4, keeper.getAuthority());
			pstmt.setInt(5, keeper.getDep_sn());
			pstmt.executeUpdate();
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured." + e.getMessage());
		}finally{
			
			DBConnection.close(rs, pstmt, stmt, connection);
		}
	}
    
    public void update_info(Keeper keeper){
		
		try {			
				pstmt = connection.prepareStatement(UPDATE_PSTMT);			
				pstmt.setString(1, keeper.getName());
				pstmt.setString(2, keeper.getPassword());
				pstmt.setString(3, keeper.getAuthority());
				pstmt.setInt(4, keeper.getDep_sn());
				pstmt.setString(5, keeper.getSn());
				pstmt.executeUpdate();
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured." + e.getMessage());
		}finally{
			
			DBConnection.close(rs, pstmt, stmt, connection);
		}
	}
    
    public void delete_keeper(String sn){
		
		try {			
				pstmt = connection.prepareStatement(DELETE_KEEPER);			
				pstmt.setString(1, sn);
				pstmt.executeUpdate();
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured." + e.getMessage());
		}finally{
			
			DBConnection.close(rs, pstmt, stmt, connection);
		}
	}
	
    public static  final String INSERT_PSTMT = "INSERT INTO keeper(sn,name,password,authority,department_sn) VALUES(?,?,?,?,?)";
	public static  final String QUERY_PSTMT = "SELECT * FROM keeper WHERE sn=? AND password=?";
	public static  final String UPDATE_PSTMT = "UPDATE keeper SET name=?, password=?, authority=?, department_sn=? WHERE sn = ?";
	public static  final String SELECT_ALL_ADMIN = "SELECT * FROM keeper WHERE authority IN ('M','B','S')";
	public static  final String SELECT_ALL_KEEPER = "SELECT * FROM keeper WHERE authority = 'N'";
	public static  final String SELECT_DEP_KEEPER = "SELECT * FROM keeper WHERE authority = 'N' AND department_sn = ?";
	public static  final String DELETE_KEEPER = "DELETE FROM keeper WHERE sn = ?";
}
