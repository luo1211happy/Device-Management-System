package model;

import java.sql.*;
import java.util.*;

import model.Model;
import model.DBConnection;

public class ModelDAO {

    private static final String INSERT_MODEL = "INSERT INTO  model(sn,name,item_sn,life,hasAppendix,supplier_sn) values(model_sn_seq.NEXTVAL,?,?,?,?,?)";
    private static final String DELETE_MODEL_BY_SN = "DELETE FROM  model WHERE sn = ?";
    private static final String SELECT_MODEL = "SELECT model.*, supplier.name AS supplier FROM  model,supplier WHERE model.supplier_sn = supplier.sn ORDER BY model.sn ASC";
    private static final String SELECT_MODEL_COUNT = "SELECT COUNT(*) FROM  model";
    private static final String SELECT_MODEL_BY_ITEM_SN = "SELECT * FROM  Model WHERE item_sn = ?";
    private static final String SELECT_MODEL_BY_SUPPLIER_SN = "SELECT * FROM  model WHERE supplier_sn = ?";
    private static final String SELECT_MODEL_BY_SN = "SELECT * FROM model WHERE sn = ?";
    
    private static final String UPDATE_MODEL_BY_SN = "UPDATE model SET name=?,item_sn=?,life=?,hasAppendix=?,supplier_sn=? WHERE sn=?";

    private Connection connection = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public ModelDAO() {
        connection = DBConnection.getDBConnection();
    }

    public void addModel(Model model) {

        try {
            pstmt = connection.prepareStatement(INSERT_MODEL);
            pstmt.setString(1, model.getName());
            pstmt.setInt(2, model.getItem_sn());
            pstmt.setInt(3, model.getLife());
            pstmt.setString(4, model.getHasAppendix());
            pstmt.setInt(5, model.getSupplier_sn());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            throw new RuntimeException("A database error occured."
                    + e.getMessage());
        } finally {
            DBConnection.close(rs, pstmt, null, connection);
        }

    }

    public ArrayList<Model> getModel() {
        ArrayList<Model> model_list = new ArrayList<Model>();
        try {
            pstmt = connection.prepareStatement(SELECT_MODEL);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Model model = new Model(rs.getInt("sn"),rs.getString("name"), 
                		 rs.getInt("item_sn"),rs.getInt("life"),
                         rs.getString("hasAppendix"), rs.getInt("supplier_sn"), rs.getString("supplier"));
                model_list.add(model);

            }

        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            throw new RuntimeException("A database error occured."
                    + e.getMessage());
        } finally {
            DBConnection.close(rs, pstmt, null, connection);
        }

        return model_list;
    }

    public ArrayList<Model> getModel(int sn, String mode) {
        ArrayList<Model> model_list = new ArrayList<Model>();
        try {
            if (mode.equals("item_sn")) {
                pstmt = connection.prepareStatement(SELECT_MODEL_BY_ITEM_SN);
                pstmt.setInt(1, sn);
            } else if (mode.equals("supplier_sn")) {
                pstmt = connection.prepareStatement(SELECT_MODEL_BY_SUPPLIER_SN);
                pstmt.setInt(1, sn);
            }

            rs = pstmt.executeQuery();
           /*
            while (rs.next()) {
                Model model;
                model_list.add(model);
            }*/

        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            throw new RuntimeException("A database error occured."
                    + e.getMessage());
        } finally {
            DBConnection.close(rs, pstmt, null, connection);
        }

        return model_list;
    }

    public Model getModel(int sn) {
        
        try {
            pstmt = connection.prepareStatement(SELECT_MODEL_BY_SN);
            pstmt.setInt(1, sn);
            rs = pstmt.executeQuery();
            
            if(rs.next()){
            	return new Model(rs.getInt("sn"), rs.getString("name"), rs.getInt("item_sn"), rs.getInt("life"), rs.getString("hasAppendix"), rs.getInt("supplier_sn"));
            }
           
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            throw new RuntimeException("A database error occured."
                    + e.getMessage());
        } finally {
            DBConnection.close(rs, pstmt, null, connection);
        }

        return null;
    }

    public int deleteModel(int sn) {
        int k = 0;
        
        try {
            pstmt = connection.prepareStatement(DELETE_MODEL_BY_SN);
            pstmt.setInt(1, sn);
      
            k = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("A database error occured." + e.getMessage());
        } finally {
            DBConnection.close(rs, pstmt, null, connection);
        }
        return k;
    }

    public int updateModel(int sn, String name, int item_sn, int life, String hasAppendix, int supplier_sn) {
        int k = 0;
        
        try {
        	
            pstmt = connection.prepareStatement(UPDATE_MODEL_BY_SN);
            pstmt.setString(1, name);
            pstmt.setInt(2, item_sn);
            pstmt.setInt(3, life);
            pstmt.setString(4, hasAppendix);
            pstmt.setInt(5, supplier_sn);
            pstmt.setInt(6, sn);
           
            k = pstmt.executeUpdate();

        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            throw new RuntimeException("A database error occured."
                    + e.getMessage());
        } finally {
            DBConnection.close(rs, pstmt, null, connection);
        }

        return k;
    }

    public int getModelCount() {
        int k = 0;
        try {

            pstmt = connection.prepareStatement(SELECT_MODEL_COUNT);

            rs = pstmt.executeQuery();

            if (rs.next())
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
