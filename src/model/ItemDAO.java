package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DBConnection;
import model.Item;

public class ItemDAO {
	private static final String DELETE_ITEM = "DELETE FROM item WHERE sn=?";
		
	private static final String INSERT_ITEM = "INSERT INTO item(sn,class_sn,name) VALUES(item_sn_seq.NEXTVAL,?,?)";
		
	private static final String SELECT_ITEM_COUNT = "SELECT COUNT(*) FROM item";
	private static final String SELECT_ITEM = "SELECT * FROM item ORDER BY sn ASC";
	private static final String SELECT_CLASS_ITEM = "SELECT * FROM item WHERE class_sn=?";
	private static final String SELECT_SINGLE_ITEM = "SELECT * FROM item WHERE sn=?";
	
	private static final String UPDATE_ITEM = "UPDATE item SET name = ? WHERE sn = ?";

	private Connection connection = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;


	public ItemDAO() {
		connection = DBConnection.getDBConnection();
	}

	public void addItem(Item item) {
		try {
			pstmt = connection.prepareStatement(INSERT_ITEM);

			pstmt.setInt(1, item.getClass_sn());
			pstmt.setString(2, item.getName());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured."
					+ e.getMessage());
		} finally {

			DBConnection.close(rs, pstmt, null, connection);
		}
	}

	public int deleteItem(int sn) {
		int k = 0;
		try {
			pstmt = connection.prepareStatement(DELETE_ITEM);
			pstmt.setInt(1, sn);
			k = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured."
					+ e.getMessage());
		} finally {

			DBConnection.close(rs, pstmt, null, connection);
		}
		return k;
	}

	public ArrayList<Item> getItem() {
		ArrayList<Item> item_list = new ArrayList<Item>();
		try {
			pstmt = connection.prepareStatement(SELECT_ITEM);
			rs=pstmt.executeQuery();

			while (rs.next()) {
				Item item = new Item(rs.getInt("sn"), rs.getInt("class_sn"),
						rs.getString("name"));
				item_list.add(item);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException("A database error occure"
					+ e.getMessage());
		} finally {
			DBConnection.close(rs, pstmt, null, connection);
		}
		return item_list;
	}

	public ArrayList<Item> getItem(int sn, String mode) {
		ArrayList<Item> item_list = new ArrayList<Item>();
		try {
			if (mode.equals("class_sn")) {
				pstmt = connection.prepareStatement(SELECT_CLASS_ITEM);
				pstmt.setInt(1, sn);
			} else {
				pstmt = connection.prepareStatement(SELECT_SINGLE_ITEM);
				pstmt.setInt(1, sn);
			}
			rs=pstmt.executeQuery();

			while (rs.next()) {
				Item item = new Item(rs.getInt("sn"), rs.getInt("class_sn"),
						rs.getString("name"));
				item_list.add(item);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException("A database error occure"
					+ e.getMessage());
		} finally {
			DBConnection.close(rs, pstmt, null, connection);
		}
		return item_list;
	}
	public int updateItem(int sn, String name){
    	int k = 0;
		try {
			if(!name.equals("") && sn!=0){
			    pstmt = connection.prepareStatement(UPDATE_ITEM);
			    pstmt.setString(1, name);
			    pstmt.setInt(2,sn);  
			    
			    k = pstmt.executeUpdate();
			}else
				return k;			
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured." + e.getMessage());
		}finally{
			
			DBConnection.close(rs, pstmt, null, connection);
		}
		return k;
		
	}
	
	 public int getItemCount() {
	        int k = 0;
	        try {

	            pstmt = connection.prepareStatement(SELECT_ITEM_COUNT);

	            rs = pstmt.executeQuery();
	            
	            if(rs.next())
	                k = rs.getInt(1);

	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            throw new RuntimeException("A database error occured."
	                    + e.getMessage());
	        } finally {

	            DBConnection.close(rs, pstmt, null, connection);
	        }
	        return k;
	    }

}
