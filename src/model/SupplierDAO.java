package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DBConnection;
import model.Supplier;

public class SupplierDAO {
    private static final String DELETE_SUPPLIER = "DELETE FROM supplier WHERE sn=?";

    private static final String INSERT_SUPPLIER = "INSERT INTO supplier(sn,name) VALUES(sup_sn_seq.NEXTVAL,?)";

    private static final String SELECT_SUPPLIER = "SELECT * FROM supplier ORDER BY sn ASC";
    private static final String SELECT_SINGLE_SUPPLIER = "SELECT * FROM supplier WHERE sn=?";

    private static final String UPDATE_SUPPLIER = "UPDATE supplier SET name = ? WHERE sn = ?";

    private Connection connection = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public SupplierDAO() {
        connection = DBConnection.getDBConnection();
    }

    public void addSupplier(Supplier supplier) {
        try {
            pstmt = connection.prepareStatement(INSERT_SUPPLIER);

            pstmt.setString(1, supplier.getName());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("A database error occured."
                    + e.getMessage());
        } finally {

            DBConnection.close(rs, pstmt, null, connection);
        }
    }

    public int deleteSupplier(int sn) {
        int k = 0;
        try {
            pstmt = connection.prepareStatement(DELETE_SUPPLIER);
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

    public ArrayList<Supplier> getSupplier() {
        ArrayList<Supplier> sup_list = new ArrayList<Supplier>();

        try {
            pstmt = connection.prepareStatement(SELECT_SUPPLIER);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Supplier sup = new Supplier(rs.getInt("sn"),
                        rs.getString("name"));
                sup_list.add(sup);
            }

        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            throw new RuntimeException("A database error occured."
                    + e.getMessage());
        } finally {
            DBConnection.close(rs, pstmt, null, connection);
        }

        return sup_list;
    }

    public Supplier getSupplier(int sn) {
        Supplier sup = new Supplier(0, "");
        try {
            pstmt = connection.prepareStatement(SELECT_SINGLE_SUPPLIER);
            pstmt.setInt(1, sn);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                sup.setSn(rs.getInt("sn"));
                sup.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            // TODO: handle exception
            throw new RuntimeException("A database error occured."
                    + e.getMessage());
        } finally {
            DBConnection.close(rs, pstmt, null, connection);
        }

        return sup;
    }

    public int updateSupplier(int sn,String name) {
        int k = 0;
        try {
            pstmt = connection.prepareStatement(UPDATE_SUPPLIER);
            pstmt.setString(1, name);
            pstmt.setInt(2, sn);
            
            k = pstmt.executeUpdate();

        } catch (SQLException e) {
            // TODO: handle exception
            throw new RuntimeException("A database error occured."
                    + e.getMessage());
        } finally {
            DBConnection.close(rs, pstmt, null, connection);
        }
        return k;
    }

}
